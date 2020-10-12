package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Ranking implements Parcelable{

    private String id;
    private int count;
    private FirstName firstName;
    private int rankingYear;


    private Ranking(Parcel in) {
    }

    public Ranking(JSONObject jObject) {
        this.id = jObject.optString("id");
        this.count = jObject.optInt("count");
        //this.firstName = jObject.optString("firstName");
        this.rankingYear = jObject.optInt("rankingYear");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public int getRankingYear() {
        return rankingYear;
    }

    public void setRankingYear(int rankingYear) {
        this.rankingYear = rankingYear;
    }

    public static final Creator<Ranking> CREATOR = new Creator<Ranking>() {
        @Override
        public Ranking createFromParcel(Parcel in) {
            return new Ranking(in);
        }

        @Override
        public Ranking[] newArray(int size) {
            return new Ranking[size];
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