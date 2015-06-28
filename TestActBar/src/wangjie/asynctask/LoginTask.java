package wangjie.asynctask;

import wangjie.filecache.BasicCache;
import wangjie.http.HttpsDownloader;
import wangjie.infotypes.LoginInfo;
import wangjie.parser.LoginParser;
import wangjie.parser.PageParser;
import wangjie.testactbar.LoginFragment;
import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String, Void, LoginInfo>{
    private LoginFragment lf;
	
	public LoginTask(LoginFragment lf) {
		this.lf = lf;
	}
    
	@Override
	protected LoginInfo doInBackground(String... params) {
		LoginInfo info = null;
		
		HttpsDownloader hds = new HttpsDownloader();
		String content = hds.login(params[0], params[1]);

		if (content != null && !content.isEmpty()) {
			System.out.println("WJ------>page downloads success");
		    PageParser parser = new LoginParser(content);
		    info = (LoginInfo) parser.parseContent();
		    if (info.isSuccess()) {
		    	BasicCache.setLogon(true);
		    	BasicCache.setLogonInfo(info);
		    }
		}
		return info;
	}

	@Override
	protected void onPostExecute(LoginInfo result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		System.out.println("WJ------>onpost is called");
		lf.checkResult(result);
	}

	
	
}
