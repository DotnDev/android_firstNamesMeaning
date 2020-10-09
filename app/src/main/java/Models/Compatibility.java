package Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Compatibility implements Parcelable{

    private String id;
    private int compatibilityTotal;
    private int numberLove;
    private int numberFriendship;
    private int numberComplicity;
    private int numberFidelity;
    private int numberSexuality;
    private String compatibilityText;
    private String compatibilityAdjective;


    private Compatibility(Parcel in) {
    }

    public Compatibility(JSONObject jObject) {
        this.id = jObject.optString("id");
        this.compatibilityTotal = jObject.optInt("compatibilityTotal");
        this.numberLove = jObject.optInt("numberLove");
        this.numberFriendship = jObject.optInt("numberFriendship");
        this.numberComplicity = jObject.optInt("numberComplicity");
        this.numberFidelity = jObject.optInt("numberFidelity");
        this.numberSexuality = jObject.optInt("numberSexuality");
        this.compatibilityText = jObject.optString("compatibilityText");
        this.compatibilityAdjective = jObject.optString("compatibilityAdjective");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompatibilityTotal() {
        return compatibilityTotal;
    }

    public void setCompatibilityTotal(int compatibilityTotal) {
        this.compatibilityTotal = compatibilityTotal;
    }

    public int getNumberLove() {
        return numberLove;
    }

    public void setNumberLove(int numberLove) {
        this.numberLove = numberLove;
    }

    public int getNumberFriendship() {
        return numberFriendship;
    }

    public void setNumberFriendship(int numberFriendship) {
        this.numberFriendship = numberFriendship;
    }

    public int getNumberComplicity() {
        return numberComplicity;
    }

    public void setNumberComplicity(int numberComplicity) {
        this.numberComplicity = numberComplicity;
    }

    public int getNumberFidelity() {
        return numberFidelity;
    }

    public void setNumberFidelity(int numberFidelity) {
        this.numberFidelity = numberFidelity;
    }

    public int getNumberSexuality() {
        return numberSexuality;
    }

    public void setNumberSexuality(int numberSexuality) {
        this.numberSexuality = numberSexuality;
    }

    public String getCompatibilityText() {
        return compatibilityText;
    }

    public void setCompatibilityText(String compatibilityText) {
        this.compatibilityText = compatibilityText;
    }

    public String getCompatibilityAdjective() {
        return compatibilityAdjective;
    }

    public void setCompatibilityAdjective(String compatibilityAdjective) {
        this.compatibilityAdjective = compatibilityAdjective;
    }

    public static final Creator<Compatibility> CREATOR = new Creator<Compatibility>() {
        @Override
        public Compatibility createFromParcel(Parcel in) {
            return new Compatibility(in);
        }

        @Override
        public Compatibility[] newArray(int size) {
            return new Compatibility[size];
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