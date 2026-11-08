package com.kwad.v8.debug;

import com.kwad.v8.JavaVoidCallback;
import com.kwad.v8.Releasable;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DebugHandler implements Releasable {
    private static final String CHANGE_BREAK_POINT_CONDITION = "changeBreakPointCondition";
    private static final String CLEAR_BREAK_POINT = "clearBreakPoint";
    private static final String DEBUG_BREAK_HANDLER = "__j2v8_debug_handler";
    public static String DEBUG_OBJECT_NAME = "__j2v8_Debug";
    private static final String DISABLE_ALL_BREAK_POINTS = "disableAllBreakPoints";
    private static final String DISABLE_SCRIPT_BREAK_POINT = "disableScriptBreakPoint";
    private static final String ENABLE_SCRIPT_BREAK_POINT = "enableScriptBreakPoint";
    private static final String FIND_SCRIPT_BREAK_POINT = "findScriptBreakPoint";
    private static final String NUMBER = "number";
    private static final String SCRIPT_BREAK_POINTS = "scriptBreakPoints";
    private static final String SET_BREAK_POINT = "setBreakPoint";
    private static final String SET_LISTENER = "setListener";
    private static final String SET_SCRIPT_BREAK_POINT_BY_NAME = "setScriptBreakPointByName";
    private static final String V8_DEBUG_OBJECT = "Debug";
    private List<BreakHandler> breakHandlers = new ArrayList();
    private V8Object debugObject;
    private V8 runtime;

    private class BreakpointHandler implements JavaVoidCallback {
        private BreakpointHandler() {
        }

        private EventData createDebugEvent(DebugEvent debugEvent, V8Object v8Object) {
            switch (debugEvent) {
                case Break:
                    return new BreakEvent(v8Object);
                case BeforeCompile:
                    return new CompileEvent(v8Object);
                case AfterCompile:
                    return new CompileEvent(v8Object);
                case Exception:
                    return new ExceptionEvent(v8Object);
                default:
                    return new EventData(v8Object);
            }
        }

        private void invokeHandler(V8Array v8Array, int i, BreakHandler breakHandler) throws Throwable {
            V8Object object;
            V8Object object2;
            V8Object object3;
            Releasable releasable;
            EventData eventDataCreateDebugEvent;
            ExecutionState executionState = null;
            try {
                object2 = v8Array.getObject(1);
            } catch (Throwable th) {
                th = th;
                object = null;
                object2 = null;
                object3 = null;
            }
            try {
                object3 = v8Array.getObject(2);
                try {
                    object = v8Array.getObject(3);
                    try {
                        ExecutionState executionState2 = new ExecutionState(object2);
                        try {
                            DebugEvent debugEvent = DebugEvent.values()[i];
                            eventDataCreateDebugEvent = createDebugEvent(debugEvent, object3);
                            try {
                                breakHandler.onBreak(debugEvent, executionState2, eventDataCreateDebugEvent, object);
                                safeRelease(object2);
                                safeRelease(object3);
                                safeRelease(object);
                                safeRelease(executionState2);
                                safeRelease(eventDataCreateDebugEvent);
                            } catch (Throwable th2) {
                                th = th2;
                                executionState = executionState2;
                                releasable = eventDataCreateDebugEvent;
                                safeRelease(object2);
                                safeRelease(object3);
                                safeRelease(object);
                                safeRelease(executionState);
                                safeRelease(releasable);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            eventDataCreateDebugEvent = null;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        releasable = null;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    object = null;
                    releasable = null;
                }
            } catch (Throwable th6) {
                th = th6;
                object = null;
                object3 = null;
                releasable = object3;
                safeRelease(object2);
                safeRelease(object3);
                safeRelease(object);
                safeRelease(executionState);
                safeRelease(releasable);
                throw th;
            }
        }

        private void safeRelease(Releasable releasable) {
            if (releasable != null) {
                releasable.release();
            }
        }

        @Override // com.kwad.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) throws Throwable {
            if (v8Array == null || v8Array.isUndefined()) {
                return;
            }
            int integer = v8Array.getInteger(0);
            Iterator it = DebugHandler.this.breakHandlers.iterator();
            while (it.hasNext()) {
                invokeHandler(v8Array, integer, (BreakHandler) it.next());
            }
        }
    }

    public enum DebugEvent {
        Undefined(0),
        Break(1),
        Exception(2),
        NewFunction(3),
        BeforeCompile(4),
        AfterCompile(5),
        CompileError(6),
        PromiseError(7),
        AsyncTaskEvent(8);

        int index;

        DebugEvent(int i) {
            this.index = i;
        }
    }

    public DebugHandler(V8 v8) throws Throwable {
        this.runtime = v8;
        setupDebugObject(v8);
        setupBreakpointHandler();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupBreakpointHandler() throws Throwable {
        V8Array v8Array;
        V8Function v8Function;
        V8Function v8Function2 = null;
        this.debugObject.registerJavaMethod(new BreakpointHandler(), DEBUG_BREAK_HANDLER);
        try {
            v8Function = (V8Function) this.debugObject.getObject(DEBUG_BREAK_HANDLER);
            try {
                v8Array = new V8Array(this.runtime);
            } catch (Throwable th) {
                v8Function2 = v8Function;
                th = th;
                v8Array = null;
            }
        } catch (Throwable th2) {
            th = th2;
            v8Array = null;
        }
        try {
            v8Array.push((V8Value) v8Function);
            this.debugObject.executeFunction(SET_LISTENER, v8Array);
            if (v8Function != null && !v8Function.isReleased()) {
                v8Function.close();
            }
            if (v8Array.isReleased()) {
                return;
            }
            v8Array.close();
        } catch (Throwable th3) {
            v8Function2 = v8Function;
            th = th3;
            if (v8Function2 != null && !v8Function2.isReleased()) {
                v8Function2.close();
            }
            if (v8Array != null && !v8Array.isReleased()) {
                v8Array.close();
            }
            throw th;
        }
    }

    private void setupDebugObject(V8 v8) {
        V8Object object = v8.getObject(DEBUG_OBJECT_NAME);
        try {
            this.debugObject = object.getObject(V8_DEBUG_OBJECT);
        } finally {
            object.close();
        }
    }

    public void addBreakHandler(BreakHandler breakHandler) {
        this.runtime.getLocker().checkThread();
        this.breakHandlers.add(breakHandler);
    }

    public void changeBreakPointCondition(int i, String str) {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(i);
        v8Array.push(str);
        try {
            this.debugObject.executeVoidFunction(CHANGE_BREAK_POINT_CONDITION, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public void clearBreakPoint(int i) {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(i);
        try {
            this.debugObject.executeVoidFunction(CLEAR_BREAK_POINT, v8Array);
        } finally {
            v8Array.close();
        }
    }

    @Override // com.kwad.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.debugObject.close();
    }

    public void disableAllBreakPoints() {
        this.debugObject.executeVoidFunction(DISABLE_ALL_BREAK_POINTS, null);
    }

    public void disableScriptBreakPoint(int i) {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(i);
        try {
            this.debugObject.executeVoidFunction(DISABLE_SCRIPT_BREAK_POINT, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public void enableScriptBreakPoint(int i) {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(i);
        try {
            this.debugObject.executeVoidFunction(ENABLE_SCRIPT_BREAK_POINT, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public ScriptBreakPoint getScriptBreakPoint(int i) throws Throwable {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(i);
        v8Array.push(false);
        V8Object v8Object = null;
        try {
            V8Object v8ObjectExecuteObjectFunction = this.debugObject.executeObjectFunction(FIND_SCRIPT_BREAK_POINT, v8Array);
            try {
                ScriptBreakPoint scriptBreakPoint = new ScriptBreakPoint(v8ObjectExecuteObjectFunction);
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return scriptBreakPoint;
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

    public int getScriptBreakPointCount() {
        V8Array v8ArrayExecuteArrayFunction = this.debugObject.executeArrayFunction(SCRIPT_BREAK_POINTS, null);
        try {
            return v8ArrayExecuteArrayFunction.length();
        } finally {
            v8ArrayExecuteArrayFunction.close();
        }
    }

    public int[] getScriptBreakPointIDs() {
        V8Array v8ArrayExecuteArrayFunction = this.debugObject.executeArrayFunction(SCRIPT_BREAK_POINTS, null);
        try {
            int[] iArr = new int[v8ArrayExecuteArrayFunction.length()];
            for (int i = 0; i < v8ArrayExecuteArrayFunction.length(); i++) {
                V8Object object = v8ArrayExecuteArrayFunction.getObject(i);
                try {
                    iArr[i] = object.executeIntegerFunction(NUMBER, null);
                    object.close();
                } finally {
                }
            }
            return iArr;
        } finally {
            v8ArrayExecuteArrayFunction.close();
        }
    }

    @Override // com.kwad.v8.Releasable
    @Deprecated
    public void release() {
        close();
    }

    public void removeBreakHandler(BreakHandler breakHandler) {
        this.runtime.getLocker().checkThread();
        this.breakHandlers.remove(breakHandler);
    }

    public int setBreakpoint(V8Function v8Function) {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push((V8Value) v8Function);
        try {
            return this.debugObject.executeIntegerFunction(SET_BREAK_POINT, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public int setScriptBreakpoint(String str, int i) {
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(str);
        v8Array.push(i);
        try {
            return this.debugObject.executeIntegerFunction(SET_SCRIPT_BREAK_POINT_BY_NAME, v8Array);
        } finally {
            v8Array.close();
        }
    }
}
