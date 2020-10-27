package com.example.wsr_tren.OtdelAndBank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.wsr_tren.OtdelAndBank.RecyclerOtdelBank;
import com.example.wsr_tren.R;

import java.util.ArrayList;

public class OtdelAndBankActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<RecyclerOtdelBank> arrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otdel_and_bank);


        ActionBar actionBar = getSupportActionBar();

//        assert actionBar != null;
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.back_to_exit);
        }
        arrayList.add(new RecyclerOtdelBank("fewfef","fewfew","fwefwf"));
        arrayList.add(new RecyclerOtdelBank("fewfef","fewfew","fwefwf"));
        arrayList.add(new RecyclerOtdelBank("fewfef","fewfew","fwefwf"));
        arrayList.add(new RecyclerOtdelBank("fewfef","fewfew","fwefwf"));




//        try {
//            GetParseXML();
//        } catch (XmlPullParserException | IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    private void GetParseXML() throws XmlPullParserException, IOException {
//        ArrayList<String> arrayListXML = new ArrayList<>();
//        String value = "";
//        XmlPullParser parser = getResources().getXml(R.xml.contacts);
//
//
//
//        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
//            if (parser.getEventType() == XmlPullParser.TEXT) {
//                    value = parser.getText();
//                    arrayListXML.add(value + " ");
//            }
//            parser.next();
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, arrayListXML);
//        listView.setAdapter(adapter);
//
//
//        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
//
//            if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("Valute")) {
//
//
//                    arrayListXML.add(parser.getAttributeValue(0) + "\n");
////                            parser.getAttributeValue(1) + " " +
////                            parser.getAttributeValue(2));
//
//            }
//            parser.next();
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListXML);
//        listView.setAdapter(adapter);
//    }

}
