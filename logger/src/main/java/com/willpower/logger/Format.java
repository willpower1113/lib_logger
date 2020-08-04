package com.willpower.logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化工具类
 */
class Format {

    /**
     * 格式化 栈信息
     */
    protected static String formatStack(boolean isFilterPackage, boolean isShieldClass, String filterPackage, String shieldClass) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuffer stack = new StringBuffer();
        if (isFilterPackage) {
            for (StackTraceElement e : stackTrace) {
                if (matchPackage(filterPackage, packageName(e.getClassName()))) {
                    if (isShieldClass) {
                        if (!matchClass(e.getClassName(), shieldClass)) {
                            stack.append(appendLocation(e));
                        }
                    } else {
                        stack.append(appendLocation(e));
                    }
                }
            }
        } else {
            stack.append(appendLocation(stackTrace[5]));
        }
        return stack.toString() + "\n";
    }

    /**
     * 格式化 JSON 日志
     */
    protected static String formatJson(String msg) {
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                return jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                return jsonArray.toString(4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 格式化 文件 日志
     * [DATE] [PID:TID] [TAG] [LOG] =>[STACK]
     */
    protected static String formatFileLog(String DATE, String PID, String TID, String TAG, String LOG) {
        return String.format("\n[%s] [%s:%s] [%s] [%s] \n", DATE, PID, TID, TAG, LOG);
    }

    /**
     * 格式化 日志 文件名 _MMdd.log
     */
    protected static String formatFileLogName() {
        return new SimpleDateFormat(FileUtils.FILE_DATE).format(new Date());
    }

    /**
     * 格式化 日志行 时间
     */
    protected static String formatLogTime() {
        return new SimpleDateFormat(FileUtils.DATE_TIME_FORMAT).format(new Date());
    }

    /**
     * 追加 栈信息
     *
     * @return
     */
    private static String appendLocation(StackTraceElement element) {
        return "\n => at " + element.getClassName() + "." + element.getMethodName() + "() ("
                + element.getFileName() + ":" + element.getLineNumber() + ")";
    }

    private static boolean matchPackage(String filterPackage, String packageName) {
        return filterPackage.equals(packageName);
    }

    private static boolean matchClass(String className, String shieldClass) {
        return shieldClass.equals(className);
    }

    private static String packageName(String className) {
        return className.contains(".") ? className.substring(0, className.lastIndexOf('.')) : className;
    }
}
