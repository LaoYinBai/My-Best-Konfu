package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do;

import android.annotation.SuppressLint;
import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends i {

    @SuppressLint({"StaticFieldLeak"})
    private static volatile h d;

    private h(Context context) {
        super(context);
    }

    public static h a(Context context) {
        if (d == null) {
            synchronized (h.class) {
                if (d == null) {
                    d = new h(context);
                }
            }
        }
        return d;
    }
}
