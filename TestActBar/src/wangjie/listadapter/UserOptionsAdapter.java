package wangjie.listadapter;

import java.util.ArrayList;

import wangjie.infotypes.BasicLinkType;
import wangjie.testactbar.R;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

public class UserOptionsAdapter extends BaseAdapter {
    private ArrayList<BasicLinkType> arrayLink;
	private Context context;
	private SparseArray<View> rows;
	
	public UserOptionsAdapter(ArrayList<BasicLinkType> arr, Context ctx) {
		arrayLink = arr;
		context = ctx;
		rows = new SparseArray<View>();
	}
	
	
	@Override
	public int getCount() {
		
		return arrayLink.size();
	}

	@Override
	public Object getItem(int position) {
		
		return arrayLink.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = rows.get(position);
		if (rowView == null ) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
			rowView = layoutInflater.inflate(R.layout.listitem2, null);
			TextView  tv = (TextView)rowView.findViewById(R.id.listitem2);
			BasicLinkType link = arrayLink.get(position);
			tv.setText(link.getText());
			rows.append(position, rowView);
		}
		
		return rowView;
	}

}
