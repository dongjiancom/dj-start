package thread;

import java.util.concurrent.CompletableFuture;

/**
 * @author jiandong 2024-07-09 create
 */
public class B3CompletableFuture {
    /**
     * 一个典型使用场景，就是将回调方法转为CompletableFuture，然后再依赖CompletableFure的能力进行调用编排
     */
    @FunctionalInterface
    public interface ThriftAsyncCall {
        void invoke() throws Exception;
    }
    /**
     * 该方法为美团内部rpc注册监听的封装，可以作为其他实现的参照
     * OctoThriftCallback 为thrift回调方法
     * ThriftAsyncCall 为自定义函数，用来表示一次thrift调用（定义如上）
     */
//    public static <T> CompletableFuture<T> toCompletableFuture(final OctoThriftCallback<?,T> callback , ThriftAsyncCall thriftCall) {
//        //新建一个未完成的CompletableFuture
//        CompletableFuture<T> resultFuture = new CompletableFuture<>();
//        //监听回调的完成，并且与CompletableFuture同步状态
//        callback.addObserver(new OctoObserver<T>() {
//            @Override
//            public void onSuccess(T t) {
//                resultFuture.complete(t);
//            }
//            @Override
//            public void onFailure(Throwable throwable) {
//                resultFuture.completeExceptionally(throwable);
//            }
//        });
//        if (thriftCall != null) {
//            try {
//                thriftCall.invoke();
//            } catch (Exception e) {
//                resultFuture.completeExceptionally(e);
//            }
//        }
//        return resultFuture;
//    }
}
