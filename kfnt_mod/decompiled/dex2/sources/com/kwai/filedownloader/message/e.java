package com.kwai.filedownloader.message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private volatile g a;
    private volatile b b;

    public static final class a {
        private static final e a = new e();
    }

    public interface b {
        void a(MessageSnapshot messageSnapshot);
    }

    public static e a() {
        return a.a;
    }

    public void a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot instanceof com.kwai.filedownloader.message.b) {
            if (this.b != null) {
                this.b.a(messageSnapshot);
            }
        } else if (this.a != null) {
            this.a.a(messageSnapshot);
        }
    }

    public void a(b bVar) {
        this.b = bVar;
        if (bVar == null) {
            this.a = null;
        } else {
            this.a = new g(5, bVar);
        }
    }
}
