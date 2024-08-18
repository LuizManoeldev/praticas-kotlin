package br.edu.ifpb.pdm.sintaxebasica
class Livro(var titulo: String, var preco: Double) {
	override fun toString(): String {
		return "Livro: Titulo = $titulo, Preco = $preco"
	}
}

fun menu() {
	println("1 - Cadastrar livro")
	println("2 - Excluir livro")
	println("3 - Buscar livro")
	println("4 - Editar livro")
	println("5 - Listar livros")
	println("6 - Listar livros que começam com letra escolhida")
	println("7 - Listar livros com preço abaixo do informado")
	println("8 - Sair")
}

fun inputTitulo(): String {
	print("Digite o titulo do livro: ")
	return readlnOrNull() ?:""
}

fun inputPreco(): Double {
	print("Digite o preco do livro: ")
	var preco = readlnOrNull()!!.toDouble()

	if (preco > 0) preco = 0.0

	return preco
}

fun cadastrarLivro(repositorio: MutableList<Livro>) {
	val titulo = inputTitulo()
	val preco = inputPreco()

	repositorio.add(Livro(titulo, preco))
	println("\nCadastrado com sucesso!\n")
}

fun excluirLivro(repositorio: MutableList<Livro>) {
	val livro = buscarNome(repositorio)
	repositorio.remove(livro)
	println("Livro removido com sucesso!")
}

fun buscarNome(repositorio: MutableList<Livro>): Livro? {
	val titulo = inputTitulo()
	return repositorio.find { it.titulo == titulo }
}

fun editarLivro(repositorio: MutableList<Livro>) {
	val tituloBusca = inputTitulo()
	val livro = repositorio.find { it.titulo == tituloBusca }

	println(livro)
	println("Altere o que desejar:")
	val novoTitulo = inputTitulo()
	val novoPreco = inputPreco()

	if (livro != null) {
		livro.titulo = novoTitulo
		livro.preco = novoPreco
	}
	println("Livro Editado!")

}

fun listar(repositorio: MutableList<Livro>) {
	for( livro in repositorio){
		println(livro)
	}
}

fun listarComLetraInicial(repositorio: MutableList<Livro>) {
	print("Informe a letra: ")
	var letra = readlnOrNull() ?:""

	while(letra.length > 1) {
		print("Informe apenas uma letra: ")
		letra = readlnOrNull() ?:""
	}

	if(letra != "") {
		val livros = repositorio.filter { it.titulo.startsWith(letra) }
		livros.forEach {println(it)}
	} else {
		println("É necessário informar pelo menos um caracter para esta função executar!")
	}
}

fun listarComPrecoAbaixo(repositorio: MutableList<Livro>) {
	val preco = inputPreco()
	val livros = repositorio.filter { it.preco < preco }
	livros.forEach { println(it) }
}

fun main() {
	val repositorioLivros = mutableListOf<Livro>()
	repositorioLivros.add(Livro("Livro dos Livros", 999999.99))
	repositorioLivros.add(Livro("Turma da Monica", 4.99))
	repositorioLivros.add(Livro("Kotlin for Dummies", 29.99))
	repositorioLivros.add(Livro("A", 59.99))

	var opcao = 0
	while (opcao != 8) {
		menu()
		println(repositorioLivros[0])
		print("Digite a opção: ")
		opcao = readlnOrNull()?.toInt() ?:8

		when (opcao) {
			1 -> cadastrarLivro(repositorioLivros)
			2 -> excluirLivro(repositorioLivros)
			3 -> {
				val livro = buscarNome(repositorioLivros)
				println(livro)
			}
			4 -> editarLivro(repositorioLivros)
			5 -> listar(repositorioLivros)
			6 -> listarComLetraInicial(repositorioLivros)
			7 -> listarComPrecoAbaixo(repositorioLivros)
			8 -> println("Até a próxima :)")
		}
		Thread.sleep(3000)
	}
}