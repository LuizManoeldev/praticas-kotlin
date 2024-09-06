package com.example.pratica_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pratica_retrofit.model.Endereco

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsultaCEPApp()
        }
    }
}

@Composable
fun ConsultaCEPApp() {
    var cepInput by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf<Endereco?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de texto para o CEP
        BasicTextField(
            value = cepInput,
            onValueChange = { cepInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(8.dp)) {
                    if (cepInput.isEmpty()) {
                        Text(text = "Digite o CEP")
                    }
                    innerTextField()
                }
            }
        )

        // Botão de consulta
        Button(
            onClick = {
                if (cepInput.isNotEmpty()) {
                    buscarEndereco(cepInput, onResult = {
                        endereco = it
                        errorMessage = ""
                    }, onError = {
                        errorMessage = "Erro ao buscar o endereço"
                    })
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Consultar")
        }

        // Exibir erro caso ocorra
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, modifier = Modifier.padding(bottom = 8.dp))
        }

        // Listagem de dados do endereço
        endereco?.let {
            LazyColumn {
                item {
                    Text(text = "Logradouro: ${it.logradouro}")
                    Text(text = "Bairro: ${it.bairro}")
                    Text(text = "Localidade: ${it.localidade}")
                    Text(text = "UF: ${it.uf}")
                    Text(text = "CEP: ${it.cep}")
                }
            }
        }
    }
}

fun buscarEndereco(cep: String, onResult: (Endereco?) -> Unit, onError: () -> Unit) {
    RetrofitClient.enderecoService.getEndereco(cep).enqueue(object : Callback<Endereco> {
        override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
            if (response.isSuccessful) {
                onResult(response.body())
            } else {
                onError()
            }
        }

        override fun onFailure(call: Call<Endereco>, t: Throwable) {
            onError()
        }
    })
}
