package com.sigmob.sdk.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;
import com.sigmob.logger.SigmobLog;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g {
    private static g a;
    private Context b;
    private ExecutorService c = Executors.newFixedThreadPool(4);
    private LruCache<String, Bitmap> d = new LruCache<>(DownloadExpSwitchCode.FIX_DOWNLOADER_ISDOWNLOADING_SETMULTIPROCESS_DEADLOCK);
    private Handler e = new Handler();
    private File f;

    public interface a {
        void a();

        void a(Bitmap bitmap);
    }

    public class b implements Runnable {
        String a;
        int b;
        int c;
        ImageView d;

        public b(String str) {
            this.a = str;
        }

        private Bitmap a() {
            File file = new File(g.this.b(), k.a(this.a.substring(this.a.lastIndexOf("/") + 1)));
            if (!file.exists() || file.length() <= 0) {
                return null;
            }
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }

        private void b() {
            g.this.e.post(new Runnable() { // from class: com.sigmob.sdk.common.utils.g.b.2
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.c == 0 || b.this.d == null) {
                        return;
                    }
                    b.this.d.setImageResource(b.this.c);
                }
            });
        }

        public b a(int i) {
            this.b = i;
            return this;
        }

        public void a(ImageView imageView) {
            this.d = imageView;
            if (this.b != 0 && this.d != null) {
                this.d.setImageResource(this.b);
            }
            if (TextUtils.isEmpty(this.a)) {
                return;
            }
            Bitmap bitmap = (Bitmap) g.this.d.get(this.a);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                return;
            }
            Bitmap bitmapA = a();
            if (bitmapA == null) {
                g.this.c.submit(this);
            } else {
                imageView.setImageBitmap(bitmapA);
                g.this.d.put(this.a, bitmapA);
            }
        }

        public b b(int i) {
            this.c = i;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() throws ProtocolException {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(2000);
                if (httpURLConnection.getResponseCode() == 200) {
                    final Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                    g.this.e.post(new Runnable() { // from class: com.sigmob.sdk.common.utils.g.b.1
                        @Override // java.lang.Runnable
                        public void run() {
                            b.this.d.setImageBitmap(bitmapDecodeStream);
                        }
                    });
                    g.this.d.put(this.a, bitmapDecodeStream);
                    bitmapDecodeStream.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(new File(g.this.b(), k.a(this.a.substring(this.a.lastIndexOf("/") + 1)))));
                } else {
                    b();
                }
            } catch (FileNotFoundException unused) {
            } catch (Exception e) {
                e.printStackTrace();
                b();
            }
        }
    }

    public g(Context context) {
        this.b = context.getApplicationContext();
    }

    public static g a(Context context) {
        return b(context);
    }

    private static g b(Context context) {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g(context);
                }
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File b() {
        return (this.f != null && this.f.isDirectory() && this.f.exists()) ? this.f : Environment.getExternalStorageState() == "mounted" ? this.b.getExternalCacheDir() : this.b.getCacheDir();
    }

    public b a(String str) {
        return new b(str);
    }

    public g a(File file) {
        this.f = file;
        return this;
    }

    public void a() {
        String str;
        try {
            File[] fileArrA = d.a(d.f(b().getAbsolutePath()), 100);
            if (fileArrA == null) {
                str = "native ad file list is null";
            } else {
                str = "native ad file remain num: " + fileArrA.length;
            }
            SigmobLog.i(str);
        } catch (Throwable th) {
            SigmobLog.e("clean native ad file error", th);
        }
    }

    public void a(final String str, final a aVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bitmap bitmap = this.d.get(str);
        Bitmap bitmapDecodeFile = null;
        if (bitmap != null) {
            aVar.a(bitmap);
            return;
        }
        File file = new File(b(), k.a(str.substring(str.lastIndexOf("/") + 1)));
        if (file.exists() && file.length() > 0) {
            bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        if (bitmapDecodeFile == null) {
            this.c.submit(new Runnable() { // from class: com.sigmob.sdk.common.utils.g.1
                @Override // java.lang.Runnable
                public void run() throws ProtocolException {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(2000);
                        if (httpURLConnection.getResponseCode() == 200) {
                            final Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                            httpURLConnection.disconnect();
                            g.this.e.post(new Runnable() { // from class: com.sigmob.sdk.common.utils.g.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    aVar.a(bitmapDecodeStream);
                                }
                            });
                            g.this.d.put(str, bitmapDecodeStream);
                            bitmapDecodeStream.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(new File(g.this.b(), k.a(str.substring(str.lastIndexOf("/") + 1)))));
                        }
                    } catch (Exception unused) {
                        g.this.e.post(new Runnable() { // from class: com.sigmob.sdk.common.utils.g.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                aVar.a();
                            }
                        });
                    }
                }
            });
        } else {
            this.d.put(str, bitmapDecodeFile);
            aVar.a(bitmapDecodeFile);
        }
    }
}
