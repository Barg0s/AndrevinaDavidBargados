package com.davidbargados.andrevinadavidbargados

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HallOfFame : AppCompatActivity() {
    private lateinit var tl: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_of_fame)

        tl = findViewById(R.id.taulaRankings)

        val ranking = MainActivity.ranking

        val headerRow = TableRow(this)
        val headerNom = TextView(this)
        val headerPunts = TextView(this)

        headerNom.text = "Nom"
        headerPunts.text = "Intents"

        headerNom.setPadding(24, 16, 24, 16)
        headerPunts.setPadding(24, 16, 24, 16)

        headerRow.addView(headerNom)
        headerRow.addView(headerPunts)
        tl.addView(headerRow)

        // Rellenamos las filas del ranking
        for (jugador in ranking) {
            val fila = TableRow(this)

            val tvNom = TextView(this)
            val tvPunts = TextView(this)

            tvNom.text = jugador.nom
            tvPunts.text = jugador.puntuacio.toString()

            tvNom.setPadding(24, 16, 24, 16)
            tvPunts.setPadding(24, 16, 24, 16)

            fila.addView(tvNom)
            fila.addView(tvPunts)

            tl.addView(fila)
        }

        val btnTornar = findViewById<Button>(R.id.btnTornar)
        btnTornar.setOnClickListener {
            finish()
        }
    }
}
