package com.kotlin.order.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.ui.activity.OrderConfirmActivity
import com.kotlin.order.ui.activity.OrderDetailActivity
import com.kotlin.order.ui.fragment.OrderFragment
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(activity: OrderDetailActivity)
    fun inject(fragment: OrderFragment)
}