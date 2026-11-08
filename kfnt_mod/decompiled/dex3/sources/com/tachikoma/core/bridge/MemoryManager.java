package com.tachikoma.core.bridge;

import android.util.Log;
import androidx.annotation.Nullable;
import com.kwad.v8.ReferenceHandler;
import com.kwad.v8.V8;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.core.base.NativeModule;
import com.tachikoma.core.common.ILifeCycle;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MemoryManager {
    private static final String OBJ_ID_KEY = "_objId";
    private static long objId;
    private final boolean enableV8ObjectLeakCheck;
    private final V8 v8;
    private ArrayList<V8Value> references = new ArrayList<>();
    private boolean releasing = false;
    private boolean released = false;
    private Map<String, Object> dataMap = new HashMap();
    private Set<V8ValueReference> weakReferenceSet = new HashSet();
    private List<V8ValueReference<Throwable>> v8ValueCreateStackTrace = new ArrayList();
    private ReferenceHandler mReferenceHandler = new ReferenceHandler() { // from class: com.tachikoma.core.bridge.MemoryManager.1
        public void v8HandleCreated(V8Value v8Value) {
            if (MemoryManager.this.releasing) {
                return;
            }
            MemoryManager.this.references.add(v8Value);
            if (MemoryManager.this.enableV8ObjectLeakCheck) {
                MemoryManager.this.v8ValueCreateStackTrace.add(new V8ValueReference(v8Value, new Exception("")));
            }
        }

        public void v8HandleDisposed(V8Value v8Value) {
            if (MemoryManager.this.releasing) {
                return;
            }
            Iterator it = MemoryManager.this.references.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next() == v8Value) {
                    it.remove();
                    break;
                }
            }
            if (MemoryManager.this.enableV8ObjectLeakCheck) {
                MemoryManager.this.v8ValueCreateStackTrace.remove(new V8ValueReference(v8Value));
            }
            if ((v8Value instanceof V8Object) && MemoryManager.this.weakReferenceSet.remove(new V8ValueReference(v8Value))) {
                MemoryManager.this.destroyLinkedNativeObject((V8Object) v8Value);
            }
        }
    };

    private static class V8ValueReference<T> {

        @Nullable
        final T ext;
        final V8Value v8Value;

        private V8ValueReference(V8Value v8Value) {
            this.v8Value = v8Value;
            this.ext = null;
        }

        private V8ValueReference(V8Value v8Value, T t) {
            this.v8Value = v8Value;
            this.ext = t;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && ((V8ValueReference) obj).v8Value == this.v8Value;
        }

        public int hashCode() {
            return this.v8Value.hashCode();
        }
    }

    public MemoryManager(V8 v8, boolean z) {
        this.v8 = v8;
        this.enableV8ObjectLeakCheck = z;
        v8.addReferenceHandler(this.mReferenceHandler);
    }

    private void checkReleased() {
        if (this.released) {
            throw new IllegalStateException("Memory manager released");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyLinkedNativeObject(V8Object v8Object) {
        Object objRemove = this.dataMap.remove(String.valueOf(v8Object.get(OBJ_ID_KEY)));
        if (objRemove instanceof ILifeCycle) {
            ((ILifeCycle) objRemove).onDestroy();
        } else if (objRemove instanceof NativeModule) {
            ((NativeModule) objRemove).destroy();
        }
    }

    private static String generateOrGetId(V8Object v8Object) {
        Object obj = v8Object.get(OBJ_ID_KEY);
        if (obj instanceof String) {
            return (String) obj;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        long j = objId;
        objId = 1 + j;
        sb.append(j);
        String string = sb.toString();
        v8Object.add(OBJ_ID_KEY, string);
        return string;
    }

    private void warningUnCloseV8Value() {
        if (!this.enableV8ObjectLeakCheck || this.v8ValueCreateStackTrace.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        int i = 0;
        for (V8ValueReference<Throwable> v8ValueReference : this.v8ValueCreateStackTrace) {
            StringWriter stringWriter = new StringWriter();
            if (v8ValueReference.ext != null && !v8ValueReference.v8Value.isReleased() && !v8ValueReference.v8Value.isWeak()) {
                i++;
                v8ValueReference.ext.printStackTrace(new PrintWriter(stringWriter));
                String string = stringWriter.toString();
                Integer num = (Integer) map.get(string);
                map.put(string, Integer.valueOf(Integer.valueOf(num == null ? 0 : num.intValue()).intValue() + 1));
            }
        }
        Log.e("MemoryManager", "总计未关闭的引用个数: " + i);
        for (String str : map.keySet()) {
            Log.e("MemoryManager", "未关闭的引用个数：" + map.get(str));
            Log.e("MemoryManager", str);
        }
        this.v8ValueCreateStackTrace.clear();
    }

    public <T> T getLinkedNativeObject(V8Object v8Object) {
        this.v8.getLocker().checkThread();
        return (T) this.dataMap.get(generateOrGetId(v8Object));
    }

    public int getObjectReferenceCount() {
        checkReleased();
        return this.references.size();
    }

    public boolean isReleased() {
        return this.released;
    }

    public void linkNativeObject(V8Object v8Object, Object obj) {
        this.v8.getLocker().checkThread();
        if (v8Object == null || obj == null) {
            return;
        }
        removeLinkedNativeObject(v8Object);
        this.weakReferenceSet.add(new V8ValueReference(v8Object.twin().setWeak()));
        this.dataMap.put(generateOrGetId(v8Object), obj);
    }

    public void persist(V8Value v8Value) {
        this.v8.getLocker().checkThread();
        checkReleased();
        this.references.remove(v8Value);
        if (this.enableV8ObjectLeakCheck) {
            this.v8ValueCreateStackTrace.remove(new V8ValueReference(v8Value));
        }
    }

    public void release() {
        this.v8.getLocker().checkThread();
        if (this.released) {
            return;
        }
        this.releasing = true;
        try {
            this.v8.removeReferenceHandler(this.mReferenceHandler);
            warningUnCloseV8Value();
            Iterator<V8Value> it = this.references.iterator();
            while (it.hasNext()) {
                V8Value next = it.next();
                if (!next.isReleased()) {
                    if (next instanceof V8Object) {
                        destroyLinkedNativeObject((V8Object) next);
                    }
                    next.close();
                }
            }
            this.references.clear();
            this.releasing = false;
            this.released = true;
        } catch (Throwable th) {
            this.releasing = false;
            throw th;
        }
    }

    public <T> T removeLinkedNativeObject(V8Object v8Object) {
        this.v8.getLocker().checkThread();
        this.weakReferenceSet.remove(new V8ValueReference((V8Value) v8Object));
        return (T) this.dataMap.remove(generateOrGetId(v8Object));
    }

    public boolean removeNativeObject(Object obj) {
        for (Map.Entry<String, Object> entry : this.dataMap.entrySet()) {
            if (entry.getValue() == obj) {
                this.dataMap.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }
}
