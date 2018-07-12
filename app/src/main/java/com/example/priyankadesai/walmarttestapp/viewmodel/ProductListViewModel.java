package com.example.priyankadesai.walmarttestapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.priyankadesai.walmarttestapp.Paging.ProductDataSourceFactory;
import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.network.RemoteDataSource;

public class ProductListViewModel extends ViewModel {

    private RemoteDataSource mRemoteDataSource;

    ProductListViewModel() {
        mRemoteDataSource = new RemoteDataSource();
    }

    /**
     * This function is responsible for creating the {@link PagedList} and give to the
     * {@link com.example.priyankadesai.walmarttestapp.view.ProductListFragment} so
     * it can observe the data changes and pass it to the adapter
     *
     * @return {@link PagedList} to be observed
     */
    public LiveData<PagedList<ProductList.Product>> getLiveData() {
        // Set page configs
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(5)
                .build();

        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(mRemoteDataSource);
        return new LivePagedListBuilder<>(productDataSourceFactory, config).build();
    }
}
