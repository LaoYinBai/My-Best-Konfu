package com.bytedance.push.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.bytedance.msdk.api.AdError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements a {
    @Override // com.bytedance.push.b.a
    public Bitmap a(c cVar) {
        try {
            return b(cVar);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap b(c cVar) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(cVar.f4756a.toString()).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(AdError.ERROR_CODE_AD_LOAD_SUCCESS);
        httpURLConnection.setUseCaches(true);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= 300) {
            httpURLConnection.disconnect();
            throw new IOException(responseCode + " " + httpURLConnection.getResponseMessage());
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) {
            return null;
        }
        try {
            if (httpURLConnection.getHeaderFieldInt("Content-Length", -1) == 0) {
                com.bytedance.common.push.utility.c.a.a(inputStream);
                throw new IOException("Received response with 0 content-length header.");
            }
            return a(inputStream, cVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            com.bytedance.common.push.utility.c.a.a(inputStream);
        }
    }

    private Bitmap a(InputStream inputStream, c cVar) throws IOException {
        b bVar = new b(inputStream);
        long jA = bVar.a(65536);
        BitmapFactory.Options optionsC = c(cVar);
        boolean zA = a(optionsC);
        boolean zA2 = e.a(bVar);
        bVar.a(jA);
        if (zA2) {
            byte[] bArrB = e.b(bVar);
            if (zA) {
                BitmapFactory.decodeByteArray(bArrB, 0, bArrB.length, optionsC);
                a(cVar.f4757b, cVar.f4758c, optionsC);
            }
            return BitmapFactory.decodeByteArray(bArrB, 0, bArrB.length, optionsC);
        }
        if (zA) {
            BitmapFactory.decodeStream(bVar, null, optionsC);
            a(cVar.f4757b, cVar.f4758c, optionsC);
            bVar.a(jA);
        }
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(bVar, null, optionsC);
        if (bitmapDecodeStream != null) {
            return bitmapDecodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    private static void a(int i, int i2, BitmapFactory.Options options) {
        a(i, i2, options.outWidth, options.outHeight, options);
    }

    private static void a(int i, int i2, int i3, int i4, BitmapFactory.Options options) {
        int iFloor;
        if (i4 > i2 || i3 > i) {
            int iFloor2 = (int) Math.floor(i4 / i2);
            iFloor = (int) Math.floor(i3 / i);
            if (iFloor2 < iFloor) {
                iFloor = iFloor2;
            }
        } else {
            iFloor = 1;
        }
        options.inSampleSize = iFloor;
        options.inJustDecodeBounds = false;
    }

    private static BitmapFactory.Options c(c cVar) {
        boolean zA = cVar.a();
        boolean z = cVar.d != null;
        BitmapFactory.Options options = null;
        if (zA || z) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = zA;
            if (z) {
                options.inPreferredConfig = cVar.d;
            }
        }
        return options;
    }

    private static boolean a(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }
}
