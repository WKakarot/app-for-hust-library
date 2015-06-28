package wangjie.testactbar;

import wangjie.filecache.BasicCache;
import wangjie.http.HttpsDownloader;
import wangjie.listadapter.UserOptionsAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MineFragment extends Fragment {
	private TextView tv;
    private ListView lv;
	
	public MineFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.mine_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	    tv = (TextView) ((MainActivity)getActivity()).findViewById(R.id.mine);
        tv.setText(BasicCache.getLogonInfo().getName() + " Í¬Ñ§£¬ ÄãºÃ");
	    
        lv = (ListView) ((MainActivity)getActivity()).findViewById(R.id.useroption);
	    UserOptionsAdapter uoa = new UserOptionsAdapter(BasicCache.getLogonInfo().getLinkList(), getActivity());
	    lv.setAdapter(uoa);
	    lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position == 0) {
					logout();
				}
				else if (position == 1) {
					String url = BasicCache.getLogonInfo().getLinkList().get(1).getUrl();
					System.out.println("WJ------>url:" + url);
					showMineRecord(url);
				}
			}
	    	
	    });    
        
	}
	
	private void logout() {
		((MainActivity)getActivity()).showLoginPage();
		HttpsDownloader.getCookieStore().clear();
		BasicCache.setLogon(false);
	}
	
	private void showMineRecord(String url) {
		Intent intent = new Intent(getActivity(), RecordActivity.class);
		intent.putExtra("url", url);
		getActivity().startActivity(intent);
	} 

}
