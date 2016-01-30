package br.com.dreambox.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.dreambox.listener.OnDreamClickListener;
import br.com.dreambox.model.Dream;

public class DreamsAdapter extends RecyclerView.Adapter<DreamsAdapter.DreamViewHolder> {

    private Context mContext;
    private List<Dream> mDreams;
    private OnDreamClickListener mClickListener;

    public DreamsAdapter(Context context, OnDreamClickListener dreamClick) {
        mContext = context;
        mClickListener = dreamClick;
    }

    public void updateSeasons(List<Dream> contents) {
        mDreams = contents;
        notifyDataSetChanged();
    }


    @Override
    public DreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DreamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DreamViewHolder extends RecyclerView.ViewHolder {

        public DreamViewHolder(View itemView) {
            super(itemView);
        }
    }

}