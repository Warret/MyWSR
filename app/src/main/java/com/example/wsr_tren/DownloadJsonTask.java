package com.example.wsr_tren;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadJsonTask extends AsyncTask<String, Void, ArrayAdapter<String>> {

    private final List<String> data;
    private ArrayAdapter<String> adapter = null;

    public DownloadJsonTask(List<String> data, ArrayAdapter<String> list) {
        this.data = data;
        this.adapter = list;
    }

    @Override
    protected ArrayAdapter<String> doInBackground(String... strings) {
        Log.d("1234", "DownloadJsonTask: started");


        try {
            //https://www.cbr-xml-daily.ru/daily_json.js
            InputStream in = downloadUrl(strings[0]);
            InputStreamReader isr = new InputStreamReader(in);
            JsonReader jr = new JsonReader(isr);

            jr.beginObject();
            while (jr.hasNext()) {
                String name = jr.nextName();
                if ("Valute".equals(name)) {
                    jr.beginObject();
                    while (jr.hasNext()) {
                        String val = jr.nextName();
                        Log.i("12345", val);
                        String valName = "";
                        String valPrice = "";
                        jr.beginObject();
                        while (jr.hasNext()) {
                            String key = jr.nextName();
                            String value = jr.nextString();
                            if ("Name".equals(key)) {
                                valName = value;
                            }
                            if ("Value".equals(key)) {
                                valPrice = value;
                            }
                        }
                        data.add(String.format("%s - %s (%s)", valName, valPrice, val));
                        Log.d("1234", String.format("DownloadJsonTask: %s - %s (%s)", valName, valPrice, val));
                        jr.endObject();
                    }
                    jr.endObject();
                } else {
                    jr.skipValue();
                }
            }
            jr.endObject();
        } catch (IOException e) {
            Log.e("1234", "DownloadJsonTask: fail");
            Log.e("1234", "DownloadJsonTask: ", e);
        }
        Log.d("1234", "DownloadJsonTask: finished");
        return adapter;
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    //https://developer.android.com/training/basics /network-ops/xml?hl=ru#java
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }

    @Override
    protected void onPostExecute(ArrayAdapter<String> stringArrayAdapter) {
        super.onPostExecute(stringArrayAdapter);
        stringArrayAdapter.notifyDataSetChanged();
    }
}
