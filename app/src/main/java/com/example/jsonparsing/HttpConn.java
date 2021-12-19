package com.example.jsonparsing;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpConn extends AsyncTask<String,Void,String> {
    LinearLayout linearLayout;
    Context context;
    public HttpConn(LinearLayout linearLayout, Context context)
    {
        this.linearLayout = linearLayout;
        this.context = context;
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line =bufferedReader.readLine()) !=null)
                stringBuilder.append(line);
            return  stringBuilder.toString();
        }
        catch (IOException ex)
        {
            Log.d("ErrorConn",ex.getMessage());
        }
        return  null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try
        {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            linearLayout.setTag(jsonObject.getInt("total_pages"));
            for(int i = 0;i<dataArray.length();i++)
            {
                JSONObject contactJson = dataArray.getJSONObject(i);
                ContactView contactView = new ContactView(context);
                contactView.setContactName(contactJson.getString("first_name"),contactJson.getString("last_name"));
                contactView.setIdContact(contactJson.getInt("id"));
                contactView.setEmail(contactJson.getString("email"));
                contactView.setImage(contactJson.getString("avatar"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)(100*context.getResources().getDisplayMetrics().density));
                contactView.setLayoutParams(layoutParams);
                linearLayout.addView(contactView);
            }
        }
        catch (JSONException jsonException)
        {
            Log.d("ErrorJson",jsonException.getLocalizedMessage());
        }
    }
}
