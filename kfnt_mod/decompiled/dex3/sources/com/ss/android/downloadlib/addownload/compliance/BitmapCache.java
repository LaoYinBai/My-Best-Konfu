package com.ss.android.downloadlib.addownload.compliance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.Chain;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.LruCache;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapCache extends LruCache<Long, Bitmap> {
    private final Map<Long, SoftReference<Callback>> callbackMap;

    public interface Callback {
        void loadFinish(Bitmap bitmap);
    }

    private static class SingleTonHolder {
        private static BitmapCache INSTANCE = new BitmapCache();

        private SingleTonHolder() {
        }
    }

    public static BitmapCache getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private BitmapCache() {
        super(8, 8);
        this.callbackMap = new HashMap();
    }

    public void setCallback(long j, @NonNull Callback callback) {
        if (get(Long.valueOf(j)) != null) {
            callback.loadFinish((Bitmap) get(Long.valueOf(j)));
        } else {
            this.callbackMap.put(Long.valueOf(j), new SoftReference<>(callback));
        }
    }

    public void loadBitmap(final long j, final long j2, final String str) throws JSONException {
        if (get(Long.valueOf(j)) != null) {
            SoftReference<Callback> softReferenceRemove = this.callbackMap.remove(Long.valueOf(j));
            if (softReferenceRemove == null || softReferenceRemove.get() == null) {
                return;
            }
            softReferenceRemove.get().loadFinish((Bitmap) get(Long.valueOf(j)));
            return;
        }
        if (TextUtils.isEmpty(str)) {
            EventSender.sendErrorEvent(12, j2);
        } else {
            Chain.createOnIo(new Chain.IRun<Object, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.BitmapCache.2
                @Override // com.ss.android.downloadlib.utils.Chain.IRun
                public Object run(Object obj) throws Throwable {
                    Throwable th;
                    BufferedInputStream bufferedInputStream;
                    Closeable[] closeableArr;
                    IDownloadHttpConnection iDownloadHttpConnectionDownloadWithConnection;
                    try {
                        try {
                            iDownloadHttpConnectionDownloadWithConnection = DownloadComponentManager.downloadWithConnection(true, 0, str, null);
                        } catch (Throwable th2) {
                            th = th2;
                            DownloadUtils.safeClose(null);
                            throw th;
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        DownloadUtils.safeClose(null);
                        throw th;
                    }
                    if (iDownloadHttpConnectionDownloadWithConnection != null) {
                        bufferedInputStream = new BufferedInputStream(iDownloadHttpConnectionDownloadWithConnection.getInputStream());
                        try {
                            bufferedInputStream.mark(bufferedInputStream.available());
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeStream(bufferedInputStream, null, options);
                            int i = options.outWidth;
                            int i2 = options.outHeight;
                            int iDp2px = ToolUtils.dp2px(GlobalInfo.getContext(), 60.0f);
                            options.inSampleSize = BitmapCache.getFitInSampleSize(iDp2px, iDp2px, options);
                            options.inJustDecodeBounds = false;
                            bufferedInputStream.reset();
                            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(bufferedInputStream, null, options);
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.putOpt(EventConstants.ExtraJson.KEY_TYPE, "load_bitmap");
                                jSONObject.putOpt("bm_original_w", Integer.valueOf(i));
                                jSONObject.putOpt("bm_original_h", Integer.valueOf(i2));
                                jSONObject.putOpt("bm_bytes", Integer.valueOf(bitmapDecodeStream == null ? -1 : bitmapDecodeStream.getByteCount()));
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.TTD_PREF_MONITOR, jSONObject, j2);
                            BitmapCache.this.put(Long.valueOf(j), bitmapDecodeStream);
                            closeableArr = new Closeable[]{bufferedInputStream};
                        } catch (Exception e3) {
                            e = e3;
                            TTDownloaderMonitor.inst().monitorException(e, "BitmapCache loadBitmap");
                            closeableArr = new Closeable[]{bufferedInputStream};
                            DownloadUtils.safeClose(closeableArr);
                            return null;
                        }
                        DownloadUtils.safeClose(closeableArr);
                        return null;
                    }
                    DownloadUtils.safeClose(null);
                    return null;
                }
            }, null).nextOnMain(new Chain.IRun<Object, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.BitmapCache.1
                @Override // com.ss.android.downloadlib.utils.Chain.IRun
                public Object run(Object obj) {
                    SoftReference softReference = (SoftReference) BitmapCache.this.callbackMap.remove(Long.valueOf(j));
                    if (softReference == null || softReference.get() == null) {
                        return null;
                    }
                    ((Callback) softReference.get()).loadFinish((Bitmap) BitmapCache.this.get(Long.valueOf(j)));
                    return null;
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getFitInSampleSize(int i, int i2, BitmapFactory.Options options) {
        if (options.outWidth > i || options.outHeight > i2) {
            return Math.min(Math.round(options.outWidth / i), Math.round(options.outHeight / i2));
        }
        return 1;
    }
}
