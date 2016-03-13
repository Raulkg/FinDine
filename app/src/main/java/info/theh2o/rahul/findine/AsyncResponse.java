package info.theh2o.rahul.findine;
//
//        Heading  : FinDine Andorid Application
//        Author.  : Rahulkumar Gaddam
//        Purpose. : To find the restaurants in the city
//        This is the interface to delegate data from restoeration class to the main activity clas
import java.util.List;
import java.util.Map;

/**
 * Created by Rahulkumat on 3/11/2016.
 */
public interface AsyncResponse {
    void processFinish(List<Map<String,?>> output);
}
