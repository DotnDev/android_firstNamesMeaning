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

import Models.FirstName;


public class HoroscopeFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private EditText firstName_editTxt;
    private EditText email_editTxt;
    private CheckBox consent_checkBox;
    private CheckBox remember_checkBox;

    private SharedPreferences mSharedPreferences;


    public void setOnButtonClickedListener(OnButtonClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        public void onHoroscopeSubmit(String firstName, String email, boolean isSubscribed);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horoscope_main, container, false);

        mSharedPreferences = getActivity().getApplicationContext().getSharedPreferences("userPrefs", 0);

        Button horoscope_submit_btn = view.findViewById(R.id.horoscope_btn);
        firstName_editTxt = view.findViewById(R.id.horoscope_name_editTxt);
        email_editTxt = view.findViewById(R.id.horoscope_email_editTxt);
        consent_checkBox = view.findViewById(R.id.horoscope_consent_checkBox);
        remember_checkBox = view.findViewById(R.id.horoscope_remember_checkBox);

        horoscope_submit_btn.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {

        //Click on submit
        if (view.getId() == R.id.horoscope_btn) {
            checkInputsAndCheckboxes();
        }
    }

    private void checkInputsAndCheckboxes() {

        //Keeping this boolean true as long as user does not fill in the email
        boolean isConsent = true;
        boolean isSubscribed = false;
        String nameEntered = firstName_editTxt.getText().toString();
        FirstName name = new FirstName();
        String email = email_editTxt.getText().toString();
        String errorMsg;

        //If name is empty
        if(!nameEntered.isEmpty()){
            errorMsg = "Name cannot be empty";
        }else{
            //Search in DB
        }

        //If name was found
        if (!name.getFirstName().isEmpty()) {
            //Check if remember me box is ticked
            if (remember_checkBox.isChecked()) {
                saveToPreferences(nameEntered);
            }

            //Then check if email is filled in + consent is ticked
            if (!email.isEmpty() && consent_checkBox.isChecked()) {
                isSubscribed = true;
            }else if (!email.isEmpty() && !consent_checkBox.isChecked()){
                isConsent = false;
                errorMsg = "Please tick the consent box to subscribe to the newsletter";
            }

            //Check if consent is fine
            if (isConsent) {
                mCallback.onHoroscopeSubmit(nameEntered, email, isSubscribed);
            }
        }else{
            errorMsg = "Name cannot be found in our database, sorry!";

        }

    }

    //Save name in SharedPrefs
    private void saveToPreferences(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString("name", name);

        editor.apply();
    }
}
