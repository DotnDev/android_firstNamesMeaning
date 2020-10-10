package canovas.firstnamesmeanings.Fragment;

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
import canovas.firstnamesmeanings.R;


public class CompatibilityFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private EditText input_name1;
    private EditText input_name2;
    private Button compatibility_submit_btn;


    public void setOnButtonClickedListener(OnButtonClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        public void onCompatibilitySubmit(String name1, String name2);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compatibility_main, container, false);

        input_name1 = view.findViewById(R.id.compatibility_input_name1);
        input_name2 = view.findViewById(R.id.compatibility_input_name2);
        compatibility_submit_btn = view.findViewById(R.id.compatibility_btn);

        compatibility_submit_btn.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {

        //Click on submit
        if (view.getId() == R.id.compatibility_btn) {
            checkInputs();
        }
    }

    private void checkInputs() {

        String name1 = input_name1.getText().toString();
        String name2 = input_name2.getText().toString();
        String errorMsg;

        if(!name1.isEmpty() && !name2.isEmpty()){
            mCallback.onCompatibilitySubmit(name1,name2);
        }else{
            errorMsg = "Please fill in both inputs";
        }

    }
}
