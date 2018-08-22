package com.kotlin.mall.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.goods.ui.activity.SearchGoodsActivity
import com.kotlin.mall.R
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.HomeDiscountAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initView()
        initDiscount()
        initTopic()
    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE,
                HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }

    private fun initView() {
        mSearchEt.onClick {
            startActivity<SearchGoodsActivity>()
        }
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场",
                "新用户立领1000元优惠劵"))
    }

    private fun initDiscount() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        val discountAdapter = HomeDiscountAdapter(activity as Context)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO,
                HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))

    }

    private fun initTopic() {

        mTopicPager.adapter = TopicAdapter(context!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO,
                HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))

        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f)
                .pagerMargin(-30.0f).spaceSize(0.0f).build()

    }

}