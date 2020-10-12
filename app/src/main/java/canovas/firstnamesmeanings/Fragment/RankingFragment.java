package canovas.firstnamesmeanings.Fragment;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

import Models.FirstName;
import Models.Horoscope;
import Models.Ranking;
import canovas.firstnamesmeanings.Adapter.RankingAdapter;
import canovas.firstnamesmeanings.R;

public class RankingFragment extends Fragment implements View.OnClickListener {

    private RankingFragment.OnButtonClickedListener mCallback;
    private RankingAdapter rankingAdapter;
    private ArrayList<Ranking> mRankingNames = new ArrayList<>();
    private String rankingData;


    public void setOnButtonClickedListener(RankingFragment.OnButtonClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnButtonClickedListener {
        void onNameClicked(String firstName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        setRecyclerView(view);
        try {
            getRankingList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;

    }

    private void setRecyclerView(View v) {

        rankingAdapter = new RankingAdapter(mRankingNames, getActivity());
        RecyclerView recyclerview = v.findViewById(R.id.ranking_recyclerView);
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(rankingAdapter);

    }

    private void getRankingList() throws JSONException {

        //Get data
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            rankingData = bundle.getString("ranking");

            JSONArray jsonArray = new JSONArray((rankingData));

            for(int i=0;i<jsonArray.length();i++){
                Gson gson = new Gson();
                Ranking ranking = gson.fromJson(jsonArray.getString(i),Ranking.class);
                mRankingNames.add(ranking);
            }

            //Notify the adapter
            rankingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {

    }

}
