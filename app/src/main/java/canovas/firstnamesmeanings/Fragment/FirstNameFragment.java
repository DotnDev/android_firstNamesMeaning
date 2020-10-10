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
import Models.FirstName;
import canovas.firstnamesmeanings.R;

public class FirstNameFragment extends Fragment {

    private TextView title_descriptionBe_txtView;
    private TextView title_descriptionLove_txtView;
    private TextView title_descriptionDo_txtView;

    private String firstNameData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name, container, false);

        TextView name_txtView = view.findViewById(R.id.name_name_txtView);
        TextView gender_txtView = view.findViewById(R.id.name_gender_txtView);
        TextView numbers_txtView = view.findViewById(R.id.name_numbers_txtView);
        TextView nameDay_txtView = view.findViewById(R.id.name_nameDay_txtView);
        TextView origin_txtView = view.findViewById(R.id.name_origin_txtView);
        TextView etymology_txtView = view.findViewById(R.id.name_etymology_txtView);
        title_descriptionBe_txtView = view.findViewById(R.id.name_desc_be_title_txtView);
        title_descriptionLove_txtView = view.findViewById(R.id.name_desc_love_title_txtView);
        title_descriptionDo_txtView = view.findViewById(R.id.name_desc_do_title_txtView);
        TextView descriptionBe_txtView = view.findViewById(R.id.name_desc_be_txtView);
        TextView descriptionLove_txtView = view.findViewById(R.id.name_desc_love_txtView);
        TextView descriptionDo_txtView = view.findViewById(R.id.name_desc_do_txtView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            firstNameData = bundle.getString("firstName", "");
        }

        //Get JSON data into FirstName Model thanks to Gson library
        Gson gson = new Gson();
        final FirstName firstName = gson.fromJson(firstNameData,FirstName.class);

        String numbers = ""+firstName.getCombination().getNumber1() + firstName.getCombination().getNumber2() + firstName.getCombination().getNumber3();
        String nameDay = "?";
        if(firstName.getNameDay() != null){
            nameDay = ""+firstName.getNameDay().getDay()+firstName.getNameDay().getMonth();
        }

        setTitles(firstName.getGender());

        firstName.getCombination().setDescriptionBe(formatDescription(firstName.getCombination().getDescriptionBe(), firstName.getFirstName()));
        firstName.getCombination().setDescriptionLove(formatDescription(firstName.getCombination().getDescriptionLove(), firstName.getFirstName()));
        firstName.getCombination().setDescriptionDo(formatDescription(firstName.getCombination().getDescriptionDo(), firstName.getFirstName()));

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

    private void setTitles(String gender) {

        if(gender.equals("F")){
            title_descriptionBe_txtView.setText(R.string.name_description_be_title_f);
            title_descriptionLove_txtView.setText(R.string.name_description_love_title_f);
            title_descriptionDo_txtView.setText(R.string.name_description_do_title_f);
        }else{
            title_descriptionBe_txtView.setText(R.string.name_description_be_title_m);
            title_descriptionLove_txtView.setText(R.string.name_description_love_title_m);
            title_descriptionDo_txtView.setText(R.string.name_description_do_title_m);
        }
    }

    private String formatDescription(String description, String firstName){

        return description.replace("<strong>%s</strong>", firstName);

    }


}
