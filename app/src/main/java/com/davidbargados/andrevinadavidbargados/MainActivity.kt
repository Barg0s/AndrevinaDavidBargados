package com.davidbargados.andrevinadavidbargados

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {
    private var et: EditText? = null
    private var sw: ScrollView? = null
    private var textViewHistorial: TextView? = null
    private var contador: TextView? = null
    private var endevina = 0
    private var cnt = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val r = Random()
        endevina = r.nextInt(100)

        et = findViewById(R.id.endrevinar)
        val button = findViewById<Button>(R.id.button2)
        sw = findViewById(R.id.historial)
        textViewHistorial = findViewById(R.id.textViewHistorial)
        contador = findViewById(R.id.contador)
        Log.d("Numero secret", endevina.toString())
        val buttonHof = findViewById<Button>(R.id.button_open_halloffame)
        buttonHof.setOnClickListener {
            val intent = Intent(this@MainActivity, HallOfFame::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            comprobarNumero()
        }
    }

    private fun comprobarNumero() {
        val text = et?.text.toString()
        if (text.isEmpty()) {
            Toast.makeText(this, "Has d'escriure un número per poder endevinar", Toast.LENGTH_SHORT).show()
            return
        }

        val numEndevinat = text.toInt()

        if (numEndevinat == endevina) {
            afegirHistorial(numEndevinat)
            contador?.text = "intents: $cnt"
            mostrarDialog("Has endevinat el número!")
        } else {
            val message = if (numEndevinat < endevina) {
                "El número a endevinar és major que el teu"
            } else {
                "El número a endevinar és més petit que el teu"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            et?.text?.clear()
            afegirHistorial(numEndevinat)
            contador?.text = "intents: $cnt"
            cnt++
        }
    }

    private fun mostrarDialog(title: String) {
        val r = Random()

        val view = layoutInflater.inflate(R.layout.customdialog, null)
        val dialog = Dialog(this).apply {
            setContentView(view)
            setCancelable(false)
        }

        val userName = view.findViewById<EditText>(R.id.userName)
        val attemptCount = view.findViewById<TextView>(R.id.attemptCount)
        val btnPlayAgain = view.findViewById<Button>(R.id.btnPlayAgain)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        attemptCount.text = "Has trigat $cnt intents"

        btnPlayAgain.setOnClickListener {


            var nomJugador = userName.text.toString()
            if (nomJugador.isEmpty()) {
                nomJugador = "Anonim"
            }


            val jugador = Jugador(nomJugador, cnt)
            ranking.add(jugador)

            endevina = r.nextInt(100)
            et?.text?.clear()
            cnt = 1
            contador?.text = "intents: $cnt"
            ClearHistorial()

            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun afegirHistorial(numIntroduit: Int) {
        textViewHistorial?.append("$numIntroduit\n")
    }

    private fun ClearHistorial() {
        textViewHistorial?.text = ""
    }

    companion object {
        var ranking: ArrayList<Jugador> = ArrayList()
    }
}
