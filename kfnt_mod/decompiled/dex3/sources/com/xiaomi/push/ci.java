package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ci {
    private static volatile ci a;

    /* renamed from: a, reason: collision with other field name */
    private Context f179a;

    /* renamed from: a, reason: collision with other field name */
    private ch f180a;

    /* renamed from: a, reason: collision with other field name */
    private final HashMap<String, cg> f182a = new HashMap<>();

    /* renamed from: a, reason: collision with other field name */
    private ThreadPoolExecutor f183a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: a, reason: collision with other field name */
    private final ArrayList<a> f181a = new ArrayList<>();

    public static abstract class a implements Runnable {

        /* renamed from: a, reason: collision with other field name */
        private a f185a;

        /* renamed from: a, reason: collision with other field name */
        private String f186a;

        /* renamed from: a, reason: collision with other field name */
        private WeakReference<Context> f187a;
        protected String b;

        /* renamed from: a, reason: collision with other field name */
        protected cg f184a = null;

        /* renamed from: a, reason: collision with other field name */
        private Random f188a = new Random();
        private int a = 0;

        public a(String str) {
            this.f186a = str;
        }

        public SQLiteDatabase a() {
            return this.f184a.getWritableDatabase();
        }

        /* renamed from: a, reason: collision with other method in class */
        public Object mo169a() {
            return null;
        }

        /* renamed from: a, reason: collision with other method in class */
        public String m170a() {
            return this.f186a;
        }

        void a(Context context) {
            if (this.f185a != null) {
                this.f185a.a(context, mo169a());
            }
            b(context);
        }

        public abstract void a(Context context, SQLiteDatabase sQLiteDatabase);

        public void a(Context context, Object obj) {
            ci.a(context).a(this);
        }

        void a(cg cgVar, Context context) {
            this.f184a = cgVar;
            this.b = this.f184a.a();
            this.f187a = new WeakReference<>(context);
        }

        public void a(a aVar) {
            this.f185a = aVar;
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m171a() {
            return this.f184a == null || TextUtils.isEmpty(this.b) || this.f187a == null;
        }

        public void b(Context context) {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Context context;
            if (this.f187a == null || (context = this.f187a.get()) == null || context.getFilesDir() == null || this.f184a == null || TextUtils.isEmpty(this.f186a)) {
                return;
            }
            File file = new File(this.f186a);
            v.a(context, new File(file.getParentFile(), bl.b(file.getAbsolutePath())), new ck(this, context));
        }
    }

    public static abstract class b<T> extends a {
        private int a;

        /* renamed from: a, reason: collision with other field name */
        private String f189a;

        /* renamed from: a, reason: collision with other field name */
        private List<String> f190a;

        /* renamed from: a, reason: collision with other field name */
        private String[] f191a;
        private List<T> b;
        private String c;
        private String d;
        private String e;

        public b(String str, List<String> list, String str2, String[] strArr, String str3, String str4, String str5, int i) {
            super(str);
            this.b = new ArrayList();
            this.f190a = list;
            this.f189a = str2;
            this.f191a = strArr;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.a = i;
        }

        @Override // com.xiaomi.push.ci.a
        public SQLiteDatabase a() {
            return this.f184a.getReadableDatabase();
        }

        public abstract T a(Context context, Cursor cursor);

        @Override // com.xiaomi.push.ci.a
        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            String[] strArr;
            this.b.clear();
            if (this.f190a == null || this.f190a.size() <= 0) {
                strArr = null;
            } else {
                String[] strArr2 = new String[this.f190a.size()];
                this.f190a.toArray(strArr2);
                strArr = strArr2;
            }
            Cursor cursorQuery = sQLiteDatabase.query(this.b, strArr, this.f189a, this.f191a, this.c, this.d, this.e, this.a > 0 ? String.valueOf(this.a) : null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                do {
                    T tA = a(context, cursorQuery);
                    if (tA != null) {
                        this.b.add(tA);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
            }
            a(context, (List) this.b);
        }

        public abstract void a(Context context, List<T> list);
    }

    public static class c extends a {
        private ArrayList<a> a;

        public c(String str, ArrayList<a> arrayList) {
            super(str);
            this.a = new ArrayList<>();
            this.a.addAll(arrayList);
        }

        @Override // com.xiaomi.push.ci.a
        public final void a(Context context) {
            super.a(context);
            Iterator<a> it = this.a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next != null) {
                    next.a(context);
                }
            }
        }

        @Override // com.xiaomi.push.ci.a
        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            Iterator<a> it = this.a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next != null) {
                    next.a(context, sQLiteDatabase);
                }
            }
        }
    }

    public static class d extends a {
        private String a;

        /* renamed from: a, reason: collision with other field name */
        protected String[] f192a;

        public d(String str, String str2, String[] strArr) {
            super(str);
            this.a = str2;
            this.f192a = strArr;
        }

        @Override // com.xiaomi.push.ci.a
        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.delete(this.b, this.a, this.f192a);
        }
    }

    public static class e extends a {
        private ContentValues a;

        public e(String str, ContentValues contentValues) {
            super(str);
            this.a = contentValues;
        }

        @Override // com.xiaomi.push.ci.a
        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.insert(this.b, null, this.a);
        }
    }

    private ci(Context context) {
        this.f179a = context;
    }

    private cg a(String str) {
        cg cgVarA = this.f182a.get(str);
        if (cgVarA == null) {
            synchronized (this.f182a) {
                if (cgVarA == null) {
                    try {
                        cgVarA = this.f180a.a(this.f179a, str);
                        this.f182a.put(str, cgVarA);
                    } finally {
                    }
                }
            }
        }
        return cgVarA;
    }

    public static ci a(Context context) {
        if (a == null) {
            synchronized (ci.class) {
                if (a == null) {
                    a = new ci(context);
                }
            }
        }
        return a;
    }

    private void a() {
        ai.a(this.f179a).b(new cj(this), com.xiaomi.push.service.aq.a(this.f179a).a(ho.StatDataProcessFrequency.a(), 5));
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m168a(String str) {
        return a(str).a();
    }

    public void a(a aVar) {
        cg cgVarA;
        if (aVar == null) {
            return;
        }
        if (this.f180a == null) {
            throw new IllegalStateException("should exec init method first!");
        }
        String strM170a = aVar.m170a();
        synchronized (this.f182a) {
            cgVarA = this.f182a.get(strM170a);
            if (cgVarA == null) {
                cgVarA = this.f180a.a(this.f179a, strM170a);
                this.f182a.put(strM170a, cgVarA);
            }
        }
        if (this.f183a.isShutdown()) {
            return;
        }
        aVar.a(cgVarA, this.f179a);
        synchronized (this.f181a) {
            this.f181a.add(aVar);
            a();
        }
    }

    public void a(Runnable runnable) {
        if (this.f183a.isShutdown()) {
            return;
        }
        this.f183a.execute(runnable);
    }

    public void a(ArrayList<a> arrayList) {
        if (this.f180a == null) {
            throw new IllegalStateException("should exec setDbHelperFactory method first!");
        }
        HashMap map = new HashMap();
        if (this.f183a.isShutdown()) {
            return;
        }
        Iterator<a> it = arrayList.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.m171a()) {
                next.a(a(next.m170a()), this.f179a);
            }
            ArrayList arrayList2 = (ArrayList) map.get(next.m170a());
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                map.put(next.m170a(), arrayList2);
            }
            arrayList2.add(next);
        }
        for (String str : map.keySet()) {
            ArrayList arrayList3 = (ArrayList) map.get(str);
            if (arrayList3 != null && arrayList3.size() > 0) {
                c cVar = new c(str, arrayList3);
                cVar.a(((a) arrayList3.get(0)).f184a, this.f179a);
                this.f183a.execute(cVar);
            }
        }
    }

    public void b(a aVar) {
        cg cgVarA;
        if (aVar == null) {
            return;
        }
        if (this.f180a == null) {
            throw new IllegalStateException("should exec init method first!");
        }
        String strM170a = aVar.m170a();
        synchronized (this.f182a) {
            cgVarA = this.f182a.get(strM170a);
            if (cgVarA == null) {
                cgVarA = this.f180a.a(this.f179a, strM170a);
                this.f182a.put(strM170a, cgVarA);
            }
        }
        if (this.f183a.isShutdown()) {
            return;
        }
        aVar.a(cgVarA, this.f179a);
        a((Runnable) aVar);
    }
}
