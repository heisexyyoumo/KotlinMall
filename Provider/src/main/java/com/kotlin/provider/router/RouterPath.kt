package com.kotlin.provider.router

object RouterPath {
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

    class PaySDK {
        companion object {
            const val PATH_PAY = "/paySDK/pay"
        }
    }

    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
            //注意第一个路径一定不能相同
            const val PATH_MESSAGE_ORDER = "/msgCenter/order"
        }
    }
}