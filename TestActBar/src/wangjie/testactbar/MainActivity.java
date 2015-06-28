package wangjie.testactbar;

import wangjie.app.NetWorkCheck;
import wangjie.filecache.BasicCache;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    public int position;
    private long exitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {    
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		NetWorkCheck.checkNet(getApplicationContext());
		
		setActionBarLayout();
        if (position == 0) {    	
        	showRecmdPage();
        	if (findViewById(R.id.recmdButton) == null)
        		System.out.println("WJ------>recmd Button is null");
        	((Button)findViewById(R.id.recmdButton)).setTextColor(getResources().getColor(R.color.lightred));
        	
        }
        else if (position == 1) {     	
        	showNewsPage();
        	((Button)findViewById(R.id.newsButton)).setTextColor(getResources().getColor(R.color.lightred));
        }
        else if (position == 2) { 	
        	showSearchPage();
        	((Button)findViewById(R.id.searchButton)).setTextColor(getResources().getColor(R.color.lightred));
        } 
        else if (position == 3) {	
        	if (BasicCache.isLogon())
        	    showMinePage();
        	else
        		showLoginPage();
        	((Button)findViewById(R.id.mineButton)).setTextColor(getResources().getColor(R.color.lightred));
        }
        
           

	}

	/********* change the current page according to the button ****************************/
	public void showLoginPage() {
		LoginFragment rf = new LoginFragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.page, rf);
		ft.commit();			
	}
	
	public void showMinePage() {
		MineFragment rf = new MineFragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.page, rf);
		ft.commit();		
	}

	public void showSearchPage() {
		SearchFragment rf = new SearchFragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.page, rf);
		ft.commit();		
	}


	public void showNewsPage() {
		NewsFragment rf = new NewsFragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.page, rf);
		ft.commit();		
	}


	public void showRecmdPage() {
		RecmdFragment rf = new RecmdFragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.page, rf);
		ft.commit();
	}
    /**************************************************************************************/

	/********* set the title in action bar ************************************************/
	public void setActionBarLayout() {
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
        	actionBar.setDisplayShowTitleEnabled(true);
        	if (position == 0)
        		actionBar.setTitle(R.string.recommend);
        	else if (position == 1)
        		actionBar.setTitle(R.string.news);
        	else if (position == 2)
        		actionBar.setTitle(R.string.search);
        	else if (position == 3)
        		actionBar.setTitle(R.string.mine);
        }				
	}
	/**************************************************************************************/
	
	/****************************handle back key*******************************************/
	@Override
	public void onBackPressed() {
		
		if((System.currentTimeMillis()-exitTime) > 2000){  
		    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();  
		    exitTime = System.currentTimeMillis();  
		}
        else {
        	finish();
		    System.exit(0);
	    }  
        
	}	
	/**************************************************************************************/
}
