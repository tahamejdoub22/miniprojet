package com.example.myapplication.ui.main.repository

import com.example.myapplication.ui.main.data.retrofit.APIClient
import com.example.myapplication.ui.main.data.retrofit.SafeApiRequest

class MaterialsRepository(
    private val api: APIClient
) : SafeApiRequest() {
    suspend fun getMaterials() = apiRequest { api.getMaterials() }

}