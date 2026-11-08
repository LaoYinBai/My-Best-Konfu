package com.ss.union.game.sdk.core.base.debug.automatic_detection.in;

import com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ILGDetectionParams {
    List<b> getAllParams();

    b getParameterByKey(String str);

    void insertParams(String str, String str2);
}
