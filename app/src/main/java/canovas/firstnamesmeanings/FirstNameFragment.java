package canovas.firstnamesmeanings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import Models.FirstName;

public class FirstNameFragment extends Fragment {

    TextView name_txtView;
    TextView gender_txtView;
    TextView numbers_txtView;
    TextView nameDay_txtView;
    TextView origin_txtView;
    TextView etymology_txtView;
    TextView descriptionBe_txtView;
    TextView descriptionLove_txtView;
    TextView descriptionDo_txtView;

    String firstNameData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name, container, false);

        name_txtView = view.findViewById(R.id.name_name_txtView);
        gender_txtView = view.findViewById(R.id.name_gender_txtView);
        numbers_txtView = view.findViewById(R.id.name_numbers_txtView);
        nameDay_txtView = view.findViewById(R.id.name_nameDay_txtView);
        origin_txtView = view.findViewById(R.id.name_origin_txtView);
        etymology_txtView = view.findViewById(R.id.name_etymology_txtView);
        descriptionBe_txtView = view.findViewById(R.id.name_desc_be_txtView);
        descriptionLove_txtView = view.findViewById(R.id.name_desc_love_txtView);
        descriptionDo_txtView = view.findViewById(R.id.name_desc_do_txtView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            firstNameData = bundle.getString("firstName", "");
        }

        //Get JSON data into FirstName Model thanks to Gson library
        Gson gson = new Gson();
        FirstName firstName = gson.fromJson(firstNameData,FirstName.class);

        String numbers = ""+firstName.getCombination().getNumber1() + firstName.getCombination().getNumber2() + firstName.getCombination().getNumber3();
        String nameDay = "";
        if(firstName.getNameDay() != null){
            nameDay = ""+firstName.getNameDay().getDay()+firstName.getNameDay().getMonth();
        }

        name_txtView.setText(firstName.getFirstName());
        gender_txtView.setText(firstName.getGender());
        numbers_txtView.setText(numbers);
        nameDay_txtView.setText(nameDay);
        origin_txtView.setText(firstName.getOrigin());
        etymology_txtView.setText(firstName.getEtymology());
        descriptionBe_txtView.setText(firstName.getCombination().getDescriptionBe());
        descriptionLove_txtView.setText(firstName.getCombination().getDescriptionLove());
        descriptionDo_txtView.setText(firstName.getCombination().getDescriptionDo());

        return view;

    }


}
