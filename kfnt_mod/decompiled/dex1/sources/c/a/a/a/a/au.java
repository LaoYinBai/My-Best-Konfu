package c.a.a.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class au {
    public static String a(String str) {
        byte length = (byte) str.length();
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ length);
        }
        return new String(bytes, 0, bytes.length);
    }
}
