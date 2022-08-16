package com.example.calculadoracombustivel

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chamada da função de click do botão calcular
        btn_calcular.setOnClickListener{
            calcularPreco()
        }
    }

    // Função responsavel por chamar o metodo de validar campos e chamar o metodo de calcular melhor preço
    // Se os campos não forem preenchidos é apresentado um Alerta para preencher os campos
    fun calcularPreco() {
        val precoAlcool = input_alcool.text.toString()
        val precoGasolina = input_gasolina.text.toString()

        val validaCampos = validarCampos(precoAlcool, precoGasolina)
        if (validaCampos) {
            calculaMelhorPreco(precoAlcool, precoGasolina)
        } else {
            renderAlert("Atenção", "Os campos estão vazios, preecha por favor!","Ok")
        }
    }

    // Metodo responsavel em validar os campos vazios
    fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {
        var camposValidados: Boolean = true
        if(precoAlcool.equals("")) {
            camposValidados = false
        } else if (precoGasolina.equals("")) {
            camposValidados = false
        }

        return camposValidados
    }

    // Metodo que realiza o calculo para saber qual tipo de combustivel vale mais a pena
    fun calculaMelhorPreco(precoAlcool: String, precoGasolina: String) {
        val valorAlcool = precoAlcool.toDouble()
        val valorGasolina = precoGasolina.toDouble()

        val resultadoPreco = valorAlcool / valorGasolina

        if (resultadoPreco >= 0.7) {
            renderAlert("Atenção", "Melhor utilizar Gasolina!","Ok")
        } else {
            renderAlert("Atenção", "Melhor utilizar Álcool!","Ok")
        }
    }

    // Metodo responsavel por renderizar o Alert
    fun renderAlert (title: String, description: String, textButton: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(description)
        builder.setPositiveButton(textButton){dialog, which -> null}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}