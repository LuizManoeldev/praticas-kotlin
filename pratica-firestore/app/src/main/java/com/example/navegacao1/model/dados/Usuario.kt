package com.example.navegacao1.model.dados

import com.google.firebase.firestore.DocumentId

data class Usuario(
    @DocumentId var id: String? = null, // Adicionando a propriedade Id com @DocumentId
    val nome: String = "",
    val senha: String = ""
)
