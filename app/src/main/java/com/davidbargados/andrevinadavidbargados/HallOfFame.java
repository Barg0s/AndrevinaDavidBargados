package com.davidbargados.andrevinadavidbargados;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HallOfFame extends AppCompatActivity {


    private TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hall_of_fame);
        tl = findViewById(R.id.taulaRankings);

        ArrayList<Integer> ranking = MainActivity.ranking;

        for (Integer cnt : ranking){
            TableRow tr = new TableRow(this);
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(cnt));
            tr.addView(tv);
            tl.addView(tr);

        }

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

    }
}