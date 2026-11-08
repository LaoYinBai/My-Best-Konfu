package com.mbridge.msdk.foundation.tools;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ts.PsExtractor;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SameMVEncoder.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class t {
    private static char[] c;
    private static final byte[] d;
    private static final char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static Map<Character, Character> a = new HashMap();

    static {
        a.put('A', 'v');
        a.put('B', 'S');
        a.put('C', 'o');
        a.put('D', 'a');
        a.put('E', 'j');
        a.put('F', 'c');
        a.put('G', '7');
        a.put('H', 'd');
        a.put('I', 'R');
        a.put('J', 'z');
        a.put('K', 'p');
        a.put('L', 'W');
        a.put('M', 'i');
        a.put('N', 'f');
        a.put('O', 'G');
        a.put('P', 'y');
        a.put('Q', 'N');
        a.put('R', 'x');
        a.put('S', 'Z');
        a.put('T', 'n');
        a.put('U', 'V');
        a.put('V', '5');
        a.put('W', 'k');
        a.put('X', '+');
        a.put('Y', 'D');
        a.put('Z', 'H');
        a.put('a', 'L');
        a.put('b', 'Y');
        a.put('c', 'h');
        a.put('d', 'J');
        a.put('e', '4');
        a.put('f', '6');
        a.put('g', 'l');
        a.put('h', 't');
        a.put('i', '0');
        a.put('j', 'U');
        a.put('k', '3');
        a.put('l', 'Q');
        a.put('m', 'r');
        a.put('n', 'g');
        a.put('o', 'E');
        a.put('p', 'u');
        a.put('q', 'q');
        a.put('r', '8');
        a.put('s', 's');
        a.put('t', 'w');
        a.put('u', '/');
        a.put('v', 'X');
        a.put('w', 'M');
        a.put('x', 'e');
        a.put('y', 'B');
        a.put('z', 'A');
        a.put('0', 'T');
        a.put('1', '2');
        a.put('2', 'F');
        a.put('3', 'b');
        a.put('4', '9');
        a.put('5', 'P');
        a.put('6', '1');
        a.put('7', 'O');
        a.put('8', 'I');
        a.put('9', 'K');
        a.put('+', 'm');
        a.put('/', 'C');
        c = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            c[i] = a.get(Character.valueOf(b[i])).charValue();
        }
        d = new byte[128];
        for (int i2 = 0; i2 < d.length; i2++) {
            d[i2] = 127;
        }
        for (int i3 = 0; i3 < c.length; i3++) {
            d[c[i3]] = (byte) i3;
        }
    }

    private static int a(char[] cArr, byte[] bArr, int i) {
        try {
            char c2 = cArr[3] == '=' ? (char) 2 : (char) 3;
            if (cArr[2] == '=') {
                c2 = 1;
            }
            byte b2 = d[cArr[0]];
            byte b3 = d[cArr[1]];
            byte b4 = d[cArr[2]];
            byte b5 = d[cArr[3]];
            switch (c2) {
                case 1:
                    bArr[i] = (byte) (((b2 << 2) & 252) | (3 & (b3 >> 4)));
                    return 1;
                case 2:
                    bArr[i] = (byte) ((3 & (b3 >> 4)) | ((b2 << 2) & 252));
                    bArr[i + 1] = (byte) (((b3 << 4) & PsExtractor.VIDEO_STREAM_MASK) | ((b4 >> 2) & 15));
                    return 2;
                case 3:
                    int i2 = i + 1;
                    bArr[i] = (byte) (((b2 << 2) & 252) | ((b3 >> 4) & 3));
                    bArr[i2] = (byte) (((b3 << 4) & PsExtractor.VIDEO_STREAM_MASK) | ((b4 >> 2) & 15));
                    bArr[i2 + 1] = (byte) ((b5 & 63) | ((b4 << 6) & PsExtractor.AUDIO_STREAM));
                    return 3;
                default:
                    throw new RuntimeException("Internal Error");
            }
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String a(String str) {
        byte[] bArrC = c(str);
        if (bArrC == null || bArrC.length <= 0) {
            return null;
        }
        return new String(bArrC);
    }

    private static byte[] c(String str) {
        int i;
        try {
            int length = str.length();
            char[] cArr = new char[length < 259 ? length : 259];
            byte[] bArr = new byte[((length >> 2) * 3) + 3];
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length) {
                int i5 = i2 + 256;
                if (i5 <= length) {
                    str.getChars(i2, i5, cArr, i4);
                    i = i4 + 256;
                } else {
                    str.getChars(i2, length, cArr, i4);
                    i = (length - i2) + i4;
                }
                int iA = i3;
                int i6 = i4;
                while (i4 < i) {
                    char c2 = cArr[i4];
                    if (c2 == '=' || (c2 < d.length && d[c2] != 127)) {
                        int i7 = i6 + 1;
                        cArr[i6] = c2;
                        if (i7 == 4) {
                            iA += a(cArr, bArr, iA);
                            i6 = 0;
                        } else {
                            i6 = i7;
                        }
                    }
                    i4++;
                }
                i4 = i6;
                i2 = i5;
                i3 = iA;
            }
            if (i3 == bArr.length) {
                return bArr;
            }
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, 0, bArr2, 0, i3);
            return bArr2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String b(String str) {
        byte[] bytes = str.getBytes();
        return a(bytes, 0, bytes.length);
    }

    private static String a(byte[] bArr, int i, int i2) {
        if (i2 <= 0) {
            return "";
        }
        try {
            char[] cArr = new char[((i2 / 3) << 2) + 4];
            int i3 = 0;
            while (i2 >= 3) {
                int i4 = ((bArr[i] & FileDownloadStatus.error) << 16) + ((bArr[i + 1] & FileDownloadStatus.error) << 8) + (bArr[i + 2] & FileDownloadStatus.error);
                int i5 = i3 + 1;
                cArr[i3] = c[i4 >> 18];
                int i6 = i5 + 1;
                cArr[i5] = c[(i4 >> 12) & 63];
                int i7 = i6 + 1;
                cArr[i6] = c[(i4 >> 6) & 63];
                i3 = i7 + 1;
                cArr[i7] = c[i4 & 63];
                i += 3;
                i2 -= 3;
            }
            if (i2 == 1) {
                int i8 = bArr[i] & FileDownloadStatus.error;
                int i9 = i3 + 1;
                cArr[i3] = c[i8 >> 2];
                int i10 = i9 + 1;
                cArr[i9] = c[(i8 << 4) & 63];
                int i11 = i10 + 1;
                cArr[i10] = '=';
                i3 = i11 + 1;
                cArr[i11] = '=';
            } else if (i2 == 2) {
                int i12 = ((bArr[i] & FileDownloadStatus.error) << 8) + (bArr[i + 1] & FileDownloadStatus.error);
                int i13 = i3 + 1;
                cArr[i3] = c[i12 >> 10];
                int i14 = i13 + 1;
                cArr[i13] = c[(i12 >> 4) & 63];
                int i15 = i14 + 1;
                cArr[i14] = c[(i12 << 2) & 63];
                i3 = i15 + 1;
                cArr[i15] = '=';
            }
            return new String(cArr, 0, i3);
        } catch (Exception unused) {
            return null;
        }
    }
}
