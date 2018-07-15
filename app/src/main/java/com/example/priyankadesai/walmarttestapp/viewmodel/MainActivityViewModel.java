package com.example.priyankadesai.walmarttestapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.priyankadesai.walmarttestapp.model.ProductList;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ProductObject> mProductObjectLiveData;
    private MutableLiveData<CurrentFragment> mCurrentFragmentLiveData;

    public MainActivityViewModel() {
        mProductObjectLiveData = new MutableLiveData<>();
        mCurrentFragmentLiveData = new MutableLiveData<>();
        mCurrentFragmentLiveData.setValue(CurrentFragment.FRAGMENT_PRODUCT_LIST);
    }

    public MutableLiveData<ProductObject> getLiveData() {
        return mProductObjectLiveData;
    }

    public MutableLiveData<CurrentFragment> getCurrentFragmentLiveData() {
        return mCurrentFragmentLiveData;
    }

    public enum CurrentFragment {
        FRAGMENT_PRODUCT_LIST,
        FRAGMENT_PRODUCT_DESC
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
