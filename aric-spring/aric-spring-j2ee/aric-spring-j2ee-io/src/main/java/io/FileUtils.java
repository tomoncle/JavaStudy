package io;

import java.io.*;

/**
 * Created by tom.lee on 2016/3/7.
 */
public final class FileUtils {
    /**
     * byte数组大小
     */
    private static  final int CACHE_SIZE=1024;


    /**
     * 枚举类型
     */
    public enum FILE_TYPE {
        TEXT,  BINARY;
        FILE_TYPE(){
        }
    }


    /**
     * 复制文件
     * @param src 文件地址
     * @param destination 目标地址
     * @param fileType 文件类型
     * @param readerSize 读取大小
     */
    public static void copyFile(String src,String destination,FILE_TYPE fileType,int readerSize){
        if(fileType.equals(FILE_TYPE.BINARY)){
           copyBinary(src, destination, readerSize);
        }
        if(fileType.equals(FILE_TYPE.TEXT)){
           copyText(src,destination);
        }
    }


    /**
     * 复制文件
     * @param src 文件地址
     * @param destination 目标地址
     * @param fileType 文件类型
     */
    public static void copyFile(String src,String destination,FILE_TYPE fileType){
        copyFile(src,destination,fileType,CACHE_SIZE);
    }


    /**
     * 复制文本
     * @param src 原地址
     * @param destination 目标地址
     */
     static void copyText(String src,String destination){
        BufferedWriter bufferedWriter=null;
        BufferedReader bufferedReader=null;
        try {
            FileReader fileReader=new FileReader(new File(src));
            FileWriter fileWriter=new FileWriter(new File(destination));
            bufferedReader=new BufferedReader(fileReader);
            bufferedWriter=new BufferedWriter(fileWriter);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    /**
     * 复制二进制文件
     * @param src
     * @param destination
     * @param readerSize
     */
    static void copyBinary(String src,String destination,int readerSize){
        BufferedInputStream bufferedInputStream= null;
        BufferedOutputStream bufferedOutputStream= null;

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(src));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(destination));
            bufferedInputStream=new BufferedInputStream(fileInputStream);
            bufferedOutputStream =new BufferedOutputStream(fileOutputStream);
            byte [] b=new byte[readerSize];
            int len;
            while ((len=bufferedInputStream.read(b))!=-1){
                bufferedOutputStream.write(b,0,len);
                bufferedOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bufferedOutputStream!=null){
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedInputStream!=null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制文件
     * @param src 文件地址
     * @param destination 目标地址
     */
    public static void copyFile(String src,String destination){
        copyBinary(src,destination,CACHE_SIZE);
    }


    /**
     * 空字符验证
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str==null||str.length()==0;
    }



    public static  void  main(String [] a){
//       copyFile("C:\\Users\\Administrator\\Desktop\\hadoop-1伪集群搭建.txt"
//               ,"hadoop伪集群搭建0.txt", FILE_TYPE.TEXT);

//        BufferedInputStream bufferedInputStream= null;
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream(new File("hadoop伪集群搭建.txt"));
//            bufferedInputStream=new BufferedInputStream(fileInputStream);
//            byte [] b=new byte[1024];
//            int len;
//            while ((len=bufferedInputStream.read(b))!=-1){
//                System.out.print(new String(b,0,len));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(bufferedInputStream!=null){
//                try {
//                    bufferedInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        writer();
    }


    public static void writer(){
        RandomAccessFile randomAccessFile=null;
        try {
            randomAccessFile=new RandomAccessFile(new File("hadoop伪集群搭建0.txt"),"rw");
            byte r[]=new byte[1024];
            int len;
            while ((len=randomAccessFile.read(r))!=-1){
                String string =new String(r,0,len);
                string=string.replaceAll("hadoop","openStack");
                System.err.print(string);

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
