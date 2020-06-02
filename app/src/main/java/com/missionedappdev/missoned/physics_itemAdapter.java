package com.missionedappdev.missoned;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class physics_itemAdapter extends RecyclerView.Adapter<physics_itemAdapter.ViewHolder>
{
    private ArrayList<physics_item> chaptersList;
    private Context context;
    ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public physics_itemAdapter(Context context,ArrayList<physics_item> chaptersList)
    {
        this.context=context;
        this.chaptersList=chaptersList;
        activity=(ItemClicked)context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView; TextView tvChapter;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvChapter=itemView.findViewById(R.id.tvChapter);
            imageView=itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(chaptersList.indexOf((physics_item) v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public physics_itemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.physics_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull physics_itemAdapter.ViewHolder holder, int pos) {

        holder.itemView.setTag(chaptersList.get(pos));
        holder.tvChapter.setText(chaptersList.get(pos).getChapterName());
        holder.imageView.setImageResource(R.drawable.ic_check_box);

    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }
}