import java.util.Base64;

/**
 * @author 朱雨鹏
 * @create 2021-05-09 15:21
 */
public class HelloClassLoader extends ClassLoader{
    public static void main(String[]args){
        try {
            new HelloClassLoader().findClass("Advance.week01.Hello").newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException{
       // String helloBase64="yv66vgAAADcAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAWTEFkdmFuY2Uvd2VlazAxL0hlbGxvOwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEACkhlbGxvLmphdmEMAAcACAcAGQwAGgAbAQALaGVsbG8gaW5pdCEHABwMAB0AHgEAFEFkdmFuY2Uvd2VlazAxL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAvAAEAAQAAAAUqtwABsQAAAAIACgAAAAYAAQAAAAIACwAAAAwAAQAAAAUADAANAAAACAAOAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAABAAIAAUAAQAPAAAAAgAQ";
        String helloBase64="yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAWTEFkdmFuY2Uvd2VlazAxL0hlbGxvOwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEACkhlbGxvLmphdmEMAAcACAcAGQwAGgAbAQALaGVsbG8gaW5pdCEHABwMAB0AHgEAFEFkdmFuY2Uvd2VlazAxL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAvAAEAAQAAAAUqtwABsQAAAAIACgAAAAYAAQAAAAIACwAAAAwAAQAAAAUADAANAAAACAAOAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAABAAIAAUAAQAPAAAAAgAQ";
        byte[] bytes=decode(helloBase64);
        System.out.println(bytes);
        return defineClass(name,bytes,0,bytes.length);

    }
    private byte[] decode(String helloBase64) {
        return Base64.getDecoder().decode(helloBase64);
    }
}
