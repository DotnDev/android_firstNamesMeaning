package Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Combination implements Parcelable{

    private String combinationId;
    private int number1;
    private int number2;
    private int number3;
    private String gender;
    private String descriptionBe;
    private String descriptionLove;
    private String descriptionDo;

    private Combination(Parcel in) {
    }

    public static final Creator<Combination> CREATOR = new Creator<Combination>() {
        @Override
        public Combination createFromParcel(Parcel in) {
            return new Combination(in);
        }

        @Override
        public Combination[] newArray(int size) {
            return new Combination[size];
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