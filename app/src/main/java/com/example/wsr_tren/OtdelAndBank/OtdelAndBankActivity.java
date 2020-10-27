package com.example.wsr_tren.OtdelAndBank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.wsr_tren.OtdelAndBank.RecyclerOtdelBank;
import com.example.wsr_tren.R;

import java.util.ArrayList;

public class OtdelAndBankActivity extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private  ArrayList<RecyclerOtdelBank> arrayList = new ArrayList<>();

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

       recyclerView = findViewById(R.id.recyclerViewSchedule);
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Не работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Не работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Работает","Время работы: 10:00-19:00"));
        arrayList.add(new RecyclerOtdelBank("Москва ул.Лесная 58","Не работает","Время работы: 10:00-19:00"));



       GraphicAdapter adapter = new GraphicAdapter(arrayList);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setAdapter(adapter);


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
