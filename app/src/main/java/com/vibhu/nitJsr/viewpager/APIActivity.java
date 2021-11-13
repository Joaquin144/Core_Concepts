package com.vibhu.nitJsr.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vibhu.nitJsr.viewpager.adapter.HotelsAPIAdapter;
import com.vibhu.nitJsr.viewpager.models.HotelsApiModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
    private TextView cityNameTV,groupTV;
    private ArrayList<HotelsApiModel> hotelList;
    private HotelsAPIAdapter adapter;
    private RecyclerView APIRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_i);cityNameTV=findViewById(R.id.cityNameTV);
        hotelList=new ArrayList<>();APIRV=findViewById(R.id.APIRV);
        /*
        HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
         */
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_URL).get().addHeader(H1,HV1).addHeader(H2,HV2).build();
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
                    String input = response.body().string();
                    Log.d("####",input);
                    //todo: Show the response in well fashioned manner
                    try {
                        JSONObject rootObj = new JSONObject(input);
//                        String whichCity = rootObj.getString("term");
//                        Log.d("####","whichCity = "+whichCity);
//                        cityNameTV.setText(whichCity);
                        JSONArray suggestionsArray = rootObj.getJSONArray("suggestions");
                        //we will iterate the suggestionsArray and inflate each element's relevant description
                          Log.d("####","suggestions array's size is = "+suggestionsArray.length());
//                        JSONObject obj0 = suggestionsArray.getJSONObject(0);
//                        Log.d("####","obj0  -->  "+obj0.toString());
//
//                        String group =(String) obj0.get("group");
//                        Log.d("####",group);
//                        //****** groupTV.setText(group);   ---->  we can't set elements to group that were created on other thread
//                        JSONArray entitiesArray=obj0.getJSONArray("entities");
//                        Log.d("####","entities array's size is = "+entitiesArray.length());
                        for(int i=0;i<suggestionsArray.length();i++){
                            JSONObject obj = suggestionsArray.getJSONObject(i);
                            Log.i("####","SuggestionArray at "+i+" is:--\n"+obj.toString());
                            String s1=(String) obj.get("group");
                            JSONArray entitiesArray = obj.getJSONArray("entities");
                            //we will show random hotels for a group
                            int maxSize = entitiesArray.length();
                            int takeRandomEntity =(int) (Math.random()*(maxSize-0+1)+0);
                            JSONObject hotel = entitiesArray.getJSONObject(0);
                            String geoId =(String) hotel.get("geoId");
                            String latitude =hotel.get("latitude").toString();
                            String longitude =hotel.get("longitude").toString();
                            String name =(String) hotel.get("name");
                            Log.i("###$",name);
                            hotelList.add(new HotelsApiModel(s1,geoId,longitude,latitude,name));
                            setData(hotelList);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    Log.d("####","Response wasn't successful");
                }
            }
        });

        hotelList.clear();//to avoid duplicating of elements
    }
    void setData(ArrayList<HotelsApiModel> hotelList){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("####","size of hotel list is  = "+hotelList.size());
                adapter=new HotelsAPIAdapter(hotelList);
                LinearLayoutManager manager = new LinearLayoutManager(APIActivity.this);
                manager.setOrientation(RecyclerView.VERTICAL);
                APIRV.setLayoutManager(manager);
                APIRV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });



    }
}