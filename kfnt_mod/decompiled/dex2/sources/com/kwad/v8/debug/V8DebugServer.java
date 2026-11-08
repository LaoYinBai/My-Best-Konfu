package com.kwad.v8.debug;

import com.kwad.v8.JavaVoidCallback;
import com.kwad.v8.Platform;
import com.kwad.v8.Releasable;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8DebugServer {
    private static final String DEBUG_BREAK_HANDLER = "__j2v8_debug_handler";
    public static String DEBUG_OBJECT_NAME = "__j2v8_Debug";
    private static final String HEADER_EMBEDDING_HOST = "Embedding-Host: ";
    private static final String HEADER_PROTOCOL_VERSION = "Protocol-Version: ";
    private static final String HEADER_TYPE = "Type: ";
    private static final String HEADER_V8_VERSION = "V8-Version: ";
    private static final String J2V8_VERSION = "4.0.0";
    private static final String MAKE_BREAK_EVENT = "__j2v8_MakeBreakEvent";
    private static final String MAKE_COMPILE_EVENT = "__j2v8_MakeCompileEvent";
    private static final int PROTOCOL_BUFFER_SIZE = 4096;
    private static final String PROTOCOL_VERSION = "1";
    private static final String SET_LISTENER = "setListener";
    private static final String V8_DEBUG_OBJECT = "Debug";
    private static final String V8_VERSION = "4.10.253";
    private Socket client;
    private V8Object debugObject;
    private V8Object runningStateDcp;
    private V8 runtime;
    private ServerSocket server;
    private V8Object stoppedStateDcp;
    private boolean waitForConnection;
    private static final Charset PROTOCOL_CHARSET = Charset.forName(C.UTF8_NAME);
    private static final String PROTOCOL_EOL = "\r\n";
    private static final byte[] PROTOCOL_EOL_BYTES = PROTOCOL_EOL.getBytes(PROTOCOL_CHARSET);
    private static final String PROTOCOL_CONTENT_LENGTH_HEADER = "Content-Length:";
    private static final byte[] PROTOCOL_CONTENT_LENGTH_BYTES = PROTOCOL_CONTENT_LENGTH_HEADER.getBytes(PROTOCOL_CHARSET);
    private Object clientLock = new Object();
    private boolean traceCommunication = false;
    private List<String> requests = new LinkedList();

    private class ClientLoop implements Runnable {
        private int from;

        private ClientLoop() {
        }

        private int indexOf(byte[] bArr, byte[] bArr2, int i, int i2) {
            int length = bArr.length;
            while (i < i2) {
                for (int i3 = 0; i3 <= length; i3++) {
                    if (i3 == length) {
                        return i;
                    }
                    int i4 = i + i3;
                    if (i4 >= i2 || bArr2[i4] != bArr[i3]) {
                        break;
                    }
                }
                i++;
            }
            return -1;
        }

        private byte[] join(byte[] bArr, byte[] bArr2, int i, int i2) {
            byte[] bArr3 = new byte[bArr.length + i2];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, i, bArr3, bArr.length, i2);
            return bArr3;
        }

        private void processClientRequests() throws IOException, NumberFormatException {
            InputStream inputStream;
            int contentLength;
            byte[] bArr = new byte[0];
            byte[] bArr2 = new byte[4096];
            synchronized (V8DebugServer.this.clientLock) {
                inputStream = V8DebugServer.this.client.getInputStream();
            }
            byte[] bArrJoin = bArr;
            int i = 0;
            boolean zSkipToolInfo = false;
            int i2 = -1;
            while (true) {
                int i3 = inputStream.read(bArr2, i, 4096 - i);
                if (i3 <= 0) {
                    return;
                }
                int i4 = i3 + i;
                this.from = 0;
                do {
                    if (i2 < 0) {
                        contentLength = readContentLength(bArr2, i4);
                        if (contentLength < 0) {
                        }
                        i2 = contentLength;
                        break;
                    }
                    contentLength = i2;
                    if (!zSkipToolInfo && !(zSkipToolInfo = skipToolInfo(bArr2, i4))) {
                        i2 = contentLength;
                        break;
                    }
                    int iMin = Math.min(contentLength - bArrJoin.length, i4 - this.from);
                    bArrJoin = join(bArrJoin, bArr2, this.from, iMin);
                    this.from += iMin;
                    if (bArrJoin.length == contentLength) {
                        String str = new String(bArrJoin, V8DebugServer.PROTOCOL_CHARSET);
                        synchronized (V8DebugServer.this.requests) {
                            V8DebugServer.this.requests.add(str);
                        }
                        bArrJoin = bArr;
                        zSkipToolInfo = false;
                        i2 = -1;
                    } else {
                        i2 = contentLength;
                    }
                } while (this.from < i4);
                if (this.from < i4) {
                    System.arraycopy(bArr2, this.from, bArr2, 0, i4 - this.from);
                    i = i4 - this.from;
                } else {
                    i = 0;
                }
            }
        }

        private int readContentLength(byte[] bArr, int i) throws NumberFormatException, IOException {
            int length;
            int iIndexOf;
            int iIndexOf2 = indexOf(V8DebugServer.PROTOCOL_CONTENT_LENGTH_BYTES, bArr, this.from, i);
            if (iIndexOf2 < 0 || (iIndexOf = indexOf(V8DebugServer.PROTOCOL_EOL_BYTES, bArr, (length = iIndexOf2 + V8DebugServer.PROTOCOL_CONTENT_LENGTH_BYTES.length), i)) < 0) {
                return -1;
            }
            String str = new String(bArr, length, iIndexOf - length, V8DebugServer.PROTOCOL_CHARSET);
            try {
                int i2 = Integer.parseInt(str.trim());
                this.from = iIndexOf + V8DebugServer.PROTOCOL_EOL_BYTES.length;
                return i2;
            } catch (Exception unused) {
                throw new IOException("Invalid content length header: '" + str + "' in message" + new String(bArr, V8DebugServer.PROTOCOL_CHARSET));
            }
        }

        private boolean skipToolInfo(byte[] bArr, int i) {
            int iIndexOf = indexOf(V8DebugServer.PROTOCOL_EOL_BYTES, bArr, this.from, i);
            if (iIndexOf < 0) {
                return false;
            }
            this.from = iIndexOf + V8DebugServer.PROTOCOL_EOL_BYTES.length;
            return true;
        }

        private void startHandshake() {
            V8DebugServer.this.sendMessage(V8DebugServer.HEADER_V8_VERSION + V8DebugServer.V8_VERSION + V8DebugServer.PROTOCOL_EOL + V8DebugServer.HEADER_PROTOCOL_VERSION + "1" + V8DebugServer.PROTOCOL_EOL + V8DebugServer.HEADER_EMBEDDING_HOST + "j2v8 " + V8DebugServer.J2V8_VERSION + V8DebugServer.PROTOCOL_EOL + V8DebugServer.HEADER_TYPE + "connect" + V8DebugServer.PROTOCOL_EOL, "");
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            while (true) {
                try {
                    Socket socketAccept = V8DebugServer.this.server.accept();
                    socketAccept.setTcpNoDelay(true);
                    synchronized (V8DebugServer.this.clientLock) {
                        V8DebugServer.this.client = socketAccept;
                        V8DebugServer.this.waitForConnection = false;
                        V8DebugServer.this.clientLock.notifyAll();
                    }
                    startHandshake();
                    processClientRequests();
                } catch (Exception e) {
                    synchronized (V8DebugServer.this.clientLock) {
                        if (V8DebugServer.this.client != null) {
                            try {
                                V8DebugServer.this.client.close();
                            } catch (IOException unused) {
                            }
                            V8DebugServer.this.client = null;
                        }
                        V8DebugServer.this.logError(e);
                    }
                }
            }
        }
    }

    private class EventHandler implements JavaVoidCallback {
        private EventHandler() {
        }

        private void safeRelease(Releasable releasable) {
            if (releasable != null) {
                releasable.release();
            }
        }

        @Override // com.kwad.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) throws Throwable {
            V8Object object;
            V8Object object2;
            int integer;
            if (v8Array == null || v8Array.isUndefined()) {
                return;
            }
            V8Object v8Object2 = null;
            try {
                integer = v8Array.getInteger(0);
                object2 = v8Array.getObject(1);
                try {
                    object = v8Array.getObject(2);
                } catch (Exception e) {
                    e = e;
                    object = null;
                } catch (Throwable th) {
                    th = th;
                    object = null;
                }
            } catch (Exception e2) {
                e = e2;
                object = null;
            } catch (Throwable th2) {
                th = th2;
                object = null;
                object2 = null;
            }
            try {
                if (V8DebugServer.this.traceCommunication) {
                    String str = Platform.UNKNOWN;
                    switch (integer) {
                        case 1:
                            str = "Break";
                            break;
                        case 2:
                            str = "Exception";
                            break;
                        case 3:
                            str = "NewFunction";
                            break;
                        case 4:
                            str = "BeforeCompile";
                            break;
                        case 5:
                            str = "AfterCompile";
                            break;
                        case 6:
                            str = "CompileError";
                            break;
                        case 7:
                            str = "PromiseEvent";
                            break;
                        case 8:
                            str = "AsyncTaskEvent";
                            break;
                    }
                    System.out.println("V8 has emmitted an event of type " + str);
                }
            } catch (Exception e3) {
                e = e3;
                v8Object2 = object2;
                try {
                    V8DebugServer.this.logError(e);
                    safeRelease(v8Object2);
                    safeRelease(object);
                } catch (Throwable th3) {
                    th = th3;
                    object2 = v8Object2;
                    safeRelease(object2);
                    safeRelease(object);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                safeRelease(object2);
                safeRelease(object);
                throw th;
            }
            if (!V8DebugServer.this.isConnected()) {
                safeRelease(object2);
                safeRelease(object);
                return;
            }
            if (integer != 1) {
                switch (integer) {
                    case 5:
                    case 6:
                        V8DebugServer.this.sendCompileEvent(object);
                        break;
                }
            } else {
                V8DebugServer.this.enterBreakLoop(object2, object);
            }
            safeRelease(object2);
            safeRelease(object);
        }
    }

    public V8DebugServer(V8 v8, int i, boolean z) {
        this.runtime = v8;
        this.waitForConnection = z;
        V8Object object = v8.getObject(DEBUG_OBJECT_NAME);
        if (object == null) {
            System.err.println("Cannot initialize debugger server - global debug object not found.");
            return;
        }
        try {
            this.debugObject = object.getObject(V8_DEBUG_OBJECT);
            object.close();
            v8.executeVoidScript("(function() {\n " + DEBUG_OBJECT_NAME + ".Debug. " + MAKE_BREAK_EVENT + " = function (break_id,breakpoints_hit) {\n  return new " + DEBUG_OBJECT_NAME + ".BreakEvent(break_id,breakpoints_hit);\n }\n " + DEBUG_OBJECT_NAME + ".Debug. " + MAKE_COMPILE_EVENT + " = function(script,type) {\n  var scripts = " + DEBUG_OBJECT_NAME + ".Debug.scripts()\n  for (var i in scripts) {\n   if (scripts[i].id == script.id()) {\n     return new " + DEBUG_OBJECT_NAME + ".CompileEvent(scripts[i], type);\n   }\n  }\n  return {toJSONProtocol: function() {return ''}}\n }\n})()");
            try {
                this.server = new ServerSocket(i);
            } catch (Exception e) {
                logError(e);
            }
        } catch (Throwable th) {
            object.close();
            throw th;
        }
    }

    public static void configureV8ForDebugging() {
        try {
            V8.setFlags("-expose-debug-as=" + DEBUG_OBJECT_NAME);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterBreakLoop(V8Object v8Object, V8Object v8Object2) {
        V8Object v8ObjectExecuteObjectFunction;
        try {
            V8Array v8Array = new V8Array(this.runtime);
            try {
                v8Array.push(false);
                this.stoppedStateDcp = v8Object.executeObjectFunction("debugCommandProcessor", v8Array);
                v8Array.close();
                int integer = v8Object.getInteger("break_id");
                V8Array array = v8Object2.getArray("break_points_hit_");
                V8Array v8Array2 = new V8Array(this.runtime);
                try {
                    v8Array2.push(integer);
                    v8Array2.push((V8Value) array);
                    v8ObjectExecuteObjectFunction = this.debugObject.executeObjectFunction(MAKE_BREAK_EVENT, v8Array2);
                    try {
                        String strExecuteStringFunction = v8ObjectExecuteObjectFunction.executeStringFunction("toJSONProtocol", null);
                        if (this.traceCommunication) {
                            System.out.println("Sending event (Break):\n" + strExecuteStringFunction);
                        }
                        sendJson(strExecuteStringFunction);
                        v8Array2.close();
                        array.close();
                        if (v8ObjectExecuteObjectFunction != null) {
                            v8ObjectExecuteObjectFunction.close();
                        }
                        while (isConnected() && !this.stoppedStateDcp.executeBooleanFunction("isRunning", null)) {
                            try {
                                processRequests(10L);
                            } catch (InterruptedException unused) {
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        v8Array2.close();
                        array.close();
                        if (v8ObjectExecuteObjectFunction != null) {
                            v8ObjectExecuteObjectFunction.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    v8ObjectExecuteObjectFunction = null;
                }
            } catch (Throwable th3) {
                v8Array.close();
                throw th3;
            }
        } finally {
            this.stoppedStateDcp.close();
            this.stoppedStateDcp = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnected() {
        boolean z;
        synchronized (this.clientLock) {
            z = (this.server == null || this.client == null || !this.client.isConnected()) ? false : true;
        }
        return z;
    }

    private void processRequest(String str) {
        if (this.traceCommunication) {
            System.out.println("Got message: \n" + str.substring(0, Math.min(str.length(), 1000)));
        }
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(str);
        V8Object v8Object = this.stoppedStateDcp != null ? this.stoppedStateDcp : this.runningStateDcp;
        String string = v8Object.executeFunction("processDebugJSONRequest", v8Array).toString();
        if (this.stoppedStateDcp == null && string.contains("\"running\":false")) {
            string = string.replace("\"running\":false", "\"running\":true").replace("\"success\":true", "\"success\":false").replace("{\"", "{\"message\":\"Client requested suspension is not supported on J2V8.\",\"");
            v8Object.add("running_", true);
        }
        if (this.traceCommunication) {
            System.out.println("Returning response: \n" + string.substring(0, Math.min(string.length(), 1000)));
        }
        sendJson(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCompileEvent(V8Object v8Object) throws Throwable {
        Throwable th;
        V8Object v8ObjectExecuteObjectFunction;
        if (!isConnected()) {
            return;
        }
        int integer = v8Object.getInteger("type_");
        V8Object object = v8Object.getObject("script_");
        V8Array v8Array = new V8Array(this.runtime);
        try {
            v8Array.push((V8Value) object);
            v8Array.push(integer);
            v8ObjectExecuteObjectFunction = this.debugObject.executeObjectFunction(MAKE_COMPILE_EVENT, v8Array);
            try {
                String strExecuteStringFunction = v8ObjectExecuteObjectFunction.executeStringFunction("toJSONProtocol", null);
                if (this.traceCommunication) {
                    System.out.println("Sending event (CompileEvent):\n" + strExecuteStringFunction.substring(0, Math.min(strExecuteStringFunction.length(), 1000)));
                }
                if (strExecuteStringFunction.length() > 0) {
                    sendJson(strExecuteStringFunction);
                }
                v8Array.close();
                object.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
            } catch (Throwable th2) {
                th = th2;
                v8Array.close();
                object.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            v8ObjectExecuteObjectFunction = null;
        }
    }

    private void sendJson(String str) {
        sendMessage("", str.replace("\\/", "/"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(String str, String str2) {
        synchronized (this.clientLock) {
            if (!isConnected()) {
                throw new IOException("There is no connected client.");
            }
            byte[] bytes = str2.getBytes(PROTOCOL_CHARSET);
            this.client.getOutputStream().write((str + PROTOCOL_CONTENT_LENGTH_HEADER + Integer.toString(bytes.length) + PROTOCOL_EOL + PROTOCOL_EOL).getBytes(PROTOCOL_CHARSET));
            if (bytes.length > 0) {
                this.client.getOutputStream().write(bytes);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupEventHandler() throws Throwable {
        V8Array v8Array;
        V8Function v8Function;
        V8Function v8Function2 = null;
        this.debugObject.registerJavaMethod(new EventHandler(), DEBUG_BREAK_HANDLER);
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

    public int getPort() {
        if (this.server == null || !this.server.isBound()) {
            return -1;
        }
        return this.server.getLocalPort();
    }

    protected void logError(Throwable th) {
        th.printStackTrace();
    }

    public void processRequests(long j) throws InterruptedException {
        String[] strArr;
        if (this.server == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (true) {
            synchronized (this.requests) {
                strArr = (String[]) this.requests.toArray(new String[this.requests.size()]);
                this.requests.clear();
            }
            for (String str : strArr) {
                try {
                    processRequest(str);
                } catch (Exception e) {
                    logError(e);
                }
            }
            if (strArr.length <= 0) {
                if (j > 0) {
                    Thread.sleep(10L);
                }
                if (j <= 0 || jCurrentTimeMillis + j <= System.currentTimeMillis()) {
                    return;
                }
            }
        }
    }

    public void setTraceCommunication(boolean z) {
        this.traceCommunication = z;
    }

    public void start() throws Throwable {
        if (this.server == null) {
            return;
        }
        boolean z = this.waitForConnection;
        Thread thread = new Thread(new ClientLoop(), "J2V8 Debugger Server");
        thread.setDaemon(true);
        thread.start();
        setupEventHandler();
        this.runningStateDcp = this.runtime.executeObjectScript("(function() {return new " + DEBUG_OBJECT_NAME + ".DebugCommandProcessor(null, true)})()");
        if (z) {
            synchronized (this.clientLock) {
                while (this.waitForConnection) {
                    try {
                        this.clientLock.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            try {
                processRequests(100L);
            } catch (InterruptedException unused2) {
            }
        }
    }

    public void stop() throws IOException {
        try {
            this.server.close();
            synchronized (this.clientLock) {
                if (this.client != null) {
                    this.client.close();
                    this.client = null;
                }
            }
        } catch (IOException e) {
            logError(e);
        }
        if (this.runningStateDcp != null) {
            this.runningStateDcp.close();
            this.runningStateDcp = null;
        }
        if (this.debugObject != null) {
            this.debugObject.close();
            this.debugObject = null;
        }
        if (this.stoppedStateDcp != null) {
            this.stoppedStateDcp.close();
            this.stoppedStateDcp = null;
        }
    }
}
