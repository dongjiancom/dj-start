

package dj.com.mybatis.handler;

import lombok.Data;

/**
 * @author jiandong 2023-03-27 create
 */
@Data
public class CardholderInfo {
    /**
     * 银联userId或微信用户的openid 支付宝接口：买家的支付宝唯一用户号（2088 开头的 16 位纯数字）
     */
    private String openid;
    private String appid;
    private String subAppid;
    private String subOpenid;

    private String buyerLogonId;

    //付款方账号
    private String payerAcctNo;
    //付款方账户名称
    private String payerAcctName;
    //付款方账号类型
    private String payerAcctType;
    //证件类型
    private String certType;
    //证件号
    private String certNo;
    //账户机构预留手机号
    private String payerMobNo;
    //付款方行号
    private String bankNo;

    /**
     * 代付相关的
     * 收款方账号类型
     */
    private String payeeAcctType;

    /**
     * 收款方账号名称
     */
    private String payeeAcctName;

    /**
     * 收款方账号
     */
    private String payeeAcctNo;

}