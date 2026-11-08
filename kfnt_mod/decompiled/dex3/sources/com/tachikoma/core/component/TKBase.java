package com.tachikoma.core.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import com.kwad.lottie.LottieComposition;
import com.kwad.lottie.LottieCompositionFactory;
import com.kwad.lottie.LottieDrawable;
import com.kwad.lottie.LottieListener;
import com.kwad.lottie.LottieTask;
import com.kwad.sdk.R;
import com.kwad.v8.JavaCallback;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.kwad.v8.utils.V8ObjectUtils;
import com.kwad.yoga.YogaEdge;
import com.sigmob.sdk.common.Constants;
import com.tachikoma.core.IV8Function;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.V8FunctionImpl;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.common.ILifeCycle;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.component.anim.CustomViewPropertyAnimator;
import com.tachikoma.core.component.anim.TKBasicAnimation;
import com.tachikoma.core.component.anim.TKKeyframeAnimation;
import com.tachikoma.core.component.view.TKViewBackgroundDrawable;
import com.tachikoma.core.component.view.ViewBackgroundManager;
import com.tachikoma.core.event.TKEventProcess;
import com.tachikoma.core.event.base.GestureUtils;
import com.tachikoma.core.event.base.IBaseEvent;
import com.tachikoma.core.event.base.TKBaseEvent;
import com.tachikoma.core.event.guesture.TKTapEvent;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.layout.TKDomNode;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.utility.SizeUtil;
import com.tachikoma.core.utility.TKColorUtil;
import com.tachikoma.core.utility.TKConverter;
import com.tachikoma.core.utility.TKEnv;
import com.tachikoma.core.utility.V8Proxy;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class TKBase<T extends View> implements ILifeCycle {
    public static final String DISPLAY_INLINE = "inline";
    public static final String DISPLAY_NONE = "none";
    public static final String VISIBILITY_HIDDEN = "hidden";
    public static final String VISIBILITY_VISIBLE = "visible";
    public boolean enabled;
    public String lottiePath;
    public String lottieRes;
    protected String mBackgroundColor;
    protected float mBorderRadius;
    private GestureDetector mGestureDetector;
    private IJS2NativeInvoker mIJS2NativeInvoker;
    private MotionEvent mLatestMotionEvent;
    private TKDomNode mNode;
    private ScaleGestureDetector mScaleGestureDetector;
    protected TKJSContext mTKJSContext;
    private T mTargetView;
    private V8Object mV8This;
    private TKBase rootTkBase;
    public HashMap style;
    private ViewBackgroundManager viewBackgroundManager;
    public String viewID;
    protected HashMap<String, V8Function> eventListener = new HashMap<>();
    private Map<String, TKBasicAnimation> mAnimationMap = new HashMap();
    private boolean isClickInit = false;
    protected HashMap<Float, Map<String, Object>> mAnimationPropertySnapshot = new LinkedHashMap();
    private List<JSContext.V8AssociateReference> associateAnimationRefList = new ArrayList();

    public interface IJS2NativeInvoker {
        Object onJSInvokeNativeWithJSONString(String str, String str2, IV8Function iV8Function);
    }

    public TKBase(Context context, List<Object> list) {
        this.mTKJSContext = V8Proxy.getTKContext(list);
        this.mTargetView = (T) createView(context);
        if (this.mTargetView != null) {
            this.mTargetView.setTag(R.id.tk_node, this);
        }
        this.mNode = TKDomNode.nodeForView(this, list);
        this.viewBackgroundManager = new ViewBackgroundManager(getView());
        this.rootTkBase = this;
    }

    private void backtrackRoot(View view) {
        if (view == null) {
            return;
        }
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            Object tag = view2.getTag(R.id.tk_node);
            if (tag instanceof TKBase) {
                this.rootTkBase = (TKBase) tag;
            }
            backtrackRoot(view2);
        }
    }

    private T createView(Context context) {
        return (T) createViewInstance(context);
    }

    private void destroyAnimation() {
        if (this.mAnimationMap == null || this.mAnimationMap.size() <= 0) {
            return;
        }
        for (Map.Entry<String, TKBasicAnimation> entry : this.mAnimationMap.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                entry.getValue().destroyAnimation();
            }
        }
    }

    private void executeAnimationJsFunction(TKBasicAnimation tKBasicAnimation) {
        if (tKBasicAnimation.newVersionEnable()) {
            backtrackRoot(getView());
            this.mAnimationPropertySnapshot.clear();
            this.mAnimationPropertySnapshot.put(Float.valueOf(0.0f), makeAnimationPropertySnapshot());
            TKBasicAnimation.AnimationJsCallback animationJsCallback = new TKBasicAnimation.AnimationJsCallback() { // from class: com.tachikoma.core.component.TKBase.5
                @Override // com.tachikoma.core.component.anim.TKBasicAnimation.AnimationJsCallback
                public void onJsExecuteComplete(double d) {
                    if (TKBase.this.rootTkBase != null && TKBase.this.rootTkBase.getDomNode() != null) {
                        TKBase.this.rootTkBase.getDomNode().layoutSubviews();
                    }
                    TKBase.this.mAnimationPropertySnapshot.put(new Float(d), TKBase.this.makeAnimationPropertySnapshot());
                }
            };
            tKBasicAnimation.executeStartValueFunction(animationJsCallback);
            tKBasicAnimation.executeEndValueFunction(animationJsCallback);
            boolean z = tKBasicAnimation instanceof TKKeyframeAnimation;
            if (z) {
                ((TKKeyframeAnimation) tKBasicAnimation).executeAnimationFunction(animationJsCallback);
            }
            if (!z || this.mAnimationMap.containsKey(Float.valueOf(1.0f))) {
                return;
            }
            this.mAnimationPropertySnapshot.put(Float.valueOf(1.0f), makeAnimationPropertySnapshot());
        }
    }

    public static <T extends View> TKBase<T> getTKBaseFromView(View view) {
        return (TKBase) view.getTag(R.id.tk_node);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initViewGestureEvent() {
        if (this.mTargetView != null && this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() { // from class: com.tachikoma.core.component.TKBase.2
                @Override // android.view.GestureDetector.OnGestureListener
                public boolean onDown(final MotionEvent motionEvent) {
                    TKBase.this.dispatchEvent("down", new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.2.1
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            iBaseEvent.setType("down");
                            iBaseEvent.setState(GestureUtils.findStateInMotionEvent(motionEvent));
                            iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.2.1.1
                                {
                                    put("x", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getX())));
                                    put("y", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getY())));
                                }
                            });
                        }
                    });
                    return true;
                }

                @Override // android.view.GestureDetector.OnGestureListener
                public boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float f, final float f2) {
                    TKBase.this.dispatchEvent(TKBaseEvent.TK_SWIPE_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.2.5
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            iBaseEvent.setType(TKBaseEvent.TK_SWIPE_EVENT_NAME);
                            iBaseEvent.setState(2);
                            if (motionEvent.getAction() == 0) {
                                iBaseEvent.setState(1);
                            }
                            if (motionEvent2.getAction() == 1) {
                                iBaseEvent.setState(3);
                            } else if (motionEvent2.getAction() == 3) {
                                iBaseEvent.setState(4);
                            }
                            iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.2.5.1
                                {
                                    put("beginX", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getX())));
                                    put("beginY", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getY())));
                                    put("endX", Integer.valueOf(SizeUtil.px2Dp(motionEvent2.getX())));
                                    put("endY", Integer.valueOf(SizeUtil.px2Dp(motionEvent2.getY())));
                                    put("velocityX", Integer.valueOf(SizeUtil.px2Dp(f)));
                                    put("velocityY", Integer.valueOf(SizeUtil.px2Dp(f2)));
                                }
                            });
                        }
                    });
                    return false;
                }

                @Override // android.view.GestureDetector.OnGestureListener
                public void onLongPress(final MotionEvent motionEvent) {
                    TKBase.this.dispatchEvent(TKBaseEvent.TK_LONG_PRESS_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.2.4
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            iBaseEvent.setType(TKBaseEvent.TK_LONG_PRESS_EVENT_NAME);
                            iBaseEvent.setState(GestureUtils.findStateInMotionEvent(motionEvent));
                            iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.2.4.1
                                {
                                    put("x", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getX())));
                                    put("y", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getY())));
                                }
                            });
                        }
                    });
                }

                @Override // android.view.GestureDetector.OnGestureListener
                public boolean onScroll(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float f, final float f2) {
                    TKBase.this.dispatchEvent(TKBaseEvent.TK_PAN_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.2.3
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            iBaseEvent.setType(TKBaseEvent.TK_PAN_EVENT_NAME);
                            if (motionEvent.getAction() == 0) {
                                iBaseEvent.setState(1);
                            }
                            if (motionEvent2.getAction() == 1) {
                                iBaseEvent.setState(3);
                            } else if (motionEvent2.getAction() == 3) {
                                iBaseEvent.setState(4);
                            }
                            iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.2.3.1
                                {
                                    put("deltaX", Integer.valueOf(SizeUtil.px2Dp(f)));
                                    put("deltaY", Integer.valueOf(SizeUtil.px2Dp(f2)));
                                }
                            });
                        }
                    });
                    return true;
                }

                @Override // android.view.GestureDetector.OnGestureListener
                public void onShowPress(MotionEvent motionEvent) {
                }

                @Override // android.view.GestureDetector.OnGestureListener
                public boolean onSingleTapUp(final MotionEvent motionEvent) {
                    TKBase.this.dispatchEvent(TKBaseEvent.TK_CLICK_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.2.2
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            iBaseEvent.setType(TKBaseEvent.TK_CLICK_EVENT_NAME);
                            iBaseEvent.setState(GestureUtils.findStateInMotionEvent(motionEvent));
                            iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.2.2.1
                                {
                                    put("x", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getX())));
                                    put("y", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getY())));
                                }
                            });
                        }
                    });
                    return false;
                }
            });
            this.mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.tachikoma.core.component.TKBase.3
                @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
                public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                    final float scaleFactor = scaleGestureDetector.getScaleFactor();
                    TKBase.this.dispatchEvent(TKBaseEvent.TK_PINCH_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.3.1
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            iBaseEvent.setType(TKBaseEvent.TK_PINCH_EVENT_NAME);
                            iBaseEvent.setState(GestureUtils.findStateInMotionEvent(TKBase.this.mLatestMotionEvent));
                            iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.3.1.1
                                {
                                    put(AnimationProperty.SCALE, Float.valueOf(scaleFactor));
                                }
                            });
                        }
                    });
                    return true;
                }
            });
            this.mTargetView.setOnTouchListener(new View.OnTouchListener() { // from class: com.tachikoma.core.component.TKBase.4
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, final MotionEvent motionEvent) {
                    TKBase.this.mLatestMotionEvent = motionEvent;
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked == 1 || actionMasked == 3) {
                        TKBase.this.dispatchEvent("up", new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.4.1
                            @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                            public void callBackEvent(IBaseEvent iBaseEvent) {
                                iBaseEvent.setType("up");
                                iBaseEvent.setState(GestureUtils.findStateInMotionEvent(motionEvent));
                                iBaseEvent.configWithData(new HashMap<String, Object>() { // from class: com.tachikoma.core.component.TKBase.4.1.1
                                    {
                                        put("x", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getX())));
                                        put("y", Integer.valueOf(SizeUtil.px2Dp(motionEvent.getY())));
                                    }
                                });
                            }
                        });
                    }
                    TKBase.this.mScaleGestureDetector.onTouchEvent(motionEvent);
                    TKBase.this.mGestureDetector.onTouchEvent(motionEvent);
                    return true;
                }
            });
        }
    }

    private boolean isClick(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(TKBaseEvent.TK_CLICK_EVENT_NAME);
    }

    private boolean isGestureEvent(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(TKBaseEvent.TK_PAN_EVENT_NAME) || str.equals(TKBaseEvent.TK_SWIPE_EVENT_NAME) || str.equals(TKBaseEvent.TK_PINCH_EVENT_NAME) || str.equals("down") || str.equals("up") || str.equals(TKBaseEvent.TK_LONG_PRESS_EVENT_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Object> makeAnimationPropertySnapshot() {
        String str;
        int color;
        HashMap map = new HashMap();
        if (this.mTargetView == null) {
            return map;
        }
        map.put(AnimationProperty.TRANSLATE_X, Float.valueOf(this.mTargetView.getTranslationX()));
        map.put(AnimationProperty.TRANSLATE_Y, Float.valueOf(this.mTargetView.getTranslationY()));
        map.put(AnimationProperty.SCALE_X, Float.valueOf(this.mTargetView.getScaleX()));
        map.put(AnimationProperty.SCALE_Y, Float.valueOf(this.mTargetView.getScaleY()));
        map.put(AnimationProperty.ROTATE_X, Float.valueOf(this.mTargetView.getRotationX()));
        map.put(AnimationProperty.ROTATE_Y, Float.valueOf(this.mTargetView.getRotationY()));
        map.put("rotation", Float.valueOf(this.mTargetView.getRotation()));
        map.put(AnimationProperty.BOUNDS, this.mTargetView.getClipBounds());
        map.put(AnimationProperty.OPACITY, Float.valueOf(this.mTargetView.getAlpha()));
        Drawable background = this.mTargetView.getBackground();
        if (!(background instanceof TKViewBackgroundDrawable)) {
            if (background instanceof ColorDrawable) {
                str = AnimationProperty.BACKGROUND_COLOR;
                color = ((ColorDrawable) background).getColor();
            }
            if (getDomNode() != null && getDomNode().getYogaNode() != null) {
                map.put(AnimationProperty.WIDTH, getDomNode().getYogaNode().getWidth());
                map.put(AnimationProperty.HEIGHT, getDomNode().getYogaNode().getHeight());
                map.put(AnimationProperty.MARGIN, getDomNode().getYogaNode().getMargin(YogaEdge.ALL));
                map.put(AnimationProperty.MARGIN_LEFT, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.LEFT)));
                map.put(AnimationProperty.MARGIN_RIGHT, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.RIGHT)));
                map.put(AnimationProperty.MARGIN_TOP, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.TOP)));
                map.put(AnimationProperty.MARGIN_BOTTOM, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.BOTTOM)));
            }
            return map;
        }
        str = AnimationProperty.BACKGROUND_COLOR;
        color = ((TKViewBackgroundDrawable) background).getColor();
        map.put(str, Integer.valueOf(color));
        if (getDomNode() != null) {
            map.put(AnimationProperty.WIDTH, getDomNode().getYogaNode().getWidth());
            map.put(AnimationProperty.HEIGHT, getDomNode().getYogaNode().getHeight());
            map.put(AnimationProperty.MARGIN, getDomNode().getYogaNode().getMargin(YogaEdge.ALL));
            map.put(AnimationProperty.MARGIN_LEFT, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.LEFT)));
            map.put(AnimationProperty.MARGIN_RIGHT, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.RIGHT)));
            map.put(AnimationProperty.MARGIN_TOP, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.TOP)));
            map.put(AnimationProperty.MARGIN_BOTTOM, Float.valueOf(getDomNode().getYogaNode().getLayoutMargin(YogaEdge.BOTTOM)));
        }
        return map;
    }

    private void onStyleUpdated(HashMap map) {
        Tachikoma.getInstance().getTKExportManager().applyAttributes(getClass().getName(), this, map);
    }

    private void registerPlatformEventListener(String str) {
        if (this.mTargetView == null) {
            return;
        }
        if (isGestureEvent(str)) {
            initViewGestureEvent();
        } else {
            if (!isClick(str) || this.isClickInit) {
                return;
            }
            if (TKBaseEvent.TK_CLICK_EVENT_NAME.equals(str)) {
                this.mTargetView.setOnClickListener(new View.OnClickListener() { // from class: com.tachikoma.core.component.TKBase.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (TKBase.this.mGestureDetector != null) {
                            return;
                        }
                        TKBase.this.dispatchEvent(TKBaseEvent.TK_CLICK_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.TKBase.1.1
                            @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                            public void callBackEvent(IBaseEvent iBaseEvent) {
                                if (iBaseEvent instanceof TKTapEvent) {
                                    TKTapEvent tKTapEvent = (TKTapEvent) iBaseEvent;
                                    tKTapEvent.setType(TKBaseEvent.TK_CLICK_EVENT_NAME);
                                    tKTapEvent.setPosition(new HashMap<String, Float>() { // from class: com.tachikoma.core.component.TKBase.1.1.1
                                        {
                                            put("x", Float.valueOf(0.0f));
                                            put("y", Float.valueOf(0.0f));
                                        }
                                    });
                                    tKTapEvent.setState(GestureUtils.findStateInMotionEvent(null));
                                }
                            }
                        });
                    }
                });
            }
            this.isClickInit = true;
        }
    }

    private int safelyGetPxValue(HashMap map, String str) {
        if (map != null && !TextUtils.isEmpty(str)) {
            try {
                if (map.containsKey(str)) {
                    return SizeUtil.dp2Px(((Number) map.get(str)).intValue());
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    private void setTransformProperty(Map map) {
        Number number;
        float fDp2px;
        if (map == null || map.size() <= 0) {
            return;
        }
        Object obj = map.get(AnimationProperty.SCALE);
        if (obj instanceof Map) {
            Map map2 = (Map) obj;
            Object obj2 = map2.get("x");
            if (obj2 instanceof Number) {
                setScaleX(((Number) obj2).floatValue());
            }
            Object obj3 = map2.get("y");
            if (obj3 instanceof Number) {
                number = (Number) obj3;
                setScaleY(number.floatValue());
            }
        } else if (obj instanceof Number) {
            number = (Number) obj;
            setScaleX(number.floatValue());
            setScaleY(number.floatValue());
        }
        Object obj4 = map.get(AnimationProperty.TRANSLATE);
        if (obj4 instanceof Map) {
            Map map3 = (Map) obj4;
            Object obj5 = map3.get("x");
            if (obj5 instanceof Number) {
                setTranslateX(TKConverter.dp2px(getContext(), ((Number) obj5).floatValue()));
            }
            Object obj6 = map3.get("y");
            if (obj6 instanceof Number) {
                fDp2px = TKConverter.dp2px(getContext(), ((Number) obj6).floatValue());
                setTranslateY(fDp2px);
            }
        } else if (obj4 instanceof Number) {
            fDp2px = TKConverter.dp2px(getContext(), ((Number) obj4).floatValue());
            setTranslateX(fDp2px);
            setTranslateY(fDp2px);
        }
        Object obj7 = map.get(AnimationProperty.ROTATE);
        if (obj7 instanceof Map) {
            Map map4 = (Map) obj7;
            Object obj8 = map4.get("x");
            if (obj8 instanceof Number) {
                setRotateX(((Number) obj8).floatValue());
            }
            Object obj9 = map4.get("y");
            if (obj9 instanceof Number) {
                setRotateY(((Number) obj9).floatValue());
            }
            obj7 = map4.get("z");
            if (!(obj7 instanceof Number)) {
                return;
            }
        } else if (!(obj7 instanceof Number)) {
            return;
        }
        setRotate(((Number) obj7).floatValue());
    }

    public void addAnimation(V8Object v8Object, String str) {
        TKBasicAnimation tKBasicAnimation;
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        TKBasicAnimation tKBasicAnimation2 = (TKBasicAnimation) associateReference.nativeObject;
        if (this.mAnimationMap.containsKey(str) && (tKBasicAnimation = this.mAnimationMap.get(str)) != null) {
            tKBasicAnimation.stop(this);
        }
        if (tKBasicAnimation2 == null) {
            Logger.logE("addAnimation", new NullPointerException("animation is null!!!"));
            associateReference.close();
        } else if (this.mTargetView == null) {
            Logger.logE("addAnimation", new IllegalStateException("tagetView is null!!!"));
            associateReference.close();
        } else {
            executeAnimationJsFunction(tKBasicAnimation2);
            tKBasicAnimation2.start(this);
            this.mAnimationMap.put(str, tKBasicAnimation2);
            this.associateAnimationRefList.add(associateReference);
        }
    }

    public void addEventListener(String str, V8Function v8Function) {
        if (!str.isEmpty() && v8Function != null) {
            this.eventListener.put(str, v8Function.twin());
        }
        V8Proxy.release(v8Function);
        registerPlatformEventListener(str);
    }

    @Deprecated
    public void applyAttributes(V8Object v8Object) {
        V8Object v8Object2 = (V8Object) v8Object.get("style");
        if (v8Object2 != null && !v8Object2.isUndefined()) {
            setStyle((HashMap) V8ObjectUtils.toMap(v8Object2));
        }
        Tachikoma.getInstance().getTKExportManager().applyProperties(getClass().getName(), this, new HashMap<>(V8ObjectUtils.toMap(v8Object)));
        V8Proxy.release(v8Object);
    }

    public void bindV8This(V8Object v8Object) {
        if (this.mV8This != null) {
            this.mV8This.close();
        }
        this.mV8This = v8Object.twin();
        this.mV8This.setWeak();
    }

    protected abstract T createViewInstance(Context context);

    public final void destroy() {
        onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dispatchEvent(String str, TKEventProcess.EventInterface eventInterface) {
        V8Function v8Function = this.eventListener.get(str);
        if (v8Function == null || v8Function.isReleased()) {
            return;
        }
        TKEventProcess.processEventCallBack(v8Function, str, this.mTKJSContext.context(), getContext(), eventInterface);
    }

    public HashMap<Float, Map<String, Object>> getAnimationPropertySnapshot() {
        return this.mAnimationPropertySnapshot;
    }

    public Context getContext() {
        return getView().getContext();
    }

    public TKDomNode getDomNode() {
        return this.mNode;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public V8Object getOrigin() {
        V8Object v8ObjectMakeObject = this.mTKJSContext.context().makeObject();
        if (getView() != null) {
            try {
                v8ObjectMakeObject.add("x", String.valueOf(SizeUtil.px2Dp((int) getView().getX())));
                v8ObjectMakeObject.add("y", String.valueOf(SizeUtil.px2Dp((int) getView().getY())));
            } catch (Exception unused) {
            }
        }
        return v8ObjectMakeObject;
    }

    protected String getRootDir() {
        return this.mTKJSContext.getRootDir();
    }

    public V8Object getSize() {
        V8Object v8ObjectMakeObject = this.mTKJSContext.context().makeObject();
        if (getView() != null) {
            try {
                v8ObjectMakeObject.add(AnimationProperty.WIDTH, String.valueOf(SizeUtil.px2Dp(getView().getWidth())));
                v8ObjectMakeObject.add(AnimationProperty.HEIGHT, String.valueOf(SizeUtil.px2Dp(getView().getHeight())));
            } catch (Exception unused) {
            }
        }
        return v8ObjectMakeObject;
    }

    public T getView() {
        return this.mTargetView;
    }

    public ViewBackgroundManager getViewBackgroundManager() {
        return this.viewBackgroundManager;
    }

    public String getViewID() {
        return getDomNode().nodeID;
    }

    public Object invokeJSFunctionWithJSONString(String str, String str2, JavaCallback javaCallback) throws Throwable {
        V8Function v8Function;
        if (!TKEnv.isDebug() && (this.mTKJSContext == null || this.mTKJSContext.context() == null)) {
            return null;
        }
        V8Array v8ArrayMakeV8Array = this.mTKJSContext.context().makeV8Array();
        try {
            try {
                v8Function = new V8Function(this.mTKJSContext.context().getContextRef(), javaCallback);
                try {
                    v8ArrayMakeV8Array.push(str2);
                    v8ArrayMakeV8Array.push(v8Function);
                    Object value = V8ObjectUtils.getValue(this.mV8This.executeFunction(str, v8ArrayMakeV8Array));
                    V8Proxy.release(v8ArrayMakeV8Array);
                    V8Proxy.release(v8Function);
                    return value;
                } catch (Throwable th) {
                    th = th;
                    TKExceptionDispatcher.reportException(this.mTKJSContext, th);
                    V8Proxy.release(v8ArrayMakeV8Array);
                    V8Proxy.release(v8Function);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                V8Proxy.release(v8ArrayMakeV8Array);
                V8Proxy.release(null);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            v8Function = null;
        }
    }

    protected void maybePlayLottieAnimation() {
        LottieTask lottieTaskFromJsonInputStream;
        if (TextUtils.isEmpty(this.lottieRes)) {
            lottieTaskFromJsonInputStream = null;
        } else {
            lottieTaskFromJsonInputStream = LottieCompositionFactory.fromAsset(getContext(), this.lottieRes + ".json");
        }
        if (!TextUtils.isEmpty(this.lottiePath)) {
            if (this.lottiePath.startsWith(Constants.HTTP)) {
                lottieTaskFromJsonInputStream = LottieCompositionFactory.fromUrl(getContext(), this.lottiePath);
            } else {
                try {
                    File file = new File(getRootDir().concat(this.lottiePath));
                    if (file.exists()) {
                        lottieTaskFromJsonInputStream = LottieCompositionFactory.fromJsonInputStream(new FileInputStream(file), this.lottiePath);
                    }
                } catch (Exception e) {
                    Logger.logE("load lottie from file failed", e);
                }
            }
        }
        if (lottieTaskFromJsonInputStream != null) {
            lottieTaskFromJsonInputStream.addFailureListener(new LottieListener<Throwable>() { // from class: com.tachikoma.core.component.TKBase.6
                public void onResult(Throwable th) {
                    if (TKEnv.isDebug(TKBase.this.mTKJSContext)) {
                        System.out.println("Tachikoma LottieTask Failed. " + Log.getStackTraceString(th));
                    }
                }
            });
            lottieTaskFromJsonInputStream.addListener(new LottieListener<LottieComposition>() { // from class: com.tachikoma.core.component.TKBase.7
                public void onResult(LottieComposition lottieComposition) {
                    TKBase.this.maybeSetComposition(lottieComposition);
                }
            });
        }
    }

    protected void maybeSetComposition(LottieComposition lottieComposition) {
        if (lottieComposition == null || getView() == null) {
            return;
        }
        Drawable lottieDrawable = new LottieDrawable();
        lottieDrawable.setComposition(lottieComposition);
        getView().setBackground(lottieDrawable);
    }

    public void onAttach() {
        maybePlayLottieAnimation();
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    @CallSuper
    public void onCreate() {
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    @CallSuper
    public void onDestroy() {
        if (this.mTargetView != null && !(this.mTargetView instanceof ListView)) {
            this.mTargetView.setOnClickListener(null);
            this.mTargetView.setTag(null);
            this.mTargetView = null;
        }
        this.mGestureDetector = null;
        Iterator<Map.Entry<String, V8Function>> it = this.eventListener.entrySet().iterator();
        while (it.hasNext()) {
            V8Proxy.release(it.next().getValue());
        }
        this.eventListener.clear();
        if (this.mV8This != null) {
            this.mV8This.close();
            this.mV8This = null;
        }
        if (this.mNode != null) {
            this.mNode.reset();
            this.mNode = null;
        }
        this.mTKJSContext.context().removeAssociateObject(this);
        destroyAnimation();
        removeAllAnimation();
        this.viewBackgroundManager.destroy();
    }

    public Object onJSInvokeNativeFunctionWithJSONString(String str, String str2, V8Function v8Function) {
        if (this.mIJS2NativeInvoker == null) {
            return null;
        }
        Object objOnJSInvokeNativeWithJSONString = this.mIJS2NativeInvoker.onJSInvokeNativeWithJSONString(str, str2, new V8FunctionImpl(v8Function, this.mTKJSContext));
        return objOnJSInvokeNativeWithJSONString instanceof V8Value ? objOnJSInvokeNativeWithJSONString : V8ObjectUtils.getV8Result(this.mTKJSContext.context().getContextRef(), objOnJSInvokeNativeWithJSONString);
    }

    protected void onShadowSet(float f) {
    }

    public void pauseAnimations() {
        if (this.mAnimationMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, TKBasicAnimation> entry : this.mAnimationMap.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                entry.getValue().pauseAnimation(this);
            }
        }
    }

    public void removeAllAnimation() {
        Iterator<Map.Entry<String, TKBasicAnimation>> it = this.mAnimationMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().stop(this);
            it.remove();
        }
        Iterator<JSContext.V8AssociateReference> it2 = this.associateAnimationRefList.iterator();
        while (it2.hasNext()) {
            it2.next().close();
        }
        this.associateAnimationRefList.clear();
    }

    public void removeAnimation(String str) {
        TKBasicAnimation tKBasicAnimation;
        if (TextUtils.isEmpty(str) || (tKBasicAnimation = this.mAnimationMap.get(str)) == null) {
            return;
        }
        tKBasicAnimation.stop(this);
        this.mAnimationMap.remove(str);
        JSContext.V8AssociateReference.closeByNative(tKBasicAnimation, this.associateAnimationRefList.iterator());
    }

    public void removeEventListener(String str) {
        V8Function v8Function = this.eventListener.get(str);
        if (v8Function != null) {
            V8Proxy.release(v8Function);
            this.eventListener.remove(str);
        }
    }

    public void resumeAnimations() {
        if (this.mAnimationMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, TKBasicAnimation> entry : this.mAnimationMap.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                entry.getValue().resumeAnimation(this);
            }
        }
    }

    public void setBackgroundColor(Object obj) {
        if (obj instanceof String) {
            this.mBackgroundColor = (String) obj;
            this.viewBackgroundManager.setBackgroundColor(Color.parseColor(TKColorUtil.rgba2argb(this.mBackgroundColor)));
        }
    }

    public void setBackgroundColorInt(@ColorInt int i) {
        this.viewBackgroundManager.setBackgroundColor(i);
    }

    public void setBackgroundImage(String str) {
        this.viewBackgroundManager.setBackgroundImage(str);
    }

    public void setBorderColor(String str) {
        String strRgba2argb = TKColorUtil.rgba2argb(str);
        this.viewBackgroundManager.setBorderColor(8, Color.parseColor(strRgba2argb), r0 >>> 24);
        this.viewBackgroundManager.setBorderColor(strRgba2argb);
    }

    public void setBorderRadius(int i) {
        this.mBorderRadius = TKConverter.dp2px(Tachikoma.sApplication, i);
        this.viewBackgroundManager.setBorderRadius(this.mBorderRadius);
    }

    public void setBorderStyle(String str) {
        if (str == null) {
            return;
        }
        this.viewBackgroundManager.setBorderStyle(str);
    }

    public void setBorderWidth(double d) {
        this.viewBackgroundManager.setBorderWidth(8, TKConverter.dp2px(Tachikoma.sApplication, (float) d));
    }

    public void setBottomLeftRadius(int i) {
        this.viewBackgroundManager.setBorderRadius(TKConverter.dp2px(Tachikoma.sApplication, i), TKViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_LEFT.ordinal());
    }

    public void setBottomRightRadius(int i) {
        this.viewBackgroundManager.setBorderRadius(TKConverter.dp2px(Tachikoma.sApplication, i), TKViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_RIGHT.ordinal());
    }

    public void setData(Object... objArr) throws Throwable {
        V8Array v8ArrayMakeV8Array;
        Object v8Value;
        Object obj = null;
        if (this.mV8This == null || this.mV8This.isReleased()) {
            if (TKEnv.isDebug(this.mTKJSContext)) {
                throw new IllegalStateException("未找到 TKBase 对应的 js object handler，setData 只对 jsContext.createView 创建的 view 有效");
            }
            Logger.logE("未找到 TKBase 对应的 js object handler，setData 只对 jsContext.createView 创建的 view 有效", null);
            return;
        }
        try {
            v8ArrayMakeV8Array = this.mTKJSContext.context().makeV8Array();
            int i = 0;
            while (i < objArr.length) {
                try {
                    v8Value = TKConverter.toV8Value(objArr[i], this.mTKJSContext.context());
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    v8ArrayMakeV8Array.push(v8Value);
                    i++;
                    obj = v8Value;
                } catch (Throwable th2) {
                    th = th2;
                    obj = v8Value;
                    V8Proxy.release(v8ArrayMakeV8Array);
                    if (obj instanceof V8Value) {
                        V8Proxy.release((V8Value) obj);
                    }
                    throw th;
                }
            }
            this.mV8This.executeVoidFunction("setData", v8ArrayMakeV8Array);
            V8Proxy.release(v8ArrayMakeV8Array);
            if (obj instanceof V8Value) {
                V8Proxy.release((V8Value) obj);
            }
        } catch (Throwable th3) {
            th = th3;
            v8ArrayMakeV8Array = null;
        }
    }

    public void setDisplay(String str) {
        getView().setVisibility("none".equals(str) ? 8 : 0);
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
        this.mTargetView.setEnabled(this.enabled);
    }

    public void setGradientBgColor(String str) throws NumberFormatException {
        String[] strArrSplit;
        if (TextUtils.isEmpty(str) || (strArrSplit = str.split(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP)) == null || strArrSplit.length < 3) {
            return;
        }
        try {
            int i = Integer.parseInt(strArrSplit[0].trim());
            int[] iArr = new int[strArrSplit.length - 1];
            float[] fArr = new float[strArrSplit.length - 1];
            boolean z = false;
            for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                String strTrim = strArrSplit[i2].trim();
                if (strTrim.contains(" ")) {
                    String[] strArrSplit2 = strTrim.split(" ");
                    int i3 = i2 - 1;
                    iArr[i3] = Color.parseColor(TKColorUtil.rgba2argb(strArrSplit2[0].trim()));
                    fArr[i3] = Float.parseFloat(strArrSplit2[1]);
                    z = true;
                } else {
                    iArr[i2 - 1] = Color.parseColor(TKColorUtil.rgba2argb(strTrim));
                }
            }
            if (z) {
                this.viewBackgroundManager.setBackgroundGradientColor(i, iArr, fArr);
            } else {
                this.viewBackgroundManager.setBackgroundGradientColor(i, iArr, null);
            }
        } catch (Exception unused) {
        }
    }

    public void setIJS2NativeInvoker(IJS2NativeInvoker iJS2NativeInvoker) {
        this.mIJS2NativeInvoker = iJS2NativeInvoker;
    }

    public void setOpacity(double d) {
        getView().setAlpha((float) d);
    }

    public void setRotate(float f) {
        getView().setRotation(f);
    }

    public void setRotateX(float f) {
        getView().setRotationX(f);
    }

    public void setRotateY(float f) {
        getView().setRotationY(f);
    }

    public void setScaleX(float f) {
        getView().setScaleX(f);
    }

    public void setScaleY(float f) {
        getView().setScaleY(f);
    }

    public void setShadow(String str) {
        String[] strArrSplit = str.split(" ");
        if (strArrSplit.length != 4) {
            return;
        }
        float[] fArr = new float[3];
        for (int i = 0; i < 3; i++) {
            if (strArrSplit[i].toLowerCase().contains("px")) {
                fArr[i] = Float.parseFloat(strArrSplit[i].replace("px", ""));
            } else {
                fArr[i] = TKConverter.dp2px(getContext(), Float.parseFloat(strArrSplit[i]));
            }
        }
        this.viewBackgroundManager.setShadow(fArr[2], fArr[0], fArr[1], Color.parseColor(TKColorUtil.rgba2argb(strArrSplit[3])));
        onShadowSet(fArr[2]);
    }

    public void setStyle(HashMap map) {
        this.style = map;
        if (this.mTargetView != null) {
            getDomNode().setDomStyle(this.style);
        }
        onStyleUpdated(this.style);
        Object obj = map.get(AnimationProperty.TRANSFORM);
        if (obj instanceof Map) {
            setTransformProperty((Map) obj);
        }
    }

    public void setTKJSContext(TKJSContext tKJSContext) {
        this.mTKJSContext = tKJSContext;
    }

    public void setTopLeftRadius(int i) {
        this.viewBackgroundManager.setBorderRadius(TKConverter.dp2px(Tachikoma.sApplication, i), TKViewBackgroundDrawable.BorderRadiusLocation.TOP_LEFT.ordinal());
    }

    public void setTopRightRadius(int i) {
        this.viewBackgroundManager.setBorderRadius(TKConverter.dp2px(Tachikoma.sApplication, i), TKViewBackgroundDrawable.BorderRadiusLocation.TOP_RIGHT.ordinal());
    }

    public void setTranslateX(float f) {
        getView().setTranslationX(f);
    }

    public void setTranslateY(float f) {
        getView().setTranslationY(f);
    }

    public void setViewID(String str) {
        this.viewID = str;
        getDomNode().nodeID = str;
    }

    public void setVisibility(String str) {
        getView().setVisibility(VISIBILITY_HIDDEN.equals(str) ? 4 : 0);
    }

    public void startViewAnimation(String str, long j, float f, float f2) {
        new CustomViewPropertyAnimator(this, str, j, f, f2).start();
    }

    protected void takeControlOfPaddingSet(HashMap map) {
        getView().setPadding(safelyGetPxValue(map, "paddingLeft"), safelyGetPxValue(map, "paddingTop"), safelyGetPxValue(map, "paddingRight"), safelyGetPxValue(map, "paddingBottom"));
        if (map != null) {
            map.remove("paddingLeft");
            map.remove("paddingRight");
            map.remove("paddingTop");
            map.remove("paddingBottom");
        }
    }
}
