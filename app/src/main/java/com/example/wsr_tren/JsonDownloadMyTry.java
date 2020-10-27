package com.example.wsr_tren;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
                                    //ПОТОМ ЭТО ↓
public class JsonDownloadMyTry extends AsyncTask<String,Void, ArrayAdapter<String>> {
    private ArrayAdapter<String> adapter;
    private List<String> list;
    public JsonDownloadMyTry(ArrayAdapter<String> adapter, List<String> list) {
        this.adapter = adapter;
        this.list = list;
    }
    @Override
    protected ArrayAdapter<String> doInBackground(String... strings) {
        try {
            InputStream inputStream = stringURL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            JsonReader jr = new JsonReader(inputStreamReader);
            jr.beginObject();
            while (jr.hasNext()){
                String token = jr.nextName();
                if (token.equals("Valute")){
                    jr.beginObject();
                    while (jr.hasNext()){
                        String val = jr.nextName();
                        String valName="";
                        String valPrice="";
                        jr.beginObject();
                        while (jr.hasNext()){
                            String Key = jr.nextName();
                            String value = jr.nextString();
                            if (Key.equals("Name")){
                                valName = value;
                            }
                            if (Key.equals("Value")){
                                valPrice = value;
                            }
                        }
                        list.add(String.format("%s - %s (%s)",valName,valPrice,val));
                        jr.endObject();
                    }
                    jr.endObject();
                }else {
                    jr.skipValue();
                }
            }
            jr.endObject();

        } catch (IOException e) {
            e.printStackTrace();
        }                                         // ДЖЕЙСОН
        return adapter;
    }
                                    // СНАЧАЛА ДЕЛАЕМ ЭТО ↓
    public InputStream stringURL (String URLstring) throws IOException {
        URL url = new URL(URLstring);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(250);
        connection.setReadTimeout(250);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getInputStream();
    }
    @Override
    protected void onPostExecute(ArrayAdapter<String> stringArrayAdapter) {
        super.onPostExecute(stringArrayAdapter);
        stringArrayAdapter.notifyDataSetChanged();
    }
}
