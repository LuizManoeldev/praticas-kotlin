package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.RetrofitClient
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    var scope = rememberCoroutineScope()
    var usuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    var idInput by remember { mutableStateOf(TextFieldValue("")) } // Campo de texto para ID

    Column(modifier = modifier) {
        Text(text = "Tela Principal")

        LaunchedEffect(Unit) {
            scope.launch {
                usuarios = getUsuarios() // Carregar usuários ao iniciar a tela
            }
        }

        // Campo de entrada para o ID
        OutlinedTextField(
            value = idInput,
            onValueChange = { idInput = it },
            label = { Text("Digite o ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Botão para carregar usuários manualmente
        Button(onClick = {
            scope.launch {
                usuarios = getUsuarios()
            }
        }) {
            Text("Carregar Usuários")
        }

        // Botão para inserir um novo usuário com ID incrementado
        Button(onClick = {
            inserirNovoUsuario(scope, usuarios) { updatedUsuarios ->
                usuarios = updatedUsuarios
            }
        }) {
            Text("Inserir Usuário")
        }

        // Botão para remover um usuário
        Button(onClick = {
            scope.launch {
                val usuarioId = idInput.text
                RetrofitClient.usuarioService.remover(usuarioId)
                usuarios = getUsuarios() // Atualizar lista de usuários
            }
        }) {
            Text("Remover Usuário")
        }

        // Botão para buscar um usuário por ID
        Button(onClick = {
            scope.launch {
                val usuarioId = idInput.text
                val usuario = RetrofitClient.usuarioService.buscarPorId(usuarioId)
                usuarios = listOf(usuario) // Exibir apenas o usuário buscado
            }
        }) {
            Text("Buscar por ID")
        }

        // Botão de logoff
        Button(onClick = { onLogoffClick() }) {
            Text("Sair")
        }

        // Exibição da lista de usuários em LazyColumn com layout aprimorado
        LazyColumn {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nome: ${usuario.nome}")
                        Text(text = "ID: ${usuario.id}")
                        Text(text = "Senha: ${usuario.senha}")
                    }
                }
            }
        }
    }
}

suspend fun getUsuarios(): List<Usuario> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.listar()
    }
}

// Função para inserir um novo usuário com id incrementado
fun inserirNovoUsuario(
    scope: CoroutineScope,
    usuarios: List<Usuario>,
    onUpdate: (List<Usuario>) -> Unit
) {
    scope.launch {
        try {
            // Buscar a lista de usuários para encontrar o último id
            val usuariosAtualizados = getUsuarios()
            val ultimoId = usuariosAtualizados.maxOfOrNull { it.id.toIntOrNull() ?: 0 } ?: 0 // Pega o último ID e trata caso seja null
            val novoId = ultimoId + 1 // Incrementa o ID

            // Criar novo usuário com o ID incrementado
            val novoUsuario = Usuario(id = novoId.toString(), nome = "novoUsuario", senha = "senha123")

            // Inserir novo usuário no servidor
            RetrofitClient.usuarioService.inserir(novoUsuario)

            // Atualizar a lista de usuários na UI
            onUpdate(getUsuarios())

        } catch (e: Exception) {
            e.printStackTrace() // Tratamento básico de erro
        }
    }
}
