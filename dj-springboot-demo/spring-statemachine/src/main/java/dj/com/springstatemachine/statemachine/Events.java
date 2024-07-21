
package dj.com.springstatemachine.statemachine;

/**
 * @author jiandong 2022-12-14 create
 * 渠道域：CEP
 * 账户域：AP
 */
public interface Events {

    enum TradeEvents {

        //上游域 返回状态--------------start------------------

        /**
         * 上游域 请求失败 —— 尚未发送上游域
         */
        UP_STREAM_REQ_FAIL,

        /**
         * 上游域 请求超时
         */
        UP_STREAM_REQ_TIMEOUT,

        /**
         * 上游域 无需发送
         */
        UP_STREAM_IGNORE,

        /**
         *  上游域 返回 支付中
         */
        UP_STREAM_RETURN_PAYING,

        /**
         * 上游域 返回 成功
         */
        UP_STREAM_RETURN_SUCCESS,

        /**
         * 上游域 返回 失败
         */
        UP_STREAM_RETURN_FAIL,

        /**
         * 上游储值授权域 返回 授权成功
         */
        UP_STREAM_RETURN_AUTH_SUCCESS,

        /**
         * 上游域 返回 授权挂起
         */
        UP_STREAM_RETURN_AUTH_SUSPEND,
        //上游域 返回状态--------------end------------------

        /**
         * 冻结成功
         */
        FREEZE_SUCCESS,

        /**
         * 冻结失败
         */
        FREEZE_FAIL,

        /**
         * 解冻成功
         */
        UNFREEZE_SUCCESS,

        /**
         * 解冻失败
         */
        UNFREEZE_FAIL,

        /**
         * 核销、预扣完成 成功
         */
        CONFIRM_SUCCESS,

        /**
         * 核销、预扣完成 失败
         */
        CONFIRM_FAIL,

        /**
         * 冲正成功
         */
        REVERSAL_SUCCESS,

        /**
         * 冲正失败
         */
        REVERSAL_FAIL,

        /**
         * 撤销成功
         */
        CANCEL_SUCCESS,

        /**
         * 撤销失败
         */
        CANCEL_FAIL,

        /**
         * 关闭成功
         */
        CLOSE_SUCCESS,

        /**
         * 关闭失败
         */
        CLOSE_FAIL,

    }

}