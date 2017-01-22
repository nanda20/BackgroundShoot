package net.simplifiedcoding.navigationdrawerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GridViewList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    public static ArrayList<DataPojo> arrayList=new ArrayList<>();
    private GridViewListAdapter adapter;
    private String URL="";
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        String input=getIntent().getStringExtra("category");
        if(getIntent().getStringExtra("page").equals("1")){
            category=input.replace("/","+");
        }else{
            category =getIntent().getStringExtra("category");
        }
        Log.d("Categori",category);

        String categoryTitle =input.replace("/"," ");
        setTitle(categoryTitle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView =(RecyclerView) findViewById(R.id.reycleViewContainer);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);


        loadDatabase();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadDatabase(){

        final AsyncHttpClient client= new AsyncHttpClient();
        arrayList.clear();
        URL="https://pixabay.com/api/?key=3899745-31636397f9d3f0943d1dbdef5&q="+category+"&image_type=photo&pretty=true&per_page=50";
        client.post(URL,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    JSONArray jsonArray= response.getJSONArray("hits");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object= jsonArray.getJSONObject(i);
                        DataPojo data= new DataPojo(Integer.valueOf(object.getString("likes")),Integer.valueOf(object.getString("favorites")),object.getString("previewURL"),object.getString("webformatURL"));
                        arrayList.add(data);
                    }
                    adapter = new GridViewListAdapter(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }




}
