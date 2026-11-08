package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c0 {
    public static void a(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
        }
    }
}
