package com.ss.union.game.sdk.core.base.dialog.model;

import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StandardDialogParams implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;

    public StandardDialogParams setTitle(String str) {
        this.a = str;
        return this;
    }

    public StandardDialogParams setEnhanceBtnText(String str) {
        this.c = str;
        return this;
    }

    public StandardDialogParams setDescription(String str) {
        this.b = str;
        return this;
    }

    public StandardDialogParams setWeaknessBtnText(String str) {
        this.d = str;
        return this;
    }

    public StandardDialogParams setBackBtnEnable(boolean z) {
        this.e = z;
        return this;
    }

    public StandardDialogParams setCloseBtnEnable(boolean z) {
        this.f = z;
        return this;
    }

    public StandardDialogParams setWeaknessBtnEnable(boolean z) {
        this.g = z;
        return this;
    }

    public String getTitle() {
        return this.a;
    }

    public String getDescription() {
        return this.b;
    }

    public String getEnhanceBtnText() {
        return this.c;
    }

    public String getWeaknessBtnText() {
        return this.d;
    }

    public boolean backBtnEnable() {
        return this.e;
    }

    public boolean closeBtnEnable() {
        return this.f;
    }

    public boolean weaknessBtnEnable() {
        return this.g;
    }
}
