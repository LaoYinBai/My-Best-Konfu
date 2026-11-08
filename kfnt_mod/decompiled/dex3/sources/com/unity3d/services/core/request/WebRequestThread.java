package com.unity3d.services.core.request;

import android.os.ConditionVariable;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.WebRequest;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WebRequestThread {
    private static int _corePoolSize = 1;
    private static long _keepAliveTime = 1000;
    private static int _maximumPoolSize = 1;
    private static CancelableThreadPoolExecutor _pool;
    private static LinkedBlockingQueue<Runnable> _queue;
    private static boolean _ready;
    private static final Object _readyLock = new Object();

    private static synchronized void init() {
        _queue = new LinkedBlockingQueue<>();
        _pool = new CancelableThreadPoolExecutor(_corePoolSize, _maximumPoolSize, _keepAliveTime, TimeUnit.MILLISECONDS, _queue);
        _pool.prestartAllCoreThreads();
        _queue.add(new Runnable() { // from class: com.unity3d.services.core.request.WebRequestThread.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = WebRequestThread._ready = true;
                synchronized (WebRequestThread._readyLock) {
                    WebRequestThread._readyLock.notify();
                }
            }
        });
        while (!_ready) {
            try {
                synchronized (_readyLock) {
                    _readyLock.wait();
                }
            } catch (InterruptedException unused) {
                DeviceLog.debug("Couldn't synchronize thread");
                return;
            }
        }
    }

    public static synchronized void reset() {
        cancel();
        if (_pool != null) {
            _pool.shutdown();
            try {
                _pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException unused) {
            }
            _queue.clear();
            _pool = null;
            _queue = null;
            _ready = false;
        }
    }

    public static synchronized void cancel() {
        if (_pool != null) {
            _pool.cancel();
            Iterator<Runnable> it = _queue.iterator();
            while (it.hasNext()) {
                Runnable next = it.next();
                if (next instanceof WebRequestRunnable) {
                    ((WebRequestRunnable) next).setCancelStatus(true);
                }
            }
            _queue.clear();
            _pool.purge();
        }
    }

    public static synchronized void request(String str, WebRequest.RequestType requestType, Map<String, List<String>> map, Integer num, Integer num2, IWebRequestListener iWebRequestListener) {
        request(str, requestType, map, null, num, num2, iWebRequestListener);
    }

    public static synchronized void request(String str, WebRequest.RequestType requestType, Map<String, List<String>> map, String str2, Integer num, Integer num2, IWebRequestListener iWebRequestListener) {
        if (!_ready) {
            init();
        }
        if (str != null && str.length() >= 3) {
            _queue.add(new WebRequestRunnable(str, requestType.name(), str2, num.intValue(), num2.intValue(), map, iWebRequestListener));
            return;
        }
        iWebRequestListener.onFailed(str, "Request is NULL or too short");
    }

    public static synchronized void setConcurrentRequestCount(int i) {
        _corePoolSize = i;
        _maximumPoolSize = _corePoolSize;
        if (_pool != null) {
            _pool.setCorePoolSize(_corePoolSize);
            _pool.setMaximumPoolSize(_maximumPoolSize);
        }
    }

    public static synchronized void setMaximumPoolSize(int i) {
        _maximumPoolSize = i;
        if (_pool != null) {
            _pool.setMaximumPoolSize(_maximumPoolSize);
        }
    }

    public static synchronized void setKeepAliveTime(long j) {
        _keepAliveTime = j;
        if (_pool != null) {
            _pool.setKeepAliveTime(_keepAliveTime, TimeUnit.MILLISECONDS);
        }
    }

    public static synchronized boolean resolve(final String str, final IResolveHostListener iResolveHostListener) {
        if (str != null) {
            if (str.length() >= 3) {
                new Thread(new Runnable() { // from class: com.unity3d.services.core.request.WebRequestThread.2
                    @Override // java.lang.Runnable
                    public void run() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                        Thread thread;
                        final ConditionVariable conditionVariable = new ConditionVariable();
                        Thread thread2 = null;
                        try {
                            thread = new Thread(new Runnable() { // from class: com.unity3d.services.core.request.WebRequestThread.2.1
                                @Override // java.lang.Runnable
                                public void run() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                                    try {
                                        iResolveHostListener.onResolve(str, InetAddress.getByName(str).getHostAddress());
                                    } catch (UnknownHostException e) {
                                        DeviceLog.exception("Unknown host", e);
                                        iResolveHostListener.onFailed(str, ResolveHostError.UNKNOWN_HOST, e.getMessage());
                                    }
                                    conditionVariable.open();
                                }
                            });
                        } catch (Exception e) {
                            e = e;
                        }
                        try {
                            thread.start();
                            thread2 = thread;
                        } catch (Exception e2) {
                            e = e2;
                            thread2 = thread;
                            DeviceLog.exception("Exception while resolving host", e);
                            iResolveHostListener.onFailed(str, ResolveHostError.UNEXPECTED_EXCEPTION, e.getMessage());
                            if (conditionVariable.block(20000L)) {
                                return;
                            } else {
                                return;
                            }
                        }
                        if (conditionVariable.block(20000L) || thread2 == null) {
                            return;
                        }
                        thread2.interrupt();
                        iResolveHostListener.onFailed(str, ResolveHostError.TIMEOUT, "Timeout");
                    }
                }).start();
                return true;
            }
        }
        iResolveHostListener.onFailed(str, ResolveHostError.INVALID_HOST, "Host is NULL");
        return false;
    }
}
