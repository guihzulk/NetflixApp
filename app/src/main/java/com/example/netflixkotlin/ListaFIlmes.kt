package com.example.netflixkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflixkotlin.Adapter.FilmesAdapter
import com.example.netflixkotlin.OnClick.OnItemClickListener
import com.example.netflixkotlin.OnClick.addOnItemClickListener
import com.example.netflixkotlin.databinding.ActivityListaFilmesBinding
import com.example.netflixkotlin.model.addFilmes
import com.google.firebase.auth.FirebaseAuth

class ListaFIlmes : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_filmes = binding.recyclerview
        recycler_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext,3)

        recycler_filmes.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {

                when{
                    position == 0 -> DetalhesFilmes()
                }

            }
        })

    }

    private fun  DetalhesFilmes(){
        val intent = Intent(this,DetalhesFIlmes::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut()
                VoltarLogin()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun VoltarLogin(){
        val intent = Intent(this,frmLogin::class.java)
        startActivity(intent)
        finish()
    }
}