package com.kwad.yoga;

import com.kwad.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class YogaNodeJNIBase extends YogaNode implements Cloneable {
    private static final byte BORDER = 4;
    private static final byte DOES_LEGACY_STRETCH_BEHAVIOUR = 8;
    private static final byte HAS_NEW_LAYOUT = 16;
    private static final byte LAYOUT_BORDER_START_INDEX = 14;
    private static final byte LAYOUT_DIRECTION_INDEX = 5;
    private static final byte LAYOUT_EDGE_SET_FLAG_INDEX = 0;
    private static final byte LAYOUT_HEIGHT_INDEX = 2;
    private static final byte LAYOUT_LEFT_INDEX = 3;
    private static final byte LAYOUT_MARGIN_START_INDEX = 6;
    private static final byte LAYOUT_PADDING_START_INDEX = 10;
    private static final byte LAYOUT_TOP_INDEX = 4;
    private static final byte LAYOUT_WIDTH_INDEX = 1;
    private static final byte MARGIN = 1;
    private static final byte PADDING = 2;

    @DoNotStrip
    @Nullable
    private float[] arr;

    @Nullable
    private YogaBaselineFunction mBaselineFunction;

    @Nullable
    private List<YogaNodeJNIBase> mChildren;

    @Nullable
    private Object mData;
    private boolean mHasNewLayout;

    @DoNotStrip
    private int mLayoutDirection;

    @Nullable
    private YogaMeasureFunction mMeasureFunction;
    protected long mNativePointer;

    @Nullable
    private YogaNodeJNIBase mOwner;

    YogaNodeJNIBase() {
        this(YogaNative.jni_YGNodeNew());
    }

    private YogaNodeJNIBase(long j) {
        this.arr = null;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        if (j == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
        this.mNativePointer = j;
    }

    YogaNodeJNIBase(YogaConfig yogaConfig) {
        this(YogaNative.jni_YGNodeNewWithConfig(yogaConfig.mNativePointer));
    }

    private void clearChildren() {
        this.mChildren = null;
        YogaNative.jni_YGNodeClearChildren(this.mNativePointer);
    }

    @DoNotStrip
    private final long replaceChild(YogaNodeJNIBase yogaNodeJNIBase, int i) {
        if (this.mChildren == null) {
            throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
        }
        this.mChildren.remove(i);
        this.mChildren.add(i, yogaNodeJNIBase);
        yogaNodeJNIBase.mOwner = this;
        return yogaNodeJNIBase.mNativePointer;
    }

    private static YogaValue valueFromLong(long j) {
        return new YogaValue(Float.intBitsToFloat((int) j), (int) (j >> 32));
    }

    @Override // com.kwad.yoga.YogaNode
    public void addChildAt(YogaNode yogaNode, int i) {
        YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) yogaNode;
        if (yogaNodeJNIBase.mOwner != null) {
            throw new IllegalStateException("Child already has a parent, it must be removed first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(4);
        }
        this.mChildren.add(i, yogaNodeJNIBase);
        yogaNodeJNIBase.mOwner = this;
        YogaNative.jni_YGNodeInsertChild(this.mNativePointer, yogaNodeJNIBase.mNativePointer, i);
    }

    @DoNotStrip
    public final float baseline(float f, float f2) {
        return this.mBaselineFunction.baseline(this, f, f2);
    }

    @Override // com.kwad.yoga.YogaNode
    public void calculateLayout(float f, float f2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        for (int i = 0; i < arrayList.size(); i++) {
            List<YogaNodeJNIBase> list = ((YogaNodeJNIBase) arrayList.get(i)).mChildren;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        YogaNodeJNIBase[] yogaNodeJNIBaseArr = (YogaNodeJNIBase[]) arrayList.toArray(new YogaNodeJNIBase[arrayList.size()]);
        long[] jArr = new long[yogaNodeJNIBaseArr.length];
        for (int i2 = 0; i2 < yogaNodeJNIBaseArr.length; i2++) {
            jArr[i2] = yogaNodeJNIBaseArr[i2].mNativePointer;
        }
        YogaNative.jni_YGNodeCalculateLayout(this.mNativePointer, f, f2, jArr, yogaNodeJNIBaseArr);
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaNodeJNIBase cloneWithoutChildren() {
        try {
            YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) super.clone();
            long jJni_YGNodeClone = YogaNative.jni_YGNodeClone(this.mNativePointer);
            yogaNodeJNIBase.mOwner = null;
            yogaNodeJNIBase.mNativePointer = jJni_YGNodeClone;
            yogaNodeJNIBase.clearChildren();
            return yogaNodeJNIBase;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kwad.yoga.YogaNode
    public void copyStyle(YogaNode yogaNode) {
        YogaNative.jni_YGNodeCopyStyle(this.mNativePointer, ((YogaNodeJNIBase) yogaNode).mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public void dirty() {
        YogaNative.jni_YGNodeMarkDirty(this.mNativePointer);
    }

    public void dirtyAllDescendants() {
        YogaNative.jni_YGNodeMarkDirtyAndPropogateToDescendants(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaAlign getAlignContent() {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignContent(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaAlign getAlignItems() {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignItems(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaAlign getAlignSelf() {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignSelf(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public float getAspectRatio() {
        return YogaNative.jni_YGNodeStyleGetAspectRatio(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public float getBorder(YogaEdge yogaEdge) {
        return YogaNative.jni_YGNodeStyleGetBorder(this.mNativePointer, yogaEdge.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaNodeJNIBase getChildAt(int i) {
        if (this.mChildren != null) {
            return this.mChildren.get(i);
        }
        throw new IllegalStateException("YogaNode does not have children");
    }

    @Override // com.kwad.yoga.YogaNode
    public int getChildCount() {
        if (this.mChildren == null) {
            return 0;
        }
        return this.mChildren.size();
    }

    @Override // com.kwad.yoga.YogaNode
    @Nullable
    public Object getData() {
        return this.mData;
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaDisplay getDisplay() {
        return YogaDisplay.fromInt(YogaNative.jni_YGNodeStyleGetDisplay(this.mNativePointer));
    }

    public boolean getDoesLegacyStretchFlagAffectsLayout() {
        return this.arr != null && (((int) this.arr[0]) & 8) == 8;
    }

    @Override // com.kwad.yoga.YogaNode
    public float getFlex() {
        return YogaNative.jni_YGNodeStyleGetFlex(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getFlexBasis() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetFlexBasis(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaFlexDirection getFlexDirection() {
        return YogaFlexDirection.fromInt(YogaNative.jni_YGNodeStyleGetFlexDirection(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public float getFlexGrow() {
        return YogaNative.jni_YGNodeStyleGetFlexGrow(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public float getFlexShrink() {
        return YogaNative.jni_YGNodeStyleGetFlexShrink(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getHeight() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetHeight(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaJustify getJustifyContent() {
        return YogaJustify.fromInt(YogaNative.jni_YGNodeStyleGetJustifyContent(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutBorder(YogaEdge yogaEdge) {
        if (this.arr == null) {
            return 0.0f;
        }
        if ((((int) this.arr[0]) & 4) != 4) {
            return 0.0f;
        }
        int i = (14 - ((((int) this.arr[0]) & 1) == 1 ? 0 : 4)) - ((((int) this.arr[0]) & 2) != 2 ? 4 : 0);
        switch (yogaEdge) {
            case LEFT:
                return this.arr[i];
            case TOP:
                return this.arr[i + 1];
            case RIGHT:
                return this.arr[i + 2];
            case BOTTOM:
                return this.arr[i + 3];
            case START:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[i + 2] : this.arr[i];
            case END:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[i] : this.arr[i + 2];
            default:
                throw new IllegalArgumentException("Cannot get layout border of multi-edge shorthands");
        }
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaDirection getLayoutDirection() {
        return YogaDirection.fromInt(this.arr != null ? (int) this.arr[5] : this.mLayoutDirection);
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutHeight() {
        if (this.arr != null) {
            return this.arr[2];
        }
        return 0.0f;
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutMargin(YogaEdge yogaEdge) {
        if (this.arr == null || (((int) this.arr[0]) & 1) != 1) {
            return 0.0f;
        }
        switch (yogaEdge) {
            case LEFT:
                return this.arr[6];
            case TOP:
                return this.arr[7];
            case RIGHT:
                return this.arr[8];
            case BOTTOM:
                return this.arr[9];
            case START:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[8] : this.arr[6];
            case END:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[6] : this.arr[8];
            default:
                throw new IllegalArgumentException("Cannot get layout margins of multi-edge shorthands");
        }
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutPadding(YogaEdge yogaEdge) {
        if (this.arr == null) {
            return 0.0f;
        }
        if ((((int) this.arr[0]) & 2) != 2) {
            return 0.0f;
        }
        int i = 10 - ((((int) this.arr[0]) & 1) != 1 ? 4 : 0);
        switch (yogaEdge) {
            case LEFT:
                return this.arr[i];
            case TOP:
                return this.arr[i + 1];
            case RIGHT:
                return this.arr[i + 2];
            case BOTTOM:
                return this.arr[i + 3];
            case START:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[i + 2] : this.arr[i];
            case END:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[i] : this.arr[i + 2];
            default:
                throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
        }
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutWidth() {
        if (this.arr != null) {
            return this.arr[1];
        }
        return 0.0f;
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutX() {
        if (this.arr != null) {
            return this.arr[3];
        }
        return 0.0f;
    }

    @Override // com.kwad.yoga.YogaNode
    public float getLayoutY() {
        if (this.arr != null) {
            return this.arr[4];
        }
        return 0.0f;
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getMargin(YogaEdge yogaEdge) {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMargin(this.mNativePointer, yogaEdge.intValue()));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getMaxHeight() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMaxHeight(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getMaxWidth() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMaxWidth(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getMinHeight() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMinHeight(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getMinWidth() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMinWidth(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaOverflow getOverflow() {
        return YogaOverflow.fromInt(YogaNative.jni_YGNodeStyleGetOverflow(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    @Nullable
    public YogaNodeJNIBase getOwner() {
        return this.mOwner;
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getPadding(YogaEdge yogaEdge) {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetPadding(this.mNativePointer, yogaEdge.intValue()));
    }

    @Override // com.kwad.yoga.YogaNode
    @Nullable
    @Deprecated
    public YogaNodeJNIBase getParent() {
        return getOwner();
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getPosition(YogaEdge yogaEdge) {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetPosition(this.mNativePointer, yogaEdge.intValue()));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaPositionType getPositionType() {
        return YogaPositionType.fromInt(YogaNative.jni_YGNodeStyleGetPositionType(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaDirection getStyleDirection() {
        return YogaDirection.fromInt(YogaNative.jni_YGNodeStyleGetDirection(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaValue getWidth() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetWidth(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaWrap getWrap() {
        return YogaWrap.fromInt(YogaNative.jni_YGNodeStyleGetFlexWrap(this.mNativePointer));
    }

    @Override // com.kwad.yoga.YogaNode
    public boolean hasNewLayout() {
        return this.arr != null ? (((int) this.arr[0]) & 16) == 16 : this.mHasNewLayout;
    }

    @Override // com.kwad.yoga.YogaNode
    public int indexOf(YogaNode yogaNode) {
        if (this.mChildren == null) {
            return -1;
        }
        return this.mChildren.indexOf(yogaNode);
    }

    @Override // com.kwad.yoga.YogaNode
    public boolean isBaselineDefined() {
        return this.mBaselineFunction != null;
    }

    @Override // com.kwad.yoga.YogaNode
    public boolean isDirty() {
        return YogaNative.jni_YGNodeIsDirty(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }

    @Override // com.kwad.yoga.YogaNode
    public boolean isReferenceBaseline() {
        return YogaNative.jni_YGNodeIsReferenceBaseline(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public void markLayoutSeen() {
        if (this.arr != null) {
            this.arr[0] = ((int) this.arr[0]) & (-17);
        }
        this.mHasNewLayout = false;
    }

    @DoNotStrip
    public final long measure(float f, int i, float f2, int i2) {
        if (isMeasureDefined()) {
            return this.mMeasureFunction.measure(this, f, YogaMeasureMode.fromInt(i), f2, YogaMeasureMode.fromInt(i2));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    @Override // com.kwad.yoga.YogaNode
    public void print() {
        YogaNative.jni_YGNodePrint(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public YogaNodeJNIBase removeChildAt(int i) {
        if (this.mChildren == null) {
            throw new IllegalStateException("Trying to remove a child of a YogaNode that does not have children");
        }
        YogaNodeJNIBase yogaNodeJNIBaseRemove = this.mChildren.remove(i);
        yogaNodeJNIBaseRemove.mOwner = null;
        YogaNative.jni_YGNodeRemoveChild(this.mNativePointer, yogaNodeJNIBaseRemove.mNativePointer);
        return yogaNodeJNIBaseRemove;
    }

    @Override // com.kwad.yoga.YogaNode
    public void reset() {
        this.mMeasureFunction = null;
        this.mBaselineFunction = null;
        this.mData = null;
        this.arr = null;
        this.mHasNewLayout = true;
        this.mLayoutDirection = 0;
        YogaNative.jni_YGNodeReset(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setAlignContent(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignContent(this.mNativePointer, yogaAlign.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setAlignItems(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignItems(this.mNativePointer, yogaAlign.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setAlignSelf(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignSelf(this.mNativePointer, yogaAlign.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setAspectRatio(float f) {
        YogaNative.jni_YGNodeStyleSetAspectRatio(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setBaselineFunction(YogaBaselineFunction yogaBaselineFunction) {
        this.mBaselineFunction = yogaBaselineFunction;
        YogaNative.jni_YGNodeSetHasBaselineFunc(this.mNativePointer, yogaBaselineFunction != null);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setBorder(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetBorder(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setData(Object obj) {
        this.mData = obj;
    }

    @Override // com.kwad.yoga.YogaNode
    public void setDirection(YogaDirection yogaDirection) {
        YogaNative.jni_YGNodeStyleSetDirection(this.mNativePointer, yogaDirection.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setDisplay(YogaDisplay yogaDisplay) {
        YogaNative.jni_YGNodeStyleSetDisplay(this.mNativePointer, yogaDisplay.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlex(float f) {
        YogaNative.jni_YGNodeStyleSetFlex(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlexBasis(float f) {
        YogaNative.jni_YGNodeStyleSetFlexBasis(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlexBasisAuto() {
        YogaNative.jni_YGNodeStyleSetFlexBasisAuto(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlexBasisPercent(float f) {
        YogaNative.jni_YGNodeStyleSetFlexBasisPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlexDirection(YogaFlexDirection yogaFlexDirection) {
        YogaNative.jni_YGNodeStyleSetFlexDirection(this.mNativePointer, yogaFlexDirection.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlexGrow(float f) {
        YogaNative.jni_YGNodeStyleSetFlexGrow(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setFlexShrink(float f) {
        YogaNative.jni_YGNodeStyleSetFlexShrink(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setHeight(float f) {
        YogaNative.jni_YGNodeStyleSetHeight(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setHeightAuto() {
        YogaNative.jni_YGNodeStyleSetHeightAuto(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setHeightPercent(float f) {
        YogaNative.jni_YGNodeStyleSetHeightPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setIsReferenceBaseline(boolean z) {
        YogaNative.jni_YGNodeSetIsReferenceBaseline(this.mNativePointer, z);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setJustifyContent(YogaJustify yogaJustify) {
        YogaNative.jni_YGNodeStyleSetJustifyContent(this.mNativePointer, yogaJustify.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMargin(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetMargin(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMarginAuto(YogaEdge yogaEdge) {
        YogaNative.jni_YGNodeStyleSetMarginAuto(this.mNativePointer, yogaEdge.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMarginPercent(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetMarginPercent(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMaxHeight(float f) {
        YogaNative.jni_YGNodeStyleSetMaxHeight(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMaxHeightPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMaxHeightPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMaxWidth(float f) {
        YogaNative.jni_YGNodeStyleSetMaxWidth(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMaxWidthPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMaxWidthPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction) {
        this.mMeasureFunction = yogaMeasureFunction;
        YogaNative.jni_YGNodeSetHasMeasureFunc(this.mNativePointer, yogaMeasureFunction != null);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMinHeight(float f) {
        YogaNative.jni_YGNodeStyleSetMinHeight(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMinHeightPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMinHeightPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMinWidth(float f) {
        YogaNative.jni_YGNodeStyleSetMinWidth(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setMinWidthPercent(float f) {
        YogaNative.jni_YGNodeStyleSetMinWidthPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setOverflow(YogaOverflow yogaOverflow) {
        YogaNative.jni_YGNodeStyleSetOverflow(this.mNativePointer, yogaOverflow.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setPadding(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPadding(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setPaddingPercent(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPaddingPercent(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setPosition(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPosition(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setPositionPercent(YogaEdge yogaEdge, float f) {
        YogaNative.jni_YGNodeStyleSetPositionPercent(this.mNativePointer, yogaEdge.intValue(), f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setPositionType(YogaPositionType yogaPositionType) {
        YogaNative.jni_YGNodeStyleSetPositionType(this.mNativePointer, yogaPositionType.intValue());
    }

    @Override // com.kwad.yoga.YogaNode
    public void setStyleInputs(float[] fArr, int i) {
        YogaNative.jni_YGNodeSetStyleInputs(this.mNativePointer, fArr, i);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setWidth(float f) {
        YogaNative.jni_YGNodeStyleSetWidth(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setWidthAuto() {
        YogaNative.jni_YGNodeStyleSetWidthAuto(this.mNativePointer);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setWidthPercent(float f) {
        YogaNative.jni_YGNodeStyleSetWidthPercent(this.mNativePointer, f);
    }

    @Override // com.kwad.yoga.YogaNode
    public void setWrap(YogaWrap yogaWrap) {
        YogaNative.jni_YGNodeStyleSetFlexWrap(this.mNativePointer, yogaWrap.intValue());
    }
}
