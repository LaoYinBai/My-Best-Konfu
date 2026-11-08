package com.tachikoma.core.yoga.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.yoga.YogaAlign;
import com.kwad.yoga.YogaDirection;
import com.kwad.yoga.YogaDisplay;
import com.kwad.yoga.YogaEdge;
import com.kwad.yoga.YogaFlexDirection;
import com.kwad.yoga.YogaJustify;
import com.kwad.yoga.YogaMeasureFunction;
import com.kwad.yoga.YogaMeasureMode;
import com.kwad.yoga.YogaMeasureOutput;
import com.kwad.yoga.YogaNode;
import com.kwad.yoga.YogaNodeJNIFinalizer;
import com.kwad.yoga.YogaOverflow;
import com.kwad.yoga.YogaPositionType;
import com.kwad.yoga.YogaWrap;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class YogaLayout extends ViewGroup {
    private final YogaNode mYogaNode;
    private final Map<View, YogaNode> mYogaNodes;

    public static class LayoutParams extends ViewGroup.LayoutParams {
        SparseArray<Float> numericAttributes;
        SparseArray<String> stringAttributes;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            if (i >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf(i));
            }
            if (i2 >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf(i2));
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            SparseArray sparseArray;
            float dimensionPixelSize;
            Object string;
            super(context, attributeSet);
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.yoga);
            if (this.width >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf(this.width));
            }
            if (this.height >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf(this.height));
            }
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                TypedValue typedValue = new TypedValue();
                typedArrayObtainStyledAttributes.getValue(index, typedValue);
                if (typedValue.type == 5) {
                    sparseArray = this.numericAttributes;
                    dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (typedValue.type == 3) {
                    sparseArray = this.stringAttributes;
                    string = typedArrayObtainStyledAttributes.getString(index);
                    sparseArray.put(index, string);
                } else {
                    sparseArray = this.numericAttributes;
                    dimensionPixelSize = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                }
                string = Float.valueOf(dimensionPixelSize);
                sparseArray.put(index, string);
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.numericAttributes = layoutParams2.numericAttributes.clone();
                this.stringAttributes = layoutParams2.stringAttributes.clone();
                return;
            }
            this.numericAttributes = new SparseArray<>();
            this.stringAttributes = new SparseArray<>();
            if (layoutParams.width >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf(this.width));
            }
            if (layoutParams.height >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf(this.height));
            }
        }
    }

    public static class ViewMeasureFunction implements YogaMeasureFunction {
        private int viewMeasureSpecFromYogaMeasureMode(YogaMeasureMode yogaMeasureMode) {
            return yogaMeasureMode == YogaMeasureMode.AT_MOST ? Target.SIZE_ORIGINAL : yogaMeasureMode == YogaMeasureMode.EXACTLY ? 1073741824 : 0;
        }

        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            View view = (View) yogaNode.getData();
            if (view == null || (view instanceof YogaLayout)) {
                return YogaMeasureOutput.make(0, 0);
            }
            view.measure(View.MeasureSpec.makeMeasureSpec((int) f, viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode)), View.MeasureSpec.makeMeasureSpec((int) f2, viewMeasureSpecFromYogaMeasureMode(yogaMeasureMode2)));
            return YogaMeasureOutput.make(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public YogaLayout(Context context) {
        this(context, null, 0);
    }

    public YogaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YogaLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mYogaNode = new YogaNodeJNIFinalizer();
        this.mYogaNodes = new HashMap();
        this.mYogaNode.setData(this);
        this.mYogaNode.setMeasureFunction(new ViewMeasureFunction());
        applyLayoutParams(attributeSet != null ? new LayoutParams(context, attributeSet) : (LayoutParams) generateDefaultLayoutParams(), this.mYogaNode, this);
    }

    protected static void applyLayoutParams(LayoutParams layoutParams, YogaNode yogaNode, View view) {
        YogaEdge yogaEdge;
        YogaEdge yogaEdge2;
        YogaEdge yogaEdge3;
        YogaEdge yogaEdge4;
        YogaEdge yogaEdge5;
        YogaEdge yogaEdge6;
        YogaEdge yogaEdge7;
        YogaEdge yogaEdge8;
        if (Build.VERSION.SDK_INT >= 17 && view.getResources().getConfiguration().getLayoutDirection() == 1) {
            yogaNode.setDirection(YogaDirection.RTL);
        }
        Drawable background = view.getBackground();
        if (background != null) {
            if (background.getPadding(new Rect())) {
                yogaNode.setPadding(YogaEdge.LEFT, r0.left);
                yogaNode.setPadding(YogaEdge.TOP, r0.top);
                yogaNode.setPadding(YogaEdge.RIGHT, r0.right);
                yogaNode.setPadding(YogaEdge.BOTTOM, r0.bottom);
            }
        }
        for (int i = 0; i < layoutParams.numericAttributes.size(); i++) {
            int iKeyAt = layoutParams.numericAttributes.keyAt(i);
            float fFloatValue = layoutParams.numericAttributes.valueAt(i).floatValue();
            if (iKeyAt == R.styleable.yoga_yg_alignContent) {
                yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(fFloatValue)));
            } else if (iKeyAt == R.styleable.yoga_yg_alignItems) {
                yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(fFloatValue)));
            } else if (iKeyAt == R.styleable.yoga_yg_alignSelf) {
                yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(fFloatValue)));
            } else if (iKeyAt == R.styleable.yoga_yg_aspectRatio) {
                yogaNode.setAspectRatio(fFloatValue);
            } else {
                if (iKeyAt == R.styleable.yoga_yg_borderLeft) {
                    yogaEdge8 = YogaEdge.LEFT;
                } else if (iKeyAt == R.styleable.yoga_yg_borderTop) {
                    yogaEdge8 = YogaEdge.TOP;
                } else if (iKeyAt == R.styleable.yoga_yg_borderRight) {
                    yogaEdge8 = YogaEdge.RIGHT;
                } else if (iKeyAt == R.styleable.yoga_yg_borderBottom) {
                    yogaEdge8 = YogaEdge.BOTTOM;
                } else if (iKeyAt == R.styleable.yoga_yg_borderStart) {
                    yogaEdge8 = YogaEdge.START;
                } else if (iKeyAt == R.styleable.yoga_yg_borderEnd) {
                    yogaEdge8 = YogaEdge.END;
                } else if (iKeyAt == R.styleable.yoga_yg_borderHorizontal) {
                    yogaEdge8 = YogaEdge.HORIZONTAL;
                } else if (iKeyAt == R.styleable.yoga_yg_borderVertical) {
                    yogaEdge8 = YogaEdge.VERTICAL;
                } else if (iKeyAt == R.styleable.yoga_yg_borderAll) {
                    yogaEdge8 = YogaEdge.ALL;
                } else if (iKeyAt == R.styleable.yoga_yg_direction) {
                    yogaNode.setDirection(YogaDirection.fromInt(Math.round(fFloatValue)));
                } else if (iKeyAt == R.styleable.yoga_yg_display) {
                    yogaNode.setDisplay(YogaDisplay.fromInt(Math.round(fFloatValue)));
                } else if (iKeyAt == R.styleable.yoga_yg_flex) {
                    yogaNode.setFlex(fFloatValue);
                } else if (iKeyAt == R.styleable.yoga_yg_flexBasis) {
                    yogaNode.setFlexBasis(fFloatValue);
                } else if (iKeyAt == R.styleable.yoga_yg_flexDirection) {
                    yogaNode.setFlexDirection(YogaFlexDirection.fromInt(Math.round(fFloatValue)));
                } else if (iKeyAt == R.styleable.yoga_yg_flexGrow) {
                    yogaNode.setFlexGrow(fFloatValue);
                } else if (iKeyAt == R.styleable.yoga_yg_flexShrink) {
                    yogaNode.setFlexShrink(fFloatValue);
                } else if (iKeyAt == R.styleable.yoga_yg_height) {
                    yogaNode.setHeight(fFloatValue);
                } else {
                    if (iKeyAt == R.styleable.yoga_yg_marginLeft) {
                        yogaEdge7 = YogaEdge.LEFT;
                    } else if (iKeyAt == R.styleable.yoga_yg_justifyContent) {
                        yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(fFloatValue)));
                    } else if (iKeyAt == R.styleable.yoga_yg_marginTop) {
                        yogaEdge7 = YogaEdge.TOP;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginRight) {
                        yogaEdge7 = YogaEdge.RIGHT;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginBottom) {
                        yogaEdge7 = YogaEdge.BOTTOM;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginStart) {
                        yogaEdge7 = YogaEdge.START;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginEnd) {
                        yogaEdge7 = YogaEdge.END;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginHorizontal) {
                        yogaEdge7 = YogaEdge.HORIZONTAL;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginVertical) {
                        yogaEdge7 = YogaEdge.VERTICAL;
                    } else if (iKeyAt == R.styleable.yoga_yg_marginAll) {
                        yogaEdge7 = YogaEdge.ALL;
                    } else if (iKeyAt == R.styleable.yoga_yg_maxHeight) {
                        yogaNode.setMaxHeight(fFloatValue);
                    } else if (iKeyAt == R.styleable.yoga_yg_maxWidth) {
                        yogaNode.setMaxWidth(fFloatValue);
                    } else if (iKeyAt == R.styleable.yoga_yg_minHeight) {
                        yogaNode.setMinHeight(fFloatValue);
                    } else if (iKeyAt == R.styleable.yoga_yg_minWidth) {
                        yogaNode.setMinWidth(fFloatValue);
                    } else if (iKeyAt == R.styleable.yoga_yg_overflow) {
                        yogaNode.setOverflow(YogaOverflow.fromInt(Math.round(fFloatValue)));
                    } else {
                        if (iKeyAt == R.styleable.yoga_yg_paddingLeft) {
                            yogaEdge6 = YogaEdge.LEFT;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingTop) {
                            yogaEdge6 = YogaEdge.TOP;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingRight) {
                            yogaEdge6 = YogaEdge.RIGHT;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingBottom) {
                            yogaEdge6 = YogaEdge.BOTTOM;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingStart) {
                            yogaEdge6 = YogaEdge.START;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingEnd) {
                            yogaEdge6 = YogaEdge.END;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingHorizontal) {
                            yogaEdge6 = YogaEdge.HORIZONTAL;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingVertical) {
                            yogaEdge6 = YogaEdge.VERTICAL;
                        } else if (iKeyAt == R.styleable.yoga_yg_paddingAll) {
                            yogaEdge6 = YogaEdge.ALL;
                        } else {
                            if (iKeyAt == R.styleable.yoga_yg_positionLeft) {
                                yogaEdge5 = YogaEdge.LEFT;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionTop) {
                                yogaEdge5 = YogaEdge.TOP;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionRight) {
                                yogaEdge5 = YogaEdge.RIGHT;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionBottom) {
                                yogaEdge5 = YogaEdge.BOTTOM;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionStart) {
                                yogaEdge5 = YogaEdge.START;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionEnd) {
                                yogaEdge5 = YogaEdge.END;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionHorizontal) {
                                yogaEdge5 = YogaEdge.HORIZONTAL;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionVertical) {
                                yogaEdge5 = YogaEdge.VERTICAL;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionAll) {
                                yogaEdge5 = YogaEdge.ALL;
                            } else if (iKeyAt == R.styleable.yoga_yg_positionType) {
                                yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(fFloatValue)));
                            } else if (iKeyAt == R.styleable.yoga_yg_width) {
                                yogaNode.setWidth(fFloatValue);
                            } else if (iKeyAt == R.styleable.yoga_yg_wrap) {
                                yogaNode.setWrap(YogaWrap.fromInt(Math.round(fFloatValue)));
                            }
                            yogaNode.setPosition(yogaEdge5, fFloatValue);
                        }
                        yogaNode.setPadding(yogaEdge6, fFloatValue);
                    }
                    yogaNode.setMargin(yogaEdge7, fFloatValue);
                }
                yogaNode.setBorder(yogaEdge8, fFloatValue);
            }
        }
        for (int i2 = 0; i2 < layoutParams.stringAttributes.size(); i2++) {
            int iKeyAt2 = layoutParams.stringAttributes.keyAt(i2);
            String strValueAt = layoutParams.stringAttributes.valueAt(i2);
            if (strValueAt.equals("auto")) {
                if (iKeyAt2 == R.styleable.yoga_yg_marginLeft) {
                    yogaEdge4 = YogaEdge.LEFT;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginTop) {
                    yogaEdge4 = YogaEdge.TOP;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginRight) {
                    yogaEdge4 = YogaEdge.RIGHT;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginBottom) {
                    yogaEdge4 = YogaEdge.BOTTOM;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginStart) {
                    yogaEdge4 = YogaEdge.START;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginEnd) {
                    yogaEdge4 = YogaEdge.END;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginHorizontal) {
                    yogaEdge4 = YogaEdge.HORIZONTAL;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginVertical) {
                    yogaEdge4 = YogaEdge.VERTICAL;
                } else if (iKeyAt2 == R.styleable.yoga_yg_marginAll) {
                    yogaEdge4 = YogaEdge.ALL;
                }
                yogaNode.setMarginAuto(yogaEdge4);
            }
            if (strValueAt.endsWith("%")) {
                float f = Float.parseFloat(strValueAt.substring(0, strValueAt.length() - 1));
                if (iKeyAt2 == R.styleable.yoga_yg_flexBasis) {
                    yogaNode.setFlexBasisPercent(f);
                } else if (iKeyAt2 == R.styleable.yoga_yg_height) {
                    yogaNode.setHeightPercent(f);
                } else {
                    if (iKeyAt2 == R.styleable.yoga_yg_marginLeft) {
                        yogaEdge3 = YogaEdge.LEFT;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginTop) {
                        yogaEdge3 = YogaEdge.TOP;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginRight) {
                        yogaEdge3 = YogaEdge.RIGHT;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginBottom) {
                        yogaEdge3 = YogaEdge.BOTTOM;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginStart) {
                        yogaEdge3 = YogaEdge.START;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginEnd) {
                        yogaEdge3 = YogaEdge.END;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginHorizontal) {
                        yogaEdge3 = YogaEdge.HORIZONTAL;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginVertical) {
                        yogaEdge3 = YogaEdge.VERTICAL;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_marginAll) {
                        yogaEdge3 = YogaEdge.ALL;
                    } else if (iKeyAt2 == R.styleable.yoga_yg_maxHeight) {
                        yogaNode.setMaxHeightPercent(f);
                    } else if (iKeyAt2 == R.styleable.yoga_yg_maxWidth) {
                        yogaNode.setMaxWidthPercent(f);
                    } else if (iKeyAt2 == R.styleable.yoga_yg_minHeight) {
                        yogaNode.setMinHeightPercent(f);
                    } else if (iKeyAt2 == R.styleable.yoga_yg_minWidth) {
                        yogaNode.setMinWidthPercent(f);
                    } else {
                        if (iKeyAt2 == R.styleable.yoga_yg_paddingLeft) {
                            yogaEdge2 = YogaEdge.LEFT;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingTop) {
                            yogaEdge2 = YogaEdge.TOP;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingRight) {
                            yogaEdge2 = YogaEdge.RIGHT;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingBottom) {
                            yogaEdge2 = YogaEdge.BOTTOM;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingStart) {
                            yogaEdge2 = YogaEdge.START;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingEnd) {
                            yogaEdge2 = YogaEdge.END;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingHorizontal) {
                            yogaEdge2 = YogaEdge.HORIZONTAL;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingVertical) {
                            yogaEdge2 = YogaEdge.VERTICAL;
                        } else if (iKeyAt2 == R.styleable.yoga_yg_paddingAll) {
                            yogaEdge2 = YogaEdge.ALL;
                        } else {
                            if (iKeyAt2 == R.styleable.yoga_yg_positionLeft) {
                                yogaEdge = YogaEdge.LEFT;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionTop) {
                                yogaEdge = YogaEdge.TOP;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionRight) {
                                yogaEdge = YogaEdge.RIGHT;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionBottom) {
                                yogaEdge = YogaEdge.BOTTOM;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionStart) {
                                yogaEdge = YogaEdge.START;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionEnd) {
                                yogaEdge = YogaEdge.END;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionHorizontal) {
                                yogaEdge = YogaEdge.HORIZONTAL;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionVertical) {
                                yogaEdge = YogaEdge.VERTICAL;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_positionAll) {
                                yogaEdge = YogaEdge.ALL;
                            } else if (iKeyAt2 == R.styleable.yoga_yg_width) {
                                yogaNode.setWidthPercent(f);
                            }
                            yogaNode.setPositionPercent(yogaEdge, f);
                        }
                        yogaNode.setPaddingPercent(yogaEdge2, f);
                    }
                    yogaNode.setMarginPercent(yogaEdge3, f);
                }
            }
        }
    }

    private void applyLayoutRecursive(YogaNode yogaNode, float f, float f2) {
        View view = (View) yogaNode.getData();
        if (view != null && view != this) {
            if (view.getVisibility() == 8) {
                return;
            }
            int iRound = Math.round(yogaNode.getLayoutX() + f);
            int iRound2 = Math.round(yogaNode.getLayoutY() + f2);
            view.measure(View.MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutWidth()), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutHeight()), 1073741824));
            view.layout(iRound, iRound2, view.getMeasuredWidth() + iRound, view.getMeasuredHeight() + iRound2);
        }
        int childCount = yogaNode.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (equals(view)) {
                applyLayoutRecursive(yogaNode.getChildAt(i), f, f2);
            } else if (!(view instanceof YogaLayout)) {
                applyLayoutRecursive(yogaNode.getChildAt(i), yogaNode.getLayoutX() + f, yogaNode.getLayoutY() + f2);
            }
        }
    }

    private void createLayout(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == 1073741824) {
            this.mYogaNode.setHeight(size2);
        }
        if (mode == 1073741824) {
            this.mYogaNode.setWidth(size);
        }
        if (mode2 == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxHeight(size2);
        }
        if (mode == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxWidth(size);
        }
        this.mYogaNode.calculateLayout(Float.NaN, Float.NaN);
    }

    private void removeViewFromYogaTree(View view, boolean z) {
        YogaNode yogaNode = this.mYogaNodes.get(view);
        if (yogaNode == null) {
            return;
        }
        YogaNode owner = yogaNode.getOwner();
        int i = 0;
        while (true) {
            if (i >= owner.getChildCount()) {
                break;
            }
            if (owner.getChildAt(i).equals(yogaNode)) {
                owner.removeChildAt(i);
                break;
            }
            i++;
        }
        yogaNode.setData((Object) null);
        this.mYogaNodes.remove(view);
        if (z) {
            this.mYogaNode.calculateLayout(Float.NaN, Float.NaN);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        YogaNode yogaNodeJNIFinalizer;
        this.mYogaNode.setMeasureFunction((YogaMeasureFunction) null);
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.transferChildren(this);
            this.mYogaNode.addChildAt(virtualYogaLayout.getYogaNode(), this.mYogaNode.getChildCount());
            return;
        }
        super.addView(view, i, layoutParams);
        if (this.mYogaNodes.containsKey(view)) {
            return;
        }
        if (view instanceof YogaLayout) {
            yogaNodeJNIFinalizer = ((YogaLayout) view).getYogaNode();
        } else {
            yogaNodeJNIFinalizer = this.mYogaNodes.containsKey(view) ? this.mYogaNodes.get(view) : new YogaNodeJNIFinalizer();
            yogaNodeJNIFinalizer.setData(view);
            yogaNodeJNIFinalizer.setMeasureFunction(new ViewMeasureFunction());
        }
        applyLayoutParams((LayoutParams) view.getLayoutParams(), yogaNodeJNIFinalizer, view);
        this.mYogaNodes.put(view, yogaNodeJNIFinalizer);
        this.mYogaNode.addChildAt(yogaNodeJNIFinalizer, this.mYogaNode.getChildCount());
    }

    public void addView(View view, YogaNode yogaNode) {
        this.mYogaNodes.put(view, yogaNode);
        addView(view);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public YogaNode getYogaNode() {
        return this.mYogaNode;
    }

    public YogaNode getYogaNodeForView(View view) {
        return this.mYogaNodes.get(view);
    }

    public void invalidate(View view) {
        if (this.mYogaNodes.containsKey(view)) {
            this.mYogaNodes.get(view).dirty();
            return;
        }
        int childCount = this.mYogaNode.getChildCount();
        for (int i = 0; i < childCount; i++) {
            YogaNode childAt = this.mYogaNode.getChildAt(i);
            if (childAt.getData() instanceof YogaLayout) {
                ((YogaLayout) childAt.getData()).invalidate(view);
            }
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(View.MeasureSpec.makeMeasureSpec(i3 - i, 1073741824), View.MeasureSpec.makeMeasureSpec(i4 - i2, 1073741824));
        }
        applyLayoutRecursive(this.mYogaNode, 0.0f, 0.0f);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(i, i2);
        }
        setMeasuredDimension(Math.round(this.mYogaNode.getLayoutWidth()), Math.round(this.mYogaNode.getLayoutHeight()));
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            removeViewFromYogaTree(getChildAt(i), false);
        }
        super.removeAllViews();
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            removeViewFromYogaTree(getChildAt(i), true);
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        removeViewFromYogaTree(view, false);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        removeViewFromYogaTree(getChildAt(i), false);
        super.removeViewAt(i);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        removeViewFromYogaTree(view, true);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            removeViewFromYogaTree(getChildAt(i3), false);
        }
        super.removeViews(i, i2);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            removeViewFromYogaTree(getChildAt(i3), true);
        }
        super.removeViewsInLayout(i, i2);
    }
}
