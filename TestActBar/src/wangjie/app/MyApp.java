package wangjie.app;

import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import wangjie.http.HttpDownloader;
import wangjie.http.HttpsDownloader;
import android.app.Application;
import android.net.http.AndroidHttpClient;
import android.widget.Toast;

public class MyApp extends Application {
     private AndroidHttpClient httpClient;
     private HttpContext httpContext;
     private BasicCookieStore cookieStore;
	
     @Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		httpClient = AndroidHttpClient.newInstance("@string/app_name");
		httpContext = new BasicHttpContext();
		cookieStore = new BasicCookieStore();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
		if (!initial()) 
			Toast.makeText(getApplicationContext(), "initial failed", Toast.LENGTH_SHORT).show();
	}

     
     /*******************init the app, config everything*****************/
 	private boolean initial() {
 		//set HttpClient as HttpDownloader's static field
 		HttpDownloader.setHttpBaseUrl("http://ftp.lib.hust.edu.cn");
 		HttpDownloader.setSearchBase("http://ftp.lib.hust.edu.cn/search*chx/X?SEARCH=");
 		HttpsDownloader.setHttpsBaseUrl("https://ftp.lib.hust.edu.cn");
        HttpsDownloader.setLoginUrl("https://ftp.lib.hust.edu.cn/patroninfo*chx~S0");
 		HttpDownloader.setHttpClient(httpClient);
 		HttpsDownloader.setCookieStore(cookieStore);
 		HttpsDownloader.setHttpContext(httpContext);
 		return true;
 	}
 	
 	/*********************************************************************/
    /*********************getters and setters*****************************/
	public AndroidHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(AndroidHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}

	public void setHttpContext(HttpContext httpContext) {
		this.httpContext = httpContext;
	}

	public BasicCookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(BasicCookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
    /***********************************************************************/ 
     
}
