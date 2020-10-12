package canovas.firstnamesmeanings.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import canovas.firstnamesmeanings.R;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;

    public void setOnButtonClickedListener(OnButtonClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        void onButtonClicked(View view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button home_search_btn = view.findViewById(R.id.home_search_btn);
        Button home_horoscope_btn = view.findViewById(R.id.home_horoscope_btn);
        Button home_compatibility_btn = view.findViewById(R.id.home_compatibility_btn);
        Button home_ranking_btn = view.findViewById(R.id.home_ranking_btn);

        home_search_btn.setOnClickListener(this);
        home_horoscope_btn.setOnClickListener(this);
        home_compatibility_btn.setOnClickListener(this);
        home_ranking_btn.setOnClickListener(this);

        return view;

    }


    //Send event to Main Activity
    @Override
    public void onClick(View view) {

        mCallback.onButtonClicked(view);

    }
}
