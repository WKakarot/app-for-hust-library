package wangjie.testactbar;

import java.util.ArrayList;

import wangjie.asynctask.FetchHotBookTask;
import wangjie.filecache.BasicCache;
import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.RecmdPageInfo;
import wangjie.listadapter.HotListAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class HotBookFragment extends Fragment {
	private ListView lv;
	private Spinner sp;
	private Integer idx;
	private Button bt;
	private TextView tv;
	private BasicPageType info;
	
	public HotBookFragment() {
		
	}	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.hot, container, false);
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		System.out.println("WJ----->onActivityCreated() in HotBook page");
		
		tv = (TextView) ((HotActivity)getActivity()).findViewById(R.id.tishi);
		sp = (Spinner) ((HotActivity)getActivity()).findViewById(R.id.date);
		
		if (BasicCache.isRecmdValid()) {
			System.out.println("WJ------>cache is valid");
            RecmdPageInfo recmdInfo = (RecmdPageInfo)BasicCache.getRecmdInfo();
            ArrayList<String> dates = recmdInfo.getDates();
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinlist, R.id.spindate, dates);
			sp.setAdapter(adapter);
			sp.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					RecmdPageInfo recmdInfo = (RecmdPageInfo)BasicCache.getRecmdInfo();
					idx = recmdInfo.getBookRank().size() - 1 - position;
					
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}				
			});
			
            setButtonListener();
		}
		
	}	
	
	private void setButtonListener() {
		bt = (Button) ((HotActivity)getActivity()).findViewById(R.id.submit);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = ((RecmdPageInfo)BasicCache.getRecmdInfo()).getBookRank().get(idx).getUrl();
				System.out.println("WJ------>url:" + url);
				beginTask(url);
			}
			
		});
	}
	
	private void beginTask(String url) {
		tv.setText(R.string.loading);
		FetchHotBookTask task = new FetchHotBookTask(this);
		task.execute(url);		
	}
	
	public void setRankList(BasicPageType pageInfo) {

		if (pageInfo == null) {
			tv.setText(R.string.loadfailed);
		}
		else  {
			info = pageInfo;
			tv.setVisibility(View.GONE);
			lv = (ListView) ((HotActivity)getActivity()).findViewById(R.id.ranklist);
    	    ArrayList<BasicLinkType> arrayLink = pageInfo.getLinkList();
		    HotListAdapter adapter = new HotListAdapter(arrayLink, getActivity());
		    lv.setAdapter(adapter);
		    setListListener();
		    
		}
		
	}

	private void setListListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("url", info.getLinkList().get(position).getUrl());
                ((HotActivity)getActivity()).startActivity(intent);
                System.out.println("WJ------>start book activity success");
			}

			
		});
	}
}
