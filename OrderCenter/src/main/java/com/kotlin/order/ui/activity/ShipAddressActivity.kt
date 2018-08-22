package com.kotlin.order.ui.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.eightbitlab.rxbus.Bus
import com.jph.takephoto.compress.CompressConfig
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.onClick
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ext.startLoading
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.injection.component.DaggerShipAddressComponent
import com.kotlin.order.injection.module.ShipAddressModule
import com.kotlin.order.presenter.ShipAddressPresenter
import com.kotlin.order.presenter.view.ShipAddressView
import com.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    private lateinit var mAdapter: ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule()).build().inject(this)
        mPresenter.mView = this

    }


    private fun initView() {

        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mAdapter

        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(OrderConstant
                        .KEY_SHIP_ADDRESS to address)
                if (address != null){
                    toast("传过去了")
                }
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除",
                        "确定删除该地址?",
                        "取消",
                        null,
                        arrayOf("确定"),
                        this@ShipAddressActivity,
                        AlertView.Style.Alert,
                        object : OnItemClickListener {
                            override fun onItemClick(o: Any?, position: Int) {
                                if (position == 0) {
                                    mPresenter.deleteShipAddress(address.id)
                                }
                            }
                        }).show()
            }


        }

        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }

        mAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter
        .OnItemClickListener<ShipAddress>{
            override fun onItemClick(item: ShipAddress, position: Int) {
                Bus.send(SelectAddressEvent(item))
                finish()
            }

        })
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()

    }

    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onSetDefaultResult(result: Boolean) {
        toast("设置默认成功")
        loadData()
    }

    override fun onDeleteResult(result: Boolean) {
        toast("删除成功")
        loadData()
    }


}