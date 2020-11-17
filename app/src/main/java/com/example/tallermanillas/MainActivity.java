package com.example.tallermanillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String mat[], pend[], type_pend[], cur[];
    private static int conversion = 3200;

    private EditText amount;
    private TextView result;
    private Spinner materialOpts, pendantOpts, type_pendantOpts, currencyOpts;

    private ArrayAdapter<String> adpMaterial, adpPendant, adpType_pendant, adpCurrency;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.txtAmount);
        materialOpts = findViewById(R.id.cmbMaterial);
        pendantOpts = findViewById(R.id.cmbPendant);
        type_pendantOpts = findViewById(R.id.cmbTypeP);
        currencyOpts = findViewById(R.id.cmbCurrency);
        result = findViewById(R.id.lblResult);

        mat = getResources().getStringArray(R.array.mat_opts);
        pend = getResources().getStringArray(R.array.pend_opts);
        type_pend = getResources().getStringArray(R.array.type_pend_opts);
        cur = getResources().getStringArray(R.array.cur_opts);

        adpMaterial = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mat);
        adpPendant = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pend);
        adpType_pendant = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_pend);
        adpCurrency = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cur);

        materialOpts.setAdapter(adpMaterial);
        pendantOpts.setAdapter(adpPendant);
        type_pendantOpts.setAdapter(adpType_pendant);
        currencyOpts.setAdapter(adpCurrency);

    }

    public void quote(View v){
        int material, pendant, type, currency, value, quote;

        material =  materialOpts.getSelectedItemPosition();
        pendant = pendantOpts.getSelectedItemPosition();
        type = type_pendantOpts.getSelectedItemPosition();
        currency = currencyOpts.getSelectedItemPosition();
        value = Integer.valueOf(amount.getText().toString());

        quote = quote(material, pendant, type, value);

        if (currency == 1){
            quote = quote*conversion;
        }
        df = new DecimalFormat("#,###.##");
        result.setText("$"+df.format(quote));
    }

    public int quote(int m, int p, int t, int v) {
        if(m == 1 && p == 1 && t == 1){
            return (quoteByMaterial(m)+quoteByPendant(p)
                    +quoteByTypeOfPendant(t)-10)*v;
        } else {
            return (quoteByMaterial(m) + quoteByPendant(p)
                    + quoteByTypeOfPendant(t)) * v;
        }

    }

    public int quoteByMaterial(int m){
        int val = 0;
        switch (m){
            //Cuero - Leather
            case 0:
                val = 10;
                break;
            //Cuerda - Rope
            case 1:
                val = 0;
                break;
        }
        return val;
    }

    public int quoteByPendant(int p){
        int val = 0;
        switch (p){
            //Ancla - Anchor
            case 0:
                val = 20;
                break;
            //Martillo - Hammer
            case 1:
                val = 0;
                break;
        }
        return val;
    }

    public int quoteByTypeOfPendant(int t){
        int val = 0;
        //Oro, Oro rosa - Gold, Pink Gold
        if (t == 0 || t == 2){
            val = 90;
        } //Niquel - Nickel
        else if (t == 1){
            val = 60;
        }//Plata - Silver
        else if(t == 3){
            val = 70;
        }
        return val;
    }

    public void reset(View v){
        materialOpts.setSelection(0);
        pendantOpts.setSelection(0);
        type_pendantOpts.setSelection(0);
        currencyOpts.setSelection(0);
        amount.setText("");
        result.setText("");
        amount.requestFocus();
    }

}