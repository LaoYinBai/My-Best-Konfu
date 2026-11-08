package com.bytedance.common.utility.persistent;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SharedPrefsEditorCompat {
    static final EditorImpl IMPL;

    interface EditorImpl {
        void apply(SharedPreferences.Editor editor);
    }

    static class BaseEditorImpl implements EditorImpl {
        BaseEditorImpl() {
        }

        @Override // com.bytedance.common.utility.persistent.SharedPrefsEditorCompat.EditorImpl
        public void apply(SharedPreferences.Editor editor) {
            editor.commit();
        }
    }

    static class GingerbreadEditorImpl implements EditorImpl {
        GingerbreadEditorImpl() {
        }

        @Override // com.bytedance.common.utility.persistent.SharedPrefsEditorCompat.EditorImpl
        @TargetApi(9)
        public void apply(SharedPreferences.Editor editor) {
            editor.apply();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 9) {
            IMPL = new GingerbreadEditorImpl();
        } else {
            IMPL = new BaseEditorImpl();
        }
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        IMPL.apply(editor);
    }
}
