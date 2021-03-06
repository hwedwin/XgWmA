package com.xgwma.app.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *异常类:捕获全局异常
 */
public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
    Context mContext;

    public DefaultExceptionHandler(Context act) {
        mContext = act;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        sendCrashReport(ex);
       //CrashLogCat 崩溃日志
        Log.e("CrashLogCat", ex.getMessage(), ex);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }

        handleException();

    }

    private void sendCrashReport(Throwable ex) {

        File file = mContext.getExternalFilesDir("LYH_CRASH");

        if (!file.exists()) {
            file.mkdirs();
        }

        File outFile = new File(file, getCurProcessName(mContext) + SystemClock.elapsedRealtime());

        try {
            outFile.createNewFile();
            saveCrashInfo2File(outFile, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleException() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }


    private void saveCrashInfo2File(File file, Throwable ex) throws IOException {

        StringBuffer sb = new StringBuffer();

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(sb.toString().getBytes());
        fos.close();
    }

}