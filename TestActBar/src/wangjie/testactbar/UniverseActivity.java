package wangjie.testactbar;

import java.util.ArrayList;

import wangjie.asynctask.UniverseTask;
import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.listadapter.HotListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class UniverseActivity extends Activity{
	private ListView lv;
	private TextView tv, tv2;
	private String url;
	ArrayList<String> links;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.universe);
		
		lv = (ListView)findViewById(R.id.unilist);
		tv = (TextView)findViewById(R.id.unititle);
		tv2 = (TextView)findViewById(R.id.unitishi);
		
		Intent intent = this.getIntent();
		url = intent.getStringExtra("url");
		tv.setText(intent.getStringExtra("name"));
		
		System.out.println(url + " : " + intent.getStringExtra("name"));
		
		if (url.compareTo("/search*chx/ftlist^bib80,1,0,56") == 0)
		    url = "/search*chx/ftlist%5Ebib80,1,0,56";
		
		UniverseTask task = new UniverseTask(this);
		task.execute(url);
        tv2.setText(R.string.loading);
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	public void setListView(BasicPageType res) {
        if (res == null) {
        	tv2.setText(R.string.loadfailed);
        }
        else {
        	tv2.setVisibility(View.GONE);
			lv = (ListView)findViewById(R.id.unilist);
    	    ArrayList<BasicLinkType> arrayLink = res.getLinkList();
		    HotListAdapter adapter = new HotListAdapter(arrayLink, this);
		    lv.setAdapter(adapter);       	
        }
		
	}
}
