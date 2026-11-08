package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LoadTimeDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n extends a<com.mbridge.msdk.foundation.entity.k> {
    private static n a;

    private n(h hVar) {
        super(hVar);
    }

    public static n a(h hVar) {
        if (a == null) {
            synchronized (n.class) {
                if (a == null) {
                    a = new n(hVar);
                }
            }
        }
        return a;
    }

    public final synchronized int a() {
        int i;
        Cursor cursor = null;
        i = 0;
        try {
            try {
                try {
                    Cursor cursorRawQuery = getReadableDatabase().rawQuery("select count(*) from load_stat", null);
                    if (cursorRawQuery != null) {
                        try {
                            if (cursorRawQuery.moveToFirst()) {
                                i = cursorRawQuery.getInt(0);
                            }
                        } catch (Exception e) {
                            e = e;
                            cursor = cursorRawQuery;
                            e.printStackTrace();
                            if (cursor != null) {
                                cursor.close();
                            }
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorRawQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return i;
    }

    public final synchronized List<com.mbridge.msdk.foundation.entity.k> a(int i) {
        ArrayList arrayList;
        Cursor cursorRawQuery;
        ArrayList arrayList2;
        Cursor cursor = null;
        arrayList2 = null;
        arrayList2 = null;
        cursor = null;
        try {
            try {
                cursorRawQuery = getReadableDatabase().rawQuery("select * from load_stat LIMIT " + i, null);
                if (cursorRawQuery != null) {
                    try {
                        try {
                            if (cursorRawQuery.getCount() > 0) {
                                arrayList = new ArrayList();
                                int i2 = 0;
                                while (cursorRawQuery.moveToNext() && i2 < i) {
                                    try {
                                        i2++;
                                        int i3 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(CampaignEx.JSON_KEY_AD_SOURCE_ID));
                                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("time"));
                                        int i4 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("adNum"));
                                        String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("unitId"));
                                        int i5 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("fb"));
                                        int i6 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("timeout"));
                                        int i7 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("network_type"));
                                        int i8 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(CampaignEx.JSON_KEY_HB));
                                        com.mbridge.msdk.foundation.entity.k kVar = new com.mbridge.msdk.foundation.entity.k(i3, string, i4, string2, i5, i6, i7);
                                        kVar.a(i8);
                                        arrayList.add(kVar);
                                        int i9 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("id"));
                                        if (getWritableDatabase() != null) {
                                            getWritableDatabase().delete("load_stat", "id = ?", new String[]{i9 + ""});
                                        }
                                    } catch (Exception e) {
                                        e = e;
                                        cursor = cursorRawQuery;
                                        e.printStackTrace();
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        arrayList2 = arrayList;
                                        return arrayList2;
                                    }
                                }
                                arrayList2 = arrayList;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            arrayList = null;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        throw th;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
                cursorRawQuery = cursor;
            }
        } catch (Exception e3) {
            e = e3;
            arrayList = null;
        }
        return arrayList2;
    }

    public final synchronized void a(com.mbridge.msdk.foundation.entity.k kVar) {
        if (getWritableDatabase() == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", kVar.c());
        contentValues.put(CampaignEx.JSON_KEY_AD_SOURCE_ID, Integer.valueOf(kVar.b()));
        contentValues.put("adNum", Integer.valueOf(kVar.d()));
        contentValues.put("unitId", kVar.e());
        contentValues.put("fb", Integer.valueOf(kVar.f()));
        contentValues.put(CampaignEx.JSON_KEY_HB, Integer.valueOf(kVar.a()));
        contentValues.put("timeout", Integer.valueOf(kVar.g()));
        contentValues.put("network_type", Integer.valueOf(kVar.h()));
        getWritableDatabase().insert("load_stat", null, contentValues);
    }
}
