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
    private TextView textViewTest = null;
    private ArrayAdapter<String> list = null;

    public DownloadJsonTask(TextView textViewTest, ArrayAdapter<String> list, List<String> data) {
        super();
        this.textViewTest = textViewTest;
        this.list = list;
        this.data = data;
    }

    @Override
    protected ArrayAdapter<String> doInBackground(String... strings) {
        Log.d("com.example.wsr_tren", "DownloadJsonTask: started");
        XmlPullParser parser = null;

        try (
                //https://www.cbr-xml-daily.ru/daily_json.js
                InputStream in = downloadUrl(strings[0]);
                InputStreamReader isr = new InputStreamReader(in);
                JsonReader jr = new JsonReader(new InputStreamReader(in));
        ) {
            jr.beginObject();
            while (jr.hasNext()) {
                String name = jr.nextName();
                if ("Valute".equals(name)) {
                    jr.beginObject();
                    while (jr.hasNext()) {
                        String val = jr.nextName();
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
                        Log.d("com.example.wsr_tren", String.format("DownloadJsonTask: %s - %s (%s)", valName, valPrice, val));
                        jr.endObject();
                    }
                    jr.endObject();
                } else {
                    jr.skipValue();
                }


            }
            jr.endObject();
        } catch (IOException e) {
            Log.e("com.example.wsr_tren", "DownloadJsonTask: fail");
            Log.e("com.example.wsr_tren", "DownloadJsonTask: ", e);
        }
        Log.d("com.example.wsr_tren", "DownloadJsonTask: finished");
        return list;
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    //https://developer.android.com/training/basics/network-ops/xml?hl=ru#java
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
