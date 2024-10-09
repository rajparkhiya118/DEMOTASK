package com.rp.ff.demotask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<City> cityList;
    private OnFavoriteClickListener favoriteClickListener;

    public interface OnFavoriteClickListener {
        void onFavoriteClick(City city);
    }

    public CityAdapter(List<City> cityList, OnFavoriteClickListener favoriteClickListener) {
        this.cityList = cityList;
        this.favoriteClickListener = favoriteClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityNameTextView.setText(city.getName());

        // Set the favorite icon based on the favorite state
        holder.favoriteButton.setImageResource(city.isFavorite() ? R.drawable.ic_star_filled : R.drawable.ic_star_empty);

        // Handle favorite button click
        holder.favoriteButton.setOnClickListener(v -> {
            city.setFavorite(!city.isFavorite()); // Toggle favorite state
            notifyItemChanged(position);          // Refresh UI

            favoriteClickListener.onFavoriteClick(city);  // Notify activity about the favorite toggle
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private ImageButton favoriteButton;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}
