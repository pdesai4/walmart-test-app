package com.example.priyankadesai.walmarttestapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.priyankadesai.walmarttestapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the ProductListFragment
        ProductListFragment productListFragment = ProductListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container, productListFragment).commit();
    }
}
