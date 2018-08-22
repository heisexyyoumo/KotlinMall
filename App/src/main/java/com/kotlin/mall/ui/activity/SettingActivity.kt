package com.kotlin.mall.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}
