package wangjie.asynctask;

import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.BookDetailParser;
import wangjie.parser.PageParser;
import wangjie.testactbar.BookDetailActivity;
import android.os.AsyncTask;

public class FetchBookDetail extends AsyncTask<String, Void, BasicPageType>{
    private BookDetailActivity bda;
	
	public FetchBookDetail(BookDetailActivity bda) {
		this.bda = bda;
	}
    
	@Override
	protected BasicPageType doInBackground(String... params) {
		// TODO Auto-generated method stub
		BasicPageType ret = null;
		
		HttpDownloader hds = new HttpDownloader();
		String content = hds.getPage(params[0]);
		if (content != null && !content.isEmpty()) {
			PageParser parser = new BookDetailParser(content);
			ret = parser.parseContent();
		}
		return ret;
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		bda.showBookInfo(result);
		
	}
	
}
