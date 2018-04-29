import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 18:18 2018-04-29
 */
public class JerseyDemo {
    public static void main(String[] args) {
        try {
            Client client = new Client();

            String url = "http://localhost:8088/upload/xxx.jpg";

            WebResource resource = client.resource(url);

            String path = "G:\\BaiduYunDownload\\巴巴网\\新巴巴运动网\\图片资源\\53a7af3bN776f8035.jpg";
            File file = new File(path);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            //PUT
            resource.put(String.class,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
