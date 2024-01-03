package com.permissionlx.heluoxuan

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.util.*

/**
 * Created by snsd on 2024/1/2
 * Describe:
 *
 * Fragment并不像Activity那样必须有界面，我们完全可以向Activity中添加一个隐藏的Fragment，
 * 然后在这个隐藏的Fragment中对运行时权限的API进行封装。
 * 这是一种非常轻量级的做法，不用担心隐藏Fragment会对Activity的性能造成什么影响。
 *
 * 另外注意，在InvisibleFragment中，我们并没有重写onCreateView()方法来加载某个布 局，
 * 因此它自然就是一个不可见的Fragment，待会只需要将它添加到Activity中即可。
 */

/**
 * Kotlin中的一个小技巧，typealias关键字可以用于给任意类型指定一个别名，
 * 比如我们将(Boolean, List<String>) -> Unit的别名指定成了PermissionCallback，
 */
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {
    private var callback: PermissionCallback ? = null
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 1) {
            //被拒绝列表
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            //是否授权全部权限
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}