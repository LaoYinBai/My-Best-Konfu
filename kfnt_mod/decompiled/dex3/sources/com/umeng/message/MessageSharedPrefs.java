package com.umeng.message;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointType;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.d;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;
import java.util.Calendar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageSharedPrefs {
    private static final String a = "com.umeng.message.MessageSharedPrefs";
    private static MessageSharedPrefs c = null;
    private static final String d = "tempkey";
    private static final String e = "tempvalue";
    private Context b;
    private SharedPreferences f;
    private int g = 0;

    private MessageSharedPrefs(Context context) {
        this.b = context;
        if (Build.VERSION.SDK_INT > 11) {
            this.g |= 4;
        }
        this.f = context.getSharedPreferences(MsgConstant.PUSH_SHARED_PREFERENCES_FILE_NAME, this.g);
    }

    public static synchronized MessageSharedPrefs getInstance(Context context) {
        if (c == null) {
            c = new MessageSharedPrefs(context);
        }
        return c;
    }

    public boolean hasAppLaunchLogSentToday() {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(m.a(this.b).f());
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d(a, e2.toString());
        }
        Calendar calendar2 = Calendar.getInstance();
        return calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1);
    }

    public void setDisplayNotificationNumber(int i) {
        c(MsgConstant.KEY_NOTIFICATION_NUMBER, i + "");
    }

    public int getDisplayNotificationNumber() {
        return Integer.valueOf(b(MsgConstant.KEY_NOTIFICATION_NUMBER, "1")).intValue();
    }

    public void setMessageAppKey(String str) {
        if (h.d(this.b)) {
            if (str == null || str.equals("")) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 0, "appkey不能为null");
            } else {
                this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, str).commit();
            }
        }
    }

    public void removeMessageAppKey() {
        this.f.edit().remove(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY).commit();
    }

    public String getMessageAppKey() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, "");
    }

    public void setMessageAppSecret(String str) {
        if (h.d(this.b)) {
            if (str == null || str.equals("")) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(a, 0, "appSecret不能为null");
            } else {
                this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, str).commit();
            }
        }
    }

    public void removeMessageAppSecret() {
        this.f.edit().remove(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET).commit();
    }

    public String getMessageAppSecret() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, "");
    }

    public void setMessageChannel(String str) {
        if (h.d(this.b)) {
            this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, str).commit();
            d.a(new Runnable() { // from class: com.umeng.message.MessageSharedPrefs.1
                @Override // java.lang.Runnable
                public void run() {
                    UTrack.getInstance(MessageSharedPrefs.this.b).updateHeader();
                }
            });
        }
    }

    public String getMessageChannel() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, "");
    }

    public void setAppLaunchLogSendPolicy(int i) {
        c(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, i + "");
    }

    public void setDaRegisterSendPolicy(int i) {
        c(MsgConstant.KEY_APP_DAREGISTER_LOG_SEND_POLICY, i + "");
    }

    public int getDaRegisterSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_APP_DAREGISTER_LOG_SEND_POLICY, "-1")).intValue();
    }

    public void setTagSendPolicy(int i) {
        c(MsgConstant.KEY_TAG_SEND_POLICY, i + "");
    }

    public int getAppLaunchLogSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, "-1")).intValue();
    }

    public int getTagSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_TAG_SEND_POLICY, "-1")).intValue();
    }

    public void addAlias(String str, String str2, int i, int i2, String str3) {
        try {
            a(str2, str3);
            String[] strArr = {str, str2, i + ""};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            Cursor cursorQuery = contentResolver.query(a.d, new String[]{"error"}, "alias=? and type=? and exclusive=?", strArr, "time desc");
            if (cursorQuery != null) {
                if (cursorQuery.getCount() > 0) {
                    cursorQuery.moveToFirst();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(l.D, Long.valueOf(System.currentTimeMillis()));
                    contentValues.put("type", str2);
                    contentValues.put("alias", str);
                    contentValues.put(l.A, Integer.valueOf(i));
                    contentValues.put("error", Integer.valueOf(i2));
                    contentValues.put("message", str3);
                    ContentResolver contentResolver2 = this.b.getContentResolver();
                    a.a(this.b);
                    contentResolver2.update(a.d, contentValues, "alias=? and type=? and exclusive=?", strArr);
                } else {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put(l.D, Long.valueOf(System.currentTimeMillis()));
                    contentValues2.put("type", str2);
                    contentValues2.put("alias", str);
                    contentValues2.put(l.A, Integer.valueOf(i));
                    contentValues2.put("error", Integer.valueOf(i2));
                    contentValues2.put("message", str3);
                    ContentResolver contentResolver3 = this.b.getContentResolver();
                    a.a(this.b);
                    contentResolver3.insert(a.d, contentValues2);
                }
            } else {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put(l.D, Long.valueOf(System.currentTimeMillis()));
                contentValues3.put("type", str2);
                contentValues3.put("alias", str);
                contentValues3.put(l.A, Integer.valueOf(i));
                contentValues3.put("error", Integer.valueOf(i2));
                contentValues3.put("message", str3);
                ContentResolver contentResolver4 = this.b.getContentResolver();
                a.a(this.b);
                contentResolver4.insert(a.d, contentValues3);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, String str2) {
        String[] strArr = {str, str2};
        ContentResolver contentResolver = this.b.getContentResolver();
        a.a(this.b);
        contentResolver.delete(a.d, "type=? and message=? ", strArr);
    }

    public void removeAlias(int i, String str, String str2) {
        try {
            String[] strArr = {str2, str, i + ""};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            contentResolver.delete(a.d, "type=? and alias=? and exclusive=? ", strArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String getLastAlias(int i, String str) {
        String string = "";
        try {
            String[] strArr = {str, i + "", Constants.FAIL, "2"};
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            Cursor cursorQuery = contentResolver.query(a.d, new String[]{"alias"}, "type=? and exclusive=? and (error=? or error = ?)", strArr, "time desc");
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                cursorQuery.moveToFirst();
                string = cursorQuery.getString(cursorQuery.getColumnIndex("alias"));
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00f1 A[PHI: r0 r11
  0x00f1: PHI (r0v2 boolean) = (r0v0 boolean), (r0v4 boolean) binds: [B:22:0x00fe, B:14:0x00ef] A[DONT_GENERATE, DONT_INLINE]
  0x00f1: PHI (r11v5 android.database.Cursor) = (r11v4 android.database.Cursor), (r11v10 android.database.Cursor) binds: [B:22:0x00fe, B:14:0x00ef] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isAliasSet(int r11, java.lang.String r12, java.lang.String r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.MessageSharedPrefs.isAliasSet(int, java.lang.String, java.lang.String):boolean");
    }

    public void addTags(String... strArr) {
        for (String str : strArr) {
            String str2 = String.format("UMENG_TAG_%s", str);
            if (!Boolean.valueOf(b(str2, "false")).booleanValue()) {
                c(str2, ITagManager.STATUS_TRUE);
                c("UMENG_TAG_COUNT", (getTagCount() + 1) + "");
            }
        }
    }

    public void removeTags(String... strArr) {
        for (String str : strArr) {
            String str2 = String.format("UMENG_TAG_%s", str);
            if (Boolean.valueOf(b(str2, "false")).booleanValue()) {
                removeKeyAndValue(str2);
                c("UMENG_TAG_COUNT", (getTagCount() - 1) + "");
            }
        }
    }

    public boolean isTagSet(String str) {
        return Boolean.valueOf(b(String.format("UMENG_TAG_%s", str), "false")).booleanValue();
    }

    public int getTagCount() {
        return Integer.valueOf(b("UMENG_TAG_COUNT", Constants.FAIL)).intValue();
    }

    public void add_addTagsInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_ADDTAGS, str);
        }
    }

    public String get_addTagsInterval() {
        return b(MsgConstant.KEY_ADDTAGS, null);
    }

    public void add_deleteTagsInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_DELETETAGS, str);
        }
    }

    public String get_deleteTagsInterval() {
        return b(MsgConstant.KEY_DELETETAGS, null);
    }

    public void add_getTagsInteral(String str) {
        if (str != null) {
            c(MsgConstant.KEY_GETTAGS, str);
        }
    }

    public String get_getTagsInterval() {
        return b(MsgConstant.KEY_GETTAGS, null);
    }

    public void setAddWeightedTagsInterval(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(MsgConstant.KEY_ADD_WEIGHTED_TAGS, str);
    }

    public String getAddWeightedTagsInterval() {
        return b(MsgConstant.KEY_ADD_WEIGHTED_TAGS, null);
    }

    public void setDeleteWeightedTagsInterval(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(MsgConstant.KEY_DELETE_WEIGHTED_TAGS, str);
    }

    public String getDeleteWeightedTagsInterval() {
        return b(MsgConstant.KEY_DELETE_WEIGHTED_TAGS, null);
    }

    public void setListWeightedTagsInterval(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(MsgConstant.KEY_LIST_WEIGHTED_TAGS, str);
    }

    public String getListWeightedTagsInterval() {
        return b(MsgConstant.KEY_LIST_WEIGHTED_TAGS, null);
    }

    public void add_addAliasInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_ADDALIAS, str);
        }
    }

    public String get_addAliasInterval() {
        return b(MsgConstant.KEY_ADDALIAS, null);
    }

    public void add_setAliasInterval(String str) {
        if (str != null) {
            c("setAlias", str);
        }
    }

    public String get_setAliasInterval() {
        return b("setAlias", null);
    }

    public void add_deleteAliasInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_DELETEALIAS, str);
        }
    }

    public String get_deleteALiasInterval() {
        return b(MsgConstant.KEY_DELETEALIAS, null);
    }

    public void setTagRemain(int i) {
        c(MsgConstant.KET_UMENG_TAG_REMAIN, i + "");
    }

    public int getTagRemain() {
        return Integer.valueOf(b(MsgConstant.KET_UMENG_TAG_REMAIN, "64")).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void resetTags() throws java.lang.Throwable {
        /*
            r9 = this;
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            r1.<init>()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            android.content.Context r2 = r9.b     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            android.content.Context r2 = r9.b     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            com.umeng.message.provider.a.a(r2)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            android.net.Uri r4 = com.umeng.message.provider.a.c     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            if (r2 == 0) goto L47
            int r0 = r2.getCount()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            if (r0 <= 0) goto L47
            r2.moveToFirst()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
        L26:
            boolean r0 = r2.isAfterLast()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            if (r0 != 0) goto L47
            java.lang.String r0 = "tempkey"
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            java.lang.String r0 = r2.getString(r0)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            java.lang.String r3 = "UMENG_TAG"
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            if (r3 == 0) goto L41
            r1.add(r0)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
        L41:
            r2.moveToNext()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            goto L26
        L45:
            r0 = move-exception
            goto L79
        L47:
            r0 = 0
            r3 = 0
        L49:
            int r4 = r1.size()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            if (r3 >= r4) goto L6f
            java.lang.String r4 = "tempkey=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            java.lang.Object r6 = r1.get(r3)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            r5[r0] = r6     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            android.content.Context r6 = r9.b     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            android.content.Context r7 = r9.b     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            com.umeng.message.provider.a.a(r7)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            android.net.Uri r7 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            r6.delete(r7, r4, r5)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L82
            int r3 = r3 + 1
            goto L49
        L6f:
            if (r2 == 0) goto L81
            goto L7e
        L72:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L83
        L76:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L79:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L82
            if (r2 == 0) goto L81
        L7e:
            r2.close()
        L81:
            return
        L82:
            r0 = move-exception
        L83:
            if (r2 == 0) goto L88
            r2.close()
        L88:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.MessageSharedPrefs.resetTags():void");
    }

    void a(int i, int i2, int i3, int i4) {
        c(MsgConstant.KEY_NO_DISTURB_START_HOUR, i + "");
        c(MsgConstant.KEY_NO_DISTURB_START_HOUR, i2 + "");
        c(MsgConstant.KEY_NO_DISTURB_END_HOUR, i3 + "");
        c(MsgConstant.KEY_NO_DISTURB_END_MINUTE, i4 + "");
    }

    int a() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_START_HOUR, "23")).intValue();
    }

    int b() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_START_MINUTE, Constants.FAIL)).intValue();
    }

    int c() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_END_HOUR, "7")).intValue();
    }

    int d() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_END_MINUTE, Constants.FAIL)).intValue();
    }

    void e() {
        c(MsgConstant.KEY_ENEABLED, ITagManager.STATUS_TRUE);
    }

    void f() {
        c(MsgConstant.KEY_ENEABLED, "false");
    }

    boolean g() {
        return b(MsgConstant.KEY_ENEABLED, "false").equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    boolean a(String str) {
        return b(MsgConstant.KEY_DEVICE_TOKEN, "").equalsIgnoreCase(str);
    }

    public void setIsEnabled(boolean z) {
        c(MsgConstant.KEY_ISENABLED, String.valueOf(z));
    }

    public boolean isEnabled() throws Throwable {
        String strB = b(MsgConstant.KEY_ISENABLED, "");
        return strB.equalsIgnoreCase(ITagManager.STATUS_TRUE) || strB.equalsIgnoreCase("");
    }

    public boolean hasTransferedCacheFileDataToSQL() {
        return b(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, "false").equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    public boolean finishTransferedCacheFileDataToSQL() {
        return b(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, ITagManager.STATUS_TRUE).equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (cls == null) {
            removeKeyAndValue(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME);
        } else {
            c(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, cls.getName());
        }
    }

    public String getPushIntentServiceClass() throws Throwable {
        String strB = b(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, "");
        if (strB.equalsIgnoreCase("")) {
            return "";
        }
        try {
            Class.forName(strB);
            return strB;
        } catch (ClassNotFoundException unused) {
            return "";
        }
    }

    public boolean hasMessageResourceDownloaded(String str) {
        return b(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str, "false").equals(ITagManager.STATUS_TRUE);
    }

    public void setMessageResourceDownloaded(String str) {
        c(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str, ITagManager.STATUS_TRUE);
    }

    public void removeMessageResouceRecord(String str) {
        removeKeyAndValue(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str);
    }

    public void setLastMessageMsgID(String str) {
        c(MsgConstant.KEY_LAST_MSG_ID, str);
    }

    public String getLastMessageMsgID() {
        return b(MsgConstant.KEY_LAST_MSG_ID, "");
    }

    public void setMuteDuration(int i) {
        c(MsgConstant.KEY_MUTE_DURATION, i + "");
    }

    public int getMuteDuration() {
        return Integer.valueOf(b(MsgConstant.KEY_MUTE_DURATION, PointType.WIND_COMMON)).intValue();
    }

    public void setSerialNo(int i) {
        c(MsgConstant.KEY_SERIA_NO, i + "");
    }

    public int getSerialNo() {
        return Integer.valueOf(b(MsgConstant.KEY_SERIA_NO, "1")).intValue();
    }

    public boolean getNotificaitonOnForeground() {
        return b(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, ITagManager.STATUS_TRUE).equals(ITagManager.STATUS_TRUE);
    }

    public void setNotificaitonOnForeground(boolean z) {
        c(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, String.valueOf(z));
    }

    public String getResourcePackageName() {
        return b(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, "");
    }

    public void setResourcePackageName(String str) {
        c(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, str);
    }

    public int getNotificationPlayVibrate() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, Constants.FAIL)).intValue();
    }

    public void setNotificationPlayVibrate(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, i + "");
    }

    public int getNotificationPlayLights() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, Constants.FAIL)).intValue();
    }

    public void setNotificationPlayLights(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, i + "");
    }

    public int getNotificationPlaySound() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, Constants.FAIL)).intValue();
    }

    public void setNotificationPlaySound(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, i + "");
    }

    public void setAppVersion(String str) {
        if (str == null) {
            removeKeyAndValue("app_version");
        } else {
            c("app_version", str);
        }
    }

    public String getAppVersion() {
        return b("app_version", "");
    }

    public void setDeviceToken(String str) {
        if (str == null) {
            removeKeyAndValue(MsgConstant.KEY_DEVICE_TOKEN);
        } else {
            c(MsgConstant.KEY_DEVICE_TOKEN, str);
        }
    }

    public String getDeviceToken() {
        return b(MsgConstant.KEY_DEVICE_TOKEN, "");
    }

    public void setUmid(String str) {
        this.f.edit().putString("KEY_SET_UMID", str).apply();
    }

    public String getUmid() {
        return this.f.getString("KEY_SET_UMID", "");
    }

    void b(String str) {
        this.f.edit().putString(MsgConstant.KEY_NOTIFICATION_CHANNEL, str).apply();
    }

    String h() {
        return this.f.getString(MsgConstant.KEY_NOTIFICATION_CHANNEL, "");
    }

    public void setLocationInterval(int i) {
        if (i >= 2 && i <= 1800) {
            c("interval", i + "");
            return;
        }
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo("LBS", 2, "LBS的请求间隔应该在2至1800秒之间");
    }

    public int getLocationInterval() {
        return Integer.valueOf(b("interval", "600")).intValue();
    }

    public void setHasResgister(boolean z) {
        c(MsgConstant.KEY_HASREGISTER, String.valueOf(z));
    }

    public boolean getHasRegister() {
        return b(MsgConstant.KEY_HASREGISTER, "false").equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    public int getRegisterTimes() {
        return Integer.valueOf(getInstance(this.b).b(MsgConstant.KEY_REGISTER_TIMES, Constants.FAIL)).intValue();
    }

    public void setRegisterTimes(int i) {
        c(MsgConstant.KEY_REGISTER_TIMES, i + "");
    }

    public String getUcode() {
        return getInstance(this.b).b(MsgConstant.KEY_UCODE, "");
    }

    public void setUcode(String str) {
        c(MsgConstant.KEY_UCODE, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    private String b(String str, String str2) throws Throwable {
        Cursor cursorQuery;
        ?? r0 = 0;
        r0 = 0;
        try {
            try {
                new ContentValues().put(d, str);
                ContentResolver contentResolver = this.b.getContentResolver();
                a.a(this.b);
                cursorQuery = contentResolver.query(a.c, new String[]{e}, "tempkey=?", new String[]{str}, null);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        if (cursorQuery == null) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return str2;
        }
        try {
            boolean zMoveToFirst = cursorQuery.moveToFirst();
            r0 = zMoveToFirst;
            if (zMoveToFirst) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex(e));
                str2 = string;
                r0 = string;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e3) {
            r0 = cursorQuery;
            e = e3;
            e.printStackTrace();
            if (r0 != 0) {
                r0.close();
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            r0 = cursorQuery;
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
        return str2;
    }

    private void c(final String str, final String str2) {
        ThreadPoolExecutorFactory.execute(new Runnable() { // from class: com.umeng.message.MessageSharedPrefs.2
            /* JADX WARN: Removed duplicated region for block: B:24:0x00cb  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    r10 = this;
                    r0 = 0
                    java.lang.String r7 = "tempkey=?"
                    r1 = 1
                    java.lang.String[] r8 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    r1 = 0
                    java.lang.String r2 = r2     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    r8[r1] = r2     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    com.umeng.message.MessageSharedPrefs r1 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.content.Context r1 = com.umeng.message.MessageSharedPrefs.a(r1)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    com.umeng.message.MessageSharedPrefs r2 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.content.Context r2 = com.umeng.message.MessageSharedPrefs.a(r2)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    com.umeng.message.provider.a.a(r2)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    android.net.Uri r2 = com.umeng.message.provider.a.c     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    java.lang.String r3 = "tempvalue"
                    java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    r6 = 0
                    r4 = r7
                    r5 = r8
                    android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbb
                    if (r1 != 0) goto L5d
                    android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempkey"
                    java.lang.String r3 = r2     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempvalue"
                    java.lang.String r3 = r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.MessageSharedPrefs r2 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r2 = com.umeng.message.MessageSharedPrefs.a(r2)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.MessageSharedPrefs r3 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r3 = com.umeng.message.MessageSharedPrefs.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.provider.a.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.net.Uri r3 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r2.insert(r3, r0)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    goto Lb3
                L5b:
                    r0 = move-exception
                    goto Lbf
                L5d:
                    boolean r0 = r1.moveToFirst()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    if (r0 == 0) goto L88
                    android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempvalue"
                    java.lang.String r3 = r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.MessageSharedPrefs r2 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r2 = com.umeng.message.MessageSharedPrefs.a(r2)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.MessageSharedPrefs r3 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r3 = com.umeng.message.MessageSharedPrefs.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.provider.a.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.net.Uri r3 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r2.update(r3, r0, r7, r8)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    goto Lb3
                L88:
                    android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempkey"
                    java.lang.String r3 = r2     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    java.lang.String r2 = "tempvalue"
                    java.lang.String r3 = r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r0.put(r2, r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.MessageSharedPrefs r2 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r2 = com.umeng.message.MessageSharedPrefs.a(r2)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.MessageSharedPrefs r3 = com.umeng.message.MessageSharedPrefs.this     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.content.Context r3 = com.umeng.message.MessageSharedPrefs.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    com.umeng.message.provider.a.a(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    android.net.Uri r3 = com.umeng.message.provider.a.c     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                    r2.insert(r3, r0)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> Lc8
                Lb3:
                    if (r1 == 0) goto Lc7
                    goto Lc4
                Lb6:
                    r1 = move-exception
                    r9 = r1
                    r1 = r0
                    r0 = r9
                    goto Lc9
                Lbb:
                    r1 = move-exception
                    r9 = r1
                    r1 = r0
                    r0 = r9
                Lbf:
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> Lc8
                    if (r1 == 0) goto Lc7
                Lc4:
                    r1.close()
                Lc7:
                    return
                Lc8:
                    r0 = move-exception
                Lc9:
                    if (r1 == 0) goto Lce
                    r1.close()
                Lce:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.MessageSharedPrefs.AnonymousClass2.run():void");
            }
        });
    }

    public void removeKeyAndValue(final String str) {
        ThreadPoolExecutorFactory.execute(new Runnable() { // from class: com.umeng.message.MessageSharedPrefs.3
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.String] */
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                Cursor cursor = null;
                cursor = null;
                cursor = null;
                try {
                    try {
                        new ContentValues().put(MessageSharedPrefs.d, str);
                        ContentResolver contentResolver = MessageSharedPrefs.this.b.getContentResolver();
                        a.a(MessageSharedPrefs.this.b);
                        Cursor cursorQuery = contentResolver.query(a.c, new String[]{MessageSharedPrefs.e}, null, null, null);
                        if (cursorQuery != null) {
                            try {
                                String[] strArr = {str};
                                ContentResolver contentResolver2 = MessageSharedPrefs.this.b.getContentResolver();
                                a.a(MessageSharedPrefs.this.b);
                                contentResolver2.delete(a.c, "tempkey=?", strArr);
                                cursor = "tempkey=?";
                            } catch (Exception e2) {
                                e = e2;
                                cursor = cursorQuery;
                                e.printStackTrace();
                                if (cursor != null) {
                                    cursor.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor = cursorQuery;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        });
    }
}
