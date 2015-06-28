package wangjie.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpsDownloader extends HttpDownloader{
    private static String httpsBaseUrl;
    private static HttpContext httpContext;
    private static BasicCookieStore cookieStore;
    private static String loginUrl;
    
    public static String getLoginUrl() {
		return loginUrl;
	}

	public static void setLoginUrl(String loginUrl) {
		HttpsDownloader.loginUrl = loginUrl;
	}

	public HttpsDownloader() {
    	
    }
    
    /********************** method get page**********************************/
    public String getPageS(String url) {
    	String content;
    	String curl = null;
    	url = url.trim();
    	if (url.startsWith("http://"))
    	    curl = url.trim();
    	else if (url.startsWith("/"))
    		curl = httpsBaseUrl + url.trim();
    	
    	content = sendGetRequestS(curl);
    	
    	return content;
    	
    }

    protected String sendGetRequestS(String url) {
    	
    	System.out.println("WJ------->send get request with S");
		String content = null;
		try {
			HttpGet get = new HttpGet(url);		
			HttpResponse response = httpClient.execute(get, httpContext);
			
			//debug
			System.out.println("get " + url + ", result:--->" + response.getStatusLine().toString());
			
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
			    HttpEntity entity = response.getEntity();
			    content = EntityUtils.toString(entity);
			    
			}
			else if (code >= 300 && code < 400) {
				String relocUrl = response.getFirstHeader("location").getValue();
			    System.out.println("Relocate--->" + relocUrl);
			    content = getPageS(relocUrl);
			}
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
		return content;
    }
    
    /************************************************************************/
    
    /************************method post login*******************************/
    public String login(String name, String pwd) {
		String content = null;
		HttpPost post = new HttpPost(loginUrl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("code", pwd));
    	try {
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(post, httpContext);
			
			System.out.println("Login------>" + response.getStatusLine().toString());
			
			int code = response.getStatusLine().getStatusCode();
			if (code > 300 && code < 400) { 
				String relocUrl = response.getFirstHeader("location").getValue();
			    System.out.println("Relocate--->" + relocUrl);
			    content = getPageS(relocUrl);
		    }
			else {
				HttpEntity entity = response.getEntity();
				content = EntityUtils.toString(entity);
			}
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
    	
    	return content;
    }
    
    /************************************************************************/
    
    
    /*************************getter and setters*****************************/
	public static String getHttpsBaseUrl() {
		return httpsBaseUrl;
	}

	public static HttpContext getHttpContext() {
		return httpContext;
	}

	public static void setHttpContext(HttpContext hc) {
		httpContext = hc;
	}

	public static BasicCookieStore getCookieStore() {
		return cookieStore;
	}

	public static void setCookieStore(BasicCookieStore cs) {
		cookieStore = cs;
	}

	public static void setHttpsBaseUrl(String hsb) {
		httpsBaseUrl = hsb;
	}
    /************************************************************************/
	
}
