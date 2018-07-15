package com.example.priyankadesai.walmarttestapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.priyankadesai.walmarttestapp.R;
import com.example.priyankadesai.walmarttestapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the ProductListFragment
        ProductListFragment productListFragment = ProductListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container, productListFragment).commit();

        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getLiveData().observe(this, new Observer<MainActivityViewModel.ProductObject>() {
            @Override
            public void onChanged(@Nullable MainActivityViewModel.ProductObject productObject) {
                ViewPagerFragment viewPagerFragment = ViewPagerFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, viewPagerFragment).commit();
            }
        });
    }
}
