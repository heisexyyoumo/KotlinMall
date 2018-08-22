package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.common.GoodsConstant
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.R
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.SkuChangedEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.ui.adapter.GoodsDetailVpAdapter
import com.kotlin.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBadge: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)

        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }

        mCartBadge = QBadgeView(this)
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    setCartBadge()
                }.registerInBus(this)

    }

    private fun loadCartSize() {
        setCartBadge()
    }

    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.END or Gravity.TOP
        mCartBadge.setGravityOffset(22f, -2f, true)
        mCartBadge.setBadgeTextSize(6f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils
                .getInt(GoodsConstant.SP_CART_SIZE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}