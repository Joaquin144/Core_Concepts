package com.vibhu.nitJsr.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIActivity extends AppCompatActivity {

    private final String BASE_URL="https://hotels-com-provider.p.rapidapi.com/v1/hotels/photos?hotel_id=363464";
    private final String H1="x-rapidapi-host";
    private final String HV1="hotels-com-provider.p.rapidapi.com";
    private final String H2="x-rapidapi-key";
    private final String HV2="8c61910c32mshdde4cbc8a929422p150fdbjsn10db04250882";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_i);

        /*
        HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
         */
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_URL).get().addHeader(H1,HV1).addHeader(H2,HV2).build();
        try {
            Response response = client.newCall(request).execute();
            Log.d("####",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}