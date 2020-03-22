package com.ddhuy.comicreader.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddhuy.comicreader.BR;
import com.ddhuy.comicreader.listener.ChapterClickedListener;
import com.ddhuy.comicreader.databinding.ItemChapterBinding;
import com.ddhuy.comicreader.models.Chapter;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterHolder> {

    private LayoutInflater inflater;
    private ArrayList<Chapter> data;
    private ChapterClickedListener listener;

    public ChapterAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(ArrayList<Chapter> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ArrayList<Chapter> getData() {
        return data;
    }

    public void setListener(ChapterClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChapterBinding binding = ItemChapterBinding.inflate(inflater, parent, false);
        return new ChapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterHolder holder, int position) {
        holder.binding.setItem(data.get(position));
        if (listener != null) {
            holder.binding.setListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ChapterHolder extends RecyclerView.ViewHolder {

        private ItemChapterBinding binding;

        public ChapterHolder(@NonNull ItemChapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
