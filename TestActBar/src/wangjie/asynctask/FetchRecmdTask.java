package wangjie.asynctask;

import wangjie.filecache.BasicCache;
import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.*;
import wangjie.testactbar.RecmdFragment;
import android.os.AsyncTask;

public class FetchRecmdTask extends AsyncTask<String, Void, BasicPageType> {
	private RecmdFragment recmdFragment;
	
	public FetchRecmdTask(RecmdFragment rf) {
		recmdFragment = rf;
	}
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		BasicPageType ret = null;

		    HttpDownloader hd = new HttpDownloader();	
		    String content = hd.getPage(params[0]);
		    if (content != null && !content.isEmpty()) {
		        PageParser parser = new RecmdParser(content);
	            ret = parser.parseContent();
	            BasicCache.setRecmdInfo(ret);
	            BasicCache.setRecmdValid(true);
	        }
		    
		return ret;
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		recmdFragment.setViewContent(result);
		
	}
	
	
}
