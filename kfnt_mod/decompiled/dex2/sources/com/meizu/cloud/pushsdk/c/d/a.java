package com.meizu.cloud.pushsdk.c.d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements d {
    private static final String a = "a";
    private SQLiteDatabase b;
    private final b c;
    private final String[] d = {"id", "eventData", "dateCreated"};
    private long e = -1;
    private final int f;

    public a(Context context, int i) {
        this.c = b.a(context, a(context));
        b();
        this.f = i;
    }

    private String a(Context context) {
        String processName = MzSystemUtils.getProcessName(context);
        if (TextUtils.isEmpty(processName)) {
            return "PushEvents.db";
        }
        return processName + "_PushEvents.db";
    }

    public static Map<String, String> a(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            HashMap map = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(Map<String, String> map) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> a(int i) {
        return a(null, "id ASC LIMIT " + i);
    }

    public List<Map<String, Object>> a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (e()) {
            Cursor cursorQuery = this.b.query("events", this.d, str, null, null, null, str2);
            cursorQuery.moveToFirst();
            while (!cursorQuery.isAfterLast()) {
                HashMap map = new HashMap(4);
                map.put("id", Long.valueOf(cursorQuery.getLong(0)));
                map.put("eventData", a(cursorQuery.getBlob(1)));
                map.put("dateCreated", cursorQuery.getString(2));
                cursorQuery.moveToNext();
                arrayList.add(map);
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    @Override // com.meizu.cloud.pushsdk.c.d.d
    public void a(com.meizu.cloud.pushsdk.c.a.a aVar) throws IOException {
        b(aVar);
    }

    @Override // com.meizu.cloud.pushsdk.c.d.d
    public boolean a() {
        return e();
    }

    @Override // com.meizu.cloud.pushsdk.c.d.d
    public boolean a(long j) {
        int iDelete;
        if (e()) {
            iDelete = this.b.delete("events", "id=" + j, null);
        } else {
            iDelete = -1;
        }
        com.meizu.cloud.pushsdk.c.f.c.b(a, "Removed event from database: " + j, new Object[0]);
        return iDelete == 1;
    }

    public long b(com.meizu.cloud.pushsdk.c.a.a aVar) throws IOException {
        if (e()) {
            byte[] bArrA = a((Map<String, String>) aVar.a());
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("eventData", bArrA);
            this.e = this.b.insert("events", null, contentValues);
        }
        com.meizu.cloud.pushsdk.c.f.c.b(a, "Added event to database: " + this.e, new Object[0]);
        return this.e;
    }

    public void b() {
        if (e()) {
            return;
        }
        try {
            this.b = this.c.getWritableDatabase();
            this.b.enableWriteAheadLogging();
        } catch (Exception e) {
            com.meizu.cloud.pushsdk.c.f.c.a(a, " open database error " + e.getMessage(), new Object[0]);
        }
    }

    @Override // com.meizu.cloud.pushsdk.c.d.d
    public long c() {
        if (e()) {
            return DatabaseUtils.queryNumEntries(this.b, "events");
        }
        return 0L;
    }

    @Override // com.meizu.cloud.pushsdk.c.d.d
    public com.meizu.cloud.pushsdk.c.b.b d() {
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        for (Map<String, Object> map : a(this.f)) {
            com.meizu.cloud.pushsdk.c.a.c cVar = new com.meizu.cloud.pushsdk.c.a.c();
            cVar.a((Map) map.get("eventData"));
            linkedList.add((Long) map.get("id"));
            arrayList.add(cVar);
        }
        return new com.meizu.cloud.pushsdk.c.b.b(arrayList, linkedList);
    }

    public boolean e() {
        return this.b != null && this.b.isOpen();
    }
}
