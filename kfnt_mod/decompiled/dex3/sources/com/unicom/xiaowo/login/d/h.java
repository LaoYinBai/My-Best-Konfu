package com.unicom.xiaowo.login.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.sigmob.sdk.common.Constants;
import com.umeng.commonsdk.proguard.az;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import kotlin.UByte;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class h {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(Context context, String str) {
        String strA;
        try {
            strA = com.unicom.xiaowo.login.a.a.a(a(b(context, str)), g.c().substring(0, 16));
        } catch (Exception e) {
            e.printStackTrace();
            strA = "";
        }
        f.a("aesSign:" + strA);
        return strA;
    }

    public static String a(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < bArrDigest.length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += ApkDownloadComplianceInterface.INSTALL_BITS;
                }
                if (i2 < 16) {
                    stringBuffer.append(Constants.FAIL);
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            boolean z = true;
            for (Map.Entry entry : e(new JSONObject(str).toString()).entrySet()) {
                stringBuffer.append(z ? "" : str2);
                stringBuffer.append((String) entry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(entry.getValue() != null ? entry.getValue() : "");
                z = false;
            }
        } catch (Exception e) {
            f.a(e);
        }
        f.a("sign&:" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    private static String a(byte[] bArr) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(bArr);
            int length = bArrDigest.length;
            char[] cArr = new char[length << 1];
            for (int i = 0; i < length; i++) {
                int i2 = bArrDigest[i] & az.m;
                int i3 = i << 1;
                cArr[i3] = a[(bArrDigest[i] & 240) >> 4];
                cArr[i3 + 1] = a[i2];
            }
            return new String(cArr);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean a(Context context) {
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
        if (allNetworkInfo != null && allNetworkInfo.length > 0) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean a(ConnectivityManager connectivityManager) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static int b(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception e) {
            f.b("getOnlineType error:" + e.getMessage());
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() != 1) {
                if (activeNetworkInfo.getType() == 0) {
                    f.a("Only Data");
                    return 0;
                }
                return -1;
            }
            boolean z = context.getPackageManager().checkPermission("android.permission.CHANGE_NETWORK_STATE", context.getPackageName()) == 0;
            f.a("CHANGE_NETWORK_STATE=" + z);
            if (z && a(connectivityManager)) {
                f.a("Data and WIFI");
                return 1;
            }
            f.a("Only WIFI");
            return 2;
        }
        return -1;
    }

    public static String b(String str) {
        int iIndexOf = str.indexOf("://");
        if (iIndexOf > 0) {
            str = str.substring(iIndexOf + 3);
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iIndexOf3 = str.indexOf(47);
        if (iIndexOf3 >= 0) {
            str = str.substring(0, iIndexOf3);
        }
        int iIndexOf4 = str.indexOf(63);
        return iIndexOf4 >= 0 ? str.substring(0, iIndexOf4) : str;
    }

    private static byte[] b(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo.packageName.equals(str)) {
                return packageInfo.signatures[0].toByteArray();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static int c(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & UByte.MAX_VALUE) | ((address[3] & UByte.MAX_VALUE) << 24) | ((address[2] & UByte.MAX_VALUE) << 16) | ((address[1] & UByte.MAX_VALUE) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    public static String d(String str) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.unicom.xiaowo.login.a.b.a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbRkBR4leALApkWRp2ng8zJ2WgI7YEqtMwW9Q1tmRzDLPNhH0ugACfbiStBG4ybdYNHzRlxvOwQ7R0MeN56qEPsv6qieg/HiRXBnQ2hQ2hypo9JHqHx8BX54ESZ+BIf0imjGTcxtHvbzYA04ckmH5Enl2Pkd+R/RZuMK589C7KwQIDAQAB")));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKeyGeneratePublic);
            return URLEncoder.encode(com.unicom.xiaowo.login.a.b.a(cipher.doFinal(str.getBytes())), "UTF-8");
        } catch (Exception e) {
            f.b(e.getMessage());
            return "";
        }
    }

    private static TreeMap e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            TreeMap treeMap = new TreeMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                treeMap.put(next, jSONObject.getString(next));
            }
            return treeMap;
        } catch (Exception e) {
            f.a(e);
            return null;
        }
    }
}
