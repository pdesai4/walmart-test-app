package com.example.priyankadesai.walmarttestapp.view;

import android.arch.paging.PagedList;

import com.example.priyankadesai.walmarttestapp.model.ProductList;

public interface ProductClickListener {
    void onProductClicked(int position, PagedList<ProductList.Product> product);
}
