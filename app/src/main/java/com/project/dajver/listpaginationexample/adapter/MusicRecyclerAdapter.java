package com.project.dajver.listpaginationexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.dajver.listpaginationexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 8/8/17.
 */

public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.ViewHolder>{

    private List<List<String>> searchModels = new ArrayList<>();

    public void add(List<String> string) {
        searchModels.add(string);
        notifyDataSetChanged();
    }

    @Override
    public MusicRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        MusicRecyclerAdapter.ViewHolder pvh = new MusicRecyclerAdapter.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MusicRecyclerAdapter.ViewHolder holder, final int position) {
        holder.title.setText(searchModels.get(position).get(4) + " - " + searchModels.get(position).get(3));
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}