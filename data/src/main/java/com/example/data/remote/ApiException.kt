package com.example.data.remote

class ApiException(val errorCode: Int, val errorBody: String?): Exception()
