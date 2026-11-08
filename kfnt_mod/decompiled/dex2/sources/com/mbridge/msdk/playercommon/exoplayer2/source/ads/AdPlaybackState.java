package com.mbridge.msdk.playercommon.exoplayer2.source.ads;

import android.net.Uri;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.util.Assertions;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class AdPlaybackState {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    public static final AdPlaybackState NONE = new AdPlaybackState(new long[0]);
    public final int adGroupCount;
    public final long[] adGroupTimesUs;
    public final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final long contentDurationUs;

    public @interface AdState {
    }

    public static final class AdGroup {
        public final int count;
        public final long[] durationsUs;
        public final int[] states;
        public final Uri[] uris;

        public AdGroup() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        private AdGroup(int i, int[] iArr, Uri[] uriArr, long[] jArr) {
            Assertions.checkArgument(iArr.length == uriArr.length);
            this.count = i;
            this.states = iArr;
            this.uris = uriArr;
            this.durationsUs = jArr;
        }

        public final int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public final int getNextAdIndexToPlay(int i) {
            int i2 = i + 1;
            while (i2 < this.states.length && this.states[i2] != 0 && this.states[i2] != 1) {
                i2++;
            }
            return i2;
        }

        public final boolean hasUnplayedAds() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public final AdGroup withAdCount(int i) {
            Assertions.checkArgument(this.count == -1 && this.states.length <= i);
            return new AdGroup(i, copyStatesWithSpaceForAdCount(this.states, i), (Uri[]) Arrays.copyOf(this.uris, i), copyDurationsUsWithSpaceForAdCount(this.durationsUs, i));
        }

        public final AdGroup withAdUri(Uri uri, int i) {
            long[] jArrCopyDurationsUsWithSpaceForAdCount;
            Assertions.checkArgument(this.count == -1 || i < this.count);
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i + 1);
            Assertions.checkArgument(iArrCopyStatesWithSpaceForAdCount[i] == 0);
            if (this.durationsUs.length == iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            } else {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, iArrCopyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr = (Uri[]) Arrays.copyOf(this.uris, iArrCopyStatesWithSpaceForAdCount.length);
            uriArr[i] = uri;
            iArrCopyStatesWithSpaceForAdCount[i] = 1;
            return new AdGroup(this.count, iArrCopyStatesWithSpaceForAdCount, uriArr, jArrCopyDurationsUsWithSpaceForAdCount);
        }

        public final AdGroup withAdState(int i, int i2) {
            long[] jArrCopyDurationsUsWithSpaceForAdCount;
            Assertions.checkArgument(this.count == -1 || i2 < this.count);
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i2 + 1);
            Assertions.checkArgument(iArrCopyStatesWithSpaceForAdCount[i2] == 0 || iArrCopyStatesWithSpaceForAdCount[i2] == 1 || iArrCopyStatesWithSpaceForAdCount[i2] == i);
            if (this.durationsUs.length == iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            } else {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, iArrCopyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr = this.uris.length == iArrCopyStatesWithSpaceForAdCount.length ? this.uris : (Uri[]) Arrays.copyOf(this.uris, iArrCopyStatesWithSpaceForAdCount.length);
            iArrCopyStatesWithSpaceForAdCount[i2] = i;
            return new AdGroup(this.count, iArrCopyStatesWithSpaceForAdCount, uriArr, jArrCopyDurationsUsWithSpaceForAdCount);
        }

        public final AdGroup withAdDurationsUs(long[] jArr) {
            Assertions.checkArgument(this.count == -1 || jArr.length <= this.uris.length);
            if (jArr.length < this.uris.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, this.uris.length);
            }
            return new AdGroup(this.count, this.states, this.uris, jArr);
        }

        public final AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(0, new int[0], new Uri[0], new long[0]);
            }
            int length = this.states.length;
            int[] iArrCopyOf = Arrays.copyOf(this.states, length);
            for (int i = 0; i < length; i++) {
                if (iArrCopyOf[i] == 1 || iArrCopyOf[i] == 0) {
                    iArrCopyOf[i] = 2;
                }
            }
            return new AdGroup(length, iArrCopyOf, this.uris, this.durationsUs);
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i) {
            int length = iArr.length;
            int iMax = Math.max(i, length);
            int[] iArrCopyOf = Arrays.copyOf(iArr, iMax);
            Arrays.fill(iArrCopyOf, length, iMax, 0);
            return iArrCopyOf;
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i) {
            int length = jArr.length;
            int iMax = Math.max(i, length);
            long[] jArrCopyOf = Arrays.copyOf(jArr, iMax);
            Arrays.fill(jArrCopyOf, length, iMax, C.TIME_UNSET);
            return jArrCopyOf;
        }
    }

    public AdPlaybackState(long... jArr) {
        int length = jArr.length;
        this.adGroupCount = length;
        this.adGroupTimesUs = Arrays.copyOf(jArr, length);
        this.adGroups = new AdGroup[length];
        for (int i = 0; i < length; i++) {
            this.adGroups[i] = new AdGroup();
        }
        this.adResumePositionUs = 0L;
        this.contentDurationUs = C.TIME_UNSET;
    }

    private AdPlaybackState(long[] jArr, AdGroup[] adGroupArr, long j, long j2) {
        this.adGroupCount = adGroupArr.length;
        this.adGroupTimesUs = jArr;
        this.adGroups = adGroupArr;
        this.adResumePositionUs = j;
        this.contentDurationUs = j2;
    }

    public final int getAdGroupIndexForPositionUs(long j) {
        int length = this.adGroupTimesUs.length - 1;
        while (length >= 0 && (this.adGroupTimesUs[length] == Long.MIN_VALUE || this.adGroupTimesUs[length] > j)) {
            length--;
        }
        if (length < 0 || !this.adGroups[length].hasUnplayedAds()) {
            return -1;
        }
        return length;
    }

    public final int getAdGroupIndexAfterPositionUs(long j) {
        int i = 0;
        while (i < this.adGroupTimesUs.length && this.adGroupTimesUs[i] != Long.MIN_VALUE && (j >= this.adGroupTimesUs[i] || !this.adGroups[i].hasUnplayedAds())) {
            i++;
        }
        if (i < this.adGroupTimesUs.length) {
            return i;
        }
        return -1;
    }

    public final AdPlaybackState withAdCount(int i, int i2) {
        Assertions.checkArgument(i2 > 0);
        if (this.adGroups[i].count == i2) {
            return this;
        }
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        adGroupArr[i] = this.adGroups[i].withAdCount(i2);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withAdUri(int i, int i2, Uri uri) {
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        adGroupArr[i] = adGroupArr[i].withAdUri(uri, i2);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withPlayedAd(int i, int i2) {
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        adGroupArr[i] = adGroupArr[i].withAdState(3, i2);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withSkippedAd(int i, int i2) {
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        adGroupArr[i] = adGroupArr[i].withAdState(2, i2);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withAdLoadError(int i, int i2) {
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        adGroupArr[i] = adGroupArr[i].withAdState(4, i2);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withSkippedAdGroup(int i) {
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        adGroupArr[i] = adGroupArr[i].withAllAdsSkipped();
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withAdDurationsUs(long[][] jArr) {
        AdGroup[] adGroupArr = (AdGroup[]) Arrays.copyOf(this.adGroups, this.adGroups.length);
        for (int i = 0; i < this.adGroupCount; i++) {
            adGroupArr[i] = adGroupArr[i].withAdDurationsUs(jArr[i]);
        }
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr, this.adResumePositionUs, this.contentDurationUs);
    }

    public final AdPlaybackState withAdResumePositionUs(long j) {
        return this.adResumePositionUs == j ? this : new AdPlaybackState(this.adGroupTimesUs, this.adGroups, j, this.contentDurationUs);
    }

    public final AdPlaybackState withContentDurationUs(long j) {
        return this.contentDurationUs == j ? this : new AdPlaybackState(this.adGroupTimesUs, this.adGroups, this.adResumePositionUs, j);
    }
}
