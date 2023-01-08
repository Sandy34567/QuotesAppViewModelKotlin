package com.book.quotesappviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val quoteText:TextView
    get() = findViewById(R.id.quoteText)
    private val quoteAuthor:TextView
        get() = findViewById(R.id.quoteAuthor)
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quote:Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onShare(view: View) {
        val intent =Intent(Intent.ACTION_SEND)
        intent.setType("text/plan")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
       startActivity(intent)
    }
}