package network;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Rita
 * URL网络编程
 * 1.URL:统一资源定位符，对应着互联网的某一资源地址
 * 2.格式：
 *  http://localhost:8080/examples/beauty.jpg?username=Tom
 *  协议   主机名    端口号  资源地址           参数列表
 */
public class URLTest {

    //URLEncoder.encode, URLDecoder.decode
    @Test
    public  void test() throws Exception {

        String baseURLStr = "http://www.***/file.txt";
        String relativeURLStr = "file.txt";
        URL baseURL = new URL(baseURLStr);
        URL resolvedRelativeURL = new URL(baseURL, relativeURLStr);
        System.out.println("Base URL:" + baseURL);
        System.out.println("Relative URL  String:" + relativeURLStr);
        System.out.println("Resolved Relative URL:" + resolvedRelativeURL);

        //特殊url转换
        String source = "index&^%*a test for 2.5% and  &";
        String encoded = URLEncoder.encode(source, "utf-8");
        String decoded = URLDecoder.decode(encoded, "utf-8");

        System.out.println("Source: " + source);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);

        String source2 = "http://www.***/38.88/40.444";
        String encoded2 = URLEncoder.encode(source2, "utf-8");
        String decoded2 = URLDecoder.decode(encoded2, "utf-8");

        System.out.println("Source2: " + source2);
        System.out.println("Encoded2: " + encoded2);
        System.out.println("Decoded2: " + decoded2);

        String urlStr = "http://www.****.cn";
        String content = getURLContent(urlStr);
        String content2 = getURLContent2(urlStr);
        System.out.println(content);
        System.out.println(content2);
    }
    //url method test
    @Test
    public void test1() {
        try {
            URL url = new URL("http://localhost:8080/examples/beauty.jpg?username=Tom");
            //URL url = new URL("https://www.google.com");
//            public String getProtocol(  )     获取该URL的协议名
            System.out.println(url.getProtocol());
//            public String getHost(  )           获取该URL的主机名
            System.out.println(url.getHost());
//            public String getPort(  )            获取该URL的端口号
            System.out.println(url.getPort());
//            public String getPath(  )           获取该URL的文件路径
            System.out.println(url.getPath());
//            public String getFile(  )             获取该URL的文件名
            System.out.println(url.getFile());
//            public String getQuery(   )        获取该URL的查询名
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    //url send file
    @Test
    public void test2(){
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            //创建一个URI对象
            URL url = new URL("http://localhost:8080/examples/beauty.jpg");

            //要创建具有相对URI字符串的URI，并使用baseURI解析它
            URI baseURI  = new URI("http://www.google.com");
            URI relativeURI = new URI("home.html");
            URI resolvedRelativeURI = baseURI.resolve(relativeURI);

            printURIDetails(baseURI);
            printURIDetails(relativeURI);
            printURIDetails(resolvedRelativeURI);

            //我们可以使用它的toURL()方法从URI对象中获取一个URL对象，如下所示：
            URL  baseURL = baseURI.toURL();

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            is = urlConnection.getInputStream();
            fos = new FileOutputStream("network\\beauty3.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

            System.out.println("下载完成");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }


    }

    public static void printURIDetails(URI uri) {
        System.out.println("URI:" + uri);
        System.out.println("Normalized:" + uri.normalize());
        String parts = "[Scheme=" + uri.getScheme() + ", Authority="
                + uri.getAuthority() + ", Path=" + uri.getPath() + ", Query:"
                + uri.getQuery() + ", Fragment:" + uri.getFragment() + "]";
        System.out.println(parts);
        System.out.println();
    }

    public static String getURLContent(String urlStr) throws Exception {
        BufferedReader br = null;
        URL url = new URL(urlStr);
        InputStream ins = url.openStream();
        br = new BufferedReader(new InputStreamReader(ins));

        StringBuilder sb = new StringBuilder();
        String msg = null;
        while ((msg = br.readLine()) != null) {
            sb.append(msg);
            sb.append("\n"); // Append a new line
        }
        br.close();
        return sb.toString();
    }

    public static String getURLContent2(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        connection.connect();
        OutputStream ous = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ous));
        bw.write("index.html");
        bw.flush();
        bw.close();

        printRequestHeaders(connection);
        InputStream ins = connection.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        StringBuffer sb = new StringBuffer();
        String msg = null;
        while ((msg = br.readLine()) != null) {
            sb.append(msg);
            sb.append("\n"); // Append a new line
        }
        br.close();
        return sb.toString();
    }
    public static void printRequestHeaders(URLConnection connection) {
        Map headers = connection.getHeaderFields();
        System.out.println(headers);
    }
    //get file size, getContentLength();
    @Test
    public void getFileSize()throws Exception{
        int size;
        URL url = new URL("//www.****/***/***/***/newlogo.png");
        URLConnection conn = url.openConnection();
        size = conn.getContentLength();
        if (size < 0)
            System.out.println("无法获取文件大小。");
        else
            System.out.println("文件大小为：" +
                    + size + " bytes");
        conn.getInputStream().close();
    }

    //以下实例演示了如何查看主机指定文件的最后修改时间：
    //getLastModified()
    @Test
    public void fileLastModifyTime() throws Exception {
        URL u = new URL("http://127.0.0.1/java.bmp");
        URLConnection uc = u.openConnection();
        uc.setUseCaches(false);
        long timestamp = uc.getLastModified();
        System.out.println("java.bmp 文件最后修改时间 :"+timestamp);
    }
    //以下实例演示了如何使用 net.URL 类的 URL() 构造函数来抓取网页：
    //将网页源码存在data.html中
    @Test
    public void fetchURL() throws Exception {
        URL url = new URL("https://www.google.com");
        BufferedReader reader = new BufferedReader  (new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter  (new FileWriter("data.html"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    //以下实例演示了如何使用 HttpURLConnection 的 httpCon.getDate() 方法来获取 URL响应头的日期信息：
    @Test
    public void responseHeadersDate() throws Exception {
        URL url = new URL("https://www.google.com");
        HttpURLConnection httpCon =   (HttpURLConnection) url.openConnection();
        long date = httpCon.getDate();
        if (date == 0) {
            System.out.println("无法获取信息。");
        } else {
            System.out.println("Date: " + new Date(date));
        }
    }
    //以下实例演示了如何获取指定 URL 的响应头信息：
    @Test
    public void responseHeaders() throws Exception {
        URL url = new URL("https://www.google.com");
        URLConnection conn = url.openConnection();

        Map headers = conn.getHeaderFields();
        Set<String> keys = headers.keySet();
        for( String key : keys ){
            String val = conn.getHeaderField(key);
            System.out.println(key+"    "+val);
        }
        System.out.println( conn.getLastModified() );
    }


}
