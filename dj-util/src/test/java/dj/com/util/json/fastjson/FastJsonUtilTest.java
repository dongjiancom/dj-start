package dj.com.util.json.fastjson;

import cn.hutool.core.lang.Console;
import dj.com.util.domain.PaymentDO;
import org.junit.Test;

public class FastJsonUtilTest {

    @Test
    public void t1() {

        Console.log("test toJsonString :");
        Console.log(
                FastJsonUtil.toJsonString(PaymentDO.getPaymentList())
        );

        Console.log("test toJsonStringNoDisableCircular :");
        Console.log(
                FastJsonUtil.toJsonStringNoDisableCircular(PaymentDO.getPaymentList())
        );

    }



}