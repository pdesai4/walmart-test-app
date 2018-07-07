package com.example.priyankadesai.walmarttestapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.network.RemoteDataSource;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<List<ProductList.Product>> mMutableLiveData;
    private RemoteDataSource mRemoteDataSource;

    ProductListViewModel() {
        mRemoteDataSource = new RemoteDataSource();
    }

    public LiveData<List<ProductList.Product>> getLiveData() {
        if (mMutableLiveData == null) {
            mMutableLiveData = new MutableLiveData<>();
            mRemoteDataSource.getData(mMutableLiveData);
        }
        return mMutableLiveData;
    }

}
