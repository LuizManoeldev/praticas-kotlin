package com.example.diadasemana.model

import java.util.*

class Calendario {
    private var data: Calendar = Calendar.getInstance();

    fun diaDaSemana(): String {
        val dias =
            arrayListOf("DOMINGO", "SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA", "SABADO");

        return dias[this.data.get(Calendar.DAY_OF_WEEK_IN_MONTH)];
    }
}