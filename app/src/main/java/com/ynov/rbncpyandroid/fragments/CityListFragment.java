package com.ynov.rbncpyandroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ynov.rbncpyandroid.R;
import com.ynov.rbncpyandroid.ViewModel.CityListViewModel;
import com.ynov.rbncpyandroid.adapter.CityListAdapter;
import com.ynov.rbncpyandroid.bo.City;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityListFragment extends Fragment {

    OkHttpClient client;
    private static final String TAG = "ListeMessageFragment";
    private String token;
    private CityListAdapter adapter;
    private RecyclerView rv;
    private CityListViewModel vm;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
        vm = new ViewModelProvider(this).get(CityListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeCities();
        Observer<ArrayList<City>> observerList = cities -> {
            adapter.setCityArrayList(cities);
            rv.scrollToPosition(adapter.getItemCount()-1);
        };
        vm.getArrayListCity().observe(getViewLifecycleOwner(),observerList);
        if(vm.getArrayListCity().getValue().isEmpty()){
            fetchCities();
        }

    }

    public void fetchCities(){
        Request requestMsg = new Request.Builder()
                .url("https://flutter-learning.mooo.com/villes")
                .build();
        client.newCall(requestMsg).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + "récupération msgs:" + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //Récupérer les messages en ArrayList<Message>
                if(response.code() == 200){
                    ArrayList<City> cities = new Gson().fromJson(
                            response.body().string(),
                            new TypeToken<ArrayList<City>>(){}.getType()
                    );
                    vm.getArrayListCity().postValue(cities);
                }else{
                    Log.e(TAG, "onResponse: " + "authentification incorrecte" );
                }
            }
        });
    }

    private void initializeCities(){
        rv = getView().findViewById(R.id.ListCityRecyclerView);
        adapter = new CityListAdapter();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

}
