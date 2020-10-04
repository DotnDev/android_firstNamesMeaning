package canovas.firstnamesmeanings;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button home_search_btn;

    private OnButtonClickedListener mCallback;

    public void setOnButtonClickedListener(OnButtonClickedListener mCallback){
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        public void onSearchButtonClicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView nameDay_date = view.findViewById(R.id.home_nameDay_date_txtView);
        nameDay_date.setText(getDate());

        home_search_btn = view.findViewById(R.id.home_search_btn);
        home_search_btn.setOnClickListener(this);

        return view;

    }

    private String getDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM yyyy", Locale.FRENCH);
        return sdf.format(new Date());
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.home_search_btn) {
            mCallback.onSearchButtonClicked();
        }
    }
}
