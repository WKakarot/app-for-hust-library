package wangjie.asynctask;

import wangjie.http.HttpsDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.PageParser;
import wangjie.parser.RecordParser;
import wangjie.testactbar.RecordActivity;
import android.os.AsyncTask;

public class FetchRecordTask extends AsyncTask<String, Void, BasicPageType> {
    private RecordActivity ra;
   
    public FetchRecordTask(RecordActivity ra) {
    	this.ra = ra;
    }
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		// TODO Auto-generated method stub
		BasicPageType ret = null;
		
		HttpsDownloader hds = new HttpsDownloader();
		String content = hds.getPageS(params[0]);
		if (content != null && !content.isEmpty()) {
			PageParser parser = new RecordParser(content);
			ret = parser.parseContent();
		}
		
		return ret;
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		ra.showRecords(result);
	}

	
}
