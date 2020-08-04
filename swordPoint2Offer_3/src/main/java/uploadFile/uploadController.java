package uploadFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author 李振华
 * @Date 2020/7/24 16:30
 * 传统方式的文件上传，指的是我们上传的文件和访问的应用存在于同一台服务器上。
 * 并且上传完成之后，浏览器可能跳转。
 */
@Controller
public class uploadController {

    /**
     * 上传到本地文件
     * 文件上传
     */
    @RequestMapping("/fileUpload")
    public String testResponseJson(String picname, MultipartFile
            uploadFile, HttpServletRequest request) throws Exception{
        //定义文件名
        String fileName = "";
        //1.获取原始文件名
        String uploadFileName = uploadFile.getOriginalFilename();
        //2.截取文件扩展名
        String extendName = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1, uploadFileName.length());
        //3.把文件加上随机数，防止文件重复
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        //4.判断是否输入了文件名
        if(!StringUtils.isEmpty(picname)) {
            fileName = uuid+"_"+picname+"."+extendName; }else {
            fileName = uuid+"_"+uploadFileName; }
        System.out.println(fileName);
        //2.获取文件路径
        String basePath = request.getRealPath("/uploads");
        //3.解决同一文件夹中文件过多问题
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //4.判断路径是否存在
        File file = new File(basePath+"/"+datePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        //5.使用 MulitpartFile 接口中方法，把上传的文件写到指定位置

        uploadFile.transferTo(new File(file,fileName));
        return "success";

    }

        public static final String FILESERVERURL =
                "http://localhost:9090/day06_spring_image/uploads/";
        /**
         * 文件上传，保存文件到不同服务器
         */
        @RequestMapping("/fileUpload2")
        public String testResponseJson(String picName,MultipartFile uploadFile) throws Exception{
            //定义文件名
            String fileName = "";
            //1.获取原始文件名
            String uploadFileName = uploadFile.getOriginalFilename();
            //2.截取文件扩展名
            assert uploadFileName != null;
            String extendName =
                    uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
            //3.把文件加上随机数，防止文件重复
            String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            //4.判断是否输入了文件名
            if(!StringUtils.isEmpty(picName)) {
                fileName = uuid+"_"+picName+"."+extendName;
            }else {
                fileName = uuid+"_"+uploadFileName;
            }
            System.out.println(fileName);
            //5.创建 sun 公司提供的 jersey 包中的 Client 对象
            Client client = Client.create();
            //6.指定上传文件的地址，该地址是 web 路径
            WebResource resource = client.resource(FILESERVERURL+fileName);
            //7.实现上传
            String result = resource.put(String.class,uploadFile.getBytes());
            System.out.println(result);
            return "success";
        }



    }



























