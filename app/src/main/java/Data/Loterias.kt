package com.example.examenjl_rf.Data

class Loterias {

    object Loterias {
        fun obtenerLoterias(): List<Loteria> {
            return listOf(
                Loteria("Euromillón", 120),
                Loteria("Lotería Nacional", 50),
                Loteria("La Primitiva", 80),

            )

        }
    }

}