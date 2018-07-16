package com.example.priyankadesai.walmarttestapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.priyankadesai.walmarttestapp.R;
import com.example.priyankadesai.walmarttestapp.viewmodel.MainActivityViewModel;
import com.example.priyankadesai.walmarttestapp.viewmodel.MainActivityViewModel.CurrentFragment;

public class MainActivity extends AppCompatActivity {

    private final static String TAG_PRODUCT_LIST_FRAGMENT = "TAG_PRODUCT_LIST_FRAGMENT";
    private final static String TAG_PRODUCT_DESC = "TAG_PRODUCT_DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getCurrentFragmentLiveData().observe(this, new Observer<CurrentFragment>() {
            @Override
            public void onChanged(@Nullable CurrentFragment currentFragment) {
                if (currentFragment != null) {
                    switch (currentFragment) {
                        case FRAGMENT_PRODUCT_LIST:
                            loadProductListFragment();
                            break;
                        case FRAGMENT_PRODUCT_DESC:
                            loadProductDescFragment(mainActivityViewModel.getLiveData().getValue());
                            break;
                    }
                }
            }
        });
    }

    private void loadProductListFragment() {
        if (getSupportFragmentManager().findFragmentByTag(TAG_PRODUCT_LIST_FRAGMENT) == null) {
            ProductListFragment productListFragment = ProductListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, productListFragment, TAG_PRODUCT_LIST_FRAGMENT)
                    .commit();
        }
    }

    private void loadProductDescFragment(MainActivityViewModel.ProductObject productObject) {
        if (productObject != null && getSupportFragmentManager().findFragmentByTag(TAG_PRODUCT_DESC) == null) {
            ViewPagerFragment viewPagerFragment = ViewPagerFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                            R.anim.slide_in_from_left, R.anim.slide_out_to_right)
                    .replace(R.id.container, viewPagerFragment, TAG_PRODUCT_DESC)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
