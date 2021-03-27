package main.http;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

    public static String MyHttpClientsPost(String url, Map<String, String> param) {
        String resultString = "";
        
        // create new HttpClients object
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		
        // create new post
		try {
			HttpPost httpPost = new HttpPost(url);

            if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}

				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
				httpPost.setEntity(entity);
			}

			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}

    // test
	public static void MyHttpClientsTest(){
		String url = "http://localhost:8801";
		Map<String,String> param = new HashMap<>();
		param.put("username", "cc");
        param.put("password", "cc");
		String result = MyHttpClientsPost(url, param);
		System.out.println(result);
	}
}
