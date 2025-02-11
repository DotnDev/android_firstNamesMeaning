package canovas.firstnamesmeanings.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import Models.Horoscope;
import canovas.firstnamesmeanings.R;


public class HoroscopeResultFragment extends Fragment implements View.OnClickListener {

    private String horoscopeData;
    private String firstName;
    private boolean saveName;

    private SharedPreferences mSharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getActivity() != null) {
            mSharedPreferences = getActivity().getApplicationContext().getSharedPreferences("userPrefs", 0);
        }

        View view = inflater.inflate(R.layout.fragment_horoscope_result, container, false);

        TextView horoscope_title_txtView = view.findViewById(R.id.horoscope_result_title_txtView);
        TextView horoscope_love_couple_txtView = view.findViewById(R.id.horoscope_result_love_couple_txtView);
        TextView horoscope_love_single_txtView = view.findViewById(R.id.horoscope_result_love_single_txtView);
        TextView horoscope_love_money_txtView = view.findViewById(R.id.horoscope_result_money_txtView);
        TextView horoscope_love_health_txtView = view.findViewById(R.id.horoscope_result_health_txtView);
        TextView horoscope_lucky_number_txtView = view.findViewById(R.id.horoscope_result_lucky_number_txtView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            horoscopeData = bundle.getString("horoscope", "");
            firstName = bundle.getString("firstName", "");
            saveName = bundle.getBoolean("saveName", false);

            if(saveName){
                saveToPreferences(firstName);
            }
        }


        //Insert name in title
        String horoscope_title = getString(R.string.horoscope_result_title);
        String title = horoscope_title + " " + firstName;
        horoscope_title_txtView.setText(title);

        //Get JSON data into FirstName Model thanks to Gson library
        Gson gson = new Gson();
        final Horoscope horoscope = gson.fromJson(horoscopeData,Horoscope.class);

        //Split love string into single/couple
        String[] horoscope_couples_singles = horoscope.getHoroscopeLove().split("(?=Singles)");

        String[] descriptionsToFormat = {
                horoscope_couples_singles[0],
                horoscope_couples_singles[1],
                horoscope.getHoroscopeMoney(),
                horoscope.getHoroscopeHealth()
        };

        for(int i=0;i<descriptionsToFormat.length;i++){
            descriptionsToFormat[i] = descriptionsToFormat[i].replace("<strong>%s</strong>", firstName);
            descriptionsToFormat[i] = descriptionsToFormat[i].replace("<br/>", "");
        }

        //Assign texts to views
        horoscope_love_couple_txtView.setText(descriptionsToFormat[0]);
        horoscope_love_single_txtView.setText(descriptionsToFormat[1]);
        horoscope_love_money_txtView.setText(descriptionsToFormat[2]);
        horoscope_love_health_txtView.setText(descriptionsToFormat[3]);
        horoscope_lucky_number_txtView.setText(String.valueOf(horoscope.getHoroscopeNumberOfTheDay()));

        return view;

    }


    @Override
    public void onClick(View view) {

    }


    //Save name in SharedPrefs
    private void saveToPreferences(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString("name", name);

        editor.apply();
    }
}
