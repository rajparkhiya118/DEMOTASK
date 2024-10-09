package com.rp.ff.demotask;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// CityActivity.java
public class CityActivity extends AppCompatActivity {

    private RecyclerView cityRecyclerView;
    private List<City> cityList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        cityRecyclerView = findViewById(R.id.cityRecyclerView);
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("favorites", MODE_PRIVATE);

        // Load cities and favorite states
        String countryName = getIntent().getStringExtra("countryName");
        List<String> cityNames = getIntent().getStringArrayListExtra("cityList");

        cityList = new ArrayList<>();
        for (String cityName : cityNames) {
            City city = new City(cityName);
            city.setFavorite(sharedPreferences.getBoolean(city.getName(), false));  // Load favorite state from SharedPreferences
            cityList.add(city);
        }

        CityAdapter adapter = new CityAdapter(cityList, this::onFavoriteClick);
        cityRecyclerView.setAdapter(adapter);
    }

    // Handle favorite/unfavorite actions
    private void onFavoriteClick(City city) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (city.isFavorite()) {
            // Add to favorites
            editor.putBoolean(city.getName(), true);
        } else {
            // Remove from favorites
            editor.remove(city.getName());
        }

        editor.apply();
    }
}
