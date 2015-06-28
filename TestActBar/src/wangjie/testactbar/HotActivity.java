package wangjie.testactbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class HotActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotrank);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);

		actionBar.setTitle(R.string.hotrank);
		
			
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().
				setText("book").setTabListener(new MyTabListener<HotBookFragment>(
				this, "book", HotBookFragment.class)));
		
		actionBar.addTab(actionBar.newTab().
				setText("reader").setTabListener(new MyTabListener<HotReaderFragment>(
				this, "reader", HotReaderFragment.class)));
		
	}
	
}
