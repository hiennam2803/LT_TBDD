package com.example.btbuoi1

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            showExitDialog()
//            onBackPressedDispatcher.onBackPressed()
        }

        val btnEdit = findViewById<ImageButton>(R.id.btnEdit)

        btnEdit.setOnClickListener {
            showErrLog()
        }


    }

    private fun showExitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Thoát ứng dụng")
            .setMessage("Bạn có chắc chắn muốn thoát không?")
            .setPositiveButton("Có") { _, _ -> finish() }
            .setNegativeButton("Không") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun showErrLog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Lỗi")
            .setMessage("Cái này chưa phát triển 😘")
            .setNegativeButton("OK") { dialog, _ -> dialog.dismiss() }.show()

    }
}