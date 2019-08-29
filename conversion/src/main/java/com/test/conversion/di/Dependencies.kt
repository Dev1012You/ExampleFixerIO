package com.test.conversion.di

import android.content.Context
import com.test.conversion.BuildConfig
import com.test.conversion.common.BigDecimalFormatter
import com.test.conversion.data.ConversionDataSource
import com.test.conversion.data.FixerService
import com.test.conversion.data.mapper.ConversionResultMapper
import com.test.conversion.data.mapper.LatestConversionRatesMapper
import com.test.conversion.data.repository.ConversionRepositoryImpl
import com.test.conversion.data.repository.SettingsRepositoryImpl
import com.test.conversion.data.utils.AuthInterceptor
import com.test.conversion.domain.usecase.ConvertUseCase
import com.test.conversion.domain.usecase.GetBaseConversionRatesUseCase
import com.test.conversion.domain.usecase.GetBaseCurrencyUseCase
import com.test.conversion.presentation.mapper.ConversionRateItemMapper
import com.test.conversion.ui.utils.CustomViewModelFactory
import com.test.conversion.ui.utils.FlagUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies {
    private val conversionResultMapper by lazy { ConversionResultMapper() }
    private val latestConversionRatesMapper by lazy { LatestConversionRatesMapper() }
    private val conversionDataSource by lazy {
        ConversionDataSource(
            getFixerService(),
            conversionResultMapper,
            latestConversionRatesMapper
        )
    }
    private val conversionRepository by lazy { ConversionRepositoryImpl(conversionDataSource) }
    private val settingsRepository by lazy { SettingsRepositoryImpl() }

    val viewModelFactory by lazy { CustomViewModelFactory(this) }
    val bigDecimalFormatter by lazy { BigDecimalFormatter() }

    private lateinit var _flagUtil: FlagUtil
    val flagUtil: FlagUtil
        get() = _flagUtil

    fun init(context: Context) {
        _flagUtil = FlagUtil(context) // todo synchronize, etc or better use dagger
    }

    fun baseConversionRatesUseCase(): GetBaseConversionRatesUseCase =
        GetBaseConversionRatesUseCase(conversionRepository, settingsRepository)

    fun conversionUseCase(): ConvertUseCase = ConvertUseCase(conversionRepository, settingsRepository)

    fun conversionRateItemMapper(): ConversionRateItemMapper = ConversionRateItemMapper(bigDecimalFormatter)

    fun getBaseCurrencyUseCase(): GetBaseCurrencyUseCase = GetBaseCurrencyUseCase(settingsRepository)

    private fun getFixerService(): FixerService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(FixerService::class.java)
    }
}