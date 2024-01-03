package com.permissionlx.heluoxuan

import androidx.fragment.app.FragmentActivity

/**
 * Created by snsd on 2024/1/3
 * Describe:
 *
 * 通过TAG判断是否已添加该空view的InvisibleFragment
 *
 * 注意，在添加结束后一定要调用commitNow()方法，而不能调用commit()方法，
 * 因为commit()方法并不会立即执行添加操作，
 * 因而无法保证下一行代码执行时 InvisibleFragment已经被添加到Activity中了。
 *
 *  需要注意的是，permissions参数在这里实际上是一个数组。
 *  对于数组，我们可以遍历它，可以通过下标访问，但是不可以直接将它传递给另外一个接收可变长度参数的方法。
 *  因此，这里在调用requestNow()方法时，在permissions参数的前面加上了一个*，
 *  这个符号并不是指针的意思，而是表示将一个 数组转换成可变长度参数传递过去。
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}