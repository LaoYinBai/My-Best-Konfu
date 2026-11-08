package com.kwad.v8.debug;

import com.kwad.v8.Releasable;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.debug.mirror.Frame;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ExecutionState implements Releasable {
    private static final String FRAME = "frame";
    private static final String FRAME_COUNT = "frameCount";
    private static final String PREPARE_STEP = "prepareStep";
    private V8Object v8Object;

    ExecutionState(V8Object v8Object) {
        this.v8Object = v8Object.twin();
    }

    @Override // com.kwad.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.v8Object == null || this.v8Object.isReleased()) {
            return;
        }
        this.v8Object.close();
        this.v8Object = null;
    }

    public Frame getFrame(int i) throws Throwable {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i);
        V8Object v8Object = null;
        try {
            V8Object v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(FRAME, v8Array);
            try {
                Frame frame = new Frame(v8ObjectExecuteObjectFunction);
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return frame;
            } catch (Throwable th) {
                th = th;
                v8Object = v8ObjectExecuteObjectFunction;
                v8Array.close();
                if (v8Object != null) {
                    v8Object.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int getFrameCount() {
        return this.v8Object.executeIntegerFunction(FRAME_COUNT, null);
    }

    public void prepareStep(StepAction stepAction) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(stepAction.index);
        try {
            this.v8Object.executeVoidFunction(PREPARE_STEP, v8Array);
        } finally {
            v8Array.close();
        }
    }

    @Override // com.kwad.v8.Releasable
    @Deprecated
    public void release() {
        close();
    }
}
