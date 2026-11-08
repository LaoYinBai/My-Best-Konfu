package com.mbridge.msdk.foundation.same.c;

import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.same.net.f;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.tools.s;
import java.io.File;

/* compiled from: CommonImageTask.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d extends com.mbridge.msdk.foundation.same.e.a {
    private String a;
    private String b;
    private String c;
    private boolean d = false;
    private a e;

    /* compiled from: CommonImageTask.java */
    public interface a {
        void a(String str, String str2);

        void b(String str, String str2);
    }

    @Override // com.mbridge.msdk.foundation.same.e.a
    public final void cancelTask() {
    }

    @Override // com.mbridge.msdk.foundation.same.e.a
    public final void pauseTask(boolean z) {
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final void a(a aVar) {
        this.e = aVar;
    }

    public d(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    private void b() {
        try {
            final File file = new File(this.c);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            com.mbridge.msdk.foundation.same.net.h.b.a(file, this.b, new f<Void>() { // from class: com.mbridge.msdk.foundation.same.c.d.1
                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a(long j, long j2) {
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a(k kVar) {
                    s.a("ImageWorker", "download file from [" + d.this.b + "] save to [" + d.this.c + "]");
                    d dVar = d.this;
                    String unused = d.this.c;
                    d.this.a();
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
                    d.this.a(d.this.b, "load image from http faild because http return code: " + aVar.a + ".image url is " + d.this.b);
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void b() {
                    s.b("ImageWorker", String.format("onFinish size : %s", Long.valueOf(file.length())));
                }
            });
        } catch (Exception e) {
            a(this.b, e.getMessage());
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        } catch (OutOfMemoryError e2) {
            a(this.b, e2.getMessage());
        }
    }

    protected final void a() {
        if (new File(this.c).exists()) {
            String str = this.b;
            String str2 = this.c;
            if (this.e != null) {
                this.e.a(str, str2);
                return;
            }
            return;
        }
        String str3 = "load image faild.because file[" + this.c + "] is not exist!";
        s.a("ImageWorker", str3);
        a(this.b, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.e != null) {
            this.e.b(str, str2);
        }
    }

    @Override // com.mbridge.msdk.foundation.same.e.a
    public final void runTask() {
        if (!this.d) {
            if (TextUtils.isEmpty(this.c)) {
                a(this.b, "save path is null.");
                return;
            }
            File file = new File(this.c);
            if (!file.exists() || file.length() <= 0) {
                b();
                return;
            } else {
                a();
                return;
            }
        }
        b();
    }
}
