package wangjie.testactbar;

import wangjie.filecache.BasicCache;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class TitleFrament extends Fragment {
	private Button recmdBt, newsBt, searchBt, mineBt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.title_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		recmdBt  = (Button) getActivity().findViewById(R.id.recmdButton);
		newsBt   = (Button) getActivity().findViewById(R.id.newsButton);
		searchBt = (Button) getActivity().findViewById(R.id.searchButton);
		mineBt   = (Button) getActivity().findViewById(R.id.mineButton);
		
		recmdBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {			    
				MainActivity ma = (MainActivity) getActivity();
				if (ma.position != 0) {
					recmdBt.setTextColor(getResources().getColor(R.color.lightred));
					newsBt.setTextColor(getResources().getColor(android.R.color.black));
					searchBt.setTextColor(getResources().getColor(android.R.color.black));
					mineBt.setTextColor(getResources().getColor(android.R.color.black));
					ma.showRecmdPage(); 
			        ma.position = 0;
			        ma.setActionBarLayout();			        
				}
			}
			
		});
		
		newsBt.setOnClickListener(new OnClickListener() {
        
			@Override
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				if (ma.position != 1) {
					recmdBt.setTextColor(getResources().getColor(android.R.color.black));
					newsBt.setTextColor(getResources().getColor(R.color.lightred));
					searchBt.setTextColor(getResources().getColor(android.R.color.black));
					mineBt.setTextColor(getResources().getColor(android.R.color.black));
					ma.showNewsPage(); 
				    ma.position = 1;
				    ma.setActionBarLayout();
				}
			}
			
		});
		
		searchBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				if (ma.position != 2) {
					recmdBt.setTextColor(getResources().getColor(android.R.color.black));
					newsBt.setTextColor(getResources().getColor(android.R.color.black));
					searchBt.setTextColor(getResources().getColor(R.color.lightred));
					mineBt.setTextColor(getResources().getColor(android.R.color.black));			
					ma.showSearchPage(); 
				    ma.position = 2;
				    ma.setActionBarLayout();
				}
			}
			
		});
		
		mineBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MainActivity ma = (MainActivity) getActivity();
				if (ma.position != 3) {
					recmdBt.setTextColor(getResources().getColor(android.R.color.black));
					newsBt.setTextColor(getResources().getColor(android.R.color.black));
					searchBt.setTextColor(getResources().getColor(android.R.color.black));
					mineBt.setTextColor(getResources().getColor(R.color.lightred));					
					if (BasicCache.isLogon())
					    ma.showMinePage();
					else
						ma.showLoginPage();
				    ma.position = 3;
				    ma.setActionBarLayout();
				}
			}
			
		});
	}

	
}
