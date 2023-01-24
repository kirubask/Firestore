package com.example.firestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<UserData> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recycler_view);
        SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems= new ArrayList<>();
        UserData userData = null;
        for (int i = 0; i < 100 ; i++) {
            userData = new UserData("dublicate@gamil.com" + (i+1));
            listItems.add(userData);
        }
        adapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);

    }
}