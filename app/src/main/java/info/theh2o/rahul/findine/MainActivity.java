package info.theh2o.rahul.findine;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.factual.driver.Circle;
import com.factual.driver.Query;
import com.mypopsy.widget.FloatingSearchView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private ImageView iv;
    private ImageView iv1;
    private FloatingSearchView searchView = null;
    private boolean flag = true;
    private boolean mflag = true;

    private int mInterval = 10000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Caviar_Dreams_Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setTypeface(tf);
        mHandler = new Handler();
        startRepeatingTask();

        searchView = (FloatingSearchView)findViewById(R.id.search);

        if (searchView != null) {

            searchView.setOnSearchFocusChangedListener( new FloatingSearchView.OnSearchFocusChangedListener(){


                @Override
                public void onFocusChanged(boolean b) {
                    if(flag) {
                        searchView.setIcon(R.drawable.ic_arrow_back_black_24dp);
                        searchView.showIcon(true);
                        flag = false;
                    }
                    else{
                        searchView.setIcon(R.drawable.ic_search_black_24dp);
                        searchView.showIcon(true);
                        flag = true;
                    }

                }
            } );



                searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener(){


                                               @Override
                                               public void onSearchAction(CharSequence charSequence) {

                                                   String s = charSequence.toString();
                                                   callWebService(s);

                                               }




                                           }
            );
        }


    }
    public void callWebService(String q) {
if(mflag) {

    Query query = new Query();

     new RestOperation(new AsyncResponse() {
         @Override
         public void processFinish(  List<Map<String,?>>  output) {
             Bundle bundle = new Bundle();

             bundle.putSerializable("HashMap",(Serializable) output);
             Intent intent1 = new Intent(MainActivity.this, ListViewActivity.class);

             for (int i = 0; i < output.size(); i++) {
                 Map<String, ?> restaurant = output.get(i);
                 String name = (String) restaurant.get("name");
                 String address = (String) restaurant.get("address");
                 Log.v("Checking",address);
                 String rating1 = String.valueOf(restaurant.get("rating"));

                 String arr = String.valueOf(restaurant.get("cuisine"));
                 String type = "";
                 arr = arr.replace("[","");
                 arr = arr.replace("]","");
                 arr = arr.replace("\"","");

                 Dine d1 = new Dine(name,address,rating1,arr);
                 intent1.putExtra("Data"+i,d1);
             }

             intent1.putExtra("count", output.size());


             startActivity(intent1);
         }
     }).execute(query.field("locality").isEqual(q));

    List<Map<String, ?>> myrest = null;


    mflag = false;
}
        else
{  mflag = true;}
    }

Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    void updateStatus(){
        iv = (ImageView)findViewById(R.id.imageView2);
        iv1 = (ImageView)findViewById(R.id.imageView3);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                Glider.glide(Skill.Linear, 10000, ObjectAnimator.ofFloat(iv, "translationX", 0, 2500)),
                ObjectAnimator.ofFloat(iv, "alpha", 0, 1),
                ObjectAnimator.ofFloat(iv1, "alpha", 0, 1),
                Glider.glide(Skill.Linear, 10000, ObjectAnimator.ofFloat(iv1, "translationX", 1500, -1000))
        );

        set.setDuration(10000);
        set.start();

    }


    @Override
    public void processFinish(List<Map<String, ?>> output) {

    }
}
