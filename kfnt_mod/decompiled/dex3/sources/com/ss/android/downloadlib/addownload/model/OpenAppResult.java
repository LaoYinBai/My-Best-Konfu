package com.ss.android.downloadlib.addownload.model;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OpenAppResult {

    @Message
    private int message;

    @Source
    private String source;

    @Type
    private int type;

    public @interface Message {
        public static final int DEFAULT_MARKET_UNINSTALLED = 15;
        public static final int LAUNCH_INTENT_NOT_EXIST = 22;
        public static final int MARKET_UNINSTALLED = 13;
        public static final int OPEN_URL_INVALID = 24;
        public static final int OPEN_URL_NOT_EXIST = 21;
        public static final int PACKAGE_NAME_EMPTY = 11;
        public static final int START_ACTIVITY_EXCEPTION = 23;
        public static final int START_EXCEPTION = 14;
        public static final int URI_ERROR = 12;
    }

    public @interface Source {
        public static final String AM_HW = "am_hw";
        public static final String AM_KLLK1 = "am_kllk1";
        public static final String AM_KLLK2 = "am_kllk2";
        public static final String AM_M1 = "am_m1";
        public static final String AM_M2 = "am_m2";
        public static final String AM_V1 = "am_v1";
    }

    public @interface Type {
        public static final int FAILED_BY_PACKAGE = 4;
        public static final int FAILED_BY_URL = 2;
        public static final int FAILED_MARKET = 6;
        public static final int IGNORE_MARKET = 7;
        public static final int NONE = 0;
        public static final int SUCCESS_BY_PACKAGE = 3;
        public static final int SUCCESS_BY_URL = 1;
        public static final int SUCCESS_MARKET = 5;
    }

    public OpenAppResult(@Type int i) {
        this(i, 0, null);
    }

    public OpenAppResult(@Type int i, @Message int i2) {
        this(i, i2, null);
    }

    public OpenAppResult(@Type int i, @Source String str) {
        this(i, 0, str);
    }

    public OpenAppResult(@Type int i, @Message int i2, @Source String str) {
        this.type = i;
        this.message = i2;
        this.source = str;
    }

    public int getType() {
        return this.type;
    }

    public int getMessage() {
        return this.message;
    }

    public String getSource() {
        return this.source;
    }

    public void setType(@Type int i) {
        this.type = i;
    }

    public void setMessage(@Message int i) {
        this.message = i;
    }

    public void setSource(@Source String str) {
        this.source = str;
    }
}
