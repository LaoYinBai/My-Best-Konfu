package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122;

import android.content.Context;
import android.os.SystemClock;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends TTAdHeaderBidingRequestCore {
    private long W;

    protected c(Context context, String str) {
        super(context, str);
    }

    static /* synthetic */ void a(c cVar, AdError adError, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        super.onAdFailed(adError, dVar);
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

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void D() {
        while (true) {
            char c2 = '^';
            char c3 = 'K';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                            case 22:
                            case 23:
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
                return;
            }
            while (true) {
                switch (c3) {
                    case '[':
                        break;
                    case '\\':
                    case ']':
                        return;
                    default:
                        c3 = '[';
                }
            }
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void E() {
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
                return;
            }
        }
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    protected void a(AdError adError) {
        while (true) {
            char c2 = '^';
            char c3 = 'K';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                            case 22:
                            case 23:
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
                return;
            }
            while (true) {
                switch (c3) {
                    case '[':
                        break;
                    case '\\':
                    case ']':
                        return;
                    default:
                        c3 = '[';
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0073, code lost:
    
        if (((((26 * 26) * 26) + ((9 * 9) * 9)) + ((15 * 15) * 15)) >= (((26 * 9) * 15) * 3)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0081, code lost:
    
        if (((((0 - 1) * 0) * ((0 * 2) - 1)) % 6) == 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008e, code lost:
    
        if (((((18 - 1) * 18) * ((18 * 2) - 1)) % 6) == 0) goto L62;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0091 A[LOOP:1: B:9:0x0043->B:24:0x0091, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0049 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0055 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0096 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.bytedance.msdk.api.AdSlot r10) {
        /*
            r9 = this;
            long r0 = android.os.SystemClock.currentThreadTimeMillis()
            r9.W = r0
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a()
            java.lang.String r1 = r9.f
            long r2 = r9.W
            r0.a(r1, r2)
            r9.h = r10
            r10 = 0
            r9.F = r10
            r9.G()
        L19:
            r10 = 52
            r0 = 96
            r1 = -1
            r2 = 13
            r3 = 1
            r4 = 34
            switch(r0) {
                case 94: goto L27;
                case 95: goto L41;
                case 96: goto L96;
                default: goto L26;
            }
        L26:
            goto L19
        L27:
            r0 = 57
            int r5 = r0 * r0
            int r6 = r4 * r4
            int r5 = r5 + r6
            r6 = 12
            int r7 = r6 * r6
            int r5 = r5 + r7
            r7 = 57
            int r7 = r7 * 34
            r8 = 34
            int r8 = r8 * 12
            int r7 = r7 + r8
            int r0 = r0 * 12
            int r7 = r7 + r0
            if (r5 >= r7) goto L96
        L41:
            r0 = 13
        L43:
            r5 = 18
            switch(r10) {
                case 55: goto L55;
                case 56: goto L96;
                case 57: goto L49;
                default: goto L48;
            }
        L48:
            goto L91
        L49:
            int r10 = r5 * r5
            r0 = 35
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r10 = r10 - r0
            if (r10 != r1) goto Lb1
            goto L59
        L55:
            switch(r0) {
                case 60: goto L59;
                case 61: goto L75;
                case 62: goto L83;
                default: goto L58;
            }
        L58:
            goto L96
        L59:
            r10 = 26
            int r0 = r10 * r10
            int r0 = r0 * 26
            r1 = 9
            int r2 = r1 * r1
            int r2 = r2 * 9
            int r0 = r0 + r2
            r2 = 15
            int r4 = r2 * r2
            int r4 = r4 * 15
            int r0 = r0 + r4
            int r10 = r10 * 9
            int r10 = r10 * 15
            int r10 = r10 * 3
            if (r0 >= r10) goto Lb1
        L75:
            r10 = 0
            int r0 = 0 - r3
            int r0 = r0 * 0
            int r10 = r10 * 2
            int r10 = r10 - r3
            int r0 = r0 * r10
            int r0 = r0 % 6
            if (r0 == 0) goto L19
        L83:
            int r10 = 18 - r3
            int r10 = r10 * 18
            int r0 = r5 * 2
            int r0 = r0 - r3
            int r10 = r10 * r0
            int r10 = r10 % 6
            if (r10 == 0) goto L83
            goto Lb1
        L91:
            r10 = 55
            r0 = 61
            goto L43
        L96:
            r10 = 49
            switch(r10) {
                case 49: goto Lb1;
                case 50: goto L9c;
                case 51: goto La7;
                default: goto L9b;
            }
        L9b:
            goto L96
        L9c:
            int r10 = r2 * r2
            r0 = 19
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r10 = r10 - r0
            if (r10 != r1) goto Lb1
        La7:
            r10 = 10
            int r10 = r10 + r3
            int r10 = r10 * 10
            int r10 = r10 % 2
            if (r10 == 0) goto L19
            goto L96
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.c.a(com.bytedance.msdk.api.AdSlot):void");
    }

    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore
    public void a(List<TTBaseAd> list, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        if (list == null) {
            return;
        }
        for (TTBaseAd tTBaseAd : list) {
            if (tTBaseAd != null) {
                b.a().b(this.f, new f(tTBaseAd, dVar, this.W, SystemClock.elapsedRealtime(), this.h));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0146, code lost:
    
        r9 = '^';
     */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[ADDED_TO_REGION, REMOVE, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0148 A[LOOP:0: B:58:0x0148->B:61:0x014d, LOOP_START, PHI: r0
  0x0148: PHI (r0v12 char) = (r0v11 char), (r0v13 char) binds: [B:57:0x0146, B:61:0x014d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0144 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0115 A[SYNTHETIC] */
    @Override // com.bytedance.msdk.core.corelogic.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void b(java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> r9) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.c.b(java.util.List):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a6, code lost:
    
        if ((((5 * 5) + (12 * 12)) + (20 * 20)) >= (((5 * 12) + (12 * 20)) + (5 * 20))) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b7, code lost:
    
        if ((((51 * 51) + (29 * 29)) - ((51 * 29) * 2)) >= 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ba, code lost:
    
        r7 = ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00be, code lost:
    
        if (r7 >= 27) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d8, code lost:
    
        if ((((82 * 82) + (15 * 15)) + (4 * 4)) >= (((82 * 15) + (15 * 4)) + (82 * 4))) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e1, code lost:
    
        if ((((62 + 1) * 62) % 2) == 0) goto L32;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00ba -> B:36:0x00bc). Please report as a decompilation issue!!! */
    @Override // com.bytedance.msdk.core.corelogic.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void c(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.c.c(java.lang.String):void");
    }

    @Override // com.bytedance.msdk.core.corelogic.d
    protected boolean o() {
        while (true) {
            char c2 = '^';
            char c3 = 'K';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                            case 22:
                            case 23:
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
                return true;
            }
            while (true) {
                switch (c3) {
                    case '[':
                        break;
                    case '\\':
                    case ']':
                        return true;
                    default:
                        c3 = '[';
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0013 A[SYNTHETIC] */
    @Override // com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore, com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onAdFailed(com.bytedance.msdk.api.AdError r7, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d r8) {
        /*
            r6 = this;
            a(r6, r7, r8)
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a()
            java.lang.String r1 = r6.f
            java.lang.String r2 = r8.d()
            long r3 = r6.W
            r5 = r7
            r0.a(r1, r2, r3, r5)
        L13:
            r7 = 12
            r8 = 96
            switch(r8) {
                case 94: goto L21;
                case 95: goto L1b;
                case 96: goto L71;
                default: goto L1a;
            }
        L1a:
            goto L13
        L1b:
            switch(r7) {
                case 94: goto L3f;
                case 95: goto L59;
                case 96: goto L71;
                default: goto L1e;
            }
        L1e:
            r7 = 95
            goto L1b
        L21:
            r7 = 1
            r8 = 81
            int r0 = 81 - r7
            int r0 = r0 * 81
            int r8 = r8 * 2
            int r8 = r8 - r7
            int r0 = r0 * r8
            int r0 = r0 % 6
            if (r0 == 0) goto L13
            r7 = 66
            int r7 = r7 * r7
            r8 = 16
            int r8 = r8 * r8
            int r8 = r8 * 34
            int r7 = r7 - r8
            r8 = -1
            if (r7 != r8) goto L59
        L3f:
            r7 = 29
            int r8 = r7 * r7
            r0 = 45
            int r1 = r0 * r0
            int r8 = r8 + r1
            r1 = 20
            int r2 = r1 * r1
            int r8 = r8 + r2
            r2 = 29
            int r2 = r2 * 45
            int r0 = r0 * 20
            int r2 = r2 + r0
            int r7 = r7 * 20
            int r2 = r2 + r7
            if (r8 >= r2) goto L71
        L59:
            r7 = 70
            int r8 = r7 * r7
            r0 = 7
            int r1 = r0 * r0
            int r8 = r8 + r1
            r1 = 4
            int r2 = r1 * r1
            int r8 = r8 + r2
            r2 = 70
            int r2 = r2 * 7
            int r0 = r0 * 4
            int r2 = r2 + r0
            int r7 = r7 * 4
            int r2 = r2 + r7
            if (r8 >= r2) goto L13
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.c.onAdFailed(com.bytedance.msdk.api.AdError, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x009c, code lost:
    
        if ((((28 + 1) * 28) % 2) == 0) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00aa, code lost:
    
        if (((69 * 69) - ((32 * 32) * 34)) != (-1)) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00cc, code lost:
    
        if (((((97 * 97) * 97) + ((33 * 33) * 33)) + ((26 * 26) * 26)) < (((97 * 33) * 26) * 3)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x011d, code lost:
    
        if ((((41 * 41) + (15 * 15)) + (23 * 23)) >= (((41 * 15) + (15 * 23)) + (41 * 23))) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0122, code lost:
    
        switch(58) {
            case 58: goto L83;
            case 59: goto L84;
            case 60: goto L85;
            default: goto L87;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0140, code lost:
    
        if ((((24 * 24) + (17 * 17)) - ((24 * 17) * 2)) < 0) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0151, code lost:
    
        if (((((14 - 1) * 14) * ((14 * 2) - 1)) % 6) == 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0153, code lost:
    
        return 3.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
    
        if ((((13 * 13) + (41 * 41)) - ((13 * 41) * 2)) < 0) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00ae -> B:45:0x00ec). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00e8 -> B:48:0x00f1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00e8 -> B:50:0x0107). Please report as a decompilation issue!!! */
    @Override // com.bytedance.msdk.core.corelogic.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected double w() {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.c.w():double");
    }
}
