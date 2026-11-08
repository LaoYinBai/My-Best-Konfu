package com.mbridge.msdk.playercommon.exoplayer2.source;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.Timeline;
import com.mbridge.msdk.playercommon.exoplayer2.util.Assertions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class SinglePeriodTimeline extends Timeline {
    private static final Object UID = new Object();
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final Object tag;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Timeline
    public final int getPeriodCount() {
        return 1;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Timeline
    public final int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j, boolean z, boolean z2) {
        this(j, z, z2, null);
    }

    public SinglePeriodTimeline(long j, boolean z, boolean z2, Object obj) {
        this(j, j, 0L, 0L, z, z2, obj);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, boolean z, boolean z2, Object obj) {
        this(C.TIME_UNSET, C.TIME_UNSET, j, j2, j3, j4, z, z2, obj);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, long j5, long j6, boolean z, boolean z2, Object obj) {
        this.presentationStartTimeMs = j;
        this.windowStartTimeMs = j2;
        this.periodDurationUs = j3;
        this.windowDurationUs = j4;
        this.windowPositionInPeriodUs = j5;
        this.windowDefaultStartPositionUs = j6;
        this.isSeekable = z;
        this.isDynamic = z2;
        this.tag = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034 A[PHI: r3
  0x0034: PHI (r3v4 long) = (r3v3 long), (r3v3 long), (r3v5 long) binds: [B:8:0x001a, B:10:0x0020, B:15:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.mbridge.msdk.playercommon.exoplayer2.Timeline
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.mbridge.msdk.playercommon.exoplayer2.Timeline.Window getWindow(int r21, com.mbridge.msdk.playercommon.exoplayer2.Timeline.Window r22, boolean r23, long r24) {
        /*
            r20 = this;
            r0 = r20
            r3 = 0
            r4 = 1
            r5 = r21
            com.mbridge.msdk.playercommon.exoplayer2.util.Assertions.checkIndex(r5, r3, r4)
            if (r23 == 0) goto Lf
            java.lang.Object r3 = r0.tag
        Ld:
            r5 = r3
            goto L11
        Lf:
            r3 = 0
            goto Ld
        L11:
            long r3 = r0.windowDefaultStartPositionUs
            boolean r6 = r0.isDynamic
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r6 == 0) goto L34
            r9 = 0
            int r6 = (r24 > r9 ? 1 : (r24 == r9 ? 0 : -1))
            if (r6 == 0) goto L34
            long r9 = r0.windowDurationUs
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 != 0) goto L2a
        L28:
            r12 = r7
            goto L35
        L2a:
            r6 = 0
            long r3 = r3 + r24
            long r1 = r0.windowDurationUs
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 <= 0) goto L34
            goto L28
        L34:
            r12 = r3
        L35:
            long r6 = r0.presentationStartTimeMs
            long r8 = r0.windowStartTimeMs
            boolean r10 = r0.isSeekable
            boolean r11 = r0.isDynamic
            long r14 = r0.windowDurationUs
            r16 = 0
            r17 = 0
            long r1 = r0.windowPositionInPeriodUs
            r4 = r22
            r18 = r1
            com.mbridge.msdk.playercommon.exoplayer2.Timeline$Window r1 = r4.set(r5, r6, r8, r10, r11, r12, r14, r16, r17, r18)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.source.SinglePeriodTimeline.getWindow(int, com.mbridge.msdk.playercommon.exoplayer2.Timeline$Window, boolean, long):com.mbridge.msdk.playercommon.exoplayer2.Timeline$Window");
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Timeline
    public final Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        Assertions.checkIndex(i, 0, 1);
        return period.set(null, z ? UID : null, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Timeline
    public final int getIndexOfPeriod(Object obj) {
        return UID.equals(obj) ? 0 : -1;
    }
}
