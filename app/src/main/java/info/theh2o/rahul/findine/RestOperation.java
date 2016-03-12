package info.theh2o.rahul.findine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.factual.driver.Factual;
import com.factual.driver.Query;
import com.factual.driver.ReadResponse;
import com.google.common.collect.Lists;




import java.util.List;

/**
 * Created by Rahulkumat on 3/11/2016.
 */
public class RestOperation extends AsyncTask<Query, Integer, List<ReadResponse>> {
    protected Factual factual = new Factual("fovXtVsIxI8KHnk0Sbh0XmB7utlBdUCuBv2Tgt4y", "YWN7HOMvo6A0cLDoNQS0GZBDJdkIYQUmOkfwzjET");
    public AsyncResponse delegate = null;
    private TextView resultText = null;
    private List<Map<String,?>> restList =  new ArrayList<Map<String,?>>();

    public List<Map<String, ?>> getRestList() {
        return restList;
    }

    public int getSize(){
        return restList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < restList.size()){
            return (HashMap) restList.get(i);
        } else return null;
    }


    public RestOperation(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected List<ReadResponse> doInBackground(Query... params) {
        List<ReadResponse> results = Lists.newArrayList();
        for (Query q : params) {
            results.add(factual.fetch("restaurants-us", q));
        }
        return results;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(List<ReadResponse> responses) {
        StringBuffer sb = new StringBuffer();

        for (ReadResponse response : responses) {
            for (Map<String, Object> restaurant : response.getData()) {
                String name = (String) restaurant.get("name");
                String address = (String) restaurant.get("address");
                String phone = (String) restaurant.get("tel");
              //  Number distance = (Number) restaurant.get("$distance");
                sb.append(name+" @ " +address + ", call "+phone);
                sb.append(System.getProperty("line.separator"));
                Iterator<Map.Entry<String, Object >> i = restaurant.entrySet().iterator();
                while(i.hasNext()){
                    String key = i.next().getKey();
                    System.out.println(key+", "+restaurant.get(key));
                }


                restList.add(restaurant);


            }
        }
       // resultText.setText(sb.toString());

            delegate.processFinish(restList);

        Log.d("check", sb.toString());
    }
}


