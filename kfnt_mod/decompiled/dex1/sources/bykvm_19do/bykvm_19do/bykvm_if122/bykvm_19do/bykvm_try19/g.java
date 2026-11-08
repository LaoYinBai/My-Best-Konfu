package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private String f1925a;

    /* renamed from: b, reason: collision with root package name */
    private String f1926b;

    /* renamed from: c, reason: collision with root package name */
    private String f1927c;
    private String d;
    private String e;
    private String f;
    private int g;

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0006. Please report as an issue. */
    public int a() {
        int i = this.g;
        while (true) {
            char c2 = ']';
            char c3 = ']';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                        }
                        c2 = '^';
                        c3 = 'K';
                        break;
                    case ']':
                        while (true) {
                            switch (c3) {
                                case '[':
                                case ']':
                                    break;
                                case '\\':
                                    break;
                                default:
                                    c3 = '[';
                            }
                        }
                        c2 = '^';
                        c3 = 'K';
                        break;
                    case '^':
                        if (c3 > 4) {
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
        return i;
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
    public void a(int r7) {
        /*
            r6 = this;
            r6.g = r7
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
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.a(int):void");
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
    public void a(java.lang.String r7) {
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
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.a(java.lang.String):void");
    }

    public String b() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.d;
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
    public void b(java.lang.String r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.b(java.lang.String):void");
    }

    public String c() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.f1927c;
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
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.c(java.lang.String):void");
    }

    public String d() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        if ((((85 * 85) + (32 * 32)) - ((85 * 32) * 2)) < 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
    
        if ((((80 * 80) + r3) + r3) < ((r3 + r6) + r6)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        if (((85 * 85) - ((16 * 16) * 34)) != (-1)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        r2 = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0020, code lost:
    
        if ((((49 * 49) + 1156) + (18 * 18)) < (((49 * 34) + (34 * 18)) + (49 * 18))) goto L8;
     */
    /* JADX WARN: Path cross not found for [B:17:0x004e, B:46:?], limit reached: 44 */
    /* JADX WARN: Path cross not found for [B:46:?, B:26:0x0071], limit reached: 44 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x003a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[LOOP:1: B:10:0x0030->B:43:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[LOOP:2: B:23:0x006b->B:47:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x006d -> B:26:0x0071). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(java.lang.String r6) {
        /*
            r5 = this;
            r5.f1927c = r6
        L2:
            r6 = 61
            r0 = 2
            switch(r6) {
                case 59: goto L9;
                case 60: goto L22;
                case 61: goto L30;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
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
            if (r1 >= r3) goto L6b
        L22:
            r6 = 40
            int r1 = r6 * r6
            int r2 = r0 * r0
            int r1 = r1 + r2
            int r6 = r6 * 2
            int r6 = r6 * 2
            int r1 = r1 - r6
            if (r1 >= 0) goto L9a
        L30:
            r6 = 52
            r1 = 93
            r2 = 85
            switch(r1) {
                case 92: goto L6b;
                case 93: goto L3a;
                case 94: goto L5e;
                default: goto L39;
            }
        L39:
            goto L30
        L3a:
            switch(r6) {
                case 52: goto L6b;
                case 53: goto L3e;
                case 54: goto L4e;
                default: goto L3d;
            }
        L3d:
            goto L5e
        L3e:
            int r2 = r2 * r2
            r6 = 32
            int r1 = r6 * r6
            int r2 = r2 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r2 = r2 - r1
            if (r2 >= 0) goto L6b
        L4e:
            r6 = 80
            int r1 = r6 * r6
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r6 = r6 * 0
            int r3 = r3 + r6
            int r3 = r3 + r6
            if (r1 >= r3) goto L71
            goto L6b
        L5e:
            int r2 = r2 * r2
            r6 = 16
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r2 = r2 - r6
            r6 = -1
            if (r2 != r6) goto L6b
            goto L80
        L6b:
            r6 = 73
            switch(r6) {
                case 72: goto L80;
                case 73: goto L9a;
                case 74: goto L71;
                default: goto L70;
            }
        L70:
            goto L6b
        L71:
            r6 = 1
            r1 = 82
            int r2 = 82 - r6
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r6
            int r2 = r2 * r1
            int r2 = r2 % 6
            goto L6b
        L80:
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
            if (r0 >= r3) goto L2
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.d(java.lang.String):void");
    }

    public String e() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.e;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        if ((((85 * 85) + (32 * 32)) - ((85 * 32) * 2)) < 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
    
        if ((((80 * 80) + r3) + r3) < ((r3 + r6) + r6)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        if (((85 * 85) - ((16 * 16) * 34)) != (-1)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        r2 = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0020, code lost:
    
        if ((((49 * 49) + 1156) + (18 * 18)) < (((49 * 34) + (34 * 18)) + (49 * 18))) goto L8;
     */
    /* JADX WARN: Path cross not found for [B:17:0x004e, B:46:?], limit reached: 44 */
    /* JADX WARN: Path cross not found for [B:46:?, B:26:0x0071], limit reached: 44 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x003a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[LOOP:1: B:10:0x0030->B:43:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[LOOP:2: B:23:0x006b->B:47:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x006d -> B:26:0x0071). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.String r6) {
        /*
            r5 = this;
            r5.f = r6
        L2:
            r6 = 61
            r0 = 2
            switch(r6) {
                case 59: goto L9;
                case 60: goto L22;
                case 61: goto L30;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
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
            if (r1 >= r3) goto L6b
        L22:
            r6 = 40
            int r1 = r6 * r6
            int r2 = r0 * r0
            int r1 = r1 + r2
            int r6 = r6 * 2
            int r6 = r6 * 2
            int r1 = r1 - r6
            if (r1 >= 0) goto L9a
        L30:
            r6 = 52
            r1 = 93
            r2 = 85
            switch(r1) {
                case 92: goto L6b;
                case 93: goto L3a;
                case 94: goto L5e;
                default: goto L39;
            }
        L39:
            goto L30
        L3a:
            switch(r6) {
                case 52: goto L6b;
                case 53: goto L3e;
                case 54: goto L4e;
                default: goto L3d;
            }
        L3d:
            goto L5e
        L3e:
            int r2 = r2 * r2
            r6 = 32
            int r1 = r6 * r6
            int r2 = r2 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r2 = r2 - r1
            if (r2 >= 0) goto L6b
        L4e:
            r6 = 80
            int r1 = r6 * r6
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r6 = r6 * 0
            int r3 = r3 + r6
            int r3 = r3 + r6
            if (r1 >= r3) goto L71
            goto L6b
        L5e:
            int r2 = r2 * r2
            r6 = 16
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r2 = r2 - r6
            r6 = -1
            if (r2 != r6) goto L6b
            goto L80
        L6b:
            r6 = 73
            switch(r6) {
                case 72: goto L80;
                case 73: goto L9a;
                case 74: goto L71;
                default: goto L70;
            }
        L70:
            goto L6b
        L71:
            r6 = 1
            r1 = 82
            int r2 = 82 - r6
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r6
            int r2 = r2 * r1
            int r2 = r2 % 6
            goto L6b
        L80:
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
            if (r0 >= r3) goto L2
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.e(java.lang.String):void");
    }

    public String f() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.f1925a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        if ((((85 * 85) + (32 * 32)) - ((85 * 32) * 2)) < 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
    
        if ((((80 * 80) + r3) + r3) < ((r3 + r6) + r6)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        if (((85 * 85) - ((16 * 16) * 34)) != (-1)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        r2 = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0020, code lost:
    
        if ((((49 * 49) + 1156) + (18 * 18)) < (((49 * 34) + (34 * 18)) + (49 * 18))) goto L8;
     */
    /* JADX WARN: Path cross not found for [B:17:0x004e, B:46:?], limit reached: 44 */
    /* JADX WARN: Path cross not found for [B:46:?, B:26:0x0071], limit reached: 44 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x003a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[LOOP:1: B:10:0x0030->B:43:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[LOOP:2: B:23:0x006b->B:47:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x006d -> B:26:0x0071). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(java.lang.String r6) {
        /*
            r5 = this;
            r5.e = r6
        L2:
            r6 = 61
            r0 = 2
            switch(r6) {
                case 59: goto L9;
                case 60: goto L22;
                case 61: goto L30;
                default: goto L8;
            }
        L8:
            goto L2
        L9:
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
            if (r1 >= r3) goto L6b
        L22:
            r6 = 40
            int r1 = r6 * r6
            int r2 = r0 * r0
            int r1 = r1 + r2
            int r6 = r6 * 2
            int r6 = r6 * 2
            int r1 = r1 - r6
            if (r1 >= 0) goto L9a
        L30:
            r6 = 52
            r1 = 93
            r2 = 85
            switch(r1) {
                case 92: goto L6b;
                case 93: goto L3a;
                case 94: goto L5e;
                default: goto L39;
            }
        L39:
            goto L30
        L3a:
            switch(r6) {
                case 52: goto L6b;
                case 53: goto L3e;
                case 54: goto L4e;
                default: goto L3d;
            }
        L3d:
            goto L5e
        L3e:
            int r2 = r2 * r2
            r6 = 32
            int r1 = r6 * r6
            int r2 = r2 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r2 = r2 - r1
            if (r2 >= 0) goto L6b
        L4e:
            r6 = 80
            int r1 = r6 * r6
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r6 = r6 * 0
            int r3 = r3 + r6
            int r3 = r3 + r6
            if (r1 >= r3) goto L71
            goto L6b
        L5e:
            int r2 = r2 * r2
            r6 = 16
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r2 = r2 - r6
            r6 = -1
            if (r2 != r6) goto L6b
            goto L80
        L6b:
            r6 = 73
            switch(r6) {
                case 72: goto L80;
                case 73: goto L9a;
                case 74: goto L71;
                default: goto L70;
            }
        L70:
            goto L6b
        L71:
            r6 = 1
            r1 = 82
            int r2 = 82 - r6
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r6
            int r2 = r2 * r1
            int r2 = r2 % 6
            goto L6b
        L80:
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
            if (r0 >= r3) goto L2
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.f(java.lang.String):void");
    }

    public String g() {
        while (true) {
            switch (73) {
                case 74:
                    int i = (((82 - 1) * 82) * ((82 * 2) - 1)) % 6;
                    break;
            }
        }
        return this.f1926b;
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
    public void g(java.lang.String r7) {
        /*
            r6 = this;
            r6.f1925a = r7
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
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.g(java.lang.String):void");
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
    public void h(java.lang.String r7) {
        /*
            r6 = this;
            r6.f1926b = r7
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
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g.h(java.lang.String):void");
    }
}
