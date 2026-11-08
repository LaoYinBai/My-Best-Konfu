package com.kwad.v8.debug;

import com.kwad.v8.V8Object;
import com.kwad.v8.debug.DebugHandler;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface BreakHandler {
    void onBreak(DebugHandler.DebugEvent debugEvent, ExecutionState executionState, EventData eventData, V8Object v8Object);
}
