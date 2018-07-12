package com.example.priyankadesai.walmarttestapp.network;

import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RemoteDataSource {

    private final static String URL = "https://mobile-tha-server.appspot.com" + "/walmartproducts/";
    private final OkHttpClient mOkHttpClient;
    private final Moshi mMoshi;

    public RemoteDataSource() {
        mOkHttpClient = new OkHttpClient();
        mMoshi = new Moshi.Builder()
                .build();
    }

    /**
     * Synchronous network call to get data
     *
     * @param pageNumber start page to get data from
     * @param pageSize   number of pages to get
     * @return {@link ProductList} retrieved data
     */
    public ProductList getData(int pageNumber, int pageSize) {
        Request request = new Request.Builder()
                .url(URL + pageNumber + "/" + pageSize)
                .build();

        // Synchronous call
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.err.println("Request failed with status code: " + response.code());
                return null;
            }

            ResponseBody body = response.body();
            if (body != null) {
                ProductList productList = jsonToItem(body.string());
                if (productList != null) {
                    return productList;
                }
            }

        } catch (IOException e) {
            System.err.println("Error making network call");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse the json response into {@link ProductList} class object
     *
     * @param jsonResponse json string
     * @return {@link ProductList}
     */
    private ProductList jsonToItem(String jsonResponse) {
        JsonAdapter<ProductList> itemJsonAdapter = mMoshi.adapter(ProductList.class);
        try {
            return itemJsonAdapter.fromJson(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
