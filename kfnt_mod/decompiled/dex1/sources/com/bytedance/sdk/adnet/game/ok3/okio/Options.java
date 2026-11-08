package com.bytedance.sdk.adnet.game.ok3.okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    private static void buildTrieRecursive(long j, Buffer buffer, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        int i7 = i2;
        if (i7 >= i3) {
            throw new AssertionError();
        }
        for (int i8 = i7; i8 < i3; i8++) {
            if (list.get(i8).size() < i) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i2);
        ByteString byteString2 = list.get(i3 - 1);
        int iIntValue = -1;
        if (i == byteString.size()) {
            iIntValue = list2.get(i7).intValue();
            i7++;
            byteString = list.get(i7);
        }
        int i9 = i7;
        if (byteString.getByte(i) == byteString2.getByte(i)) {
            int iMin = Math.min(byteString.size(), byteString2.size());
            int i10 = 0;
            for (int i11 = i; i11 < iMin && byteString.getByte(i11) == byteString2.getByte(i11); i11++) {
                i10++;
            }
            long jIntCount = j + intCount(buffer) + 2 + i10 + 1;
            buffer.writeInt(-i10);
            buffer.writeInt(iIntValue);
            int i12 = i;
            while (true) {
                i4 = i + i10;
                if (i12 >= i4) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i12) & 255);
                i12++;
            }
            if (i9 + 1 == i3) {
                if (i4 != list.get(i9).size()) {
                    throw new AssertionError();
                }
                buffer.writeInt(list2.get(i9).intValue());
                return;
            } else {
                Buffer buffer2 = new Buffer();
                buffer.writeInt((int) ((intCount(buffer2) + jIntCount) * (-1)));
                buildTrieRecursive(jIntCount, buffer2, i4, list, i9, i3, list2);
                buffer.write(buffer2, buffer2.size());
                return;
            }
        }
        int i13 = 1;
        for (int i14 = i9 + 1; i14 < i3; i14++) {
            if (list.get(i14 - 1).getByte(i) != list.get(i14).getByte(i)) {
                i13++;
            }
        }
        long jIntCount2 = j + intCount(buffer) + 2 + (i13 * 2);
        buffer.writeInt(i13);
        buffer.writeInt(iIntValue);
        for (int i15 = i9; i15 < i3; i15++) {
            byte b2 = list.get(i15).getByte(i);
            if (i15 == i9 || b2 != list.get(i15 - 1).getByte(i)) {
                buffer.writeInt(b2 & 255);
            }
        }
        Buffer buffer3 = new Buffer();
        int i16 = i9;
        while (i16 < i3) {
            byte b3 = list.get(i16).getByte(i);
            int i17 = i16 + 1;
            int i18 = i17;
            while (true) {
                if (i18 >= i3) {
                    i5 = i3;
                    break;
                } else {
                    if (b3 != list.get(i18).getByte(i)) {
                        i5 = i18;
                        break;
                    }
                    i18++;
                }
            }
            if (i17 == i5 && i + 1 == list.get(i16).size()) {
                buffer.writeInt(list2.get(i16).intValue());
                i6 = i5;
            } else {
                buffer.writeInt((int) ((intCount(buffer3) + jIntCount2) * (-1)));
                i6 = i5;
                buildTrieRecursive(jIntCount2, buffer3, i + 1, list, i16, i5, list2);
            }
            i16 = i6;
        }
        buffer.write(buffer3, buffer3.size());
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00bc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedance.sdk.adnet.game.ok3.okio.Options of(com.bytedance.sdk.adnet.game.ok3.okio.ByteString... r10) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.adnet.game.ok3.okio.Options.of(com.bytedance.sdk.adnet.game.ok3.okio.ByteString[]):com.bytedance.sdk.adnet.game.ok3.okio.Options");
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }
}
