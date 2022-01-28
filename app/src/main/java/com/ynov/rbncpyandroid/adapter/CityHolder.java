package com.ynov.rbncpyandroid.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ynov.rbncpyandroid.databinding.RowCityListBinding;

public class CityHolder extends RecyclerView.ViewHolder {

    public RowCityListBinding binding;

    public CityHolder(@NonNull RowCityListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

}
