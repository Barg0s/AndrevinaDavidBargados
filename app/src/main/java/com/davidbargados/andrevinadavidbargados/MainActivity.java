package com.davidbargados.andrevinadavidbargados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private TextView tv;
    private  int endevina;
    private  String dialog = "Has endevinat el numero!";
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
        tv = findViewById(R.id.historial); // el ID debe coincidir con tu XML

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarNumero();
            }
        });

    }
    private void comprobarNumero(){
        String text = et.getText().toString();
        if (text.isEmpty()){
            Toast.makeText(this, "Has d'escriure un numero per poder endevinar", Toast.LENGTH_SHORT).show();
            return;
        }
        int numEndevinat = Integer.parseInt(text);
        Log.i("INFO","El número a endevinar és: " + endevina);
        if (numEndevinat == endevina){
            afegirHistorial(numEndevinat);
            mostrarDialog();

        } if (numEndevinat < endevina) {
            Toast.makeText(this, "El numero a endevinar es major que el teu", Toast.LENGTH_SHORT).show();
            et.getText().clear(); //https://stackoverflow.com/questions/5308200/clear-text-in-edittext-when-entered
            afegirHistorial(numEndevinat);

        } if (numEndevinat > endevina) {
            Toast.makeText(this, "El numero a endevinar es mes petit que el teu", Toast.LENGTH_SHORT).show();
            et.getText().clear();
            afegirHistorial(numEndevinat);

        }


    }


    private void mostrarDialog() { //https://developer.android.com/develop/ui/views/components/dialogs?hl=es-419#java
        Random r = new Random();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GUANYADOR!");
        builder.setMessage("Has endevinat el numero!");
        builder.setPositiveButton("Tornar a jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endevina = r.nextInt(100);
                et.getText().clear();
            }
        });
        builder.show();
        ClearHistorial();

    }


    private void afegirHistorial(int numIntroduit){
        tv.append(String.valueOf(numIntroduit) + ",");

    }
    private void ClearHistorial(){
        tv.setText("");

    }


}