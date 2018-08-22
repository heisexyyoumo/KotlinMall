package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.loadUrl
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.mall.R
import com.kotlin.mall.ui.activity.SettingActivity
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.ui.activity.OrderActivity
import com.kotlin.order.ui.activity.ShipAddressActivity
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.common.afterLogin
import com.kotlin.provider.common.isLogin
import com.kotlin.user.ui.activity.LoginActivity
import com.kotlin.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity


class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (!isLogin()) {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        } else {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }

            R.id.mAddressTv -> {
                afterLogin {
                    startActivity<ShipAddressActivity>()
                }
            }

            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }


            R.id.mWaitPayOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS
                        to OrderStatus.ORDER_WAIT_PAY)
            }
            R.id.mWaitConfirmOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS
                        to OrderStatus.ORDER_WAIT_CONFIRM)
            }
            R.id.mCompleteOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS
                        to OrderStatus.ORDER_COMPLETED)
            }

            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }
        }
    }

}