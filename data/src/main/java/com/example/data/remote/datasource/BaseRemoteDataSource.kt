package com.example.data.remote.datasource

import com.example.data.constant.Constants
import com.example.data.remote.ApiException
import retrofit2.Response
import java.io.IOException
import com.example.common.Result

abstract class BaseRemoteDataSource() {
    protected suspend fun <T, R> callAPI(
        response: suspend () -> Response<T>,
        mapper: (T) -> R
    ): Result<R> {
        return try {
            val result = response()
            if (result.isSuccessful) {
                Result.Success(mapper(result.body()!!))
            } else {
                Result.Failure(ApiException(result.code(), result.errorBody().toString()))
            }
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> Result.Failure(exception)
                else -> Result.Failure(Exception(Constants.NETWORK_ERROR))
            }
        }
    }
}