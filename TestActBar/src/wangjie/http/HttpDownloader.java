package wangjie.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.net.http.AndroidHttpClient;

public class HttpDownloader {
    private static String httpBaseUrl;
    private static String searchBase;
    protected static AndroidHttpClient httpClient;
    
    public HttpDownloader() {
    	
    }
    
    /********************** method get page**********************************/
    public String getPage(String url) {
    	String content;
    	String curl = null;
    	url = url.trim();
    	if (url.startsWith("http://"))
    	    curl = url.trim();
    	else if (url.startsWith("/"))
    		curl = httpBaseUrl + url.trim();
    	
    	content = sendGetRequest(curl);
    	
    	return content;
    }
    
    protected String sendGetRequest(String url) {
    	
    	System.out.println("WJ------->send get request");
		String content = null;
		try {
			HttpGet get = new HttpGet(url);		
			HttpResponse response = httpClient.execute(get);
			
			//debug
			System.out.println("get " + url + "result:--->" + response.getStatusLine().toString());
			
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
			    HttpEntity entity = response.getEntity();
			    content = EntityUtils.toString(entity);
			}
			else if (code >= 300 && code < 400) {
				String relocUrl = response.getFirstHeader("location").getValue();
			    System.out.println("Relocate--->" + relocUrl);
			    content = getPage(relocUrl);
			}
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
		return content;
    }
    
    
    /************************************************************************/
    
    /*****************************search book********************************/
    public String search(String key) {
    	String content = null;
    	
    	try {
			String url = searchBase + URLEncoder.encode(key, "utf-8");
			
			content = getPage(url);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	return content;
    } 
    
    /************************************************************************/    
    
    /*************************getter and setters*****************************/
	public static AndroidHttpClient getHttpClient() {
		return httpClient;
	}

	public static void setHttpClient(AndroidHttpClient httpClient) {
		HttpDownloader.httpClient = httpClient;
	}

	public static String getHttpBaseUrl() {
		return httpBaseUrl;
	}

	public static void setHttpBaseUrl(String httpBaseUrl) {
		HttpDownloader.httpBaseUrl = httpBaseUrl;
	}

	public static String getSearchBase() {
		return searchBase;
	}

	public static void setSearchBase(String searchBase) {
		HttpDownloader.searchBase = searchBase;
	}
	
	
	/************************************************************************/
}
