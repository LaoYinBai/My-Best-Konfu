package com.mbridge.msdk.widget.custom.b;

/* compiled from: XmlUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    public static boolean a(String str) throws NumberFormatException {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            try {
                try {
                    Integer.parseInt(str);
                    return true;
                } catch (NumberFormatException unused) {
                    return false;
                }
            } catch (NumberFormatException unused2) {
                Float.parseFloat(str);
                return true;
            }
        } catch (NumberFormatException unused3) {
            Double.parseDouble(str);
            return true;
        }
    }
}
