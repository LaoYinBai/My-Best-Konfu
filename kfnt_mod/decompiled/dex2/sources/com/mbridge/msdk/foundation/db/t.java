package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;

/* compiled from: VideoDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class t extends a<Campaign> {
    private static t a;

    private t(h hVar) {
        super(hVar);
    }

    public static t a(h hVar) {
        if (a == null) {
            synchronized (t.class) {
                if (a == null) {
                    a = new t(hVar);
                }
            }
        }
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0098 A[Catch: all -> 0x00b8, PHI: r0 r7
  0x0098: PHI (r0v6 com.mbridge.msdk.foundation.entity.p) = (r0v4 com.mbridge.msdk.foundation.entity.p), (r0v14 com.mbridge.msdk.foundation.entity.p) binds: [B:30:0x00ac, B:22:0x0096] A[DONT_GENERATE, DONT_INLINE]
  0x0098: PHI (r7v7 android.database.Cursor) = (r7v6 android.database.Cursor), (r7v10 android.database.Cursor) binds: [B:30:0x00ac, B:22:0x0096] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #4 {, blocks: (B:3:0x0001, B:23:0x0098, B:36:0x00b4, B:37:0x00b7), top: B:45:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized com.mbridge.msdk.foundation.entity.p a(java.lang.String r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lb8
            r1 = 0
            if (r0 == 0) goto La
            monitor-exit(r6)
            return r1
        La:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            r0.<init>()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            java.lang.String r2 = "SELECT * FROM video WHERE video_url = '"
            r0.append(r2)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            r0.append(r7)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            java.lang.String r7 = "'"
            r0.append(r7)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            java.lang.String r7 = r0.toString()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            android.database.sqlite.SQLiteDatabase r0 = r6.getReadableDatabase()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            android.database.Cursor r7 = r0.rawQuery(r7, r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            if (r7 == 0) goto L95
            int r0 = r7.getCount()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb1
            if (r0 <= 0) goto L95
            com.mbridge.msdk.foundation.entity.p r0 = new com.mbridge.msdk.foundation.entity.p     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb1
            r0.<init>()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb1
        L35:
            boolean r1 = r7.moveToNext()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            if (r1 == 0) goto L96
            java.lang.String r1 = "video_url"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            r0.a(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = "video_state"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            int r1 = r7.getInt(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            r0.b(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = "pregeress_size"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            long r1 = r7.getLong(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            r0.b(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = "total_size"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            int r1 = r7.getInt(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            r0.a(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = "video_path"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            r0.b(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            java.lang.String r1 = "video_download_start"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            long r1 = r7.getLong(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r3
            r0.a(r1)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lb1
            goto L35
        L8e:
            r1 = move-exception
            goto La3
        L90:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto La3
        L95:
            r0 = r1
        L96:
            if (r7 == 0) goto Laf
        L98:
            r7.close()     // Catch: java.lang.Throwable -> Lb8
            goto Laf
        L9c:
            r0 = move-exception
            r7 = r1
            goto Lb2
        L9f:
            r7 = move-exception
            r0 = r1
            r1 = r7
            r7 = r0
        La3:
            java.lang.String r2 = "VideoDao"
            java.lang.String r1 = r1.getLocalizedMessage()     // Catch: java.lang.Throwable -> Lb1
            com.mbridge.msdk.foundation.tools.s.d(r2, r1)     // Catch: java.lang.Throwable -> Lb1
            if (r7 == 0) goto Laf
            goto L98
        Laf:
            monitor-exit(r6)
            return r0
        Lb1:
            r0 = move-exception
        Lb2:
            if (r7 == 0) goto Lb7
            r7.close()     // Catch: java.lang.Throwable -> Lb8
        Lb7:
            throw r0     // Catch: java.lang.Throwable -> Lb8
        Lb8:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.t.a(java.lang.String):com.mbridge.msdk.foundation.entity.p");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00a3 A[Catch: all -> 0x00b5, PHI: r5
  0x00a3: PHI (r5v9 android.database.Cursor) = (r5v8 android.database.Cursor), (r5v10 android.database.Cursor) binds: [B:27:0x00b0, B:19:0x00a1] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:8:0x000a, B:20:0x00a3, B:24:0x00ab, B:25:0x00ae), top: B:35:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized com.mbridge.msdk.foundation.entity.p a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> Lb5
            r0 = 0
            if (r6 == 0) goto La
            monitor-exit(r4)
            return r0
        La:
            com.mbridge.msdk.foundation.entity.p r6 = new com.mbridge.msdk.foundation.entity.p     // Catch: java.lang.Throwable -> Lb5
            r6.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r1.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = " WHERE video_url = '"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lb5
            r1.append(r5)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = "'"
            r1.append(r5)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> Lb5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r1.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = "SELECT * FROM video"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lb5
            r1.append(r5)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> Lb5
            android.database.sqlite.SQLiteDatabase r1 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laf
            android.database.Cursor r5 = r1.rawQuery(r5, r0)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laf
            if (r5 == 0) goto La1
            int r0 = r5.getCount()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            if (r0 <= 0) goto La1
        L46:
            boolean r0 = r5.moveToNext()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            if (r0 == 0) goto La1
            java.lang.String r0 = "video_url"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = r5.getString(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            r6.a(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = "video_state"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            int r0 = r5.getInt(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            r6.b(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = "pregeress_size"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            long r0 = r5.getLong(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            r6.b(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = "total_size"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            int r0 = r5.getInt(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            r6.a(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = "video_path"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = r5.getString(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            r6.b(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            java.lang.String r0 = "video_download_start"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            long r0 = r5.getLong(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r2
            r6.a(r0)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> Lb0
            goto L46
        L9f:
            r6 = move-exception
            goto La9
        La1:
            if (r5 == 0) goto Lb3
        La3:
            r5.close()     // Catch: java.lang.Throwable -> Lb5
            goto Lb3
        La7:
            r6 = move-exception
            r5 = r0
        La9:
            if (r5 == 0) goto Lae
            r5.close()     // Catch: java.lang.Throwable -> Lb5
        Lae:
            throw r6     // Catch: java.lang.Throwable -> Lb5
        Laf:
            r5 = r0
        Lb0:
            if (r5 == 0) goto Lb3
            goto La3
        Lb3:
            monitor-exit(r4)
            return r6
        Lb5:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.t.a(java.lang.String, java.lang.String):com.mbridge.msdk.foundation.entity.p");
    }

    public final synchronized void b(String str) {
        String str2;
        try {
            str2 = "video_url = '" + str + "'";
        } catch (Exception unused) {
        }
        if (getWritableDatabase() == null) {
            return;
        }
        getWritableDatabase().delete(MimeTypes.BASE_TYPE_VIDEO, str2, null);
    }

    public final synchronized void a(String str, long j) {
        if (j == 0) {
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("video_download_start", Long.valueOf(j / 1000));
            if (b(str, "")) {
                getWritableDatabase().update(MimeTypes.BASE_TYPE_VIDEO, contentValues, "video_url = '" + str + "'", null);
            }
        } catch (Exception unused) {
        }
    }

    public final synchronized long a(CampaignEx campaignEx, long j, String str, int i) {
        if (campaignEx == null) {
            return 0L;
        }
        try {
            if (getWritableDatabase() == null) {
                return -1L;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("unitid", campaignEx.getCampaignUnitId());
            contentValues.put("id", campaignEx.getId());
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
            contentValues.put(CampaignEx.JSON_KEY_AD_URL_LIST, campaignEx.getAd_url_list());
            contentValues.put(CampaignEx.JSON_KEY_VIDEO_URL, campaignEx.getVideoUrlEncode());
            contentValues.put("total_size", Long.valueOf(j));
            contentValues.put("video_state", Integer.valueOf(i));
            contentValues.put("video_download_start", Long.valueOf(System.currentTimeMillis() / 1000));
            contentValues.put("ad_bid_token", campaignEx.getBidToken());
            if (!TextUtils.isEmpty(str)) {
                contentValues.put("video_path", str);
            }
            if (b(campaignEx.getVideoUrlEncode(), campaignEx.getId())) {
                return 0L;
            }
            return getWritableDatabase().insert(MimeTypes.BASE_TYPE_VIDEO, null, contentValues);
        } catch (Exception unused) {
            return -1L;
        }
    }

    private synchronized boolean b(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "SELECT id FROM video WHERE video_url = '" + str + "'";
        } else {
            str3 = "SELECT id FROM video WHERE video_url = '" + str + "' AND id = " + str2;
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

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:18:0x0056
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public final synchronized long a(java.lang.String r4, long r5, int r7) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = -1
            android.database.sqlite.SQLiteDatabase r1 = r3.getWritableDatabase()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            if (r1 != 0) goto Lb
            long r4 = (long) r0
            monitor-exit(r3)
            return r4
        Lb:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            r1.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.String r2 = "pregeress_size"
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            r1.put(r2, r5)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.String r5 = "video_state"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            r1.put(r5, r6)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.String r5 = ""
            boolean r5 = r3.b(r4, r5)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            if (r5 == 0) goto L5b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            r5.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.String r6 = "video_url = '"
            r5.append(r6)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            r5.append(r4)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.String r4 = "'"
            r5.append(r4)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            java.lang.Object r5 = new java.lang.Object     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            r5.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            monitor-enter(r5)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L5d
            android.database.sqlite.SQLiteDatabase r6 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> L56
            java.lang.String r7 = "video"
            r2 = 0
            int r4 = r6.update(r7, r1, r4, r2)     // Catch: java.lang.Throwable -> L56
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L53
            goto L60
        L53:
            r6 = move-exception
            r0 = r4
            goto L57
        L56:
            r6 = move-exception
        L57:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L56
            throw r6     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L5d
        L59:
            r4 = r0
            goto L60
        L5b:
            r4 = -1
            goto L60
        L5d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L60:
            long r4 = (long) r4
            monitor-exit(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.t.a(java.lang.String, long, int):long");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:18:0x004d
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public final synchronized long b(java.lang.String r5, long r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = -1
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            if (r1 != 0) goto Lb
            long r5 = (long) r0
            monitor-exit(r4)
            return r5
        Lb:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            r1.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            java.lang.String r2 = "total_size"
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            r1.put(r2, r6)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            java.lang.String r6 = ""
            boolean r6 = r4.b(r5, r6)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            if (r6 == 0) goto L52
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            r6.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            java.lang.String r7 = "video_url = '"
            r6.append(r7)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            r6.append(r5)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            java.lang.String r5 = "'"
            r6.append(r5)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            java.lang.String r5 = r6.toString()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            java.lang.Object r6 = new java.lang.Object     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            r6.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            monitor-enter(r6)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L54
            android.database.sqlite.SQLiteDatabase r7 = r4.getWritableDatabase()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r2 = "video"
            r3 = 0
            int r5 = r7.update(r2, r1, r5, r3)     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L4a
            goto L57
        L4a:
            r7 = move-exception
            r0 = r5
            goto L4e
        L4d:
            r7 = move-exception
        L4e:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L4d
            throw r7     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L54
        L50:
            r5 = r0
            goto L57
        L52:
            r5 = -1
            goto L57
        L54:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L57:
            long r5 = (long) r5
            monitor-exit(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.t.b(java.lang.String, long):long");
    }
}
