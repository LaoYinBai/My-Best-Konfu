package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.out.Campaign;
import java.util.Iterator;
import java.util.List;

/* compiled from: SCDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class q extends a<Campaign> {
    private static final String a = "com.mbridge.msdk.foundation.db.q";
    private static q b;

    private q(h hVar) {
        super(hVar);
    }

    public static q a(h hVar) {
        if (b == null) {
            synchronized (q.class) {
                if (b == null) {
                    b = new q(hVar);
                }
            }
        }
        return b;
    }

    public final synchronized long a(CampaignEx campaignEx) {
        if (campaignEx == null) {
            return 0L;
        }
        try {
            if (getWritableDatabase() == null) {
                return -1L;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(CampaignEx.JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
            contentValues.put(DownloadModel.DOWNLOAD_URL, campaignEx.getClickURL());
            contentValues.put(CampaignEx.JSON_KEY_CLICK_MODE, campaignEx.getClick_mode());
            contentValues.put(DomainCampaignEx.JSON_KEY_PRE_CLICK, Boolean.valueOf(campaignEx.isPreClick()));
            contentValues.put("get_time", Long.valueOf(System.currentTimeMillis()));
            if (a(campaignEx.getPackageName())) {
                return getWritableDatabase().update("sc", contentValues, "package_name = '" + campaignEx.getPackageName() + "'", null);
            }
            return getWritableDatabase().insert("sc", null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public final synchronized void a(final List<CampaignEx> list) {
        if (list != null) {
            if (list.size() != 0) {
                new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.db.q.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            q.this.a((CampaignEx) it.next());
                        }
                    }
                }).start();
            }
        }
    }

    private synchronized boolean a(String str) {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT get_time FROM sc WHERE package_name='" + str + "'", null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
            cursorRawQuery.close();
            return true;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return false;
    }
}
