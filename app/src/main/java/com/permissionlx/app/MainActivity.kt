package com.permissionlx.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionlx.heluoxuan.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by snsd on 2024/1/3
 * Describe:
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeCallBtn.setOnClickListener {

            PermissionX.request(
                this,
                Manifest.permission.CALL_PHONE
            ) { allGranted: Boolean, deniedList: List<String> ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(
                        this, "You denied $deniedList", Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }


}