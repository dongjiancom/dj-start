1. 多线程（线程池）
2. 异步
3. Future

# CompletableFuture说明
## 会阻塞（主线程）的方法
1. get()、join() —— B4
2. allof(···)、anyof() —— B5

## 重要：CompletableFuture里方法，jvm正常情况的一定会执行。
demo里需要主线程等待，只是因为是main函数执行完会退出JVM，正常的项目无需这么做。 下面的内容仅仅了解就好。

## 不传入自定以的线程池，默认ForkJoinPool（守护线程）
如果在使用 CompletableFuture 时没有传入自定义的线程池，它会默认使用 ForkJoinPool.commonPool() 来执行任务。ForkJoinPool.commonPool() 是一个共享的、全局的 ForkJoinPool 实例，适合处理并行任务和异步计算。

## 守护线程、非守护线程
### 区别
非守护线程：主线程结束后，非守护线程会继续执行，直到它们的任务完成。
守护线程：当所有非守护线程结束后，守护线程也会终止，即使它们的任务没有完成。

### 守护线程（Daemon Thread）
守护线程是程序运行中提供服务的后台线程。当所有非守护线程结束时，JVM 会自动终止所有仍然运行的守护线程。典型的守护线程有垃圾收集器线程。

### 非守护线程（User Thread）
非守护线程是普通的用户线程。只要有一个非守护线程还在运行，JVM 就不会退出。当所有非守护线程结束时，JVM 才会退出。


