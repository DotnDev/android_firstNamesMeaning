package canovas.firstnamesmeanings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import Models.Compatibility;
import Models.FirstName;


public class CompatibilityResultFragment extends Fragment implements View.OnClickListener {

    String firstName1;
    String firstName2;
    String compatibilityData;

    TextView compatibility_result_names_txtView;
    TextView compatibility_result_love_txtView;
    TextView compatibility_result_sexuality_txtView;
    TextView compatibility_result_complicity_txtView;
    TextView compatibility_result_fidelity_txtView;
    TextView compatibility_result_friendship_txtView;
    TextView compatibility_result_txt_txtView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compatibility_result, container, false);

        compatibility_result_names_txtView = view.findViewById(R.id.compatibility_result_names_txtView);
        compatibility_result_love_txtView = view.findViewById(R.id.compatibility_result_love_txtView);
        compatibility_result_sexuality_txtView = view.findViewById(R.id.compatibility_result_sexuality_txtView);
        compatibility_result_complicity_txtView = view.findViewById(R.id.compatibility_result_complicity_txtView);
        compatibility_result_fidelity_txtView = view.findViewById(R.id.compatibility_result_fidelity_txtView);
        compatibility_result_friendship_txtView = view.findViewById(R.id.compatibility_result_friendship_txtView);
        compatibility_result_txt_txtView = view.findViewById(R.id.compatibility_result_txt_txtView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            firstName1 = bundle.getString("firstName1", "");
            firstName2 = bundle.getString("firstName2", "");
            compatibilityData = bundle.getString("compatibility", "");
        }

        //Get JSON data into FirstName Model thanks to Gson library
        Gson gson = new Gson();
        final Compatibility compatibility = gson.fromJson(compatibilityData, Compatibility.class);

        //Set title
        String title = firstName1 + " + " + firstName2 + " = " + compatibility.getCompatibilityAdjective();
        compatibility_result_names_txtView.setText(title);

        compatibility_result_love_txtView.setText(String.valueOf(compatibility.getNumberLove()));
        compatibility_result_sexuality_txtView.setText((String.valueOf(compatibility.getNumberSexuality())));
        compatibility_result_complicity_txtView.setText(String.valueOf(compatibility.getNumberComplicity()));
        compatibility_result_fidelity_txtView.setText(String.valueOf(compatibility.getNumberFidelity()));
        compatibility_result_friendship_txtView.setText(String.valueOf(compatibility.getNumberFriendship()));
        compatibility_result_txt_txtView.setText(compatibility.getCompatibilityText());


        return view;

    }


    @Override
    public void onClick(View view) {


    }

}
