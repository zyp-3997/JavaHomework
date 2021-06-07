1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。
代码在项目里面
结果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210607103331118.png)

2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
**通过xml方式注入**
bean.xml
```java
<!-- 1 配置service和dao对象 -->
	<bean id="userDao" class="cn.zyp.UserDao"></bean>
	 <bean id="userService" class="cn.zyp.UserService">
		<property name="userDao" ref="userDao"></property>
	</bean>
```
实体类
```java
public class UserDao {

	public void add() {
		System.out.println("dao.........");
	}
}

public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void add() {
		System.out.println("service.........");
		userDao.add();
	}
}
```
测试类

```java
public class TestIOC {

	@Test
	public void testUser() {
		//1 加载spring配置文件，根据创建对象
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("bean.xml");
		//2 得到配置创建的对象
		UserService userService = (UserService) context.getBean("userService");
		userService.add();
	}
}
```
结果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210606234224701.png)
**通过注解方式**

```java
@Repository
public class UserDao {

	public void add() {
		System.out.println("dao.........");
	}
}
```

```java
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	public void add() {
		System.out.println("service.........");
		userDao.add();
	}
```

3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。
实体类
```java
public class Person {

	private String pname;

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	private String[] arrs;
	private List<String> list;
	private Map<String,String> map;
	private Properties properties;
	
	public void setArrs(String[] arrs) {
		this.arrs = arrs;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}


	public void test1() {
		System.out.println("arrs:"+arrs);
		System.out.println("list:"+list);
		System.out.println("map:"+map);
		System.out.println("properties"+properties);
	}
}
```
配置文件

```java
<bean id="person" class="cn.zyp.property.Person">
		<!-- 数组 -->
		<property name="arrs">
			<list>
				<value>小王</value>
				<value>小马</value>
				<value>小宋</value>
			</list>
		</property>

		<!-- list -->
		<property name="list">
			<list>
				<value>小奥</value>
				<value>小金</value>
				<value>小普</value>
			</list>
		</property>

		<!-- map -->
		<property name="map">
			<map>
				<entry key="aa" value="lucy"></entry>
				<entry key="bb" value="mary"></entry>
				<entry key="cc" value="tom"></entry>
			</map>
		</property>

		<!-- properties -->
		<property name="properties">
			<props>
				<prop key="driverclass">com.mysql.jdbc.Driver</prop>
				<prop key="username">root</prop>
			</props>
		</property>
	</bean>
```


5.（选做）总结一下，单例的各种写法，比较它们的优劣。
饿汉式
创建时创建实例

```java
public class HungrySingle {
    private static final HungrySingle instance=new HungrySingle();
    private HungrySingle(){}
    public static HungrySingle getInstance(){
        return instance;
    }
}
```

懒汉式
用到的时候创建实例
```java
public class LazySingle {
    private static volatile LazySingle instance=null;
    private LazySingle(){
    }
    public static LazySingle getInstance(){
        if (instance==null){
            synchronized (LazySingle.class){
                if (instance==null){
                    instance=new LazySingle();
                }
            }
        }return instance;
    }
}
```

6.（选做）maven/spring 的 profile 机制，都有什么用法？

 - 开发环境，应用需要连接一个可供调试的数据库单机进程
 - 生产环境，应用需要使用正式发布的数据库，通常是高可用的集群
 - 测试环境，应用只需要使用内存式的模拟数据库

7.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。

 - mybatis是一个不完全的orm框架，因为mybatis需要程序员自己写大量的sql，而Hibernate是完全的orm框架，简单的执行语句程序员可以不写sql。
 - Mybatis的学习门槛较低，可严格控制sql的执行性能，灵活度高，适合于对关系数据模型要求不高的软件开发，比如互联网软件、企业运营类软件等。因为这类软件的需求变化快而且多。灵活的前提是无法做到数据库的无关系，如果要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。
 - Hibernate，数据库无关性好，适用于关系数据模型较高的软件，可以节省很多代码，提高工作效率。而且hibernate的性能调优需要很强的经验和能力。

8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
在项目里面

