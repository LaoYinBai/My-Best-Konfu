package com.meizu.cloud.pushsdk.b.i;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.NetworkOnMainThreadException;
import android.widget.ImageView;
import com.meizu.cloud.pushsdk.b.a.c;
import com.meizu.cloud.pushsdk.b.c.k;
import com.meizu.cloud.pushsdk.b.g.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static int a(int i, int i2, int i3, int i4) {
        double dMin = Math.min(i / i3, i2 / i4);
        float f = 1.0f;
        while (true) {
            float f2 = 2.0f * f;
            if (f2 > dMin) {
                return (int) f;
            }
            f = f2;
        }
    }

    private static int a(int i, int i2, int i3, int i4, ImageView.ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return i == 0 ? i3 : i;
        }
        if (i == 0) {
            return (int) (i3 * (i2 / i4));
        }
        if (i2 == 0) {
            return i;
        }
        double d = i4 / i3;
        if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            double d2 = i2;
            return ((double) i) * d < d2 ? (int) (d2 / d) : i;
        }
        double d3 = i2;
        return ((double) i) * d > d3 ? (int) (d3 / d) : i;
    }

    public static c<Bitmap> a(k kVar, int i, int i2, Bitmap.Config config, ImageView.ScaleType scaleType) throws IOException {
        Bitmap bitmapCreateScaledBitmap;
        byte[] bArrI = new byte[0];
        try {
            bArrI = g.a(kVar.b().a()).i();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (i == 0 && i2 == 0) {
            options.inPreferredConfig = config;
            bitmapCreateScaledBitmap = BitmapFactory.decodeByteArray(bArrI, 0, bArrI.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArrI, 0, bArrI.length, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            int iA = a(i, i2, i3, i4, scaleType);
            int iA2 = a(i2, i, i4, i3, scaleType);
            options.inJustDecodeBounds = false;
            options.inSampleSize = a(i3, i4, iA, iA2);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrI, 0, bArrI.length, options);
            if (a(bitmapDecodeByteArray, iA, iA2)) {
                bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeByteArray, iA, iA2, true);
                bitmapDecodeByteArray.recycle();
            } else {
                bitmapCreateScaledBitmap = bitmapDecodeByteArray;
            }
        }
        return bitmapCreateScaledBitmap == null ? c.a(b(new com.meizu.cloud.pushsdk.b.b.a(kVar))) : c.a(bitmapCreateScaledBitmap);
    }

    public static com.meizu.cloud.pushsdk.b.b.a a(com.meizu.cloud.pushsdk.b.b.a aVar) {
        aVar.a("connectionError");
        aVar.a(0);
        aVar.b(aVar.getMessage());
        return aVar;
    }

    public static com.meizu.cloud.pushsdk.b.b.a a(com.meizu.cloud.pushsdk.b.b.a aVar, com.meizu.cloud.pushsdk.b.a.b bVar, int i) {
        com.meizu.cloud.pushsdk.b.b.a aVarA = bVar.a(aVar);
        aVarA.a(i);
        aVarA.a("responseFromServerError");
        return aVarA;
    }

    public static com.meizu.cloud.pushsdk.b.b.a a(Exception exc) {
        com.meizu.cloud.pushsdk.b.b.a aVar = new com.meizu.cloud.pushsdk.b.b.a(exc);
        aVar.a((Build.VERSION.SDK_INT < 11 || !(exc instanceof NetworkOnMainThreadException)) ? "connectionError" : "networkOnMainThreadError");
        aVar.a(0);
        return aVar;
    }

    public static String a(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        return contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
    }

    public static void a(k kVar, String str, String str2) throws Throwable {
        InputStream inputStreamB;
        byte[] bArr = new byte[2048];
        FileOutputStream fileOutputStream = null;
        try {
            inputStreamB = kVar.b().b();
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                while (true) {
                    try {
                        int i = inputStreamB.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            fileOutputStream2.write(bArr, 0, i);
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (inputStreamB != null) {
                            try {
                                inputStreamB.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            throw th;
                        }
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            throw th;
                        }
                    }
                }
                fileOutputStream2.flush();
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamB = null;
        }
    }

    private static boolean a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return false;
        }
        return bitmap.getWidth() > i || bitmap.getHeight() > i2;
    }

    public static com.meizu.cloud.pushsdk.b.b.a b(com.meizu.cloud.pushsdk.b.b.a aVar) {
        aVar.a(0);
        aVar.a("parseError");
        aVar.b(aVar.getMessage());
        return aVar;
    }
}
