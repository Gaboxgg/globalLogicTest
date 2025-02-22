package com.example.globallogictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.globallogictest.data.Info;
import com.example.globallogictest.fragments.InfoDetailFragment;
import com.example.globallogictest.fragments.InfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.MainLayout)
    ConstraintLayout MainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(savedInstanceState == null)
            selectFragment();
        else{
            InfoDetailFragment myFragment = (InfoDetailFragment) getSupportFragmentManager()
                    .findFragmentByTag("url");
        }
    }

    public void selectFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = new InfoFragment();
        transaction.replace(R.id.MainLayout, fragment);
        transaction.commit();
    }
    public void selectFragment(Info info) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        new InfoDetailFragment();
        Fragment fragment = InfoDetailFragment.newInstance(info);
        transaction.addToBackStack("url");
        transaction.add(R.id.MainLayout, fragment);
        transaction.commit();
        }

}
