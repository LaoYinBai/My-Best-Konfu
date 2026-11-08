package a.a.b.a;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c<T> extends AbstractCollection<T> {

    /* renamed from: a, reason: collision with root package name */
    public final List<WeakReference<T>> f330a = new ArrayList();

    public static class b<T> implements Iterator<T> {

        /* renamed from: a, reason: collision with root package name */
        public final Iterator<WeakReference<T>> f331a;

        /* renamed from: b, reason: collision with root package name */
        public T f332b;

        public /* synthetic */ b(Iterator it, a aVar) {
            this.f331a = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f332b != null) {
                return true;
            }
            while (this.f331a.hasNext()) {
                T t = this.f331a.next().get();
                if (t != null) {
                    this.f332b = t;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            T t = this.f332b;
            this.f332b = null;
            while (t == null) {
                t = this.f331a.next().get();
            }
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.f331a.remove();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(T t) {
        return this.f330a.add(new WeakReference<>(t));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.f330a.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Iterator<WeakReference<T>> it = this.f330a.iterator();
        while (it.hasNext()) {
            if (obj.equals(it.next().get())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    @NonNull
    public Iterator<T> iterator() {
        return new b(this.f330a.iterator(), null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        if (obj != null) {
            for (int i = 0; i < this.f330a.size(); i++) {
                if (obj.equals(this.f330a.get(i).get())) {
                    this.f330a.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        ArrayList arrayList = new ArrayList();
        for (WeakReference<T> weakReference : this.f330a) {
            if (weakReference.get() != null) {
                arrayList.add(weakReference);
            }
        }
        this.f330a.clear();
        this.f330a.addAll(arrayList);
        return this.f330a.size();
    }
}
