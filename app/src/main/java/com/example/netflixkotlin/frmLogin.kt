package com.example.netflixkotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.netflixkotlin.databinding.ActivityFrmLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class frmLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFrmLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrmLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        VerificaUsuarioLogado()

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this,frmCadastro::class.java)
            startActivity(intent)
        }
        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.mensagemErro
            if(email.isEmpty() || senha.isEmpty()){

                mensagemErro.setText("Preencha os campos")

            }else{
                AutenticarUsuario()
            }
        }

    }
    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagemErro = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful){

                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                irParaTelaFilmes()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
                mensagemErro.setText("")
            }
        }.addOnFailureListener {
            var erro = it
            when{

                erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a Internet")
                erro is FirebaseAuthInvalidCredentialsException -> mensagemErro.setText("Email ou senha estão incorretos")
                else -> mensagemErro.setText("Erro ao logar usuario!")
            }
        }
    }
    private fun VerificaUsuarioLogado(){

        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if(usuarioLogado != null){

            irParaTelaFilmes()

        }
    }
    private fun irParaTelaFilmes(){
        val intent = Intent(this,ListaFIlmes::class.java)
        startActivity(intent)
        finish()
    }

}
