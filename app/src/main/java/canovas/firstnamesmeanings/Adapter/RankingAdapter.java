package canovas.firstnamesmeanings.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Models.FirstName;
import Models.Ranking;
import canovas.firstnamesmeanings.Fragment.HoroscopeFragment;
import canovas.firstnamesmeanings.Fragment.RankingFragment;
import canovas.firstnamesmeanings.R;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder>{

    private ArrayList<Ranking> mRankingNames;
    private Context mContext;
    private OnRankingNameClickListener mOnClickListener;

    public RankingAdapter(ArrayList<Ranking> mRankingNames, Context mContext, OnRankingNameClickListener mOnClickListener){
        this.mRankingNames = mRankingNames;
        this.mContext = mContext;
        this.mOnClickListener = mOnClickListener;
    }

    //Inflate the layout of the RecyclerView
    @NonNull
    @Override
    public RankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ranking, parent, false);

        return new ViewHolder(v, mOnClickListener);    }

    //Binds the Views and the data together
    @Override
    public void onBindViewHolder(@NonNull RankingAdapter.ViewHolder holder, int position) {
        holder.ranking_number_txtView.setText(String.valueOf(position+1));
        holder.ranking_name_txtView.setText(mRankingNames.get(position).getFirstName().getFirstName());
        holder.ranking_gender_txtView.setText(mRankingNames.get(position).getFirstName().getGender());
        holder.ranking_origin_txtView.setText(mRankingNames.get(position).getFirstName().getOrigin());

    }

    @Override
    public int getItemCount() {
        return mRankingNames.size();
    }

    //Initiate the Views
    //Static works?? if crash, remove
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ranking_number_txtView;
        TextView ranking_name_txtView;
        TextView ranking_gender_txtView;
        TextView ranking_origin_txtView;

        OnRankingNameClickListener mOnClickListener;

        public ViewHolder(@NonNull View itemView,OnRankingNameClickListener onClickListener) {
            super(itemView);

            ConstraintLayout ranking_layout = itemView.findViewById(R.id.ranking_row_cl);

            ranking_number_txtView = itemView.findViewById(R.id.ranking_row_number_txtView);
            ranking_name_txtView = itemView.findViewById(R.id.ranking_row_name_txtView);
            ranking_gender_txtView = itemView.findViewById(R.id.ranking_row_gender_txtView);
            ranking_origin_txtView = itemView.findViewById(R.id.ranking_row_origin_txtView);

            this.mOnClickListener = onClickListener;

            ranking_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mOnClickListener.onRowClicked(getAdapterPosition());

        }
    }

    public interface OnRankingNameClickListener {
        void onRowClicked(int position);

    }
}
