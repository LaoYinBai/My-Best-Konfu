package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Frame extends Mirror {
    private static final String ARGUMENT_COUNT = "argumentCount";
    private static final String ARGUMENT_NAME = "argumentName";
    private static final String ARGUMENT_VALUE = "argumentValue";
    private static final String COLUMN = "column";
    private static final String FUNC = "func";
    private static final String LINE = "line";
    private static final String LOCAL_COUNT = "localCount";
    private static final String LOCAL_NAME = "localName";
    private static final String LOCAL_VALUE = "localValue";
    private static final String NAME = "name";
    private static final String POSITION = "position";
    private static final String SCOPE = "scope";
    private static final String SCOPE_COUNT = "scopeCount";
    private static final String SCRIPT = "script";
    private static final String SOURCE_LOCATION = "sourceLocation";
    private static final String SOURCE_TEXT = "sourceText";

    public Frame(V8Object v8Object) {
        super(v8Object);
    }

    public int getArgumentCount() {
        return this.v8Object.executeIntegerFunction(ARGUMENT_COUNT, null);
    }

    public String getArgumentName(int i) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i);
        try {
            return this.v8Object.executeStringFunction(ARGUMENT_NAME, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public ValueMirror getArgumentValue(int i) throws Throwable {
        V8Object v8ObjectExecuteObjectFunction;
        Throwable th;
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i);
        try {
            v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(ARGUMENT_VALUE, v8Array);
            try {
                if (!isValue(v8ObjectExecuteObjectFunction)) {
                    throw new IllegalStateException("Argument value is not a ValueMirror");
                }
                ValueMirror valueMirror = new ValueMirror(v8ObjectExecuteObjectFunction);
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return valueMirror;
            } catch (Throwable th2) {
                th = th2;
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            v8ObjectExecuteObjectFunction = null;
            th = th3;
        }
    }

    public FunctionMirror getFunction() throws Throwable {
        V8Object v8Object = null;
        try {
            V8Object v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(FUNC, null);
            try {
                FunctionMirror functionMirror = new FunctionMirror(v8ObjectExecuteObjectFunction);
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return functionMirror;
            } catch (Throwable th) {
                th = th;
                v8Object = v8ObjectExecuteObjectFunction;
                if (v8Object != null) {
                    v8Object.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int getLocalCount() {
        return this.v8Object.executeIntegerFunction(LOCAL_COUNT, null);
    }

    public String getLocalName(int i) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i);
        try {
            return this.v8Object.executeStringFunction(LOCAL_NAME, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public ValueMirror getLocalValue(int i) throws Throwable {
        V8Object v8ObjectExecuteObjectFunction;
        Throwable th;
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i);
        try {
            v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(LOCAL_VALUE, v8Array);
            try {
                if (!isValue(v8ObjectExecuteObjectFunction)) {
                    throw new IllegalStateException("Local value is not a ValueMirror");
                }
                ValueMirror valueMirrorCreateMirror = createMirror(v8ObjectExecuteObjectFunction);
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return valueMirrorCreateMirror;
            } catch (Throwable th2) {
                th = th2;
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            v8ObjectExecuteObjectFunction = null;
            th = th3;
        }
    }

    public Scope getScope(int i) throws Throwable {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i);
        V8Object v8Object = null;
        try {
            V8Object v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(SCOPE, v8Array);
            try {
                Scope scope = new Scope(v8ObjectExecuteObjectFunction);
                v8Array.close();
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return scope;
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

    public int getScopeCount() {
        return this.v8Object.executeIntegerFunction(SCOPE_COUNT, null);
    }

    public SourceLocation getSourceLocation() throws Throwable {
        String string = null;
        V8Object v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(SOURCE_LOCATION, null);
        FunctionMirror function = getFunction();
        String scriptName = function.getScriptName();
        try {
            V8Object v8Object = (V8Object) v8ObjectExecuteObjectFunction.get(SCRIPT);
            if (v8Object != null) {
                string = v8Object.getString("name");
                v8Object.close();
            }
            if (string != null || scriptName == null) {
                scriptName = "undefined";
            }
            return new SourceLocation(scriptName, v8ObjectExecuteObjectFunction.getInteger(POSITION), v8ObjectExecuteObjectFunction.getInteger(LINE), v8ObjectExecuteObjectFunction.getInteger(COLUMN), v8ObjectExecuteObjectFunction.getString(SOURCE_TEXT));
        } finally {
            function.close();
            v8ObjectExecuteObjectFunction.close();
        }
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isFrame() {
        return true;
    }
}
