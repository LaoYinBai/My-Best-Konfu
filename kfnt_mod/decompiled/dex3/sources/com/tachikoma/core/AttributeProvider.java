package com.tachikoma.core;

import com.tachikoma.core.manager.IProvider;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AttributeProvider implements IProvider<Object> {
    private static double trans(Number number) {
        return number.doubleValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00cf  */
    @Override // com.tachikoma.core.manager.IProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void apply(java.lang.String r3, java.lang.Object r4, java.util.HashMap<java.lang.String, java.lang.Object> r5) throws java.lang.IllegalAccessException, java.lang.NoSuchFieldException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 7264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.AttributeProvider.apply(java.lang.String, java.lang.Object, java.util.HashMap):void");
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void clear() {
    }

    @Override // com.tachikoma.core.manager.IProvider
    public HashMap<String, String> get() {
        return null;
    }

    @Override // com.tachikoma.core.manager.IProvider
    public void init() {
    }

    @Override // com.tachikoma.core.manager.IProvider
    public Object of(String str) {
        return null;
    }

    @Override // com.tachikoma.core.manager.IProvider
    public HashMap<String, Object> retrieveEvent(String str, Object obj) {
        return null;
    }
}
