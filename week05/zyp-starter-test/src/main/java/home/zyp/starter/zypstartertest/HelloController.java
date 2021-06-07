package home.zyp.starter.zypstartertest;

import home.zyp.starter.zypspringbootstarterautoconfigurer.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱雨鹏
 * @create 2021-06-07 11:45
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello(){
        return helloService.sayHello("朱雨鹏");
    }
}
