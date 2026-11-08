package com.unity3d.services.core.cache;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.taobao.accs.common.Constants;
import com.unity3d.services.core.log.DeviceLog;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CacheThread extends Thread {
    public static final int MSG_DOWNLOAD = 1;
    private static CacheThreadHandler _handler;
    private static boolean _ready;
    private static final Object _readyLock = new Object();
    private static int _connectTimeout = 30000;
    private static int _readTimeout = 30000;
    private static int _progressInterval = 0;

    private static void init() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        CacheThread cacheThread = new CacheThread();
        cacheThread.setName("UnityAdsCacheThread");
        cacheThread.start();
        while (!_ready) {
            try {
                synchronized (_readyLock) {
                    _readyLock.wait();
                }
            } catch (InterruptedException unused) {
                DeviceLog.debug("Couldn't synchronize thread");
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        _handler = new CacheThreadHandler();
        _ready = true;
        synchronized (_readyLock) {
            _readyLock.notify();
        }
        Looper.loop();
    }

    public static synchronized void download(String str, String str2, HashMap<String, List<String>> map, boolean z) {
        if (!_ready) {
            init();
        }
        Bundle bundle = new Bundle();
        bundle.putString("source", str);
        bundle.putString(Constants.KEY_TARGET, str2);
        bundle.putInt("connectTimeout", _connectTimeout);
        bundle.putInt("readTimeout", _readTimeout);
        bundle.putInt("progressInterval", _progressInterval);
        bundle.putBoolean("append", z);
        if (map != null) {
            for (String str3 : map.keySet()) {
                bundle.putStringArray(str3, (String[]) map.get(str3).toArray(new String[map.get(str3).size()]));
            }
        }
        Message message = new Message();
        message.what = 1;
        message.setData(bundle);
        _handler.setCancelStatus(false);
        _handler.sendMessage(message);
    }

    public static boolean isActive() {
        if (_ready) {
            return _handler.isActive();
        }
        return false;
    }

    public static void cancel() {
        if (_ready) {
            _handler.removeMessages(1);
            _handler.setCancelStatus(true);
        }
    }

    public static void setProgressInterval(int i) {
        _progressInterval = i;
    }

    public static int getProgressInterval() {
        return _progressInterval;
    }

    public static void setConnectTimeout(int i) {
        _connectTimeout = i;
    }

    public static void setReadTimeout(int i) {
        _readTimeout = i;
    }

    public static int getConnectTimeout() {
        return _connectTimeout;
    }

    public static int getReadTimeout() {
        return _readTimeout;
    }
}
