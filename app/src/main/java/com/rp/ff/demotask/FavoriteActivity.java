package com.rp.ff.demotask;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// FavoriteActivity.java
public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView favoriteRecyclerView;
    private List<City> favoriteCities;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteRecyclerView = findViewById(R.id.favoriteRecyclerView);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("favorites", MODE_PRIVATE);

        loadFavoriteCities();
        CityAdapter adapter = new CityAdapter(favoriteCities, city -> {
            // Handle clicks if necessary in favorite cities (optional)
        });
        favoriteRecyclerView.setAdapter(adapter);
    }

    // Load only favorite cities from SharedPreferences
    private void loadFavoriteCities() {
        favoriteCities = new ArrayList<>();
        Map<String, ?> favorites = sharedPreferences.getAll();  // Get all favorites from SharedPreferences
        for (Map.Entry<String, ?> entry : favorites.entrySet()) {
            favoriteCities.add(new City(entry.getKey()));  // Add favorite cities to the list
        }
    }
}
