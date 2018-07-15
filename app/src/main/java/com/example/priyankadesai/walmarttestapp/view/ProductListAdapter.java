package com.example.priyankadesai.walmarttestapp.view;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.priyankadesai.walmarttestapp.R;
import com.example.priyankadesai.walmarttestapp.model.ProductList;

public class ProductListAdapter extends PagedListAdapter<ProductList.Product, ProductListAdapter.ViewHolder> {

    private final static String IMG_URL = "https://mobile-tha-server.appspot.com";
    private static final DiffUtil.ItemCallback<ProductList.Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<ProductList.Product>() {

        @Override
        public boolean areItemsTheSame(ProductList.Product oldItem, ProductList.Product newItem) {
            // Compare products by id
            return oldItem.getProductId().equals(newItem.getProductId());
        }

        @Override
        public boolean areContentsTheSame(ProductList.Product oldItem, ProductList.Product newItem) {
            // Compare all product fields
            return oldItem.equals(newItem);
        }
    };
    private ProductClickListener mProductClickListener;

    ProductListAdapter(ProductClickListener productClickListener) {
        super(DIFF_CALLBACK);
        mProductClickListener = productClickListener;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.list_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductListAdapter.ViewHolder holder, int position) {
        final ProductList.Product product = getItem(position);
        if (product != null) {
            holder.bind(product);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProductClickListener.onProductClicked(holder.getAdapterPosition(), getCurrentList());
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImage;
        private final TextView productName;
        private final TextView productRating;
        private final TextView productPrice;

        ViewHolder(final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productRating = itemView.findViewById(R.id.product_rating);
            productPrice = itemView.findViewById(R.id.product_price);
        }

        void bind(final ProductList.Product product) {
            Glide.with(productImage.getContext())
                    .load(IMG_URL + product.getProductImage())
                    .into(productImage);
            productName.setText(product.getProductName());
            productRating.setText(String.valueOf(product.getReviewRating()));
            productPrice.setText(product.getPrice());
        }
    }
}
