package canovas.firstnamesmeanings.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import canovas.firstnamesmeanings.R;


public class HoroscopeFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private EditText firstName_editTxt;
    private CheckBox remember_checkBox;

    private SharedPreferences mSharedPreferences;


    public void setOnButtonClickedListener(OnButtonClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        void onHoroscopeSubmit(String firstName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horoscope_main, container, false);

        if (getActivity() != null) {
            mSharedPreferences = getActivity().getApplicationContext().getSharedPreferences("userPrefs", 0);
        }

        Button horoscope_submit_btn = view.findViewById(R.id.horoscope_btn);
        firstName_editTxt = view.findViewById(R.id.horoscope_name_editTxt);
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
        String nameEntered = firstName_editTxt.getText().toString();

        //If name is empty
        if (nameEntered.isEmpty()) {
            Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_LONG).show();
        } else {
            //Check if remember me box is ticked
            if (remember_checkBox.isChecked()) {
                saveToPreferences(nameEntered);
            }

            mCallback.onHoroscopeSubmit(nameEntered);
        }
    }

    //Save name in SharedPrefs
    private void saveToPreferences(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString("name", name);

        editor.apply();
    }
}
