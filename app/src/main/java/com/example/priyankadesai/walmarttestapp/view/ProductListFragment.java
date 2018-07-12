package com.example.priyankadesai.walmarttestapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.priyankadesai.walmarttestapp.R;
import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.viewmodel.ProductListViewModel;

public class ProductListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProductListViewModel mProductListViewModel;

    /**
     * In order to avoid parameterized constructor, this method is called to get instance of
     * ProductListFragment class
     *
     * @return instance of {@link ProductListFragment}
     */
    public static ProductListFragment newInstance() {
        Bundle args = new Bundle();
        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(args);
        return productListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.product_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        mProductListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        final ProductListAdapter productListAdapter = new ProductListAdapter();

        mProductListViewModel.getLiveData().observe(this, new Observer<PagedList<ProductList.Product>>() {
            @Override
            public void onChanged(@Nullable PagedList<ProductList.Product> products) {
                productListAdapter.submitList(products);
            }
        });
        mRecyclerView.setAdapter(productListAdapter);
    }
}
