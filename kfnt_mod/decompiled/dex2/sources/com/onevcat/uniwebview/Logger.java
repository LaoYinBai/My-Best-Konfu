package com.onevcat.uniwebview;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class Logger {
    static final int CRITICAL = 80;
    static final int DEBUG = 10;
    static final int INFO = 20;
    static final int OFF = 99;
    static final int VERBOSE = 0;
    private static Logger instance;
    private int level;
    private String tag;

    Logger(String str, int i) {
        this.tag = str;
        this.level = i;
    }

    static Logger getInstance() {
        if (instance == null) {
            instance = new Logger("UniWebView", 80);
        }
        return instance;
    }

    void verbose(String str) {
        log(0, str);
    }

    void debug(String str) {
        log(10, str);
    }

    void info(String str) {
        log(20, str);
    }

    void critical(String str) {
        log(80, str);
    }

    private void log(int i, String str) {
        if (i >= getLevel()) {
            if (i == 80) {
                Log.e(this.tag, "<UniWebView-Android> " + str);
                return;
            }
            Log.d(this.tag, "<UniWebView-Android> " + str);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
        log(99, "Setting logging level to " + i);
    }
}
