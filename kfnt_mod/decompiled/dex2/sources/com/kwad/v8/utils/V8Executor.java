package com.kwad.v8.utils;

import com.kwad.v8.JavaVoidCallback;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8Executor extends Thread {
    private Exception exception;
    private volatile boolean forceTerminating;
    private boolean longRunning;
    private String messageHandler;
    private LinkedList<String[]> messageQueue;
    private String result;
    private V8 runtime;
    private final String script;
    private volatile boolean shuttingDown;
    private volatile boolean terminated;

    class ExecutorTermination implements JavaVoidCallback {
        ExecutorTermination() {
        }

        @Override // com.kwad.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (V8Executor.this.forceTerminating) {
                throw new RuntimeException("V8Thread Termination");
            }
        }
    }

    public V8Executor(String str) {
        this(str, false, null);
    }

    public V8Executor(String str, boolean z, String str2) {
        this.terminated = false;
        this.shuttingDown = false;
        this.forceTerminating = false;
        this.exception = null;
        this.messageQueue = new LinkedList<>();
        this.script = str;
        this.longRunning = z;
        this.messageHandler = str2;
    }

    public void forceTermination() {
        synchronized (this) {
            this.forceTerminating = true;
            this.shuttingDown = true;
            if (this.runtime != null) {
                this.runtime.terminateExecution();
            }
            notify();
        }
    }

    public Exception getException() {
        return this.exception;
    }

    public String getResult() {
        return this.result;
    }

    public boolean hasException() {
        return this.exception != null;
    }

    public boolean hasTerminated() {
        return this.terminated;
    }

    public boolean isShuttingDown() {
        return this.shuttingDown;
    }

    public boolean isTerminating() {
        return this.forceTerminating;
    }

    public void postMessage(String... strArr) {
        synchronized (this) {
            this.messageQueue.add(strArr);
            notify();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0099, code lost:
    
        if (r8.messageQueue.isEmpty() != false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
    
        r3 = 0;
        r2 = r8.messageQueue.remove(0);
        r4 = new com.kwad.v8.V8Array(r8.runtime);
        r5 = new com.kwad.v8.V8Array(r8.runtime);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b2, code lost:
    
        r6 = r2.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b3, code lost:
    
        if (r3 >= r6) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b5, code lost:
    
        r5.push(r2[r3]);
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bd, code lost:
    
        r4.push((com.kwad.v8.V8Value) r5);
        r8.runtime.executeVoidFunction(r8.messageHandler, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c7, code lost:
    
        r5.close();
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ce, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00cf, code lost:
    
        r5.close();
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d5, code lost:
    
        throw r2;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.v8.utils.V8Executor.run():void");
    }

    protected void setup(V8 v8) {
    }

    public void shutdown() {
        synchronized (this) {
            this.shuttingDown = true;
            notify();
        }
    }
}
