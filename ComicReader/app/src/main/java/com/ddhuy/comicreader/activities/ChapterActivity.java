package com.ddhuy.comicreader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ddhuy.comicreader.listener.ChapterClickedListener;
import com.ddhuy.comicreader.models.Chapter;
import com.ddhuy.comicreader.models.Common;
import com.ddhuy.comicreader.R;
import com.ddhuy.comicreader.adapters.ChapterAdapter;
import com.ddhuy.comicreader.databinding.ActivityChapterBinding;

public class ChapterActivity extends AppCompatActivity implements View.OnClickListener, ChapterClickedListener {

    private ActivityChapterBinding binding;

    private ChapterAdapter adapter;

    private Common common = new Common();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chapter);

        binding.toolbar.setTitle(Common.comicSelected.getName());
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setNavigationOnClickListener(this);

        adapter = new ChapterAdapter(getLayoutInflater());
        binding.rvChapter.setAdapter(adapter);
        binding.rvChapter.setLayoutManager(new LinearLayoutManager(this));
        adapter.setData(Common.comicSelected.getChapters());
        binding.rvChapter.setHasFixedSize(true);
        adapter.setListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onChapterClickedListener(Chapter chapter) {
        Common.chapterIndex = adapter.getData().indexOf(chapter);
        Common.chapterList = adapter.getData();
        Common.chapterSelected = chapter;
        Common.chapterListSize = adapter.getData().size();
        this.startActivity(new Intent(this, ImageShowActivity.class));
    }
}
