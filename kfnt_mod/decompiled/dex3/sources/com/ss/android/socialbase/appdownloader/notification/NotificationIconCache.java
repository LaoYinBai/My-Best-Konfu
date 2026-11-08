package com.ss.android.socialbase.appdownloader.notification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationIconCache {
    private static int MAX_CACHE_ICON_COUNT = 8;
    private static volatile NotificationIconCache sInstance;
    private IconLRUCache<Integer, Bitmap> mIconCache;

    private static class IconLRUCache<K, T> extends LinkedHashMap<K, T> {
        final int mMaxSize;

        public IconLRUCache(int i, int i2) {
            super(i2, 0.75f, true);
            this.mMaxSize = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, T> entry) {
            return size() > this.mMaxSize;
        }
    }

    public static NotificationIconCache inst() {
        if (sInstance == null) {
            synchronized (NotificationIconCache.class) {
                if (sInstance == null) {
                    sInstance = new NotificationIconCache();
                }
            }
        }
        return sInstance;
    }

    private NotificationIconCache() {
        this.mIconCache = null;
        this.mIconCache = new IconLRUCache<>(MAX_CACHE_ICON_COUNT, MAX_CACHE_ICON_COUNT / 2);
    }

    public Bitmap getAppIconByInfoId(int i) {
        return this.mIconCache.get(Integer.valueOf(i));
    }

    public void putAppIcon(int i, Bitmap bitmap) {
        this.mIconCache.put(Integer.valueOf(i), bitmap);
    }

    public void parseAndCacheIcon(final int i, final String str) {
        if (TextUtils.isEmpty(str) || getAppIconByInfoId(i) != null) {
            return;
        }
        DownloadComponentManager.getIOThreadExecutor().submit(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.notification.NotificationIconCache.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v0 */
            /* JADX WARN: Type inference failed for: r2v12 */
            /* JADX WARN: Type inference failed for: r2v13 */
            /* JADX WARN: Type inference failed for: r2v5, types: [java.io.Closeable[]] */
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                ByteArrayOutputStream byteArrayOutputStreamCloneInputStream;
                ByteArrayInputStream byteArrayInputStream;
                ByteArrayInputStream byteArrayInputStream2;
                Throwable th;
                InputStream inputStream;
                Exception e;
                IDownloadHttpConnection iDownloadHttpConnectionDownloadWithConnection;
                int i2 = 4;
                i2 = 4;
                i2 = 4;
                i2 = 4;
                i2 = 4;
                try {
                    try {
                        iDownloadHttpConnectionDownloadWithConnection = DownloadComponentManager.downloadWithConnection(true, 0, str, null);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    byteArrayOutputStreamCloneInputStream = null;
                    byteArrayInputStream = null;
                    byteArrayInputStream2 = null;
                    e = e2;
                    inputStream = null;
                } catch (Throwable th3) {
                    byteArrayOutputStreamCloneInputStream = null;
                    byteArrayInputStream = null;
                    byteArrayInputStream2 = null;
                    th = th3;
                    inputStream = null;
                }
                if (iDownloadHttpConnectionDownloadWithConnection != null) {
                    inputStream = iDownloadHttpConnectionDownloadWithConnection.getInputStream();
                    try {
                        byteArrayOutputStreamCloneInputStream = NotificationIconCache.cloneInputStream(inputStream);
                        try {
                            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStreamCloneInputStream.toByteArray());
                        } catch (Exception e3) {
                            byteArrayInputStream2 = null;
                            e = e3;
                            byteArrayInputStream = null;
                        } catch (Throwable th4) {
                            byteArrayInputStream2 = null;
                            th = th4;
                            byteArrayInputStream = null;
                        }
                        try {
                            byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStreamCloneInputStream.toByteArray());
                            try {
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeStream(byteArrayInputStream, null, options);
                                int i3 = options.outWidth;
                                int i4 = options.outHeight;
                                int iDp2px = AppDownloadUtils.dp2px(DownloadComponentManager.getAppContext(), 44.0f);
                                options.inSampleSize = NotificationIconCache.getFitInSampleSize(iDp2px, iDp2px, options);
                                options.inJustDecodeBounds = false;
                                NotificationIconCache.this.mIconCache.put(Integer.valueOf(i), BitmapFactory.decodeStream(byteArrayInputStream2, null, options));
                                i2 = new Closeable[]{inputStream, byteArrayOutputStreamCloneInputStream, byteArrayInputStream, byteArrayInputStream2};
                            } catch (Exception e4) {
                                e = e4;
                                e.printStackTrace();
                                i2 = new Closeable[]{inputStream, byteArrayOutputStreamCloneInputStream, byteArrayInputStream, byteArrayInputStream2};
                                DownloadUtils.safeClose((Closeable[]) i2);
                                return;
                            }
                        } catch (Exception e5) {
                            byteArrayInputStream2 = null;
                            e = e5;
                        } catch (Throwable th5) {
                            byteArrayInputStream2 = null;
                            th = th5;
                            Closeable[] closeableArr = new Closeable[i2];
                            closeableArr[0] = inputStream;
                            closeableArr[1] = byteArrayOutputStreamCloneInputStream;
                            closeableArr[2] = byteArrayInputStream;
                            closeableArr[3] = byteArrayInputStream2;
                            DownloadUtils.safeClose(closeableArr);
                            throw th;
                        }
                    } catch (Exception e6) {
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        e = e6;
                        byteArrayOutputStreamCloneInputStream = null;
                    } catch (Throwable th6) {
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        th = th6;
                        byteArrayOutputStreamCloneInputStream = null;
                    }
                    DownloadUtils.safeClose((Closeable[]) i2);
                    return;
                }
                DownloadUtils.safeClose(null, null, null, null);
            }
        });
    }

    public static int getFitInSampleSize(int i, int i2, BitmapFactory.Options options) {
        if (options.outWidth > i || options.outHeight > i2) {
            return Math.min(Math.round(options.outWidth / i), Math.round(options.outHeight / i2));
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteArrayOutputStream cloneInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[d.d];
        while (true) {
            int i = inputStream.read(bArr);
            if (i > -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream;
            }
        }
    }
}
