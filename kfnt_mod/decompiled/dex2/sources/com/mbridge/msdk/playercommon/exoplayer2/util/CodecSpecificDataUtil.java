package com.mbridge.msdk.playercommon.exoplayer2.util;

import android.util.Pair;
import com.mbridge.msdk.playercommon.exoplayer2.ParserException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class CodecSpecificDataUtil {
    private static final int AUDIO_OBJECT_TYPE_AAC_LC = 2;
    private static final int AUDIO_OBJECT_TYPE_ER_BSAC = 22;
    private static final int AUDIO_OBJECT_TYPE_ESCAPE = 31;
    private static final int AUDIO_OBJECT_TYPE_PS = 29;
    private static final int AUDIO_OBJECT_TYPE_SBR = 5;
    private static final int AUDIO_SPECIFIC_CONFIG_CHANNEL_CONFIGURATION_INVALID = -1;
    private static final int AUDIO_SPECIFIC_CONFIG_FREQUENCY_INDEX_ARBITRARY = 15;
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    private CodecSpecificDataUtil() {
    }

    public static Pair<Integer, Integer> parseAacAudioSpecificConfig(byte[] bArr) throws ParserException {
        return parseAacAudioSpecificConfig(new ParsableBitArray(bArr), false);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004a A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> parseAacAudioSpecificConfig(com.mbridge.msdk.playercommon.exoplayer2.util.ParsableBitArray r5, boolean r6) throws com.mbridge.msdk.playercommon.exoplayer2.ParserException {
        /*
            int r0 = getAacAudioObjectType(r5)
            int r1 = getAacSamplingFrequency(r5)
            r2 = 4
            int r3 = r5.readBits(r2)
            r4 = 5
            if (r0 == r4) goto L14
            r4 = 29
            if (r0 != r4) goto L24
        L14:
            int r1 = getAacSamplingFrequency(r5)
            int r0 = getAacAudioObjectType(r5)
            r4 = 22
            if (r0 != r4) goto L24
            int r3 = r5.readBits(r2)
        L24:
            if (r6 == 0) goto L73
            r6 = 17
            if (r0 == r6) goto L4a
            switch(r0) {
                case 1: goto L4a;
                case 2: goto L4a;
                case 3: goto L4a;
                case 4: goto L4a;
                default: goto L2d;
            }
        L2d:
            switch(r0) {
                case 6: goto L4a;
                case 7: goto L4a;
                default: goto L30;
            }
        L30:
            switch(r0) {
                case 19: goto L4a;
                case 20: goto L4a;
                case 21: goto L4a;
                case 22: goto L4a;
                case 23: goto L4a;
                default: goto L33;
            }
        L33:
            com.mbridge.msdk.playercommon.exoplayer2.ParserException r5 = new com.mbridge.msdk.playercommon.exoplayer2.ParserException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "Unsupported audio object type: "
            r6.append(r1)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L4a:
            parseGaSpecificConfig(r5, r0, r3)
            switch(r0) {
                case 17: goto L51;
                case 18: goto L50;
                case 19: goto L51;
                case 20: goto L51;
                case 21: goto L51;
                case 22: goto L51;
                case 23: goto L51;
                default: goto L50;
            }
        L50:
            goto L73
        L51:
            r6 = 2
            int r5 = r5.readBits(r6)
            if (r5 == r6) goto L5c
            r6 = 3
            if (r5 == r6) goto L5c
            goto L73
        L5c:
            com.mbridge.msdk.playercommon.exoplayer2.ParserException r6 = new com.mbridge.msdk.playercommon.exoplayer2.ParserException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported epConfig: "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        L73:
            int[] r5 = com.mbridge.msdk.playercommon.exoplayer2.util.CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE
            r5 = r5[r3]
            r6 = -1
            if (r5 == r6) goto L7c
            r6 = 1
            goto L7d
        L7c:
            r6 = 0
        L7d:
            com.mbridge.msdk.playercommon.exoplayer2.util.Assertions.checkArgument(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            android.util.Pair r5 = android.util.Pair.create(r6, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.util.CodecSpecificDataUtil.parseAacAudioSpecificConfig(com.mbridge.msdk.playercommon.exoplayer2.util.ParsableBitArray, boolean):android.util.Pair");
    }

    public static byte[] buildAacLcAudioSpecificConfig(int i, int i2) {
        int i3 = -1;
        for (int i4 = 0; i4 < AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE.length; i4++) {
            if (i == AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[i4]) {
                i3 = i4;
            }
        }
        int i5 = -1;
        for (int i6 = 0; i6 < AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE.length; i6++) {
            if (i2 == AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[i6]) {
                i5 = i6;
            }
        }
        if (i == -1 || i5 == -1) {
            throw new IllegalArgumentException("Invalid sample rate or number of channels: " + i + ", " + i2);
        }
        return buildAacAudioSpecificConfig(2, i3, i5);
    }

    public static byte[] buildAacAudioSpecificConfig(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & 120))};
    }

    public static byte[] buildNalUnit(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[NAL_START_CODE.length + i2];
        System.arraycopy(NAL_START_CODE, 0, bArr2, 0, NAL_START_CODE.length);
        System.arraycopy(bArr, i, bArr2, NAL_START_CODE.length, i2);
        return bArr2;
    }

    public static byte[][] splitNalUnits(byte[] bArr) {
        if (!isNalStartCode(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iFindNalStartCode = 0;
        do {
            arrayList.add(Integer.valueOf(iFindNalStartCode));
            iFindNalStartCode = findNalStartCode(bArr, iFindNalStartCode + NAL_START_CODE.length);
        } while (iFindNalStartCode != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i = 0;
        while (i < arrayList.size()) {
            int iIntValue = ((Integer) arrayList.get(i)).intValue();
            byte[] bArr3 = new byte[(i < arrayList.size() + (-1) ? ((Integer) arrayList.get(i + 1)).intValue() : bArr.length) - iIntValue];
            System.arraycopy(bArr, iIntValue, bArr3, 0, bArr3.length);
            bArr2[i] = bArr3;
            i++;
        }
        return bArr2;
    }

    private static int findNalStartCode(byte[] bArr, int i) {
        int length = bArr.length - NAL_START_CODE.length;
        while (i <= length) {
            if (isNalStartCode(bArr, i)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static boolean isNalStartCode(byte[] bArr, int i) {
        if (bArr.length - i <= NAL_START_CODE.length) {
            return false;
        }
        for (int i2 = 0; i2 < NAL_START_CODE.length; i2++) {
            if (bArr[i + i2] != NAL_START_CODE[i2]) {
                return false;
            }
        }
        return true;
    }

    private static int getAacAudioObjectType(ParsableBitArray parsableBitArray) {
        int bits = parsableBitArray.readBits(5);
        return bits == 31 ? parsableBitArray.readBits(6) + 32 : bits;
    }

    private static int getAacSamplingFrequency(ParsableBitArray parsableBitArray) {
        int bits = parsableBitArray.readBits(4);
        if (bits == 15) {
            return parsableBitArray.readBits(24);
        }
        Assertions.checkArgument(bits < 13);
        return AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[bits];
    }

    private static void parseGaSpecificConfig(ParsableBitArray parsableBitArray, int i, int i2) {
        parsableBitArray.skipBits(1);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(14);
        }
        boolean bit = parsableBitArray.readBit();
        if (i2 == 0) {
            throw new UnsupportedOperationException();
        }
        if (i == 6 || i == 20) {
            parsableBitArray.skipBits(3);
        }
        if (bit) {
            if (i == 22) {
                parsableBitArray.skipBits(16);
            }
            if (i == 17 || i == 19 || i == 20 || i == 23) {
                parsableBitArray.skipBits(3);
            }
            parsableBitArray.skipBits(1);
        }
    }
}
