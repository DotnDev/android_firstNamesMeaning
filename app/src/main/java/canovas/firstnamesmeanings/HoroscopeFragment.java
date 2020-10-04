package canovas.firstnamesmeanings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class HoroscopeFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private EditText firstName_editTxt;
    private EditText email_editTxt;
    private CheckBox consent_checkBox;
    private CheckBox remember_checkBox;

    SharedPreferences mSharedPreferences;


    public void setOnButtonClickedListener(OnButtonClickedListener mCallback){
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        public void onHoroscopeSubmit(String firstName, String email);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horoscope_main, container, false);

        mSharedPreferences = getActivity().getApplicationContext().getSharedPreferences("userPrefs", 0);


        Button horoscope_submit_btn = view.findViewById(R.id.widget_horoscope_btn);
        firstName_editTxt = view.findViewById(R.id.widget_horoscope_input_name);
        email_editTxt = view.findViewById(R.id.widget_horoscope_input_email);
        consent_checkBox = view.findViewById(R.id.widget_horoscope_consent_checkBox);
        remember_checkBox = view.findViewById(R.id.horoscope_remember_checkBox);

        horoscope_submit_btn.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {

        //Click on submit
        if (view.getId() == R.id.widget_horoscope_btn) {
            checkInputsAndCheckboxes();
        }
    }

    private void checkInputsAndCheckboxes(){
        //Keeping this boolean true as long as user does not fill in the email
        boolean isConsent = true;
        String firstName = firstName_editTxt.getText().toString();
        String email = email_editTxt.getText().toString();

        //First, check remember me box
        if(!firstName.isEmpty() && remember_checkBox.isChecked()){
            saveToPreferences(firstName);
        }

        //Then check if email is filled in + consent is ticked
        if(!email.isEmpty() && !consent_checkBox.isChecked()){
            isConsent = false;
        }

        //Check if name is not empty
        if(!firstName.isEmpty() && isConsent){
            mCallback.onHoroscopeSubmit(firstName, email);
        }

    }

    //Save name in SharedPrefs
    private void saveToPreferences(String name){
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString("name", name);

        editor.apply();
    }
}
