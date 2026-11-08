package com.kwad.sdk.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class u {
    private static Handler a = new Handler(Looper.getMainLooper());
    private static volatile boolean b = false;

    public static void a(Context context, String str) {
        b(context, str, R.layout.ksad_content_alliance_toast_2);
    }

    public static void a(Context context, String str, int i) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.ksad_content_alliance_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.ksad_message_toast_txt)).setText(str);
        Toast toast = new Toast(context);
        toast.setGravity(17, 0, 0);
        toast.setDuration(i);
        toast.setView(viewInflate);
        toast.show();
    }

    private static void a(Context context, String str, int i, long j) {
        if (b) {
            return;
        }
        b = true;
        View viewInflate = LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.ksad_message_toast_txt)).setText(str);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(17, 0, 0);
        toast.setDuration(0);
        toast.setView(viewInflate);
        toast.show();
        a.postDelayed(new Runnable() { // from class: com.kwad.sdk.utils.u.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = u.b = false;
            }
        }, j);
    }

    private static void b(Context context, String str, int i) {
        a(context, str, i, 800L);
    }
}
