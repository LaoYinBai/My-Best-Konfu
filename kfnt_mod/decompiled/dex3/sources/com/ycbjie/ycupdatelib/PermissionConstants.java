package com.ycbjie.ycupdatelib;

import android.annotation.SuppressLint;
import com.umeng.message.MsgConstant;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"InlinedApi"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PermissionConstants {
    public static final String CALENDAR = "android.permission-group.CALENDAR";
    public static final String CAMERA = "android.permission-group.CAMERA";
    public static final String CONTACTS = "android.permission-group.CONTACTS";
    private static final String[] GROUP_CALENDAR = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
    private static final String[] GROUP_CAMERA = {"android.permission.CAMERA"};
    private static final String[] GROUP_CONTACTS = {"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"};
    private static final String[] GROUP_LOCATION = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final String[] GROUP_MICROPHONE = {"android.permission.RECORD_AUDIO"};
    private static final String[] GROUP_PHONE = {MsgConstant.PERMISSION_READ_PHONE_STATE, "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.ANSWER_PHONE_CALLS", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS"};
    private static final String[] GROUP_SENSORS = {"android.permission.BODY_SENSORS"};
    private static final String[] GROUP_SMS = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"};
    private static final String[] GROUP_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE};
    public static final String LOCATION = "android.permission-group.LOCATION";
    public static final String MICROPHONE = "android.permission-group.MICROPHONE";
    public static final String PHONE = "android.permission-group.PHONE";
    public static final String SENSORS = "android.permission-group.SENSORS";
    public static final String SMS = "android.permission-group.SMS";
    public static final String STORAGE = "android.permission-group.STORAGE";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Permission {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String[] getPermissions(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 0
            r2 = 1
            switch(r0) {
                case -1639857183: goto L5b;
                case -1410061184: goto L51;
                case -1250730292: goto L47;
                case -1140935117: goto L3d;
                case 421761675: goto L33;
                case 828638019: goto L29;
                case 852078861: goto L1e;
                case 1581272376: goto L14;
                case 1795181803: goto La;
                default: goto L9;
            }
        L9:
            goto L65
        La:
            java.lang.String r0 = "android.permission-group.SMS"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 7
            goto L66
        L14:
            java.lang.String r0 = "android.permission-group.MICROPHONE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 4
            goto L66
        L1e:
            java.lang.String r0 = "android.permission-group.STORAGE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 8
            goto L66
        L29:
            java.lang.String r0 = "android.permission-group.LOCATION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 3
            goto L66
        L33:
            java.lang.String r0 = "android.permission-group.SENSORS"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 6
            goto L66
        L3d:
            java.lang.String r0 = "android.permission-group.CAMERA"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 1
            goto L66
        L47:
            java.lang.String r0 = "android.permission-group.CALENDAR"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 0
            goto L66
        L51:
            java.lang.String r0 = "android.permission-group.PHONE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 5
            goto L66
        L5b:
            java.lang.String r0 = "android.permission-group.CONTACTS"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L65
            r0 = 2
            goto L66
        L65:
            r0 = -1
        L66:
            switch(r0) {
                case 0: goto L86;
                case 1: goto L83;
                case 2: goto L80;
                case 3: goto L7d;
                case 4: goto L7a;
                case 5: goto L77;
                case 6: goto L74;
                case 7: goto L71;
                case 8: goto L6e;
                default: goto L69;
            }
        L69:
            java.lang.String[] r0 = new java.lang.String[r2]
            r0[r1] = r3
            return r0
        L6e:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_STORAGE
            return r3
        L71:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_SMS
            return r3
        L74:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_SENSORS
            return r3
        L77:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_PHONE
            return r3
        L7a:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_MICROPHONE
            return r3
        L7d:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_LOCATION
            return r3
        L80:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_CONTACTS
            return r3
        L83:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_CAMERA
            return r3
        L86:
            java.lang.String[] r3 = com.ycbjie.ycupdatelib.PermissionConstants.GROUP_CALENDAR
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ycbjie.ycupdatelib.PermissionConstants.getPermissions(java.lang.String):java.lang.String[]");
    }
}
