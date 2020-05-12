package com.example.globallogictest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globallogictest.MainActivity;
import com.example.globallogictest.R;
import com.example.globallogictest.adapters.InfoAdapter;
import com.example.globallogictest.data.Info;
import com.example.globallogictest.utils.RetrofitUtils;
import com.example.globallogictest.viewmodels.InfoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends Fragment {

    private AppCompatActivity activity;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private InfoViewModel infoViewModel;

    public InfoFragment() { }

    public static InfoFragment newInstance() {
        return  new InfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        RetrofitUtils.getInfo(InfoData -> {
            if(InfoData.size()>0) {
                infoViewModel.deleteInfo();
                for (Info info : InfoData)
                    infoViewModel.insert(info);
            }
        });
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        activity = (AppCompatActivity) getActivity();
        final InfoAdapter adapter = new InfoAdapter(getContext(), info -> ((MainActivity)getActivity()).selectFragment(info));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        infoViewModel.getAllInfo().observe(this, new Observer<List<Info>>() {
            @Override
            public void onChanged(@Nullable final List<Info> info) {
                // Update the cached copy of the words in the adapter.
                adapter.setInfo(info);
            }
        });
    }
}
