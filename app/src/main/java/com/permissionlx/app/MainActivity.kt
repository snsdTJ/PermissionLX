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

            /**
             * 写法一：
             * 可变参数之后还有其它参数时的写法：当在 Kotlin 函数中有 vararg 参数之后还有其他参数时，可以通过在函数调用中指定其他参数的名称来调用该函数
             * 方法作为参数：传递一个方法申明
             */
//            PermissionX.request(
//                this,
//                Manifest.permission.CALL_PHONE,
//                callback = fun(allGranted: Boolean, deniedList: List<String>) {
//
//                })

            /**
             * 写法二：
             * 可变参数之后还有其它参数时的写法：当在 Kotlin 函数中有 vararg 参数之后还有其他参数时，可以通过在函数调用中指定其他参数的名称来调用该函数。
             * 方法作为参数：传递方法地址
             */
//            fun callback(allGranted: Boolean, deniedList: List<String>) {
//
//            }
//            PermissionX.request(this, Manifest.permission.CALL_PHONE, callback = ::callback)

            /**
             * 写法三：
             * 可变参数之后还有其它参数时的写法
             * 方法作为参数：使用lambda
             */
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