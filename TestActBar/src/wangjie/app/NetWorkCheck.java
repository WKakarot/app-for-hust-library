package wangjie.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetWorkCheck {
	static private boolean isNetworkConnected(Context context) { 
		if (context != null) { 
		    ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
		         .getSystemService(Context.CONNECTIVITY_SERVICE); 
		    NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
		    if (mNetworkInfo != null) { 
		        return mNetworkInfo.isAvailable(); 
		    } 
		} 
		return false; 
    }
	
	static public void checkNet(Context context) {
		if (!isNetworkConnected(context)) 
			Toast.makeText(context, "无法连接网络", Toast.LENGTH_SHORT).show();
	}

}
