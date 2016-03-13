package info.theh2o.rahul.findine;
//
//        Heading  : FinDine Andorid Application
//        Author.  : Rahulkumar Gaddam
//        Purpose. : To find the restaurants in the city
//        This is the Adapter class used to load our data in the listview
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Rahulkumat on 3/11/2016.
 */
public class ListViewAdapter extends BaseAdapter{

    private List<Map<String, ?>> mDataset;
    private Context mContext;


    public ListViewAdapter(Context myContext, List<Map<String, ?>> myDataset) {

        mContext = myContext;
        mDataset = myDataset;

    }
    @Override
    public int getCount() {
        return  mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        int type = getItemViewType(position);
        if (v == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_layout, parent, false);

        }


        Typeface tf = Typeface.createFromAsset(mContext.getAssets(),"fonts/Caviar_Dreams_Bold.ttf");


        TextView rname = (TextView) v.findViewById(R.id.Rname);
        TextView address = (TextView) v.findViewById(R.id.Addr);
        TextView rating = (TextView) v.findViewById(R.id.Rating);
        TextView type1 = (TextView) v.findViewById(R.id.type);
        RatingBar  rbar = (RatingBar) v.findViewById(R.id.ratingBar);
        address.setTypeface(tf);
        rname.setTypeface(tf);
        rating.setTypeface(tf);
        type1.setTypeface(tf);
        Map<String, ?> resto = mDataset.get(position);
        rname.setText((String) resto.get("name"));
       address.setText((String) resto.get("address"));
        if((String) resto.get("rating") == null)
        {}
        else {
            rating.setText("Rating: " + (String) resto.get("rating") + " / 5");
            String f = (String) resto.get("rating");
            if(f.equalsIgnoreCase("null")) {
                rating.setText("No rating set ");}
            else{
                Float protein = new Float(f);
                rbar.setRating(protein);
            }
        }
        type1.setText( (String)resto.get("type"));


        return v;
    }



}
