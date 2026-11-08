package com.kwad.v8;

import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.inspector.V8InspectorDelegate;
import com.kwad.v8.utils.V8Executor;
import com.kwad.v8.utils.V8Map;
import com.kwad.v8.utils.V8Runnable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8 extends V8Object {
    private static V8Object sV8Receiver;
    private Map<String, Object> data;
    private V8Map<V8Executor> executors;
    private boolean forceTerminateExecutors;
    private Map<Long, MethodDescriptor> functionRegistry;
    private final V8Locker locker;
    public long methodId;
    private long objectReferences;
    private LinkedList<ReferenceHandler> referenceHandlers;
    private LinkedList<V8Runnable> releaseHandlers;
    private List<Releasable> resources;
    private SignatureProvider signatureProvider;
    private long v8RuntimePtr;
    protected Map<Long, V8Value> v8WeakReferences;
    private static Object lock = new Object();
    private static volatile int runtimeCounter = 0;
    private static String v8Flags = null;
    private static boolean initialized = false;
    private static boolean nativeLibraryLoaded = false;
    private static Error nativeLoadError = null;
    private static Exception nativeLoadException = null;
    private static V8Value undefined = new V8Object.Undefined();
    private static Object invalid = new Object();

    private class MethodDescriptor {
        JavaCallback callback;
        boolean includeReceiver;
        Method method;
        Object object;
        JavaVoidCallback voidCallback;

        private MethodDescriptor() {
        }
    }

    protected V8() {
        this(null);
    }

    protected V8(String str) {
        super(null);
        this.v8WeakReferences = new HashMap();
        this.data = null;
        this.signatureProvider = null;
        this.objectReferences = 0L;
        this.v8RuntimePtr = 0L;
        this.resources = null;
        this.executors = null;
        this.forceTerminateExecutors = false;
        this.functionRegistry = new HashMap();
        this.referenceHandlers = new LinkedList<>();
        this.releaseHandlers = new LinkedList<>();
        this.released = false;
        this.v8RuntimePtr = _createIsolate(str);
        this.locker = new V8Locker(this);
        checkThread();
        this.objectHandle = _getGlobalObject(this.v8RuntimePtr);
    }

    private native void _acquireLock(long j);

    private native void _add(long j, long j2, String str, double d);

    private native void _add(long j, long j2, String str, int i);

    private native void _add(long j, long j2, String str, String str2);

    private native void _add(long j, long j2, String str, boolean z);

    private native void _addArrayBooleanItem(long j, long j2, boolean z);

    private native void _addArrayDoubleItem(long j, long j2, double d);

    private native void _addArrayIntItem(long j, long j2, int i);

    private native void _addArrayNullItem(long j, long j2);

    private native void _addArrayObjectItem(long j, long j2, long j3);

    private native void _addArrayStringItem(long j, long j2, String str);

    private native void _addArrayUndefinedItem(long j, long j2);

    private native void _addNull(long j, long j2, String str);

    private native void _addObject(long j, long j2, String str, long j3);

    private native void _addUndefined(long j, long j2, String str);

    private native Object _arrayGet(long j, int i, long j2, int i2);

    private native boolean _arrayGetBoolean(long j, long j2, int i);

    private native int _arrayGetBooleans(long j, long j2, int i, int i2, boolean[] zArr);

    private native boolean[] _arrayGetBooleans(long j, long j2, int i, int i2);

    private native byte _arrayGetByte(long j, long j2, int i);

    private native int _arrayGetBytes(long j, long j2, int i, int i2, byte[] bArr);

    private native byte[] _arrayGetBytes(long j, long j2, int i, int i2);

    private native double _arrayGetDouble(long j, long j2, int i);

    private native int _arrayGetDoubles(long j, long j2, int i, int i2, double[] dArr);

    private native double[] _arrayGetDoubles(long j, long j2, int i, int i2);

    private native int _arrayGetInteger(long j, long j2, int i);

    private native int _arrayGetIntegers(long j, long j2, int i, int i2, int[] iArr);

    private native int[] _arrayGetIntegers(long j, long j2, int i, int i2);

    private native int _arrayGetSize(long j, long j2);

    private native String _arrayGetString(long j, long j2, int i);

    private native int _arrayGetStrings(long j, long j2, int i, int i2, String[] strArr);

    private native String[] _arrayGetStrings(long j, long j2, int i, int i2);

    private native void _clearWeak(long j, long j2);

    private native boolean _contains(long j, long j2, String str);

    private native long _createInspector(long j, V8InspectorDelegate v8InspectorDelegate, String str);

    private native long _createIsolate(String str);

    private native void _createTwin(long j, long j2, long j3);

    private native ByteBuffer _createV8ArrayBufferBackingStore(long j, long j2, int i);

    private native void _dispatchProtocolMessage(long j, long j2, String str);

    private native boolean _equals(long j, long j2, long j3);

    private native boolean _executeBooleanFunction(long j, long j2, String str, long j3);

    private native boolean _executeBooleanScript(long j, String str, String str2, int i);

    private native double _executeDoubleFunction(long j, long j2, String str, long j3);

    private native double _executeDoubleScript(long j, String str, String str2, int i);

    private native Object _executeFunction(long j, int i, long j2, String str, long j3);

    private native Object _executeFunction(long j, long j2, long j3, long j4);

    private native int _executeIntegerFunction(long j, long j2, String str, long j3);

    private native int _executeIntegerScript(long j, String str, String str2, int i);

    private native Object _executeScript(long j, int i, String str, String str2, int i2);

    private native String _executeStringFunction(long j, long j2, String str, long j3);

    private native String _executeStringScript(long j, String str, String str2, int i);

    private native void _executeVoidFunction(long j, long j2, String str, long j3);

    private native void _executeVoidScript(long j, String str, String str2, int i);

    private native Object _get(long j, int i, long j2, String str);

    private native int _getArrayType(long j, long j2);

    private native boolean _getBoolean(long j, long j2, String str);

    private static native long _getBuildID();

    private native String _getConstructorName(long j, long j2);

    private native double _getDouble(long j, long j2, String str);

    private native long _getGlobalObject(long j);

    private native int _getInteger(long j, long j2, String str);

    private native String[] _getKeys(long j, long j2);

    private native String _getString(long j, long j2, String str);

    private native int _getType(long j, long j2);

    private native int _getType(long j, long j2, int i);

    private native int _getType(long j, long j2, int i, int i2);

    private native int _getType(long j, long j2, String str);

    private static native String _getVersion();

    private native int _identityHash(long j, long j2);

    private native long _initEmptyContainer(long j);

    private native long _initNewV8Array(long j);

    private native long _initNewV8ArrayBuffer(long j, int i);

    private native long _initNewV8ArrayBuffer(long j, ByteBuffer byteBuffer, int i);

    private native long _initNewV8Float32Array(long j, long j2, int i, int i2);

    private native long _initNewV8Float64Array(long j, long j2, int i, int i2);

    private native long[] _initNewV8Function(long j);

    private native long _initNewV8Int16Array(long j, long j2, int i, int i2);

    private native long _initNewV8Int32Array(long j, long j2, int i, int i2);

    private native long _initNewV8Int8Array(long j, long j2, int i, int i2);

    private native long _initNewV8Object(long j);

    private native long _initNewV8UInt16Array(long j, long j2, int i, int i2);

    private native long _initNewV8UInt32Array(long j, long j2, int i, int i2);

    private native long _initNewV8UInt8Array(long j, long j2, int i, int i2);

    private native long _initNewV8UInt8ClampedArray(long j, long j2, int i, int i2);

    private static native boolean _isNodeCompatible();

    private static native boolean _isRunning(long j);

    private native boolean _isWeak(long j, long j2);

    private native void _lowMemoryNotification(long j);

    private static native boolean _pumpMessageLoop(long j);

    private native long _registerJavaMethod(long j, long j2, String str, boolean z);

    private native void _release(long j, long j2);

    private native void _releaseLock(long j);

    private native void _releaseMethodDescriptor(long j, long j2);

    private native void _releaseRuntime(long j);

    private native boolean _sameValue(long j, long j2, long j3);

    private native void _schedulePauseOnNextStatement(long j, long j2, String str);

    private static native void _setFlags(String str);

    private native void _setPrototype(long j, long j2, long j3);

    private native void _setWeak(long j, long j2);

    private static native void _startNodeJS(long j, String str);

    private native boolean _strictEquals(long j, long j2, long j3);

    private native void _terminateExecution(long j);

    private native String _toString(long j, long j2);

    private void checkArgs(Object[] objArr) {
        for (Object obj : objArr) {
            if (obj == invalid) {
                throw new IllegalArgumentException("argument type mismatch");
            }
        }
    }

    private static void checkNativeLibraryLoaded() {
        if (nativeLibraryLoaded) {
            return;
        }
        String strComputeLibraryShortName = LibraryLoader.computeLibraryShortName(true);
        String str = "J2V8 native library not loaded (" + LibraryLoader.computeLibraryShortName(false) + "/" + strComputeLibraryShortName + ")";
        if (nativeLoadError != null) {
            throw new IllegalStateException(str, nativeLoadError);
        }
        if (nativeLoadException == null) {
            throw new IllegalStateException(str);
        }
        throw new IllegalStateException(str, nativeLoadException);
    }

    private Object checkResult(Object obj) {
        if (obj == null) {
            return obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if ((obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        }
        if (obj instanceof V8Value) {
            if (((V8Value) obj).isReleased()) {
                throw new V8RuntimeException("V8Value already released");
            }
            return obj;
        }
        throw new V8RuntimeException("Unknown return type: " + obj.getClass());
    }

    static void checkScript(String str) {
        if (str == null) {
            throw new NullPointerException("Script is null");
        }
    }

    public static V8 createV8Runtime() {
        return createV8Runtime(null, null);
    }

    public static V8 createV8Runtime(String str) {
        return createV8Runtime(str, null);
    }

    public static V8 createV8Runtime(String str, String str2) {
        if (!nativeLibraryLoaded) {
            synchronized (lock) {
                if (!nativeLibraryLoaded) {
                    load(str2);
                }
            }
        }
        checkNativeLibraryLoaded();
        if (!initialized) {
            _setFlags(v8Flags);
            initialized = true;
        }
        V8 v8 = new V8(str);
        synchronized (lock) {
            runtimeCounter++;
        }
        return v8;
    }

    public static int getActiveRuntimes() {
        return runtimeCounter;
    }

    private Object[] getArgs(V8Object v8Object, MethodDescriptor methodDescriptor, V8Array v8Array, boolean z) {
        int length = methodDescriptor.method.getParameterTypes().length;
        int i = z ? length - 1 : length;
        Object[] defaultValues = setDefaultValues(new Object[length], methodDescriptor.method.getParameterTypes(), v8Object, methodDescriptor.includeReceiver);
        ArrayList arrayList = new ArrayList();
        populateParamters(v8Array, i, defaultValues, arrayList, methodDescriptor.includeReceiver);
        if (z) {
            Object varArgContainer = getVarArgContainer(methodDescriptor.method.getParameterTypes(), arrayList.size());
            System.arraycopy(arrayList.toArray(), 0, varArgContainer, 0, arrayList.size());
            defaultValues[i] = varArgContainer;
        }
        return defaultValues;
    }

    private Object getArrayItem(V8Array v8Array, int i) {
        try {
            int type = v8Array.getType(i);
            if (type == 10) {
                return v8Array.get(i);
            }
            if (type == 99) {
                return getUndefined();
            }
            switch (type) {
                case 1:
                    return Integer.valueOf(v8Array.getInteger(i));
                case 2:
                    return Double.valueOf(v8Array.getDouble(i));
                case 3:
                    return Boolean.valueOf(v8Array.getBoolean(i));
                case 4:
                    return v8Array.getString(i);
                case 5:
                case 8:
                    return v8Array.getArray(i);
                case 6:
                    return v8Array.getObject(i);
                case 7:
                    return v8Array.getObject(i);
                default:
                    return null;
            }
        } catch (V8ResultUndefined unused) {
            return null;
        }
    }

    public static long getBuildID() {
        return _getBuildID();
    }

    private Object getDefaultValue(Class<?> cls) {
        return cls.equals(V8Object.class) ? new V8Object.Undefined() : cls.equals(V8Array.class) ? new V8Array.Undefined() : invalid;
    }

    public static String getSCMRevision() {
        return "Unknown revision ID";
    }

    public static V8Value getUndefined() {
        return undefined;
    }

    public static V8Object getV8Receiver() {
        return sV8Receiver;
    }

    public static String getV8Version() {
        return _getVersion();
    }

    private Object getVarArgContainer(Class<?>[] clsArr, int i) {
        Class<?> componentType = clsArr[clsArr.length - 1];
        if (componentType.isArray()) {
            componentType = componentType.getComponentType();
        }
        return Array.newInstance(componentType, i);
    }

    public static boolean isLoaded() {
        return nativeLibraryLoaded;
    }

    public static boolean isNodeCompatible() {
        if (!nativeLibraryLoaded) {
            synchronized (lock) {
                if (!nativeLibraryLoaded) {
                    load(null);
                }
            }
        }
        return _isNodeCompatible();
    }

    private boolean isVoidMethod(Method method) {
        return method.getReturnType().equals(Void.TYPE);
    }

    private static synchronized void load(String str) {
        try {
            LibraryLoader.loadLibrary(str);
            nativeLibraryLoaded = true;
        } catch (Error e) {
            nativeLoadError = e;
        } catch (Exception e2) {
            nativeLoadException = e2;
        }
    }

    private void notifyReferenceCreated(V8Value v8Value) {
        Iterator<ReferenceHandler> it = this.referenceHandlers.iterator();
        while (it.hasNext()) {
            it.next().v8HandleCreated(v8Value);
        }
    }

    private void notifyReferenceDisposed(V8Value v8Value) {
        Iterator<ReferenceHandler> it = this.referenceHandlers.iterator();
        while (it.hasNext()) {
            it.next().v8HandleDisposed(v8Value);
        }
    }

    private void notifyReleaseHandlers(V8 v8) {
        Iterator<V8Runnable> it = this.releaseHandlers.iterator();
        while (it.hasNext()) {
            it.next().run(v8);
        }
    }

    private void populateParamters(V8Array v8Array, int i, Object[] objArr, List<Object> list, boolean z) {
        for (int i2 = z ? 1 : 0; i2 < v8Array.length() + (z ? 1 : 0); i2++) {
            if (i2 >= i) {
                list.add(getArrayItem(v8Array, i2 - (z ? 1 : 0)));
            } else {
                objArr[i2] = getArrayItem(v8Array, i2 - (z ? 1 : 0));
            }
        }
    }

    private void releaseArguments(Object[] objArr, boolean z) {
        if (z && objArr.length > 0 && (objArr[objArr.length - 1] instanceof Object[])) {
            for (Object obj : (Object[]) objArr[objArr.length - 1]) {
                if (obj instanceof V8Value) {
                    ((V8Value) obj).close();
                }
            }
        }
        for (Object obj2 : objArr) {
            if (obj2 instanceof V8Value) {
                ((V8Value) obj2).close();
            }
        }
    }

    private void releaseNativeMethodDescriptors() {
        Iterator<Long> it = this.functionRegistry.keySet().iterator();
        while (it.hasNext()) {
            releaseMethodDescriptor(this.v8RuntimePtr, it.next().longValue());
        }
    }

    private void releaseResources() {
        if (this.resources != null) {
            Iterator<Releasable> it = this.resources.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.resources.clear();
            this.resources = null;
        }
    }

    private Object[] setDefaultValues(Object[] objArr, Class<?>[] clsArr, V8Object v8Object, boolean z) {
        int i = 0;
        if (z) {
            objArr[0] = v8Object;
            i = 1;
        }
        while (i < objArr.length) {
            objArr[i] = getDefaultValue(clsArr[i]);
            i++;
        }
        return objArr;
    }

    public static void setFlags(String str) {
        v8Flags = str;
        initialized = false;
    }

    protected void acquireLock(long j) {
        _acquireLock(j);
    }

    protected void add(long j, long j2, String str, double d) {
        _add(j, j2, str, d);
    }

    protected void add(long j, long j2, String str, int i) {
        _add(j, j2, str, i);
    }

    protected void add(long j, long j2, String str, String str2) {
        _add(j, j2, str, str2);
    }

    protected void add(long j, long j2, String str, boolean z) {
        _add(j, j2, str, z);
    }

    protected void addArrayBooleanItem(long j, long j2, boolean z) {
        _addArrayBooleanItem(j, j2, z);
    }

    protected void addArrayDoubleItem(long j, long j2, double d) {
        _addArrayDoubleItem(j, j2, d);
    }

    protected void addArrayIntItem(long j, long j2, int i) {
        _addArrayIntItem(j, j2, i);
    }

    protected void addArrayNullItem(long j, long j2) {
        _addArrayNullItem(j, j2);
    }

    protected void addArrayObjectItem(long j, long j2, long j3) {
        _addArrayObjectItem(j, j2, j3);
    }

    protected void addArrayStringItem(long j, long j2, String str) {
        _addArrayStringItem(j, j2, str);
    }

    protected void addArrayUndefinedItem(long j, long j2) {
        _addArrayUndefinedItem(j, j2);
    }

    protected void addNull(long j, long j2, String str) {
        _addNull(j, j2, str);
    }

    void addObjRef(V8Value v8Value) {
        this.objectReferences++;
        if (this.referenceHandlers.isEmpty()) {
            return;
        }
        notifyReferenceCreated(v8Value);
    }

    protected void addObject(long j, long j2, String str, long j3) {
        _addObject(j, j2, str, j3);
    }

    public void addReferenceHandler(ReferenceHandler referenceHandler) {
        this.referenceHandlers.add(0, referenceHandler);
    }

    public void addReleaseHandler(V8Runnable v8Runnable) {
        this.releaseHandlers.add(v8Runnable);
    }

    protected void addUndefined(long j, long j2, String str) {
        _addUndefined(j, j2, str);
    }

    protected Object arrayGet(long j, int i, long j2, int i2) {
        return _arrayGet(j, i, j2, i2);
    }

    protected boolean arrayGetBoolean(long j, long j2, int i) {
        return _arrayGetBoolean(j, j2, i);
    }

    protected int arrayGetBooleans(long j, long j2, int i, int i2, boolean[] zArr) {
        return _arrayGetBooleans(j, j2, i, i2, zArr);
    }

    protected boolean[] arrayGetBooleans(long j, long j2, int i, int i2) {
        return _arrayGetBooleans(j, j2, i, i2);
    }

    protected byte arrayGetByte(long j, long j2, int i) {
        return _arrayGetByte(j, j2, i);
    }

    protected int arrayGetBytes(long j, long j2, int i, int i2, byte[] bArr) {
        return _arrayGetBytes(j, j2, i, i2, bArr);
    }

    protected byte[] arrayGetBytes(long j, long j2, int i, int i2) {
        return _arrayGetBytes(j, j2, i, i2);
    }

    protected double arrayGetDouble(long j, long j2, int i) {
        return _arrayGetDouble(j, j2, i);
    }

    protected int arrayGetDoubles(long j, long j2, int i, int i2, double[] dArr) {
        return _arrayGetDoubles(j, j2, i, i2, dArr);
    }

    protected double[] arrayGetDoubles(long j, long j2, int i, int i2) {
        return _arrayGetDoubles(j, j2, i, i2);
    }

    protected int arrayGetInteger(long j, long j2, int i) {
        return _arrayGetInteger(j, j2, i);
    }

    protected int arrayGetIntegers(long j, long j2, int i, int i2, int[] iArr) {
        return _arrayGetIntegers(j, j2, i, i2, iArr);
    }

    protected int[] arrayGetIntegers(long j, long j2, int i, int i2) {
        return _arrayGetIntegers(j, j2, i, i2);
    }

    protected int arrayGetSize(long j, long j2) {
        return _arrayGetSize(j, j2);
    }

    protected String arrayGetString(long j, long j2, int i) {
        return _arrayGetString(j, j2, i);
    }

    protected int arrayGetStrings(long j, long j2, int i, int i2, String[] strArr) {
        return _arrayGetStrings(j, j2, i, i2, strArr);
    }

    protected String[] arrayGetStrings(long j, long j2, int i, int i2) {
        return _arrayGetStrings(j, j2, i, i2);
    }

    protected Object callObjectJavaMethod(long j, V8Object v8Object, V8Array v8Array) {
        MethodDescriptor methodDescriptor = this.functionRegistry.get(Long.valueOf(j));
        if (methodDescriptor.callback != null) {
            return checkResult(methodDescriptor.callback.invoke(v8Object, v8Array));
        }
        boolean zIsVarArgs = methodDescriptor.method.isVarArgs();
        Object[] args = getArgs(v8Object, methodDescriptor, v8Array, zIsVarArgs);
        checkArgs(args);
        try {
            try {
                sV8Receiver = v8Object;
                return checkResult(methodDescriptor.method.invoke(methodDescriptor.object, args));
            } catch (IllegalAccessException e) {
                throw e;
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (InvocationTargetException e3) {
                throw e3.getTargetException();
            }
        } finally {
            sV8Receiver = null;
            releaseArguments(args, zIsVarArgs);
        }
    }

    protected void callVoidJavaMethod(long j, V8Object v8Object, V8Array v8Array) {
        MethodDescriptor methodDescriptor = this.functionRegistry.get(Long.valueOf(j));
        if (methodDescriptor.voidCallback != null) {
            methodDescriptor.voidCallback.invoke(v8Object, v8Array);
            return;
        }
        boolean zIsVarArgs = methodDescriptor.method.isVarArgs();
        Object[] args = getArgs(v8Object, methodDescriptor, v8Array, zIsVarArgs);
        checkArgs(args);
        try {
            try {
                sV8Receiver = v8Object;
                methodDescriptor.method.invoke(methodDescriptor.object, args);
            } catch (Exception e) {
                if (V8Plugins.getJSBindingErrorHandler() == null) {
                    throw e;
                }
                V8Plugins.getJSBindingErrorHandler().onExceptionCaptured(e);
            }
        } finally {
            sV8Receiver = null;
            releaseArguments(args, zIsVarArgs);
        }
    }

    void checkRuntime(V8Value v8Value) {
        if (v8Value == null || v8Value.isUndefined()) {
            return;
        }
        V8 runtime = v8Value.getRuntime();
        if (runtime == null || runtime.isReleased() || runtime != this) {
            throw new Error("Invalid target runtime");
        }
    }

    void checkThread() {
        this.locker.checkThread();
        if (isReleased()) {
            throw new Error("Runtime disposed error");
        }
    }

    protected void clearWeak(long j, long j2) {
        _clearWeak(j, j2);
    }

    @Override // com.kwad.v8.V8Value, com.kwad.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        release(true);
    }

    protected boolean contains(long j, long j2, String str) {
        return _contains(j, j2, str);
    }

    void createAndRegisterMethodDescriptor(JavaCallback javaCallback, long j) {
        MethodDescriptor methodDescriptor = new MethodDescriptor();
        methodDescriptor.callback = javaCallback;
        this.functionRegistry.put(Long.valueOf(j), methodDescriptor);
    }

    public long createInspector(V8InspectorDelegate v8InspectorDelegate, String str) {
        return _createInspector(this.v8RuntimePtr, v8InspectorDelegate, str);
    }

    void createNodeRuntime(String str) {
        _startNodeJS(this.v8RuntimePtr, str);
    }

    protected void createTwin(long j, long j2, long j3) {
        _createTwin(j, j2, j3);
    }

    protected void createTwin(V8Value v8Value, V8Value v8Value2) {
        checkThread();
        createTwin(this.v8RuntimePtr, v8Value.getHandle(), v8Value2.getHandle());
    }

    protected ByteBuffer createV8ArrayBufferBackingStore(long j, long j2, int i) {
        return _createV8ArrayBufferBackingStore(j, j2, i);
    }

    public void dispatchProtocolMessage(long j, String str) {
        checkThread();
        _dispatchProtocolMessage(this.v8RuntimePtr, j, str);
    }

    protected void disposeMethodID(long j) {
        this.functionRegistry.remove(Long.valueOf(j));
    }

    protected boolean equals(long j, long j2, long j3) {
        return _equals(j, j2, j3);
    }

    public V8Array executeArrayScript(String str) {
        return executeArrayScript(str, null, 0);
    }

    public V8Array executeArrayScript(String str, String str2, int i) {
        checkThread();
        Object objExecuteScript = executeScript(str, str2, i);
        if (objExecuteScript instanceof V8Array) {
            return (V8Array) objExecuteScript;
        }
        throw new V8ResultUndefined();
    }

    protected boolean executeBooleanFunction(long j, long j2, String str, long j3) {
        return _executeBooleanFunction(j, j2, str, j3);
    }

    protected boolean executeBooleanScript(long j, String str, String str2, int i) {
        return _executeBooleanScript(j, str, str2, i);
    }

    public boolean executeBooleanScript(String str) {
        return executeBooleanScript(str, null, 0);
    }

    public boolean executeBooleanScript(String str, String str2, int i) {
        checkThread();
        checkScript(str);
        return executeBooleanScript(this.v8RuntimePtr, str, str2, i);
    }

    protected double executeDoubleFunction(long j, long j2, String str, long j3) {
        return _executeDoubleFunction(j, j2, str, j3);
    }

    protected double executeDoubleScript(long j, String str, String str2, int i) {
        return _executeDoubleScript(j, str, str2, i);
    }

    public double executeDoubleScript(String str) {
        return executeDoubleScript(str, null, 0);
    }

    public double executeDoubleScript(String str, String str2, int i) {
        checkThread();
        checkScript(str);
        return executeDoubleScript(this.v8RuntimePtr, str, str2, i);
    }

    protected Object executeFunction(long j, int i, long j2, String str, long j3) {
        return _executeFunction(j, i, j2, str, j3);
    }

    protected Object executeFunction(long j, long j2, long j3, long j4) {
        return _executeFunction(j, j2, j3, j4);
    }

    protected int executeIntegerFunction(long j, long j2, String str, long j3) {
        return _executeIntegerFunction(j, j2, str, j3);
    }

    protected int executeIntegerScript(long j, String str, String str2, int i) {
        return _executeIntegerScript(j, str, str2, i);
    }

    public int executeIntegerScript(String str) {
        return executeIntegerScript(str, null, 0);
    }

    public int executeIntegerScript(String str, String str2, int i) {
        checkThread();
        checkScript(str);
        return executeIntegerScript(this.v8RuntimePtr, str, str2, i);
    }

    public Object executeModule(String str, String str2, String str3, String str4) {
        checkThread();
        checkScript(str);
        return executeScript(getV8RuntimePtr(), 0, str2 + str + str3, str4, 0);
    }

    public V8Object executeObjectScript(String str) {
        return executeObjectScript(str, null, 0);
    }

    public V8Object executeObjectScript(String str, String str2, int i) {
        checkThread();
        Object objExecuteScript = executeScript(str, str2, i);
        if (objExecuteScript instanceof V8Object) {
            return (V8Object) objExecuteScript;
        }
        throw new V8ResultUndefined();
    }

    protected Object executeScript(long j, int i, String str, String str2, int i2) {
        return _executeScript(j, i, str, str2, i2);
    }

    public Object executeScript(String str) {
        return executeScript(str, null, 0);
    }

    public Object executeScript(String str, String str2) {
        checkThread();
        checkScript(str);
        return executeScript(getV8RuntimePtr(), 0, str, str2, 0);
    }

    public Object executeScript(String str, String str2, int i) {
        checkThread();
        checkScript(str);
        return executeScript(getV8RuntimePtr(), 0, str, str2, i);
    }

    protected String executeStringFunction(long j, long j2, String str, long j3) {
        return _executeStringFunction(j, j2, str, j3);
    }

    protected String executeStringScript(long j, String str, String str2, int i) {
        return _executeStringScript(j, str, str2, i);
    }

    public String executeStringScript(String str) {
        return executeStringScript(str, null, 0);
    }

    public String executeStringScript(String str, String str2, int i) {
        checkThread();
        checkScript(str);
        return executeStringScript(this.v8RuntimePtr, str, str2, i);
    }

    protected void executeVoidFunction(long j, long j2, String str, long j3) {
        _executeVoidFunction(j, j2, str, j3);
    }

    protected void executeVoidScript(long j, String str, String str2, int i) {
        _executeVoidScript(j, str, str2, i);
    }

    public void executeVoidScript(String str) {
        executeVoidScript(str, null, 0);
    }

    public void executeVoidScript(String str, String str2, int i) {
        checkThread();
        checkScript(str);
        executeVoidScript(this.v8RuntimePtr, str, str2, i);
    }

    protected Object get(long j, int i, long j2, String str) {
        return _get(j, i, j2, str);
    }

    protected int getArrayType(long j, long j2) {
        return _getArrayType(j, j2);
    }

    protected boolean getBoolean(long j, long j2, String str) {
        return _getBoolean(j, j2, str);
    }

    protected String getConstructorName(long j, long j2) {
        return _getConstructorName(j, j2);
    }

    public Object getData(String str) {
        if (this.data == null) {
            return null;
        }
        return this.data.get(str);
    }

    protected double getDouble(long j, long j2, String str) {
        return _getDouble(j, j2, str);
    }

    public V8Executor getExecutor(V8Object v8Object) {
        checkThread();
        if (this.executors == null) {
            return null;
        }
        return this.executors.get(v8Object);
    }

    protected int getInteger(long j, long j2, String str) {
        return _getInteger(j, j2, str);
    }

    protected String[] getKeys(long j, long j2) {
        return _getKeys(j, j2);
    }

    public V8Locker getLocker() {
        return this.locker;
    }

    public long getObjectReferenceCount() {
        return this.objectReferences - this.v8WeakReferences.size();
    }

    protected String getString(long j, long j2, String str) {
        return _getString(j, j2, str);
    }

    protected int getType(long j, long j2) {
        return _getType(j, j2);
    }

    protected int getType(long j, long j2, int i) {
        return _getType(j, j2, i);
    }

    protected int getType(long j, long j2, int i, int i2) {
        return _getType(j, j2, i, i2);
    }

    protected int getType(long j, long j2, String str) {
        return _getType(j, j2, str);
    }

    protected long getV8RuntimePtr() {
        return this.v8RuntimePtr;
    }

    protected int identityHash(long j, long j2) {
        return _identityHash(j, j2);
    }

    protected long initEmptyContainer(long j) {
        return _initEmptyContainer(j);
    }

    protected long initNewV8Array(long j) {
        return _initNewV8Array(j);
    }

    protected long initNewV8ArrayBuffer(long j, int i) {
        return _initNewV8ArrayBuffer(j, i);
    }

    protected long initNewV8ArrayBuffer(long j, ByteBuffer byteBuffer, int i) {
        return _initNewV8ArrayBuffer(j, byteBuffer, i);
    }

    public long initNewV8Float32Array(long j, long j2, int i, int i2) {
        return _initNewV8Float32Array(j, j2, i, i2);
    }

    public long initNewV8Float64Array(long j, long j2, int i, int i2) {
        return _initNewV8Float64Array(j, j2, i, i2);
    }

    protected long[] initNewV8Function(long j) {
        checkThread();
        return _initNewV8Function(j);
    }

    public long initNewV8Int16Array(long j, long j2, int i, int i2) {
        return _initNewV8Int16Array(j, j2, i, i2);
    }

    public long initNewV8Int32Array(long j, long j2, int i, int i2) {
        return _initNewV8Int32Array(j, j2, i, i2);
    }

    public long initNewV8Int8Array(long j, long j2, int i, int i2) {
        return _initNewV8Int8Array(j, j2, i, i2);
    }

    protected long initNewV8Object(long j) {
        return _initNewV8Object(j);
    }

    public long initNewV8UInt16Array(long j, long j2, int i, int i2) {
        return _initNewV8UInt16Array(j, j2, i, i2);
    }

    public long initNewV8UInt32Array(long j, long j2, int i, int i2) {
        return _initNewV8UInt32Array(j, j2, i, i2);
    }

    public long initNewV8UInt8Array(long j, long j2, int i, int i2) {
        return _initNewV8UInt8Array(j, j2, i, i2);
    }

    public long initNewV8UInt8ClampedArray(long j, long j2, int i, int i2) {
        return _initNewV8UInt8ClampedArray(j, j2, i, i2);
    }

    boolean isRunning() {
        return _isRunning(this.v8RuntimePtr);
    }

    protected boolean isWeak(long j, long j2) {
        return _isWeak(j, j2);
    }

    public void lowMemoryNotification() {
        checkThread();
        lowMemoryNotification(getV8RuntimePtr());
    }

    protected void lowMemoryNotification(long j) {
        _lowMemoryNotification(j);
    }

    boolean pumpMessageLoop() {
        return _pumpMessageLoop(this.v8RuntimePtr);
    }

    void registerCallback(JavaCallback javaCallback, long j, String str) {
        createAndRegisterMethodDescriptor(javaCallback, registerJavaMethod(getV8RuntimePtr(), j, str, false));
    }

    void registerCallback(Object obj, Method method, long j, String str, boolean z) {
        MethodDescriptor methodDescriptor = new MethodDescriptor();
        methodDescriptor.object = obj;
        methodDescriptor.method = method;
        methodDescriptor.includeReceiver = z;
        long jRegisterJavaMethod = registerJavaMethod(getV8RuntimePtr(), j, str, isVoidMethod(method));
        this.methodId = jRegisterJavaMethod;
        this.functionRegistry.put(Long.valueOf(jRegisterJavaMethod), methodDescriptor);
    }

    protected long registerJavaMethod(long j, long j2, String str, boolean z) {
        return _registerJavaMethod(j, j2, str, z);
    }

    public void registerResource(Releasable releasable) {
        checkThread();
        if (this.resources == null) {
            this.resources = new ArrayList();
        }
        this.resources.add(releasable);
    }

    public void registerV8Executor(V8Object v8Object, V8Executor v8Executor) {
        checkThread();
        if (this.executors == null) {
            this.executors = new V8Map<>();
        }
        this.executors.put2((V8Value) v8Object, (V8Object) v8Executor);
    }

    void registerVoidCallback(JavaVoidCallback javaVoidCallback, long j, String str) {
        MethodDescriptor methodDescriptor = new MethodDescriptor();
        methodDescriptor.voidCallback = javaVoidCallback;
        this.functionRegistry.put(Long.valueOf(registerJavaMethod(getV8RuntimePtr(), j, str, true)), methodDescriptor);
    }

    @Override // com.kwad.v8.V8Value, com.kwad.v8.Releasable
    @Deprecated
    public void release() {
        release(true);
    }

    protected void release(long j, long j2) {
        _release(j, j2);
    }

    public void release(boolean z) {
        if (isReleased()) {
            return;
        }
        checkThread();
        try {
            notifyReleaseHandlers(this);
            releaseResources();
            shutdownExecutors(this.forceTerminateExecutors);
            if (this.executors != null) {
                this.executors.clear();
            }
            releaseNativeMethodDescriptors();
            synchronized (lock) {
                runtimeCounter--;
            }
            _releaseRuntime(this.v8RuntimePtr);
            this.v8RuntimePtr = 0L;
            this.released = true;
            if (!z || getObjectReferenceCount() <= 0) {
                return;
            }
            throw new IllegalStateException(getObjectReferenceCount() + " Object(s) still exist in runtime");
        } catch (Throwable th) {
            releaseResources();
            shutdownExecutors(this.forceTerminateExecutors);
            if (this.executors != null) {
                this.executors.clear();
            }
            releaseNativeMethodDescriptors();
            synchronized (lock) {
                runtimeCounter--;
                _releaseRuntime(this.v8RuntimePtr);
                this.v8RuntimePtr = 0L;
                this.released = true;
                if (!z || getObjectReferenceCount() <= 0) {
                    throw th;
                }
                throw new IllegalStateException(getObjectReferenceCount() + " Object(s) still exist in runtime");
            }
        }
    }

    protected void releaseLock(long j) {
        _releaseLock(j);
    }

    protected void releaseMethodDescriptor(long j, long j2) {
        _releaseMethodDescriptor(j, j2);
    }

    void releaseObjRef(V8Value v8Value) {
        if (!this.referenceHandlers.isEmpty()) {
            notifyReferenceDisposed(v8Value);
        }
        this.objectReferences--;
    }

    public V8Executor removeExecutor(V8Object v8Object) {
        checkThread();
        if (this.executors == null) {
            return null;
        }
        return this.executors.remove(v8Object);
    }

    public void removeReferenceHandler(ReferenceHandler referenceHandler) {
        this.referenceHandlers.remove(referenceHandler);
    }

    public void removeReleaseHandler(V8Runnable v8Runnable) {
        this.releaseHandlers.remove(v8Runnable);
    }

    protected boolean sameValue(long j, long j2, long j3) {
        return _sameValue(j, j2, j3);
    }

    public void schedulePauseOnNextStatement(long j, String str) {
        checkThread();
        _schedulePauseOnNextStatement(this.v8RuntimePtr, j, str);
    }

    public synchronized void setData(String str, Object obj) {
        if (this.data == null) {
            this.data = new HashMap();
        }
        this.data.put(str, obj);
    }

    protected void setPrototype(long j, long j2, long j3) {
        _setPrototype(j, j2, j3);
    }

    public void setSignatureProvider(SignatureProvider signatureProvider) {
        this.signatureProvider = signatureProvider;
    }

    protected void setWeak(long j, long j2) {
        _setWeak(j, j2);
    }

    public void shutdownExecutors(boolean z) {
        checkThread();
        if (this.executors == null) {
            return;
        }
        for (V8Executor v8Executor : this.executors.values()) {
            if (z) {
                v8Executor.forceTermination();
            } else {
                v8Executor.shutdown();
            }
        }
    }

    protected boolean strictEquals(long j, long j2, long j3) {
        return _strictEquals(j, j2, j3);
    }

    public void terminateExecution() {
        this.forceTerminateExecutors = true;
        terminateExecution(this.v8RuntimePtr);
    }

    protected void terminateExecution(long j) {
        _terminateExecution(j);
    }

    protected String toString(long j, long j2) {
        return _toString(j, j2);
    }

    protected void weakReferenceReleased(long j) {
        V8Value v8Value = this.v8WeakReferences.get(Long.valueOf(j));
        if (v8Value != null) {
            this.v8WeakReferences.remove(Long.valueOf(j));
            try {
                v8Value.close();
            } catch (Exception unused) {
            }
        }
    }
}
