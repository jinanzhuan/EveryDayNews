package com.guigu.test.everydaynews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by shuwei on 2016/8/10.
 */
public class CacheUtils {
    private static final String NAME = "ydzixun";
    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void putString(Context context, String key, String value) {

        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            //1.对应的文件加密
            String fileName=null;
            try {
                fileName = MD5Encoder.encode(key);
                //2.创建文件
                File file=new File(Environment.getExternalStorageDirectory()+"/beijingnews",fileName);
                File parentFile = file.getParentFile();
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                //如果文件不存在
                if(!file.exists()){
                    file.createNewFile();//如果文件不存在，创建文件
                }
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(value.getBytes());
                fos.flush();//强制刷新
                //关闭流
                fos.close();

                /*=====================================================================*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
            sp.edit().putString(key, value).commit();
        }
    }

    public static String getString(Context context, String key) {
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            //1.对应的文件加密
            String fileName=null;
            String result="";
            try {
                fileName = MD5Encoder.encode(key);
                //2.创建文件
                File file=new File(Environment.getExternalStorageDirectory()+"/beijingnews",fileName);
                //如果文件不存在
                if(file.exists()){
                    byte[] buffer=new byte[1024];
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    FileInputStream fis=new FileInputStream(file);
                    int length;
                    while ((length=fis.read(buffer))!=-1){
                        stream.write(buffer,0,length);
                    }
                    //关闭流
                    stream.close();
                    fis.close();
                    result=stream.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }else {
            SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
            return sp.getString(key, "");
        }
    }
}
