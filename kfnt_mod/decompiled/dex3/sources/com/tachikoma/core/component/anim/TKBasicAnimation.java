package com.tachikoma.core.component.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.yoga.YogaValue;
import com.sigmob.sdk.common.mta.PointCategory;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.V8FunctionImpl;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.anim.TimeFunctionConst;
import com.tachikoma.core.component.anim.interpolator.CubicBezierInterpolator;
import com.tachikoma.core.component.view.TKViewBackgroundDrawable;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.utility.TKConverter;
import com.tachikoma.core.utility.UIThreadUtil;
import com.tachikoma.core.utility.V8Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBasicAnimation {
    public static final int AXIS_X = 1;
    public static final int AXIS_Y = 2;
    public static final int AXIS_Z = 3;
    public static final int DIRECTION_X = 11;
    public static final int DIRECTION_XY = 13;
    public static final int DIRECTION_Y = 12;
    protected V8Function animNJEndListener;
    protected V8Function animNJStartListener;
    public String animType;
    public Object animValue;
    protected V8Array animationV8Array;
    protected Animator animator;
    public boolean autoReverse;
    public float delay;
    public float duration;
    protected V8FunctionImpl endValue;
    protected View mTargetView;
    public boolean needTranformIdentity;
    public int repeatCount;
    protected V8FunctionImpl startValue;
    public String timeFunction;
    public String timingFunction;
    protected float mInitialScaleX = 1.0f;
    protected float mInitialScaleY = 1.0f;
    protected float mInitialTranslationX = 0.0f;
    protected float mInitialTranslationY = 0.0f;
    protected float mInitialRotationX = 0.0f;
    protected float mInitialRotationY = 0.0f;
    protected float mInitialAlpha = 1.0f;
    protected Runnable animatorOnStartForDelay = new Runnable() { // from class: com.tachikoma.core.component.anim.TKBasicAnimation.3
        @Override // java.lang.Runnable
        public void run() {
            if (TKBasicAnimation.this.animNJStartListener != null && !TKBasicAnimation.this.animNJStartListener.isReleased()) {
                try {
                    TKBasicAnimation.this.animNJStartListener.call((V8Object) null, (V8Array) null);
                } catch (Throwable th) {
                    TKExceptionDispatcher.reportException(null, th);
                }
                V8Proxy.release(TKBasicAnimation.this.animNJStartListener);
            }
            TKBasicAnimation.this.readInitial();
        }
    };
    protected AnimatorListenerAdapter animatorListener = new AnimatorListenerAdapter() { // from class: com.tachikoma.core.component.anim.TKBasicAnimation.4
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (TKBasicAnimation.this.animNJEndListener != null && !TKBasicAnimation.this.animNJEndListener.isReleased()) {
                try {
                    TKBasicAnimation.this.animNJEndListener.call((V8Object) null, (V8Array) null);
                    V8Proxy.release(TKBasicAnimation.this.animNJEndListener);
                } catch (Throwable th) {
                    TKExceptionDispatcher.reportException(null, th);
                }
            }
            TKBasicAnimation.this.resetInitial();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            UIThreadUtil.postDelay(TKBasicAnimation.this.animatorOnStartForDelay, TKBasicAnimation.this.getAnimDelay());
        }
    };

    public interface AnimationJsCallback {
        void onJsExecuteComplete(double d);
    }

    public TKBasicAnimation(Context context, @Nullable List<Object> list) {
    }

    private boolean animationEnable(HashMap<Float, Object> map) {
        if (map != null && !map.isEmpty() && map.size() != 1) {
            Iterator<Map.Entry<Float, Object>> it = map.entrySet().iterator();
            Map.Entry<Float, Object> next = it.hasNext() ? it.next() : null;
            if (next == null) {
                return false;
            }
            while (it.hasNext()) {
                Map.Entry<Float, Object> next2 = it.next();
                if (!isAnimationPropertyEquals(next.getValue(), next2.getValue())) {
                    return true;
                }
                next = next2;
            }
        }
        return false;
    }

    private ObjectAnimator buildColorKeyFrameAnimator(final TKBase tKBase, String str, Map<Float, Object> map) {
        if (tKBase != null && tKBase.getView() != null && map != null) {
            if (1 < map.size()) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<Float, Object> entry : map.entrySet()) {
                    if (entry != null && entry.getValue() != null) {
                        Object value = entry.getValue();
                        if (value instanceof Integer) {
                            arrayList.add(Keyframe.ofInt(entry.getKey().floatValue(), ((Integer) value).intValue()));
                        }
                    }
                }
                if (arrayList.size() <= 0) {
                    return null;
                }
                ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(tKBase.getView(), PropertyValuesHolder.ofKeyframe(String.format("_%s_", str), (Keyframe[]) arrayList.toArray(new Keyframe[0])));
                objectAnimatorOfPropertyValuesHolder.setEvaluator(new ArgbEvaluator());
                objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
                objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
                objectAnimatorOfPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.tachikoma.core.component.anim.TKBasicAnimation.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (!(valueAnimator.getAnimatedValue() instanceof Integer) || tKBase.getView() == null) {
                            return;
                        }
                        tKBase.setBackgroundColorInt(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                return objectAnimatorOfPropertyValuesHolder;
            }
        }
        return null;
    }

    private ObjectAnimator buildKeyFrameAnimator(View view, String str, Map<Float, Object> map) {
        float fFloatValue;
        float fFloatValue2;
        Keyframe keyframeOfFloat;
        if (map != null) {
            if (1 < map.size()) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<Float, Object> entry : map.entrySet()) {
                    if (entry != null && entry.getValue() != null) {
                        Object value = entry.getValue();
                        if (value instanceof Float) {
                            fFloatValue = entry.getKey().floatValue();
                            fFloatValue2 = ((Float) value).floatValue();
                        } else if (value instanceof YogaValue) {
                            fFloatValue = entry.getKey().floatValue();
                            fFloatValue2 = ((YogaValue) value).value;
                        } else if (value instanceof Integer) {
                            keyframeOfFloat = Keyframe.ofInt(entry.getKey().floatValue(), ((Integer) value).intValue());
                            arrayList.add(keyframeOfFloat);
                        }
                        keyframeOfFloat = Keyframe.ofFloat(fFloatValue, fFloatValue2);
                        arrayList.add(keyframeOfFloat);
                    }
                }
                if (arrayList.size() <= 0) {
                    return null;
                }
                ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(str, (Keyframe[]) arrayList.toArray(new Keyframe[0])));
                objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
                objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
                objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
                return objectAnimatorOfPropertyValuesHolder;
            }
        }
        return null;
    }

    private boolean isAnimationPropertyEquals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        return ((obj instanceof Float) && (obj2 instanceof Float)) ? Float.compare(((Float) obj).floatValue(), ((Float) obj2).floatValue()) == 0 : ((obj instanceof Integer) && (obj2 instanceof Integer)) ? ((Integer) obj).intValue() == ((Integer) obj2).intValue() : (obj instanceof YogaValue) && (obj2 instanceof YogaValue) && Float.compare(((YogaValue) obj).value, ((YogaValue) obj2).value) == 0;
    }

    private void pauseAnimation(AnimatorSet animatorSet) {
        if (Build.VERSION.SDK_INT >= 19) {
            pauseAnimation19(animatorSet);
        } else {
            pauseAnimation18(animatorSet);
        }
    }

    @TargetApi(18)
    private void pauseAnimation18(AnimatorSet animatorSet) {
        animatorSet.cancel();
    }

    @TargetApi(19)
    private void pauseAnimation19(AnimatorSet animatorSet) {
        animatorSet.pause();
    }

    private void resumeAnimation(AnimatorSet animatorSet) {
        if (Build.VERSION.SDK_INT >= 19) {
            resumeAnimation19(animatorSet);
        } else {
            resumeAnimation18(animatorSet);
        }
    }

    @TargetApi(18)
    private void resumeAnimation18(AnimatorSet animatorSet) {
        animatorSet.start();
    }

    @TargetApi(19)
    private void resumeAnimation19(AnimatorSet animatorSet) {
        animatorSet.resume();
    }

    private ObjectAnimator setAlphaAnim(View view) {
        if (!((Map) this.animValue).containsKey("opacity")) {
            return null;
        }
        float alpha = view.getAlpha();
        float floatValue = parseFloatValue(trans2String(((Map) this.animValue).get("opacity")));
        if (alpha == floatValue) {
            return null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, AnimationProperty.OPACITY, alpha, floatValue);
        objectAnimatorOfFloat.setDuration(getAnimDuration());
        objectAnimatorOfFloat.setRepeatCount(this.repeatCount);
        objectAnimatorOfFloat.setRepeatMode(this.autoReverse ? 2 : 1);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator setBackgroundColorAnim(View view) {
        if (!((Map) this.animValue).containsKey(AnimationProperty.BACKGROUND_COLOR)) {
            return null;
        }
        Object obj = ((Map) this.animValue).get(AnimationProperty.BACKGROUND_COLOR);
        TKViewBackgroundDrawable tKViewBackgroundDrawable = (TKViewBackgroundDrawable) view.getBackground();
        int color = tKViewBackgroundDrawable.getColor();
        int color2 = parseColor(trans2String(obj));
        if (color == color2) {
            return null;
        }
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(tKViewBackgroundDrawable, "color", color, color2);
        objectAnimatorOfInt.setDuration(getAnimDuration());
        objectAnimatorOfInt.setEvaluator(new ArgbEvaluator());
        objectAnimatorOfInt.setRepeatCount(this.repeatCount);
        objectAnimatorOfInt.setRepeatMode(this.autoReverse ? 2 : 1);
        return objectAnimatorOfInt;
    }

    private ObjectAnimator setPositionAnim(View view) {
        String[] strArrTrans2StringArray;
        if (!((Map) this.animValue).containsKey("position") || (strArrTrans2StringArray = trans2StringArray(((Map) this.animValue).get("position"))) == null) {
            return null;
        }
        if (strArrTrans2StringArray.length != 2) {
            return null;
        }
        float[] fArr = {0.0f, 0.0f};
        fArr[0] = parsePxValue(strArrTrans2StringArray[0]);
        fArr[1] = parsePxValue(strArrTrans2StringArray[1]);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(AnimationProperty.TRANSLATE_X, fArr[0]), PropertyValuesHolder.ofFloat(AnimationProperty.TRANSLATE_Y, fArr[1]));
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        return objectAnimatorOfPropertyValuesHolder;
    }

    private ObjectAnimator setRotationAnim(View view) {
        String[] strArrTrans2StringArray;
        if (!((Map) this.animValue).containsKey("rotation") || (strArrTrans2StringArray = trans2StringArray(((Map) this.animValue).get("rotation"))) == null) {
            return null;
        }
        if (strArrTrans2StringArray.length != 2) {
            return null;
        }
        float[] fArr = {0.0f, 0.0f};
        fArr[0] = parseFloatValue(strArrTrans2StringArray[0]);
        fArr[1] = parseFloatValue(strArrTrans2StringArray[1]);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(AnimationProperty.ROTATE_X, fArr[0]), PropertyValuesHolder.ofFloat(AnimationProperty.ROTATE_Y, fArr[1]));
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        return objectAnimatorOfPropertyValuesHolder;
    }

    private ObjectAnimator setScaleAnim(View view) {
        String[] strArrTrans2StringArray;
        if (!((Map) this.animValue).containsKey(AnimationProperty.SCALE) || (strArrTrans2StringArray = trans2StringArray(((Map) this.animValue).get(AnimationProperty.SCALE))) == null) {
            return null;
        }
        if (strArrTrans2StringArray.length != 2) {
            return null;
        }
        float[] fArr = {0.0f, 0.0f};
        fArr[0] = parseFloatValue(strArrTrans2StringArray[0]);
        fArr[1] = parseFloatValue(strArrTrans2StringArray[1]);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(AnimationProperty.SCALE_X, fArr[0]), PropertyValuesHolder.ofFloat(AnimationProperty.SCALE_Y, fArr[1]));
        objectAnimatorOfPropertyValuesHolder.setDuration(getAnimDuration());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        return objectAnimatorOfPropertyValuesHolder;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0130  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void startAnimation(com.tachikoma.core.component.TKBase r11) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.anim.TKBasicAnimation.startAnimation(com.tachikoma.core.component.TKBase):void");
    }

    private void startBasicAnimation(View view) {
        if (this.animValue instanceof Map) {
            ObjectAnimator positionAnim = setPositionAnim(view);
            ObjectAnimator scaleAnim = setScaleAnim(view);
            ObjectAnimator rotationAnim = setRotationAnim(view);
            ObjectAnimator alphaAnim = setAlphaAnim(view);
            ObjectAnimator backgroundColorAnim = setBackgroundColorAnim(view);
            ArrayList arrayList = new ArrayList();
            if (positionAnim != null) {
                arrayList.add(positionAnim);
            }
            if (scaleAnim != null) {
                arrayList.add(scaleAnim);
            }
            if (rotationAnim != null) {
                arrayList.add(rotationAnim);
            }
            if (alphaAnim != null) {
                arrayList.add(alphaAnim);
            }
            if (backgroundColorAnim != null) {
                arrayList.add(backgroundColorAnim);
            }
            if (arrayList.size() == 0) {
                return;
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            animatorSet.setStartDelay(getAnimDelay());
            animatorSet.setInterpolator(getInterpolator());
            animatorSet.addListener(this.animatorListener);
            animatorSet.start();
        }
    }

    private void startKeyframeAnimation(View view) {
        int i;
        int i2;
        if ("position".equalsIgnoreCase(this.animType)) {
            animTranslation(view);
            return;
        }
        if ("opacity".equalsIgnoreCase(this.animType)) {
            animAlpha(view);
            return;
        }
        if (AnimationProperty.SCALE.equalsIgnoreCase(this.animType)) {
            i2 = 13;
        } else if (AnimationProperty.SCALE_X.equalsIgnoreCase(this.animType)) {
            i2 = 11;
        } else {
            if (!AnimationProperty.SCALE_Y.equalsIgnoreCase(this.animType)) {
                if (AnimationProperty.ROTATE_X.equalsIgnoreCase(this.animType)) {
                    i = 1;
                } else if (AnimationProperty.ROTATE_Y.equalsIgnoreCase(this.animType)) {
                    i = 2;
                } else {
                    if (!"rotationZ".equalsIgnoreCase(this.animType)) {
                        if ("bgColor".equalsIgnoreCase(this.animType)) {
                            animBackgroundColor(view);
                            return;
                        }
                        return;
                    }
                    i = 3;
                }
                animRotation(view, i);
                return;
            }
            i2 = 12;
        }
        animScale(view, i2);
    }

    private void updateAnimateLayoutParams(ObjectAnimator objectAnimator, final TKBase tKBase, final String str) {
        if (objectAnimator == null || tKBase == null || tKBase.getView() == null) {
            return;
        }
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.tachikoma.core.component.anim.TKBasicAnimation.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Object animatedValue = valueAnimator.getAnimatedValue();
                if (!(animatedValue instanceof Float)) {
                    Logger.logE("updateAnimateLayoutParams", new IllegalStateException("unknown state " + str));
                    return;
                }
                if (tKBase.getView() == null) {
                    return;
                }
                float fFloatValue = ((Float) animatedValue).floatValue();
                HashMap map = new HashMap();
                map.put(str, Float.valueOf(TKConverter.px2dp(tKBase.getView().getContext(), fFloatValue)));
                tKBase.setStyle(map);
            }
        });
    }

    protected void animAlpha(View view) {
        float alpha = view.getAlpha();
        float floatValue = parseFloatValue(trans2String(this.animValue));
        if (alpha != floatValue) {
            ObjectAnimator duration = ObjectAnimator.ofFloat(view, AnimationProperty.OPACITY, alpha, floatValue).setDuration(getAnimDuration());
            this.animator = duration;
            duration.setRepeatCount(this.repeatCount);
            duration.setRepeatMode(this.autoReverse ? 2 : 1);
            duration.setStartDelay(getAnimDelay());
            duration.setInterpolator(getInterpolator());
            duration.addListener(this.animatorListener);
            duration.start();
        }
    }

    protected void animBackgroundColor(View view) {
        int color = ((TKViewBackgroundDrawable) view.getBackground()).getColor();
        int color2 = parseColor(trans2String(this.animValue));
        if (color != color2) {
            ObjectAnimator duration = ObjectAnimator.ofInt(view, AnimationProperty.BACKGROUND_COLOR, color, color2).setDuration(getAnimDuration());
            this.animator = duration;
            duration.setRepeatCount(this.repeatCount);
            duration.setRepeatMode(this.autoReverse ? 2 : 1);
            duration.setStartDelay(getAnimDelay());
            duration.setInterpolator(getInterpolator());
            duration.setEvaluator(new ArgbEvaluator());
            duration.addListener(this.animatorListener);
            duration.start();
        }
    }

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
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, str, 0.0f, parseFloatValue(trans2String(this.animValue))).setDuration(getAnimDuration());
        this.animator = duration;
        duration.setRepeatCount(this.repeatCount);
        duration.setRepeatMode(this.autoReverse ? 2 : 1);
        duration.setStartDelay(getAnimDelay());
        duration.setInterpolator(getInterpolator());
        duration.addListener(this.animatorListener);
        duration.start();
    }

    protected void animScale(View view, int i) {
        String str;
        float[] fArr;
        ObjectAnimator duration;
        float floatValue = parseFloatValue(trans2String(this.animValue));
        switch (i) {
            case 11:
                str = AnimationProperty.SCALE_X;
                fArr = new float[]{0.0f, floatValue};
                duration = ObjectAnimator.ofFloat(view, str, fArr).setDuration(getAnimDuration());
                break;
            case 12:
                str = AnimationProperty.SCALE_Y;
                fArr = new float[]{0.0f, floatValue};
                duration = ObjectAnimator.ofFloat(view, str, fArr).setDuration(getAnimDuration());
                break;
            default:
                duration = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(AnimationProperty.SCALE_X, floatValue), PropertyValuesHolder.ofFloat(AnimationProperty.SCALE_Y, floatValue));
                break;
        }
        this.animator = duration;
        duration.setRepeatCount(this.repeatCount);
        duration.setRepeatMode(this.autoReverse ? 2 : 1);
        duration.setStartDelay(getAnimDelay());
        duration.setInterpolator(getInterpolator());
        duration.addListener(this.animatorListener);
        duration.start();
    }

    protected void animTranslation(View view) {
        float[] fArr = {0.0f, 0.0f};
        String[] strArrTrans2StringArray = trans2StringArray(this.animValue);
        if (strArrTrans2StringArray != null && strArrTrans2StringArray.length == 2) {
            fArr[0] = parsePxValue(strArrTrans2StringArray[0]);
            fArr[1] = parsePxValue(strArrTrans2StringArray[1]);
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(AnimationProperty.TRANSLATE_X, fArr[0]), PropertyValuesHolder.ofFloat(AnimationProperty.TRANSLATE_Y, fArr[1]));
        this.animator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(this.repeatCount);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(this.autoReverse ? 2 : 1);
        objectAnimatorOfPropertyValuesHolder.setStartDelay(getAnimDelay());
        objectAnimatorOfPropertyValuesHolder.setInterpolator(getInterpolator());
        objectAnimatorOfPropertyValuesHolder.addListener(this.animatorListener);
        objectAnimatorOfPropertyValuesHolder.start();
    }

    public void destroyAnimation() {
        V8Proxy.release(this.animationV8Array);
    }

    protected Map<String, HashMap<Float, Object>> diffAnimationProperty(List<String> list, HashMap<Float, HashMap<String, Object>> map) {
        Object obj;
        HashMap map2 = new HashMap();
        if (map != null && map.size() > 0) {
            for (String str : list) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<Float, HashMap<String, Object>> entry : map.entrySet()) {
                    Float key = entry.getKey();
                    HashMap<String, Object> value = entry.getValue();
                    if (!value.isEmpty() && (obj = value.get(str)) != null) {
                        linkedHashMap.put(key, obj);
                    }
                }
                if (!linkedHashMap.isEmpty()) {
                    map2.put(str, linkedHashMap);
                }
            }
            Iterator it = map2.entrySet().iterator();
            while (it.hasNext()) {
                if (!animationEnable((HashMap) ((Map.Entry) it.next()).getValue())) {
                    it.remove();
                }
            }
        }
        return map2;
    }

    public void executeEndValueFunction(AnimationJsCallback animationJsCallback) {
        if (this.endValue == null) {
            return;
        }
        this.endValue.call(new Object[0]);
        if (animationJsCallback != null) {
            animationJsCallback.onJsExecuteComplete(1.0d);
        }
    }

    public void executeStartValueFunction(AnimationJsCallback animationJsCallback) {
        if (this.startValue == null) {
            return;
        }
        this.startValue.call(new Object[0]);
        if (animationJsCallback != null) {
            animationJsCallback.onJsExecuteComplete(0.0d);
        }
    }

    protected int getAnimDelay() {
        return (int) this.delay;
    }

    protected long getAnimDuration() {
        return (long) this.duration;
    }

    protected TimeInterpolator getInterpolator() {
        if (TimeFunctionConst.Time.LINEAR.equalsIgnoreCase(this.timeFunction)) {
            return new LinearInterpolator();
        }
        if (TimeFunctionConst.Time.EASE_IN.equalsIgnoreCase(this.timeFunction)) {
            return new AccelerateInterpolator();
        }
        if (TimeFunctionConst.Time.EASE_OUT.equalsIgnoreCase(this.timeFunction)) {
            return new DecelerateInterpolator();
        }
        if (TimeFunctionConst.Time.EASE_IN_OUT.equalsIgnoreCase(this.timeFunction)) {
            return new AccelerateDecelerateInterpolator();
        }
        if (TimeFunctionConst.Timing.LINEAR.equalsIgnoreCase(this.timingFunction)) {
            return CubicBezierInterpolator.buildDefaultLinearInterpolator();
        }
        if (TimeFunctionConst.Timing.EASE_IN.equalsIgnoreCase(this.timingFunction)) {
            return CubicBezierInterpolator.buildDefaultEaseInInterpolator();
        }
        if (TimeFunctionConst.Timing.EASE_OUT.equalsIgnoreCase(this.timingFunction)) {
            return CubicBezierInterpolator.buildDefaultEaseOutInterpolator();
        }
        if (TimeFunctionConst.Timing.EASE_IN_OUT.equalsIgnoreCase(this.timingFunction)) {
            return CubicBezierInterpolator.buildDefaultEaseInOutInterpolator();
        }
        if (TextUtils.isEmpty(this.timingFunction)) {
            return new AccelerateDecelerateInterpolator();
        }
        String[] strArrSplit = this.timingFunction.split(" ");
        if (4 != strArrSplit.length) {
            return new AccelerateDecelerateInterpolator();
        }
        try {
            return new CubicBezierInterpolator(Float.parseFloat(strArrSplit[0]), Float.parseFloat(strArrSplit[1]), Float.parseFloat(strArrSplit[2]), Float.parseFloat(strArrSplit[3]));
        } catch (Throwable unused) {
            return new AccelerateDecelerateInterpolator();
        }
    }

    public boolean isRunning() {
        return this.animator != null && this.animator.isRunning();
    }

    public boolean newVersionEnable() {
        return (this.startValue == null && this.endValue == null && this.animationV8Array == null) ? false : true;
    }

    public void on(String str, V8Function v8Function) {
        if (PointCategory.START.equalsIgnoreCase(str)) {
            this.animNJStartListener = v8Function.twin();
        } else if ("end".equalsIgnoreCase(str)) {
            this.animNJEndListener = v8Function.twin();
        }
    }

    protected int parseColor(String str) {
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            return Color.parseColor(str);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    protected float parseFloatValue(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    protected float parsePxValue(String str) throws NumberFormatException {
        float fDp2px = 0.0f;
        try {
        } catch (Exception e) {
            e = e;
        }
        if (str.toLowerCase().endsWith("px")) {
            return Float.parseFloat(str.replace("px", ""));
        }
        float f = Float.parseFloat(str);
        try {
            fDp2px = TKConverter.dp2px(Tachikoma.sApplication, f);
        } catch (Exception e2) {
            fDp2px = f;
            e = e2;
            e.printStackTrace();
            return fDp2px;
        }
        return fDp2px;
    }

    public void pauseAnimation(TKBase tKBase) {
        if (tKBase == null || tKBase.getView() == null) {
            return;
        }
        Object tag = tKBase.getView().getTag(R.id.tk_animation);
        if (tag instanceof AnimatorSet) {
            pauseAnimation((AnimatorSet) tag);
        }
    }

    protected void readInitial() {
        if (this.mTargetView == null || !this.needTranformIdentity) {
            return;
        }
        this.mInitialScaleX = this.mTargetView.getScaleX();
        this.mInitialScaleY = this.mTargetView.getScaleY();
        this.mInitialTranslationX = this.mTargetView.getTranslationX();
        this.mInitialTranslationY = this.mTargetView.getTranslationY();
        this.mInitialRotationX = this.mTargetView.getRotationX();
        this.mInitialRotationY = this.mTargetView.getRotationY();
        this.mInitialAlpha = this.mTargetView.getAlpha();
    }

    protected void resetInitial() {
        if (this.mTargetView == null || !this.needTranformIdentity) {
            return;
        }
        this.mTargetView.setScaleX(this.mInitialScaleX);
        this.mTargetView.setScaleY(this.mInitialScaleY);
        this.mTargetView.setTranslationX(this.mInitialTranslationX);
        this.mTargetView.setTranslationY(this.mInitialTranslationY);
        this.mTargetView.setRotationX(this.mInitialRotationX);
        this.mTargetView.setRotationY(this.mInitialRotationY);
        this.mTargetView.setAlpha(this.mInitialAlpha);
    }

    public void resumeAnimation(TKBase tKBase) {
        if (tKBase == null || tKBase.getView() == null) {
            return;
        }
        Object tag = tKBase.getView().getTag(R.id.tk_animation);
        if (tag instanceof AnimatorSet) {
            resumeAnimation((AnimatorSet) tag);
        }
    }

    public void setAnimType(String str) {
        this.animType = str;
    }

    public void setAnimValue(Object obj) {
        this.animValue = obj;
    }

    public void setAutoReverse(boolean z) {
        this.autoReverse = z;
    }

    public void setDelay(float f) {
        this.delay = f;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public void setEndValue(Object obj) {
        if (obj instanceof V8FunctionImpl) {
            this.endValue = (V8FunctionImpl) obj;
        }
    }

    public void setNeedTranformIdentity(boolean z) {
        this.needTranformIdentity = z;
    }

    public void setRepeatCount(int i) {
        int i2;
        if (i >= Integer.MAX_VALUE) {
            i2 = -1;
        } else {
            if (i <= 0) {
                this.repeatCount = i;
                return;
            }
            i2 = i - 1;
        }
        this.repeatCount = i2;
    }

    public void setStartValue(Object obj) {
        if (obj instanceof V8FunctionImpl) {
            this.startValue = (V8FunctionImpl) obj;
        }
    }

    public void setTimeFunction(String str) {
        this.timeFunction = str;
    }

    public void setTimingFunction(String str) {
        this.timingFunction = str;
    }

    public void start(TKBase tKBase) {
        stop(tKBase);
        if (newVersionEnable()) {
            startAnimation(tKBase);
            return;
        }
        this.mTargetView = tKBase.getView();
        if (!TextUtils.isEmpty(this.animType)) {
            startKeyframeAnimation(this.mTargetView);
        } else if (this.animValue != null) {
            startBasicAnimation(this.mTargetView);
        }
    }

    public void stop(TKBase tKBase) {
        if (newVersionEnable() && tKBase != null && tKBase.getView() != null) {
            Object tag = tKBase.getView().getTag(R.id.tk_animation);
            if (tag instanceof AnimatorSet) {
                AnimatorSet animatorSet = (AnimatorSet) tag;
                animatorSet.removeAllListeners();
                animatorSet.end();
            }
        }
        if (isRunning()) {
            this.animator.cancel();
            this.animator = null;
        }
        UIThreadUtil.removeCallback(this.animatorOnStartForDelay);
    }

    protected String trans2String(Object obj) {
        if (obj instanceof Map) {
            obj = ((Map) obj).get("value");
        } else if (!(obj instanceof String)) {
            return String.valueOf(obj);
        }
        return (String) obj;
    }

    protected String[] trans2StringArray(Object obj) {
        if (!(obj instanceof Map)) {
            return null;
        }
        Map map = (Map) obj;
        String[] strArr = new String[2];
        Object obj2 = map.get("x");
        if (obj2 instanceof String) {
            strArr[0] = (String) obj2;
        } else {
            strArr[0] = String.valueOf(obj2);
        }
        Object obj3 = map.get("y");
        if (obj3 instanceof String) {
            strArr[1] = (String) obj3;
            return strArr;
        }
        strArr[1] = String.valueOf(obj3);
        return strArr;
    }
}
