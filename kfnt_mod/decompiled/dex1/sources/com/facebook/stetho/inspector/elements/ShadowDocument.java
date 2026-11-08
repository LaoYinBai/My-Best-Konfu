package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.Util;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ShadowDocument implements DocumentView {
    private final IdentityHashMap<Object, ElementInfo> mElementToInfoMap = new IdentityHashMap<>();
    private boolean mIsUpdating;
    private final Object mRootElement;

    public ShadowDocument(Object obj) {
        this.mRootElement = Util.throwIfNull(obj);
    }

    @Override // com.facebook.stetho.inspector.elements.DocumentView
    public Object getRootElement() {
        return this.mRootElement;
    }

    @Override // com.facebook.stetho.inspector.elements.DocumentView
    public ElementInfo getElementInfo(Object obj) {
        return this.mElementToInfoMap.get(obj);
    }

    public UpdateBuilder beginUpdate() {
        if (this.mIsUpdating) {
            throw new IllegalStateException();
        }
        this.mIsUpdating = true;
        return new UpdateBuilder();
    }

    public final class UpdateBuilder {
        private HashSet<Object> mCachedNotNewChildrenSet;
        private final Map<Object, ElementInfo> mElementToInfoChangesMap = new LinkedHashMap();
        private final HashSet<Object> mRootElementChanges = new HashSet<>();

        public UpdateBuilder() {
        }

        public void setElementChildren(Object obj, List<Object> list) {
            Object obj2;
            ElementInfo elementInfo;
            ElementInfo elementInfo2 = this.mElementToInfoChangesMap.get(obj);
            if (elementInfo2 == null || !ListUtil.identityEquals(list, elementInfo2.children)) {
                ElementInfo elementInfo3 = (ElementInfo) ShadowDocument.this.mElementToInfoMap.get(obj);
                if (elementInfo2 == null && elementInfo3 != null && ListUtil.identityEquals(list, elementInfo3.children)) {
                    return;
                }
                if (elementInfo2 != null && elementInfo3 != null && elementInfo3.parentElement == elementInfo2.parentElement && ListUtil.identityEquals(list, elementInfo3.children)) {
                    elementInfo = (ElementInfo) ShadowDocument.this.mElementToInfoMap.get(obj);
                    this.mElementToInfoChangesMap.remove(obj);
                } else {
                    if (elementInfo2 != null) {
                        obj2 = elementInfo2.parentElement;
                    } else {
                        obj2 = elementInfo3 != null ? elementInfo3.parentElement : null;
                    }
                    ElementInfo elementInfo4 = new ElementInfo(obj, obj2, list);
                    this.mElementToInfoChangesMap.put(obj, elementInfo4);
                    elementInfo = elementInfo4;
                }
                HashSet<Object> hashSetAcquireNotNewChildrenHashSet = acquireNotNewChildrenHashSet();
                if (elementInfo3 != null && elementInfo3.children != elementInfo.children) {
                    int size = elementInfo3.children.size();
                    for (int i = 0; i < size; i++) {
                        hashSetAcquireNotNewChildrenHashSet.add(elementInfo3.children.get(i));
                    }
                }
                if (elementInfo2 != null && elementInfo2.children != elementInfo.children) {
                    int size2 = elementInfo2.children.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        hashSetAcquireNotNewChildrenHashSet.add(elementInfo2.children.get(i2));
                    }
                }
                int size3 = elementInfo.children.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    Object obj3 = elementInfo.children.get(i3);
                    setElementParent(obj3, obj);
                    hashSetAcquireNotNewChildrenHashSet.remove(obj3);
                }
                Iterator<Object> it = hashSetAcquireNotNewChildrenHashSet.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    ElementInfo elementInfo5 = this.mElementToInfoChangesMap.get(next);
                    if (elementInfo5 == null || elementInfo5.parentElement == obj) {
                        ElementInfo elementInfo6 = (ElementInfo) ShadowDocument.this.mElementToInfoMap.get(next);
                        if (elementInfo6 != null && elementInfo6.parentElement == obj) {
                            setElementParent(next, null);
                        }
                    }
                }
                releaseNotNewChildrenHashSet(hashSetAcquireNotNewChildrenHashSet);
            }
        }

        private void setElementParent(Object obj, Object obj2) {
            List<Object> listEmptyList;
            ElementInfo elementInfo = this.mElementToInfoChangesMap.get(obj);
            if (elementInfo == null || obj2 != elementInfo.parentElement) {
                ElementInfo elementInfo2 = (ElementInfo) ShadowDocument.this.mElementToInfoMap.get(obj);
                if (elementInfo == null && elementInfo2 != null && obj2 == elementInfo2.parentElement) {
                    return;
                }
                if (elementInfo != null && elementInfo2 != null && obj2 == elementInfo2.parentElement && ListUtil.identityEquals(elementInfo2.children, elementInfo.children)) {
                    this.mElementToInfoChangesMap.remove(obj);
                    if (obj2 == null) {
                        this.mRootElementChanges.remove(obj);
                        return;
                    }
                    return;
                }
                if (elementInfo != null) {
                    listEmptyList = elementInfo.children;
                } else {
                    listEmptyList = elementInfo2 != null ? elementInfo2.children : Collections.emptyList();
                }
                this.mElementToInfoChangesMap.put(obj, new ElementInfo(obj, obj2, listEmptyList));
                if (obj2 == null) {
                    this.mRootElementChanges.add(obj);
                } else {
                    this.mRootElementChanges.remove(obj);
                }
            }
        }

        public Update build() {
            return ShadowDocument.this.new Update(this.mElementToInfoChangesMap, this.mRootElementChanges);
        }

        private HashSet<Object> acquireNotNewChildrenHashSet() {
            HashSet<Object> hashSet = this.mCachedNotNewChildrenSet;
            if (hashSet == null) {
                hashSet = new HashSet<>();
            }
            this.mCachedNotNewChildrenSet = null;
            return hashSet;
        }

        private void releaseNotNewChildrenHashSet(HashSet<Object> hashSet) {
            hashSet.clear();
            if (this.mCachedNotNewChildrenSet == null) {
                this.mCachedNotNewChildrenSet = hashSet;
            }
        }
    }

    public final class Update implements DocumentView {
        private final Map<Object, ElementInfo> mElementToInfoChangesMap;
        private final Set<Object> mRootElementChangesSet;

        public Update(Map<Object, ElementInfo> map, Set<Object> set) {
            this.mElementToInfoChangesMap = map;
            this.mRootElementChangesSet = set;
        }

        public boolean isEmpty() {
            return this.mElementToInfoChangesMap.isEmpty();
        }

        public boolean isElementChanged(Object obj) {
            return this.mElementToInfoChangesMap.containsKey(obj);
        }

        @Override // com.facebook.stetho.inspector.elements.DocumentView
        public Object getRootElement() {
            return ShadowDocument.this.getRootElement();
        }

        @Override // com.facebook.stetho.inspector.elements.DocumentView
        public ElementInfo getElementInfo(Object obj) {
            ElementInfo elementInfo = this.mElementToInfoChangesMap.get(obj);
            return elementInfo != null ? elementInfo : (ElementInfo) ShadowDocument.this.mElementToInfoMap.get(obj);
        }

        public void getChangedElements(Accumulator<Object> accumulator) {
            Iterator<Object> it = this.mElementToInfoChangesMap.keySet().iterator();
            while (it.hasNext()) {
                accumulator.store(it.next());
            }
        }

        public void getGarbageElements(Accumulator<Object> accumulator) {
            ArrayDeque arrayDeque = new ArrayDeque();
            for (Object obj : this.mRootElementChangesSet) {
                ElementInfo elementInfo = getElementInfo(obj);
                if (obj != ShadowDocument.this.mRootElement && elementInfo.parentElement == null) {
                    arrayDeque.add(obj);
                    arrayDeque.add(obj);
                }
            }
            while (!arrayDeque.isEmpty()) {
                Object objRemove = arrayDeque.remove();
                Object objRemove2 = arrayDeque.remove();
                if (objRemove == objRemove2) {
                    objRemove2 = null;
                }
                if (getElementInfo(objRemove).parentElement == objRemove2) {
                    accumulator.store(objRemove);
                    ElementInfo elementInfo2 = ShadowDocument.this.getElementInfo(objRemove);
                    if (elementInfo2 != null) {
                        int size = elementInfo2.children.size();
                        for (int i = 0; i < size; i++) {
                            arrayDeque.add(elementInfo2.children.get(i));
                            arrayDeque.add(objRemove);
                        }
                    }
                }
            }
        }

        public void abandon() {
            if (ShadowDocument.this.mIsUpdating) {
                ShadowDocument.this.mIsUpdating = false;
                return;
            }
            throw new IllegalStateException();
        }

        public void commit() {
            if (ShadowDocument.this.mIsUpdating) {
                ShadowDocument.this.mElementToInfoMap.putAll(this.mElementToInfoChangesMap);
                Iterator<Object> it = this.mRootElementChangesSet.iterator();
                while (it.hasNext()) {
                    removeGarbageSubTree(ShadowDocument.this.mElementToInfoMap, it.next());
                }
                ShadowDocument.this.mIsUpdating = false;
                return;
            }
            throw new IllegalStateException();
        }

        private void removeGarbageSubTree(Map<Object, ElementInfo> map, Object obj) {
            ElementInfo elementInfo = map.get(obj);
            if (elementInfo.parentElement == null || !map.containsKey(elementInfo.parentElement)) {
                map.remove(obj);
                int size = elementInfo.children.size();
                for (int i = 0; i < size; i++) {
                    removeGarbageSubTree(map, elementInfo.children.get(i));
                }
            }
        }

        private void validateTree(Map<Object, ElementInfo> map) {
            HashSet hashSet = new HashSet();
            for (Map.Entry<Object, ElementInfo> entry : map.entrySet()) {
                Object key = entry.getKey();
                ElementInfo value = entry.getValue();
                if (key != value.element) {
                    throw new IllegalStateException("element != elementInfo.element");
                }
                int size = value.children.size();
                for (int i = 0; i < size; i++) {
                    ElementInfo elementInfo = map.get(value.children.get(i));
                    if (elementInfo == null) {
                        throw new IllegalStateException(String.format("elementInfo.get(elementInfo.children.get(%s)) == null", Integer.valueOf(i)));
                    }
                    if (elementInfo.parentElement != key) {
                        throw new IllegalStateException("childElementInfo.parentElement != element");
                    }
                }
                if (value.parentElement == null) {
                    hashSet.add(key);
                } else {
                    ElementInfo elementInfo2 = map.get(value.parentElement);
                    if (elementInfo2 == null) {
                        throw new IllegalStateException("elementToInfoMap.get(elementInfo.parentElementInfo) == NULL");
                    }
                    if (value.parentElement != elementInfo2.element) {
                        throw new IllegalStateException("elementInfo.parentElementInfo != parentElementInfo.parent");
                    }
                    if (!elementInfo2.children.contains(key)) {
                        throw new IllegalStateException("parentElementInfo.children.contains(element) == FALSE");
                    }
                }
            }
            if (hashSet.size() != 1) {
                throw new IllegalStateException("elementToInfoMap is a forest, not a tree. rootElements.size() != 1");
            }
        }
    }
}
