package wangjie.asynctask;

import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicPageType;
import wangjie.parser.UniPageParser;
import wangjie.testactbar.UniverseActivity;
import android.os.AsyncTask;

public class UniverseTask extends AsyncTask<String, Void, BasicPageType> {
    private UniverseActivity ua;
    
    public UniverseTask(UniverseActivity ua) {
    	this.ua = ua;
    }
	
	@Override
	protected BasicPageType doInBackground(String... params) {
		BasicPageType ret = null;
		
		HttpDownloader hd = new HttpDownloader();
		String content = hd.getPage(params[0]);
		
		if (content == null || content.isEmpty())
			return ret;
		
		String filename = params[0];
		if (params[0].compareTo("/search*chx/X?%E8%8B%B1%E8%AF%AD%E5%9B%9B%E7%BA%A7&SORT=D") == 0)
			filename = "/cet4.html";
		if (params[0].compareTo("/search*chx/X?%E8%8B%B1%E8%AF%AD%E5%85%AD%E7%BA%A7&SORT=D") == 0)
			filename = "/cet6.html";
		if (params[0].compareTo("/search*chx/ftlist%5Ebib80,1,0,56") == 0)
			filename = "/renwen.html";
		UniPageParser parser = new UniPageParser(content);
	    ret = parser.parseUni(content, filename);
		return ret;	
	}

	@Override
	protected void onPostExecute(BasicPageType result) {
        ua.setListView(result);
	} 
}
