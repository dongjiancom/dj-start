package dj.com.util.log;


import cn.hutool.core.util.StrUtil;
import dj.com.util.json.fastjson.FastJsonUtil;

/**
 * 2024-03-19 21:13:25 应该只返回组装好的字符串，去真正打印的地方才能看到行号
 *
 * @author jiandong
 * @date 2023/06/08  15:30
 */
public class LocalLog {

    /**
     * @param obj     要打印的对象
     * @param stepInt 1 == 接收请求 from 下游域
     *                2 == 发送报文 to 上游域
     *                3 == 接收报文 from 上游域
     *                4 == 返回报文 to 下游域
     * @param apiName 自定义附加信息
     */
    public static String ppPackageLogStr(Object obj, int stepInt, String apiName) {
        LogAllTypes.LogPpType logPpType = getLogPpType(stepInt);

        StringBuilder logReqSb = StrUtil.builder();
        StringBuilder logRespSb = StrUtil.builder();
        if (StrUtil.isNotBlank(apiName)) {
            logReqSb.append(apiName);
            logRespSb.append(apiName);
        }
        logReqSb.append("req执行步骤 ");
        logReqSb.append(stepInt);

        logRespSb.append("resp执行步骤 ");
        logRespSb.append(stepInt);

        if (logPpType == LogAllTypes.LogPpType.REQ_TO_UP) {
            logReqSb.append(reqSymbolStr);
            logReqSb.append(reqStr);
            logReqSb.append(startStr);
            logReqSb.append(reqSymbolStr);

            logRespSb.append(reqSymbolStr);
            logRespSb.append(reqStr);
            logRespSb.append(endStr);
            logRespSb.append(reqSymbolStr);
        }
        if (logPpType == LogAllTypes.LogPpType.RESP_FROM_UP) {
            logReqSb.append(respSymbolStr);
            logReqSb.append(respStr);
            logReqSb.append(startStr);
            logReqSb.append(respSymbolStr);

            logRespSb.append(respSymbolStr);
            logRespSb.append(respStr);
            logRespSb.append(endStr);
            logRespSb.append(respSymbolStr);
        }
        if (logPpType == LogAllTypes.LogPpType.RETURN_TO_DOWN) {
            logReqSb.append(reqSymbolStr);
            logReqSb.append(returnStr);
            logReqSb.append(startStr);
            logReqSb.append(reqSymbolStr);

            logRespSb.append(reqSymbolStr);
            logRespSb.append(returnStr);
            logRespSb.append(endStr);
            logRespSb.append(reqSymbolStr);
        }
        if (logPpType == LogAllTypes.LogPpType.RECEIVE_FROM_DOWN) {
            logReqSb.append(respSymbolStr);
            logReqSb.append(receiveStr);
            logReqSb.append(startStr);
            logReqSb.append(respSymbolStr);

            logRespSb.append(respSymbolStr);
            logRespSb.append(receiveStr);
            logRespSb.append(endStr);
            logRespSb.append(respSymbolStr);
        }

        if (logPpType == LogAllTypes.LogPpType.REQ_TO_UP_START) {
            logReqSb.append(reqSymbolStr);
            logReqSb.append(reqStr);
            logReqSb.append(startStr);
            logReqSb.append(reqSymbolStr);
        }

        if (logPpType == LogAllTypes.LogPpType.REQ_TO_UP_END) {
            logRespSb.append(reqSymbolStr);
            logRespSb.append(reqStr);
            logRespSb.append(endStr);
            logRespSb.append(reqSymbolStr);

        }

        if (logPpType == LogAllTypes.LogPpType.RESP_FROM_UP_START) {
            logReqSb.append(respSymbolStr);
            logReqSb.append(respStr);
            logReqSb.append(startStr);
            logReqSb.append(respSymbolStr);
        }

        if (logPpType == LogAllTypes.LogPpType.RESP_FROM_UP_END) {
            logRespSb.append(respSymbolStr);
            logRespSb.append(respStr);
            logRespSb.append(endStr);
            logRespSb.append(respSymbolStr);
        }
        return logReqSb + logToJsonStr(obj) + logRespSb;
    }

    /**
     * 打印日志json 格式
     * https://www.cnblogs.com/candlia/p/11919878.html
     * SerializerFeature.DisableCircularReferenceDetect:消除循环引用
     * SerializerFeature.PrettyFormat：格式化输出
     */
    private static String logToJsonStr(Object logObj){
        return FastJsonUtil.toJsonStringNoDisableCircular(logObj);
    }

    private static LogAllTypes.LogPpType getLogPpType(int stepInt) {
        LogAllTypes.LogPpType logPpType = null;
        switch (stepInt) {
            case 1:
                logPpType = LogAllTypes.LogPpType.RECEIVE_FROM_DOWN;
                break;
            case 2:
                logPpType = LogAllTypes.LogPpType.REQ_TO_UP;
                break;
            case 3:
                logPpType = LogAllTypes.LogPpType.RESP_FROM_UP;
                break;
            case 4:
                logPpType = LogAllTypes.LogPpType.RETURN_TO_DOWN;
                break;
            //----------------------------------------------------------------
            case 21:
                logPpType = LogAllTypes.LogPpType.REQ_TO_UP_START;
                break;
            case 22:
                logPpType = LogAllTypes.LogPpType.REQ_TO_UP_END;
                break;
            case 31:
                logPpType = LogAllTypes.LogPpType.RESP_FROM_UP_START;
                break;
            case 32:
                logPpType = LogAllTypes.LogPpType.RESP_FROM_UP_END;
                break;

            default:
                break;
        }
        return logPpType;
    }


    private static final String reqSymbolStr = "->->";
    private static final String respSymbolStr = "<-<-";
    private static final String reqStr = "请求";
    private static final String respStr = "返回";

    private static final String receiveStr = "接收";

    private static final String returnStr = " 回复";
    private static final String startStr = "开始";
    private static final String endStr = "结束";

}
