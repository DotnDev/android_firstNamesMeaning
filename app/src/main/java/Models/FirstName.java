package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class FirstName implements Parcelable{

    private String id;
    private String firstName;
    private String gender;
    private String origin;
    private String etymology;
    private NameDay nameDay;
    private Combination combination;

    private FirstName(Parcel in) {
    }

    public FirstName() {
    }

    public FirstName(JSONObject jObject) {
        this.id = jObject.optString("id");
        this.firstName = jObject.optString("firstName");
        this.gender = jObject.optString("gender");
        this.origin = jObject.optString("origin");
        this.gender = jObject.optString("gender");
        this.etymology = jObject.optString("etymology");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEtymology() {
        return etymology;
    }

    public void setEtymology(String etymology) {
        this.etymology = etymology;
    }

    public NameDay getNameDay() {
        return nameDay;
    }

    public void setNameDay(NameDay nameDay) {
        this.nameDay = nameDay;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
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