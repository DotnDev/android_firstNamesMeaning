package canovas.firstnamesmeanings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Models.FirstName;
import canovas.firstnamesmeanings.Adapter.RankingAdapter;

public class RankingFragment extends Fragment implements View.OnClickListener {

    private RankingAdapter rankingAdapter;
    private ArrayList<FirstName> mRankingNames = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        setRecyclerView(view);
        getRankingList();

        return view;

    }

    private void setRecyclerView(View v) {

        rankingAdapter = new RankingAdapter(mRankingNames, getActivity());
        RecyclerView recyclerview = v.findViewById(R.id.ranking_recyclerView);
        recyclerview.setHasFixedSize(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(rankingAdapter);

    }

    private void getRankingList(){

        //Get data

        //Notify the adapter
        rankingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }
}
