package com.davidbargados.andrevinadavidbargados

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
    private val dialog = "Has endevinat el numero!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Edge-to-edge UI for modern devices (if needed)
        // EdgeToEdge.enable(this);

        val r = Random()
        endevina = r.nextInt(100) // Random number between 0 and 99
        et = findViewById(R.id.endrevinar)
        val button = findViewById<Button>(R.id.button2)
        sw = findViewById(R.id.historial)
        textViewHistorial = findViewById(R.id.textViewHistorial)
        contador = findViewById(R.id.contador)

        val buttonHof = findViewById<Button>(R.id.button_open_halloffame)
        buttonHof.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Open HallOfFame Activity
                val intent = Intent(this@MainActivity, HallOfFame::class.java)
                startActivity(intent)
            }
        })

        button.setOnClickListener {
            comprobarNumero()
        }
    }

    private fun comprobarNumero() {
        val text = et?.text.toString()
        if (text.isEmpty()) {
            Toast.makeText(this, "Has d'escriure un numero per poder endevinar", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val numEndevinat = text.toInt()
        Log.i("INFO", "El número a endevinar és: $endevina")

        if (numEndevinat == endevina) {
            afegirHistorial(numEndevinat)
            contador?.text = "intents: $cnt"
            mostrarDialog()
        } else {
            val message = if (numEndevinat < endevina) {
                "El numero a endevinar es major que el teu"
            } else {
                "El numero a endevinar es mes petit que el teu"
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

        val dialog = Dialog(this)


        val builder = AlertDialog.Builder(this)
        builder.setTitle("CONGRATS!")
        builder.setMessage("Has trigat $cnt intents")
        builder.setPositiveButton("Tornar a jugar") { dialog, which ->
            endevina = r.nextInt(100) // New random number
            et!!.text.clear()
            cnt = 1
            contador!!.text = "intents: $cnt"
            ranking.add(cnt)
        }

        builder.show()

        // Clear the guesses history
        ClearHistorial()
    }

    private fun afegirHistorial(numIntroduit: Int) {
        textViewHistorial?.append("$numIntroduit\n")
    }

    private fun ClearHistorial() {
        textViewHistorial?.text = "" // Clear history (not append empty string)
    }

    companion object {
        const val RANKINGVIEWS = "com.davidbargados.andrevinadavidbargados.HallOfFame"
        @JvmField
        var ranking: ArrayList<Int> = ArrayList()
    }
}
