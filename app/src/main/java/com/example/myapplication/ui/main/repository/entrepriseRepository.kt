package com.example.myapplication.ui.main.repository

import com.example.myapplication.ui.main.data.retrofit.APIClient
import com.example.myapplication.ui.main.data.retrofit.SafeApiRequest

class entrepriseRepository(
    private val api: APIClient
) : SafeApiRequest() {
    suspend fun getentreprise() = apiRequest { api.getentreprise() }

}