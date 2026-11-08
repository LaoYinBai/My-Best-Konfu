package com.kwai.filedownloader.download;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.download.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ConnectTask {
    final int a;
    final String b;
    final com.kwai.filedownloader.c.b c;
    private com.kwai.filedownloader.download.a d;
    private String e;
    private Map<String, List<String>> f;
    private List<String> g;

    class Reconnect extends Throwable {
        private static final long serialVersionUID = 2940866805654257562L;

        Reconnect() {
        }
    }

    static class a {
        private Integer a;
        private String b;
        private String c;
        private com.kwai.filedownloader.c.b d;
        private com.kwai.filedownloader.download.a e;

        a() {
        }

        public a a(int i) {
            this.a = Integer.valueOf(i);
            return this;
        }

        public a a(com.kwai.filedownloader.c.b bVar) {
            this.d = bVar;
            return this;
        }

        public a a(com.kwai.filedownloader.download.a aVar) {
            this.e = aVar;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        ConnectTask a() {
            if (this.a == null || this.e == null || this.b == null) {
                throw new IllegalArgumentException();
            }
            return new ConnectTask(this.e, this.a.intValue(), this.b, this.c, this.d);
        }

        public a b(String str) {
            this.c = str;
            return this;
        }
    }

    private ConnectTask(com.kwai.filedownloader.download.a aVar, int i, String str, String str2, com.kwai.filedownloader.c.b bVar) {
        this.a = i;
        this.b = str;
        this.e = str2;
        this.c = bVar;
        this.d = aVar;
    }

    private void a(com.kwai.filedownloader.kwai.b bVar) {
        HashMap<String, List<String>> mapA;
        if (this.c == null || (mapA = this.c.a()) == null) {
            return;
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.e(this, "%d add outside header: %s", Integer.valueOf(this.a), mapA);
        }
        for (Map.Entry<String, List<String>> entry : mapA.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value != null) {
                Iterator<String> it = value.iterator();
                while (it.hasNext()) {
                    bVar.a(key, it.next());
                }
            }
        }
    }

    private void b(com.kwai.filedownloader.kwai.b bVar) {
        if (bVar.a(this.e, this.d.a)) {
            return;
        }
        if (!TextUtils.isEmpty(this.e)) {
            bVar.a("If-Match", this.e);
        }
        bVar.a(Command.HTTP_HEADER_RANGE, this.d.c == 0 ? com.kwai.filedownloader.e.f.a("bytes=%d-", Long.valueOf(this.d.b)) : com.kwai.filedownloader.e.f.a("bytes=%d-%d", Long.valueOf(this.d.b), Long.valueOf(this.d.c)));
    }

    private void c(com.kwai.filedownloader.kwai.b bVar) {
        if (this.c == null || this.c.a().get("User-Agent") == null) {
            bVar.a("User-Agent", com.kwai.filedownloader.e.f.e());
        }
    }

    com.kwai.filedownloader.kwai.b a() {
        com.kwai.filedownloader.kwai.b bVarA = b.a().a(this.b);
        a(bVarA);
        b(bVarA);
        c(bVarA);
        this.f = bVarA.b();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "%s request header %s", Integer.valueOf(this.a), this.f);
        }
        bVarA.d();
        this.g = new ArrayList();
        return com.kwai.filedownloader.kwai.d.a(this.f, bVarA, this.g);
    }

    boolean b() {
        return this.d.b > 0;
    }

    String c() {
        if (this.g == null || this.g.isEmpty()) {
            return null;
        }
        return this.g.get(this.g.size() - 1);
    }

    public Map<String, List<String>> d() {
        return this.f;
    }

    public com.kwai.filedownloader.download.a e() {
        return this.d;
    }
}
