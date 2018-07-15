package com.example.priyankadesai.walmarttestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class ProductList {
    private List<Product> products;
    private int totalProducts;
    private int pageNumber;
    private int pageSize;
    private int statusCode;

    protected ProductList(Parcel in) {
        totalProducts = in.readInt();
        pageNumber = in.readInt();
        pageSize = in.readInt();
        statusCode = in.readInt();
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                ", totalProducts=" + totalProducts +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", statusCode=" + statusCode +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static class Product implements Parcelable {
        public static final Creator<Product> CREATOR = new Creator<Product>() {
            @Override
            public Product createFromParcel(Parcel in) {
                return new Product(in);
            }

            @Override
            public Product[] newArray(int size) {
                return new Product[size];
            }
        };
        String productId;
        String productImage;
        String price;
        String productName;
        String shortDescription;
        String longDescription;
        float reviewRating;
        int reviewCount;
        boolean inStock;

        protected Product(Parcel in) {
            productId = in.readString();
            productImage = in.readString();
            price = in.readString();
            productName = in.readString();
            shortDescription = in.readString();
            longDescription = in.readString();
            reviewRating = in.readFloat();
            reviewCount = in.readInt();
            inStock = in.readByte() != 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Float.compare(product.reviewRating, reviewRating) == 0 &&
                    reviewCount == product.reviewCount &&
                    inStock == product.inStock &&
                    Objects.equals(productId, product.productId) &&
                    Objects.equals(productImage, product.productImage) &&
                    Objects.equals(price, product.price) &&
                    Objects.equals(productName, product.productName) &&
                    Objects.equals(shortDescription, product.shortDescription) &&
                    Objects.equals(longDescription, product.longDescription);
        }

        @Override
        public int hashCode() {

            return Objects.hash(productId, productImage, price, productName, shortDescription, longDescription, reviewRating, reviewCount, inStock);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "productId='" + productId + '\'' +
                    ", productImage='" + productImage + '\'' +
                    ", price='" + price + '\'' +
                    ", productName='" + productName + '\'' +
                    ", shortDescription='" + shortDescription + '\'' +
                    ", longDescription='" + longDescription + '\'' +
                    ", reviewRating=" + reviewRating +
                    ", reviewCount=" + reviewCount +
                    ", inStock=" + inStock +
                    '}';
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        public float getReviewRating() {
            return reviewRating;
        }

        public void setReviewRating(float reviewRating) {
            this.reviewRating = reviewRating;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }

        public boolean isInStock() {
            return inStock;
        }

        public void setInStock(boolean inStock) {
            this.inStock = inStock;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(productId);
            dest.writeString(productImage);
            dest.writeString(price);
            dest.writeString(productName);
            dest.writeString(shortDescription);
            dest.writeString(longDescription);
            dest.writeFloat(reviewRating);
            dest.writeInt(reviewCount);
            dest.writeByte((byte) (inStock ? 1 : 0));
        }
    }
}
