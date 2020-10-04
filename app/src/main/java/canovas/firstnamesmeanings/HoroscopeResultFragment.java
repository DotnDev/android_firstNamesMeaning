package canovas.firstnamesmeanings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HoroscopeResultFragment extends Fragment implements View.OnClickListener {

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

        Button horoscope_submit_btn = view.findViewById(R.id.compatibility_btn);
        horoscope_submit_btn.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.compatibility_btn) {
            mCallback.onHoroscopeSubmit();
        }
    }
}
