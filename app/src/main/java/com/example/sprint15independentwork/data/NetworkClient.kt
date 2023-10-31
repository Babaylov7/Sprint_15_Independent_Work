package com.example.sprint15independentwork.data

import com.example.sprint15independentwork.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}