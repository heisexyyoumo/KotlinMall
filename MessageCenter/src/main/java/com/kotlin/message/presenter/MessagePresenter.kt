package com.kotlin.message.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.message.data.protocol.Message
import com.kotlin.message.service.MessageService
import com.kotlin.message.presenter.view.MessageView
import javax.inject.Inject

class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService


    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        messageService.getMessageList()
                .execute(object : BaseSubscriber<MutableList<Message>?>(mView) {
                    override fun onNext(t: MutableList<Message>?) {
                        mView.onGetMessageResult(t)
                    }
                }, lifecycleProvider)

    }

}