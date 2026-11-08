package com.facebook.stetho.websocket;

import com.facebook.stetho.common.Utf8Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class FrameHelper {
    FrameHelper() {
    }

    public static Frame createTextFrame(String str) {
        return createSimpleFrame((byte) 1, Utf8Charset.encodeUTF8(str));
    }

    public static Frame createBinaryFrame(byte[] bArr) {
        return createSimpleFrame((byte) 2, bArr);
    }

    public static Frame createCloseFrame(int i, String str) {
        byte[] bArrEncodeUTF8;
        int length;
        if (str != null) {
            bArrEncodeUTF8 = Utf8Charset.encodeUTF8(str);
            length = bArrEncodeUTF8.length + 2;
        } else {
            bArrEncodeUTF8 = null;
            length = 2;
        }
        byte[] bArr = new byte[length];
        bArr[0] = (byte) ((i >> 8) & 255);
        bArr[1] = (byte) (i & 255);
        if (bArrEncodeUTF8 != null) {
            System.arraycopy(bArrEncodeUTF8, 0, bArr, 2, bArrEncodeUTF8.length);
        }
        return createSimpleFrame((byte) 8, bArr);
    }

    public static Frame createPingFrame(byte[] bArr, int i) {
        return createSimpleFrame((byte) 9, bArr, i);
    }

    public static Frame createPongFrame(byte[] bArr, int i) {
        return createSimpleFrame((byte) 10, bArr, i);
    }

    private static Frame createSimpleFrame(byte b2, byte[] bArr) {
        return createSimpleFrame(b2, bArr, bArr.length);
    }

    private static Frame createSimpleFrame(byte b2, byte[] bArr, int i) {
        Frame frame = new Frame();
        frame.fin = true;
        frame.hasMask = false;
        frame.opcode = b2;
        frame.payloadLen = i;
        frame.payloadData = bArr;
        return frame;
    }
}
