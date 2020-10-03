package Models;

import android.os.Parcel;
import android.os.Parcelable;

public class FirstName implements Parcelable{

    private String firstNameId;
    private String name;
    private String gender;
    private String origin;
    private String etymology;
    private NameDay nameDay;
    private Combination combination;

    private FirstName(Parcel in) {
    }

    public static final Creator<FirstName> CREATOR = new Creator<FirstName>() {
        @Override
        public FirstName createFromParcel(Parcel in) {
            return new FirstName(in);
        }

        @Override
        public FirstName[] newArray(int size) {
            return new FirstName[size];
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