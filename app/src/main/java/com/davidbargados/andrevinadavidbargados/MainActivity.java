package com.davidbargados.andrevinadavidbargados;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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
        int endevina =  r.nextInt(0,100);
        EditText et = (EditText) findViewById(R.id.endrevinar);
        int numEndevninat = Integer.parseInt(et.getText().toString());
        Toast.makeText(this, numEndevninat, Toast.LENGTH_SHORT).show();

        if (numEndevninat == endevina){
            Toast.makeText(this, "Has endevinat el numero!", Toast.LENGTH_SHORT).show();

        } else if (numEndevninat < endevina) {
            Toast.makeText(this, "El numero a endevinar es major que el teu", Toast.LENGTH_SHORT).show();

            
        } else if (numEndevninat > endevina) {
            Toast.makeText(this, "El numero a endevinar es mes petit que el teu", Toast.LENGTH_SHORT).show();
            
        }
    }

}