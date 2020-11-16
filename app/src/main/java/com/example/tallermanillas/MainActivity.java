package com.example.tallermanillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String mat[], pend[], type_pend[], cur[];
    private String value, result;
    private int conversion;
    private EditText amount;
    private Spinner materialOpts, pendantOpts, type_pendantOpts, currencyOpts;
    private ArrayAdapter<String> adpMaterial, adpPendant, adpType_pendant, adpCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.txtAmount);
        materialOpts = findViewById(R.id.cmbMaterial);
        pendantOpts = findViewById(R.id.cmbPendant);
        type_pendantOpts = findViewById(R.id.cmbTypeP);
        currencyOpts = findViewById(R.id.cmbCurrency);

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
}