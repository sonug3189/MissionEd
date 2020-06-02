package com.missionedappdev.missoned;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity implements physics_itemAdapter.ItemClicked {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<physics_item> physics_itemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        physics_itemArrayList.add(new physics_item("chapter 1", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 2", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 3", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 4", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 5", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 6", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 7", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 8", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 9", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 10", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 11", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 12", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 13", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 14", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 15", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 1", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 2", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 3", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 4", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 5", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 6", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 7", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 8", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 9", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 10", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 11", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 12", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 13", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 14", R.drawable.ic_check_box));
        physics_itemArrayList.add(new physics_item("chapter 15", R.drawable.ic_check_box));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new physics_itemAdapter(this,physics_itemArrayList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClicked(int index) {
        Log.d("Chapter clicked:",physics_itemArrayList.get(index).getChapterName());
        Intent chapIntent=new Intent(Activity2.this,activity_chapter.class);
        chapIntent.putExtra("CHAPTER_INDEX",index);
        chapIntent.putExtra("SUBJECT","CHEMISTRY");
        startActivity(chapIntent);
    }
}
