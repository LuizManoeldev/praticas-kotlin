package br.edu.ifpb.pdm.classesobjetos
/** SISTEMA DE REGISTROS DE FUNCIONÁRIOS
 *
 * Desenvolva um sistema de registro de funcionários para uma empresa.
 * O sistema deverá permitir registrar diferentes tipos de funcionários e apresentar suas informações.
 *
 * Alguns passos abaixo:
 *
 * 1.Crie uma classe base chamada Funcionario com as seguintes propriedades:
 * - Nome (string): o nome do funcionário.
 * - Idade (int): a idade do funcionário.
 */
open class Funcionario( var nome: String, var idade: Int){
    init{
        println("Um novo funcionario foi adicionado -> Nome: $nome - Idade: $idade")
    }
    open fun apresentacao(){
        println("Olá! Me chamo $nome e tenho $idade anos!")
    }
}
class Gerente(nome: String, idade: Int, var setor: String) :Funcionario(nome, idade){
    override fun apresentacao(){
        println("Olá! Me chamo $nome e tenho $idade anos! Sou do setor $setor")
    }
}
class Desenvolvedor(nome: String, idade: Int, var linguagem: String) :Funcionario(nome, idade){
    override fun apresentacao(){
        println("Olá! Me chamo $nome e tenho $idade anos! Sou desenvolvedor $linguagem")
    }
}
class Analista(nome: String, idade: Int, var area: String) :Funcionario(nome, idade){
    override fun apresentacao(){
        println("Olá! Me chamo $nome e tenho $idade anos! Sou Analista $area")
    }
}
/**
 * 5. Imprima uma mensagem informando que um novo funcionário foi registrado, juntamente com o nome e a idade do
 * funcionário. (Lembre-se do init)
 */

/**
 * 6. Crie um método na classe Funcionario chamado Apresentar que imprima uma mensagem de apresentação do funcionário,
 * incluindo o nome e a idade.
 *
 * 7. Crie uma lista de funcionários e adicione diferentes tipos de funcionários (gerentes, desenvolvedores e analistas)
 * à lista.
 *
 *
 *
 *
 * 8. Utilize o typecast (is e as) para verificar o tipo de cada funcionário na lista e chamar o método Apresentar
 * correspondente.*/

fun main(){
    val listaFuncionarios = listOf(
        Funcionario("Andre", 23),
        Gerente("Tomas", 27, "RH"),
        Desenvolvedor("Caio", 19, "Kotlin"),
        Analista("Carla", 37, "Analise de Dados")
    )
    for ( funcionario in listaFuncionarios){
        funcionario.apresentacao()
    }
}
