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

import java.util.Objects;

public class ViewPagerFragment extends Fragment {

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainActivityViewModel.class);
        MainActivityViewModel.ProductObject productObject = mainActivityViewModel.getLiveData().getValue();
        ViewPager viewPager = getActivity().findViewById(R.id.pager);
        if (productObject != null) {
            ProductDescriptionPagerAdapter productDescriptionPagerAdapter =
                    new ProductDescriptionPagerAdapter(getFragmentManager(), productObject.getProducts());
            viewPager.setAdapter(productDescriptionPagerAdapter);
            viewPager.setCurrentItem(productObject.getPosition());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_description, container, false);
    }
}
