package com.huawei.a.a.b.h;

import java.util.Calendar;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private long a = 1800000;
    private long b = 30000;
    private volatile boolean c = false;
    private volatile long d = 0;
    private a e = null;

    private class a {
        String a = UUID.randomUUID().toString().replace("-", "");
        boolean b;
        private long d;

        a(long j) {
            this.a += "_" + j;
            this.d = j;
            this.b = true;
            b.this.c = false;
        }

        private boolean a(long j, long j2) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(j2);
            return (calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) ? false : true;
        }

        private void b(long j) {
            com.huawei.a.a.b.e.a.b("SessionWrapper", "getNewSession() session is flush!");
            this.a = UUID.randomUUID().toString();
            this.a = this.a.replace("-", "");
            this.a += "_" + j;
            this.d = j;
            this.b = true;
        }

        private boolean b(long j, long j2) {
            return j2 - j >= b.this.a;
        }

        void a(long j) {
            if (b.this.c) {
                b.this.c = false;
                b(j);
            } else if (b(this.d, j) || a(this.d, j)) {
                b(j);
            } else {
                this.d = j;
                this.b = false;
            }
        }
    }

    public String a() {
        if (this.e != null) {
            return this.e.a;
        }
        com.huawei.a.a.b.e.a.c("SessionWrapper", "getSessionName(): session not prepared. onEvent() must be called first.");
        return "";
    }

    void a(long j) {
        if (this.e != null) {
            this.e.a(j);
        } else {
            com.huawei.a.a.b.e.a.b("SessionWrapper", "Session is first flush");
            this.e = new a(j);
        }
    }

    public boolean b() {
        if (this.e != null) {
            return this.e.b;
        }
        com.huawei.a.a.b.e.a.c("SessionWrapper", "isFirstEvent(): session not prepared. onEvent() must be called first.");
        return false;
    }

    public void c() {
        this.e = null;
        this.d = 0L;
        this.c = false;
    }
}
