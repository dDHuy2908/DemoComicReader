package com.ddhuy.comicreader.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ddhuy.comicreader.R;
import com.ddhuy.comicreader.adapters.ImagePagerAdapter;
import com.ddhuy.comicreader.databinding.ActivityImageShowBinding;
import com.ddhuy.comicreader.listener.ImageShowNaviListener;
import com.ddhuy.comicreader.models.Chapter;
import com.ddhuy.comicreader.models.Common;
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

public class ImageShowActivity extends AppCompatActivity implements ImageShowNaviListener {

    private ActivityImageShowBinding binding;
    private ImagePagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_show);

        binding.setListener(this);
        showImage(Common.chapterSelected);
    }

    @Override
    public void onPreClickedListener() {
        if (Common.chapterIndex == 0) {
            Toast.makeText(this, "You are reading the first chapter!", Toast.LENGTH_LONG).show();
        } else {
            Common.chapterIndex--;
            showImage(Common.chapterList.get(Common.chapterIndex));
        }
    }

    @Override
    public void onNextClickedListener() {
        if (Common.chapterIndex == Common.chapterListSize) {
            Toast.makeText(this, "You are reading the last chapter!", Toast.LENGTH_LONG).show();
        } else {
            Common.chapterIndex++;
            showImage(Common.chapterList.get(Common.chapterIndex));
        }
    }

    private void showImage(Chapter chapter) {
        if (chapter.getLinks() != null) {
            if (chapter.getLinks().size() > 0) {
                adapter = new ImagePagerAdapter(this, chapter.getLinks());
                binding.viewPager.setAdapter(adapter);

                BookFlipPageTransformer bookFlipPageTransformer = new BookFlipPageTransformer();
                bookFlipPageTransformer.setScaleAmountPercent(10f);
                binding.viewPager.setPageTransformer(true, bookFlipPageTransformer);
            } else {
                Toast.makeText(this, "Mo image to show!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "This chapter is updating!", Toast.LENGTH_LONG).show();
        }
    }
}
