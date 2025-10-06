package com.davidbargados.andrevinadavidbargados

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HallOfFame : AppCompatActivity() {
    private lateinit var tl: TableLayout // Safe and direct initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_of_fame)

        tl = findViewById(R.id.taulaRankings)

        val ranking = MainActivity.ranking

        // Add each ranking item to the table
        for (cnt in ranking) {
            val tr = TableRow(this).apply {
                // Optionally, you can set properties on the row like padding, layout params, etc.
            }

            val tv = TextView(this).apply {
                text = cnt.toString()
                // You can customize the TextView appearance here
                setPadding(16, 16, 16, 16) // Example padding
            }

            tr.addView(tv)  // Add the TextView to the TableRow
            tl.addView(tr)   // Add the TableRow to the TableLayout
        }

        // If using ViewCompat for insets, uncomment the following code
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        */
    }
}
