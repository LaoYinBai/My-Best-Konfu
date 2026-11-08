package com.bytedance.mobile.cpsdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import com.bytedance.mobile.cpsdk.CPConfig;
import cp.bd.c.b;
import cp.bd.c.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CPManagerUtils {
    private static final int CPP_LSHIFT = 24;
    private static final int JAVA_LSHIFT = 28;
    private static String TAG = "METASEC";
    private static int maxImageNum = 10;

    public static List<Bitmap> extractImages(String str) {
        ArrayList arrayList = new ArrayList();
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        String str2 = options.outMimeType;
        Log.d("METASEC", "图片类型1：".concat(String.valueOf(str2)));
        if (str2.contains("gif")) {
            b bVar = new b();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(new File(str));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                bVar.a(fileInputStream);
                int iA = bVar.a();
                int i = iA > maxImageNum ? maxImageNum : iA;
                for (int i2 = 0; i2 < iA; i2 += iA / i) {
                    arrayList.add(bVar.a(i2));
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            arrayList.add(bitmapDecodeFile);
        }
        return arrayList;
    }

    public static List<Bitmap> extractVideo(String str) throws IllegalArgumentException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
        Log.e("TAG", String.format("frameCount :: %s", strExtractMetadata));
        if (strExtractMetadata == null) {
            return null;
        }
        long j = Long.parseLong(strExtractMetadata) * 1000;
        ArrayList arrayList = new ArrayList();
        int i = maxImageNum;
        for (long j2 = 0; j2 < j; j2 += j / i) {
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(j2, 2);
            if (frameAtTime != null) {
                arrayList.add(frameAtTime);
            }
        }
        return arrayList;
    }

    public static byte[] processFrames(List<Bitmap> list, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        ArrayList arrayList = new ArrayList();
        for (Bitmap bitmap : list) {
            long height = bitmap.getHeight();
            long width = bitmap.getWidth();
            int i = (int) width;
            int i2 = (int) height;
            int[] iArr = new int[i * i2];
            bitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
            arrayList.add(Long.valueOf(height));
            arrayList.add(Long.valueOf(width));
            arrayList.add(Long.valueOf(4 * width));
            arrayList.add(0L);
            arrayList.add(0L);
            arrayList.add(iArr);
        }
        byte[] bArr = (byte[]) d.a(16777219, (int) contentType.getContentType(), cPScenes.getScene(), (String) null, arrayList.toArray());
        Log.d("METASEC", "runModelOnImage ret:".concat(String.valueOf(bArr)));
        return bArr;
    }

    public static byte[] processOnImage(Bitmap bitmap, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        long height = bitmap.getHeight();
        long width = bitmap.getWidth();
        int i = (int) width;
        int i2 = (int) height;
        int[] iArr = new int[i * i2];
        bitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(height));
        arrayList.add(Long.valueOf(width));
        arrayList.add(Long.valueOf(4 * width));
        arrayList.add(0L);
        arrayList.add(0L);
        arrayList.add(iArr);
        byte[] bArr = (byte[]) d.a(16777218, (int) contentType.getContentType(), cPScenes.getScene(), (String) null, arrayList.toArray());
        Log.d("METASEC", "runModelOnImage ret:".concat(String.valueOf(bArr)));
        return bArr;
    }

    public static CPConfig.CPVerrificationResult processText(String str, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        int iIntValue = ((Integer) d.a(16777220, (int) contentType.getContentType(), cPScenes.getScene(), str, (Object) null)).intValue();
        return iIntValue == CPConfig.CPVerrificationResult.CPVerificationResultPass.getResult() ? CPConfig.CPVerrificationResult.CPVerificationResultPass : iIntValue == CPConfig.CPVerrificationResult.CPVerificationResultReject.getResult() ? CPConfig.CPVerrificationResult.CPVerificationResultReject : iIntValue == CPConfig.CPVerrificationResult.CPVerificationResultSelf.getResult() ? CPConfig.CPVerrificationResult.CPVerificationResultSelf : CPConfig.CPVerrificationResult.CPVerificationResultPass;
    }
}
