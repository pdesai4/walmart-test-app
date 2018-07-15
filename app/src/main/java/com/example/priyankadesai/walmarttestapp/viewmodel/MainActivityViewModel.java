package com.example.priyankadesai.walmarttestapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.priyankadesai.walmarttestapp.model.ProductList;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ProductObject> mMutableLiveData;

    public MainActivityViewModel() {
        mMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ProductObject> getLiveData() {
        return mMutableLiveData;
    }

    public static class ProductObject {
        int position;
        List<ProductList.Product> products;

        public ProductObject(int position, List<ProductList.Product> products) {
            this.position = position;
            this.products = products;
        }

        public List<ProductList.Product> getProducts() {
            return products;
        }

        public int getPosition() {
            return position;
        }
    }
}
