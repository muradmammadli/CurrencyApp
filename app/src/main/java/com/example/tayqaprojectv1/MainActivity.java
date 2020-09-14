package com.example.tayqaprojectv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private TextView baseCode, baseRate;
    private ApiInterface apiInterface;
    public List<rates> ratesList;
    private RateAdapter adapter;
    static final String baseCurrency = "GBP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        getRatesList(baseCurrency);
        adapter = new RateAdapter(MainActivity.this, new ArrayList<rates>());
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new RateAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = ratesList.get(position).getCode();
                getRatesList(name);
            }
        });
        baseRate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    int ratio = Integer.parseInt(baseRate.getText().toString());
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtra("ratio", ratio);
                    startActivity(intent);
                }
                return false;
            }
        });

    }

    public void getRatesList(final String name) {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        baseCode.setText(name);
        Disposable subscription = Observable.interval(1000, 5000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Call<List<rates>> call = apiInterface.getRates(name);
                        call.enqueue(new Callback<List<rates>>() {
                            @Override
                            public void onResponse(Call<List<rates>> call, Response<List<rates>> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    ratesList = response.body();
                                    adapter.addData(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<rates>> call, Throwable t) {
                                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                });

    }

    private void bindViews() {
        baseCode = findViewById(R.id.baseCode);
        baseRate = findViewById(R.id.baseRate);
        rv = findViewById(R.id.recyclerView);
    }
}