package com.example.retrofitexample

import android.graphics.pdf.PdfDocument
import com.example.retrofitexample.network.ApiService

// SSOT - Single Source of truth
class Repository(private val apiService: ApiService) {
    fun getCharacters(page: String) = apiService.fetchCharacter(page)
}