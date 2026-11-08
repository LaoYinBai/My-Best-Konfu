package com.ss.android.downloadlib.event;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.umeng.message.proguard.l;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ClickEventHelper {
    private static final long LAST_TIME = 1209600000;
    private static volatile ClickEventHelper sInstance;
    private SQLiteDatabase mDb;

    public static ClickEventHelper getInstance() {
        if (sInstance == null) {
            synchronized (ClickEventHelper.class) {
                if (sInstance == null) {
                    sInstance = new ClickEventHelper();
                }
            }
        }
        return sInstance;
    }

    private ClickEventHelper() throws JSONException {
        try {
            this.mDb = new ClickEventDBHelper(GlobalInfo.getContext()).getWritableDatabase();
        } catch (Throwable th) {
            TTDownloaderMonitor.inst().monitorException(th, "ClickEventHelper");
        }
    }

    public boolean sendClicEventkWithIdAndReqId() {
        return DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.KEY_CLICK_EVENT_SWITCH, 0) == 1;
    }

    public boolean sendClickEventWithClickButton() {
        return DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.KEY_CLICK_EVENT_SWITCH, 0) == 2;
    }

    public void recordClick(long j, String str) {
        String strOptString;
        if (this.mDb == null || !this.mDb.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            strOptString = new JSONObject(str).optString("req_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(DownloadConstants.KEY_AD_ID, Long.valueOf(j));
        contentValues.put("req_id", strOptString);
        contentValues.put(l.D, Long.valueOf(System.currentTimeMillis()));
        this.mDb.insert("click_event", null, contentValues);
        deleteClickRecord(j, str);
    }

    public boolean hasClickRecord(long j, String str) throws Throwable {
        if (this.mDb == null || !this.mDb.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                String strOptString = new JSONObject(str).optString("req_id");
                if (TextUtils.isEmpty(strOptString)) {
                    return false;
                }
                Cursor cursorQuery = this.mDb.query("click_event", ClickEventDBHelper.EVENT_COLS, "time > ? AND ad_id = ? AND req_id = ?", new String[]{String.valueOf(System.currentTimeMillis() - LAST_TIME), String.valueOf(j), strOptString}, null, null, null, null);
                try {
                    boolean z = cursorQuery.getCount() > 0;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return z;
                } catch (Exception e) {
                    e = e;
                    cursor = cursorQuery;
                    e.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void deleteClickRecord(long j, String str) {
        if (this.mDb == null || !this.mDb.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("req_id");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            this.mDb.delete("click_event", "time < ? AND ad_id = ? AND req_id = ?", new String[]{String.valueOf(System.currentTimeMillis() - LAST_TIME), String.valueOf(j), strOptString});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
