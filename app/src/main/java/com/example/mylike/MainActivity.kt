package com.example.mylike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //module-level
    //var like: Int = 0
    //create an instance of the viewModel
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences: SharedPreferences
    var dislike: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")

        //initialize the counterViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //initialize the share preferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        textViewDislike.text = dislike.toString()

        imageView.setOnClickListener{
            counterViewModel.incrementLike()
            textViewLike.text = counterViewModel.likeCount.toString()
        }

        imageView2.setOnClickListener{
            dislike++
            textViewDislike.text = dislike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onPause() {
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like), counterViewModel.likeCount)
            apply()
        }
        Log.d("MainActivity", "onPause")
        super.onPause()
    }

    override fun onResume() {
        textViewLike.text = counterViewModel.likeCount.toString()
        val like = sharedPreferences.getInt(getString(R.string.like), 0)
        counterViewModel.likeCount = like

        Log.d("MainActivity", "onPause")
        super.onResume()
    }
    override fun onStop() {
        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }
}
