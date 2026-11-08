package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12;

import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1826a = "a";

    /* renamed from: b, reason: collision with root package name */
    private static volatile a f1827b;

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f1830a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ AdError f1831b;

        b(a aVar, d dVar, AdError adError) {
            this.f1830a = dVar;
            this.f1831b = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar = this.f1830a;
            if (dVar != null) {
                dVar.a(this.f1831b);
            }
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f1832a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f1833b;

        c(a aVar, d dVar, f fVar) {
            this.f1832a = dVar;
            this.f1833b = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar = this.f1832a;
            if (dVar != null) {
                dVar.a(this.f1833b);
            }
        }
    }

    public interface d {
        void a(f fVar);

        void a(AdError adError);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x001e, code lost:
    
        return 4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(int r3) {
        /*
        L0:
            r0 = 93
            r1 = 93
        L4:
            r2 = 4
            switch(r0) {
                case 92: goto Lc;
                case 93: goto Lf;
                case 94: goto L9;
                default: goto L8;
            }
        L8:
            goto L0
        L9:
            if (r1 > r2) goto L1e
            goto L0
        Lc:
            switch(r1) {
                case 21: goto L1e;
                case 22: goto L1b;
                case 23: goto L19;
                default: goto Lf;
            }
        Lf:
            switch(r1) {
                case 91: goto L1f;
                case 92: goto L1e;
                case 93: goto L13;
                default: goto L12;
            }
        L12:
            goto L24
        L13:
            r0 = 3
            switch(r3) {
                case 1: goto L1b;
                case 2: goto L1f;
                case 3: goto L19;
                case 4: goto L19;
                case 5: goto L18;
                case 6: goto L17;
                case 7: goto L19;
                case 8: goto L19;
                case 9: goto L18;
                default: goto L17;
            }
        L17:
            goto L1d
        L18:
            return r0
        L19:
            r3 = 5
            return r3
        L1b:
            r3 = 2
            return r3
        L1d:
            return r0
        L1e:
            return r2
        L1f:
            r0 = 94
            r1 = 75
            goto L4
        L24:
            r1 = 91
            goto Lf
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(int):int");
    }

    static /* synthetic */ String a() {
        while (true) {
            char c2 = ']';
            char c3 = ']';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                            case 22:
                            case 23:
                                c2 = '^';
                                c3 = 'K';
                        }
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
        return f1826a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
    
        if (((((26 * 26) * 26) + ((9 * 9) * 9)) + ((15 * 15) * 15)) >= (((26 * 9) * 15) * 3)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
    
        if (((((40 - 1) * 40) * ((40 * 2) - 1)) % 6) == 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0000, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0021. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(com.bytedance.msdk.api.AdSlot r7, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i r8) {
        /*
            r6 = this;
        L0:
            r0 = 49
            r1 = 60
            r2 = 1
            switch(r1) {
                case 60: goto L9;
                case 61: goto L32;
                case 62: goto L4e;
                default: goto L8;
            }
        L8:
            goto L0
        L9:
            switch(r0) {
                case 49: goto L5d;
                case 50: goto Ld;
                case 51: goto Ld;
                default: goto Lc;
            }
        Lc:
            goto L32
        Ld:
            r0 = 87
            int r1 = 87 - r2
            int r1 = r1 * 87
            int r0 = r0 * 2
            int r0 = r0 - r2
            int r1 = r1 * r0
            int r1 = r1 % 6
            if (r1 == 0) goto L1d
            goto L71
        L1d:
            r0 = 94
            r1 = 94
        L21:
            switch(r1) {
                case 55: goto L25;
                case 56: goto L0;
                case 57: goto L0;
                default: goto L24;
            }
        L24:
            goto L2f
        L25:
            r1 = 94
            int r1 = r1 + r2
            int r1 = r1 * 94
            int r1 = r1 % 2
            if (r1 == 0) goto L5d
            goto L0
        L2f:
            r1 = 56
            goto L21
        L32:
            r0 = 26
            int r1 = r0 * r0
            int r1 = r1 * 26
            r3 = 9
            int r4 = r3 * r3
            int r4 = r4 * 9
            int r1 = r1 + r4
            r4 = 15
            int r5 = r4 * r4
            int r5 = r5 * 15
            int r1 = r1 + r5
            int r0 = r0 * 9
            int r0 = r0 * 15
            int r0 = r0 * 3
            if (r1 >= r0) goto L5d
        L4e:
            r0 = 40
            int r1 = 40 - r2
            int r1 = r1 * 40
            int r0 = r0 * 2
            int r0 = r0 - r2
            int r1 = r1 * r0
            int r1 = r1 % 6
            if (r1 == 0) goto L71
        L5d:
            r0 = 53
            switch(r0) {
                case 52: goto L63;
                case 53: goto L71;
                case 54: goto L5d;
                default: goto L62;
            }
        L62:
            goto L5d
        L63:
            r0 = 13
            int r0 = r0 * r0
            r1 = 19
            int r1 = r1 * r1
            int r1 = r1 * 34
            int r0 = r0 - r1
            r1 = -1
            if (r0 != r1) goto L63
        L71:
            r0 = 0
            if (r7 == 0) goto L78
            java.lang.String r0 = r7.getLinkedId()
        L78:
            if (r8 == 0) goto L9e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r0)
            java.lang.String r0 = "_"
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            java.lang.String r7 = r8.d()
            r0.append(r7)
            java.lang.String r0 = r0.toString()
        L9e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(com.bytedance.msdk.api.AdSlot, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:158:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(com.bytedance.msdk.api.AdSlot r17, java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> r18, java.util.List<com.bytedance.msdk.base.TTBaseAd> r19, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c r20, int r21) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(com.bytedance.msdk.api.AdSlot, java.util.List, java.util.List, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c, int):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
    
        if (((((26 * 26) * 26) + ((9 * 9) * 9)) + ((15 * 15) * 15)) < (((26 * 9) * 15) * 3)) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0024, code lost:
    
        r8 = '7';
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0024 -> B:10:0x0022). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0080 -> B:10:0x0022). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0089 -> B:15:0x0038). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0073 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0014 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0014 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0014 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0014 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x004d -> B:16:0x002e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0071 -> B:18:0x0038). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0099 -> B:24:0x0073). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(org.json.JSONObject r7, boolean r8) throws org.json.JSONException {
        /*
            r6 = this;
            org.json.JSONObject r7 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e0.a(r7)
            if (r7 == 0) goto L14
            java.lang.String r0 = "token_type"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Exception -> Lf
            r7.putOpt(r0, r8)     // Catch: java.lang.Exception -> Lf
        Lf:
            java.lang.String r7 = r7.toString()
            return r7
        L14:
            r7 = 729(0x2d9, float:1.022E-42)
            r8 = 400(0x190, float:5.6E-43)
            r0 = 12
            r1 = 92
            r2 = 14
            r3 = 15
            r4 = 4
            switch(r2) {
                case 13: goto L2e;
                case 14: goto L2c;
                case 15: goto L25;
                default: goto L24;
            }
        L24:
            goto L14
        L25:
            switch(r1) {
                case 94: goto L38;
                case 95: goto L4f;
                case 96: goto L65;
                default: goto L28;
            }
        L28:
            switch(r0) {
                case 55: goto L73;
                case 56: goto L87;
                case 57: goto L91;
                default: goto L2b;
            }
        L2b:
            goto L2e
        L2c:
            r7 = 0
            return r7
        L2e:
            r0 = 39
            int r0 = r0 + 1
            int r0 = r0 * 39
            int r0 = r0 % 2
            if (r0 == 0) goto L14
        L38:
            r0 = 70
            int r1 = r0 * r0
            r2 = 7
            int r5 = r2 * r2
            int r1 = r1 + r5
            int r5 = r4 * r4
            int r1 = r1 + r5
            r5 = 70
            int r5 = r5 * 7
            int r2 = r2 * 4
            int r5 = r5 + r2
            int r0 = r0 * 4
            int r5 = r5 + r0
            if (r1 >= r5) goto L2e
        L4f:
            r0 = 69
            int r1 = r0 * r0
            int r1 = r1 + r8
            int r2 = r4 * r4
            int r1 = r1 + r2
            r2 = 69
            r5 = 20
            int r2 = r2 * 20
            int r5 = r5 * 4
            int r2 = r2 + r5
            int r0 = r0 * 4
            int r2 = r2 + r0
            if (r1 >= r2) goto L14
        L65:
            r0 = 18
            int r0 = r0 * r0
            r1 = 35
            int r1 = r1 * r1
            int r1 = r1 * 34
            int r0 = r0 - r1
            r1 = -1
            if (r0 != r1) goto L38
        L73:
            r8 = 26
            int r0 = r8 * r8
            int r0 = r0 * 26
            int r0 = r0 + r7
            int r1 = r3 * r3
            int r1 = r1 * 15
            int r0 = r0 + r1
            int r8 = r8 * 9
            int r8 = r8 * 15
            int r8 = r8 * 3
            if (r0 >= r8) goto L14
        L87:
            r8 = 90
            int r8 = r8 + 1
            int r8 = r8 * 90
            int r8 = r8 % 2
            if (r8 == 0) goto L14
        L91:
            r8 = 15
            int r8 = r8 + 1
            int r8 = r8 * 15
            int r8 = r8 % 2
            if (r8 == 0) goto L73
            goto L14
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
    
        if (((18 * 18) - ((35 * 35) * 34)) == (-1)) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bf, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0014. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0072 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0070 -> B:17:0x0046). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject a(com.bytedance.msdk.api.AdSlot r10) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(com.bytedance.msdk.api.AdSlot):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0046, code lost:
    
        r5 = 30;
        r6 = (30 - 1) * 30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004c, code lost:
    
        r6 = (r6 * ((r5 * 2) - 1)) % 6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d r5, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f r6) {
        /*
            r4 = this;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$c r0 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$c
            r0.<init>(r4, r5, r6)
            com.bytedance.msdk.adapter.util.ThreadHelper.runOnMSDKThread(r0)
        L8:
            r5 = 37
            r6 = 54
            r0 = 1
            switch(r6) {
                case 52: goto L11;
                case 53: goto L46;
                case 54: goto L53;
                default: goto L10;
            }
        L10:
            goto L8
        L11:
            switch(r5) {
                case 94: goto L15;
                case 95: goto L23;
                case 96: goto L3f;
                default: goto L14;
            }
        L14:
            goto L46
        L15:
            r5 = 18
            int r5 = r5 * r5
            r6 = 35
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r5 = r5 - r6
            r6 = -1
            if (r5 != r6) goto L53
        L23:
            r5 = 26
            int r6 = r5 * r5
            int r6 = r6 * 26
            r1 = 9
            int r2 = r1 * r1
            int r2 = r2 * 9
            int r6 = r6 + r2
            r2 = 15
            int r3 = r2 * r2
            int r3 = r3 * 15
            int r6 = r6 + r3
            int r5 = r5 * 9
            int r5 = r5 * 15
            int r5 = r5 * 3
            if (r6 >= r5) goto L53
        L3f:
            r5 = 40
            int r6 = 40 - r0
            int r6 = r6 * 40
            goto L4c
        L46:
            r5 = 30
            int r6 = 30 - r0
            int r6 = r6 * 30
        L4c:
            int r5 = r5 * 2
            int r5 = r5 - r0
            int r6 = r6 * r5
            int r6 = r6 % 6
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$d, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0046, code lost:
    
        r5 = 30;
        r6 = (30 - 1) * 30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004c, code lost:
    
        r6 = (r6 * ((r5 * 2) - 1)) % 6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d r5, com.bytedance.msdk.api.AdError r6) {
        /*
            r4 = this;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$b r0 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$b
            r0.<init>(r4, r5, r6)
            com.bytedance.msdk.adapter.util.ThreadHelper.runOnMSDKThread(r0)
        L8:
            r5 = 37
            r6 = 54
            r0 = 1
            switch(r6) {
                case 52: goto L11;
                case 53: goto L46;
                case 54: goto L53;
                default: goto L10;
            }
        L10:
            goto L8
        L11:
            switch(r5) {
                case 94: goto L15;
                case 95: goto L23;
                case 96: goto L3f;
                default: goto L14;
            }
        L14:
            goto L46
        L15:
            r5 = 18
            int r5 = r5 * r5
            r6 = 35
            int r6 = r6 * r6
            int r6 = r6 * 34
            int r5 = r5 - r6
            r6 = -1
            if (r5 != r6) goto L53
        L23:
            r5 = 26
            int r6 = r5 * r5
            int r6 = r6 * 26
            r1 = 9
            int r2 = r1 * r1
            int r2 = r2 * 9
            int r6 = r6 + r2
            r2 = 15
            int r3 = r2 * r2
            int r3 = r3 * 15
            int r6 = r6 + r3
            int r5 = r5 * 9
            int r5 = r5 * 15
            int r5 = r5 * 3
            if (r6 >= r5) goto L53
        L3f:
            r5 = 40
            int r6 = 40 - r0
            int r6 = r6 * 40
            goto L4c
        L46:
            r5 = 30
            int r6 = 30 - r0
            int r6 = r6 * 30
        L4c:
            int r5 = r5 * 2
            int r5 = r5 - r0
            int r6 = r6 * r5
            int r6 = r6 % 6
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$d, com.bytedance.msdk.api.AdError):void");
    }

    static /* synthetic */ void a(a aVar, d dVar, f fVar) {
        aVar.a(dVar, fVar);
        while (true) {
            char c2 = '^';
            char c3 = 'K';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                            case 21:
                            case 22:
                            case 23:
                                continue;
                                c2 = ']';
                                c3 = ']';
                        }
                        break;
                    case ']':
                        break;
                    case '^':
                    default:
                        c2 = ']';
                        c3 = ']';
                }
                while (true) {
                    switch (c3) {
                        case '[':
                            break;
                        case '\\':
                            break;
                        case ']':
                            return;
                        default:
                            c3 = '[';
                    }
                }
                c2 = ']';
                c3 = ']';
            }
        }
    }

    static /* synthetic */ void a(a aVar, d dVar, AdError adError) {
        aVar.a(dVar, adError);
        while (true) {
            char c2 = '^';
            char c3 = 'K';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                            case 21:
                            case 22:
                            case 23:
                                continue;
                                c2 = ']';
                                c3 = ']';
                        }
                        break;
                    case ']':
                        break;
                    case '^':
                    default:
                        c2 = ']';
                        c3 = ']';
                }
                while (true) {
                    switch (c3) {
                        case '[':
                            break;
                        case '\\':
                            break;
                        case ']':
                            return;
                        default:
                            c3 = '[';
                    }
                }
                c2 = ']';
                c3 = ']';
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:281)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:281)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:281)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x003a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0008. Please report as an issue. */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x006a -> B:22:0x003e). Please report as a decompilation issue!!! */
    private void a(org.json.JSONObject r6) throws org.json.JSONException {
        /*
            r5 = this;
        L0:
            r0 = 92
        L2:
            r1 = 14
            r2 = 15
        L6:
            r3 = 12
            switch(r1) {
                case 13: goto L35;
                case 14: goto L6d;
                case 15: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L2
        Lc:
            switch(r0) {
                case 94: goto L6d;
                case 95: goto L10;
                case 96: goto L6d;
                default: goto Lf;
            }
        Lf:
            goto L3a
        L10:
            android.content.Context r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e.b(r0)
            if (r0 == 0) goto L34
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L34
            r1.<init>()     // Catch: java.lang.Exception -> L34
            java.lang.String r2 = "latitude"
            float r3 = r0.f1988a     // Catch: java.lang.Exception -> L34
            double r3 = (double) r3     // Catch: java.lang.Exception -> L34
            r1.put(r2, r3)     // Catch: java.lang.Exception -> L34
            java.lang.String r2 = "longitude"
            float r0 = r0.f1989b     // Catch: java.lang.Exception -> L34
            double r3 = (double) r0     // Catch: java.lang.Exception -> L34
            r1.put(r2, r3)     // Catch: java.lang.Exception -> L34
            java.lang.String r0 = "geo"
            r6.put(r0, r1)     // Catch: java.lang.Exception -> L34
        L34:
            return
        L35:
            r1 = 72
            if (r0 < r1) goto L3a
            goto L6d
        L3a:
            switch(r3) {
                case 55: goto L3e;
                case 56: goto L58;
                case 57: goto L62;
                default: goto L3d;
            }
        L3d:
            goto L6d
        L3e:
            r0 = 26
            int r1 = r0 * r0
            int r1 = r1 * 26
            r3 = 9
            int r4 = r3 * r3
            int r4 = r4 * 9
            int r1 = r1 + r4
            int r4 = r2 * r2
            int r4 = r4 * 15
            int r1 = r1 + r4
            int r0 = r0 * 9
            int r0 = r0 * 15
            int r0 = r0 * 3
            if (r1 >= r0) goto L0
        L58:
            r0 = 90
            int r0 = r0 + 1
            int r0 = r0 * 90
            int r0 = r0 % 2
            if (r0 == 0) goto L0
        L62:
            r0 = 15
            int r0 = r0 + 1
            int r0 = r0 * 15
            int r0 = r0 % 2
            if (r0 == 0) goto L3e
            goto L0
        L6d:
            r0 = 95
            r1 = 15
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(org.json.JSONObject):void");
    }

    private void a(JSONObject jSONObject, String str, int i, int i2) throws JSONException {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject2.put("width", i);
            jSONObject2.put("height", i2);
            jSONArray.put(jSONObject2);
            jSONObject.put(str, jSONArray);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
    
        if (((((0 - 1) * 0) * ((0 * 2) - 1)) % 6) != 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        if (r1 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0012  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0066 A[PHI: r1
  0x0066: PHI (r1v3 boolean) = (r1v11 boolean), (r1v2 boolean), (r1v2 boolean) binds: [B:27:0x005a, B:8:0x000e, B:7:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0043 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject b() {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = 74
            r3 = 55
        L6:
            r4 = 0
            switch(r2) {
                case 72: goto L2;
                case 73: goto Lb;
                case 74: goto Le;
                default: goto La;
            }
        La:
            goto L6b
        Lb:
            switch(r3) {
                case 94: goto L66;
                case 95: goto L12;
                case 96: goto L43;
                default: goto Le;
            }
        Le:
            switch(r3) {
                case 55: goto L5a;
                case 56: goto L43;
                case 57: goto L12;
                default: goto L11;
            }
        L11:
            goto L66
        L12:
            r2 = 18
            r3 = 1
            switch(r3) {
                case 60: goto L19;
                case 61: goto L27;
                case 62: goto L36;
                default: goto L18;
            }
        L18:
            goto L43
        L19:
            int r5 = 0 - r3
            int r5 = r5 * 0
            r6 = 0
            int r6 = r6 * 2
            int r6 = r6 - r3
            int r5 = r5 * r6
            int r5 = r5 % 6
            if (r5 == 0) goto L5a
        L27:
            int r5 = 18 - r3
            int r5 = r5 * 18
            r6 = 18
            int r6 = r6 * 2
            int r6 = r6 - r3
            int r5 = r5 * r6
            int r5 = r5 % 6
            if (r5 == 0) goto L43
        L36:
            r1 = 99
            int r1 = r1 * r1
            int r2 = r2 * r2
            int r2 = r2 * 34
            int r1 = r1 - r2
            r2 = -1
            if (r1 != r2) goto L45
            goto L5a
        L43:
            if (r1 == 0) goto L46
        L45:
            return r4
        L46:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L54
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()     // Catch: java.lang.Exception -> L54
            java.lang.String r1 = r1.f()     // Catch: java.lang.Exception -> L54
            r0.<init>(r1)     // Catch: java.lang.Exception -> L54
            goto L59
        L54:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r4
        L59:
            return r0
        L5a:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()
            java.lang.String r1 = r1.f()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
        L66:
            r2 = 73
            r3 = 96
            goto L6
        L6b:
            r2 = 72
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.b():org.json.JSONObject");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0006. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0019. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000a A[PHI: r0 r9
  0x000a: PHI (r0v25 bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a) = 
  (r0v24 bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a)
  (r0v1 bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a)
 binds: [B:18:0x0060, B:61:0x000a] A[DONT_GENERATE, DONT_INLINE]
  0x000a: PHI (r9v18 com.bytedance.msdk.api.AdSlot) = (r9v17 com.bytedance.msdk.api.AdSlot), (r9v1 com.bytedance.msdk.api.AdSlot) binds: [B:18:0x0060, B:61:0x000a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[PHI: r0 r9
  PHI (r0v3 bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a) = 
  (r0v1 bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a)
  (r0v25 bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a)
 binds: [B:62:0x0017, B:7:0x0015] A[DONT_GENERATE, DONT_INLINE]
  PHI (r9v3 com.bytedance.msdk.api.AdSlot) = (r9v1 com.bytedance.msdk.api.AdSlot), (r9v18 com.bytedance.msdk.api.AdSlot) binds: [B:62:0x0017, B:7:0x0015] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0060 -> B:20:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0060 -> B:6:0x000a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0091 -> B:20:0x0063). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject b(com.bytedance.msdk.api.AdSlot r9) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.b(com.bytedance.msdk.api.AdSlot):org.json.JSONObject");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0012. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0063 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0007 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0007 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0051 -> B:10:0x0016). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0094 -> B:22:0x0063). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject b(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 == 0) goto L99
        L7:
            r7 = 125(0x7d, float:1.75E-43)
            r0 = 94
            r2 = 15
            switch(r0) {
                case 94: goto L98;
                case 95: goto L11;
                case 96: goto L5f;
                default: goto L10;
            }
        L10:
            goto L7
        L11:
            r0 = 4
            switch(r7) {
                case 94: goto L16;
                case 95: goto L2d;
                case 96: goto L45;
                default: goto L15;
            }
        L15:
            goto L5f
        L16:
            r7 = 70
            int r3 = r7 * r7
            r4 = 7
            int r5 = r4 * r4
            int r3 = r3 + r5
            int r5 = r0 * r0
            int r3 = r3 + r5
            r5 = 70
            int r5 = r5 * 7
            int r4 = r4 * 4
            int r5 = r5 + r4
            int r7 = r7 * 4
            int r5 = r5 + r7
            if (r3 >= r5) goto L54
        L2d:
            r7 = 69
            int r3 = r7 * r7
            r4 = 20
            int r5 = r4 * r4
            int r3 = r3 + r5
            int r5 = r0 * r0
            int r3 = r3 + r5
            r5 = 69
            int r5 = r5 * 20
            int r4 = r4 * 4
            int r5 = r5 + r4
            int r7 = r7 * 4
            int r5 = r5 + r7
            if (r3 >= r5) goto L7
        L45:
            r7 = 18
            int r7 = r7 * r7
            r3 = 35
            int r3 = r3 * r3
            int r3 = r3 * 34
            int r7 = r7 - r3
            r3 = -1
            if (r7 != r3) goto L16
            goto L63
        L54:
            r7 = 39
            int r7 = r7 + 1
            int r7 = r7 * 39
            int r7 = r7 % 2
            if (r7 == 0) goto L7
            goto L16
        L5f:
            switch(r7) {
                case 55: goto L63;
                case 56: goto L7d;
                case 57: goto L8c;
                default: goto L62;
            }
        L62:
            goto L98
        L63:
            r7 = 26
            int r0 = r7 * r7
            int r0 = r0 * 26
            r3 = 9
            int r4 = r3 * r3
            int r4 = r4 * 9
            int r0 = r0 + r4
            int r4 = r2 * r2
            int r4 = r4 * 15
            int r0 = r0 + r4
            int r7 = r7 * 9
            int r7 = r7 * 15
            int r7 = r7 * 3
            if (r0 >= r7) goto L7
        L7d:
            r7 = 80
            int r0 = r7 * r7
            r3 = 0
            int r4 = r3 * r3
            int r0 = r0 + r4
            int r0 = r0 + r4
            int r7 = r7 * 0
            int r4 = r4 + r7
            int r4 = r4 + r7
            if (r0 >= r4) goto L7
        L8c:
            r7 = 15
            int r7 = r7 + 1
            int r7 = r7 * 15
            int r7 = r7 % 2
            if (r7 == 0) goto L63
            goto L7
        L98:
            return r1
        L99:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L9f
            r0.<init>(r7)     // Catch: java.lang.Exception -> L9f
            goto La4
        L9f:
            r7 = move-exception
            r7.printStackTrace()
            r0 = r1
        La4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.b(java.lang.String):org.json.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        r1 = '[';
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000d, code lost:
    
        switch(r1) {
            case 91: goto L25;
            case 92: goto L21;
            case 93: goto L21;
            default: goto L15;
        };
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(org.json.JSONObject r4) throws org.json.JSONException {
        /*
            r3 = this;
        L0:
            r0 = 94
            r1 = 75
            r2 = 93
        L6:
            switch(r0) {
                case 92: goto La;
                case 93: goto Ld;
                case 94: goto L30;
                default: goto L9;
            }
        L9:
            goto L30
        La:
            switch(r1) {
                case 21: goto L11;
                case 22: goto L11;
                case 23: goto L11;
                default: goto Ld;
            }
        Ld:
            switch(r1) {
                case 91: goto L0;
                case 92: goto L11;
                case 93: goto L11;
                default: goto L10;
            }
        L10:
            goto L2d
        L11:
            java.lang.String r0 = "package_name"
            java.lang.String r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e0.a()     // Catch: java.lang.Exception -> L2c
            r4.put(r0, r1)     // Catch: java.lang.Exception -> L2c
            java.lang.String r0 = "version_code"
            java.lang.String r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e0.d()     // Catch: java.lang.Exception -> L2c
            r4.put(r0, r1)     // Catch: java.lang.Exception -> L2c
            java.lang.String r0 = "version"
            java.lang.String r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e0.e()     // Catch: java.lang.Exception -> L2c
            r4.put(r0, r1)     // Catch: java.lang.Exception -> L2c
        L2c:
            return
        L2d:
            r1 = 91
            goto Ld
        L30:
            r0 = 93
            r1 = 93
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.b(org.json.JSONObject):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
    
        if (((((85 - 1) * 85) * ((85 * 2) - 1)) % 6) == 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
    
        if (((85 * 85) - ((16 * 16) * 34)) == (-1)) goto L7;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0005. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x003d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0035 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject c() throws org.json.JSONException {
        /*
            r6 = this;
        L0:
            r0 = 91
            r1 = 1
            r2 = 85
            switch(r0) {
                case 91: goto L35;
                case 92: goto L9;
                case 93: goto L15;
                default: goto L8;
            }
        L8:
            goto L0
        L9:
            int r0 = r2 * r2
            r3 = 16
            int r3 = r3 * r3
            int r3 = r3 * 34
            int r0 = r0 - r3
            r3 = -1
            if (r0 != r3) goto L35
        L15:
            r0 = 30
            int r3 = 30 - r1
            int r3 = r3 * 30
            int r0 = r0 * 2
            int r0 = r0 - r1
            int r3 = r3 * r0
            int r3 = r3 % 6
            if (r3 == 0) goto L35
            r0 = 95
            int r3 = r0 * r0
            r4 = 10
            int r5 = r4 * r4
            int r3 = r3 + r5
            int r0 = r0 * 10
            int r0 = r0 * 2
            int r3 = r3 - r0
            if (r3 >= 0) goto L35
            goto L59
        L35:
            r0 = -55
            r3 = 73
            switch(r3) {
                case 72: goto L0;
                case 73: goto L59;
                case 74: goto L3d;
                default: goto L3c;
            }
        L3c:
            goto L35
        L3d:
            switch(r0) {
                case 39: goto L41;
                case 40: goto L4a;
                case 41: goto L59;
                default: goto L40;
            }
        L40:
            goto L35
        L41:
            r0 = 94
            int r0 = r0 + r1
            int r0 = r0 * 94
            int r0 = r0 % 2
            if (r0 == 0) goto L35
        L4a:
            int r0 = 85 - r1
            int r0 = r0 * 85
            r2 = 85
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r0 = r0 * r2
            int r0 = r0 % 6
            if (r0 == 0) goto L0
        L59:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "appid"
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b r2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D()     // Catch: java.lang.Exception -> L7e
            java.lang.String r2 = r2.c()     // Catch: java.lang.Exception -> L7e
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L7e
            java.lang.String r1 = "name"
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b r2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D()     // Catch: java.lang.Exception -> L7e
            java.lang.String r2 = r2.h()     // Catch: java.lang.Exception -> L7e
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L7e
            r6.b(r0)     // Catch: java.lang.Exception -> L7e
            r6.a(r0)     // Catch: java.lang.Exception -> L7e
        L7e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.c():org.json.JSONObject");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0000, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a d() {
        /*
        L0:
            r0 = 74
            r1 = 55
        L4:
            switch(r0) {
                case 72: goto L0;
                case 73: goto L8;
                case 74: goto Lb;
                default: goto L7;
            }
        L7:
            goto L55
        L8:
            switch(r1) {
                case 94: goto L50;
                case 95: goto Lf;
                case 96: goto L32;
                default: goto Lb;
            }
        Lb:
            switch(r1) {
                case 55: goto L50;
                case 56: goto L0;
                case 57: goto Lf;
                default: goto Le;
            }
        Le:
            goto L50
        Lf:
            r0 = 1
            switch(r0) {
                case 60: goto L14;
                case 61: goto L22;
                case 62: goto L0;
                default: goto L13;
            }
        L13:
            goto L32
        L14:
            r1 = 0
            int r2 = 0 - r0
            int r2 = r2 * 0
            int r1 = r1 * 2
            int r1 = r1 - r0
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L0
        L22:
            r1 = 18
            int r2 = 18 - r0
            int r2 = r2 * 18
            int r1 = r1 * 2
            int r1 = r1 - r0
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L50
            goto L0
        L32:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.f1827b
            if (r0 != 0) goto L4d
            java.lang.Class<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a> r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.class
            monitor-enter(r0)
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.f1827b     // Catch: java.lang.Throwable -> L48
            if (r0 != 0) goto L44
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a r0 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a     // Catch: java.lang.Throwable -> L48
            r0.<init>()     // Catch: java.lang.Throwable -> L48
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.f1827b = r0     // Catch: java.lang.Throwable -> L48
        L44:
            java.lang.Class<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a> r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.class
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L48
            goto L4d
        L48:
            r0 = move-exception
            java.lang.Class<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a> r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.class
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L48
            throw r0
        L4d:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.f1827b
            return r0
        L50:
            r0 = 73
            r1 = 96
            goto L4
        L55:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d():bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x009e, code lost:
    
        if ((((27 * 27) + (34 * 34)) + (17 * 17)) >= (((27 * 34) + (34 * 17)) + (27 * 17))) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        continue;
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0077 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00a8 -> B:9:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00b5 -> B:9:0x0063). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.bytedance.msdk.api.AdSlot r3, java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> r4, java.util.List<com.bytedance.msdk.base.TTBaseAd> r5, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c r6, int r7, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d r8) throws org.json.JSONException {
        /*
            r2 = this;
            java.lang.String r3 = r2.a(r3, r4, r5, r6, r7)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "serverBiddingRequest-encryptBody="
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "serverBiddingRequest"
            com.bytedance.msdk.adapter.util.Logger.i(r5, r4)
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b r4 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b.b()
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a r4 = r4.a()
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.c r4 = r4.b()
            java.lang.String r5 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c.b()
            r4.b(r5)
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b r5 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D()
            java.lang.String r5 = r5.p()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L47
            java.lang.String r6 = "X-Tt-Env"
            r4.a(r6, r5)
            java.lang.String r5 = "x-use-ppe"
            java.lang.String r6 = "1"
            r4.a(r5, r6)
        L47:
            java.lang.String r5 = com.bytedance.msdk.base.b.f4463b
            java.lang.String r6 = "User-Agent"
            r4.a(r6, r5)
            r4.c(r3)
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$a r3 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$a
            r3.<init>(r8)
            r4.a(r3)
        L59:
            r3 = 70
            r4 = -1
            r5 = 1
            r6 = 34
            switch(r3) {
                case 70: goto L63;
                case 71: goto L63;
                case 72: goto La1;
                default: goto L62;
            }
        L62:
            goto L59
        L63:
            r3 = 95
            switch(r3) {
                case 94: goto L86;
                case 95: goto Lb8;
                case 96: goto L69;
                default: goto L68;
            }
        L68:
            goto L77
        L69:
            r3 = 61
            int r3 = r3 * r3
            r7 = 21
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            if (r3 != r4) goto L59
            goto L63
        L77:
            r3 = 38
            int r7 = 38 - r5
            int r7 = r7 * 38
            int r3 = r3 * 2
            int r3 = r3 - r5
            int r7 = r7 * r3
            int r7 = r7 % 6
            if (r7 == 0) goto L63
        L86:
            r3 = 27
            int r7 = r3 * r3
            int r8 = r6 * r6
            int r7 = r7 + r8
            r8 = 17
            int r0 = r8 * r8
            int r7 = r7 + r0
            r0 = 27
            int r0 = r0 * 34
            r1 = 34
            int r1 = r1 * 17
            int r0 = r0 + r1
            int r3 = r3 * 17
            int r0 = r0 + r3
            if (r7 >= r0) goto La1
            goto Lb8
        La1:
            r3 = 10
            int r3 = r3 + r5
            int r3 = r3 * 10
            int r3 = r3 % 2
            if (r3 == 0) goto Lab
            goto L63
        Lab:
            r3 = 5
            int r3 = r3 * r3
            r7 = 28
            int r7 = r7 * r7
            int r7 = r7 * 34
            int r3 = r3 - r7
            if (r3 != r4) goto Lb8
            goto L63
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.a(com.bytedance.msdk.api.AdSlot, java.util.List, java.util.List, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c, int, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$d):void");
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a$a, reason: collision with other inner class name */
    class C0062a extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f1828a;

        C0062a(d dVar) {
            this.f1828a = dVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x008e  */
        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b r11, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b r12) {
            /*
                Method dump skipped, instructions count: 692
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.C0062a.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b):void");
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b bVar, IOException iOException) {
            String message = AdError.getMessage(-16);
            if (iOException != null) {
                message = iOException.getMessage();
            }
            Logger.e(a.a(), "Server Bidding Request onError...errorCode=-16");
            d dVar = this.f1828a;
            if (dVar != null) {
                a.a(a.this, dVar, new AdError(-16, message));
            }
        }
    }
}
