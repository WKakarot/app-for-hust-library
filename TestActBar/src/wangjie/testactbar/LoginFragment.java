package wangjie.testactbar;

import wangjie.asynctask.LoginTask;
import wangjie.infotypes.LoginInfo;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
	private TextView tv;
	private Button loginBt, cancelBt;
	private EditText name, pwd;
	private MainActivity ma;
	
	public LoginFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.login_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
					
	    ma = (MainActivity)getActivity();
	    tv = (TextView) ma.findViewById(R.id.loginres);
	    loginBt = (Button)ma.findViewById(R.id.ButtonLogin);
	    cancelBt = (Button)ma.findViewById(R.id.ButtonCancel);
	    name = (EditText)ma.findViewById(R.id.username);
	    pwd = (EditText)ma.findViewById(R.id.passwd);
	    
	    
	    loginBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = name.getText().toString();
				String passwd  = pwd.getText().toString();
				if (username.isEmpty() || passwd.isEmpty()) {
					Toast.makeText(ma.getApplicationContext(), "ÐÕÃûºÍ¿¨ºÅ²»ÄÜÎª¿Õ", Toast.LENGTH_SHORT).show();
				}
				else if (username.length() < 2) {
					Toast.makeText(ma.getApplicationContext(), "ÐÕÃûÌ«¶Ì", Toast.LENGTH_SHORT).show();
				}
				else if (passwd.length() < 12) {
					Toast.makeText(ma.getApplicationContext(), "¿¨ºÅÌ«¶Ì", Toast.LENGTH_SHORT).show();
				}
				else  {
                    beginLogin(username, passwd);
				}
			}
	    	
	    });
	    
	    cancelBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				name.setText("");
				pwd.setText("");
			}
	    	
	    });
	}
	
	public void beginLogin(String name, String pwd) {
		LoginTask task = new LoginTask(this);
		task.execute(name, pwd);
		tv.setText(R.string.loading);
	}
    
	public void checkResult(LoginInfo result) {
		if (result == null){
			tv.setText(R.string.loadfailed);
		}
		else {
	//		tv.setText(result.getName());
			ma.showMinePage();
		}
	}
}
