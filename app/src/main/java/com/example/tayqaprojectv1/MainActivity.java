package com.example.tayqaprojectv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private CardView cardView;
    private ApiInterface apiInterface;
    public List<rates> ratesList;
    private RateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerView);
        cardView = findViewById(R.id.cardView);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        adapter = new RateAdapter(this, new ArrayList<rates>());
        rv.setAdapter(adapter);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<rates>> call = apiInterface.getRates();
        call.enqueue(new Callback<List<rates>>() {
            @Override
            public void onResponse(Call<List<rates>> call, Response<List<rates>> response) {
                if (response.isSuccessful() && response.body() != null){
                    ratesList = response.body();
                    adapter.addData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<rates>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}