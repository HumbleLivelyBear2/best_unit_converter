package simple.a;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
	
	//private static LayoutInflater inflater=null;
    private Activity activity;
	public List<ImageItem> items;
	public Context context;
	
    public LazyAdapter(Context ctx) {
        
        //inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        activity=(Activity)ctx;
        context = ctx;
        items = new ArrayList<ImageItem>();
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public static class ViewHolder{
    	public TextView text;
    	public ImageView image;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        
        TestItemView iiv;
         
            // TODO Auto-generated method stub
       
        if (convertView == null)
        {
            iiv = new TestItemView(context, items.get(position).text,items.get(position).rate,items.get(position).ps, items.get(position).photoNum);
        }
        else
        {
            iiv = (TestItemView) convertView;
            iiv.setText(items.get(position).text);
            iiv.setRate(items.get(position).rate);
            iiv.setPhoto(items.get(position).photoNum);
            iiv.setPs(items.get(position).ps);
        }
        
        Log.d("getView:",position+":"+items.get(position).text);
        
		
		
        return iiv;
    }
}