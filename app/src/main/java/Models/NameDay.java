package Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NameDay implements Parcelable{

    private String nameDayId;
    private int day;
    private int month;

    private NameDay(Parcel in) {
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