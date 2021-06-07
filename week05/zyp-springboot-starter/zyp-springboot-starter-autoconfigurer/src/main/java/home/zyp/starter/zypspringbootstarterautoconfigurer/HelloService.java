package home.zyp.starter.zypspringbootstarterautoconfigurer;

/**
 * @author 朱雨鹏
 * @create 2021-06-07 11:16
 */
public class HelloService {
    HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
    public String sayHello(String name){
        return helloProperties.getPrefix()+"-"+name+"-"+helloProperties.getSuffix();
    }
}
