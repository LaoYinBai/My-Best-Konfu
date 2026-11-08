package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;
import android.content.pm.Signature;
import com.bytedance.msdk.adapter.util.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, ArrayList<String>> f2009a = new HashMap<>();

    public static ArrayList<String> a(Context context, String str) {
        String strA;
        ArrayList<String> arrayList = null;
        if (context != null && str != null) {
            String packageName = context.getPackageName();
            if (packageName == null) {
                return null;
            }
            if (f2009a.get(str) != null) {
                return f2009a.get(str);
            }
            arrayList = new ArrayList<>();
            try {
                for (Signature signature : b(context, packageName)) {
                    String str2 = "error!";
                    if ("MD5".equals(str)) {
                        strA = a(signature, "MD5");
                    } else if ("SHA1".equals(str)) {
                        strA = a(signature, "SHA1");
                    } else {
                        if ("SHA256".equals(str)) {
                            strA = a(signature, "SHA256");
                        }
                        arrayList.add(str2);
                    }
                    str2 = strA;
                    arrayList.add(str2);
                }
            } catch (Exception e) {
                Logger.e(e.toString());
            }
            f2009a.put(str, arrayList);
        }
        return arrayList;
    }

    private static Signature[] b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception e) {
            Logger.e(e.toString());
            return null;
        }
    }

    private static String a(Signature signature, String str) throws NoSuchAlgorithmException {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest != null) {
                byte[] bArrDigest = messageDigest.digest(byteArray);
                StringBuilder sb = new StringBuilder();
                for (byte b2 : bArrDigest) {
                    sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3).toUpperCase());
                    sb.append(":");
                }
                return sb.substring(0, sb.length() - 1).toString();
            }
        } catch (Exception e) {
            Logger.e(e.toString());
        }
        return "error!";
    }
}
