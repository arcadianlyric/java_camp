import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/*
自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此 文件内容是一个 Hello.class 文件所有字节(x=255-x)处理后的文件。
 */
public class XlassLoader extends ClassLoader {
    
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class xlass = null;
        String xlassFilename = name + ".xlass";
        File xlassFile = new File(xlassFilename);
        if (xlassFile.exists()) {
            try (FileChannel fileChannel = new FileInputStream(xlassFile).getChannel();) 
            {
                MappedByteBuffer mappedByteBuffer = fileChannel
                    .map(MapMode.READ_ONLY, 0, fileChannel.size());
                byte[] xlassByteE = mappedByteBuffer.array();
                byte[] xlassByte = decode(xlassByteE);
                xlass = defineClass(name, xlassByte, 0, xlassByte.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (xlass == null) {
            throw new ClassNotFoundException(name);
        }
        return xlass;
    }

    // decode by rule
    private static byte[] decode(byte[] xlassByteE) {
        byte[] xlassByte = new byte[xlassByteE.length];
        for (int i = 0; i < xlassByteE.length; i++) {
            xlassByteE[i] = (byte) (255 - xlassByteE[i]);
        }
        return xlassByte;
    }
    
    public static void main(String[] args) throws Exception{
        XlassLoader xlassLoader = new XlassLoader();
        Class<?> xlass = xlassLoader.loadClass(args[0]);
        Method method = xlass.getMethod("hello");
        Object instance = xlass.newInstance();
        method.invoke(instance);
    }
}