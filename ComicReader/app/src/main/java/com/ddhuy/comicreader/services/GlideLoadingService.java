package com.ddhuy.comicreader.services;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import ss.com.bannerslider.ImageLoadingService;

public class GlideLoadingService implements ImageLoadingService {
    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(imageView).load(url).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Glide.with(imageView).load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Glide.with(imageView).load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}
