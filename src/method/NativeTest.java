package method;

/**
 * @Author: Rita
 * @Date:4/23/2021 5:09 PM
 */
public class NativeTest {
      static
    {
        System.loadLibrary("HelloNative");
    }

    public static native void sayHello();

    @SuppressWarnings("static-access")
    public static void main(String[] args){
        new NativeTest().sayHello();
    }
    /**
     javac HelloNative.java
     javah HelloNative
     /%JAVA_HOME%include -->jni.h
     gcc -m64 -Wl,--add-stdcall-alias -I  -->dll
     * */


}
