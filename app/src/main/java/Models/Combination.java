package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

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

    public Combination(JSONObject jObject) {
        this.combinationId = jObject.optString("id");
        this.number1 = jObject.optInt("number1");
        this.number2 = jObject.optInt("number2");
        this.number3 = jObject.optInt("number3");
        this.gender = jObject.optString("gender");
        this.descriptionBe = jObject.optString("descriptionBe");
        this.descriptionLove = jObject.optString("descriptionLove");
        this.descriptionDo = jObject.optString("descriptionDo");
    }

    public String getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(String combinationId) {
        this.combinationId = combinationId;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescriptionBe() {
        return descriptionBe;
    }

    public void setDescriptionBe(String descriptionBe) {
        this.descriptionBe = descriptionBe;
    }

    public String getDescriptionLove() {
        return descriptionLove;
    }

    public void setDescriptionLove(String descriptionLove) {
        this.descriptionLove = descriptionLove;
    }

    public String getDescriptionDo() {
        return descriptionDo;
    }

    public void setDescriptionDo(String descriptionDo) {
        this.descriptionDo = descriptionDo;
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