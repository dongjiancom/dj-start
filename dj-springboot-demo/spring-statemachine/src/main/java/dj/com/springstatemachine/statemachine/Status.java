package dj.com.springstatemachine.statemachine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author jiandong 2022-12-14" create
 */
public interface Status {

    /**
     * 支付域交易状态
     */
    @AllArgsConstructor
    @Getter
    enum TradeStatus {
        //已创建
        CREATED("CREATED", "已创建"),
        //支付中
        PAYING("PAYING", "支付中"),
        //支付成功
        SUCCESS("SUCCESS", "支付成功"),
        //支付失败
        FAIL("FAIL", "支付失败"),
        ;

        private String code;
        private String desc;

        public static TradeStatus valuesOf(String code) {
            return Arrays.stream(TradeStatus.values()).filter(s->s.code.equals(code)).findFirst().get();
        }
    }
}