package wangjie.asynctask;

import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.HotBookParser;
import wangjie.parser.PageParser;
import wangjie.testactbar.HotBookFragment;
import android.os.AsyncTask;

public class FetchHotBookTask extends AsyncTask<String, Void, BasicPageType>{
    private HotBookFragment hbf;
	
	public FetchHotBookTask(HotBookFragment hb) {
		hbf = hb;
	}
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		HttpDownloader hd = new HttpDownloader();
		String content = hd.getPage(params[0]);
		
		BasicPageType ret = null;
		if (content != null && !content.isEmpty()) {
			PageParser parser = new HotBookParser(content);
			ret = parser.parseContent();
		}
		
		return ret;
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		hbf.setRankList(result);
	}

	
}
