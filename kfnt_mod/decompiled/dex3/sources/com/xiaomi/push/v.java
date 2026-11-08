package com.xiaomi.push;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class v implements Runnable {
    private Context a;

    /* renamed from: a, reason: collision with other field name */
    private File f988a;

    /* renamed from: a, reason: collision with other field name */
    private Runnable f989a;

    private v(Context context, File file) {
        this.a = context;
        this.f988a = file;
    }

    /* synthetic */ v(Context context, File file, w wVar) {
        this(context, file);
    }

    public static void a(Context context, File file, Runnable runnable) {
        new w(context, file, runnable).run();
    }

    protected abstract void a(Context context);

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        u uVarA;
        u uVar = null;
        try {
            try {
                if (this.f988a == null) {
                    this.f988a = new File(this.a.getFilesDir(), "default_locker");
                }
                uVarA = u.a(this.a, this.f988a);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            if (this.f989a != null) {
                this.f989a.run();
            }
            a(this.a);
            if (uVarA != null) {
                uVarA.a();
            }
        } catch (IOException e2) {
            e = e2;
            uVar = uVarA;
            e.printStackTrace();
            if (uVar != null) {
                uVar.a();
            }
        } catch (Throwable th2) {
            th = th2;
            uVar = uVarA;
            if (uVar != null) {
                uVar.a();
            }
            throw th;
        }
    }
}
