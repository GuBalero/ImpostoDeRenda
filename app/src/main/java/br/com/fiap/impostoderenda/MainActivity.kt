package br.com.fiap.impostoderenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.fiap.impostoderenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun calcularImposto(view: View) {

        val salarioTxt = binding.txtSalario.text.toString().trim()
        val salarioNum = if(salarioTxt != "") salarioTxt.toDouble() else 0.0

        if(salarioTxt == "" || salarioNum == 0.0){
            Toast.makeText(this, "Informe um sálario válido!", Toast.LENGTH_LONG).show()
        }
        else{

            if(salarioNum <= 1903.99){
                exibirMensagem("Faz o L", "Você está isento do imposto de renda", true)
            }
            else{
                val imposto = salarioNum * 0.4
                exibirMensagem("Não faz o L", "Você deve pagar ${imposto} de imposto de renda", false)
            }
        }
    }

    fun exibirMensagem(title: String, mensagem: String, fazerL: Boolean){
        val builder = AlertDialog.Builder(this)

        if(fazerL){
            val factory = LayoutInflater.from(this)
            val view = factory.inflate(R.layout.lula, null)
            builder.setView(view)
        }

        builder
            .setTitle(title)
            .setMessage(mensagem)
            .setPositiveButton("Ok", null)

        builder.create().show()
    }
}