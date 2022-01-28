package com.ynov.rbncpyandroid.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.ynov.rbncpyandroid.R;
import com.ynov.rbncpyandroid.activity.DetailLodgingActivity;
import com.ynov.rbncpyandroid.bo.City;
import com.ynov.rbncpyandroid.bo.Lodging;
import com.ynov.rbncpyandroid.databinding.RowCityListBinding;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityListAdapter extends RecyclerView.Adapter<CityHolder> {

    ArrayList<City> cityArrayList;

    public CityListAdapter() {
        cityArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCityListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_city_list,
                parent,
                false
        );
        return new CityHolder(binding);
    }

    public void setCityArrayList(ArrayList<City> messageArrayList) {
        this.cityArrayList = messageArrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        City city = cityArrayList.get(position);
        holder.binding.setCity(city);
        ImageView picture = holder.itemView.findViewById(R.id.cityPicture);
        Picasso.get().load("https://flutter-learning.mooo.com" + city.getPic().getUrl()).resize(50,50).into(picture);
        holder.itemView.setOnClickListener(
                (view)-> goToDetail(holder, city.getId()) );
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }

    public void addCity(City m){
        cityArrayList.add(m);
        notifyItemInserted(cityArrayList.size()-1);
    }

    void goToDetail(CityHolder holder, String cityId)
        {
            Request requestMsg = new Request.Builder()
                    .url("https://flutter-learning.mooo.com/logements?place.id=" + cityId)
                    .build();
            new OkHttpClient().newCall(requestMsg).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e("DetailLodgingActivity", "onFailure: " + "récupération des infos:" + e.getMessage());
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    //Récupérer les messages en ArrayList<Message>
                    if (response.code() == 200) {
                        ArrayList<Lodging>lodging = new Gson().fromJson(
                                response.body().string(),
                                new TypeToken<ArrayList<Lodging>>() {
                                }.getType());

                        if (!lodging.isEmpty()) {
                            Intent intentToLodging = new Intent(holder.itemView.getContext(), DetailLodgingActivity.class);
                            intentToLodging.putExtra("lodging", lodging.get(0));
                            holder.itemView.getContext().startActivity(intentToLodging);
                        }

                    } else {
                        Log.e("DetailLodgingActivity", "onResponse: " + "authentification incorrecte");
                    }
                }
            });
        }

}
