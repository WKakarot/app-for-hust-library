package wangjie.listadapter;

import java.util.ArrayList;

import wangjie.infotypes.BasicReaderType;
import wangjie.testactbar.R;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HotReaderAdapter extends BaseAdapter {
    private ArrayList<BasicReaderType> arrayLink;
	private Context context;
	private SparseArray<View> rows;
	
	public HotReaderAdapter(ArrayList<BasicReaderType> arr, Context ctx) {
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
		if (rowView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
			rowView = layoutInflater.inflate(R.layout.listitem2, null);
			TextView  tv = (TextView)rowView.findViewById(R.id.listitem2);
			BasicReaderType link = arrayLink.get(position);
			tv.setText(position + " " + link.getName() + " " + link.getSchool() + " " + link.getNum());
			rows.append(position, rowView);
		}
		
		return rowView;
	}

}
