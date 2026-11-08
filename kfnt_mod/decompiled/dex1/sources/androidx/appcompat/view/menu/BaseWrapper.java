package androidx.appcompat.view.menu;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class BaseWrapper<T> {
    final T mWrappedObject;

    BaseWrapper(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = t;
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}
