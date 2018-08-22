package com.kotlin.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.provider.router.RouterPath

fun isLogin():Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method:()->Unit){
    if (isLogin()){
        method()
    }else{
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}