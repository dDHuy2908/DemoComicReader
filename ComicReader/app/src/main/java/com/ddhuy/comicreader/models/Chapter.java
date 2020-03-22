package com.ddhuy.comicreader.models;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private String Name;
    private ArrayList<String> Links;

    public Chapter() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<String> getLinks() {
        return Links;
    }

    public void setLinks(ArrayList<String> links) {
        Links = links;
    }
}
