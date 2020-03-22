package com.ddhuy.comicreader.listener;

import com.ddhuy.comicreader.models.Comic;

import java.util.ArrayList;
import java.util.List;

public interface ComicLoadDoneListener {
    void onComicLoadDoneListener(ArrayList<Comic> comicList);
}
