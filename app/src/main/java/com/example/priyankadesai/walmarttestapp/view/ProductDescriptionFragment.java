package com.example.priyankadesai.walmarttestapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.priyankadesai.walmarttestapp.R;
import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.example.priyankadesai.walmarttestapp.viewmodel.MainActivityViewModel;
import com.example.priyankadesai.walmarttestapp.viewmodel.MainActivityViewModel.CurrentFragment;

public class ProductDescriptionFragment extends Fragment {
    public static final String ARG_OBJECT = "Product";
    private static final String IMG_URL = "https://mobile-tha-server.appspot.com";

    public static ProductDescriptionFragment newInstance(ProductList.Product product) {
        ProductDescriptionFragment fragment = new ProductDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_OBJECT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            ProductList.Product product = (ProductList.Product) args.get(ARG_OBJECT);
            ProductDisplay productDisplay = new ProductDisplay(view);
            if (product != null) {
                productDisplay.bind(product);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.description_product, container, false);
    }

    class ProductDisplay {
        private final TextView productName;
        private final RatingBar productRating;
        private final TextView productReviewCount;
        private final ImageView productImage;
        private final TextView productPrice;
        private final TextView productDescription;

        ProductDisplay(final View view) {
            productName = view.findViewById(R.id.product_name);
            productRating = view.findViewById(R.id.product_rating);
            productReviewCount = view.findViewById(R.id.product_review_count);
            productImage = view.findViewById(R.id.product_image);
            productPrice = view.findViewById(R.id.product_price);
            productDescription = view.findViewById(R.id.product_description);
        }

        void bind(final ProductList.Product product) {
            Glide.with(productImage.getContext())
                    .load(IMG_URL + product.getProductImage())
                    .into(productImage);
            productName.setText(product.getProductName());
            productRating.setRating(product.getReviewRating());
            productReviewCount.setText(String.valueOf(product.getReviewCount() + " reviews"));
            productPrice.setText(product.getPrice());
            if (product.getLongDescription() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    productDescription.setText(Html.fromHtml(product.getLongDescription(), Html.FROM_HTML_MODE_LEGACY));
                } else {
                    productDescription.setText(Html.fromHtml(product.getLongDescription()));
                }
            }
        }
    }
}
