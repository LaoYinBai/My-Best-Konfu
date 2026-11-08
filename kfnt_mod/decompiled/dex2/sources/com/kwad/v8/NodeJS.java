package com.kwad.v8;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class NodeJS {
    private static final String GLOBAL = "global";
    private static final String NEXT_TICK = "nextTick";
    private static final String NODE = "node";
    private static final String PROCESS = "process";
    private static final String STARTUP_CALLBACK = "__run";
    private static final String STARTUP_SCRIPT = "global.__run(require, exports, module, __filename, __dirname);";
    private static final String STARTUP_SCRIPT_NAME = "startup";
    private static final String TMP_JS_EXT = ".js.tmp";
    private static final String VERSIONS = "versions";
    private String nodeVersion = null;
    private V8Function require;
    private V8 v8;

    private NodeJS(V8 v8) {
        this.v8 = v8;
    }

    public static NodeJS createNodeJS() {
        return createNodeJS(null);
    }

    public static NodeJS createNodeJS(File file) throws Throwable {
        V8 v8CreateV8Runtime = V8.createV8Runtime(GLOBAL);
        NodeJS nodeJS = new NodeJS(v8CreateV8Runtime);
        v8CreateV8Runtime.registerJavaMethod(new JavaVoidCallback() { // from class: com.kwad.v8.NodeJS.1
            @Override // com.kwad.v8.JavaVoidCallback
            public void invoke(V8Object v8Object, V8Array v8Array) {
                V8Function v8Function = (V8Function) v8Array.get(0);
                try {
                    NodeJS.this.init(v8Function.twin());
                } finally {
                    v8Function.close();
                }
            }
        }, STARTUP_CALLBACK);
        try {
            File fileCreateTemporaryScriptFile = createTemporaryScriptFile(STARTUP_SCRIPT, STARTUP_SCRIPT_NAME);
            try {
                v8CreateV8Runtime.createNodeRuntime(fileCreateTemporaryScriptFile.getAbsolutePath());
                if (file != null) {
                    nodeJS.exec(file);
                }
                return nodeJS;
            } finally {
                fileCreateTemporaryScriptFile.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private V8Function createScriptExecutionCallback(final File file) {
        return new V8Function(this.v8, new JavaCallback() { // from class: com.kwad.v8.NodeJS.2
            @Override // com.kwad.v8.JavaCallback
            public Object invoke(V8Object v8Object, V8Array v8Array) {
                V8Array v8Array2 = new V8Array(NodeJS.this.v8);
                try {
                    v8Array2.push(file.getAbsolutePath());
                    return NodeJS.this.require.call(null, v8Array2);
                } finally {
                    v8Array2.close();
                }
            }
        });
    }

    private static File createTemporaryScriptFile(String str, String str2) throws IOException {
        File fileCreateTempFile = File.createTempFile(str2, TMP_JS_EXT);
        PrintWriter printWriter = new PrintWriter(fileCreateTempFile, C.UTF8_NAME);
        try {
            printWriter.print(str);
            return fileCreateTempFile;
        } finally {
            printWriter.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(V8Function v8Function) {
        this.require = v8Function;
    }

    private void safeRelease(Releasable releasable) {
        if (releasable != null) {
            releasable.release();
        }
    }

    public void exec(File file) throws Throwable {
        V8Array v8Array;
        Throwable th;
        V8Object object;
        V8Function v8FunctionCreateScriptExecutionCallback = createScriptExecutionCallback(file);
        try {
            object = this.v8.getObject(PROCESS);
            try {
                v8Array = new V8Array(this.v8);
                try {
                    v8Array.push((V8Value) v8FunctionCreateScriptExecutionCallback);
                    object.executeObjectFunction(NEXT_TICK, v8Array);
                    safeRelease(object);
                    safeRelease(v8Array);
                    safeRelease(v8FunctionCreateScriptExecutionCallback);
                } catch (Throwable th2) {
                    th = th2;
                    safeRelease(object);
                    safeRelease(v8Array);
                    safeRelease(v8FunctionCreateScriptExecutionCallback);
                    throw th;
                }
            } catch (Throwable th3) {
                v8Array = null;
                th = th3;
            }
        } catch (Throwable th4) {
            v8Array = null;
            th = th4;
            object = null;
        }
    }

    public String getNodeVersion() throws Throwable {
        V8Object object;
        Throwable th;
        V8Object object2;
        if (this.nodeVersion != null) {
            return this.nodeVersion;
        }
        try {
            object2 = this.v8.getObject(PROCESS);
            try {
                object = object2.getObject(VERSIONS);
                try {
                    this.nodeVersion = object.getString(NODE);
                    safeRelease(object2);
                    safeRelease(object);
                    return this.nodeVersion;
                } catch (Throwable th2) {
                    th = th2;
                    safeRelease(object2);
                    safeRelease(object);
                    throw th;
                }
            } catch (Throwable th3) {
                object = null;
                th = th3;
            }
        } catch (Throwable th4) {
            object = null;
            th = th4;
            object2 = null;
        }
    }

    public V8 getRuntime() {
        return this.v8;
    }

    public boolean handleMessage() {
        this.v8.checkThread();
        return this.v8.pumpMessageLoop();
    }

    public boolean isRunning() {
        this.v8.checkThread();
        return this.v8.isRunning();
    }

    public void release() {
        this.v8.checkThread();
        if (!this.require.isReleased()) {
            this.require.close();
        }
        if (this.v8.isReleased()) {
            return;
        }
        this.v8.close();
    }

    public V8Object require(File file) {
        this.v8.checkThread();
        V8Array v8Array = new V8Array(this.v8);
        try {
            v8Array.push(file.getAbsolutePath());
            return (V8Object) this.require.call(null, v8Array);
        } finally {
            v8Array.close();
        }
    }
}
