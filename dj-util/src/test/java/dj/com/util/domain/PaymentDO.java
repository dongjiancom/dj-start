package dj.com.util.domain;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jiandong 2024-07-24 create
 */
@Data
@Accessors(chain = true)
public class PaymentDO {

    private String ppOrderNo;

    private DOEnums.PaymentTypeEnum paymentType;

    private Long amount;

    private LocalDateTime businessDateTime;

    private Boolean forceUpdate;

    private JSONObject reqAppend;

    List<AssetDO> assetDOList;

    /**
     * 为true时循环引用，打印出来的格式会观看困难
     *     ······
     *     {
     *         "$ref": "$[0].assetList[0]"
     *     }
     *     ······
     */
    public static final boolean assetCopy = true;

    public static  List<PaymentDO> getPaymentList() {
        return ListUtil.of(randomPayment(), randomPayment(), randomPayment());
    }

    /**
     * 随机生成个Payment对象
     */
    public static PaymentDO randomPayment(){

        JSONObject appendJson = (JSONObject) new JSONObject().put("pp", IdUtil.fastSimpleUUID());

        List<AssetDO> assetDOList;
        if(assetCopy){
            // 只生成一个对象
            AssetDO assetDO = AssetDO.randomAsset();
            assetDOList = ListUtil.of(assetDO, assetDO, assetDO);
        } else {
            // 每个对象新生成
            assetDOList = ListUtil.of(AssetDO.randomAsset(), AssetDO.randomAsset(), AssetDO.randomAsset());
        }
        return new PaymentDO().setPpOrderNo(IdUtil.fastSimpleUUID())
                .setAmount(100L)
                .setPaymentType(DOEnums.PaymentTypeEnum.FROZEN)
                .setForceUpdate(true)
                .setReqAppend(appendJson)
                .setBusinessDateTime(LocalDateTime.now())
                .setAssetDOList(assetDOList);
    }

}
