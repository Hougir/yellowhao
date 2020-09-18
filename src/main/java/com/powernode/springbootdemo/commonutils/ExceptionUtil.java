package com.powernode.springbootdemo.commonutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @PACKAGE_NAME: com.powernode.springbootdemo.commonutils
 * @NAME: ExceptionUtil
 * @AUTHOR: 如意郎君
 * @DATE: 2020/9/16
 * @TIME: 16:10
 * @DAY_NAME_SHORT: 星期三
 * @VERSION: 1.0
 *
 * 日志输出
 */
public class ExceptionUtil {


    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

}
