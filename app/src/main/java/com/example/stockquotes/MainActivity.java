package com.example.stockquotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText enter;
    TextView symbol;
    TextView name;
    TextView price;
    TextView time;
    TextView change;
    TextView range;
    Stock stock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enter);
        symbol = findViewById(R.id.getSymbol);
        name = findViewById(R.id.getName);
        price = findViewById(R.id.getLastPrice);
        time = findViewById(R.id.getLastTime);
        change = findViewById(R.id.getChange);
        range = findViewById(R.id.getRange);
        stock = new Stock(enter.getText().toString());

        enter.requestFocus();
        enter.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if((event.getAction()==KeyEvent.ACTION_DOWN)&& (keyCode==KeyEvent.KEYCODE_ENTER)){
                    Retrieve retrieve = new Retrieve(symbol,name,price,time,change,range,getApplicationContext());
                    retrieve.execute(enter.getText().toString());
                }
                return false;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("enter",enter.getText().toString());
        outState.putString("symbol",symbol.getText().toString());
        outState.putString("name",name.getText().toString());
        outState.putString("price",price.getText().toString());
        outState.putString("time",time.getText().toString());
        outState.putString("change",change.getText().toString());
        outState.putString("range",range.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        enter.setText(savedInstanceState.getString("symbolEntry"));
        symbol.setText(savedInstanceState.getString("symbol"));
        name.setText(savedInstanceState.getString("name"));
        price.setText(savedInstanceState.getString("price"));
        time.setText(savedInstanceState.getString("time"));
        change.setText(savedInstanceState.getString("change"));
        range.setText(savedInstanceState.getString("range"));
    }
}
