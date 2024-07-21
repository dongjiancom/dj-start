
package dj.com.mybatis.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author jiandong 2023-05-22 create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AppendInfo {
    /**
     * 特殊处理、账户域冻结解冻接口需传 acctNo
     */
    private String apFreezeAccountNo;

    /**
     * 特特殊处理、账户域对手账号
     */
    private String opponentAccountNo;

    /**
     * 外部资产渠道信息
     */
    private JSONObject reqAppend;
    
}