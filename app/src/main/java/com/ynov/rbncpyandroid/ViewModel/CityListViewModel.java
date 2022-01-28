package com.ynov.rbncpyandroid.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ynov.rbncpyandroid.bo.City;

import java.util.ArrayList;

    public class CityListViewModel extends ViewModel {
        MutableLiveData<ArrayList<City>> arrayListCity;

        public MutableLiveData<ArrayList<City>> getArrayListCity(){
            if(arrayListCity == null){
                this.arrayListCity = new MutableLiveData<>(new ArrayList<>());
            }
            return this.arrayListCity;
        }

    }


