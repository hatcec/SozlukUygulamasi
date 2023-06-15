package com.example.sozlukuygulamasi

class Kelimelerdao {
    fun tumKelimeler(vt:VeritabaniYArdimcisi):ArrayList<Kelimeler>{
        val kelimelerListe=ArrayList<Kelimeler>()
        val db=vt.writableDatabase
        val sorgu=db.rawQuery("SELECT * FROM kelimeler",null)
        while (sorgu.moveToNext()){
            val kelime=Kelimeler(sorgu.getInt(sorgu.getColumnIndex("kelime_id"))
                ,sorgu.getString(sorgu.getColumnIndex("ingilizce"))
                ,sorgu.getString(sorgu.getColumnIndex("turkce")))
            kelimelerListe.add(kelime)
        }
        return kelimelerListe
    }
    fun aramaYap(vt:VeritabaniYArdimcisi, aramaKElime:String ):ArrayList<Kelimeler>{
        val kelimelerListe=ArrayList<Kelimeler>()
        val db=vt.writableDatabase
        val sorgu=db.rawQuery("SELECT * FROM kelimeler  WHERE ingilizce like '%$aramaKElime%'",null)
        while (sorgu.moveToNext()){
            val kelime=Kelimeler(sorgu.getInt(sorgu.getColumnIndex("kelime_id"))
                ,sorgu.getString(sorgu.getColumnIndex("ingilizce"))
                ,sorgu.getString(sorgu.getColumnIndex("turkce")))
            kelimelerListe.add(kelime)
        }
        return kelimelerListe
    }
}