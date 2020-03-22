package com.ddhuy.comicreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ddhuy.comicreader.R;
import com.ddhuy.comicreader.services.GlideLoadingService;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> links;
    private LayoutInflater inflater;

    public ImagePagerAdapter(Context context, ArrayList<String> links) {
        this.context = context;
        this.links = links;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return links.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View image_layout = inflater.inflate(R.layout.item_image_pager, container, false);
        PhotoView page_image = image_layout.findViewById(R.id.page_image);
        Glide.with(container).load(links.get(position)).into(page_image);
        container.addView(image_layout);
        return image_layout;
    }
}
