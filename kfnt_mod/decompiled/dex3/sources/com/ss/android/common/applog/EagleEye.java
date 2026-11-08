package com.ss.android.common.applog;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Pair;
import c.a.a.a.a.bk;
import com.taobao.accs.utl.UtilityImpl;
import com.xiaomi.mipush.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipFile;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class EagleEye {
    private static final String TAG;
    private static boolean isInited;
    private static IEagleEyeInterface mIEagleEyeInterface;
    private static List<ScanResult> mScanResults;
    private static boolean sApkAllUploaded;
    private static WifiManager wifi;

    public interface IEagleEyeInterface {
        String appVersion();

        String channel();

        String deviceId();

        String huoshanId();

        Pair<Double, Double> location();

        String networkType();

        String nickName();

        String userId();
    }

    static {
        try {
            bk.a(GlobalContext.getContext(), "gp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        TAG = EagleEye.class.getSimpleName();
        isInited = false;
    }

    public static String byteArrayToHexStr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] charArray = "0123456789abcdef".toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = i * 2;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        return new String(cArr);
    }

    public static int bytesToInt(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i];
        }
        return new BigInteger(bArr2).intValue();
    }

    public static byte[] compress(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes());
        gZIPOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    private static String decryptAES(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String s = UserInfo.getS();
        byte[] bArrHexStringToByteArray = hexStringToByteArray(str);
        SecretKeySpec secretKeySpec = new SecretKeySpec(s.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(UserInfo.getT());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding ");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return new String(cipher.doFinal(bArrHexStringToByteArray), "UTF-8");
    }

    private static String decryptAES2(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String s = UserInfo.getS();
        byte[] bArrHexStringToByteArray = hexStringToByteArray(str);
        SecretKeySpec secretKeySpec = new SecretKeySpec(s.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(UserInfo.getT());
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding ");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return new String(cipher.doFinal(bArrHexStringToByteArray), "UTF-8");
    }

    public static byte[] encryptAES(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(UserInfo.getS().getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(UserInfo.getT());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding ");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static byte[] encryptAES2(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(UserInfo.getS().getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(UserInfo.getT());
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding ");
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static String getCRC32(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        return "" + crc32.getValue();
    }

    public static String getCRC32(byte[] bArr) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return "" + crc32.getValue();
    }

    public static String getDexCRC(Context context, int i) {
        String packageCodePath = context.getPackageCodePath();
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            try {
                str = str + Constants.ACCEPT_TIME_SEPARATOR_SP + new ZipFile(packageCodePath).getEntry(i2 == 0 ? "classes.dex" : "classes" + i2 + ".dex").getCrc();
            } catch (Exception unused) {
            }
        }
        return !TextUtils.isEmpty(str) ? str.substring(1) : "";
    }

    public static String getIpAddressJava() {
        String hostAddress = "";
        String hostAddress2 = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ("wlan0".equals(networkInterfaceNextElement.getName())) {
                    for (InterfaceAddress interfaceAddress : networkInterfaceNextElement.getInterfaceAddresses()) {
                        if (interfaceAddress.getAddress() instanceof Inet4Address) {
                            hostAddress = ((Inet4Address) interfaceAddress.getAddress()).getHostAddress();
                        }
                    }
                }
                if ("rmnet0".equals(networkInterfaceNextElement.getName())) {
                    for (InterfaceAddress interfaceAddress2 : networkInterfaceNextElement.getInterfaceAddresses()) {
                        if (interfaceAddress2.getAddress() instanceof Inet4Address) {
                            hostAddress2 = ((Inet4Address) interfaceAddress2.getAddress()).getHostAddress();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return !hostAddress.equals("") ? hostAddress : hostAddress2;
    }

    public static String getPrivacyString(String str) {
        try {
            return decryptAES2(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<ScanResult> getScanResults() {
        return mScanResults;
    }

    public static String getVericationString(String str) {
        try {
            return decryptAES(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static String getWifiBSSID(Context context) {
        try {
            return ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo().getBSSID();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static synchronized void init(Context context) {
        if (isInited) {
            return;
        }
        isInited = true;
    }

    public static String sendVerification(String str) {
        try {
            return byteArrayToHexStr(encryptAES(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setIEagleEyeInterface(IEagleEyeInterface iEagleEyeInterface) {
        mIEagleEyeInterface = iEagleEyeInterface;
    }

    public static String tanscode(String str) {
        try {
            String strByteArrayToHexStr = byteArrayToHexStr(encryptAES(str.getBytes()));
            return byteArrayToHexStr(compress(strByteArrayToHexStr + Constants.ACCEPT_TIME_SEPARATOR_SP + getCRC32(strByteArrayToHexStr)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String tanscode2(String str) {
        try {
            return byteArrayToHexStr(encryptAES2(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized void uninit(Context context) {
    }

    public static boolean useVpn(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    arrayList.add(networkInterface.getName());
                }
            }
        } catch (Exception unused) {
        }
        return arrayList.contains("tun0");
    }
}
