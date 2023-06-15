package com.example.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sozlukuygulamasi.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener{
    lateinit var binding: ActivityMainBinding
    private lateinit var kelimelerListe:ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt: VeritabaniYArdimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        veritabaniKopyala()
        binding.toolbar.title="Sözlük Uygulamasi"
        setSupportActionBar(binding.toolbar)
        binding.rv.setHasFixedSize(true)
        vt=VeritabaniYArdimcisi(this)
        binding.rv.layoutManager=LinearLayoutManager(this)
        kelimelerListe=Kelimelerdao().tumKelimeler(vt )

        adapter= KelimelerAdapter(this, kelimelerListe)
        binding.rv.adapter=adapter


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        val item=menu?.findItem(R.id.action_ara)
        val searchView=item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        arama(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        arama(newText)
       return true
    }
    fun veritabaniKopyala(){
        val copyHelper=DatabaseCopyHelper(this)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun arama(aramaKelime:String){
        kelimelerListe=Kelimelerdao().aramaYap(vt , aramaKelime)

        adapter= KelimelerAdapter(this, kelimelerListe)
        binding.rv.adapter=adapter
    }
}