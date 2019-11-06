package com.lins.xuthing.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/11/6.
 */

public class CommonUtil {

    public static String getTime(boolean b){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(b?"yyyy-MM-dd":"yyyyMMdd");
        Date curDate = new Date();
        Log.e("date",curDate.toString());
        String str = format.format(curDate);
        return str;
    }
    public static String getTimeLong(boolean b) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd-HH-mm-ss" : "yyyyMMddHHmmss");
        Date curDate = new Date();
        Log.e("date", curDate.toString());
        String str = format.format(curDate);
        return str;
    }

    //更新软件
    public static void installApk(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }
        Lg.e("获得文件路径："+apkPath);

        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            Lg.e(">=24时");
//            Log.v(TAG,"7.0以上，正在安装apk...");
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context,
                    context.getPackageName()+".provider",
//                    BuildConfig.APPLICATION_ID + ".provider",
                    file);
//            Uri apkUri = FileProvider.getUriForFile(context, "com.fangzuo.assist.fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            Lg.e("<24时");
//            Log.v(TAG,"7.0以下，正在安装apk...");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }


    public static Date addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }
    //根据指定时间，加或减天数后，返回时间
    public static String dealCreateDate(String time,int end){
        if ("".equals(time))return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
        Date date = null; // 指定日期
        try {
//            date = dateFormat.parse("2019-08-10");
            date = dateFormat.parse(time);
            Date newDate = addDate(date, end); // 指定日期加上20天
            return dateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

//        System.out.println(dateFormat.format(date));// 输出格式化后的日期
//        System.out.println(dateFormat.format(newDate));
    }


    //读取本地下载好的txt数据包，解析
    public static String getString(String txtName) {
        String lineTxt = null;
        StringBuilder builder = new StringBuilder();
        try {

            File file = new File(txtName);
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                while ((lineTxt = br.readLine()) != null) {
                    lineTxt+=br.readLine();
                    Lg.e("读取txt:"+lineTxt);
                    if (!"".equals(lineTxt)){
                        builder.append(lineTxt);
//                        Lg.e("读取txt2:"+builder.toString());
                    }
                }
                br.close();
                return builder.toString().substring(0,builder.toString().length()-4);
            } else {
                System.out.println("文件不存在!");
            }

//            File f = new File(txtName);
//            //以防有中文名路径，中文路径里面的空格会被"%20"代替
//            txtName = java.net.URLDecoder.decode(txtName, "utf-8");
//
//            FileInputStream redis = new FileInputStream(f);
////            br = new BufferedReader(new InputStreamReader(redis));
//
//            InputStream inputStream = App.getContext().getResources().getAssets().open(txtName);
//            byte[] arrayOfByte = new byte[inputStream.available()];
//            inputStream.read(arrayOfByte);
//            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineTxt;
    }
}
