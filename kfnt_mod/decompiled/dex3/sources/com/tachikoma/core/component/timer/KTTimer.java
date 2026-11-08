package com.tachikoma.core.component.timer;

import androidx.annotation.Keep;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.tachikoma.core.base.NativeModule;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.utility.UIThreadUtil;
import com.tachikoma.core.utility.V8Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KTTimer implements NativeModule {
    private HashMap<Integer, V8Function> cMap = new HashMap<>();
    private HashMap<Integer, TimerTask> tasks = new HashMap<>();
    private HashMap<Integer, V8Function> timeoutCallbacks = new HashMap<>();
    private List<Runnable> pendingRunnableList = new ArrayList();
    private Timer timer = new Timer();

    public void clearInterval(int i) {
        try {
            V8Function v8Function = this.cMap.get(Integer.valueOf(i));
            if (v8Function != null) {
                V8Proxy.release(v8Function);
            }
            this.cMap.remove(Integer.valueOf(i));
            TimerTask timerTask = this.tasks.get(Integer.valueOf(i));
            if (timerTask != null) {
                timerTask.cancel();
            }
            if (this.timer != null) {
                this.timer.purge();
            }
        } catch (Throwable th) {
            TKExceptionDispatcher.reportException(null, th);
        }
    }

    public void clearTimeout(int i) {
        if (this.timeoutCallbacks.containsKey(Integer.valueOf(i))) {
            try {
                V8Function v8Function = this.timeoutCallbacks.get(Integer.valueOf(i));
                if (v8Function != null) {
                    V8Proxy.release(v8Function);
                }
                this.timeoutCallbacks.remove(Integer.valueOf(i));
            } catch (Throwable th) {
                TKExceptionDispatcher.reportException(null, th);
            }
        }
    }

    @Override // com.tachikoma.core.base.NativeModule
    public void destroy() {
        Iterator<V8Function> it = this.cMap.values().iterator();
        while (it.hasNext()) {
            V8Proxy.release(it.next());
        }
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.cMap.clear();
        this.tasks.clear();
        Iterator<Runnable> it2 = this.pendingRunnableList.iterator();
        while (it2.hasNext()) {
            UIThreadUtil.runOnUiThread(it2.next());
        }
        this.pendingRunnableList.clear();
    }

    @Override // com.tachikoma.core.base.NativeModule
    public String getName() {
        return "KTTimer";
    }

    public int setInterval(V8Function v8Function, long j) {
        try {
            final V8Function v8FunctionTwin = v8Function.twin();
            TimerTask timerTask = new TimerTask() { // from class: com.tachikoma.core.component.timer.KTTimer.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    UIThreadUtil.runOnUiThread(new Runnable() { // from class: com.tachikoma.core.component.timer.KTTimer.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (V8Proxy.isV8Valid(v8FunctionTwin)) {
                                    v8FunctionTwin.call((V8Object) null, (V8Array) null);
                                }
                            } catch (Throwable th) {
                                TKExceptionDispatcher.reportException(null, th);
                            }
                        }
                    });
                }
            };
            this.timer.schedule(timerTask, j, j);
            int iHashCode = timerTask.hashCode();
            this.cMap.put(Integer.valueOf(iHashCode), v8FunctionTwin);
            this.tasks.put(Integer.valueOf(iHashCode), timerTask);
            return iHashCode;
        } catch (Throwable th) {
            TKExceptionDispatcher.reportException(null, th);
            return 0;
        }
    }

    public int setTimeout(V8Function v8Function, long j) {
        try {
            final V8Function v8FunctionTwin = v8Function.twin();
            final int iHashCode = v8FunctionTwin.hashCode();
            Runnable runnable = new Runnable() { // from class: com.tachikoma.core.component.timer.KTTimer.2
                @Override // java.lang.Runnable
                public void run() {
                    KTTimer.this.pendingRunnableList.remove(this);
                    if (V8Proxy.isV8Valid(v8FunctionTwin) && KTTimer.this.timeoutCallbacks.containsKey(Integer.valueOf(iHashCode))) {
                        try {
                            v8FunctionTwin.call((V8Object) null, (V8Array) null);
                        } catch (Throwable th) {
                            TKExceptionDispatcher.reportException(null, th);
                        }
                        KTTimer.this.timeoutCallbacks.remove(Integer.valueOf(iHashCode));
                    }
                }
            };
            this.pendingRunnableList.add(runnable);
            UIThreadUtil.postDelay(runnable, j);
            this.timeoutCallbacks.put(Integer.valueOf(iHashCode), v8FunctionTwin);
            return iHashCode;
        } catch (Throwable th) {
            TKExceptionDispatcher.reportException(null, th);
            return 0;
        }
    }
}
