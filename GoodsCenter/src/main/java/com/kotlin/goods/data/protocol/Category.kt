package com.kotlin.goods.data.protocol

/*
    商品分类
 */
data class Category(
        val id: Int, //分类id
        val categoryName: String, //分类名称
        val categoryIcon: String = "", //分类图标
        val parentId: Int, //分类 父级id
        var isSelected: Boolean = false //是否被选中
)