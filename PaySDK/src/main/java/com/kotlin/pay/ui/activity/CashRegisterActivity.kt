package com.kotlin.pay.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.pay.R
import com.kotlin.pay.injection.component.DaggerPayComponent
import com.kotlin.pay.injection.module.PayModule
import com.kotlin.pay.presenter.PayPresenter
import com.kotlin.pay.presenter.view.PayView
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {


    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)
        //设置沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        ARouter.getInstance().inject(this)
        initView()
        //initData()
    }

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(activityComponent)
                .payModule(PayModule()).build().inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mAlipayTypeTv.isSelected = true
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)

        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
        mPayBtn.onClick(this)

    }


    private fun initData() {
        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mTotalPrice = intent.getLongExtra(ProviderConstant.KEY_ORDER_PRICE, -1)
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mPayBtn -> {
                mPresenter.getPaySign(mOrderId, mTotalPrice)
            }

            R.id.mAlipayTypeTv -> {
                updatePayType(true, false, false)
            }

            R.id.mWeixinTypeTv -> {
                updatePayType(false, true, false)
            }

            R.id.mBankCardTypeTv -> {
                updatePayType(false, false, true)
            }
        }
    }

    private fun updatePayType(isAliPay: Boolean, isWeixinPay: Boolean, isBankCardPay: Boolean) {
        mAlipayTypeTv.isSelected = isAliPay
        mWeixinTypeTv.isSelected = isWeixinPay
        mBankCardTypeTv.isSelected = isBankCardPay

    }


    override fun onGetSignResult(result: String) {
        doAsync {
            val resultMap: Map<String, String> = PayTask(this@CashRegisterActivity)
                    .payV2(result, true)
            //回到主线程
            uiThread {
                if (resultMap["resultStatus"].equals("9000")) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }

    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()

    }

}