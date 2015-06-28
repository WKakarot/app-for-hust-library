package wangjie.listadapter;

import java.util.ArrayList;

import wangjie.infotypes.BasicLinkType;
import wangjie.testactbar.R;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RecmdAdapter extends BaseAdapter{
    private ArrayList<BasicLinkType> arrayLink;
    private String title;
	private Context context;
	private SparseArray<View> rows;
	
	public RecmdAdapter(ArrayList<BasicLinkType> arr, String t, Context ctx) {
		arrayLink = arr;
		context = ctx;
		title = t;
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
		if (rowView == null) {
			if (position == 0) {
				LayoutInflater layoutInflater = LayoutInflater.from(context);
				rowView = layoutInflater.inflate(R.layout.listtitle, null);
				TextView  tv = (TextView)rowView.findViewById(R.id.listtitle);
                tv.setText(title);
				rows.append(0, rowView);
			}
			else {
			    LayoutInflater layoutInflater = LayoutInflater.from(context);
			    rowView = layoutInflater.inflate(R.layout.listitem1, null);
			    TextView  tv = (TextView)rowView.findViewById(R.id.listitem);
			    BasicLinkType link = arrayLink.get(position-1);
			    tv.setText(link.getText());
			    rows.append(position, rowView);
			}
		}
		
		return rowView;
	}

}
