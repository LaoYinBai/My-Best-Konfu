package com.kwad.v8.inspector;

import com.kwad.v8.V8;
import com.kwad.v8.V8Object;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8Inspector {
    private long inspectorPtr;
    private V8 runtime;
    private boolean waitingForConnection = true;
    private List<DebuggerConnectionListener> debuggerConnectionListeners = new ArrayList();

    protected V8Inspector(V8 v8, V8InspectorDelegate v8InspectorDelegate, String str) {
        this.inspectorPtr = 0L;
        this.runtime = v8;
        this.inspectorPtr = v8.createInspector(v8InspectorDelegate, str);
    }

    public static V8Inspector createV8Inspector(V8 v8, V8InspectorDelegate v8InspectorDelegate) {
        return new V8Inspector(v8, v8InspectorDelegate, null);
    }

    public static V8Inspector createV8Inspector(V8 v8, V8InspectorDelegate v8InspectorDelegate, String str) {
        return new V8Inspector(v8, v8InspectorDelegate, str);
    }

    private void verifyDebuggerConnection(String str) throws Throwable {
        Throwable th;
        V8Object v8ObjectExecuteObjectScript;
        try {
            v8ObjectExecuteObjectScript = this.runtime.executeObjectScript("JSON.parse(JSON.stringify(" + str + "))");
            try {
                if (v8ObjectExecuteObjectScript.getString("method").equals("Runtime.runIfWaitingForDebugger")) {
                    this.waitingForConnection = false;
                    this.runtime.schedulePauseOnNextStatement(this.inspectorPtr, "");
                    Iterator<DebuggerConnectionListener> it = this.debuggerConnectionListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onDebuggerConnected();
                    }
                }
                if (v8ObjectExecuteObjectScript != null) {
                    v8ObjectExecuteObjectScript.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (v8ObjectExecuteObjectScript != null) {
                    v8ObjectExecuteObjectScript.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            v8ObjectExecuteObjectScript = null;
        }
    }

    public void addDebuggerConnectionListener(DebuggerConnectionListener debuggerConnectionListener) {
        this.debuggerConnectionListeners.add(debuggerConnectionListener);
    }

    public void dispatchProtocolMessage(String str) throws Throwable {
        try {
            this.runtime.dispatchProtocolMessage(this.inspectorPtr, str);
            if (this.waitingForConnection) {
                verifyDebuggerConnection(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeDebuggerConnectionListener(DebuggerConnectionListener debuggerConnectionListener) {
        this.debuggerConnectionListeners.remove(debuggerConnectionListener);
    }
}
