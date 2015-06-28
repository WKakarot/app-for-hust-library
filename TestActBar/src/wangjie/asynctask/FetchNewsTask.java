package wangjie.asynctask;

import wangjie.filecache.BasicCache;
import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.NewsParser;
import wangjie.parser.PageParser;
import wangjie.testactbar.NewsFragment;
import android.os.AsyncTask;

public class FetchNewsTask extends AsyncTask<String, Void, BasicPageType> {
	private NewsFragment newsFragment;
	
	public FetchNewsTask(NewsFragment nf) {
		newsFragment = nf;
	}
	
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		BasicPageType ret = null;

		    HttpDownloader hd = new HttpDownloader();	
		    String content = hd.getPage(params[0]);
		    if (content != null && !content.isEmpty()) {
		        PageParser parser = new NewsParser(content);
	            ret = parser.parseContent();
	            BasicCache.setNewsInfo(ret);
	            BasicCache.setNewsValid(true);
	        }

		return ret;
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		newsFragment.setViewContent(result);
		
	}
}
