package com.sigmob.sdk.common.utils;

import android.graphics.Bitmap;
import com.qq.e.comm.constants.Constants;
import java.io.FileInputStream;
import java.util.HashMap;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    private static final HashMap<String, String> a = new HashMap<>();

    static {
        a.put("FFD8FF", "jpg");
        a.put("89504E47", "png");
        a.put("47494638", "gif");
        a.put("49492A00", "tif");
        a.put("424D", "bmp");
        a.put("41433130", "dwg");
        a.put("38425053", "psd");
        a.put("7B5C727466", "rtf");
        a.put("3C3F786D6C", "xml");
        a.put("68746D6C3E", "html");
        a.put("44656C69766572792D646174653A", "eml");
        a.put("D0CF11E0", "doc");
        a.put("5374616E64617264204A", "mdb");
        a.put("252150532D41646F6265", Constants.KEYS.PLACEMENTS);
        a.put("255044462D312E", "pdf");
        a.put("504B0304", "docx");
        a.put("52617221", "rar");
        a.put("57415645", "wav");
        a.put("41564920", "avi");
        a.put("2E524D46", "rm");
        a.put("000001BA", "mpg");
        a.put("000001B3", "mpg");
        a.put("6D6F6F76", "mov");
        a.put("3026B2758E66CF11", "asf");
        a.put("4D546864", "mid");
        a.put("1F8B08", "gz");
        a.put("4D5A9000", "exe/dll");
        a.put("75736167", "txt");
    }

    public static String a(String str) {
        return a.get(b(str));
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & UByte.MAX_VALUE).toUpperCase();
            if (upperCase.length() < 2) {
                sb.append(0);
            }
            sb.append(upperCase);
        }
        return sb.toString();
    }

    public static void a(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    private static String b(String str) throws Throwable {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[4];
                fileInputStream.read(bArr, 0, bArr.length);
                String strA = a(bArr);
                try {
                    fileInputStream.close();
                    return strA;
                } catch (Throwable unused) {
                    return strA;
                }
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }
}
