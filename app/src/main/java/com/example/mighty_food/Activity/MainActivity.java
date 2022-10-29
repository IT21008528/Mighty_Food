package com.example.mighty_food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mighty_food.Adapter.FoodAdapter;
import com.example.mighty_food.Domain.FoodDomain;
import com.example.mighty_food.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewFoodList;

    ///////////////////////////----
    TextView welcomeID;
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////---
        welcomeID=findViewById(R.id.viewID3);

        st=getIntent().getExtras().getString("Value");
        welcomeID.setText(st);

        recyclerViewFood();
        bottomNavigation();
    }
/////////////////
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout loginBtn = findViewById(R.id.loginBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this,CartListActivity.class);
                startActivity(i2);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void recyclerViewFood() {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewFoodList = findViewById(R.id.foodView1);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

       ArrayList<FoodDomain> foodList=new ArrayList<>();
        foodList.add(new FoodDomain("Peporoni Pizza", "pop_1","slices pepperoni, mozzeralla cheese,fresh oregano, ground black pepper", 1050.00));
        foodList.add(new FoodDomain("Cheese Burger", "pop_2","Beef gauda cheese, special sause,lettuce, tomato", 999.00));
        foodList.add(new FoodDomain("Vegetable Pizza", "pop_3","Olive oil,Vegetable Oil,pitted kalamata, cherry tomatoes", 950.00));
        foodList.add(new FoodDomain("Hot Dog", "cat_3","Chicken sausage and Bun", 350.00));

        adapter=new FoodAdapter(foodList);
        recyclerViewFoodList.setAdapter(adapter);
    }
}