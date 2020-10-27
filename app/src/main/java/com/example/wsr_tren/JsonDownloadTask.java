package com.example.wsr_tren;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonDownloadTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (line != null){
                stringBuilder.append(line);
                line = reader.readLine();
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }

        return null;
    }




    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            JSONObject jsonObject = new JSONObject(s);

            JSONObject valutUSD = jsonObject.getJSONObject("Valute").getJSONObject("USD");
            JSONObject valutEUR = jsonObject.getJSONObject("Valute").getJSONObject("EUR");
            String CharCode = valutUSD.getString("CharCode");
            String Name = valutUSD.getString("Name");

            String valueUSD = valutUSD.getString("Value");
            String valueEUR = valutUSD.getString("Value");
            String date = jsonObject.getString("PreviousDate");

            String resultUSD = "USD " + valueUSD;
            String resultEUR = "EUR " + valueEUR;
            date = date.substring(0,10);



            MainActivity.textViewUSD.setText(resultUSD);
            MainActivity.textViewEUR.setText(resultEUR);
            MainActivity.textViewDate.setText(date);


            Log.i("myres", resultUSD);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {

        }


    }
}
