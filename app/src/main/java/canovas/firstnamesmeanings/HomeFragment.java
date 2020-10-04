package canovas.firstnamesmeanings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView nameDay_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        nameDay_date = view.findViewById(R.id.home_nameDay_date_txtView);
        nameDay_date.setText(getDate());

        return view;

    }

    private String getDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM yyyy", Locale.FRENCH);
        String date  = sdf.format(new Date());
        return date;
    }


    @Override
    public void onClick(View view) {

    }
}
