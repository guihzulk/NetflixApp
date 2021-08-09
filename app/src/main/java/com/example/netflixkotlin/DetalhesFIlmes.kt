package com.example.netflixkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflixkotlin.Adapter.FilmesAdapter
import com.example.netflixkotlin.OnClick.OnItemClickListener
import com.example.netflixkotlin.OnClick.addOnItemClickListener
import com.example.netflixkotlin.databinding.ActivityDetalhesFilmesBinding
import com.example.netflixkotlin.model.addFilmes
import com.squareup.picasso.Picasso

class DetalhesFIlmes : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext,3)

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-kotlin-65184.appspot.com/o/video.jpg?alt=media&token=2ea86926-6326-4832-b0e7-1508fcb607db"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this,video::class.java)
            startActivity(intent)
        }
    }

    private fun Toolbar(){
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(R.drawable.ic_voltar)
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this,ListaFIlmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}