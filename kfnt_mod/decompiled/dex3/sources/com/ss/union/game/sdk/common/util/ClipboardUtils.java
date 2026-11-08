package com.ss.union.game.sdk.common.util;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.ClipboardManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ClipboardUtils {
    static final b a;

    interface b {
        String a(Context context);

        void a(Context context, CharSequence charSequence, CharSequence charSequence2);
    }

    static class a implements b {
        a() {
        }

        @Override // com.ss.union.game.sdk.common.util.ClipboardUtils.b
        public void a(Context context, CharSequence charSequence, CharSequence charSequence2) {
            ((ClipboardManager) context.getSystemService("clipboard")).setText(charSequence2);
        }

        @Override // com.ss.union.game.sdk.common.util.ClipboardUtils.b
        public String a(Context context) {
            StringBuilder sb = new StringBuilder();
            sb.append(((ClipboardManager) context.getSystemService("clipboard")).getText());
            return sb.toString();
        }
    }

    static class c implements b {
        c() {
        }

        @Override // com.ss.union.game.sdk.common.util.ClipboardUtils.b
        @TargetApi(11)
        public void a(Context context, CharSequence charSequence, CharSequence charSequence2) {
            ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
        }

        @Override // com.ss.union.game.sdk.common.util.ClipboardUtils.b
        public String a(Context context) {
            StringBuilder sb = new StringBuilder();
            android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) context.getSystemService("clipboard");
            if (clipboardManager.hasPrimaryClip()) {
                ClipData primaryClip = clipboardManager.getPrimaryClip();
                int itemCount = primaryClip.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    sb.append(primaryClip.getItemAt(i).coerceToText(context));
                }
            }
            return sb.toString();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static void setText(CharSequence charSequence, CharSequence charSequence2) {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null || charSequence == null || charSequence2 == null) {
            return;
        }
        try {
            a.a(context, charSequence, charSequence2);
        } catch (Throwable unused) {
        }
    }

    public static String getText() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            return a.a(context);
        } catch (Throwable unused) {
            return "";
        }
    }
}
