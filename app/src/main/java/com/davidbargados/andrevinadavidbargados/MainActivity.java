package com.davidbargados.andrevinadavidbargados;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private  int endevina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Random r = new Random();
        endevina = r.nextInt(0, 100);
        et = (EditText) findViewById(R.id.endrevinar);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarNumero();
            }
        });

    }
    private void comprobarNumero(){
        String text = et.getText().toString();
        int numEndevinat = Integer.parseInt(text);
        Toast.makeText(this, String.valueOf(endevina), Toast.LENGTH_SHORT).show();
        if (numEndevinat == endevina){
            Toast.makeText(this, "Has endevinat el numero!", Toast.LENGTH_SHORT).show();

        } if (numEndevinat < endevina) {
            Toast.makeText(this, "El numero a endevinar es major que el teu", Toast.LENGTH_SHORT).show();


        } if (numEndevinat > endevina) {
            Toast.makeText(this, "El numero a endevinar es mes petit que el teu", Toast.LENGTH_SHORT).show();

        }

    }


}