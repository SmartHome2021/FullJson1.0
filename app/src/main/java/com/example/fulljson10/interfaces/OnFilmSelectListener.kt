package com.example.fulljson10.interfaces

import com.example.fulljson10.model.Film

interface OnFilmSelectListener {
    fun onSelect(film: Film)
    fun onLoad(film: Film)
    fun onDelete(film: Film)
    fun onFavorite(film: Film)
}