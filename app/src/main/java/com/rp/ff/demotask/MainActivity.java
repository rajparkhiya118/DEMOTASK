package com.rp.ff.demotask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// MainActivity.java
public class MainActivity extends AppCompatActivity {

    private RecyclerView countryRecyclerView;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryRecyclerView = findViewById(R.id.countryRecyclerView);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryList = getCountryData();

        CountryAdapter adapter = new CountryAdapter(countryList, this::onCountryClick);
        countryRecyclerView.setAdapter(adapter);

        Button btnFavorite = findViewById(R.id.btnFavorite);
        btnFavorite.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

    }

    private List<Country> getCountryData() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("USA", Arrays.asList("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")));
        countries.add(new Country("India", Arrays.asList("Delhi", "Mumbai", "Bangalore", "Hyderabad", "Chennai")));
        countries.add(new Country("Germany", Arrays.asList("Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt")));
        countries.add(new Country("Japan", Arrays.asList("Tokyo", "Osaka", "Nagoya", "Sapporo", "Fukuoka")));
        countries.add(new Country("Australia", Arrays.asList("Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide")));

        return countries;
    }

    private void onCountryClick(Country country) {
        Intent intent = new Intent(MainActivity.this, CityActivity.class);
        intent.putExtra("countryName", country.getName());
        intent.putStringArrayListExtra("cityList", new ArrayList<>(country.getCities()));
        startActivity(intent);
    }
}
