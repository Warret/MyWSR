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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTest ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ActionBar actionBar = getSupportActionBar();
       if (actionBar != null){
           actionBar.hide();
       }
       textViewTest = findViewById(R.id.textViewTest);
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