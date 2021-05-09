import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 朱雨鹏
 * @create 2021-05-09 15:53
 */
public class MyClassLoader extends ClassLoader{
    public static void main(String[]args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //创建类加载器
        ClassLoader classLoader = new MyClassLoader();
        //得到Class
        Class<?> clazz = classLoader.loadClass("Hello");
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName()+"."+declaredMethod.getName());
        }
        //利用发射获得构造器，对象实例，方法
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod("hello");
        //执行方法
        method.invoke(instance);
    }
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException{
        String replace = name.replace(".", "/");
        //获得输入流
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(replace+".xlass");
        try {
            int length = inputStream.available();
            byte[]bytes=new byte[length];
            inputStream.read(bytes);
            //转换
            byte[] res=decode(bytes);
            return defineClass(name,res,0,res.length);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private byte[] decode(byte[] bytes) {
        byte[]res=new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            res[i]=(byte)(255-bytes[i]);
        }
        return res;
    }
}
