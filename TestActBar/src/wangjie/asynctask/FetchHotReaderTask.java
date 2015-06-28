package wangjie.asynctask;

import android.os.AsyncTask;
import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.HotReaderParser;
import wangjie.parser.PageParser;
import wangjie.testactbar.HotReaderFragment;

public class FetchHotReaderTask extends AsyncTask<String, Void, BasicPageType>{
    private HotReaderFragment hbf;
	
	public FetchHotReaderTask(HotReaderFragment hb) {
		hbf = hb;
	}
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		HttpDownloader hd = new HttpDownloader();
		String content = hd.getPage(params[0]);
		
		BasicPageType ret = null;
		if (content != null && !content.isEmpty()) {
			PageParser parser = new HotReaderParser(content);
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
