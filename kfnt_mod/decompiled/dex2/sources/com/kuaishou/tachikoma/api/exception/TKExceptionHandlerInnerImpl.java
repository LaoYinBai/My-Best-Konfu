package com.kuaishou.tachikoma.api.exception;

import com.kuaishou.tachikoma.api.model.TKBundle;
import com.kwad.v8.V8ScriptCompilationException;
import com.kwad.v8.V8ScriptExecutionException;
import com.tachikoma.core.api.ITKExceptionHandlerInner;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKExceptionHandlerInnerImpl implements ITKExceptionHandlerInner {
    private ITKExceptionHandler mHandler;

    public TKExceptionHandlerInnerImpl(ITKExceptionHandler iTKExceptionHandler) {
        this.mHandler = iTKExceptionHandler;
    }

    public void handleException(Throwable th, int i) {
        if (this.mHandler != null) {
            this.mHandler.handleException(new TKException(th), BundleContextMap.get(i));
        }
    }

    public void handleJSException(Throwable th, int i) {
        ITKExceptionHandler iTKExceptionHandler;
        Throwable tKJSCompilationException;
        if (this.mHandler != null) {
            TKBundle tKBundle = BundleContextMap.get(i);
            if (th instanceof V8ScriptExecutionException) {
                V8ScriptExecutionException v8ScriptExecutionException = (V8ScriptExecutionException) th;
                iTKExceptionHandler = this.mHandler;
                tKJSCompilationException = new TKJSExecutionException(v8ScriptExecutionException.getFileName(), v8ScriptExecutionException.getLineNumber(), v8ScriptExecutionException.getJSMessage(), v8ScriptExecutionException.getSourceLine(), v8ScriptExecutionException.getStartColumn(), v8ScriptExecutionException.getEndColumn(), v8ScriptExecutionException.getJSStackTrace(), v8ScriptExecutionException.getCause());
            } else if (!(th instanceof V8ScriptCompilationException)) {
                this.mHandler.handleException(new TKException(th), tKBundle);
                return;
            } else {
                V8ScriptCompilationException v8ScriptCompilationException = (V8ScriptCompilationException) th;
                iTKExceptionHandler = this.mHandler;
                tKJSCompilationException = new TKJSCompilationException(v8ScriptCompilationException.getFileName(), v8ScriptCompilationException.getLineNumber(), v8ScriptCompilationException.getJSMessage(), v8ScriptCompilationException.getSourceLine(), v8ScriptCompilationException.getStartColumn(), v8ScriptCompilationException.getEndColumn(), v8ScriptCompilationException.getJSStackTrace(), v8ScriptCompilationException.getCause());
            }
            iTKExceptionHandler.handleException(tKJSCompilationException, tKBundle);
        }
    }

    public void handleJSException(Throwable th, String str, int i) {
        ITKExceptionHandler iTKExceptionHandler;
        Throwable tKJSCompilationException;
        if (this.mHandler != null) {
            TKBundle tKBundle = BundleContextMap.get(i);
            if (th instanceof V8ScriptExecutionException) {
                V8ScriptExecutionException v8ScriptExecutionException = (V8ScriptExecutionException) th;
                iTKExceptionHandler = this.mHandler;
                tKJSCompilationException = new TKJSExecutionException(v8ScriptExecutionException.getFileName(), v8ScriptExecutionException.getLineNumber(), str + "_" + v8ScriptExecutionException.getJSMessage(), v8ScriptExecutionException.getSourceLine(), v8ScriptExecutionException.getStartColumn(), v8ScriptExecutionException.getEndColumn(), v8ScriptExecutionException.getJSStackTrace(), v8ScriptExecutionException.getCause());
            } else {
                if (!(th instanceof V8ScriptCompilationException)) {
                    this.mHandler.handleException(new TKException(th), tKBundle);
                    return;
                }
                V8ScriptCompilationException v8ScriptCompilationException = (V8ScriptCompilationException) th;
                iTKExceptionHandler = this.mHandler;
                tKJSCompilationException = new TKJSCompilationException(v8ScriptCompilationException.getFileName(), v8ScriptCompilationException.getLineNumber(), str + "_" + v8ScriptCompilationException.getJSMessage(), v8ScriptCompilationException.getSourceLine(), v8ScriptCompilationException.getStartColumn(), v8ScriptCompilationException.getEndColumn(), v8ScriptCompilationException.getJSStackTrace(), v8ScriptCompilationException.getCause());
            }
            iTKExceptionHandler.handleException(tKJSCompilationException, tKBundle);
        }
    }
}
