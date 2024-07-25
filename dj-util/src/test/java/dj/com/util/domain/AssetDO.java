package dj.com.util.domain;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author jiandong 2024-07-24 create
 */
@Data
@Accessors(chain = true)
public class AssetDO {
    private String assetOrderNo;

    private DOEnums.AssetTypeEnum assetType;

    private Long amount;

    private LocalDateTime businessDateTime;

    private Boolean forceUpdate;

    private JSONObject reqAppend;


    /**
     * 随机生成个Asset对象
     */
    public static AssetDO randomAsset(){
        JSONObject appendJson = (JSONObject) new JSONObject().put("pa", IdUtil.fastSimpleUUID());

        return new AssetDO().setAssetOrderNo(IdUtil.fastSimpleUUID())
                .setAssetType(DOEnums.AssetTypeEnum.OUT)
                .setAmount(100L)
                .setBusinessDateTime(LocalDateTime.now())
                .setForceUpdate(true)
                .setReqAppend(appendJson);
    }

}
