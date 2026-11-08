package com.kwad.v8.debug;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum StepAction {
    STEP_OUT(0),
    STEP_NEXT(1),
    STEP_IN(2),
    STEP_FRAME(3);

    int index;

    StepAction(int i) {
        this.index = i;
    }
}
