package com.yidai.sdk;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author yidai
 * @date 2022年2月23日
 */
public class Client {
    /**
     * 根据指定URL将文件上传到服务器
     *
     * @param urlPath   地址
     * @param uploadDir 文件地址
     * @param fileName  文件名
     * @param param     参数
     * @return 返回uuid
     */
    public static String uploadFile(String urlPath, String uploadDir, String fileName, String param) {
        String result = "";
        File file = null;
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL realUrl = new URL(urlPath);
            // 发送POST请求必须设置如下两行
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // 头
            String boundary = BOUNDARY;
            // 传输内容
            StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);
            // 尾
            String endBoundary = "\r\n--" + boundary + "--\r\n";
            //输出
            OutputStream out = connection.getOutputStream();
            // 1. 处理普通表单域(即形如key = value对)的POST请求（这里也可以循环处理多个字段，或直接给json）
            //这里看过其他的资料，都没有尝试成功是因为下面多给了个Content-Type
            //form-data  这个是form上传 可以模拟任何类型
            contentBody.append("\r\n")
                    .append("Content-Disposition: form-data; name=\"")
                    .append("param" + "\"")
                    .append("\r\n")
                    .append("\r\n")
                    .append("\r\n")
                    .append("--")
                    .append(boundary);
            String boundaryMessage1 = contentBody.toString();

            out.write(boundaryMessage1.getBytes("utf-8"));

            // 2. 处理file文件的POST请求（多个file可以循环处理）
            contentBody = new StringBuffer();
            contentBody.append("\r\n")
                    .append("Content-Disposition:form-data; name=\"")
                    .append("file" + "\"; ")   // form中field的名称
                    .append("filename=\"")
                    .append(fileName + "\"")   //上传文件的文件名，包括目录
                    .append("\r\n")
                    .append("Content-Type:multipart/form-data")
                    .append("\r\n\r\n");
            String boundaryMessage2 = contentBody.toString();

            out.write(boundaryMessage2.getBytes("utf-8"));

            // 开始真正向服务器写文件
            String path = uploadDir + File.separatorChar + fileName;
            file = new File(path);
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[(int) file.length()];
            bytes = dis.read(bufferOut);
            out.write(bufferOut, 0, bytes);
            dis.close();
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 4. 从服务器获得回答的内容
            String strLine = "";
            String strResponse = "";
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((strLine = reader.readLine()) != null) {
                strResponse += strLine + "\n";
            }
            System.out.print(strResponse);
            return strResponse;
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 根据指定URL将文件下载到指定目标位置
     *
     * @param urlPath     下载路径
     * @param downloadDir 文件存放目录
     * @param fileName    文件名
     * @return 返回下载文件
     */
    public static File downloadFile(String urlPath, String downloadDir, String fileName) {
        File file = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            //设置超时
            httpURLConnection.setConnectTimeout(1000 * 5);
            //设置请求方式，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 建立链接从请求中获取数据
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            // 指定存放位置
            String path = downloadDir + File.separatorChar + fileName;
            file = new File(path);
            // 校验文件夹目录是否存在，不存在就创建一个目录
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            // 关闭资源
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("文件下载失败！");
        } finally {
            return file;
        }

    }


    /**
     * 向指定的URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @return 远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader bufferedReader = null;
        try {
            //2、将url转变为URL类对象
            URL realUrl = new URL(url);

            //3、打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //4、设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            //5、建立实际的连接
            connection.connect();
            //获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "---->" + map.get(key));
            }

            //6、定义BufferedReader输入流来读取URL的响应内容 ，UTF-8是后续自己加的设置编码格式，也可以去掉这个参数
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = "";
            while (null != (line = bufferedReader.readLine())) {
                result += line;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("发送GET请求出现异常！！！" + e);
            e.printStackTrace();
        } finally {
            //使用finally块来关闭输入流
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return result;
    }
}
