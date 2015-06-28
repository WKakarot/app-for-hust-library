package wangjie.testactbar;

import wangjie.asynctask.FetchRecordTask;
import wangjie.infotypes.BasicLinkType;
import wangjie.infotypes.BasicPageType;
import wangjie.listadapter.RecordAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class RecordActivity extends Activity{
    private TextView tvtishi;
    private ListView lv;
    private BasicPageType info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record);
		
		tvtishi = (TextView) findViewById(R.id.recordtishi);
		lv = (ListView) findViewById(R.id.recordlist);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		
		FetchRecordTask frt = new FetchRecordTask(this);
		frt.execute(url);
		tvtishi.setText(R.string.loading);
	}

	
	public void showRecords(BasicPageType info) {
		if (info == null) {
			tvtishi.setText(R.string.loadfailed);
		}
		else {
			this.info = info;
			tvtishi.setVisibility(View.GONE);
		    RecordAdapter adapter = new RecordAdapter(info.getLinkList(), info.getTitle(), this);
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
                	BasicLinkType link = info.getLinkList().get(position);
                    System.out.println("WJ----->text: "+link.getText() + ", url: " + link.getUrl());
                    Intent intent = new Intent(RecordActivity.this, BookDetailActivity.class);
                    intent.putExtra("url", link.getUrl());
                    startActivity(intent);
                }				
			}  		
    	});    	
    }
	
}
