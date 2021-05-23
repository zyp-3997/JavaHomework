
@[toc]

## 1.（必做）整合你上次作业的 httpclient/okhttp； 

```java
  private CloseableHttpAsyncClient httpclient;
httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response,context) -> 6000)
                .build();
        httpclient.start();
```
```java
   public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }
```
```java
private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);
        //httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("mao", inbound.headers().get("mao"));

        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse endpointResponse) {
                try {
                    handleResponse(inbound, ctx, endpointResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
```

## 2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210523170148633.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210523170209366.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210523170220905.png)


## 3.（必做）实现过滤器。

```java
public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("pp", "soul");
    }
}
```

```java
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("zyp", "java-1-nio");
    }
}
```

## 4.（选做）实现路由。

```java
 String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802,http://localhost:8803");
```

```java
   public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }
```

采用轮询算法
```java
 int count = 0;
    public String route(List<String> urls) {
        count++;
        int size = urls.size();
        if (count >= size) {
            count = 0;
        }
        return urls.get(count);
    }
```

## 5.（选做）跑一跑课上的各个例子，加深对多线程的理解
多线程并发访问共享变量的问题

```java
public class Counter {

    public final static int A=10;

    public static int B=10;

    private  int sum = 0;
    public  void incr() {
        sum=sum+1;
    }
    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 100000;

        // test single thread
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }
        System.out.println("single thread: " + counter.getSum());

        // test multiple threads
        final Counter counter2 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);
//        while (Thread.activeCount()>2){//当前线程的线程组中的数量>2
//            Thread.yield();
//        }
        System.out.println("multiple threads: " + counter2.getSum());


    }
}

```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210523161721876.png)

在方法上添加synchronized关键字解决

```java
 public synchronized void incr() {
        sum=sum+1;
    }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210523161642536.png)


## 6.（选做）完善网关的例子，试着调整其中的线程池参数
设置了最大线程数为核心数的2倍
```java
      int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
proxyService = new ThreadPoolExecutor(cores, cores*2,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
```

