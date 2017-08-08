package com.project.dajver.listpaginationexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.widget.ProgressBar;

import com.project.dajver.listpaginationexample.adapter.MusicRecyclerAdapter;
import com.project.dajver.listpaginationexample.api.RestClient;
import com.project.dajver.listpaginationexample.api.model.SearchModel;
import com.project.dajver.listpaginationexample.task.AddItemsTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<SearchModel>,
        AddItemsTask.OnAddItemListener {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public int NUM_ITEMS_PAGE = 10;

    private MusicRecyclerAdapter musicRecyclerAdapter;
    private List<List<String>> listList;
    private int paginationCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recycleViewSetup(recycleView);
        musicRecyclerAdapter = new MusicRecyclerAdapter();
        recycleView.setAdapter(musicRecyclerAdapter);
        recycleView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    new AddItemsTask(MainActivity.this).execute();
                }
            }
        });

        RestClient.instance().searchAudio("Armin", RestClient.API_KEY).enqueue(this);
    }

    public void recycleViewSetup(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
        this.listList = response.body().getList();
        addItemToAdapter();
    }

    @Override
    public void onFailure(Call<SearchModel> call, Throwable t) {
        t.printStackTrace();
    }

    private void addItemToAdapter() {
        paginationCounter += NUM_ITEMS_PAGE;
        for (int i = paginationCounter - NUM_ITEMS_PAGE; i < paginationCounter; i++) {
            progressBar.setVisibility(View.VISIBLE);
            if (i < listList.size() && listList.get(i) != null)
                musicRecyclerAdapter.add(listList.get(i));
        }
    }

    @Override
    public void onStartTask() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void addItem() {
        addItemToAdapter();
        progressBar.setVisibility(View.GONE);
    }
}