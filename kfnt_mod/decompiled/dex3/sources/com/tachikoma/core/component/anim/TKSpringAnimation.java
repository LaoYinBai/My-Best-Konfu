package com.tachikoma.core.component.anim;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSpringAnimation extends TKBasicAnimation {
    public float damping;
    public float stiffness;

    public TKSpringAnimation(Context context, @Nullable List<Object> list) {
        super(context, list);
        this.stiffness = 380.0f;
        this.damping = 20.0f;
    }

    private float calculateDampingRatio(float f, float f2) {
        return (float) (f / (Math.sqrt(f2) * 2.0d));
    }

    private void createAndStartAnimation(View view, FloatPropertyCompat<View> floatPropertyCompat, float f, float f2, float f3) {
        SpringAnimation springAnimation = new SpringAnimation(view, floatPropertyCompat, f);
        springAnimation.getSpring().setStiffness(f3);
        springAnimation.getSpring().setDampingRatio(f2);
        springAnimation.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void startAnimationNew(com.tachikoma.core.component.TKBase r11) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.anim.TKSpringAnimation.startAnimationNew(com.tachikoma.core.component.TKBase):void");
    }

    private void startAnimationOld(View view) {
        if (this.animValue == null) {
            return;
        }
        float fCalculateDampingRatio = calculateDampingRatio(this.damping, this.stiffness);
        startPositionAnim(view, fCalculateDampingRatio, this.stiffness);
        startScaleAnim(view, fCalculateDampingRatio, this.stiffness);
    }

    private void startPositionAnim(View view, float f, float f2) {
        String[] strArrTrans2StringArray;
        if (((Map) this.animValue).containsKey("position") && (strArrTrans2StringArray = trans2StringArray(((Map) this.animValue).get("position"))) != null && strArrTrans2StringArray.length == 2) {
            float[] fArr = {0.0f, 0.0f};
            fArr[0] = parsePxValue(strArrTrans2StringArray[0]);
            fArr[1] = parsePxValue(strArrTrans2StringArray[1]);
            createAndStartAnimation(view, DynamicAnimation.TRANSLATION_X, fArr[0], f, f2);
            createAndStartAnimation(view, DynamicAnimation.TRANSLATION_Y, fArr[1], f, f2);
        }
    }

    private void startScaleAnim(View view, float f, float f2) {
        String[] strArrTrans2StringArray;
        if (((Map) this.animValue).containsKey(AnimationProperty.SCALE) && (strArrTrans2StringArray = trans2StringArray(((Map) this.animValue).get(AnimationProperty.SCALE))) != null && strArrTrans2StringArray.length == 2) {
            float[] fArr = {0.0f, 0.0f};
            fArr[0] = parseFloatValue(strArrTrans2StringArray[0]);
            fArr[1] = parseFloatValue(strArrTrans2StringArray[1]);
            createAndStartAnimation(view, DynamicAnimation.SCALE_X, fArr[0], f, f2);
            createAndStartAnimation(view, DynamicAnimation.SCALE_Y, fArr[1], f, f2);
        }
    }

    public void setDamping(float f) {
        this.damping = f;
    }

    public void setStiffness(float f) {
        this.stiffness = f;
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    public void start(TKBase tKBase) {
        if (newVersionEnable()) {
            startAnimationNew(tKBase);
        } else {
            startAnimationOld(tKBase.getView());
        }
    }
}
