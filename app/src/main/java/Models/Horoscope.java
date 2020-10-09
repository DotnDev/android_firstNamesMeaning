package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Horoscope implements Parcelable{

    private String id;
    private int horoscopeNumberOfTheDay;
    private String gender;
    private String horoscopeLove;
    private String horoscopeMoney;
    private String horoscopeHealth;

    private Horoscope(Parcel in) {
    }

    public Horoscope(JSONObject jObject) {
        this.id = jObject.optString("id");
        this.horoscopeNumberOfTheDay = jObject.optInt("number1");
        this.gender = jObject.optString("gender");
        this.horoscopeLove = jObject.optString("descriptionBe");
        this.horoscopeMoney = jObject.optString("descriptionLove");
        this.horoscopeHealth = jObject.optString("descriptionDo");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHoroscopeNumberOfTheDay() {
        return horoscopeNumberOfTheDay;
    }

    public void setHoroscopeNumberOfTheDay(int horoscopeNumberOfTheDay) {
        this.horoscopeNumberOfTheDay = horoscopeNumberOfTheDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHoroscopeLove() {
        return horoscopeLove;
    }

    public void setHoroscopeLove(String horoscopeLove) {
        this.horoscopeLove = horoscopeLove;
    }

    public String getHoroscopeMoney() {
        return horoscopeMoney;
    }

    public void setHoroscopeMoney(String horoscopeMoney) {
        this.horoscopeMoney = horoscopeMoney;
    }

    public String getHoroscopeHealth() {
        return horoscopeHealth;
    }

    public void setHoroscopeHealth(String horoscopeHealth) {
        this.horoscopeHealth = horoscopeHealth;
    }

    public static final Creator<Horoscope> CREATOR = new Creator<Horoscope>() {
        @Override
        public Horoscope createFromParcel(Parcel in) {
            return new Horoscope(in);
        }

        @Override
        public Horoscope[] newArray(int size) {
            return new Horoscope[size];
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