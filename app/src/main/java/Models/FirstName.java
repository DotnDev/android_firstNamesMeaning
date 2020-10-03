package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

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

    public FirstName(JSONObject jObject) {
        this.firstNameId = jObject.optString("id");
        this.name = jObject.optString("number1");
        this.gender = jObject.optString("number2");
        this.origin = jObject.optString("number3");
        this.gender = jObject.optString("gender");
        this.etymology = jObject.optString("descriptionBe");
    }

    public String getFirstNameId() {
        return firstNameId;
    }

    public void setFirstNameId(String firstNameId) {
        this.firstNameId = firstNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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