package com.ddhuy.comicreader.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ddhuy.comicreader.listener.BannerLoadDoneListener;
import com.ddhuy.comicreader.listener.ComicClickedListener;
import com.ddhuy.comicreader.listener.ComicLoadDoneListener;
import com.ddhuy.comicreader.models.Common;
import com.ddhuy.comicreader.R;
import com.ddhuy.comicreader.adapters.BannerSliderAdapter;
import com.ddhuy.comicreader.adapters.ComicAdapter;
import com.ddhuy.comicreader.databinding.ActivityMainBinding;
import com.ddhuy.comicreader.models.Comic;
import com.ddhuy.comicreader.services.GlideLoadingService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import ss.com.bannerslider.Slider;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Runnable, BannerLoadDoneListener, ComicLoadDoneListener, ComicClickedListener {

    private ActivityMainBinding binding;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference bannerReference = database.getReference("Banners");
    private DatabaseReference comicReference = database.getReference("Comic");

    private BannerLoadDoneListener bannerListener;
    private ComicLoadDoneListener comicListener;

    private ComicAdapter adapter;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Slider.init(new GlideLoadingService());

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.swipeRefreshLayout.post(this);

        bannerListener = this;
        comicListener = this;

        adapter = new ComicAdapter(getLayoutInflater());
        binding.rvComic.setAdapter(adapter);
        binding.rvComic.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvComic.setHasFixedSize(true);
        adapter.setListener(this);

        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setCancelable(false)
                .setMessage("Please wait ...")
                .setTheme(R.style.CustomSpotsDialog)
                .build();
    }

    @Override
    public void onRefresh() {
        loadBanner();
    }

    @Override
    public void run() {
        loadBanner();
    }

    private void loadBanner() {
        if (!binding.swipeRefreshLayout.isRefreshing()) {
            alertDialog.show();
        }

        bannerReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> bannerList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String image = snapshot.getValue(String.class);
                    bannerList.add(image);
                }
                bannerListener.onBannerLoadDoneListener(bannerList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "" + databaseError.getMessage(), LENGTH_LONG).show();
            }
        });
    }

    private void loadComic() {
        comicReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Comic> comicList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Comic comic = snapshot.getValue(Comic.class);
                    comicList.add(comic);
                }
                adapter.setData(comicList);
                adapter.notifyDataSetChanged();
                comicListener.onComicLoadDoneListener(comicList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "" + databaseError.getMessage(), LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBannerLoadDoneListener(ArrayList<String> bannerList) {
        binding.sliderBanner.setAdapter(new BannerSliderAdapter(bannerList));
        loadComic();
    }

    @Override
    public void onComicLoadDoneListener(ArrayList<Comic> comicList) {
        if (!binding.swipeRefreshLayout.isRefreshing()) {
            alertDialog.dismiss();
        }
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onComicClickedListener(Comic comic) {
        this.startActivity(new Intent(this, ChapterActivity.class));
        Common.comicSelected = comic;
//        Log.e(this.getClass().getSimpleName(), "" + Common.comicSelected.getChapters().size());
    }
}
