package com.example.linseb325.shuntingyard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Converter c = new Converter("3+4*2/(1-5)^2^3");
        System.out.println(c.convert());
    }

    public void processButtonClicked(View v)
    {
        String input = ((EditText)this.findViewById(R.id.inputEditText)).getText().toString();
        Converter cvt = new Converter(input);
        String output = cvt.convert();
        ((TextView)this.findViewById(R.id.outputTextView)).setText(output);

    }

}
