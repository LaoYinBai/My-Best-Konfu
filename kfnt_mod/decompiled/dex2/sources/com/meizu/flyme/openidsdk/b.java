package com.meizu.flyme.openidsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {

    @Keep
    public static volatile b g;

    @Keep
    public static boolean h;

    @Keep
    public Boolean e;

    @Keep
    public BroadcastReceiver f;

    @Keep
    public OpenId a = new OpenId("udid");

    @Keep
    public OpenId b = new OpenId("oaid");

    @Keep
    public OpenId d = new OpenId("vaid");

    @Keep
    public OpenId c = new OpenId("aaid");

    @Keep
    public b() {
    }

    @Keep
    public static native ValueData a(Cursor cursor);

    @Keep
    public static final native b a();

    @Keep
    public static native void b(String str);

    @Keep
    public native OpenId a(String str);

    @Keep
    public final native String a(Context context, OpenId openId);

    @Keep
    public final native synchronized void a(Context context);

    @Keep
    public final native boolean a(Context context, boolean z);
}
