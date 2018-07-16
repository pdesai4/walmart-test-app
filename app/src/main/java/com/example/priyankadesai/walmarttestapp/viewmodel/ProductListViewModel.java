package com.example.priyankadesai.walmarttestapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.priyankadesai.walmarttestapp.Paging.ProductDataSourceFactory;
import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.network.RemoteDataSource;

public class ProductListViewModel extends ViewModel {

    private final LiveData<PagedList<ProductList.Product>> pagedListLiveData;

    public ProductListViewModel() {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        // Set page configs
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(12)
                .setPrefetchDistance(12)
                .build();
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(remoteDataSource);
        pagedListLiveData = new LivePagedListBuilder<>(productDataSourceFactory, config).build();
    }

    public LiveData<PagedList<ProductList.Product>> getLiveData() {
        return pagedListLiveData;
    }
}
