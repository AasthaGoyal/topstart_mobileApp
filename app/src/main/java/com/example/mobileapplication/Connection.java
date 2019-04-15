package com.example.mobileapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Connection extends AsyncTask<Object, Void, Boolean> {

    Context context;

    public Connection(Context context)
    {
        this.context = context;
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_connection);
//    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        URL url = null;
        try{
            url = new URL("http://10.0.2.2:17815/api/values");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.connect();

            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //   bufferedWriter.write(jsonUser.toString());
            bufferedWriter.close();

            int responseCode = urlConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d("Response from API", stringBuilder.toString());
            if(responseCode == 200){
                return true;
            }else {
                return false;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if(aBoolean){
            Intent loginIntent = new Intent(this.context, BuyActivity.class);
            this.context.startActivity(loginIntent);
        }else{
            Toast.makeText(this.context, "Unable to register. Please check your connection and try again. ", Toast.LENGTH_LONG).show();
        }
    }
}
