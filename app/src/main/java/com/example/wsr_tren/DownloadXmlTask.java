package com.example.wsr_tren;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadXmlTask extends AsyncTask<String, Void, ArrayAdapter<String>> {

    private final List<String> data;
    private TextView textViewTest = null;
    private ArrayAdapter<String> list = null;

    public DownloadXmlTask(TextView textViewTest, ArrayAdapter<String> adapter, List<String> data) {
        super();
        this.textViewTest = textViewTest;
        this.list = adapter;
        this.data = data;
    }

    @Override
    protected ArrayAdapter<String> doInBackground(String... strings) {
        Log.d("res", "XmlLoader: started");
        XmlPullParser parser = null;

        try (InputStream in = downloadUrl(strings[0])) {
            parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);

            if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("contact")){
                this.textViewTest.setText(parser.getText());
            }

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT){

                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("contact")){

                    data.add(parser.getAttributeValue(0) + " " +
                            parser.getAttributeValue(1) + "\n\t\t\t\t\t\t" +
                            parser.getAttributeValue(2));

                }
                parser.next();

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("com.example.wsr_tren", "XmlLoader: finished");
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
