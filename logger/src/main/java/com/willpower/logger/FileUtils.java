package com.willpower.logger;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileUtils {
    private static final String TAG = AndroidLogHelper.TAG;
    public static final String DATE_TIME_FORMAT = "HH:mm:ss.SSS";
    public static final String FILE_DATE = "MMdd";

    /**
     * 将日志写入文件
     */
    protected static boolean writeLogToFile(String logPath, String logName, String tag, String msg) {
        if (!hasSdcard()) {
            Log.e(TAG, "The device does not have an SD card!");
            return false;
        }
        File file = new File(logPath, logName + "_" + Format.formatFileLogName() + ".log");
        StringBuilder sbf = new StringBuilder();
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                } else {
                    // 添加文件头注释
                    sbf.append(Format.formatFileLog(DATE_TIME_FORMAT, "PID", "TID",
                            "TAG", "LOG"));
                }
            }
            sbf.append(Format.formatFileLog(Format.formatLogTime(),
                    android.os.Process.myPid() + "",
                    android.os.Process.myTid() + "",
                    tag, msg));
            FileWriter fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sbf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 设备是否有SD卡
     */
    protected static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * sd卡的绝对路径
     */
    protected static String sdcardAbsolutePath() {
        return hasSdcard() ? Environment.getExternalStorageDirectory().getAbsolutePath() : "";
    }

    /**
     * 删除过期日志
     */
    public static void overdueLog(int overdue, File directory) {
        if (directory == null || !directory.exists()) return;
        if (directory.listFiles() == null || directory.listFiles().length == 0) return;
        try {
            int current = dateIntValue(System.currentTimeMillis());
            for (File f : directory.listFiles()) {
                if (current - fileIntValue(f.getName()) >= overdue) {
                    boolean b = f.delete();
                    Log.d(TAG, String.format("[%s] delete: %s", f.getName(), (b ? "success" : "fail")));
                }
            }
        } catch (Exception e) {
        }
    }


    /**
     * 获取某天的日志
     *
     * @param date
     * @return
     */
    public static List<File> getLogByDate(long date, File directory) {
        List<File> files = new ArrayList<>();
        if (directory == null || !directory.exists())
            return files;
        try {
            int current = dateIntValue(date);
            for (File f : directory.listFiles()) {
                if (fileIntValue(f.getName()) == current)
                    files.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    /**
     * 获取文件的Int值 [MMdd]
     */
    private static int fileIntValue(String fileName) throws
            NumberFormatException, IndexOutOfBoundsException {
        return Integer.valueOf(fileName.substring(fileName.lastIndexOf('_') + 1
                , fileName.lastIndexOf('.')));
    }

    /**
     * 获取指定时间的Int值 [MMdd]
     */
    private static int dateIntValue(long mills) throws NumberFormatException {
        return Integer.valueOf(new SimpleDateFormat(FILE_DATE).format(new Date(mills)));
    }
}
