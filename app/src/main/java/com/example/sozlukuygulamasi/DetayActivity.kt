package com.example.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sozlukuygulamasi.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sonuc=intent.getSerializableExtra("nesne")as Kelimeler
        binding.textViewingilizce.text=sonuc.ingilizce
        binding.textViewturkce.text=sonuc.turkce
    }
}