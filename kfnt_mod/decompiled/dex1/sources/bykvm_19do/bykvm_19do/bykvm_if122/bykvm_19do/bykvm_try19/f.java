package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19;

import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private String f1920a;

    /* renamed from: b, reason: collision with root package name */
    private g f1921b;

    /* renamed from: c, reason: collision with root package name */
    private a f1922c;
    private List<e> d;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f1923a;

        /* renamed from: b, reason: collision with root package name */
        private List<i> f1924b = new ArrayList();

        public void a(String str) {
            this.f1923a = str;
        }

        public void a(List<i> list) {
            this.f1924b = list;
        }
    }

    public List<e> a() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.d;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0011 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x002b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0039 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:2: B:13:0x0039->B:53:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0075 -> B:6:0x0009). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a r7) {
        /*
            r6 = this;
            r6.f1922c = r7
        L2:
            r7 = 73
            r0 = 2
            switch(r7) {
                case 72: goto L9;
                case 73: goto L9;
                case 74: goto L67;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
            r7 = 61
            r1 = 34
            switch(r7) {
                case 59: goto L11;
                case 60: goto L2b;
                case 61: goto L39;
                default: goto L10;
            }
        L10:
            goto L9
        L11:
            r7 = 49
            int r2 = r7 * r7
            int r3 = r1 * r1
            int r2 = r2 + r3
            r3 = 18
            int r4 = r3 * r3
            int r2 = r2 + r4
            r4 = 49
            int r4 = r4 * 34
            r5 = 34
            int r5 = r5 * 18
            int r4 = r4 + r5
            int r7 = r7 * 18
            int r4 = r4 + r7
            if (r2 >= r4) goto L83
        L2b:
            r7 = 40
            int r2 = r7 * r7
            int r3 = r0 * r0
            int r2 = r2 + r3
            int r7 = r7 * 2
            int r7 = r7 * 2
            int r2 = r2 - r7
            if (r2 >= 0) goto L83
        L39:
            r7 = 52
            r2 = 93
            r3 = 85
            switch(r2) {
                case 92: goto L2;
                case 93: goto L43;
                case 94: goto L78;
                default: goto L42;
            }
        L42:
            goto L39
        L43:
            switch(r7) {
                case 52: goto L83;
                case 53: goto L47;
                case 54: goto L57;
                default: goto L46;
            }
        L46:
            goto L78
        L47:
            int r3 = r3 * r3
            r7 = 32
            int r1 = r7 * r7
            int r3 = r3 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r3 = r3 - r1
            if (r3 >= 0) goto L2
        L57:
            r7 = 80
            int r1 = r7 * r7
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r7 = r7 * 0
            int r3 = r3 + r7
            int r3 = r3 + r7
            if (r1 >= r3) goto L67
            goto L9
        L67:
            r7 = 1
            r1 = 82
            int r2 = 82 - r7
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r7
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L9
            goto L83
        L78:
            int r3 = r3 * r3
            r7 = 16
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            r7 = -1
            goto L9
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f$a):void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0011 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x002b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0039 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:2: B:13:0x0039->B:53:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0075 -> B:6:0x0009). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g r7) {
        /*
            r6 = this;
            r6.f1921b = r7
        L2:
            r7 = 73
            r0 = 2
            switch(r7) {
                case 72: goto L9;
                case 73: goto L9;
                case 74: goto L67;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
            r7 = 61
            r1 = 34
            switch(r7) {
                case 59: goto L11;
                case 60: goto L2b;
                case 61: goto L39;
                default: goto L10;
            }
        L10:
            goto L9
        L11:
            r7 = 49
            int r2 = r7 * r7
            int r3 = r1 * r1
            int r2 = r2 + r3
            r3 = 18
            int r4 = r3 * r3
            int r2 = r2 + r4
            r4 = 49
            int r4 = r4 * 34
            r5 = 34
            int r5 = r5 * 18
            int r4 = r4 + r5
            int r7 = r7 * 18
            int r4 = r4 + r7
            if (r2 >= r4) goto L83
        L2b:
            r7 = 40
            int r2 = r7 * r7
            int r3 = r0 * r0
            int r2 = r2 + r3
            int r7 = r7 * 2
            int r7 = r7 * 2
            int r2 = r2 - r7
            if (r2 >= 0) goto L83
        L39:
            r7 = 52
            r2 = 93
            r3 = 85
            switch(r2) {
                case 92: goto L2;
                case 93: goto L43;
                case 94: goto L78;
                default: goto L42;
            }
        L42:
            goto L39
        L43:
            switch(r7) {
                case 52: goto L83;
                case 53: goto L47;
                case 54: goto L57;
                default: goto L46;
            }
        L46:
            goto L78
        L47:
            int r3 = r3 * r3
            r7 = 32
            int r1 = r7 * r7
            int r3 = r3 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r3 = r3 - r1
            if (r3 >= 0) goto L2
        L57:
            r7 = 80
            int r1 = r7 * r7
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r7 = r7 * 0
            int r3 = r3 + r7
            int r3 = r3 + r7
            if (r1 >= r3) goto L67
            goto L9
        L67:
            r7 = 1
            r1 = 82
            int r2 = 82 - r7
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r7
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L9
            goto L83
        L78:
            int r3 = r3 * r3
            r7 = 16
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            r7 = -1
            goto L9
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004a, code lost:
    
        if ((((85 * 85) + (32 * 32)) - ((85 * 32) * 2)) < 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
    
        if ((((80 * 80) + r3) + r3) < ((r3 + r6) + r6)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0066, code lost:
    
        if (((85 * 85) - ((16 * 16) * 34)) != (-1)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
    
        r2 = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001e, code lost:
    
        if ((((49 * 49) + 1156) + (18 * 18)) < (((49 * 34) + (34 * 18)) + (49 * 18))) goto L7;
     */
    /* JADX WARN: Path cross not found for [B:16:0x004c, B:45:?], limit reached: 43 */
    /* JADX WARN: Path cross not found for [B:45:?, B:25:0x006f], limit reached: 43 */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0000 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0038 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[LOOP:1: B:9:0x002e->B:42:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[LOOP:2: B:22:0x0069->B:46:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006b -> B:25:0x006f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r6) {
        /*
            r5 = this;
        L0:
            r6 = 61
            r0 = 2
            switch(r6) {
                case 59: goto L7;
                case 60: goto L20;
                case 61: goto L2e;
                default: goto L6;
            }
        L6:
            goto L0
        L7:
            r6 = 49
            int r1 = r6 * r6
            int r1 = r1 + 1156
            r2 = 18
            int r3 = r2 * r2
            int r1 = r1 + r3
            r3 = 49
            int r3 = r3 * 34
            r4 = 34
            int r4 = r4 * 18
            int r3 = r3 + r4
            int r6 = r6 * 18
            int r3 = r3 + r6
            if (r1 >= r3) goto L69
        L20:
            r6 = 40
            int r1 = r6 * r6
            int r2 = r0 * r0
            int r1 = r1 + r2
            int r6 = r6 * 2
            int r6 = r6 * 2
            int r1 = r1 - r6
            if (r1 >= 0) goto L98
        L2e:
            r6 = 52
            r1 = 93
            r2 = 85
            switch(r1) {
                case 92: goto L69;
                case 93: goto L38;
                case 94: goto L5c;
                default: goto L37;
            }
        L37:
            goto L2e
        L38:
            switch(r6) {
                case 52: goto L69;
                case 53: goto L3c;
                case 54: goto L4c;
                default: goto L3b;
            }
        L3b:
            goto L5c
        L3c:
            int r2 = r2 * r2
            r6 = 32
            int r1 = r6 * r6
            int r2 = r2 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r2 = r2 - r1
            if (r2 >= 0) goto L69
        L4c:
            r6 = 80
            int r1 = r6 * r6
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r6 = r6 * 0
            int r3 = r3 + r6
            int r3 = r3 + r6
            if (r1 >= r3) goto L6f
            goto L69
        L5c:
            int r2 = r2 * r2
            r6 = 16
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r2 = r2 - r6
            r6 = -1
            if (r2 != r6) goto L69
            goto L7e
        L69:
            r6 = 73
            switch(r6) {
                case 72: goto L7e;
                case 73: goto L98;
                case 74: goto L6f;
                default: goto L6e;
            }
        L6e:
            goto L69
        L6f:
            r6 = 1
            r1 = 82
            int r2 = 82 - r6
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r6
            int r2 = r2 * r1
            int r2 = r2 % 6
            goto L69
        L7e:
            r6 = 29
            int r0 = r6 * r6
            r1 = 45
            int r2 = r1 * r1
            int r0 = r0 + r2
            r2 = 20
            int r3 = r2 * r2
            int r0 = r0 + r3
            r3 = 29
            int r3 = r3 * 45
            int r1 = r1 * 20
            int r3 = r3 + r1
            int r6 = r6 * 20
            int r3 = r3 + r6
            if (r0 >= r3) goto L0
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a(java.lang.String):void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0011 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x002b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0039 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:2: B:13:0x0039->B:53:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0075 -> B:6:0x0009). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.e> r7) {
        /*
            r6 = this;
            r6.d = r7
        L2:
            r7 = 73
            r0 = 2
            switch(r7) {
                case 72: goto L9;
                case 73: goto L9;
                case 74: goto L67;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
            r7 = 61
            r1 = 34
            switch(r7) {
                case 59: goto L11;
                case 60: goto L2b;
                case 61: goto L39;
                default: goto L10;
            }
        L10:
            goto L9
        L11:
            r7 = 49
            int r2 = r7 * r7
            int r3 = r1 * r1
            int r2 = r2 + r3
            r3 = 18
            int r4 = r3 * r3
            int r2 = r2 + r4
            r4 = 49
            int r4 = r4 * 34
            r5 = 34
            int r5 = r5 * 18
            int r4 = r4 + r5
            int r7 = r7 * 18
            int r4 = r4 + r7
            if (r2 >= r4) goto L83
        L2b:
            r7 = 40
            int r2 = r7 * r7
            int r3 = r0 * r0
            int r2 = r2 + r3
            int r7 = r7 * 2
            int r7 = r7 * 2
            int r2 = r2 - r7
            if (r2 >= 0) goto L83
        L39:
            r7 = 52
            r2 = 93
            r3 = 85
            switch(r2) {
                case 92: goto L2;
                case 93: goto L43;
                case 94: goto L78;
                default: goto L42;
            }
        L42:
            goto L39
        L43:
            switch(r7) {
                case 52: goto L83;
                case 53: goto L47;
                case 54: goto L57;
                default: goto L46;
            }
        L46:
            goto L78
        L47:
            int r3 = r3 * r3
            r7 = 32
            int r1 = r7 * r7
            int r3 = r3 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r3 = r3 - r1
            if (r3 >= 0) goto L2
        L57:
            r7 = 80
            int r1 = r7 * r7
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r7 = r7 * 0
            int r3 = r3 + r7
            int r3 = r3 + r7
            if (r1 >= r3) goto L67
            goto L9
        L67:
            r7 = 1
            r1 = 82
            int r2 = 82 - r7
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r7
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L9
            goto L83
        L78:
            int r3 = r3 * r3
            r7 = 16
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            r7 = -1
            goto L9
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a(java.util.List):void");
    }

    public String b() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.f1920a;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0011 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x002b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0039 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:2: B:13:0x0039->B:53:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0075 -> B:6:0x0009). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.lang.String r7) {
        /*
            r6 = this;
            r6.f1920a = r7
        L2:
            r7 = 73
            r0 = 2
            switch(r7) {
                case 72: goto L9;
                case 73: goto L9;
                case 74: goto L67;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
            r7 = 61
            r1 = 34
            switch(r7) {
                case 59: goto L11;
                case 60: goto L2b;
                case 61: goto L39;
                default: goto L10;
            }
        L10:
            goto L9
        L11:
            r7 = 49
            int r2 = r7 * r7
            int r3 = r1 * r1
            int r2 = r2 + r3
            r3 = 18
            int r4 = r3 * r3
            int r2 = r2 + r4
            r4 = 49
            int r4 = r4 * 34
            r5 = 34
            int r5 = r5 * 18
            int r4 = r4 + r5
            int r7 = r7 * 18
            int r4 = r4 + r7
            if (r2 >= r4) goto L83
        L2b:
            r7 = 40
            int r2 = r7 * r7
            int r3 = r0 * r0
            int r2 = r2 + r3
            int r7 = r7 * 2
            int r7 = r7 * 2
            int r2 = r2 - r7
            if (r2 >= 0) goto L83
        L39:
            r7 = 52
            r2 = 93
            r3 = 85
            switch(r2) {
                case 92: goto L2;
                case 93: goto L43;
                case 94: goto L78;
                default: goto L42;
            }
        L42:
            goto L39
        L43:
            switch(r7) {
                case 52: goto L83;
                case 53: goto L47;
                case 54: goto L57;
                default: goto L46;
            }
        L46:
            goto L78
        L47:
            int r3 = r3 * r3
            r7 = 32
            int r1 = r7 * r7
            int r3 = r3 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r3 = r3 - r1
            if (r3 >= 0) goto L2
        L57:
            r7 = 80
            int r1 = r7 * r7
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r7 = r7 * 0
            int r3 = r3 + r7
            int r3 = r3 + r7
            if (r1 >= r3) goto L67
            goto L9
        L67:
            r7 = 1
            r1 = 82
            int r2 = 82 - r7
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r7
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L9
            goto L83
        L78:
            int r3 = r3 * r3
            r7 = 16
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            r7 = -1
            goto L9
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.b(java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0004 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> c() {
        /*
            r5 = this;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f$a r0 = r5.f1922c
            if (r0 != 0) goto L54
        L4:
            r0 = 92
            r1 = 14
            switch(r1) {
                case 13: goto L12;
                case 14: goto L52;
                case 15: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L4
        Lc:
            switch(r0) {
                case 94: goto L20;
                case 95: goto L3a;
                case 96: goto L52;
                default: goto Lf;
            }
        Lf:
            r0 = 95
            goto Lc
        L12:
            r0 = 66
            int r0 = r0 * r0
            r1 = 16
            int r1 = r1 * r1
            int r1 = r1 * 34
            int r0 = r0 - r1
            r1 = -1
            if (r0 != r1) goto L3a
        L20:
            r0 = 29
            int r1 = r0 * r0
            r2 = 45
            int r3 = r2 * r2
            int r1 = r1 + r3
            r3 = 20
            int r4 = r3 * r3
            int r1 = r1 + r4
            r4 = 29
            int r4 = r4 * 45
            int r2 = r2 * 20
            int r4 = r4 + r2
            int r0 = r0 * 20
            int r4 = r4 + r0
            if (r1 >= r4) goto L52
        L3a:
            r0 = 70
            int r1 = r0 * r0
            r2 = 7
            int r3 = r2 * r2
            int r1 = r1 + r3
            r3 = 4
            int r4 = r3 * r3
            int r1 = r1 + r4
            r4 = 70
            int r4 = r4 * 7
            int r2 = r2 * 4
            int r4 = r4 + r2
            int r0 = r0 * 4
            int r4 = r4 + r0
            if (r1 >= r4) goto L4
        L52:
            r0 = 0
            return r0
        L54:
            java.util.List r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a.b(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.c():java.util.List");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004a, code lost:
    
        if ((((85 * 85) + (32 * 32)) - ((85 * 32) * 2)) < 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
    
        if ((((80 * 80) + r3) + r3) < ((r3 + r6) + r6)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0066, code lost:
    
        if (((85 * 85) - ((16 * 16) * 34)) != (-1)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
    
        r2 = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001e, code lost:
    
        if ((((49 * 49) + 1156) + (18 * 18)) < (((49 * 34) + (34 * 18)) + (49 * 18))) goto L7;
     */
    /* JADX WARN: Path cross not found for [B:16:0x004c, B:45:?], limit reached: 43 */
    /* JADX WARN: Path cross not found for [B:45:?, B:25:0x006f], limit reached: 43 */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0000 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0038 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[LOOP:1: B:9:0x002e->B:42:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[LOOP:2: B:22:0x0069->B:46:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006b -> B:25:0x006f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(java.lang.String r6) {
        /*
            r5 = this;
        L0:
            r6 = 61
            r0 = 2
            switch(r6) {
                case 59: goto L7;
                case 60: goto L20;
                case 61: goto L2e;
                default: goto L6;
            }
        L6:
            goto L0
        L7:
            r6 = 49
            int r1 = r6 * r6
            int r1 = r1 + 1156
            r2 = 18
            int r3 = r2 * r2
            int r1 = r1 + r3
            r3 = 49
            int r3 = r3 * 34
            r4 = 34
            int r4 = r4 * 18
            int r3 = r3 + r4
            int r6 = r6 * 18
            int r3 = r3 + r6
            if (r1 >= r3) goto L69
        L20:
            r6 = 40
            int r1 = r6 * r6
            int r2 = r0 * r0
            int r1 = r1 + r2
            int r6 = r6 * 2
            int r6 = r6 * 2
            int r1 = r1 - r6
            if (r1 >= 0) goto L98
        L2e:
            r6 = 52
            r1 = 93
            r2 = 85
            switch(r1) {
                case 92: goto L69;
                case 93: goto L38;
                case 94: goto L5c;
                default: goto L37;
            }
        L37:
            goto L2e
        L38:
            switch(r6) {
                case 52: goto L69;
                case 53: goto L3c;
                case 54: goto L4c;
                default: goto L3b;
            }
        L3b:
            goto L5c
        L3c:
            int r2 = r2 * r2
            r6 = 32
            int r1 = r6 * r6
            int r2 = r2 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r2 = r2 - r1
            if (r2 >= 0) goto L69
        L4c:
            r6 = 80
            int r1 = r6 * r6
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r6 = r6 * 0
            int r3 = r3 + r6
            int r3 = r3 + r6
            if (r1 >= r3) goto L6f
            goto L69
        L5c:
            int r2 = r2 * r2
            r6 = 16
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r2 = r2 - r6
            r6 = -1
            if (r2 != r6) goto L69
            goto L7e
        L69:
            r6 = 73
            switch(r6) {
                case 72: goto L7e;
                case 73: goto L98;
                case 74: goto L6f;
                default: goto L6e;
            }
        L6e:
            goto L69
        L6f:
            r6 = 1
            r1 = 82
            int r2 = 82 - r6
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r6
            int r2 = r2 * r1
            int r2 = r2 % 6
            goto L69
        L7e:
            r6 = 29
            int r0 = r6 * r6
            r1 = 45
            int r2 = r1 * r1
            int r0 = r0 + r2
            r2 = 20
            int r3 = r2 * r2
            int r0 = r0 + r3
            r3 = 29
            int r3 = r3 * 45
            int r1 = r1 * 20
            int r3 = r3 + r1
            int r6 = r6 * 20
            int r3 = r3 + r6
            if (r0 >= r3) goto L0
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.c(java.lang.String):void");
    }

    public g d() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.f1921b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if ((((85 * 85) + (32 * 32)) - ((85 * 32) * 2)) >= 0) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e() {
        /*
            r5 = this;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f$a r0 = r5.f1922c
            r1 = 0
            if (r0 != 0) goto L6
            goto L37
        L6:
            java.lang.String r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.a.a(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L12
            r0 = 1
            return r0
        L12:
            r0 = 62
            switch(r0) {
                case 60: goto L18;
                case 61: goto L26;
                case 62: goto L37;
                default: goto L17;
            }
        L17:
            goto L12
        L18:
            r0 = 18
            int r0 = r0 * r0
            r2 = 35
            int r2 = r2 * r2
            int r2 = r2 * 34
            int r0 = r0 - r2
            r2 = -1
            if (r0 != r2) goto L37
        L26:
            r0 = 85
            int r2 = r0 * r0
            r3 = 32
            int r4 = r3 * r3
            int r2 = r2 + r4
            int r0 = r0 * 32
            int r0 = r0 * 2
            int r2 = r2 - r0
            if (r2 >= 0) goto L37
        L37:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f.e():boolean");
    }
}
