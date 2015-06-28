package wangjie.testactbar;

import wangjie.asynctask.SearchTask;
import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.listadapter.HotListAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchFragment extends Fragment {
    private Button bt;
    private EditText et;
    private TextView tv;
    private ListView lv;
	private BasicPageType info;
    
	public SearchFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.search_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		bt = (Button) ((MainActivity)getActivity()).findViewById(R.id.ButtonSearch);
		lv = (ListView) ((MainActivity)getActivity()).findViewById(R.id.searchlist);
		et = (EditText) ((MainActivity)getActivity()).findViewById(R.id.searchkey);
		tv = (TextView) ((MainActivity)getActivity()).findViewById(R.id.searchtishi);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String key = et.getText().toString();
				if (key.isEmpty()) {
					Toast.makeText(getActivity(), "关键字不能为空", Toast.LENGTH_SHORT).show();
				}
				else {
				    beginSearch(key);
				}
			}
			
		});
	
	}

	protected void beginSearch(String key) {
		// TODO Auto-generated method stub
		SearchTask task = new SearchTask(this);
		task.execute(key);
		tv.setText(R.string.loading);
	}

	public void showResult(BasicPageType info) {
		if (info == null) {
			tv.setTag(R.string.loadfailed);
		}
		else {
			this.info = info;
			tv.setVisibility(View.GONE);
			HotListAdapter adapter = new HotListAdapter(info.getLinkList(), getActivity());
			lv.setAdapter(adapter);
			setListListener();
		}
	}

    private void setListListener() {
    	lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
                BasicLinkType link = info.getLinkList().get(position);
                System.out.println("WJ----->text: "+link.getText() + ", url: " + link.getUrl());
                showBookDetail(link.getUrl());    			
			}  		
    	});    	
    }

	protected void showBookDetail(String url) {
		// TODO Auto-generated method stub
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);		
	}
    
    
}
