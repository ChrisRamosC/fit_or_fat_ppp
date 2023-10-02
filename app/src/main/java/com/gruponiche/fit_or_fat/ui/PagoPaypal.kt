package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.databinding.ActivityPagoPaypalBinding
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton

class PagoPaypal : AppCompatActivity() {
    private lateinit var binding: ActivityPagoPaypalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagoPaypalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una instancia de Shared Preferences
        val sharedPreferences3 = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        // Recuperar un valor entero almacenado
        val nombre = sharedPreferences3.getString("nombre_usuario", "")
        Log.i("nombre",nombre.toString())
        binding.tvNombreCompleto2.text = nombre

        binding.flechaAtras.setOnClickListener {
            finish()
        }

        findViewById<PayPalButton>(R.id.paypal_button).setup(
            createOrder =
            CreateOrder { createOrderActions ->
                val order =
                    Order(
                        intent = OrderIntent.CAPTURE,
                        appContext = AppContext(userAction = UserAction.PAY_NOW),
                        purchaseUnitList =
                        listOf(
                            PurchaseUnit(
                                amount =
                                Amount(currencyCode = CurrencyCode.USD, value = "3.94")
                            )
                        )
                    )
                createOrderActions.create(order)
            },
            onApprove =
            OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    //here you will get the result (Success)
                    Toast.makeText(applicationContext, "Pago completado", Toast.LENGTH_SHORT).show()
                    goToPagoCompletado()
                }
            },
            onCancel = OnCancel {
                Toast.makeText(applicationContext, "Pago cancelado", Toast.LENGTH_SHORT).show()
            },
            onError = OnError {
                    errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
                goToPagoCompletado()
            }
        )
    }
    private fun goToPagoCompletado() {
        val intent = Intent (this, PagoCompletado::class.java)
        startActivity(intent)
    }
}