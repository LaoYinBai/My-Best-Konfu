package com.ss.android.socialbase.appdownloader.util.package_info;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StringBlock {
    private int[] m_stringOffsets;
    private int[] m_strings;

    public static StringBlock read(IntReader intReader) throws IOException {
        ChunkUtil.readCheckType(intReader, 1835009);
        int i = intReader.readInt();
        int i2 = intReader.readInt();
        int i3 = intReader.readInt();
        intReader.readInt();
        int i4 = intReader.readInt();
        int i5 = intReader.readInt();
        StringBlock stringBlock = new StringBlock();
        stringBlock.m_stringOffsets = intReader.readIntArray(i2);
        if (i3 != 0) {
            intReader.readIntArray(i3);
        }
        int i6 = (i5 == 0 ? i : i5) - i4;
        if (i6 % 4 != 0) {
            throw new IOException("String data size is not multiple of 4 (" + i6 + ").");
        }
        stringBlock.m_strings = intReader.readIntArray(i6 / 4);
        if (i5 != 0) {
            int i7 = i - i5;
            if (i7 % 4 != 0) {
                throw new IOException("Style data size is not multiple of 4 (" + i7 + ").");
            }
            intReader.readIntArray(i7 / 4);
        }
        return stringBlock;
    }

    public String getString(int i) {
        if (i < 0 || this.m_stringOffsets == null || i >= this.m_stringOffsets.length) {
            return null;
        }
        int i2 = this.m_stringOffsets[i];
        int i3 = getShort(this.m_strings, i2);
        StringBuilder sb = new StringBuilder(i3);
        while (i3 != 0) {
            i2 += 2;
            sb.append((char) getShort(this.m_strings, i2));
            i3--;
        }
        return sb.toString();
    }

    public int find(String str) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i != this.m_stringOffsets.length; i++) {
            int i2 = this.m_stringOffsets[i];
            int i3 = getShort(this.m_strings, i2);
            if (i3 == str.length()) {
                int i4 = i2;
                int i5 = 0;
                while (i5 != i3) {
                    i4 += 2;
                    if (str.charAt(i5) != getShort(this.m_strings, i4)) {
                        break;
                    }
                    i5++;
                }
                if (i5 == i3) {
                    return i;
                }
            }
        }
        return -1;
    }

    private StringBlock() {
    }

    private static final int getShort(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        return (i % 4) / 2 == 0 ? i2 & 65535 : i2 >>> 16;
    }
}
