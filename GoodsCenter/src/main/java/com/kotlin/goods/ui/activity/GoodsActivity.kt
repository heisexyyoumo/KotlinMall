package com.kotlin.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.kotlin.base.common.GoodsConstant
import com.kotlin.base.ext.startLoading
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.R
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.injection.component.DaggerGoodsComponent
import com.kotlin.goods.injection.module.GoodsModule
import com.kotlin.goods.presenter.GoodsListPresenter
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity

class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView,
        BGARefreshLayout.BGARefreshLayoutDelegate {


    private lateinit var mGoodsAdapter: GoodsAdapter

    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    private val mData: MutableList<Goods>? = null

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
                .goodsModule(GoodsModule()).build().inject(this)

        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        initRefreshLayout()
        loadData()
    }


    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter

        mGoodsAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter
        .OnItemClickListener<Goods>{
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID
                        to item.id)
            }

        })
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)

    }

    private fun loadData() {
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,0)!= 0){
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra
            (GoodsConstant.KEY_GOODS_KEYWORD),mCurrentPage)
        }else{
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent
                    .getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
        }


    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }

            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            return true
        } else {
            return false
        }

    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

}