package com.example.newstak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {
    String api = "6a7e1dc9ac4241519b3ba7b3f181e616";
    ArrayList<Model> modelArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView healthrecyclerview;
    private String category = "health";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.healthfragment,null);

        healthrecyclerview = v.findViewById(R.id.healthrecl);
        modelArrayList = new ArrayList<>();

        healthrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelArrayList);
        healthrecyclerview.setAdapter(adapter);

        findNews();



        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                if(response.isSuccessful()){
                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}
