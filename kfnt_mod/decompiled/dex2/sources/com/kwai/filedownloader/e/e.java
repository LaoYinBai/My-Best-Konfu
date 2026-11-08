package com.kwai.filedownloader.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    public final int a;
    public final long b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final boolean g;

    public static class a {
        private static final e a = new e();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007d A[Catch: all -> 0x021c, TryCatch #9 {all -> 0x021c, blocks: (B:7:0x0024, B:8:0x002d, B:9:0x0033, B:10:0x0039, B:11:0x003f, B:12:0x0045, B:40:0x0079, B:42:0x007d, B:44:0x0081, B:45:0x008b), top: B:131:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008b A[Catch: all -> 0x021c, TRY_LEAVE, TryCatch #9 {all -> 0x021c, blocks: (B:7:0x0024, B:8:0x002d, B:9:0x0033, B:10:0x0039, B:11:0x003f, B:12:0x0045, B:40:0x0079, B:42:0x007d, B:44:0x0081, B:45:0x008b), top: B:131:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0185  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private e() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.e.e.<init>():void");
    }

    public static int a(int i) {
        if (i > 12) {
            d.d(e.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i), 12, 12);
            return 12;
        }
        if (i >= 1) {
            return i;
        }
        d.d(e.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i), 1, 1);
        return 1;
    }

    public static e a() {
        return a.a;
    }
}
