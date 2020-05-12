package com.example.globallogictest.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.globallogictest.R;
import com.example.globallogictest.data.Info;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoDetailFragment extends Fragment{
        private AppCompatActivity activity;
        private Info info;

        @BindView(R.id.title)
        TextView titleTextView;
        @BindView(R.id.text)
        TextView textTextView;

        @BindView(R.id.image)
        ImageView imageImageView;

        public InfoDetailFragment() {
        }
        public static  InfoDetailFragment newInstance(Info info) {
            InfoDetailFragment fragment = new InfoDetailFragment();
            Bundle bundle=new Bundle();
            bundle.putSerializable("info",info);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                info = (Info)getArguments().getSerializable("info");
            }
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape
                return inflater.inflate(R.layout.fragment_info_detail_landscape, container, false);
            }
            else {
                // Portrait
                return inflater.inflate(R.layout.fragment_info_detail, container, false);
            }
        }

        @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.bind(this, view);
            activity = (AppCompatActivity) getActivity();
            textTextView.setMovementMethod(new ScrollingMovementMethod());
            titleTextView.setText(info.getTitle());
            textTextView.setText(info.getDescription());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_no_image);
            requestOptions.error(R.drawable.ic_error_not_found);
            Glide.with(getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(info.getImg())
                    .into(imageImageView);
        }


}

