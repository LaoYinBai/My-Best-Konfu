package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.out.ApkDisplayInfo;
import com.mbridge.msdk.out.Campaign;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: CampaignDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends a<Campaign> {
    private static final String a = "com.mbridge.msdk.foundation.db.f";
    private static f b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String p;
    private final String q;
    private final String r;

    protected f(h hVar) {
        super(hVar);
        this.c = "rid";
        this.d = BidResponsedEx.KEY_CID;
        this.e = CampaignEx.JSON_KEY_PLCT;
        this.f = CampaignEx.JSON_KEY_PLCTB;
        this.g = CampaignEx.JSON_KEY_TIMESTAMP;
        this.h = "ridCount";
        this.i = " WHERE ";
        this.j = " AND ";
        this.k = "SELECT ";
        this.l = "*";
        this.m = " FROM ";
        this.n = " AND readyState = '0'";
        this.o = " AND is_bid_campaign = 1";
        this.p = " order by ts ASC";
        this.q = " group by request_id";
        this.r = " group by bid_token";
    }

    public static f a(h hVar) {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new f(hVar);
                }
            }
        }
        return b;
    }

    public final synchronized void a(String str, int i, int i2) {
        String str2;
        try {
            str2 = "unitid = " + str + " AND " + FirebaseAnalytics.Param.LEVEL + " = " + i + " AND adSource = " + i2;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void a(String str, int i, int i2, boolean z) {
        String str2;
        try {
            str2 = ("unitid = " + str + " AND " + FirebaseAnalytics.Param.LEVEL + " = " + i + " AND adSource = " + i2) + " AND is_bid_campaign = " + (z ? 1 : 0);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void a(String str, List<CampaignEx> list) {
        if (list != null) {
            if (list.size() > 0) {
                for (CampaignEx campaignEx : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("readyState", (Integer) 2);
                    String str2 = ("id = '" + campaignEx.getId() + "' AND unitid = " + str + " AND request_id = '" + campaignEx.getRequestId() + "'") + " AND is_bid_campaign = " + (campaignEx.isBidCampaign() ? 1 : 0);
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        if (writableDatabase != null) {
                            writableDatabase.update("campaign", contentValues, str2, null);
                        }
                    } catch (SQLException e) {
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    public final synchronized void a(String str, List<CampaignEx> list, boolean z) {
        if (list != null) {
            if (list.size() > 0) {
                for (CampaignEx campaignEx : list) {
                    try {
                        if (campaignEx.getLoadTimeoutState() == 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("unitid");
                            sb.append(" = '");
                            sb.append(str);
                            sb.append("' AND ");
                            sb.append("id");
                            sb.append(" = '");
                            sb.append(campaignEx.getId());
                            sb.append("' AND ");
                            sb.append("request_id");
                            sb.append(" = '");
                            sb.append(campaignEx.getRequestId());
                            sb.append("'");
                            if (z) {
                                sb.append(" AND is_bid_campaign = 1");
                            } else {
                                sb.append(" AND is_bid_campaign = 0");
                            }
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("readyState", (Integer) 2);
                            SQLiteDatabase writableDatabase = getWritableDatabase();
                            if (writableDatabase != null) {
                                writableDatabase.update("campaign", contentValues, sb.toString(), null);
                            }
                        }
                    } catch (SQLException e) {
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    public final synchronized void a(String str, List<CampaignEx> list, String str2, int i) {
        if (!TextUtils.isEmpty(str) && list != null && list.size() > 0 && !TextUtils.isEmpty(str2)) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
            try {
                for (CampaignEx campaignEx : list) {
                    if (campaignEx != null) {
                        String str3 = "unitid = '" + str + "' AND id = '" + campaignEx.getId() + "' AND request_id = '" + campaignEx.getRequestId() + "'";
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(str2, Integer.valueOf(i));
                        writableDatabase.update("campaign", contentValues, str3, null);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public final synchronized void a(String str, List<CampaignEx> list, List<CampaignEx> list2, boolean z) {
        if (getWritableDatabase() == null) {
            return;
        }
        try {
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        for (CampaignEx campaignEx : list) {
                            try {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("readyState", (Integer) 0);
                                String str2 = "unitid = '" + str + "' AND id = '" + campaignEx.getId() + "' AND request_id = '" + campaignEx.getRequestId() + "'";
                                getWritableDatabase().update("campaign", contentValues, z ? str2 + " AND is_bid_campaign = 1" : str2 + " AND is_bid_campaign = 0", null);
                            } catch (Exception e) {
                                com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                            }
                        }
                    }
                } catch (Exception e2) {
                    com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e2.getLocalizedMessage());
                }
            }
            if (list2 != null && list2.size() > 0) {
                for (CampaignEx campaignEx2 : list2) {
                    try {
                        if (campaignEx2.getLoadTimeoutState() == 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("unitid");
                            sb.append(" = '");
                            sb.append(str);
                            sb.append("' AND ");
                            sb.append("id");
                            sb.append(" = '");
                            sb.append(campaignEx2.getId());
                            sb.append("' AND ");
                            sb.append("request_id");
                            sb.append(" = '");
                            sb.append(campaignEx2.getRequestId());
                            sb.append("'");
                            if (z) {
                                sb.append(" AND is_bid_campaign = 1");
                            } else {
                                sb.append(" AND is_bid_campaign = 0");
                            }
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put("readyState", (Integer) 2);
                            getWritableDatabase().update("campaign", contentValues2, sb.toString(), null);
                        }
                    } catch (SQLException e3) {
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e3.getLocalizedMessage());
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void b(String str, List<CampaignEx> list) {
        if (list != null) {
            if (list.size() > 0) {
                for (CampaignEx campaignEx : list) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("readyState", (Integer) 0);
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        if (writableDatabase != null) {
                            writableDatabase.update("campaign", contentValues, "unitid = '" + str + "' AND id = '" + campaignEx.getId() + "' AND request_id = '" + campaignEx.getRequestId() + "'", null);
                        }
                    } catch (Exception e) {
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    public final synchronized void a(String str, String str2) {
        String str3;
        try {
            str3 = "id = '" + str + "' AND unitid = " + str2;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str3, null);
    }

    public final synchronized void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                writableDatabase.delete("campaign", "unitid = '" + str + "' AND readyState = 2", null);
            }
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
    }

    public final synchronized List<CampaignEx> b(String str) {
        try {
            Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT * FROM campaign WHERE unitid = '" + str + "' AND readyState = 2", null);
            if (cursorRawQuery == null || cursorRawQuery.getCount() <= 0) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return null;
            }
            ArrayList arrayList = new ArrayList();
            while (cursorRawQuery.moveToNext()) {
                arrayList.add(a(cursorRawQuery));
            }
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public final synchronized void a(List<CampaignEx> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                for (CampaignEx campaignEx : list) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("readyState", (Integer) 2);
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        if (writableDatabase != null) {
                            writableDatabase.update("campaign", contentValues, "unitid = '" + str + "' AND id = '" + campaignEx.getId() + "' AND request_id = '" + campaignEx.getRequestId() + "'", null);
                        }
                    } catch (SQLException e) {
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    public final synchronized void a(String str, String str2, boolean z, String str3) {
        String string;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("id = '");
            sb.append(str);
            sb.append("' AND ");
            sb.append("unitid");
            sb.append(" = ");
            sb.append(str2);
            sb.append(" AND ");
            sb.append("is_bid_campaign");
            sb.append(" = ");
            sb.append(z ? "1" : PushConstants.PUSH_TYPE_NOTIFY);
            sb.append(" AND ");
            sb.append("request_id");
            sb.append(" = '");
            sb.append(str3);
            sb.append("'");
            string = sb.toString();
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("readyState", (Integer) 2);
        getWritableDatabase().update("campaign", contentValues, string, null);
    }

    public final synchronized void b(String str, String str2) {
        try {
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        String str3 = "id = '" + str + "' AND request_id = '" + str2 + "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put("readyState", (Integer) 2);
        getWritableDatabase().update("campaign", contentValues, str3, null);
    }

    public final synchronized void a(String str, String str2, int i, int i2, boolean z) {
        String str3;
        try {
            String str4 = "id = '" + str + "' AND unitid = " + str2 + " AND " + FirebaseAnalytics.Param.LEVEL + " = " + i + " AND adSource = " + i2;
            if (z) {
                str3 = str4 + " AND is_bid_campaign = 1";
            } else {
                str3 = str4 + " AND is_bid_campaign = 0";
            }
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str3, null);
    }

    public final synchronized void a(int i, String str) {
        String str2;
        try {
            str2 = "tab = " + i + " AND unitid = " + str;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void c(String str) {
        String str2;
        try {
            str2 = "unitid = " + str;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void a(String str, int i, boolean z) {
        String str2;
        try {
            if (z) {
                str2 = "placement_id = " + str + " AND ad_type = " + i;
            } else {
                str2 = "ad_type = " + i;
            }
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void a(String str, int i) {
        String str2;
        try {
            str2 = "unitid = " + str + " AND ia_rst = " + i;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void c(String str, String str2) {
        String str3;
        try {
            str3 = "unitid = " + str + " AND " + CampaignEx.KEY_IA_EXT1 + " = " + str2;
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str3, null);
    }

    public final synchronized void d(String str) {
        String str2;
        try {
            str2 = "unitid = " + str + " AND short_ctime<" + (System.currentTimeMillis() - 604800000);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete("campaign", str2, null);
    }

    public final synchronized void a(String str, ContentValues contentValues) {
        try {
            getWritableDatabase().update("campaign", contentValues, "id = ?", new String[]{str});
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0277 A[Catch: Throwable -> 0x027b, all -> 0x029c, PHI: r1 r10
  0x0277: PHI (r1v9 android.database.Cursor) = (r1v8 android.database.Cursor), (r1v12 android.database.Cursor) binds: [B:82:0x0275, B:74:0x0268] A[DONT_GENERATE, DONT_INLINE]
  0x0277: PHI (r10v3 org.json.JSONObject) = (r10v2 org.json.JSONObject), (r10v13 org.json.JSONObject) binds: [B:82:0x0275, B:74:0x0268] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Throwable -> 0x027b, blocks: (B:83:0x0277, B:79:0x026f, B:80:0x0272), top: B:99:0x0096 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.lang.String e(java.lang.String r42) {
        /*
            Method dump skipped, instructions count: 671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.e(java.lang.String):java.lang.String");
    }

    public final synchronized int b(int i, String str) {
        int i2;
        Cursor cursor = null;
        i2 = 0;
        try {
            try {
                Cursor cursorQuery = getReadableDatabase().query("campaign", new String[]{" count(id) "}, "tab = " + i + " AND unitid = " + str, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            i2 = cursorQuery.getInt(0);
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                        if (cursor != null) {
                            cursor.close();
                        }
                        return i2;
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
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return i2;
    }

    public final synchronized void a() {
        try {
            String str = "ts<" + (System.currentTimeMillis() - 3600000) + " AND ts>0";
            if (getWritableDatabase() != null) {
                getWritableDatabase().delete("campaign", str, null);
            }
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
    }

    public final synchronized void a(long j, String str) {
        String str2;
        String[] strArr;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            str2 = "(plctb>0 and (plctb* 1000+ts)<" + jCurrentTimeMillis + ") or (" + CampaignEx.JSON_KEY_PLCTB + "<=0 and ts<" + (jCurrentTimeMillis - j) + ") and unitid=?";
            strArr = new String[]{str};
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d(a, e.getMessage());
        }
        if (getWritableDatabase() != null) {
            getWritableDatabase().delete("campaign", str2, strArr);
        }
    }

    public final synchronized void b(long j, String str) {
        try {
            String str2 = "ts<" + (System.currentTimeMillis() - j) + " and unitid=?";
            String[] strArr = {str};
            if (getWritableDatabase() != null) {
                getWritableDatabase().delete("campaign", str2, strArr);
            }
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
    }

    public final synchronized void a(List<CampaignEx> list, String str, String str2, int i) {
        if (list != null) {
            if (list.size() != 0) {
                if (getWritableDatabase() == null) {
                    return;
                }
                Iterator<CampaignEx> it = list.iterator();
                while (it.hasNext()) {
                    a(it.next(), str, str2, i);
                }
            }
        }
    }

    private synchronized long a(CampaignEx campaignEx, String str, String str2, int i) {
        if (campaignEx == null) {
            return 0L;
        }
        try {
            if (getWritableDatabase() == null) {
                return -1L;
            }
            ContentValues contentValues = new ContentValues();
            if (campaignEx.getPv_urls() != null && campaignEx.getPv_urls().size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = campaignEx.getPv_urls().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                contentValues.put("pv_urls", jSONArray.toString());
            }
            contentValues.put("id", campaignEx.getId());
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            contentValues.put("placement_id", str);
            contentValues.put("unitid", str2);
            contentValues.put(CampaignEx.JSON_KEY_NUMBER_RATING, Integer.valueOf(campaignEx.getNumberRating()));
            contentValues.put("tab", Integer.valueOf(campaignEx.getTab()));
            contentValues.put(CampaignEx.JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
            contentValues.put("app_name", campaignEx.getAppName());
            contentValues.put("app_desc", campaignEx.getAppDesc());
            contentValues.put(CampaignEx.JSON_KEY_APP_SIZE, campaignEx.getSize());
            contentValues.put(CampaignEx.JSON_KEY_IMAGE_SIZE, campaignEx.getImageSize());
            contentValues.put(CampaignEx.JSON_KEY_ICON_URL, campaignEx.getIconUrl());
            contentValues.put(CampaignEx.JSON_KEY_IMAGE_URL, campaignEx.getImageUrl());
            contentValues.put(CampaignEx.JSON_KEY_IMPRESSION_URL, campaignEx.getImpressionURL());
            contentValues.put(CampaignEx.JSON_KEY_NOTICE_URL, campaignEx.getNoticeUrl());
            contentValues.put(DownloadModel.DOWNLOAD_URL, campaignEx.getClickURL());
            contentValues.put(CampaignEx.JSON_KEY_WITHOUT_INSTALL_CHECK, Integer.valueOf(campaignEx.getWtick()));
            contentValues.put("deeplink_url", campaignEx.getDeepLinkURL());
            contentValues.put(CampaignEx.JSON_KEY_USER_ACTIVATION, Boolean.toString(campaignEx.getUserActivation()));
            contentValues.put("only_impression", campaignEx.getOnlyImpressionURL());
            contentValues.put("ts", Long.valueOf(campaignEx.getTimestamp()));
            contentValues.put("template", Integer.valueOf(campaignEx.getTemplate()));
            contentValues.put(CampaignEx.JSON_KEY_CLICK_MODE, campaignEx.getClick_mode());
            contentValues.put(CampaignEx.JSON_KEY_LANDING_TYPE, campaignEx.getLandingType());
            contentValues.put(CampaignEx.JSON_KEY_LINK_TYPE, Integer.valueOf(campaignEx.getLinkType()));
            contentValues.put("star", Double.valueOf(campaignEx.getRating()));
            contentValues.put("cti", Integer.valueOf(campaignEx.getClickInterval()));
            contentValues.put("cpti", Integer.valueOf(campaignEx.getPreClickInterval()));
            contentValues.put("preclick", Boolean.valueOf(campaignEx.isPreClick()));
            contentValues.put(FirebaseAnalytics.Param.LEVEL, Integer.valueOf(campaignEx.getCacheLevel()));
            contentValues.put("adSource", Integer.valueOf(campaignEx.getType()));
            contentValues.put("ad_call", campaignEx.getAdCall());
            contentValues.put("fc_a", Integer.valueOf(campaignEx.getFca()));
            contentValues.put("fc_b", Integer.valueOf(campaignEx.getFcb()));
            contentValues.put(CampaignEx.JSON_KEY_AD_URL_LIST, campaignEx.getAd_url_list());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_URL, campaignEx.getVideoUrlEncode());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_SIZE, Integer.valueOf(campaignEx.getVideoSize()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_LENGTHL, Integer.valueOf(campaignEx.getVideoLength()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_RESOLUTION, campaignEx.getVideoResolution());
            contentValues.put(CampaignEx.JSON_KEY_ENDCARD_CLICK, Integer.valueOf(campaignEx.getEndcard_click_result()));
            contentValues.put(CampaignEx.JSON_KEY_WATCH_MILE, Integer.valueOf(campaignEx.getWatchMile()));
            contentValues.put("advImp", campaignEx.getAdvImp());
            contentValues.put("bty", Integer.valueOf(campaignEx.getBty()));
            contentValues.put(CampaignEx.JSON_KEY_T_IMP, Integer.valueOf(campaignEx.getTImp()));
            contentValues.put(CampaignEx.JSON_KEY_GUIDELINES, campaignEx.getGuidelines());
            contentValues.put(CampaignEx.JSON_KEY_OFFER_TYPE, Integer.valueOf(campaignEx.getOfferType()));
            contentValues.put("html_url", campaignEx.getHtmlUrl());
            contentValues.put("end_screen_url", campaignEx.getEndScreenUrl());
            contentValues.put(CampaignEx.JSON_KEY_REWARD_AMOUNT, Integer.valueOf(campaignEx.getRewardAmount()));
            contentValues.put(CampaignEx.JSON_KEY_REWARD_NAME, campaignEx.getRewardName());
            contentValues.put("reward_play_status", Integer.valueOf(campaignEx.getRewardPlayStatus()));
            contentValues.put(DomainCampaignEx.JSON_KEY_ADV_ID, campaignEx.getAdvId());
            contentValues.put(DomainCampaignEx.JSON_KEY_TTC_CT2, Integer.valueOf(campaignEx.getTtc_ct2() * 1000));
            contentValues.put(DomainCampaignEx.JSON_KEY_TTC_TYPE, Integer.valueOf(campaignEx.getTtc_type()));
            contentValues.put("retarget", Integer.valueOf(campaignEx.getRetarget_offer()));
            contentValues.put("native_ad_tracking", campaignEx.getNativeVideoTrackingString());
            contentValues.put(CampaignEx.PLAYABLE_ADS_WITHOUT_VIDEO, Integer.valueOf(campaignEx.getPlayable_ads_without_video()));
            contentValues.put(CampaignEx.ENDCARD_URL, campaignEx.getendcard_url());
            contentValues.put(CampaignEx.VIDEO_END_TYPE, Integer.valueOf(campaignEx.getVideo_end_type()));
            contentValues.put(DomainCampaignEx.LOOPBACK, campaignEx.getLoopbackString());
            contentValues.put(CampaignEx.JSON_KEY_REWARD_VIDEO_MD5, campaignEx.getVideoMD5Value());
            contentValues.put(CampaignEx.JSON_KEY_NV_T2, Integer.valueOf(campaignEx.getNvT2()));
            contentValues.put(CampaignEx.JSON_KEY_GIF_URL, campaignEx.getGifUrl());
            if (campaignEx.getRewardTemplateMode() != null) {
                contentValues.put("reward_teamplate", campaignEx.getRewardTemplateMode().a());
            }
            contentValues.put("c_coi", Integer.valueOf(campaignEx.getClickTimeOutInterval()));
            contentValues.put(CampaignEx.JSON_KEY_C_UA, Integer.valueOf(campaignEx.getcUA()));
            contentValues.put(CampaignEx.JSON_KEY_IMP_UA, Integer.valueOf(campaignEx.getImpUA()));
            contentValues.put(CampaignEx.JSON_KEY_JM_PD, Integer.valueOf(campaignEx.getJmPd()));
            contentValues.put("is_deleted", Integer.valueOf(campaignEx.getIsDeleted()));
            contentValues.put("is_click", Integer.valueOf(campaignEx.getIsClick()));
            contentValues.put("is_add_sucesful", Integer.valueOf(campaignEx.getIsAddSuccesful()));
            contentValues.put("short_ctime", String.valueOf(System.currentTimeMillis()));
            contentValues.put("ia_icon", campaignEx.getKeyIaIcon());
            contentValues.put("ia_url", campaignEx.getKeyIaUrl());
            contentValues.put("ia_rst", Integer.valueOf(campaignEx.getKeyIaRst()));
            contentValues.put("ia_ori", Integer.valueOf(campaignEx.getKeyIaOri()));
            contentValues.put("ad_type", Integer.valueOf(campaignEx.getAdType()));
            contentValues.put(CampaignEx.KEY_IA_EXT1, campaignEx.getIa_ext1());
            contentValues.put(CampaignEx.KEY_IA_EXT2, campaignEx.getIa_ext2());
            contentValues.put(CampaignEx.KEY_IS_DOWNLOAD, Integer.valueOf(campaignEx.getIsDownLoadZip()));
            contentValues.put(CampaignEx.KEY_IA_CACHE, campaignEx.getInteractiveCache());
            contentValues.put(DomainCampaignEx.KEY_GH_ID, campaignEx.getGhId());
            contentValues.put(DomainCampaignEx.KEY_GH_PATH, campaignEx.getGhPath());
            contentValues.put(DomainCampaignEx.KEY_BIND_ID, campaignEx.getBindId());
            contentValues.put(CampaignEx.KEY_OC_TIME, Integer.valueOf(campaignEx.getOc_time()));
            contentValues.put(CampaignEx.KEY_OC_TYPE, Integer.valueOf(campaignEx.getOc_type()));
            contentValues.put(CampaignEx.KEY_T_LIST, campaignEx.getT_list());
            CampaignEx.a adchoice = campaignEx.getAdchoice();
            if (adchoice != null) {
                contentValues.put(CampaignEx.KEY_ADCHOICE, adchoice.c());
                contentValues.put("adchoice_size_height", Integer.valueOf(adchoice.b()));
                contentValues.put("adchoice_size_width", Integer.valueOf(adchoice.a()));
            }
            contentValues.put(CampaignEx.JSON_KEY_PLCT, Long.valueOf(campaignEx.getPlct()));
            contentValues.put(CampaignEx.JSON_KEY_PLCTB, Long.valueOf(campaignEx.getPlctb()));
            contentValues.put(CampaignEx.JSON_KEY_AD_HTML, campaignEx.getAdHtml());
            contentValues.put("ad_zip", campaignEx.getAdZip());
            contentValues.put(CampaignEx.JSON_KEY_BANNER_URL, campaignEx.getBannerUrl());
            contentValues.put(CampaignEx.JSON_KEY_BANNER_HTML, campaignEx.getBannerHtml());
            contentValues.put(CampaignEx.JSON_KEY_CREATIVE_ID, Long.valueOf(campaignEx.getCreativeId()));
            contentValues.put("is_bid_campaign", Boolean.valueOf(campaignEx.isBidCampaign()));
            contentValues.put("bid_token", campaignEx.getBidToken());
            contentValues.put(CampaignEx.JSON_KEY_MRAID, campaignEx.getMraid());
            contentValues.put("is_mraid_campaign", Boolean.valueOf(campaignEx.isMraid()));
            contentValues.put(CampaignEx.KEY_OMID, campaignEx.getOmid());
            contentValues.put(CampaignUnit.JSON_KEY_MOF_TPLID, Integer.valueOf(campaignEx.getMof_tplid()));
            contentValues.put(CampaignEx.JSON_KEY_READY_RATE, Integer.valueOf(campaignEx.getReady_rate()));
            if (campaignEx.getRewardPlus() != null && campaignEx.getRewardPlus().toJsonObject() != null) {
                contentValues.put(CampaignEx.JSON_KEY_REWARD_PLUS, campaignEx.getRewardPlus().toJsonObject().toString());
            }
            if (campaignEx.getApkDisplayInfo() != null && campaignEx.getApkDisplayInfo().toJson() != null) {
                contentValues.put(DomainCampaignEx.JSON_KEY_APK_INFO, campaignEx.getApkDisplayInfo().toJson().toString());
            }
            contentValues.put(CampaignEx.JSON_KEY_EXT_DATA, campaignEx.getExt_data());
            contentValues.put(CampaignUnit.JSON_KEY_NSCPT, Integer.valueOf(campaignEx.getNscpt()));
            contentValues.put(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL, campaignEx.getMof_template_url());
            contentValues.put(CampaignUnit.JSON_KEY_REQ_EXT_DATA, campaignEx.getReq_ext_data());
            contentValues.put("readyState", Integer.valueOf(campaignEx.getReadyState()));
            contentValues.put("request_id", campaignEx.getRequestId());
            contentValues.put("load_timeout", Integer.valueOf(campaignEx.getLoadTimeoutState()));
            contentValues.put(DomainCampaignEx.JSON_KEY_APK_ALT, Integer.valueOf(campaignEx.getApkAlt()));
            contentValues.put(DomainCampaignEx.JSON_KEY_ATAT_TYPE, Integer.valueOf(campaignEx.getAtatType()));
            contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPASBL, Integer.valueOf(campaignEx.getNtbarpasbl()));
            contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPT, Integer.valueOf(campaignEx.getNtbarpt()));
            contentValues.put(DomainCampaignEx.JSON_KEY_AKDLUI, campaignEx.getAkdlui());
            contentValues.put(CampaignEx.JSON_KEY_FLB, Integer.valueOf(campaignEx.getFlb()));
            contentValues.put(CampaignEx.JSON_KEY_FLB_SKIP_TIME, Integer.valueOf(campaignEx.getFlbSkipTime()));
            contentValues.put("vcn", Integer.valueOf(campaignEx.getVcn()));
            contentValues.put("token_r", Integer.valueOf(campaignEx.getTokenRule()));
            contentValues.put("encrypt_p", campaignEx.getEncryptPrice());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_CHECK_TYPE, Integer.valueOf(campaignEx.getVideoCheckType()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_CTN_TYPE, Integer.valueOf(campaignEx.getVideoCtnType()));
            contentValues.put(CampaignEx.JSON_KEY_RS_IGNORE_CHECK_RULE, campaignEx.getRsIgnoreCheckRuleString());
            return getWritableDatabase().insert("campaign", null, contentValues);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
            return -1L;
        }
    }

    public final synchronized long a(CampaignEx campaignEx, String str, int i) {
        String str2;
        if (campaignEx == null) {
            return 0L;
        }
        try {
            if (getWritableDatabase() == null) {
                return -1L;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", campaignEx.getId());
            if (campaignEx.getPv_urls() != null && campaignEx.getPv_urls().size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = campaignEx.getPv_urls().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                contentValues.put("pv_urls", jSONArray.toString());
            }
            contentValues.put("unitid", str);
            contentValues.put(CampaignEx.JSON_KEY_NUMBER_RATING, Integer.valueOf(campaignEx.getNumberRating()));
            contentValues.put("request_id", campaignEx.getRequestId());
            contentValues.put("tab", Integer.valueOf(campaignEx.getTab()));
            contentValues.put(CampaignEx.JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
            contentValues.put("app_name", campaignEx.getAppName());
            contentValues.put("app_desc", campaignEx.getAppDesc());
            contentValues.put(CampaignEx.JSON_KEY_APP_SIZE, campaignEx.getSize());
            contentValues.put(CampaignEx.JSON_KEY_IMAGE_SIZE, campaignEx.getImageSize());
            contentValues.put(CampaignEx.JSON_KEY_ICON_URL, campaignEx.getIconUrl());
            contentValues.put(CampaignEx.JSON_KEY_IMAGE_URL, campaignEx.getImageUrl());
            contentValues.put(CampaignEx.JSON_KEY_IMPRESSION_URL, campaignEx.getImpressionURL());
            contentValues.put(CampaignEx.JSON_KEY_NOTICE_URL, campaignEx.getNoticeUrl());
            contentValues.put(DownloadModel.DOWNLOAD_URL, campaignEx.getClickURL());
            contentValues.put(CampaignEx.JSON_KEY_WITHOUT_INSTALL_CHECK, Integer.valueOf(campaignEx.getWtick()));
            contentValues.put("deeplink_url", campaignEx.getDeepLinkURL());
            contentValues.put(CampaignEx.JSON_KEY_USER_ACTIVATION, Boolean.toString(campaignEx.getUserActivation()));
            contentValues.put("only_impression", campaignEx.getOnlyImpressionURL());
            contentValues.put("ts", Long.valueOf(campaignEx.getTimestamp()));
            contentValues.put("template", Integer.valueOf(campaignEx.getTemplate()));
            contentValues.put(CampaignEx.JSON_KEY_CLICK_MODE, campaignEx.getClick_mode());
            contentValues.put(CampaignEx.JSON_KEY_LANDING_TYPE, campaignEx.getLandingType());
            contentValues.put(CampaignEx.JSON_KEY_LINK_TYPE, Integer.valueOf(campaignEx.getLinkType()));
            contentValues.put("star", Double.valueOf(campaignEx.getRating()));
            contentValues.put("cti", Integer.valueOf(campaignEx.getClickInterval()));
            contentValues.put("cpti", Integer.valueOf(campaignEx.getPreClickInterval()));
            contentValues.put("preclick", Boolean.valueOf(campaignEx.isPreClick()));
            contentValues.put(FirebaseAnalytics.Param.LEVEL, Integer.valueOf(campaignEx.getCacheLevel()));
            contentValues.put("adSource", Integer.valueOf(campaignEx.getType()));
            contentValues.put("ad_call", campaignEx.getAdCall());
            contentValues.put("fc_a", Integer.valueOf(campaignEx.getFca()));
            contentValues.put("fc_b", Integer.valueOf(campaignEx.getFcb()));
            contentValues.put(CampaignEx.JSON_KEY_AD_URL_LIST, campaignEx.getAd_url_list());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_URL, campaignEx.getVideoUrlEncode());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_SIZE, Integer.valueOf(campaignEx.getVideoSize()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_LENGTHL, Integer.valueOf(campaignEx.getVideoLength()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_RESOLUTION, campaignEx.getVideoResolution());
            contentValues.put(CampaignEx.JSON_KEY_ENDCARD_CLICK, Integer.valueOf(campaignEx.getEndcard_click_result()));
            contentValues.put(CampaignEx.JSON_KEY_WATCH_MILE, Integer.valueOf(campaignEx.getWatchMile()));
            contentValues.put("advImp", campaignEx.getAdvImp());
            contentValues.put("bty", Integer.valueOf(campaignEx.getBty()));
            contentValues.put(CampaignEx.JSON_KEY_T_IMP, Integer.valueOf(campaignEx.getTImp()));
            contentValues.put(CampaignEx.JSON_KEY_GUIDELINES, campaignEx.getGuidelines());
            contentValues.put(CampaignEx.JSON_KEY_OFFER_TYPE, Integer.valueOf(campaignEx.getOfferType()));
            contentValues.put("html_url", campaignEx.getHtmlUrl());
            contentValues.put("end_screen_url", campaignEx.getEndScreenUrl());
            contentValues.put(CampaignEx.JSON_KEY_REWARD_AMOUNT, Integer.valueOf(campaignEx.getRewardAmount()));
            contentValues.put(CampaignEx.JSON_KEY_REWARD_NAME, campaignEx.getRewardName());
            contentValues.put("reward_play_status", Integer.valueOf(campaignEx.getRewardPlayStatus()));
            contentValues.put(DomainCampaignEx.JSON_KEY_ADV_ID, campaignEx.getAdvId());
            contentValues.put(DomainCampaignEx.JSON_KEY_TTC_CT2, Integer.valueOf(campaignEx.getTtc_ct2() * 1000));
            contentValues.put(DomainCampaignEx.JSON_KEY_TTC_TYPE, Integer.valueOf(campaignEx.getTtc_type()));
            contentValues.put("retarget", Integer.valueOf(campaignEx.getRetarget_offer()));
            contentValues.put("native_ad_tracking", campaignEx.getNativeVideoTrackingString());
            contentValues.put(CampaignEx.PLAYABLE_ADS_WITHOUT_VIDEO, Integer.valueOf(campaignEx.getPlayable_ads_without_video()));
            contentValues.put(CampaignEx.ENDCARD_URL, campaignEx.getendcard_url());
            contentValues.put(CampaignEx.VIDEO_END_TYPE, Integer.valueOf(campaignEx.getVideo_end_type()));
            contentValues.put(DomainCampaignEx.LOOPBACK, campaignEx.getLoopbackString());
            contentValues.put(CampaignEx.JSON_KEY_REWARD_VIDEO_MD5, campaignEx.getVideoMD5Value());
            contentValues.put(CampaignEx.JSON_KEY_NV_T2, Integer.valueOf(campaignEx.getNvT2()));
            contentValues.put(CampaignEx.JSON_KEY_GIF_URL, campaignEx.getGifUrl());
            if (campaignEx.getRewardTemplateMode() != null) {
                contentValues.put("reward_teamplate", campaignEx.getRewardTemplateMode().a());
            }
            contentValues.put("c_coi", Integer.valueOf(campaignEx.getClickTimeOutInterval()));
            contentValues.put(CampaignEx.JSON_KEY_C_UA, Integer.valueOf(campaignEx.getcUA()));
            contentValues.put(CampaignEx.JSON_KEY_IMP_UA, Integer.valueOf(campaignEx.getImpUA()));
            contentValues.put(CampaignEx.JSON_KEY_JM_PD, Integer.valueOf(campaignEx.getJmPd()));
            contentValues.put("is_deleted", Integer.valueOf(campaignEx.getIsDeleted()));
            contentValues.put("is_click", Integer.valueOf(campaignEx.getIsClick()));
            contentValues.put("is_add_sucesful", Integer.valueOf(campaignEx.getIsAddSuccesful()));
            contentValues.put("short_ctime", String.valueOf(System.currentTimeMillis()));
            contentValues.put("ia_icon", campaignEx.getKeyIaIcon());
            contentValues.put("ia_url", campaignEx.getKeyIaUrl());
            contentValues.put("ia_rst", Integer.valueOf(campaignEx.getKeyIaRst()));
            contentValues.put("ia_ori", Integer.valueOf(campaignEx.getKeyIaOri()));
            contentValues.put("ad_type", Integer.valueOf(campaignEx.getAdType()));
            contentValues.put(CampaignEx.KEY_IA_EXT1, campaignEx.getIa_ext1());
            contentValues.put(CampaignEx.KEY_IA_EXT2, campaignEx.getIa_ext2());
            contentValues.put(CampaignEx.KEY_IS_DOWNLOAD, Integer.valueOf(campaignEx.getIsDownLoadZip()));
            contentValues.put(CampaignEx.KEY_IA_CACHE, campaignEx.getInteractiveCache());
            contentValues.put(DomainCampaignEx.KEY_GH_ID, campaignEx.getGhId());
            contentValues.put(DomainCampaignEx.KEY_GH_PATH, campaignEx.getGhPath());
            contentValues.put(DomainCampaignEx.KEY_BIND_ID, campaignEx.getBindId());
            contentValues.put(CampaignEx.KEY_OC_TIME, Integer.valueOf(campaignEx.getOc_time()));
            contentValues.put(CampaignEx.KEY_OC_TYPE, Integer.valueOf(campaignEx.getOc_type()));
            contentValues.put(CampaignEx.KEY_T_LIST, campaignEx.getT_list());
            CampaignEx.a adchoice = campaignEx.getAdchoice();
            if (adchoice != null) {
                contentValues.put(CampaignEx.KEY_ADCHOICE, adchoice.c());
                contentValues.put("adchoice_size_height", Integer.valueOf(adchoice.b()));
                contentValues.put("adchoice_size_width", Integer.valueOf(adchoice.a()));
            }
            contentValues.put(CampaignEx.JSON_KEY_PLCT, Long.valueOf(campaignEx.getPlct()));
            contentValues.put(CampaignEx.JSON_KEY_PLCTB, Long.valueOf(campaignEx.getPlctb()));
            contentValues.put(CampaignEx.JSON_KEY_AD_HTML, campaignEx.getAdHtml());
            contentValues.put("ad_zip", campaignEx.getAdZip());
            contentValues.put(CampaignEx.JSON_KEY_BANNER_URL, campaignEx.getBannerUrl());
            contentValues.put(CampaignEx.JSON_KEY_BANNER_HTML, campaignEx.getBannerHtml());
            contentValues.put(CampaignEx.JSON_KEY_CREATIVE_ID, Long.valueOf(campaignEx.getCreativeId()));
            contentValues.put("is_bid_campaign", Boolean.valueOf(campaignEx.isBidCampaign()));
            contentValues.put("bid_token", campaignEx.getBidToken());
            contentValues.put(CampaignEx.JSON_KEY_MRAID, campaignEx.getMraid());
            contentValues.put("is_mraid_campaign", Boolean.valueOf(campaignEx.isMraid()));
            contentValues.put(CampaignEx.KEY_OMID, campaignEx.getOmid());
            contentValues.put(CampaignUnit.JSON_KEY_MOF_TPLID, Integer.valueOf(campaignEx.getMof_tplid()));
            if (campaignEx.getRewardPlus() != null && campaignEx.getRewardPlus().toJsonObject() != null) {
                contentValues.put(CampaignEx.JSON_KEY_REWARD_PLUS, campaignEx.getRewardPlus().toJsonObject().toString());
            }
            if (campaignEx.getApkDisplayInfo() != null && campaignEx.getApkDisplayInfo().toJson() != null) {
                contentValues.put(DomainCampaignEx.JSON_KEY_APK_INFO, campaignEx.getApkDisplayInfo().toJson().toString());
            }
            contentValues.put(CampaignEx.JSON_KEY_READY_RATE, Integer.valueOf(campaignEx.getReady_rate()));
            contentValues.put(CampaignEx.JSON_KEY_EXT_DATA, campaignEx.getExt_data());
            contentValues.put(CampaignUnit.JSON_KEY_NSCPT, Integer.valueOf(campaignEx.getNscpt()));
            contentValues.put(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL, campaignEx.getMof_template_url());
            contentValues.put(CampaignUnit.JSON_KEY_REQ_EXT_DATA, campaignEx.getReq_ext_data());
            contentValues.put("readyState", Integer.valueOf(campaignEx.getReadyState()));
            contentValues.put("load_timeout", Integer.valueOf(campaignEx.getLoadTimeoutState()));
            contentValues.put("placement_id", campaignEx.getPlacementId());
            contentValues.put(DomainCampaignEx.JSON_KEY_APK_ALT, Integer.valueOf(campaignEx.getApkAlt()));
            contentValues.put(DomainCampaignEx.JSON_KEY_ATAT_TYPE, Integer.valueOf(campaignEx.getAtatType()));
            contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPASBL, Integer.valueOf(campaignEx.getNtbarpasbl()));
            contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPT, Integer.valueOf(campaignEx.getNtbarpt()));
            contentValues.put(DomainCampaignEx.JSON_KEY_AKDLUI, campaignEx.getAkdlui());
            contentValues.put(CampaignEx.JSON_KEY_FLB, Integer.valueOf(campaignEx.getFlb()));
            contentValues.put(CampaignEx.JSON_KEY_FLB_SKIP_TIME, Integer.valueOf(campaignEx.getFlbSkipTime()));
            contentValues.put("vcn", Integer.valueOf(campaignEx.getVcn()));
            contentValues.put("token_r", Integer.valueOf(campaignEx.getTokenRule()));
            contentValues.put("encrypt_p", campaignEx.getEncryptPrice());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_CHECK_TYPE, Integer.valueOf(campaignEx.getVideoCheckType()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_CTN_TYPE, Integer.valueOf(campaignEx.getVideoCtnType()));
            contentValues.put(CampaignEx.JSON_KEY_RS_IGNORE_CHECK_RULE, campaignEx.getRsIgnoreCheckRuleString());
            if (a(campaignEx.getId(), campaignEx.getTab(), str, i, campaignEx.getType(), campaignEx.isBidCampaign())) {
                if (campaignEx.isBidCampaign()) {
                    str2 = "unitid = " + str + " AND is_bid_campaign = 1";
                } else {
                    str2 = "id = " + campaignEx.getId() + " AND unitid = " + str + " AND is_bid_campaign = 0";
                }
                return getWritableDatabase().update("campaign", contentValues, str2, null);
            }
            return getWritableDatabase().insert("campaign", null, contentValues);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
            return -1L;
        }
    }

    public final synchronized long b(CampaignEx campaignEx, String str, int i) {
        if (campaignEx == null) {
            return 0L;
        }
        try {
            if (getWritableDatabase() == null) {
                return -1L;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", campaignEx.getId());
            contentValues.put("unitid", str);
            if (campaignEx.getPv_urls() != null && campaignEx.getPv_urls().size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = campaignEx.getPv_urls().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                contentValues.put("pv_urls", jSONArray.toString());
            }
            contentValues.put("tab", Integer.valueOf(campaignEx.getTab()));
            contentValues.put(CampaignEx.JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
            contentValues.put(CampaignEx.JSON_KEY_NUMBER_RATING, Integer.valueOf(campaignEx.getNumberRating()));
            contentValues.put("app_name", campaignEx.getAppName());
            contentValues.put("app_desc", campaignEx.getAppDesc());
            contentValues.put(CampaignEx.JSON_KEY_APP_SIZE, campaignEx.getSize());
            contentValues.put(CampaignEx.JSON_KEY_IMAGE_SIZE, campaignEx.getImageSize());
            contentValues.put(CampaignEx.JSON_KEY_ICON_URL, campaignEx.getIconUrl());
            contentValues.put(CampaignEx.JSON_KEY_IMAGE_URL, campaignEx.getImageUrl());
            contentValues.put(CampaignEx.JSON_KEY_IMPRESSION_URL, campaignEx.getImpressionURL());
            contentValues.put(CampaignEx.JSON_KEY_NOTICE_URL, campaignEx.getNoticeUrl());
            contentValues.put(DownloadModel.DOWNLOAD_URL, campaignEx.getClickURL());
            contentValues.put(CampaignEx.JSON_KEY_WITHOUT_INSTALL_CHECK, Integer.valueOf(campaignEx.getWtick()));
            contentValues.put("deeplink_url", campaignEx.getDeepLinkURL());
            contentValues.put(CampaignEx.JSON_KEY_USER_ACTIVATION, Boolean.toString(campaignEx.getUserActivation()));
            contentValues.put("only_impression", campaignEx.getOnlyImpressionURL());
            contentValues.put("ts", Long.valueOf(campaignEx.getTimestamp()));
            contentValues.put("template", Integer.valueOf(campaignEx.getTemplate()));
            contentValues.put(CampaignEx.JSON_KEY_CLICK_MODE, campaignEx.getClick_mode());
            contentValues.put(CampaignEx.JSON_KEY_LANDING_TYPE, campaignEx.getLandingType());
            contentValues.put(CampaignEx.JSON_KEY_LINK_TYPE, Integer.valueOf(campaignEx.getLinkType()));
            contentValues.put("star", Double.valueOf(campaignEx.getRating()));
            contentValues.put("cti", Integer.valueOf(campaignEx.getClickInterval()));
            contentValues.put("cpti", Integer.valueOf(campaignEx.getPreClickInterval()));
            contentValues.put("preclick", Boolean.valueOf(campaignEx.isPreClick()));
            contentValues.put(FirebaseAnalytics.Param.LEVEL, Integer.valueOf(campaignEx.getCacheLevel()));
            contentValues.put("adSource", Integer.valueOf(campaignEx.getType()));
            contentValues.put("ad_call", campaignEx.getAdCall());
            contentValues.put("fc_a", Integer.valueOf(campaignEx.getFca()));
            contentValues.put("fc_b", Integer.valueOf(campaignEx.getFcb()));
            contentValues.put(CampaignEx.JSON_KEY_AD_URL_LIST, campaignEx.getAd_url_list());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_URL, campaignEx.getVideoUrlEncode());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_SIZE, Integer.valueOf(campaignEx.getVideoSize()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_LENGTHL, Integer.valueOf(campaignEx.getVideoLength()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_RESOLUTION, campaignEx.getVideoResolution());
            contentValues.put(CampaignEx.JSON_KEY_ENDCARD_CLICK, Integer.valueOf(campaignEx.getEndcard_click_result()));
            contentValues.put(CampaignEx.JSON_KEY_WATCH_MILE, Integer.valueOf(campaignEx.getWatchMile()));
            contentValues.put("advImp", campaignEx.getAdvImp());
            contentValues.put("bty", Integer.valueOf(campaignEx.getBty()));
            contentValues.put(CampaignEx.JSON_KEY_T_IMP, Integer.valueOf(campaignEx.getTImp()));
            contentValues.put(CampaignEx.JSON_KEY_GUIDELINES, campaignEx.getGuidelines());
            contentValues.put(CampaignEx.JSON_KEY_OFFER_TYPE, Integer.valueOf(campaignEx.getOfferType()));
            contentValues.put("html_url", campaignEx.getHtmlUrl());
            contentValues.put("end_screen_url", campaignEx.getEndScreenUrl());
            contentValues.put(CampaignEx.JSON_KEY_REWARD_AMOUNT, Integer.valueOf(campaignEx.getRewardAmount()));
            contentValues.put(CampaignEx.JSON_KEY_REWARD_NAME, campaignEx.getRewardName());
            contentValues.put("reward_play_status", Integer.valueOf(campaignEx.getRewardPlayStatus()));
            contentValues.put(DomainCampaignEx.JSON_KEY_ADV_ID, campaignEx.getAdvId());
            contentValues.put(DomainCampaignEx.JSON_KEY_TTC_CT2, Integer.valueOf(campaignEx.getTtc_ct2() * 1000));
            contentValues.put(DomainCampaignEx.JSON_KEY_TTC_TYPE, Integer.valueOf(campaignEx.getTtc_type()));
            contentValues.put("retarget", Integer.valueOf(campaignEx.getRetarget_offer()));
            contentValues.put("native_ad_tracking", campaignEx.getNativeVideoTrackingString());
            contentValues.put(CampaignEx.PLAYABLE_ADS_WITHOUT_VIDEO, Integer.valueOf(campaignEx.getPlayable_ads_without_video()));
            contentValues.put(CampaignEx.ENDCARD_URL, campaignEx.getendcard_url());
            contentValues.put(CampaignEx.VIDEO_END_TYPE, Integer.valueOf(campaignEx.getVideo_end_type()));
            contentValues.put(DomainCampaignEx.LOOPBACK, campaignEx.getLoopbackString());
            contentValues.put(CampaignEx.JSON_KEY_REWARD_VIDEO_MD5, campaignEx.getVideoMD5Value());
            contentValues.put(CampaignEx.JSON_KEY_NV_T2, Integer.valueOf(campaignEx.getNvT2()));
            contentValues.put(CampaignEx.JSON_KEY_GIF_URL, campaignEx.getGifUrl());
            if (campaignEx.getRewardTemplateMode() != null) {
                contentValues.put("reward_teamplate", campaignEx.getRewardTemplateMode().a());
            }
            contentValues.put("c_coi", Integer.valueOf(campaignEx.getClickTimeOutInterval()));
            contentValues.put(CampaignEx.JSON_KEY_C_UA, Integer.valueOf(campaignEx.getcUA()));
            contentValues.put(CampaignEx.JSON_KEY_IMP_UA, Integer.valueOf(campaignEx.getImpUA()));
            contentValues.put(CampaignEx.JSON_KEY_JM_PD, Integer.valueOf(campaignEx.getJmPd()));
            contentValues.put("is_deleted", Integer.valueOf(campaignEx.getIsDeleted()));
            contentValues.put("is_click", Integer.valueOf(campaignEx.getIsClick()));
            contentValues.put("is_add_sucesful", Integer.valueOf(campaignEx.getIsAddSuccesful()));
            contentValues.put("short_ctime", String.valueOf(System.currentTimeMillis()));
            contentValues.put("ia_icon", campaignEx.getKeyIaIcon());
            contentValues.put("ia_url", campaignEx.getKeyIaUrl());
            contentValues.put("ia_rst", Integer.valueOf(campaignEx.getKeyIaRst()));
            contentValues.put("ia_ori", Integer.valueOf(campaignEx.getKeyIaOri()));
            contentValues.put("ad_type", Integer.valueOf(campaignEx.getAdType()));
            contentValues.put(CampaignEx.KEY_IA_EXT1, campaignEx.getIa_ext1());
            contentValues.put(CampaignEx.KEY_IA_EXT2, campaignEx.getIa_ext2());
            contentValues.put(CampaignEx.KEY_IS_DOWNLOAD, Integer.valueOf(campaignEx.getIsDownLoadZip()));
            contentValues.put(CampaignEx.KEY_IA_CACHE, campaignEx.getInteractiveCache());
            contentValues.put(DomainCampaignEx.KEY_GH_ID, campaignEx.getGhId());
            contentValues.put(DomainCampaignEx.KEY_GH_PATH, campaignEx.getGhPath());
            contentValues.put(DomainCampaignEx.KEY_BIND_ID, campaignEx.getBindId());
            contentValues.put(CampaignEx.KEY_OC_TYPE, Integer.valueOf(campaignEx.getOc_type()));
            contentValues.put(CampaignEx.KEY_OC_TIME, Integer.valueOf(campaignEx.getOc_time()));
            contentValues.put(CampaignEx.KEY_T_LIST, campaignEx.getT_list());
            CampaignEx.a adchoice = campaignEx.getAdchoice();
            if (adchoice != null) {
                contentValues.put(CampaignEx.KEY_ADCHOICE, adchoice.c());
                contentValues.put("adchoice_size_height", Integer.valueOf(adchoice.b()));
                contentValues.put("adchoice_size_width", Integer.valueOf(adchoice.a()));
            }
            contentValues.put(CampaignEx.JSON_KEY_PLCT, Long.valueOf(campaignEx.getPlct()));
            contentValues.put(CampaignEx.JSON_KEY_PLCTB, Long.valueOf(campaignEx.getPlctb()));
            contentValues.put(CampaignEx.JSON_KEY_BANNER_URL, campaignEx.getBannerUrl());
            contentValues.put(CampaignEx.JSON_KEY_BANNER_HTML, campaignEx.getBannerHtml());
            contentValues.put(CampaignEx.JSON_KEY_CREATIVE_ID, Long.valueOf(campaignEx.getCreativeId()));
            contentValues.put("is_bid_campaign", Boolean.valueOf(campaignEx.isBidCampaign()));
            contentValues.put("bid_token", campaignEx.getBidToken());
            contentValues.put(CampaignEx.JSON_KEY_MRAID, campaignEx.getMraid());
            contentValues.put("is_mraid_campaign", Boolean.valueOf(campaignEx.isMraid()));
            contentValues.put(CampaignEx.KEY_OMID, campaignEx.getOmid());
            contentValues.put(CampaignUnit.JSON_KEY_MOF_TPLID, Integer.valueOf(campaignEx.getMof_tplid()));
            if (campaignEx.getRewardPlus() != null) {
                contentValues.put(CampaignEx.JSON_KEY_REWARD_PLUS, campaignEx.getRewardPlus().toJsonObject().toString());
            }
            if (campaignEx.getApkDisplayInfo() != null) {
                contentValues.put(DomainCampaignEx.JSON_KEY_APK_INFO, campaignEx.getApkDisplayInfo().toJson().toString());
            }
            contentValues.put(CampaignEx.JSON_KEY_READY_RATE, Integer.valueOf(campaignEx.getReady_rate()));
            contentValues.put(CampaignEx.JSON_KEY_EXT_DATA, campaignEx.getExt_data());
            contentValues.put(CampaignUnit.JSON_KEY_NSCPT, Integer.valueOf(campaignEx.getNscpt()));
            contentValues.put(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL, campaignEx.getMof_template_url());
            contentValues.put(CampaignUnit.JSON_KEY_REQ_EXT_DATA, campaignEx.getReq_ext_data());
            contentValues.put("readyState", Integer.valueOf(campaignEx.getReadyState()));
            contentValues.put("request_id", campaignEx.getRequestId());
            contentValues.put("load_timeout", Integer.valueOf(campaignEx.getLoadTimeoutState()));
            contentValues.put("placement_id", campaignEx.getPlacementId());
            contentValues.put(DomainCampaignEx.JSON_KEY_APK_ALT, Integer.valueOf(campaignEx.getApkAlt()));
            contentValues.put(DomainCampaignEx.JSON_KEY_ATAT_TYPE, Integer.valueOf(campaignEx.getAtatType()));
            contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPASBL, Integer.valueOf(campaignEx.getNtbarpasbl()));
            contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPT, Integer.valueOf(campaignEx.getNtbarpt()));
            contentValues.put(DomainCampaignEx.JSON_KEY_AKDLUI, campaignEx.getAkdlui());
            contentValues.put(CampaignEx.JSON_KEY_FLB, Integer.valueOf(campaignEx.getFlb()));
            contentValues.put(CampaignEx.JSON_KEY_FLB_SKIP_TIME, Integer.valueOf(campaignEx.getFlbSkipTime()));
            contentValues.put("vcn", Integer.valueOf(campaignEx.getVcn()));
            contentValues.put("token_r", Integer.valueOf(campaignEx.getTokenRule()));
            contentValues.put("encrypt_p", campaignEx.getEncryptPrice());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_CHECK_TYPE, Integer.valueOf(campaignEx.getVideoCheckType()));
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_CTN_TYPE, Integer.valueOf(campaignEx.getVideoCtnType()));
            contentValues.put(CampaignEx.JSON_KEY_RS_IGNORE_CHECK_RULE, campaignEx.getRsIgnoreCheckRuleString());
            return getWritableDatabase().insert("campaign", null, contentValues);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
            return -1L;
        }
    }

    public final synchronized void b(final List<CampaignEx> list, final String str) {
        if (list != null) {
            if (list.size() != 0) {
                new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.db.f.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            f.this.a((CampaignEx) it.next(), str, 0);
                        }
                    }
                }).start();
            }
        }
    }

    public final synchronized void c(final List<CampaignEx> list, final String str) {
        if (list != null) {
            if (list.size() != 0) {
                new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.db.f.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            f.this.b((CampaignEx) it.next(), str, 0);
                        }
                    }
                }).start();
            }
        }
    }

    public final synchronized int a(String str, long j) {
        try {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            long jB = aVarB.B() * 1000;
            a(jB, str);
            List<CampaignEx> listA = a(str, 0, 0, 1, false);
            if (listA != null && !listA.isEmpty()) {
                Iterator<CampaignEx> it = listA.iterator();
                while (it.hasNext()) {
                    if (!it.next().isSpareOffer(j, jB)) {
                        return 0;
                    }
                }
                return 1;
            }
            return -1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public final synchronized void f(String str) {
        try {
            getReadableDatabase().execSQL("UPDATE campaign SET is_download_zip='1' WHERE ia_url='" + str + "'");
        } catch (SQLException e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
    }

    public final synchronized boolean a(String str, int i, String str2, int i2, int i3, boolean z) {
        String str3;
        if (z) {
            str3 = "SELECT id FROM campaign WHERE unitid = " + str2 + " AND is_bid_campaign = 1";
        } else {
            str3 = "SELECT id FROM campaign WHERE id='" + str + "' AND tab" + ContainerUtils.KEY_VALUE_DELIMITER + i + " AND unitid = '" + str2 + "' AND " + FirebaseAnalytics.Param.LEVEL + " = " + i2 + " AND adSource = " + i3 + " AND is_bid_campaign = 0";
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery(str3, null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
            cursorRawQuery.close();
            return true;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return false;
    }

    public final List<CampaignEx> a(String str, int i, int i2, int i3) {
        String str2 = " WHERE unitid = '" + str + "' AND " + FirebaseAnalytics.Param.LEVEL + " = " + i2 + " AND adSource = " + i3;
        String str3 = "";
        if (i > 0) {
            str3 = " LIMIT " + i;
        }
        return j("SELECT * FROM campaign" + str2 + str3);
    }

    public final synchronized List<CampaignEx> a(String str, int i, int i2, int i3, boolean z) {
        String str2;
        String str3;
        String str4 = " WHERE unitid = '" + str + "' AND " + FirebaseAnalytics.Param.LEVEL + " = " + i2 + " AND adSource = " + i3;
        if (z) {
            str2 = str4 + " AND is_bid_campaign = 1";
        } else {
            str2 = str4 + " AND is_bid_campaign = 0";
        }
        str3 = "";
        if (i > 0) {
            str3 = " LIMIT " + i;
        }
        return j("SELECT * FROM campaign" + str2 + str3);
    }

    public final synchronized List<CampaignEx> a(String str, int i, int i2, int i3, String str2) {
        String str3;
        String str4;
        str3 = ((" WHERE unitid = '" + str + "' AND " + FirebaseAnalytics.Param.LEVEL + " = " + i2 + " AND adSource = " + i3) + " AND is_bid_campaign = 1") + " AND readyState = '0'";
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + " AND request_id = '" + str2 + "'";
        }
        str4 = "";
        if (i > 0) {
            str4 = " LIMIT " + i;
        }
        return j("SELECT * FROM campaign" + (str3 + " order by ts ASC") + str4);
    }

    public final synchronized List<CampaignEx> b(String str, int i, int i2, int i3) {
        String str2;
        String str3;
        str2 = " WHERE unitid = '" + str + "' AND " + FirebaseAnalytics.Param.LEVEL + " = " + i2 + " AND adSource = " + i3;
        str3 = "";
        if (i > 0) {
            str3 = " LIMIT " + i;
        }
        return j("SELECT * FROM campaign" + str2 + str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0658 A[Catch: all -> 0x0680, PHI: r10
  0x0658: PHI (r10v7 ??) = (r10v6 ??), (r10v9 ??) binds: [B:58:0x0674, B:42:0x0656] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0680, blocks: (B:39:0x0651, B:43:0x0658, B:64:0x067c, B:67:0x0682), top: B:70:0x0002 }] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v9, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> j(java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 1669
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.j(java.lang.String):java.util.List");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x0129
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Removed duplicated region for block: B:38:0x013e A[PHI: r11
  0x013e: PHI (r11v26 android.database.Cursor) = (r11v25 android.database.Cursor), (r11v31 android.database.Cursor) binds: [B:37:0x013c, B:25:0x0121] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.mbridge.msdk.foundation.entity.c> g(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.g(java.lang.String):java.util.List");
    }

    public final int d(String str, String str2) {
        String str3;
        if (!TextUtils.isEmpty(str)) {
            str3 = "unitid = '" + str2 + "' AND request_id = '" + str + "' AND is_bid_campaign = 1";
        } else {
            str3 = "unitid = '" + str2 + "' AND is_bid_campaign = 1";
        }
        String str4 = str3 + " AND readyState = '0'";
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("readyState", (Integer) 2);
            return getWritableDatabase().update("campaign", contentValues, str4, null);
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
            return 0;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:27:0x0116
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012b A[PHI: r12
  0x012b: PHI (r12v22 android.database.Cursor) = (r12v21 android.database.Cursor), (r12v27 android.database.Cursor) binds: [B:35:0x0129, B:23:0x010e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.concurrent.ConcurrentHashMap<java.lang.String, com.mbridge.msdk.foundation.entity.c> h(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.h(java.lang.String):java.util.concurrent.ConcurrentHashMap");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x071d A[PHI: r8
  0x071d: PHI (r8v8 ??) = (r8v7 ??), (r8v10 ??) binds: [B:58:0x071b, B:43:0x0700] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0724  */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r8v10, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> a(int r8, int r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1832
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.a(int, int, java.lang.String):java.util.List");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0741 A[PHI: r9
  0x0741: PHI (r9v11 ??) = (r9v10 ??), (r9v13 ??) binds: [B:55:0x073f, B:40:0x0724] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0748  */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v13, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> b(java.lang.String r9, int r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1868
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.b(java.lang.String, int):java.util.List");
    }

    public final List<CampaignEx> i(String str) {
        return k("SELECT * FROM campaign" + (" WHERE unitid = '" + str + "'"));
    }

    public final List<CampaignEx> a(JSONArray jSONArray, String str) {
        if (jSONArray == null) {
            return null;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" WHERE ");
            stringBuffer.append("unitid");
            stringBuffer.append(" = '");
            stringBuffer.append(str);
            stringBuffer.append("' AND (");
            for (int i = 0; i < jSONArray.length(); i++) {
                Long lValueOf = Long.valueOf(jSONArray.getLong(i));
                if (i != 0) {
                    stringBuffer.append(" or ");
                }
                stringBuffer.append("id");
                stringBuffer.append(" = '");
                stringBuffer.append(lValueOf);
                stringBuffer.append("'");
            }
            stringBuffer.append(" )");
            com.mbridge.msdk.foundation.tools.s.a(a, "tabWhereSb : " + ((Object) stringBuffer));
            return k("SELECT * FROM campaign" + ((Object) stringBuffer) + " AND " + CampaignEx.KEY_IA_CACHE + " = 'onelevel'");
        } catch (Throwable th) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", th.getLocalizedMessage());
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x071a A[PHI: r10
  0x071a: PHI (r10v6 ??) = (r10v5 ??), (r10v8 ??) binds: [B:55:0x0718, B:40:0x06fd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0721  */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> k(java.lang.String r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1829
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.k(java.lang.String):java.util.List");
    }

    private CampaignEx a(Cursor cursor) {
        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        CampaignEx campaignEx = new CampaignEx();
        try {
            String string = cursor.getString(cursor.getColumnIndex("pv_urls"));
            if (!TextUtils.isEmpty(string)) {
                JSONArray jSONArray = new JSONArray(string);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                campaignEx.setPv_urls(arrayList);
            }
        } catch (JSONException unused) {
        }
        campaignEx.setId(cursor.getString(cursor.getColumnIndex("id")));
        campaignEx.setTab(cursor.getInt(cursor.getColumnIndex("tab")));
        campaignEx.setPackageName(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_PACKAGE_NAME)));
        campaignEx.setAppName(cursor.getString(cursor.getColumnIndex("app_name")));
        campaignEx.setAppDesc(cursor.getString(cursor.getColumnIndex("app_desc")));
        campaignEx.setSize(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_APP_SIZE)));
        campaignEx.setImageSize(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_IMAGE_SIZE)));
        campaignEx.setIconUrl(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_ICON_URL)));
        campaignEx.setImageUrl(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_IMAGE_URL)));
        campaignEx.setImpressionURL(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_IMPRESSION_URL)));
        campaignEx.setNoticeUrl(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_NOTICE_URL)));
        campaignEx.setClickURL(cursor.getString(cursor.getColumnIndex(DownloadModel.DOWNLOAD_URL)));
        campaignEx.setWtick(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_WITHOUT_INSTALL_CHECK)));
        campaignEx.setDeepLinkUrl(cursor.getString(cursor.getColumnIndex("deeplink_url")));
        campaignEx.setUserActivation(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_USER_ACTIVATION))));
        campaignEx.setOnlyImpressionURL(cursor.getString(cursor.getColumnIndex("only_impression")));
        campaignEx.setPreClick(cursor.getInt(cursor.getColumnIndex("preclick")) == 1);
        campaignEx.setTemplate(cursor.getInt(cursor.getColumnIndex("template")));
        campaignEx.setLandingType(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_LANDING_TYPE)));
        campaignEx.setLinkType(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_LINK_TYPE)));
        campaignEx.setClick_mode(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_CLICK_MODE)));
        campaignEx.setRating(Double.parseDouble(cursor.getString(cursor.getColumnIndex("star"))));
        campaignEx.setNumberRating(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_NUMBER_RATING)));
        campaignEx.setClickInterval(cursor.getInt(cursor.getColumnIndex("cti")));
        campaignEx.setPreClickInterval(cursor.getInt(cursor.getColumnIndex("cpti")));
        campaignEx.setTimestamp(cursor.getLong(cursor.getColumnIndex("ts")));
        campaignEx.setCacheLevel(cursor.getInt(cursor.getColumnIndex(FirebaseAnalytics.Param.LEVEL)));
        campaignEx.setAdCall(cursor.getString(cursor.getColumnIndex("ad_call")));
        campaignEx.setFcb(cursor.getInt(cursor.getColumnIndex("fc_b")));
        campaignEx.setAd_url_list(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_AD_URL_LIST)));
        campaignEx.setVideoLength(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_VIDEO_LENGTHL)));
        campaignEx.setVideoSize(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_VIDEO_SIZE)));
        campaignEx.setVideoResolution(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_VIDEO_RESOLUTION)));
        campaignEx.setEndcard_click_result(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_ENDCARD_CLICK)));
        campaignEx.setVideoUrlEncode(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_VIDEO_URL)));
        campaignEx.setWatchMile(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_WATCH_MILE)));
        campaignEx.setTImp(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_T_IMP)));
        campaignEx.setBty(cursor.getInt(cursor.getColumnIndex("bty")));
        campaignEx.setAdvImp(cursor.getString(cursor.getColumnIndex("advImp")));
        campaignEx.setGuidelines(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_GUIDELINES)));
        campaignEx.setOfferType(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_OFFER_TYPE)));
        campaignEx.setHtmlUrl(cursor.getString(cursor.getColumnIndex("html_url")));
        campaignEx.setGuidelines(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_GUIDELINES)));
        campaignEx.setHtmlUrl(cursor.getString(cursor.getColumnIndex("html_url")));
        campaignEx.setEndScreenUrl(cursor.getString(cursor.getColumnIndex("end_screen_url")));
        campaignEx.setRewardName(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_REWARD_NAME)));
        campaignEx.setRewardAmount(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_REWARD_AMOUNT)));
        campaignEx.setRewardPlayStatus(cursor.getInt(cursor.getColumnIndex("reward_play_status")));
        campaignEx.setAdvId(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_ADV_ID)));
        campaignEx.setTtc_ct2(cursor.getInt(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_TTC_CT2)));
        campaignEx.setTtc_type(cursor.getInt(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_TTC_TYPE)));
        campaignEx.setRetarget_offer(cursor.getInt(cursor.getColumnIndex("retarget")));
        campaignEx.setCampaignUnitId(cursor.getString(cursor.getColumnIndex("unitid")));
        campaignEx.setNativeVideoTracking(CampaignEx.TrackingStr2Object(cursor.getString(cursor.getColumnIndex("native_ad_tracking"))));
        campaignEx.setNativeVideoTrackingString(cursor.getString(cursor.getColumnIndex("native_ad_tracking")));
        campaignEx.setVideo_end_type(cursor.getInt(cursor.getColumnIndex(CampaignEx.VIDEO_END_TYPE)));
        campaignEx.setendcard_url(cursor.getString(cursor.getColumnIndex(CampaignEx.ENDCARD_URL)));
        campaignEx.setPlayable_ads_without_video(cursor.getInt(cursor.getColumnIndex(CampaignEx.PLAYABLE_ADS_WITHOUT_VIDEO)));
        campaignEx.setLoopbackString(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.LOOPBACK)));
        campaignEx.setLoopbackMap(CampaignEx.loopbackStrToMap(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.LOOPBACK))));
        campaignEx.setRewardTemplateMode(CampaignEx.c.a(cursor.getString(cursor.getColumnIndex("reward_teamplate"))));
        campaignEx.setVideoMD5Value(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_REWARD_VIDEO_MD5)));
        campaignEx.setGifUrl(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_GIF_URL)));
        campaignEx.setNvT2(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_NV_T2)));
        campaignEx.setClickTimeOutInterval(cursor.getInt(cursor.getColumnIndex("c_coi")));
        campaignEx.setcUA(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_C_UA)));
        campaignEx.setImpUA(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_IMP_UA)));
        campaignEx.setJmPd(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_JM_PD)));
        campaignEx.setIsDeleted(cursor.getInt(cursor.getColumnIndex("is_deleted")));
        campaignEx.setIsClick(cursor.getInt(cursor.getColumnIndex("is_click")));
        campaignEx.setIsAddSuccesful(cursor.getInt(cursor.getColumnIndex("is_add_sucesful")));
        campaignEx.setIsDownLoadZip(cursor.getInt(cursor.getColumnIndex(CampaignEx.KEY_IS_DOWNLOAD)));
        campaignEx.setInteractiveCache(cursor.getString(cursor.getColumnIndex(CampaignEx.KEY_IA_CACHE)));
        campaignEx.setKeyIaOri(cursor.getInt(cursor.getColumnIndex("ia_ori")));
        campaignEx.setAdType(cursor.getInt(cursor.getColumnIndex("ad_type")));
        campaignEx.setIa_ext1(cursor.getString(cursor.getColumnIndex(CampaignEx.KEY_IA_EXT1)));
        campaignEx.setIa_ext2(cursor.getString(cursor.getColumnIndex(CampaignEx.KEY_IA_EXT2)));
        campaignEx.setKeyIaRst(cursor.getInt(cursor.getColumnIndex("ia_rst")));
        campaignEx.setKeyIaUrl(cursor.getString(cursor.getColumnIndex("ia_url")));
        campaignEx.setKeyIaIcon(cursor.getString(cursor.getColumnIndex("ia_icon")));
        campaignEx.setGhId(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.KEY_GH_ID)));
        campaignEx.setGhPath(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.KEY_GH_PATH)));
        campaignEx.setBindId(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.KEY_BIND_ID)));
        campaignEx.setOc_time(cursor.getInt(cursor.getColumnIndex(CampaignEx.KEY_OC_TIME)));
        campaignEx.setOc_type(cursor.getInt(cursor.getColumnIndex(CampaignEx.KEY_OC_TYPE)));
        campaignEx.setT_list(cursor.getString(cursor.getColumnIndex(CampaignEx.KEY_T_LIST)));
        String string2 = cursor.getString(cursor.getColumnIndex(CampaignEx.KEY_ADCHOICE));
        if (!TextUtils.isEmpty(string2)) {
            campaignEx.setAdchoice(CampaignEx.a.a(string2));
        }
        campaignEx.setAdchoiceSizeHeight(cursor.getInt(cursor.getColumnIndex("adchoice_size_height")));
        campaignEx.setAdchoiceSizeWidth(cursor.getInt(cursor.getColumnIndex("adchoice_size_width")));
        campaignEx.setPlct(cursor.getLong(cursor.getColumnIndex(CampaignEx.JSON_KEY_PLCT)));
        campaignEx.setPlctb(cursor.getLong(cursor.getColumnIndex(CampaignEx.JSON_KEY_PLCTB)));
        campaignEx.setAdZip(cursor.getString(cursor.getColumnIndex("ad_zip")));
        campaignEx.setAdHtml(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_AD_HTML)));
        campaignEx.setBannerUrl(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_BANNER_URL)));
        campaignEx.setBannerHtml(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_BANNER_HTML)));
        campaignEx.setCreativeId(cursor.getLong(cursor.getColumnIndex(CampaignEx.JSON_KEY_CREATIVE_ID)));
        campaignEx.setIsBidCampaign(cursor.getInt(cursor.getColumnIndex("is_bid_campaign")) == 1);
        campaignEx.setBidToken(cursor.getString(cursor.getColumnIndex("bid_token")));
        campaignEx.setMraid(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_MRAID)));
        campaignEx.setIsMraid(cursor.getInt(cursor.getColumnIndex("is_mraid_campaign")) == 1);
        campaignEx.setOmid(cursor.getString(cursor.getColumnIndex(CampaignEx.KEY_OMID)));
        campaignEx.setReady_rate(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_READY_RATE)));
        campaignEx.setExt_data(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_EXT_DATA)));
        campaignEx.setNscpt(cursor.getInt(cursor.getColumnIndex(CampaignUnit.JSON_KEY_NSCPT)));
        campaignEx.setMof_template_url(cursor.getString(cursor.getColumnIndex(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL)));
        campaignEx.setMof_tplid(cursor.getInt(cursor.getColumnIndex(CampaignUnit.JSON_KEY_MOF_TPLID)));
        campaignEx.setReq_ext_data(cursor.getString(cursor.getColumnIndex(CampaignUnit.JSON_KEY_REQ_EXT_DATA)));
        campaignEx.setReadyState(cursor.getInt(cursor.getColumnIndex("readyState")));
        campaignEx.setLoadTimeoutState(cursor.getInt(cursor.getColumnIndex("load_timeout")));
        campaignEx.setPlacementId(cursor.getString(cursor.getColumnIndex("placement_id")));
        campaignEx.setApkAlt(cursor.getInt(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_APK_ALT)));
        campaignEx.setAkdlui(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_AKDLUI)));
        campaignEx.setNtbarpasbl(cursor.getInt(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_NTBARPASBL)));
        campaignEx.setNtbarpt(cursor.getInt(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_NTBARPT)));
        campaignEx.setAtatType(cursor.getInt(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_ATAT_TYPE)));
        campaignEx.setRewardPlus(RewardPlus.parseByString(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_REWARD_PLUS))));
        campaignEx.setApkDisplayInfo(ApkDisplayInfo.parseByString(cursor.getString(cursor.getColumnIndex(DomainCampaignEx.JSON_KEY_APK_INFO))));
        campaignEx.setFlb(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_FLB)));
        campaignEx.setFlbSkipTime(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_FLB_SKIP_TIME)));
        campaignEx.setVcn(cursor.getInt(cursor.getColumnIndex("vcn")));
        campaignEx.setTokenRule(cursor.getInt(cursor.getColumnIndex("token_r")));
        campaignEx.setEncryptPrice(cursor.getString(cursor.getColumnIndex("encrypt_p")));
        campaignEx.setVideoCheckType(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_VIDEO_CHECK_TYPE)));
        campaignEx.setVideoCtnType(cursor.getInt(cursor.getColumnIndex(CampaignEx.JSON_KEY_VIDEO_CTN_TYPE)));
        campaignEx.setRsIgnoreCheckRuleByString(cursor.getString(cursor.getColumnIndex(CampaignEx.JSON_KEY_RS_IGNORE_CHECK_RULE)));
        return campaignEx;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final CampaignEx e(String str, String str2) throws Throwable {
        Cursor cursor;
        CampaignEx campaignEx;
        Cursor cursorA = null;
        try {
            try {
                Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT * FROM campaign where unitid ='" + str2 + "' and id = '" + str + "'", null);
                if (cursorRawQuery != null) {
                    try {
                        if (cursorRawQuery.getCount() > 0) {
                            while (cursorRawQuery.moveToNext()) {
                                cursorA = a(cursorRawQuery);
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        Cursor cursor2 = cursorA;
                        cursorA = cursorRawQuery;
                        cursor = cursor2;
                        com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
                        campaignEx = cursor;
                        if (cursorA != null) {
                            cursorA.close();
                            campaignEx = cursor;
                        }
                        return campaignEx;
                    } catch (Throwable th) {
                        th = th;
                        cursorA = cursorRawQuery;
                        if (cursorA != null) {
                            cursorA.close();
                        }
                        throw th;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                campaignEx = cursorA;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        }
        return campaignEx;
    }

    public final synchronized void f(String str, String str2) {
        try {
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str3 = "unitid = '" + str + "'";
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + " AND bid_token != '" + str2 + "'";
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("readyState", (Integer) 2);
        getWritableDatabase().update("campaign", contentValues, (str3 + " AND is_bid_campaign = 1") + " AND bid_token IS NOT NULL", null);
    }

    public final synchronized void g(String str, String str2) {
        try {
        } catch (Exception e) {
            com.mbridge.msdk.foundation.tools.s.d("CampaignDao", e.getLocalizedMessage());
        }
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            String str3 = "unitid = '" + str + "' AND bid_token = '" + str2 + "' AND bid_token IS NOT NULL";
            ContentValues contentValues = new ContentValues();
            contentValues.put("readyState", (Integer) 2);
            getWritableDatabase().update("campaign", contentValues, str3, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> b(java.lang.String r4, int r5, boolean r6) throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            if (r6 == 0) goto L2a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r6.<init>()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r1 = "placement_id = "
            r6.append(r1)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r6.append(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r4 = " AND "
            r6.append(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r4 = "ad_type"
            r6.append(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r4 = " = "
            r6.append(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r6.append(r5)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            goto L3b
        L27:
            r4 = move-exception
            r5 = r0
            goto L84
        L2a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r4.<init>()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r6 = "ad_type = "
            r4.append(r6)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r4.append(r5)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
        L3b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r5.<init>()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r6 = "SELECT * FROM campaign WHERE "
            r5.append(r6)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            r5.append(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            android.database.sqlite.SQLiteDatabase r5 = r3.getReadableDatabase()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            android.database.Cursor r4 = r5.rawQuery(r4, r0)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L93
            if (r4 == 0) goto L7d
            int r5 = r4.getCount()     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L77
            if (r5 <= 0) goto L7d
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L77
            r5.<init>()     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L77
        L61:
            boolean r6 = r4.moveToNext()     // Catch: java.lang.Exception -> L6f java.lang.Throwable -> L73
            if (r6 == 0) goto L7e
            com.mbridge.msdk.foundation.entity.CampaignEx r6 = r3.a(r4)     // Catch: java.lang.Exception -> L6f java.lang.Throwable -> L73
            r5.add(r6)     // Catch: java.lang.Exception -> L6f java.lang.Throwable -> L73
            goto L61
        L6f:
            r6 = move-exception
            r0 = r4
            r4 = r6
            goto L84
        L73:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L94
        L77:
            r5 = move-exception
            r2 = r0
            r0 = r4
            r4 = r5
            r5 = r2
            goto L84
        L7d:
            r5 = r0
        L7e:
            if (r4 == 0) goto L92
            r4.close()
            goto L92
        L84:
            java.lang.String r6 = "CampaignDao"
            java.lang.String r4 = r4.getLocalizedMessage()     // Catch: java.lang.Throwable -> L93
            com.mbridge.msdk.foundation.tools.s.d(r6, r4)     // Catch: java.lang.Throwable -> L93
            if (r0 == 0) goto L92
            r0.close()
        L92:
            return r5
        L93:
            r4 = move-exception
        L94:
            if (r0 == 0) goto L99
            r0.close()
        L99:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.f.b(java.lang.String, int, boolean):java.util.List");
    }
}
