package com.example.netflixkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.netflixkotlin.databinding.ActivityFrmCadastroBinding
import com.example.netflixkotlin.databinding.ActivityFrmLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.ktx.Firebase

class frmCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFrmCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrmCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        Toolbar()

        binding.btnCadastrar.setOnClickListener({
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.msgErro

            if(email.isEmpty() || senha.isEmpty()){
                mensagemErro.setText("Preencha todos os campos")
            }else{
                cadastrarUsuario()
            }
        })
    }

    private fun Toolbar(){
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
    }
    private fun cadastrarUsuario() {

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagemErro = binding.msgErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT)
                        .show()
                    binding.editEmail.setText("")
                    binding.editSenha.setText("")
                    mensagemErro.setText("")
                }
            }.addOnFailureListener {
                var erro = it

                    when{
                        erro is FirebaseAuthWeakPasswordException -> mensagemErro.setText("Digite uma senha com pelo menos 6 caracteres")
                        erro is FirebaseAuthUserCollisionException -> mensagemErro.setText("Esta conta já foi cadastrada")
                        erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a Internet")
                        else -> mensagemErro.setText("Erro ao Cadastrar Usuario!")
                    }

            }
    }
}