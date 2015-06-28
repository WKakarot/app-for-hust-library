package wangjie.testactbar;

import java.util.ArrayList;

import wangjie.asynctask.FetchNewsTask;
import wangjie.filecache.BasicCache;
import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.listadapter.RecmdAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class NewsFragment extends Fragment{
	private TextView tv;
	private ListView lv;
    
	public NewsFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.news_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
			
		tv = (TextView) ((MainActivity)getActivity()).findViewById(R.id.news);
		tv.setText(R.string.news);

		lv = (ListView)((MainActivity)getActivity()).findViewById(R.id.newslist);
		
		if (BasicCache.isNewsValid()) {
			setViewContent(BasicCache.getNewsInfo());
		}
		else {
	        FetchNewsTask fetchTask = new FetchNewsTask(this);
	        fetchTask.execute("http://202.114.9.3/index.nsf/index2014?openform");	
	        tv.setText(R.string.loading); 
	    }
	}

    public void setViewContent(BasicPageType pageinfo) {
    	if (pageinfo == null) {
    		System.out.println("WJ----->pageinfo is null, means load failed");
    		tv.setText(R.string.loadfailed);
    	}
    	else {
    	    tv.setVisibility(View.GONE);
    	    ArrayList<BasicLinkType> arrayLink = pageinfo.getLinkList();
		    RecmdAdapter adapter = new RecmdAdapter(arrayLink, pageinfo.getTitle(), getActivity());
		    lv.setAdapter(adapter);
		}
    }

	
}
