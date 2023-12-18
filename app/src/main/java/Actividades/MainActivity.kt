package com.example.examenjl_rf.Actividades

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.examenjl_rf.Data.Loterias
import com.example.examenjl_rf.Data.Loteria
import com.example.examenjl_rf.R
import com.example.examenjl_rf.ui.theme.ExamenJLRFTheme

class MainActivity : ComponentActivity() {
    // Declaración de variables
    private lateinit var loteriaListLayout: LinearLayout
    private lateinit var editTextLoteria: EditText
    private lateinit var editTextDineroApostado: EditText
    private lateinit var textViewResultado: TextView
    private var totalJugado = 0
    private var totalGanado = 0
    private var vecesJugadas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenJLRFTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }

        // Inicialización de variables
        loteriaListLayout = findViewById(R.id.lotteryListLayout)
        editTextLoteria = findViewById(R.id.editTextLoteria)
        editTextDineroApostado = findViewById(R.id.editTextDineroApostado)
        textViewResultado = findViewById(R.id.textViewResultado)

        // Agregar dinámicamente las vistas de lotería
        agregarLoterias()

        // Manejar eventos de los botones
        findViewById<Button>(R.id.botonApostar).setOnClickListener { apostar() }
        findViewById<Button>(R.id.botonJugarLoteria).setOnClickListener { jugarLoteriaEscrita() }
        findViewById<Button>(R.id.botonCambiarPantalla).setOnClickListener { cambiarPantalla() }
    }

    @Composable
    fun MainScreen() {
        val loterias = Loterias.Loterias.obtenerLoterias()

        Column {
            LotteryList(loterias = loterias)
        }
    }

    @Composable
    fun LotteryButton(loteria: Loteria) {
        Button(onClick = { apostarEnLoteria(loteria) }) {
            Text(text = "Apostar en ${loteria.nombre}")
        }
    }

    @Composable
    fun LotteryList(loterias: List<Loteria>) {
        Column {
            for (loteria in loterias) {
                LotteryButton(loteria = loteria)
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        ExamenJLRFTheme {
            MainScreen()
        }
    }

    private fun agregarLoterias() {
        // Obtener las loterías desde la clase Loterias (puedes modificarla según tus necesidades)
        val loterias = Loterias.Loterias.obtenerLoterias()

        // Iterar sobre las loterías y agregar vistas dinámicamente
        for (loteria in loterias) {
            val botonApostar = Button(this)
            botonApostar.text = "Apostar en ${loteria.nombre}"
            botonApostar.setOnClickListener { apostarEnLoteria(loteria) }

            loteriaListLayout.addView(botonApostar)
        }
    }

    private fun apostarEnLoteria(loteria: Loteria) {
        val dineroApostadoStr = editTextDineroApostado.text.toString()
        if (dineroApostadoStr.isNotEmpty()) {
            val dineroApostado = dineroApostadoStr.toInt()
            totalJugado += dineroApostado
            vecesJugadas++

            // Simular el resultado de la lotería (1: Gana, 0: Pierde)
            val resultado = (0..1).random()

            if (resultado == 1) {
                val premio = loteria.premio
                val ganancia = premio * dineroApostado
                totalGanado += ganancia
                actualizarResultado("Has ganado la lotería. Jugadas: $vecesJugadas, Total Jugado: $totalJugado, Total Ganado: $totalGanado")
            } else {
                actualizarResultado("Has perdido la lotería. Jugadas: $vecesJugadas, Total Jugado: $totalJugado, Total Ganado: $totalGanado")
            }
        } else {
            actualizarResultado("No se puede comprar una lotería con 0 euros")
        }
    }

    private fun apostar() {
        val dineroApostadoStr = editTextDineroApostado.text.toString()
        if (dineroApostadoStr.isNotEmpty()) {
            val dineroApostado = dineroApostadoStr.toInt()
            totalJugado += dineroApostado
            vecesJugadas++

            // Simular el resultado de la lotería (1: Gana, 0: Pierde)
            val resultado = (0..1).random()

            if (resultado == 1) {
                val premio = Loterias.Loterias.obtenerLoterias().random().premio
                val ganancia = premio * dineroApostado
                totalGanado += ganancia
                actualizarResultado("Has ganado la lotería. Jugadas: $vecesJugadas, Total Jugado: $totalJugado, Total Ganado: $totalGanado")
            } else {
                actualizarResultado("Has perdido la lotería. Jugadas: $vecesJugadas, Total Jugado: $totalJugado, Total Ganado: $totalGanado")
            }
        } else {
            actualizarResultado("No se puede comprar una lotería con 0 euros")
        }
    }

    private fun jugarLoteriaEscrita() {
        val nombreLoteria = editTextLoteria.text.toString()
        val dineroApostadoStr = editTextDineroApostado.text.toString()

        if (dineroApostadoStr.isNotEmpty()) {
            val dineroApostado = dineroApostadoStr.toInt()
            totalJugado += dineroApostado
            vecesJugadas++

            val loteria = Loterias.Loterias.obtenerLoterias().find { it.nombre == nombreLoteria }

            if (loteria != null) {
                // Simular el resultado de la lotería (1: Gana, 0: Pierde)
                val resultado = (0..1).random()

                if (resultado == 1) {
                    val premio = loteria.premio
                    val ganancia = premio * dineroApostado
                    totalGanado += ganancia
                    actualizarResultado("Has ganado la lotería. Jugadas: $vecesJugadas, Total Jugado: $totalJugado, Total Ganado: $totalGanado")
                } else {
                    actualizarResultado("Has perdido la lotería. Jugadas: $vecesJugadas, Total Jugado: $totalJugado, Total Ganado: $totalGanado")
                }
            } else {
                actualizarResultado("No existe ninguna lotería con ese nombre")
            }
        } else {
            actualizarResultado("No se puede comprar una lotería con 0 euros")
        }
    }

    private fun cambiarPantalla() {
        val intent = Intent(this, NuevaPantallaActivity::class.java)
        startActivity(intent)
    }

    private fun actualizarResultado(texto: String) {
        textViewResultado.text = texto
    }
}
