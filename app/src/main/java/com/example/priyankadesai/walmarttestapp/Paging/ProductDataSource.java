package com.example.priyankadesai.walmarttestapp.Paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.network.RemoteDataSource;

public class ProductDataSource extends PageKeyedDataSource<Integer, ProductList.Product> {

    private RemoteDataSource mRemoteDataSource;

    ProductDataSource(RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ProductList.Product> callback) {
        ProductList data = getData(1, params.requestedLoadSize);

        if (data != null) {
            callback.onResult(data.getProducts(), 0, data.getTotalProducts(), null, 2);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ProductList.Product> callback) {
        // Not required
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ProductList.Product> callback) {
        ProductList data = getData(params.key, params.requestedLoadSize);
        int adjKey = 0;

        if (data != null) {
            if (params.key < data.getTotalProducts()) {
                adjKey = params.key + 1;
            }

            callback.onResult(data.getProducts(), adjKey);
        }
    }

    /**
     * Network call to get data
     *
     * @param pageNumber start page to get data from
     * @param pageSize   number of pages to get
     * @return {@link ProductList} retrieved data
     */
    private ProductList getData(int pageNumber, int pageSize) {
        ProductList data = mRemoteDataSource.getData(pageNumber, pageSize);
        if (data == null) {
            System.err.println("Error fetching data");
            return null;
        }
        return data;
    }

}
