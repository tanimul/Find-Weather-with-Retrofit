package com.example.findweatherwithretrofitandjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.findweatherwithretrofitandjson.Retrofit.ApiClient;
import com.example.findweatherwithretrofitandjson.Retrofit.ApiInterface;
import com.example.findweatherwithretrofitandjson.Retrofit.Example;
import com.example.findweatherwithretrofitandjson.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Using View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherData(binding.cityname.getText().toString());
            }
        });
    }

    private void getWeatherData(String name) {


        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Example>call=apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("dddd","dddd");
                binding.textTemp.setText(binding.textTemp.getText()+""+response.body().getMain().getTemp());
                binding.textDesc.setText(binding.textDesc.getText()+""+response.body().getMain().getFeels_like());
                binding.textHumidity.setText(binding.textHumidity.getText()+""+response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}
