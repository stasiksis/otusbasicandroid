package com.sfedorchuk

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val textViewInception by lazy {
        findViewById<TextView>(R.id.text_view_inception)
    }

    private val textViewMrRobot by lazy {
        findViewById<TextView>(R.id.text_view_mr_robot)
    }

    private val textViewIronMan by lazy {
        findViewById<TextView>(R.id.text_view_iron_man)
    }

    private val textViewXMen by lazy {
        findViewById<TextView>(R.id.text_view_x_men)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.details_iception).setOnClickListener() {
            resetColor()
            textViewInception.setTextColor(Color.RED)
        }

        findViewById<Button>(R.id.details_mr_robot).setOnClickListener() {
            resetColor()
            textViewMrRobot.setTextColor(Color.RED)
        }

        findViewById<Button>(R.id.details_iron_man).setOnClickListener() {
            resetColor()
            textViewIronMan.setTextColor(Color.RED)
        }

        findViewById<Button>(R.id.details_x_men).setOnClickListener() {
            resetColor()
            textViewXMen.setTextColor(Color.RED)
        }
    }

    private fun resetColor() {
        textViewInception.setTextColor(Color.BLACK)
        textViewMrRobot.setTextColor(Color.BLACK)
        textViewIronMan.setTextColor(Color.BLACK)
        textViewXMen.setTextColor(Color.BLACK)
    }
}