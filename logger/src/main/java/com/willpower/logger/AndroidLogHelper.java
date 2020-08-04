package com.willpower.logger;

import android.util.Log;

import java.io.File;
import java.util.List;

import static android.util.Log.ASSERT;
import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;
import static android.util.Log.getStackTraceString;

/*
 * 日志工具类
 */
public class AndroidLogHelper {
    public static final String TAG = "Android_Log_Helper";
    /*日志过滤级别 ASSERT > ERROR > WARN > INFO > DEBUG > VERBOSE  默认打印所有日志*/
    protected int logLevel;
    /*打印栈信息时匹配包名*/
    protected String matchPackage;
    /*打印栈信息时屏蔽的类名*/
    protected String filterClass;
    /*日志文件名前缀*/
    protected String logFilePrefix;
    /*日志目录*/
    protected String logDirectory;
    /*公共TAG，当TAG为缺省时默认 globalTag*/
    protected String globalTag = "Android";

    public void logLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void matchPackage(String matchPackage) {
        this.matchPackage = matchPackage;
    }

    public void filterClass(Class filterClass) {
        this.filterClass = filterClass == null ? null : filterClass.getName();
    }

    public void logFilePrefix(String logFilePrefix) {
        this.logFilePrefix = logFilePrefix;
    }

    public void logDirectory(String logDirectory) {
        this.logDirectory = FileUtils.sdcardAbsolutePath() + (logDirectory == null ? "Log" : logDirectory);
    }

    public void globalTag(String globalTag) {
        this.globalTag = globalTag;
    }

    private AndroidLogHelper() {
    }

    public static AndroidLogHelper createLogger() {
        return new AndroidLogHelper();
    }

    public int v(String msg) {
        return println(VERBOSE, false, this.globalTag, msg);
    }

    public int v(String tag, String msg) {
        return println(VERBOSE, false, tag, msg);
    }

    public int v(String msg, Throwable tr) {
        return println(VERBOSE, false, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int v(String tag, String msg, Throwable tr) {
        return println(VERBOSE, false, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int d(String msg) {
        return println(DEBUG, false, this.globalTag, msg);
    }

    public int d(String tag, String msg) {
        return println(DEBUG, false, tag, msg);
    }

    public int d(String msg, Throwable tr) {
        return println(DEBUG, false, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int d(String tag, String msg, Throwable tr) {
        return println(DEBUG, false, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int i(String msg) {
        return println(INFO, false, this.globalTag, msg);
    }

    public int i(String tag, String msg) {
        return println(INFO, false, tag, msg);
    }

    public int i(String msg, Throwable tr) {
        return println(INFO, false, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int i(String tag, String msg, Throwable tr) {
        return println(INFO, false, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int w(String msg) {
        return println(WARN, false, this.globalTag, msg);
    }

    public int w(String tag, String msg) {
        return println(WARN, false, tag, msg);
    }

    public int w(String msg, Throwable tr) {
        return println(WARN, false, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int w(String tag, String msg, Throwable tr) {
        return println(WARN, false, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int e(String msg) {
        return println(ERROR, false, this.globalTag, msg);
    }

    public int e(String msg, Throwable tr) {
        return println(ERROR, false, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int e(String tag, String msg) {
        return println(ERROR, false, tag, msg);
    }

    public int e(String tag, String msg, Throwable tr) {
        return println(ERROR, false, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int wtf(String msg) {
        return println(ASSERT, false, this.globalTag, msg);
    }

    public int wtf(String msg, Throwable tr) {
        return println(ASSERT, false, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int wtf(String tag, String msg) {
        return println(ASSERT, false, tag, msg);
    }

    public int wtf(String tag, String msg, Throwable tr) {
        return println(ASSERT, false, tag, msg + '\n' + getStackTraceString(tr));
    }


    public int vToFile(String msg) {
        return println(VERBOSE, true, this.globalTag, msg);
    }

    public int vToFile(String msg, Throwable tr) {
        return println(VERBOSE, true, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }


    public int vToFile(String tag, String msg) {
        return println(VERBOSE, true, tag, msg);
    }

    public int vToFile(String tag, String msg, Throwable tr) {
        return println(VERBOSE, true, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int dToFile(String msg) {
        return println(DEBUG, true, this.globalTag, msg);
    }

    public int dToFile(String msg, Throwable tr) {
        return println(DEBUG, true, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int dToFile(String tag, String msg) {
        return println(DEBUG, true, tag, msg);
    }

    public int dToFile(String tag, String msg, Throwable tr) {
        return println(DEBUG, true, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int iToFile(String msg) {
        return println(INFO, true, this.globalTag, msg);
    }

    public int iToFile(String msg, Throwable tr) {
        return println(INFO, true, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int iToFile(String tag, String msg) {
        return println(INFO, true, tag, msg);
    }

    public int iToFile(String tag, String msg, Throwable tr) {
        return println(INFO, true, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int wToFile(String msg) {
        return println(WARN, true, this.globalTag, msg);
    }

    public int wToFile(String msg, Throwable tr) {
        return println(WARN, true, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int wToFile(String tag, String msg) {
        return println(WARN, true, tag, msg);
    }

    public int wToFile(String tag, String msg, Throwable tr) {
        return println(WARN, true, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int eToFile(String msg) {
        return println(ERROR, true, this.globalTag, msg);
    }

    public int eToFile(String msg, Throwable tr) {
        return println(ERROR, true, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int eToFile(String tag, String msg) {
        return println(ERROR, true, tag, msg);
    }

    public int eToFile(String tag, String msg, Throwable tr) {
        return println(ERROR, true, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int wtfToFile(String msg) {
        return println(ASSERT, true, this.globalTag, msg);
    }

    public int wtfToFile(String msg, Throwable tr) {
        return println(ASSERT, true, this.globalTag, msg + '\n' + getStackTraceString(tr));
    }

    public int wtfToFile(String tag, String msg) {
        return println(ASSERT, true, tag, msg);
    }

    public int wtfToFile(String tag, String msg, Throwable tr) {
        return println(ASSERT, true, tag, msg + '\n' + getStackTraceString(tr));
    }

    /**
     * 输出日志
     */
    public int println(int priority, boolean toFile, String tag, String msg) {
        if (priority < this.logLevel) return -1;
        if (toFile) //输出日志到文件
            FileUtils.writeLogToFile(this.logDirectory, this.logFilePrefix, tag, wrapperContent(msg));
        Log.println(priority, "【" + tag + "】", wrapperContent(msg));
        return 0;
    }

    private String wrapperContent(String msg) {
        msg = Format.formatJson(msg);
        return msg + Format.formatStack(isMatchPackage(), isFilterClass(), this.matchPackage, this.filterClass);
    }

    //是否需要匹配包名
    private boolean isMatchPackage() {
        return this.matchPackage != null;
    }

    //是否需要过滤类名
    private boolean isFilterClass() {
        return this.filterClass != null;
    }

    /**
     * 获取指定时间的日志
     */
    public List<File> getFileByDate(long date) {
        return FileUtils.getLogByDate(date, new File(this.logDirectory));
    }

    /**
     * 删除过期日志
     * @param overdue 过期标识 单位：天
     */
    public void deleteLogByOverdueDay(int overdue) {
        FileUtils.overdueLog(overdue, new File(this.logDirectory));
    }
}
