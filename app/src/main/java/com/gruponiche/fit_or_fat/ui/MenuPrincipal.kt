package com.gruponiche.fit_or_fat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.gruponiche.fit_or_fat.R
import com.gruponiche.fit_or_fat.databinding.ActivityMenuPrincipalBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuPrincipal : AppCompatActivity(){

    private lateinit var binding : ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una instancia de Shared Preferences
        val sharedPreferences3 = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        // Recuperar un valor entero almacenado
        val nombre = sharedPreferences3.getString("nombre_usuario", "")
        Log.i("nombre",nombre.toString())
        binding.tvnombreUsuario.text = nombre

        val inicioFragment =  InicioFragment()
        val buscarFragment = BuscarFragment()
        val graficasFragment = GraficasFragment()
        val usuarioFragment = UsuarioFragment()

//        Probando().pruebaApi()

//        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.menu_home -> {
//                    setCurrentFragment(inicioFragment)
//                    true
//                }
//                R.id.menu_buscar -> {
//                    setCurrentFragment(buscarFragment)
//                    true
//                }
//                R.id.menu_graficas -> {
//                    setCurrentFragment(graficasFragment)
//                    true
//                }
//                R.id.menu_cuenta -> {
//                    setCurrentFragment(usuarioFragment)
//                    true
//                }
//                else -> false
//            }
//        }

        navegacion()
    }

    fun navegacion() {
        // Navegacion barra inferior
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.pantalla_principal

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuPrincipal -> true
                R.id.buscarRutina -> {
                    startActivity(Intent(applicationContext, BuscarRutina::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.graficas-> {
                    startActivity(Intent(applicationContext, GraficaReportes::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.perfil -> {
                    startActivity(Intent(applicationContext, PerfilUsuario::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }

        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerViewMenuPrincipal, fragment)
            commit()
        }
    }


}