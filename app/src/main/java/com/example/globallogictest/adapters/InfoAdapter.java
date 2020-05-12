package com.example.globallogictest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.globallogictest.R;
import com.example.globallogictest.data.Info;
import com.example.globallogictest.interfaces.InfoDetailClickListener;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private final LayoutInflater mInflater;
    private List<Info> mInfo; // Cached copy of words
    private Context mContext;
    private InfoDetailClickListener mClickListener;

    public InfoAdapter(Context context, @Nullable InfoDetailClickListener clickListener) {
        mContext = context;
        mClickListener = clickListener;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.detail_item, parent, false);
        return new InfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        if (mInfo != null) {
            Info current = mInfo.get(position);
            holder.titleTextView.setText(current.getTitle());
            holder.textTextView.setText(current.getDescription());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_no_image);
            requestOptions.error(R.drawable.ic_error_not_found);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(current.getImg())
                    .into(holder.imageItemView);

            if(mClickListener != null)
                holder.view.setOnClickListener(v -> {
                    mClickListener.OnInfoDetailClickListener(current);
                });
        } else {
            // Covers the case of data not being ready yet.
            holder.textTextView.setText("No title");
        }
    }

    public void setInfo(List<Info> info){
        mInfo = info;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    @Override
    public int getItemCount() {
        if (mInfo != null)
            return mInfo.size();
        else return 0;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {
        private final TextView  textTextView;
        private final TextView  titleTextView;
        private final ImageView imageItemView;
        private final View view;

        private InfoViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textTextView = itemView.findViewById(R.id.text);
            imageItemView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.title);
        }
    }
}