package wangjie.testactbar;

import java.util.ArrayList;

import wangjie.asynctask.FetchHotReaderTask;
import wangjie.asynctask.FetchReaderRank;
import wangjie.filecache.BasicCache;
import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.BasicReaderType;
import wangjie.infotypes.HotReaderInfo;
import wangjie.listadapter.HotReaderAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class HotReaderFragment extends Fragment {
	private ListView lv;
	private Spinner sp;
	private Button bt;
	private Integer idx;
	private TextView tv;
	
	public HotReaderFragment() {
		
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

		System.out.println("WJ----->onActivityCreated() in HotReader page");
		
		tv = (TextView) ((HotActivity)getActivity()).findViewById(R.id.tishi);
		
		FetchReaderRank getReaderTask = new FetchReaderRank(this);
		getReaderTask.execute("http://ftp.lib.hust.edu.cn/screens/rank_face_checkout.html");

		tv.setText(R.string.loaddates);
	}
	
	public void setSpinner() {
		tv.setText("");
		sp = (Spinner) ((HotActivity)getActivity()).findViewById(R.id.date);		
        ArrayList<String> dates = BasicCache.getReaderDates();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinlist, R.id.spindate, dates);
	    sp.setAdapter(adapter);
	    sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayList<String> dates = BasicCache.getReaderDates();
				idx = dates.size() - 1 - position;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    setButtonListener();
	}
	
	private void setButtonListener() {
		bt = (Button) ((HotActivity)getActivity()).findViewById(R.id.submit);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = BasicCache.getReader().get(idx).getUrl();
                beginTask(url);
			}
			
		});
	}	
	
	private void beginTask(String url) {
		tv.setText(R.string.loading);
		FetchHotReaderTask task = new FetchHotReaderTask(this);
		task.execute(url);		
	}
	
	public void setRankList(BasicPageType pageInfo) {	
		if (pageInfo == null) {
			tv.setText(R.string.loadfailed);
		}
		else  {
			tv.setVisibility(View.GONE);
			lv = (ListView) ((HotActivity)getActivity()).findViewById(R.id.ranklist);
    	    ArrayList<BasicReaderType> arrayLink = ((HotReaderInfo)pageInfo).getReaders();
    	    HotReaderAdapter adapter = new HotReaderAdapter(arrayLink, getActivity());
		    lv.setAdapter(adapter);
		}
		
	}	
}
