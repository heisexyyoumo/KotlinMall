package com.kotlin.order.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    private fun initView() {

        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        //设置viewpager的选中
        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS,
                OrderStatus.ORDER_ALL)
    }


}