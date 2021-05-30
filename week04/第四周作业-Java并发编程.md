（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。
**第一种**
```java

public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        ExecutorService executorService= Executors.newCachedThreadPool();
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        // 异步执行 下面方法
        executorService.execute(task);
        Integer integer = task.get();
        System.out.println("异步计算结果为："+integer);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }

    
}

```
**第二种**

```java
public class Demo02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();

        //第一种方式
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        new Thread(task).start();
        Integer integer = task.get();


        System.out.println("异步计算结果为："+integer);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }


}

```


（选做）请思考: 什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪些 因素，对于这些你是怎么理解的?
答：并发就是统一时刻有多个请求过来，高并发就是同一时刻有大量的请求过来。实现高并发高可用系统需要考虑

 - 批量请求过来，可以使用消息队列，进行异步处理，流量控制
 - 采用池化思想，线程池，数据库连接池
 - 采用缓存技术
 - 在数据库层面进行SQL优化，索引优化


