package wangjie.testactbar;

import java.util.ArrayList;

import wangjie.asynctask.FetchRecmdTask;
import wangjie.filecache.BasicCache;
import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.listadapter.RecmdAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class RecmdFragment extends Fragment{	
	private TextView tv;
	private ListView lv;
	private BasicPageType info;
	
	public RecmdFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.recmd_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
		tv = (TextView)((MainActivity)getActivity()).findViewById(R.id.recmd);
		tv.setText(R.string.recommend);
		
	    lv = (ListView)((MainActivity)getActivity()).findViewById(R.id.recmdlist);
		
	    if (BasicCache.isRecmdValid()) {
	    	setViewContent(BasicCache.getRecmdInfo());
	    }
	    else {
	        FetchRecmdTask fetchTask = new FetchRecmdTask(this);
	        fetchTask.execute("http://ftp.lib.hust.edu.cn/screens/rank_face_hotbook.html");
	        tv.setText(R.string.loading);
	    }
	    
	}


    public void setViewContent(BasicPageType pageinfo) {
    	if (pageinfo == null) {
    		tv.setText(R.string.loadfailed);
    	}
    	else {
    		info = pageinfo;
    	    tv.setVisibility(View.GONE);
    	    ArrayList<BasicLinkType> arrayLink = pageinfo.getLinkList();
		    RecmdAdapter adapter = new RecmdAdapter(arrayLink, pageinfo.getTitle(), getActivity());
		    lv.setAdapter(adapter);
		    setListListener();
		}
    }
    
    private void setListListener() {
    	lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
                if (position != 0) {
                	BasicLinkType link = info.getLinkList().get(position-1);
                    System.out.println("WJ----->text: "+link.getText() + ", url: " + link.getUrl());
                    if (position == 1) { /* hot book rank */
                    	Intent intent = new Intent(getActivity(), HotActivity.class);
                    	getActivity().startActivity(intent);
                    }
                    else {
                        beginUniTask(position-1);
                    }
                }				
			}  		
    	});    	
    }
    
    private void beginUniTask(int pos) {
    	Intent intent = new Intent(getActivity(), UniverseActivity.class);
    	intent.putExtra("name", info.getLinkList().get(pos).getText());
    	intent.putExtra("url", info.getLinkList().get(pos).getUrl());
    	getActivity().startActivity(intent);   	
    }
	
	
}
