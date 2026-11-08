package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends c {
    private int X;

    protected d(Context context, String str) {
        super(context, str);
        this.X = 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00dd, code lost:
    
        if (((7 * 7) - ((1 * 1) * 34)) == (-1)) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0092, code lost:
    
        continue;
     */
    /* JADX WARN: Path cross not found for [B:38:0x00df, B:64:?], limit reached: 67 */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0139 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x009f A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00d2 -> B:27:0x009f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00dd -> B:27:0x009f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00fb -> B:27:0x009f). Please report as a decompilation issue!!! */
    @Override // com.bytedance.msdk.core.corelogic.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a() {
        /*
            Method dump skipped, instructions count: 344
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.d.a():void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[ADDED_TO_REGION, REMOVE, RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x006f -> B:19:0x004b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x006f -> B:21:0x0062). Please report as a decompilation issue!!! */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(int r6, boolean r7) {
        /*
            r5 = this;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a()
            java.lang.String r1 = r5.f
            int r0 = r0.d(r1)
            java.util.List<java.lang.Integer> r1 = r5.n
            r2 = 27
            if (r1 == 0) goto L72
            int r1 = r1.size()
            if (r6 >= r1) goto L72
            java.util.List<java.lang.Integer> r1 = r5.n
            java.lang.Object r1 = r1.get(r6)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r1 != 0) goto L72
            super.a(r6, r7)
        L27:
            r6 = 37
            r7 = 15
            switch(r2) {
                case 27: goto L2f;
                case 28: goto L6d;
                case 29: goto L6d;
                default: goto L2e;
            }
        L2e:
            goto L27
        L2f:
            r0 = 25
            switch(r6) {
                case 37: goto L9d;
                case 38: goto L35;
                case 39: goto L41;
                default: goto L34;
            }
        L34:
            goto L2f
        L35:
            r0 = 84
            int r0 = r0 * r0
            int r1 = r7 * r7
            int r1 = r1 * 34
            int r0 = r0 - r1
            r1 = -1
            if (r0 != r1) goto L9b
        L41:
            r0 = 30
            int r0 = r0 + 1
            int r0 = r0 * 30
            int r0 = r0 % 2
            if (r0 == 0) goto L27
        L4b:
            r0 = 82
            int r1 = r0 * r0
            int r2 = r7 * r7
            int r1 = r1 + r2
            r2 = 4
            int r3 = r2 * r2
            int r1 = r1 + r3
            r3 = 82
            int r3 = r3 * 15
            int r4 = r7 * 4
            int r3 = r3 + r4
            int r0 = r0 * 4
            int r3 = r3 + r0
            if (r1 >= r3) goto La1
        L62:
            r0 = 62
            int r0 = r0 + 1
            int r0 = r0 * 62
            int r0 = r0 % 2
            if (r0 == 0) goto L6d
            goto La1
        L6d:
            r0 = 61
            if (r6 <= r0) goto L4b
            goto L62
        L72:
            int r1 = r5.X
            if (r1 < r0) goto La2
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "--==--loadAdByLoadSort("
            r6.append(r7)
            int r7 = r5.X
            r6.append(r7)
            java.lang.String r7 = ")请求层数超过pre_load_sort_control("
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = ")"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "TTMediationSDK"
            com.bytedance.msdk.adapter.util.Logger.d(r7, r6)
        L9b:
            r0 = 27
        L9d:
            switch(r0) {
                case 25: goto La1;
                case 26: goto La1;
                case 27: goto La1;
                default: goto La0;
            }
        La0:
            goto L9b
        La1:
            return
        La2:
            java.util.List<java.lang.Integer> r0 = r5.n
            if (r0 == 0) goto Lc6
            int r0 = r0.size()
            if (r6 >= r0) goto Lc6
            java.util.List<java.lang.Integer> r0 = r5.n
            java.lang.Object r0 = r0.get(r6)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            com.bytedance.msdk.core.corelogic.g r1 = r5.u
            boolean r0 = r1.e(r0)
            if (r0 != 0) goto Lc6
            int r0 = r5.X
            int r0 = r0 + 1
            r5.X = r0
        Lc6:
            super.a(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.d.a(int, boolean):void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0012 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x002c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x003a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0079 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0068 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x000a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x000a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:2: B:13:0x003a->B:53:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0076 -> B:6:0x000a). Please report as a decompilation issue!!! */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(android.os.Message r7) {
        /*
            r6 = this;
            super.a(r7)
        L3:
            r7 = 73
            r0 = 2
            switch(r7) {
                case 72: goto La;
                case 73: goto La;
                case 74: goto L68;
                default: goto L9;
            }
        L9:
            goto L3
        La:
            r7 = 61
            r1 = 34
            switch(r7) {
                case 59: goto L12;
                case 60: goto L2c;
                case 61: goto L3a;
                default: goto L11;
            }
        L11:
            goto La
        L12:
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
            if (r2 >= r4) goto L84
        L2c:
            r7 = 40
            int r2 = r7 * r7
            int r3 = r0 * r0
            int r2 = r2 + r3
            int r7 = r7 * 2
            int r7 = r7 * 2
            int r2 = r2 - r7
            if (r2 >= 0) goto L84
        L3a:
            r7 = 52
            r2 = 93
            r3 = 85
            switch(r2) {
                case 92: goto L3;
                case 93: goto L44;
                case 94: goto L79;
                default: goto L43;
            }
        L43:
            goto L3a
        L44:
            switch(r7) {
                case 52: goto L84;
                case 53: goto L48;
                case 54: goto L58;
                default: goto L47;
            }
        L47:
            goto L79
        L48:
            int r3 = r3 * r3
            r7 = 32
            int r1 = r7 * r7
            int r3 = r3 + r1
            r1 = 85
            int r1 = r1 * 32
            int r1 = r1 * 2
            int r3 = r3 - r1
            if (r3 >= 0) goto L3
        L58:
            r7 = 80
            int r1 = r7 * r7
            r2 = 0
            int r3 = r2 * r2
            int r1 = r1 + r3
            int r1 = r1 + r3
            int r7 = r7 * 0
            int r3 = r3 + r7
            int r3 = r3 + r7
            if (r1 >= r3) goto L68
            goto La
        L68:
            r7 = 1
            r1 = 82
            int r2 = 82 - r7
            int r2 = r2 * 82
            int r1 = r1 * 2
            int r1 = r1 - r7
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto La
            goto L84
        L79:
            int r3 = r3 * r3
            r7 = 16
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            r7 = -1
            goto La
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.d.a(android.os.Message):void");
    }

    @Override // com.bytedance.msdk.core.corelogic.d
    public int g() {
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
                return 1;
            }
        }
    }
}
