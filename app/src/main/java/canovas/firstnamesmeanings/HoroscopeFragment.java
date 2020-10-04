package canovas.firstnamesmeanings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class HoroscopeFragment extends Fragment implements View.OnClickListener {

    private Button horoscope_submit_btn;

    private OnButtonClickedListener mCallback;

    public void setOnButtonClickedListener(OnButtonClickedListener mCallback){
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        public void onHoroscopeSubmit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horoscope_main, container, false);


        horoscope_submit_btn = view.findViewById(R.id.widget_horoscope_btn);
        horoscope_submit_btn.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.widget_horoscope_btn) {
            mCallback.onHoroscopeSubmit();
        }
    }
}
