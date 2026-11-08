package com.ss.union.game.sdk.common.util;

import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ToastUtils {
    private static volatile ToastUtils a;
    private Toast b;

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        if (a == null) {
            synchronized (ToastUtils.class) {
                if (a == null) {
                    a = new ToastUtils();
                }
            }
        }
        return a;
    }

    public void toast(String str) {
        toast(new Builder().toast(str));
    }

    public void toastRes(String str) {
        toast(ResourceUtils.getString(str));
    }

    public void toast(final Builder builder) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            a(builder);
        } else {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.common.util.ToastUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtils.this.a(builder);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Builder builder) {
        if (builder == null || TextUtils.isEmpty(builder.a)) {
            return;
        }
        a();
        try {
            b(builder);
            if (builder.b == null) {
                return;
            }
            this.b = new Toast(GlobalApplicationUtils.getContext());
            this.b.setView(builder.b);
            this.b.setGravity(builder.c, builder.d, builder.e);
            this.b.setDuration(builder.f);
            this.b.show();
        } catch (Throwable unused) {
        }
    }

    private void b(Builder builder) {
        if (builder.b == null) {
            builder.b = View.inflate(GlobalApplicationUtils.getContext(), ResourceUtils.getLayoutIdByName("lg_toast_common"), null);
            ((TextView) builder.b.findViewById(ResourceUtils.getIdByName("lg_toast_common_content"))).setText(builder.a);
        }
    }

    private void a() {
        try {
            if (this.b != null) {
                this.b.cancel();
            }
        } catch (Throwable unused) {
        }
    }

    public static class Builder {
        private String a;
        private View b;
        private int c = 17;
        private int d = 0;
        private int e = 0;
        private int f = 0;

        public Builder toast(String str) {
            this.a = str;
            return this;
        }

        public Builder view(View view) {
            this.b = view;
            return this;
        }

        public Builder gravity(int i) {
            this.c = i;
            return this;
        }

        public Builder xOffset(int i) {
            this.d = i;
            return this;
        }

        public Builder yOffset(int i) {
            this.e = i;
            return this;
        }

        public Builder duration(int i) {
            if (i == 0 || i == 1) {
                this.f = i;
            }
            return this;
        }
    }
}
