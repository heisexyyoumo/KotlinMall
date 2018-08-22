package com.kotlin.goods.data.protocol

/*
    获取分类请求列表，parentId为0是一级分类
 */
data class GetCategoryReq(val parentId:Int)