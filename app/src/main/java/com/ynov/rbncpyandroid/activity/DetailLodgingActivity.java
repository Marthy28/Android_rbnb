package com.ynov.rbncpyandroid.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.squareup.picasso.Picasso;
import com.ynov.rbncpyandroid.R;
import com.ynov.rbncpyandroid.bo.Lodging;
import com.ynov.rbncpyandroid.databinding.ActivityDetailLodgingBinding;

public class DetailLodgingActivity extends AppCompatActivity {

    Lodging lodging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        lodging = (Lodging) myIntent.getSerializableExtra("lodging");

        setContentView(R.layout.activity_detail_lodging);
        ActivityDetailLodgingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_lodging);
        binding.price.setText(lodging.getPrice() + "/nuit");
        Picasso.get().load("https://flutter-learning.mooo.com" + lodging.getIllustrations().getUrl()).fit().centerCrop().into(binding.lodgingPicture);
        binding.lodgingTitle.setText(lodging.getTitle());
    }

}
