package com.mbridge.msdk.playercommon.exoplayer2.extractor.mp4;

import com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorInput;
import com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};
    private static final int SEARCH_LENGTH = 4096;

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return sniffInternal(extractorInput, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return sniffInternal(extractorInput, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z) throws InterruptedException, IOException {
        boolean z2;
        long length = extractorInput.getLength();
        long j = -1;
        if (length == -1 || length > 4096) {
            length = 4096;
        }
        int i = (int) length;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i2 = 0;
        boolean z3 = false;
        while (i2 < i) {
            parsableByteArray.reset(8);
            extractorInput.peekFully(parsableByteArray.data, 0, 8);
            long unsignedInt = parsableByteArray.readUnsignedInt();
            int i3 = parsableByteArray.readInt();
            int i4 = 16;
            if (unsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.data, 8, 8);
                parsableByteArray.setLimit(16);
                unsignedInt = parsableByteArray.readUnsignedLongToLong();
            } else {
                if (unsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j) {
                        unsignedInt = 8 + (length2 - extractorInput.getPosition());
                    }
                }
                i4 = 8;
            }
            long j2 = i4;
            if (unsignedInt < j2) {
                return false;
            }
            i2 += i4;
            if (i3 != Atom.TYPE_moov) {
                if (i3 == Atom.TYPE_moof || i3 == Atom.TYPE_mvex) {
                    z2 = true;
                    break;
                }
                if ((i2 + unsignedInt) - j2 >= i) {
                    break;
                }
                int i5 = (int) (unsignedInt - j2);
                i2 += i5;
                if (i3 == Atom.TYPE_ftyp) {
                    if (i5 < 8) {
                        return false;
                    }
                    parsableByteArray.reset(i5);
                    extractorInput.peekFully(parsableByteArray.data, 0, i5);
                    int i6 = i5 / 4;
                    int i7 = 0;
                    while (true) {
                        if (i7 >= i6) {
                            break;
                        }
                        if (i7 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                            z3 = true;
                            break;
                        }
                        i7++;
                    }
                    if (!z3) {
                        return false;
                    }
                } else if (i5 != 0) {
                    extractorInput.advancePeekPosition(i5);
                }
            }
            j = -1;
        }
        z2 = false;
        return z3 && z == z2;
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
