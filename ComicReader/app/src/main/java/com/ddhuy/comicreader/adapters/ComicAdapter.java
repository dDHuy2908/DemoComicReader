package com.ddhuy.comicreader.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddhuy.comicreader.BR;
import com.ddhuy.comicreader.listener.ComicClickedListener;
import com.ddhuy.comicreader.databinding.ItemComicBinding;
import com.ddhuy.comicreader.models.Comic;

import java.util.ArrayList;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicHolder> {

    private ArrayList<Comic> data;
    private LayoutInflater inflater;
    private ComicClickedListener listener;

    public ComicAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(ArrayList<Comic> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(ComicClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ComicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemComicBinding binding = ItemComicBinding.inflate(inflater, parent, false);
        return new ComicHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicHolder holder, int position) {
        holder.binding.setItem(data.get(position));
        if (listener != null) {
            holder.binding.setVariable(BR.listener, listener);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ComicHolder extends RecyclerView.ViewHolder {

        private ItemComicBinding binding;

        public ComicHolder(@NonNull ItemComicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
