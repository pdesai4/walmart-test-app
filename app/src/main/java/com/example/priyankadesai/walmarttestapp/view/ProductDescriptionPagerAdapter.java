package com.example.priyankadesai.walmarttestapp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.priyankadesai.walmarttestapp.model.ProductList;

import java.util.List;

public class ProductDescriptionPagerAdapter extends FragmentStatePagerAdapter {

    private List<ProductList.Product> mProductList;

    ProductDescriptionPagerAdapter(FragmentManager fragmentManager, List<ProductList.Product> productList) {
        super(fragmentManager);
        mProductList = productList;
    }

    @Override
    public Fragment getItem(int position) {
        return ProductDescriptionFragment.newInstance(mProductList.get(position));
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }
}
