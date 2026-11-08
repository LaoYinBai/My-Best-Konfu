package com.bun.miitmdid.supplier.msa;

import android.os.AsyncTask;
import androidx.annotation.Keep;
import com.bun.lib.c;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends AsyncTask<Void, Void, Boolean> {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    public c f3323a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    public com.bun.miitmdid.c.e.a f3324b;

    @Keep
    public a(c cVar, com.bun.miitmdid.c.e.a aVar) {
        this.f3323a = cVar;
        this.f3324b = aVar;
    }

    @Keep
    protected native Boolean a(Void... voidArr);

    @Keep
    protected native void a(Boolean bool);

    @Override // android.os.AsyncTask
    @Keep
    protected native /* bridge */ /* synthetic */ Boolean doInBackground(Void[] voidArr);

    @Override // android.os.AsyncTask
    @Keep
    protected native /* bridge */ /* synthetic */ void onPostExecute(Boolean bool);
}
