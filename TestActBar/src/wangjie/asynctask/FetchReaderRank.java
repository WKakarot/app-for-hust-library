package wangjie.asynctask;

import java.util.ArrayList;

import wangjie.filecache.BasicCache;
import wangjie.http.HttpDownloader;
import wangjie.infotypes.BasicLinkType;
import wangjie.parser.RecmdParser;
import wangjie.testactbar.HotReaderFragment;
import android.os.AsyncTask;

public class FetchReaderRank extends AsyncTask<String, Void, ArrayList<BasicLinkType>>{
    private HotReaderFragment hrf;
	
	public FetchReaderRank(HotReaderFragment hf) {
		hrf = hf;
	}
	
	@Override
	protected ArrayList<BasicLinkType> doInBackground(String... params) {
		ArrayList<BasicLinkType> ret = null;
		if (!BasicCache.isReaderValid()) {
	    	HttpDownloader hd = new HttpDownloader();
	    	String content = hd.getPage(params[0]);
	    	if (content != null && !content.isEmpty()) {
	    		RecmdParser parser = new RecmdParser(content);
	    		ret = parser.parseReader();
	    		BasicCache.setReader(ret);
	    		BasicCache.setReaderValid(true);
	    	}
	    }
		return ret;	    
	}

	@Override
	protected void onPostExecute(ArrayList<BasicLinkType> result) {
		super.onPostExecute(result);
		
		hrf.setSpinner();
	}
	

	
}
