package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.ArrayList;

public class NameDay implements Parcelable{

    private String nameDayId;
    private int day;
    private int month;

    private ArrayList<FirstName> firstNames;

    private NameDay(Parcel in) {
    }

    public NameDay(JSONObject jObject) {
        this.nameDayId = jObject.optString("id");
        this.day = jObject.optInt("day");
        this.month = jObject.optInt("month");
    }

    public String getNameDayId() {
        return nameDayId;
    }

    public void setNameDayId(String nameDayId) {
        this.nameDayId = nameDayId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<FirstName> getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(ArrayList<FirstName> firstNames) {
        this.firstNames = firstNames;
    }

    public static final Creator<NameDay> CREATOR = new Creator<NameDay>() {
        @Override
        public NameDay createFromParcel(Parcel in) {
            return new NameDay(in);
        }

        @Override
        public NameDay[] newArray(int size) {
            return new NameDay[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}