package com.vibhu.nitJsr.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIActivity extends AppCompatActivity {

    private final String BASE_URL="https://hotels4.p.rapidapi.com/locations/v2/search?query=new%20york&locale=en_US&currency=USD";
    private final String H1="x-rapidapi-host";
    private final String HV1="hotels4.p.rapidapi.com";
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
//        try {
//            Response response = client.newCall(request).execute();
//            Log.d("####",response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("####","call failed "+e);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.d("####","Okay Good! The Response is successful");
                    Log.d("####",response.body().string());
                    //todo: Show the response in well fashioned manner

                }
                else{
                    Log.d("####","Response wasn't successful");
                }
            }
        });
    }
}