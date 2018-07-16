package com.example.priyankadesai.walmarttestapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.priyankadesai.walmarttestapp.R;
import com.example.priyankadesai.walmarttestapp.viewmodel.MainActivityViewModel;

public class ViewPagerFragment extends Fragment {

    private MainActivityViewModel mMainActivityViewModel;

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainActivityViewModel.getCurrentFragmentLiveData().setValue(MainActivityViewModel.CurrentFragment.FRAGMENT_PRODUCT_DESC);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            mMainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
            MainActivityViewModel.ProductObject productObject = mMainActivityViewModel.getProductObjectLiveData().getValue();
            ViewPager viewPager = view.findViewById(R.id.pager);
            if (productObject != null) {
                ProductDescriptionPagerAdapter productDescriptionPagerAdapter =
                        new ProductDescriptionPagerAdapter(getFragmentManager(), productObject.getProducts());
                viewPager.setAdapter(productDescriptionPagerAdapter);
                viewPager.setCurrentItem(productObject.getPosition());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_description, container, false);
    }
}
