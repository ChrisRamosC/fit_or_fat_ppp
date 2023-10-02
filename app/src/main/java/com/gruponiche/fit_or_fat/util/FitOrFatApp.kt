package com.gruponiche.fit_or_fat.util

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.gruponiche.fit_or_fat.BuildConfig
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction

class FitOrFatApp:Application() {
    var returnUrl = "com.gruponiche.fit_or_fat://paypalpay"
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        val config = CheckoutConfig(
            application = this,
            clientId = "AQoUIXNw11PGajeF4G1ggQ18J9z137u4irTXufj0vPctVF3tOsTCwDT8EEex7Tb0_sM3pwJr5BXIUAxj",
            //returnUrl = returnUrl,
            environment = Environment.SANDBOX,
            currencyCode = CurrencyCode.USD,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)
    }
}