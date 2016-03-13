package info.theh2o.rahul.findine;
//
//        Heading  : FinDine Andorid Application
//        Author.  : Rahulkumar Gaddam
//        Purpose. : To find the restaurants in the city
//      This class is to transfer the data from main acitivity to list activity . IMplemented the parcelable class to transfer data through bundle
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rahulkumat on 3/11/2016.
 */
public class Dine implements Parcelable {

    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_RATING = "rating";
    private static final String KEY_TYPE = "type";

    private String name;
    private String address;
    private String rating;
    private String type;




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Dine() {
    }

    public Dine(String name, String address , String rating , String type) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        Bundle bundle = new Bundle();

        // insert the key value pairs to the bundle
        bundle.putString(KEY_NAME, name);
        bundle.putString(KEY_ADDRESS, address);
        bundle.putString(KEY_RATING, rating);
        bundle.putString(KEY_TYPE, type);

        // write the key value pairs to the parcel
        dest.writeBundle(bundle);

    }

    public static final Parcelable.Creator<Dine> CREATOR = new Creator<Dine>() {

        @Override
        public Dine createFromParcel(Parcel source) {
            // read the bundle containing key value pairs from the parcel
            Bundle bundle = source.readBundle();

            // instantiate a person using values from the bundle
            return new Dine(bundle.getString(KEY_NAME),bundle.getString(KEY_ADDRESS),bundle.getString(KEY_RATING),bundle.getString(KEY_TYPE));
        }

        @Override
        public Dine[] newArray(int size) {
            return new Dine[size];
        }

    };


}
