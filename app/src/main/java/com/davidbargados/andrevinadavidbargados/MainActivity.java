package com.davidbargados.andrevinadavidbargados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private ScrollView sw;
    private  TextView textViewHistorial,contador;
    private  int endevina;
    static final String RANKINGVIEWS = "com.davidbargados.andrevinadavidbargados.HallOfFame";
    public static ArrayList<Integer> ranking = new ArrayList<>();

    private  int cnt = 1;
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
        sw = findViewById(R.id.historial);
        textViewHistorial = findViewById(R.id.textViewHistorial);
        contador = findViewById(R.id.contador);

        Button buttonHof = findViewById(R.id.button_open_halloffame);

        buttonHof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HallOfFame.class);
                startActivity(intent);
            }
        });



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
            contador.setText("intents:" + cnt);

            mostrarDialog();

        } if (numEndevinat < endevina) {
            Toast.makeText(this, "El numero a endevinar es major que el teu", Toast.LENGTH_SHORT).show();
            et.getText().clear(); //https://stackoverflow.com/questions/5308200/clear-text-in-edittext-when-entered
            afegirHistorial(numEndevinat);
            contador.setText("intents:" + cnt);

            cnt++;

        } if (numEndevinat > endevina) {
            Toast.makeText(this, "El numero a endevinar es mes petit que el teu", Toast.LENGTH_SHORT).show();
            et.getText().clear();
            afegirHistorial(numEndevinat);
            contador.setText("intents:" + cnt);

            cnt++;

        }


    }


    private void mostrarDialog() { //https://developer.android.com/develop/ui/views/components/dialogs?hl=es-419#java
        Random r = new Random();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CONGRATS!");
        builder.setMessage("Has trigat" + cnt + "intents");
        builder.setPositiveButton("Tornar a jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endevina = r.nextInt(100);
                et.getText().clear();
                cnt = 1;
                contador.setText("intents:" + cnt);
                ranking.add((cnt));


            }
        });
        builder.show();
        ClearHistorial();


    }


    private void afegirHistorial(int numIntroduit){
        textViewHistorial.append(String.valueOf(numIntroduit) + "\n");

    }
    private void ClearHistorial(){
        textViewHistorial.setText("");

    }}



