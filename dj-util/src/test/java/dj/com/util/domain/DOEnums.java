/*******************************************************************************
 * 系统名称:  新一代支付系统天禄项目
 * 子系统名： tianlu-ddd-template
 * 模块名称： tianlu-pp-domain
 * 文件名称： PaymentEnums.java
 * 创建人：   jiandong
 * Copyright (c) 2022-2023,  All Rights Reserved.
 * 所有版权归易生支付有限公司所有
 *
 *
 * 注意： 本内容仅限于易生支付有限公司内部使用，禁止转发
 ******************************************************************************/

package dj.com.util.domain;


/**
 * @author jiandong
 */
public interface DOEnums {

    /**
     * 支付单的类型
     */
    enum PaymentTypeEnum {
        /**
         * 支付单
         */
        PAY,

        /**
         * 合并支付单
         */
        MERGE,

        /**
         * 退款单
         */
        REFUND,

        /**
         * 冲正单
         */
        REVERSAL,

        /**
         * 撤销冲正单
         */
        CANCEL_REVERSAL,

        /**
         * 关闭单
         */
        CLOSE,

        /**
         * 撤销单
         */
        CANCEL,

        /**
         * 解冻单
         */
        UNFREEZE,

        /**
         * 冻结单
         */
        FROZEN
    }



    /**
     * 资产单类型
     */
    enum AssetTypeEnum {

        /**
         * 营销资产
         */
        MARKETING,

        /**
         * 记录资产
         */
        RECORD,

        /**
         * 外部资产
         */
        OUT,

        /**
         * 内部资产
         */
        INNER,

        /**
         * 外部资产单（记录资产单），不记账
         */
        OUT_RECORD,

        /**
         * 外部资产单（记账资产单），直接成功，只记账
         */
        OUT_ACCOUNT,

        /**
         * 储值账户-易生发卡、易生余额
         */
        INNER_PRE_CARD,
        /**
         * 储值绑定银行卡
         */
        INNER_PPCARD_OUT
    }


}
