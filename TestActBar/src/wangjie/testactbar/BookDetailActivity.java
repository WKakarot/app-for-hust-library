package wangjie.testactbar;

import wangjie.asynctask.FetchBookDetail;
import wangjie.infotypes.BasicPageType;
import wangjie.infotypes.BookDetail;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class BookDetailActivity extends Activity {
    private TextView tv1, tv2, tv3;
    private BookDetail book;
    private Button bt;
    private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookdetail1);
		
		lv = (ListView) findViewById(R.id.storeinfolist);
		tv1 = (TextView) findViewById(R.id.detailpart1);
		tv2 = (TextView) findViewById(R.id.detailpart2);
		tv3 = (TextView) findViewById(R.id.storeinfo);
		bt = (Button) findViewById(R.id.storeinfoButton);
		bt.setVisibility(View.INVISIBLE);
	    Intent intent = this.getIntent();
	    String url = intent.getStringExtra("url");
	    
	    FetchBookDetail detailTask = new FetchBookDetail(this);
	    detailTask.execute(url);
	    
	    tv1.setText(R.string.loading);
	}
	
	public void showBookInfo(BasicPageType info) {
		if (info == null) {
			tv1.setText(R.string.loadfailed);
		}
		else {
		    book = (BookDetail)info;
		
		    tv1.setText(book.getTitle());
		    tv2.setText(book.getTable2());
		
		    bt.setVisibility(View.VISIBLE);
		    bt.setOnClickListener(new OnClickListener() {

			    @Override
			    public void onClick(View v) {
				    if (book.getBibOrder() != null) {
					    tv3.setText(book.getBibOrder());
					    System.out.println("WJ------>show biborder");
				    }
				    else {
                        setInfoList();
                        System.out.println("WJ------>show list");
                        System.out.println("WJ------>size of infolist " + book.getStoreInfo().size());
				    }
			    }			
		    });
		}
	}
	
	private void setInfoList() {
		tv3.setVisibility(View.GONE);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, book.getStoreInfo());
		lv.setAdapter(adapter);		
	}

}
