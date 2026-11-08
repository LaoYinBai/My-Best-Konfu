package com.umeng.message.inapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class UImageLoadTask extends AsyncTask<String, Void, Bitmap[]> {
    private static final String a = "com.umeng.message.inapp.UImageLoadTask";
    private ImageLoaderCallback b;
    private String c;
    private BitmapFactory.Options d;

    interface ImageLoaderCallback {
        void onLoadImage(Bitmap[] bitmapArr);
    }

    public UImageLoadTask(Context context, UInAppMessage uInAppMessage) {
        this.c = h.d(context, uInAppMessage.msg_id);
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            this.d = new BitmapFactory.Options();
            this.d.inSampleSize = a(uInAppMessage, i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap[] doInBackground(String... strArr) {
        Bitmap[] bitmapArr = new Bitmap[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                if (!a(strArr[i])) {
                    bitmapArr[i] = b(strArr[i]);
                    if (bitmapArr[i] == null) {
                        bitmapArr[i] = c(strArr[i]);
                        a(bitmapArr[i], strArr[i]);
                    }
                } else {
                    bitmapArr[i] = b(strArr[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmapArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Bitmap[] bitmapArr) {
        super.onPostExecute(bitmapArr);
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap == null) {
                return;
            }
        }
        if (this.b != null) {
            this.b.onLoadImage(bitmapArr);
        }
    }

    private boolean a(String str) {
        return new File(this.c, str.hashCode() + "").exists();
    }

    private Bitmap b(String str) {
        Bitmap bitmapDecodeFile;
        Bitmap bitmap = null;
        try {
            bitmapDecodeFile = BitmapFactory.decodeFile(this.c + (str.hashCode() + ""));
        } catch (Exception e) {
            e = e;
        }
        try {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "load from local");
            return bitmapDecodeFile;
        } catch (Exception e2) {
            bitmap = bitmapDecodeFile;
            e = e2;
            e.printStackTrace();
            return bitmap;
        }
    }

    private Bitmap c(String str) throws IOException {
        Bitmap bitmapDecodeStream;
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "Downloading image start");
        URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
        uRLConnectionOpenConnection.connect();
        InputStream inputStream = uRLConnectionOpenConnection.getInputStream();
        if (this.d == null) {
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
        } else {
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "decode options");
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, this.d);
        }
        inputStream.close();
        UMLog uMLog3 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "Downloading image finish");
        return bitmapDecodeStream;
    }

    private void a(Bitmap bitmap, String str) throws IOException {
        boolean z;
        boolean zCompress;
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return;
        }
        try {
            File file = new File(this.c);
            if (!file.exists()) {
                file.mkdirs();
            }
            fileOutputStream = new FileOutputStream(new File(this.c, str.hashCode() + ""));
            zCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e = e;
            z = false;
        }
        try {
            fileOutputStream.close();
        } catch (Exception e2) {
            z = zCompress;
            e = e2;
            e.printStackTrace();
            zCompress = z;
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "store bitmap" + zCompress);
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "store bitmap" + zCompress);
    }

    private static int a(UInAppMessage uInAppMessage, int i, int i2) {
        int i3 = uInAppMessage.height;
        int i4 = uInAppMessage.width;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public void a(ImageLoaderCallback imageLoaderCallback) {
        this.b = imageLoaderCallback;
    }
}
