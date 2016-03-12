package info.theh2o.rahul.findine;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rahulkumat on 3/11/2016.
 */
public class ListViewActivity extends AppCompatActivity {

    List<Map<String,?>> mylist =  new ArrayList<Map<String,?>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
       Integer mycount = in.getExtras().getInt("count");
        Log.v("check",""+ mycount);

        for (int i = 0; i < mycount; i++) {
            Dine db = in.getExtras().getParcelable("Data"+i);
            HashMap resto = new HashMap();
            resto.put("name",db.getName());
            resto.put("address", db.getAddress());
            resto.put("rating", db.getRating());
            resto.put("type", db.getType());
            mylist.add(resto);
        }

        if(mycount == 0)
        {
            HashMap resto = new HashMap();
            resto.put("name","No Results");
            resto.put("address", "-");
            resto.put("rating","0");
            resto.put("type", "Try with another city .Ex:Syracuse,Buffalo");
            mylist.add(resto);


        }
        setContentView(R.layout.layout_listview);
        ListView lv = (ListView) findViewById(R.id.mainListView);

       ListViewAdapter ca = new ListViewAdapter(this,mylist);

        lv.setAdapter(ca);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }


}
