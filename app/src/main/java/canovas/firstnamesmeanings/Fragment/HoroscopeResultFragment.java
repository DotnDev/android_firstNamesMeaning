package canovas.firstnamesmeanings.Fragment;

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        //Assign texts to views
        horoscope_love_couple_txtView.setText(horoscope_couples_singles[0]);
        horoscope_love_single_txtView.setText(horoscope_couples_singles[1]);
        horoscope_love_money_txtView.setText(horoscope.getHoroscopeMoney());
        horoscope_love_health_txtView.setText(horoscope.getHoroscopeHealth());
        horoscope_lucky_number_txtView.setText(String.valueOf(horoscope.getHoroscopeNumberOfTheDay()));

        return view;

    }


    @Override
    public void onClick(View view) {

    }
}
