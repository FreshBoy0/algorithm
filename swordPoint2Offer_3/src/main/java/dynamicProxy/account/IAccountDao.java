package dynamicProxy.account;

import java.util.List;

/**
 * @Author 李振华
 * @Date 2020/7/20 18:46
 */
public interface IAccountDao {
    /**
     * 保存
     * @param account
     */
    void save(Account account);
    /**
     * 更新
     * @param account
     */
    void update(Account account);
    /**
     * 删除
     * @param accountId
     */
    void delete(Integer accountId);
    /**
     * 根据 id 查询
     * @param accountId
     * @return
     */
    Account findById(Integer accountId);
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();
}
