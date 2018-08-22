package com.kotlin.base.widgets

import android.text.Editable
import android.text.TextWatcher

/*
    默认TextWatcher，空实现
 */
//监听EditText的输入情况
open class DefaultTextWatcher:TextWatcher{
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}
