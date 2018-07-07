package com.example.priyankadesai.walmarttestapp.network;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.priyankadesai.walmarttestapp.model.ProductList;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RemoteDataSource {

    private final static String URL = "https://mobile-tha-server.appspot.com" + "/walmartproducts/1/30";
    private final OkHttpClient mOkHttpClient;
    private final Moshi mMoshi;

    public RemoteDataSource() {
        mOkHttpClient = new OkHttpClient();
        mMoshi = new Moshi.Builder().build();
    }

    /**
     * Makes an asynchronous api call using the {@link OkHttpClient} and posts the live data
     * changes onto the main thread
     *
     * @param mutableLiveData Live data to be monitored
     */
    public void getData(final MutableLiveData<List<ProductList.Product>> mutableLiveData) {
        Request request = new Request.Builder()
                .url(URL)
                .build();

        // Asynchronous call
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.err.println("Request failed");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        System.err.println("Request failed with status code: " + response.code());
                        return;
                    }

                    ResponseBody body = response.body();
                    if (body != null) {
                        ProductList productList = jsonToItem(body.string());
                        if (productList != null) {
                            mutableLiveData.postValue(productList.getProducts());
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Request failed with with IO error");
                    e.printStackTrace();
                }
            }
        });
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
