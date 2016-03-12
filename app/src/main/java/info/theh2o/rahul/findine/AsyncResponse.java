package info.theh2o.rahul.findine;

import java.util.List;
import java.util.Map;

/**
 * Created by Rahulkumat on 3/11/2016.
 */
public interface AsyncResponse {
    void processFinish(List<Map<String,?>> output);
}
