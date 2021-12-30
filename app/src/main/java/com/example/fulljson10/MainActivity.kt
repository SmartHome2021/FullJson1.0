package com.example.fulljson10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.room.FilmDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FilmDatabase.getInstance(this)
        setContentView(R.layout.activity_main)
    }

}