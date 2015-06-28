package wangjie.asynctask;

import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.PageParser;
import wangjie.parser.SearchParser;
import wangjie.testactbar.SearchFragment;
import android.os.AsyncTask;

public class SearchTask extends AsyncTask<String, Void, BasicPageType>{
    private SearchFragment sf;
    
	public SearchTask(SearchFragment sf) {
	    this.sf = sf;
	}
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		// TODO Auto-generated method stub
		BasicPageType ret = null;
		
		HttpDownloader hd = new HttpDownloader();
		String content = hd.search(params[0]);
		if (content != null && !content.isEmpty()) {
			PageParser parser = new SearchParser(content);
			ret = parser.parseContent();
		}
		
		return ret;
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		sf.showResult(result);
	}

	
}
