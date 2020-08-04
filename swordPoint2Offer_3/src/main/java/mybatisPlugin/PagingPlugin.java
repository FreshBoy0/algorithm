package mybatisPlugin;

import com.mysql.jdbc.Connection;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Author 李振华
 * @Date 2020/7/23 17:44
 */
@Intercepts(
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class}
        )
)
public class PagingPlugin implements Interceptor {

        private Integer defaultPage;
        private Integer defaultPageSize;
        private Boolean defaultUseFlag;
        private Boolean defaultCheckFlag;


        @Override
        public Object intercept(Invocation invocation) throws Throwable {

                StatementHandler statementHandler = getUnProxyObject(invocation);
                MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
                String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
                if (!checkSelect(sql))
                {
                        return invocation.proceed();
                }
                BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
                Object parameterObject = boundSql.getParameterObject();
                PageParams pageParams = getPageParams(parameterObject);
                if (pageParams==null){
                        return invocation.proceed();
                }
                Integer pageNum = pageParams.getPage()==null?this.defaultPage:pageParams.getPage();
                Integer pageSize = pageParams.getPageSize()==null?this.defaultPageSize:pageParams.getPageSize();
                Boolean useFlag = pageParams.getUseFlag()==null?this.defaultUseFlag:pageParams.getUseFlag();
                Boolean checkFlag = pageParams.getCheckFlag()==null?this.defaultCheckFlag:pageParams.getCheckFlag();
                if (!useFlag){
                        return invocation.proceed();
                }
                int total = getTotal(invocation,metaStatementHandler,boundSql);
                setTotalToPageParams(pageParams,total,pageSize);

                return changeSQL(invocation,metaStatementHandler,boundSql,pageNum,pageSize);
        }

        @Override
        public Object plugin(Object target) {
                return Plugin.wrap(target, this);
        }

        @Override
        public void setProperties(Properties properties) {
                String strDefaultPage = properties.getProperty("default.page","1");
                String strDefaultPageSize = properties.getProperty("default.pageSize","50");
                String strDefaultUseFlag = properties.getProperty("default.useFlag","false");
                String strDefaultCheckFlag = properties.getProperty("default.checkFlag","false");

                this.defaultPage = Integer.parseInt(strDefaultPage);
                this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
                this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
                this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);

        }


        /**
         * 从代理对象中分理出真实对象
         * @param ivt
         * @return
         */
        private StatementHandler getUnProxyObject(Invocation ivt){
                StatementHandler statementHandler = (StatementHandler) ivt.getTarget();
                MetaObject metaStatmentHandler = SystemMetaObject.forObject(statementHandler);
                //分离代理链，由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分理出最原始的目标类
                Object object = null;
                while (metaStatmentHandler.hasGetter("h")){
                        object = metaStatmentHandler.getValue("h");
                }
                if (object==null){
                        return statementHandler;
                }
                return (StatementHandler)object;
        }

        /**
         * 判断是否select语句
         * @param sql
         * @return
         */
        private boolean checkSelect(String sql){
                String trimsql = sql.trim();
                int idx = trimsql.toLowerCase().indexOf("select");
                return idx == 0;
        }

        /**
         * 分解分页参数
         */
private PageParams getPageParams(Object parameterObject){
        if (parameterObject == null){
                return null;
        }
        PageParams pageParams = null;
        if (parameterObject instanceof Map){
                Map<String,Object> paramMap = (Map<String, Object>) parameterObject;
                Set<String> keySet = paramMap.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()){
                        String key = iterator.next();
                        Object value = paramMap.get(key);
                        if (value instanceof PageParams){
                                return (PageParams) value;
                        }
                }
        }else if (parameterObject instanceof PageParams){
                pageParams = (PageParams) parameterObject;
        }


        return pageParams;

}


        /**
         * 获取总数
         * @param ivt
         * @param metaStatementHandler
         * @param boundSql
         * @return sql查询总数
         */
        private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql) throws SQLException {

                //获取当前的mappedStatement
                MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
                //设置对象
                Configuration cfg = mappedStatement.getConfiguration();
                //当前需要执行的SQL
                String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
                //改写统计总数的SQL
                String countSql = "select count(*) as total from ("+sql+") $_paging";
                //获取拦截方法的参数
               Connection connection = (Connection) ivt.getArgs()[0];
                PreparedStatement ps = null;
                int total = 0;
                try {
                        ps = connection.prepareStatement(countSql);
                        BoundSql countBountSql = new BoundSql(cfg,countSql,boundSql.getParameterMappings(),boundSql.getParameterObject());
                        ParameterHandler handler = new DefaultParameterHandler(mappedStatement,boundSql.getParameterObject(),countBountSql);
                        handler.setParameters(ps);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()){
                                total = rs.getInt("total");
                        }


                }finally{
                //这里不能关闭连接否则后续的SQL就没办法继续了
                        if (ps!=null){
                                ps.close();
                        }
                }

                return total;
        }

        private void setTotalToPageParams(PageParams pageParams,int total,int pageSize){
                        pageParams.setTotal(total);
                        int totalPage = total%pageSize ==0 ?total/pageSize:total/pageSize + 1;
                        pageParams.setTotalPage(totalPage);
        }

        private Object changeSQL(Invocation invocation,MetaObject metaObject,BoundSql boundSql,int page,int pageSize) throws InvocationTargetException, IllegalAccessException, SQLException {
                //获取当前要执行的SQL
                String sql = (String) metaObject.getValue("delegate.boundSql.sql");
                //修改sql
                String newSQL = "select * from ("+sql+")+)$_paging_tablelimit?,?";
                //修改当前需要执行的SQL
                metaObject.setValue("delegate.boundSql",newSQL );
                //预编译当前sql并设置原有的参数
                PreparedStatement ps = (PreparedStatement) invocation.proceed();
                int count = ps.getParameterMetaData().getParameterCount();
                ps.setInt(count-1,(page-1)*pageSize);
                ps.setInt(count,pageSize);
                return ps;

        }


}
