package com.tachikoma.core.component.anim;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import com.kwad.v8.Releasable;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.anim.TKBasicAnimation;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.utility.V8Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKKeyframeAnimation extends TKBasicAnimation {
    public ArrayList<KeyFrame> keyframes;

    public class KeyFrame {
        public String easing;
        public float percent;
        public Object value;

        public KeyFrame() {
        }
    }

    public TKKeyframeAnimation(Context context, @Nullable List<Object> list) {
        super(context, list);
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    protected void animAlpha(View view) {
        Keyframe[] keyframeArr = new Keyframe[this.keyframes.size()];
        for (int i = 0; i < this.keyframes.size(); i++) {
            KeyFrame keyFrame = this.keyframes.get(i);
            keyframeArr[i] = Keyframe.ofFloat(keyFrame.percent, parseFloatValue(trans2String(keyFrame.value)));
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(AnimationProperty.OPACITY, keyframeArr));
        this.animator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        objectAnimatorOfPropertyValuesHolder.setStartDelay(getAnimDelay());
        objectAnimatorOfPropertyValuesHolder.setInterpolator(getInterpolator());
        objectAnimatorOfPropertyValuesHolder.addListener(this.animatorListener);
        objectAnimatorOfPropertyValuesHolder.start();
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    protected void animBackgroundColor(View view) {
        Keyframe[] keyframeArr = new Keyframe[this.keyframes.size()];
        for (int i = 0; i < this.keyframes.size(); i++) {
            KeyFrame keyFrame = this.keyframes.get(i);
            keyframeArr[i] = Keyframe.ofInt(keyFrame.percent, parseColor(trans2String(keyFrame.value)));
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(AnimationProperty.BACKGROUND_COLOR, keyframeArr));
        this.animator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setEvaluator(new ArgbEvaluator());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        objectAnimatorOfPropertyValuesHolder.setStartDelay(getAnimDelay());
        objectAnimatorOfPropertyValuesHolder.setInterpolator(getInterpolator());
        objectAnimatorOfPropertyValuesHolder.addListener(this.animatorListener);
        objectAnimatorOfPropertyValuesHolder.start();
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    protected void animRotation(View view, int i) {
        String str;
        switch (i) {
            case 1:
                str = AnimationProperty.ROTATE_X;
                break;
            case 2:
                str = AnimationProperty.ROTATE_Y;
                break;
            default:
                str = "rotation";
                break;
        }
        Keyframe[] keyframeArr = new Keyframe[this.keyframes.size()];
        for (int i2 = 0; i2 < this.keyframes.size(); i2++) {
            KeyFrame keyFrame = this.keyframes.get(i2);
            keyframeArr[i2] = Keyframe.ofFloat(keyFrame.percent, parseFloatValue(trans2String(keyFrame.value)));
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(str, keyframeArr));
        this.animator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        objectAnimatorOfPropertyValuesHolder.setStartDelay(getAnimDelay());
        objectAnimatorOfPropertyValuesHolder.setInterpolator(getInterpolator());
        objectAnimatorOfPropertyValuesHolder.addListener(this.animatorListener);
        objectAnimatorOfPropertyValuesHolder.start();
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    protected void animScale(View view, int i) {
        PropertyValuesHolder[] propertyValuesHolderArr;
        ObjectAnimator objectAnimatorOfPropertyValuesHolder;
        Keyframe[] keyframeArr = new Keyframe[this.keyframes.size()];
        for (int i2 = 0; i2 < this.keyframes.size(); i2++) {
            KeyFrame keyFrame = this.keyframes.get(i2);
            keyframeArr[i2] = Keyframe.ofFloat(keyFrame.percent, parseFloatValue(trans2String(keyFrame.value)));
        }
        switch (i) {
            case 11:
                propertyValuesHolderArr = new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(AnimationProperty.SCALE_X, keyframeArr)};
                objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolderArr);
                break;
            case 12:
                propertyValuesHolderArr = new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(AnimationProperty.SCALE_Y, keyframeArr)};
                objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolderArr);
                break;
            default:
                objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(AnimationProperty.SCALE_X, keyframeArr), PropertyValuesHolder.ofKeyframe(AnimationProperty.SCALE_Y, keyframeArr));
                break;
        }
        this.animator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        objectAnimatorOfPropertyValuesHolder.setStartDelay(getAnimDelay());
        objectAnimatorOfPropertyValuesHolder.setInterpolator(getInterpolator());
        objectAnimatorOfPropertyValuesHolder.addListener(this.animatorListener);
        objectAnimatorOfPropertyValuesHolder.start();
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    protected void animTranslation(View view) {
        Keyframe[] keyframeArr = new Keyframe[this.keyframes.size()];
        Keyframe[] keyframeArr2 = new Keyframe[this.keyframes.size()];
        for (int i = 0; i < this.keyframes.size(); i++) {
            KeyFrame keyFrame = this.keyframes.get(i);
            String[] strArrTrans2StringArray = trans2StringArray(keyFrame.value);
            Keyframe keyframeOfFloat = Keyframe.ofFloat(keyFrame.percent, parsePxValue(strArrTrans2StringArray[0]));
            Keyframe keyframeOfFloat2 = Keyframe.ofFloat(keyFrame.percent, parsePxValue(strArrTrans2StringArray[1]));
            keyframeArr[i] = keyframeOfFloat;
            keyframeArr2[i] = keyframeOfFloat2;
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(AnimationProperty.TRANSLATE_X, keyframeArr), PropertyValuesHolder.ofKeyframe(AnimationProperty.TRANSLATE_Y, keyframeArr2));
        this.animator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        objectAnimatorOfPropertyValuesHolder.setStartDelay(getAnimDelay());
        objectAnimatorOfPropertyValuesHolder.setInterpolator(getInterpolator());
        objectAnimatorOfPropertyValuesHolder.addListener(this.animatorListener);
        objectAnimatorOfPropertyValuesHolder.start();
    }

    public void executeAnimationFunction(TKBasicAnimation.AnimationJsCallback animationJsCallback) {
        if (this.animationV8Array == null || this.animationV8Array.isReleased()) {
            return;
        }
        int length = this.animationV8Array.length();
        for (int i = 0; i < length; i++) {
            try {
                try {
                    Object obj = this.animationV8Array.get(i);
                    if (obj instanceof V8Function) {
                        V8Function v8Function = (V8Function) obj;
                        V8Array v8ArrayCreateV8Array = V8Proxy.createV8Array((V8Value) v8Function, new String[0]);
                        Object objCall = v8Function.call((V8Object) null, v8ArrayCreateV8Array);
                        if (animationJsCallback != null && (objCall instanceof Number)) {
                            animationJsCallback.onJsExecuteComplete(((Number) objCall).floatValue());
                        }
                        if (objCall instanceof V8Value) {
                            V8Proxy.release((V8Value) objCall);
                        }
                        v8ArrayCreateV8Array.close();
                        v8Function.close();
                    } else if (obj instanceof Releasable) {
                        ((Releasable) obj).release();
                    }
                } catch (Throwable th) {
                    Logger.logE("executeAnimationFunction", th);
                }
            } finally {
                this.animationV8Array.close();
            }
        }
    }

    public void setAnimations(Object obj) {
        if (obj instanceof V8Array) {
            this.animationV8Array = ((V8Array) obj).twin();
        }
    }

    @Override // com.tachikoma.core.component.anim.TKBasicAnimation
    public void setDuration(float f) {
        this.duration = f;
    }

    public void setKeyframes(ArrayList<Object> arrayList) {
        this.keyframes = new ArrayList<>();
        Object[] array = arrayList.toArray();
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            KeyFrame keyFrame = new KeyFrame();
            Map map = (Map) obj;
            if (map.containsKey("percent")) {
                keyFrame.percent = ((Number) map.get("percent")).floatValue();
            }
            if (map.containsKey("value")) {
                keyFrame.value = map.get("value");
            }
            if (map.containsKey("easing")) {
                keyFrame.easing = (String) map.get("easing");
            }
            if (keyFrame.percent == 0.0f) {
                float length = 1.0f;
                if (array.length == 1) {
                    keyFrame.percent = length;
                } else if (i > 0) {
                    length = (1.0f / (array.length - 1)) * i;
                    keyFrame.percent = length;
                }
            }
            this.keyframes.add(keyFrame);
        }
    }
}
