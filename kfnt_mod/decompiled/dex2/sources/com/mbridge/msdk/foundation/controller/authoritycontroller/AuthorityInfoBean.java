package com.mbridge.msdk.foundation.controller.authoritycontroller;

import com.mbridge.msdk.MBridgeConstans;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AuthorityInfoBean {
    public int getAuthGenDataStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_GENERAL_DATA).equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_GENERAL_DATA));
    }

    public int getAuthDeviceIdStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_DEVICE_ID).equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_DEVICE_ID));
    }

    public int getAuthSerialIdStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_SERIAL_ID).equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_SERIAL_ID));
    }

    public int getAuthImsiIdStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b("authority_imsi_id").equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b("authority_imsi_id"));
    }

    public int getAuthOaidStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b(com.mbridge.msdk.a.AUTHORITY_OA_ID).equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b(com.mbridge.msdk.a.AUTHORITY_OA_ID));
    }

    public int getAuthAppListStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b("authority_applist").equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b("authority_applist"));
    }

    public int getAuthAppDownloadStatus() {
        if (com.mbridge.msdk.foundation.a.a.a.a().b("authority_app_download").equals("")) {
            return 1;
        }
        return Integer.parseInt(com.mbridge.msdk.foundation.a.a.a.a().b("authority_app_download"));
    }

    protected final AuthorityInfoBean a(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA, String.valueOf(i));
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID, String.valueOf(i));
        com.mbridge.msdk.foundation.a.a.a.a().a("authority_applist", String.valueOf(i));
        com.mbridge.msdk.foundation.a.a.a.a().a("authority_app_download", String.valueOf(i));
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_SERIAL_ID, String.valueOf(i));
        com.mbridge.msdk.foundation.a.a.a.a().a("authority_imsi_id", String.valueOf(i));
        com.mbridge.msdk.foundation.a.a.a.a().a(com.mbridge.msdk.a.AUTHORITY_OA_ID, String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean b(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA, String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean c(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID, String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean d(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_SERIAL_ID, String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean e(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(com.mbridge.msdk.a.AUTHORITY_OA_ID, String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean f(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a("authority_applist", String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean g(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a("authority_app_download", String.valueOf(i));
        return this;
    }

    protected final AuthorityInfoBean h(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_OTHER, String.valueOf(i));
        return this;
    }
}
