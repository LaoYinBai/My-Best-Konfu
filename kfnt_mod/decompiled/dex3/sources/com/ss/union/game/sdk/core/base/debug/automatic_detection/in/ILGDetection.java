package com.ss.union.game.sdk.core.base.debug.automatic_detection.in;

import com.ss.union.game.sdk.core.base.debug.automatic_detection.b.a;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ILGDetection {
    void detection(LGDetectionConstant.DetectionType detectionType, String str, LGDetectionConstant.DetectionState detectionState);

    void generateDetectionReport(a<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a> aVar);
}
