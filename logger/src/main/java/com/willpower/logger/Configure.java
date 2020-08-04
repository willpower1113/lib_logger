//package com.willpower.logger;
//
///**
// * logLevel - 日志过滤级别 ASSERT > ERROR > WARN > INFO > DEBUG > VERBOSE
// * logFileDir  -- 日志文件夹 【不包含SD卡路径】
// * logFilePrefix  --  日志文件名前缀【完整文件名为 logFilePrefix + _MMdd.log】
// * matchPackage -- 打印栈信息时 匹配包名
// * filterClass  -- 打印栈信息时 屏蔽的类
// * <p>
// * 日志设置类
// */
//public class Configure {
//    /*日志过滤级别 ASSERT > ERROR > WARN > INFO > DEBUG > VERBOSE  默认打印所有日志*/
//    protected int logLevel;
//    /*打印栈信息时匹配包名*/
//    protected String matchPackage;
//    /*打印栈信息时屏蔽的类名*/
//    protected String filterClass;
//    /*日志文件名前缀*/
//    protected String logFilePrefix;
//    /*日志目录*/
//    protected String logDirectory;
//    /*公共TAG，当TAG为缺省时默认 globalTag*/
//    protected String globalTag = "Android";
//
//    public static Configure create() {
//        return new Configure();
//    }
//
//    public Configure logLevel(int logLevel) {
//        this.logLevel = logLevel;
//        return this;
//    }
//
//    public Configure matchPackage(String matchPackage) {
//        this.matchPackage = matchPackage;
//        return this;
//    }
//
//    public Configure filterClass(Class filterClass) {
//        this.filterClass = filterClass == null ? null : filterClass.getName();
//        return this;
//    }
//
//    public Configure logFilePrefix(String logFilePrefix) {
//        this.logFilePrefix = logFilePrefix;
//        return this;
//    }
//
//    public Configure logDirectory(String logDirectory) {
//        this.logDirectory = FileUtils.sdcardAbsolutePath() + (logDirectory == null ? "Log" : logDirectory);
//        return this;
//    }
//
//    public Configure globalTag(String globalTag) {
//        this.globalTag = globalTag;
//        return this;
//    }
//
//}
