package br.com.dreambox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import br.com.dreambox.R;
import br.com.dreambox.listener.OnDreamClickListener;
import br.com.dreambox.model.Dream;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DreamAdapter extends RecyclerView.Adapter<DreamAdapter.DreamViewHolder> {

    private List<Dream> mDreams;
    private OnDreamClickListener mDreamOnClickListener;

    public DreamAdapter(OnDreamClickListener dreamClick) {
        mDreamOnClickListener = dreamClick;
    }

    public void updateSeasons(List<Dream> contents) {
        mDreams = contents;
        notifyDataSetChanged();
    }

    @Override
    public DreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dream, parent, false);
        return new DreamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DreamViewHolder holder, int position) {
        Dream dream = mDreams.get(position);

        holder.mDreamTitle.setText(dream.getTitle());
        holder.mRealized.setChecked(dream.isRealized());
        holder.itemView.setOnClickListener(mDreamOnClickListener.onClick(dream));
    }

    @Override
    public int getItemCount() {
        return (mDreams != null ? mDreams.size() : 0);
    }

    public static class DreamViewHolder extends RecyclerView.ViewHolder {

        public DreamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Bind(R.id.dream_title)
        TextView mDreamTitle;

        @Bind(R.id.realized)
        CheckBox mRealized;
    }
}