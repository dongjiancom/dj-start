
package dj.com.util.log;

/**
 * @author jiandong
 * @date 2023/06/08  15:30
 */
public interface LogAllTypes {
    /**
     * 日志类型
     */
    enum LogPpType {
        /**
         * 请求上游
         */
        REQ_TO_UP,

        /**
         * 上游响应
         */
        RESP_FROM_UP,

        /**
         * 接收下游的请求
         */
        RECEIVE_FROM_DOWN,

        /**
         * 返回给下游
         */
        RETURN_TO_DOWN,

        //不打印具体报文
        /**
         * 请求上游 开始
         */
        REQ_TO_UP_START,

        /**
         * 请求上游 结束
         */
        REQ_TO_UP_END,

        /**
         * 上游响应 开始
         */
        RESP_FROM_UP_START,

        /**
         * 上游响应 结束
         */
        RESP_FROM_UP_END

    }
}
