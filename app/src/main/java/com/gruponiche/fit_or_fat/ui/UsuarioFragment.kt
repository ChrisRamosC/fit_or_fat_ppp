package com.gruponiche.fit_or_fat.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.gruponiche.fit_or_fat.R

class UsuarioFragment : Fragment(R.layout.fragment_usuario) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnRetar = requireView().findViewById<Button>(R.id.btnRetarAmigoUsuario)
        val btnConfig = requireView().findViewById<Button>(R.id.btnVerConfigUsuario)

        btnRetar.setOnClickListener {
            goToRetarAmigo()
        }

        btnConfig.setOnClickListener {
            goToConfigUsuario()
        }


    }

    /*
    private fun goToRetarAmigo(){
        val intent = Intent (getActivity(), RetarAmigo::class.java)
        getActivity()?.startActivity(intent)
    }
    */

    private fun goToConfigUsuario(){
        val intent = Intent (activity, VisualizarConfigUsuario::class.java)
        activity?.startActivity(intent)
    }
    private fun goToRetarAmigo(){
        val intent = Intent (activity, RetarAmigo::class.java)
        startActivity(intent)
    }

}