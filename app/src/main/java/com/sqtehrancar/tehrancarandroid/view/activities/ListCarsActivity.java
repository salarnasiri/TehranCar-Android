package com.sqtehrancar.tehrancarandroid.view.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sqtehrancar.tehrancarandroid.R;
import com.sqtehrancar.tehrancarandroid.contorller.adapter.CarsRecyclerView;
import com.sqtehrancar.tehrancarandroid.contorller.network.ApiService;
import com.sqtehrancar.tehrancarandroid.contorller.network.RetrofitClientInstance;
import com.sqtehrancar.tehrancarandroid.model.Car;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCarsActivity extends AppCompatActivity implements Callback<List<Car>> {

    @BindView(R.id.recyclerView_main_cars)
    RecyclerView recyclerViewMainCars;
    private CarsRecyclerView adapter;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car_admin);
        ButterKnife.bind(this);

        service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        updateList();
    }

    private void updateList() {
        Call<List<Car>> call = service.getAllCars();
        call.enqueue(this);
    }

    private void generateDataList(final List<Car> cars) {
        adapter = new CarsRecyclerView(this, cars);
        recyclerViewMainCars.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMainCars.setAdapter(adapter);
    }

    @Override
    public void onResponse(@NonNull Call<List<Car>> call, @NonNull Response<List<Car>> response) {
        generateDataList(response.body());
    }

    @Override
    public void onFailure(@NonNull Call<List<Car>> call, @NonNull Throwable t) {
        Toast.makeText(ListCarsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }
}
