package com.example.priyankadesai.walmarttestapp.Paging;

import android.arch.paging.DataSource;

import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.network.RemoteDataSource;

public class ProductDataSourceFactory extends DataSource.Factory<Integer, ProductList.Product> {

    private RemoteDataSource mRemoteDataSource;

    public ProductDataSourceFactory(RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public DataSource<Integer, ProductList.Product> create() {
        return new ProductDataSource(mRemoteDataSource);
    }
}
