package com.example.demorecyclerview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Recycler_View extends AppCompatActivity {

    private ArrayList<Hero> mListHero = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = findViewById(R.id.recycler_main);

        mAdapter = new RecyclerAdapter(mListHero);
        RecyclerView.LayoutManager mManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        addInfoHero();

    }

    private void addInfoHero() {
        mListHero.add(new Hero("Iron Man",R.drawable.ic_iron_man));
        mListHero.add(new Hero("Thor",R.drawable.ic_thor));
        mListHero.add(new Hero("Black Panther",R.drawable.ic_black_penther));
        mListHero.add(new Hero("Spider Man",R.drawable.ic_spiderman));
    }
}
