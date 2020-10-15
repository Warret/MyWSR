package com.example.wsr_tren;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTest ;
    private  TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ActionBar actionBar = getSupportActionBar();
       if (actionBar != null){
           actionBar.hide();
       }
       textViewTest = findViewById(R.id.textViewTest);
       txt = findViewById(R.id.textViewDollar);
       parseXML();
    }

    private  void parseXML (){
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("data.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is,null);

            processParsing(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Kurs> players = new ArrayList<>();
        int eventType = parser.getEventType();
        Kurs currentKurs = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String eltName = null;
            switch (eventType){
                case XmlPullParser.START_TAG :
                    eltName = parser.getName();

                    if ("Name".equals(eltName)){
                        currentKurs = new Kurs();

                    }else if (currentKurs != null){
                        if ("error".equals(eltName)){
                            currentKurs.name = parser.nextText();
                            players.add(currentKurs);
                        }else if ("age".equals(eltName)){
                            currentKurs.NumCode = parser.nextText();
                        }else if ("position".equals(eltName)){
                            currentKurs.Value = parser.nextText();
                        }
                    }
                    break;

            }
            eventType = parser.next();
        }
        printPlayers(players);

    }
    private void printPlayers(ArrayList<Kurs> kurs){
        StringBuilder builder =new StringBuilder();

        for (Kurs kurs1 : kurs){
            builder.append(kurs1.name).append("\n").
                    append(kurs1.NumCode).append("\n").
                    append(kurs1.Value).append("\n\n");
        }
       txt.setText(builder.toString());
    }





    public void onClickOtdBank(View view) {
        Intent intent = new Intent(this, OtdelAndBankActivity.class);
        startActivity(intent);
//        finish();

    }
    public void onClickKurs(View view) {
        Intent intent = new Intent(this, KursActivity.class);
        startActivity(intent);
    }
        //диалоговое окно
        private void myCustomDialog () {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.my_dialog, null);
            builder.setView(cl);


            builder.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    AlertDialog alertDialog = (AlertDialog) dialog;
                    EditText editText = alertDialog.findViewById(R.id.editTextTextPersonLogin);
                    assert editText != null;
                    textViewTest.setText(editText.getText().toString());
                    alertDialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }





    public void onClickLogin(View view) {
        myCustomDialog();

    }
}