package com.tachikoma.core.layout;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.kwad.yoga.YogaAlign;
import com.kwad.yoga.YogaDirection;
import com.kwad.yoga.YogaDisplay;
import com.kwad.yoga.YogaEdge;
import com.kwad.yoga.YogaFlexDirection;
import com.kwad.yoga.YogaJustify;
import com.kwad.yoga.YogaNode;
import com.kwad.yoga.YogaOverflow;
import com.kwad.yoga.YogaPositionType;
import com.kwad.yoga.YogaWrap;
import com.sigmob.sdk.common.mta.PointCategory;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.component.input.TextAlign;
import com.tachikoma.core.utility.TKConverter;
import com.tachikoma.core.utility.TKFlexible;
import com.umeng.message.proguard.l;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKYogaConfig {
    HashMap yogaConfig;
    private static final Map<String, Pattern> sPatternMapCache = new ConcurrentHashMap();
    private static final Map flexDirectMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.1
        {
            put("column", Integer.valueOf(YogaFlexDirection.COLUMN.ordinal()));
            put("column-reverse", Integer.valueOf(YogaFlexDirection.COLUMN_REVERSE.ordinal()));
            put("row", Integer.valueOf(YogaFlexDirection.ROW.ordinal()));
            put("row-reverse", Integer.valueOf(YogaFlexDirection.ROW_REVERSE.ordinal()));
        }
    };
    private static final Map overflowMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.2
        {
            put(TKBase.VISIBILITY_HIDDEN, Integer.valueOf(YogaOverflow.HIDDEN.ordinal()));
            put("scroll", Integer.valueOf(YogaOverflow.SCROLL.ordinal()));
            put(TKBase.VISIBILITY_VISIBLE, Integer.valueOf(YogaOverflow.VISIBLE.ordinal()));
        }
    };
    private static final Map wrapMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.3
        {
            put("wrap", Integer.valueOf(YogaWrap.WRAP.ordinal()));
            put("wrap-reverse", Integer.valueOf(YogaWrap.WRAP_REVERSE.ordinal()));
            put("nowrap", Integer.valueOf(YogaWrap.NO_WRAP.ordinal()));
        }
    };
    private static final Map directionMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.4
        {
            put("left", Integer.valueOf(YogaDirection.LTR.ordinal()));
            put("right", Integer.valueOf(YogaDirection.RTL.ordinal()));
            put("inherit", Integer.valueOf(YogaDirection.INHERIT.ordinal()));
        }
    };
    private static final Map justifyMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.5
        {
            put("flex-start", Integer.valueOf(YogaJustify.FLEX_START.ordinal()));
            put(TextAlign.CENTER, Integer.valueOf(YogaJustify.CENTER.ordinal()));
            put("flex-end", Integer.valueOf(YogaJustify.FLEX_END.ordinal()));
            put("space-between", Integer.valueOf(YogaJustify.SPACE_BETWEEN.ordinal()));
            put("space-around", Integer.valueOf(YogaJustify.SPACE_AROUND.ordinal()));
        }
    };
    private static final Map positionTypeMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.6
        {
            put("absolute", Integer.valueOf(YogaPositionType.ABSOLUTE.ordinal()));
            put("relative", Integer.valueOf(YogaPositionType.RELATIVE.ordinal()));
            put("static", Integer.valueOf(YogaPositionType.RELATIVE.ordinal()));
        }
    };
    private static final Map alignMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.7
        {
            put("auto", Integer.valueOf(YogaAlign.AUTO.ordinal()));
            put("flex-start", Integer.valueOf(YogaAlign.FLEX_START.ordinal()));
            put(TextAlign.CENTER, Integer.valueOf(YogaAlign.CENTER.ordinal()));
            put("flex-end", Integer.valueOf(YogaAlign.FLEX_END.ordinal()));
            put("stretch", Integer.valueOf(YogaAlign.STRETCH.ordinal()));
            put("baseline", Integer.valueOf(YogaAlign.BASELINE.ordinal()));
            put("space-between", Integer.valueOf(YogaAlign.SPACE_BETWEEN.ordinal()));
            put("space-around", Integer.valueOf(YogaAlign.SPACE_AROUND.ordinal()));
        }
    };
    private static final Map displayMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.8
        {
            put("flex", Integer.valueOf(YogaDisplay.FLEX.ordinal()));
            put("none", Integer.valueOf(YogaDisplay.NONE.ordinal()));
        }
    };
    private static final Map styleMap = new HashMap() { // from class: com.tachikoma.core.layout.TKYogaConfig.9
        {
            put("alignContent", 0);
            put("alignItems", 1);
            put("alignSelf", 2);
            put("aspectRatio", 3);
            put("borderAll", 4);
            put("borderBottom", 5);
            put("borderEnd", 6);
            put("borderHorizontal", 7);
            put("borderLeft", 8);
            put("borderRight", 9);
            put("borderStart", 10);
            put("borderTop", 11);
            put("borderVertical", 12);
            put("direction", 13);
            put("display", 14);
            put("flex", 15);
            put("flexBasis", 16);
            put("flexDirection", 17);
            put("flexGrow", 18);
            put("flexShrink", 19);
            put(AnimationProperty.HEIGHT, 20);
            put("justifyContent", 21);
            put("marginAll", 22);
            put(AnimationProperty.MARGIN, 22);
            put(AnimationProperty.MARGIN_BOTTOM, 23);
            put("marginEnd", 24);
            put("marginHorizontal", 25);
            put(AnimationProperty.MARGIN_LEFT, 26);
            put(AnimationProperty.MARGIN_RIGHT, 27);
            put("marginStart", 28);
            put(AnimationProperty.MARGIN_TOP, 29);
            put("marginVertical", 30);
            put("maxHeight", 31);
            put("maxWidth", 32);
            put("minHeight", 33);
            put("minWidth", 34);
            put("overflow", 35);
            put("paddingAll", 36);
            put("paddingBottom", 37);
            put("paddingEnd", 38);
            put("paddingHorizontal", 39);
            put("paddingLeft", 40);
            put("paddingRight", 41);
            put("paddingStart", 42);
            put("paddingTop", 43);
            put("paddingVertical", 44);
            put("positionAll", 45);
            put("positionBottom", 46);
            put("positionEnd", 47);
            put("positionHorizontal", 48);
            put("positionLeft", 49);
            put("positionRight", 50);
            put("positionStart", 51);
            put("positionTop", 52);
            put("bottom", 46);
            put("end", 47);
            put("left", 49);
            put("right", 50);
            put(PointCategory.START, 51);
            put(AnimationProperty.TOP, 52);
            put("positionType", 53);
            put("position", 53);
            put("positionVertical", 54);
            put(AnimationProperty.WIDTH, 55);
            put("flexWrap", 56);
        }
    };
    private static final List<String> NON_DP_CONVERT_KEYS = new ArrayList<String>() { // from class: com.tachikoma.core.layout.TKYogaConfig.10
        {
            add("opacity");
            add("maxLength");
            add("textLineClamp");
            add("column");
        }
    };

    private static class TKYogaConfigHolder {
        public static TKYogaConfig instance = new TKYogaConfig();

        private TKYogaConfigHolder() {
        }
    }

    private TKYogaConfig() {
        this.yogaConfig = new HashMap();
    }

    public static void applyLayoutBackgroudParams(YogaNode yogaNode, TKBase tKBase) {
        if (Build.VERSION.SDK_INT >= 17 && tKBase.getView().getResources().getConfiguration().getLayoutDirection() == 1) {
            yogaNode.setDirection(YogaDirection.RTL);
        }
        Drawable background = tKBase.getView().getBackground();
        if (background != null) {
            if (background.getPadding(new Rect())) {
                yogaNode.setPadding(YogaEdge.LEFT, r0.left);
                yogaNode.setPadding(YogaEdge.TOP, r0.top);
                yogaNode.setPadding(YogaEdge.RIGHT, r0.right);
                yogaNode.setPadding(YogaEdge.BOTTOM, r0.bottom);
            }
        }
    }

    public static void applyLayoutParams(YogaNode yogaNode, String str, Object obj) {
        YogaEdge yogaEdge;
        YogaEdge yogaEdge2;
        YogaEdge yogaEdge3;
        YogaEdge yogaEdge4;
        YogaEdge yogaEdge5;
        YogaEdge yogaEdge6;
        YogaEdge yogaEdge7;
        YogaEdge yogaEdge8;
        int iYgPropertyWithCSSStyle = defaultConfig().ygPropertyWithCSSStyle(str);
        if (iYgPropertyWithCSSStyle == Integer.MAX_VALUE) {
            return;
        }
        float number = formatNumber(iYgPropertyWithCSSStyle, obj);
        boolean zIsPercentageNumeric = isPercentageNumeric(obj);
        boolean zIsAutoNumeric = isAutoNumeric(obj);
        if (number == 2.1474836E9f) {
            return;
        }
        if (!zIsPercentageNumeric && !zIsAutoNumeric) {
            if (iYgPropertyWithCSSStyle == 0) {
                yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(number)));
            } else if (iYgPropertyWithCSSStyle == 1) {
                yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(number)));
            } else if (iYgPropertyWithCSSStyle == 2) {
                yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(number)));
            } else if (iYgPropertyWithCSSStyle == 3) {
                yogaNode.setAspectRatio(number);
            } else {
                if (iYgPropertyWithCSSStyle == 8) {
                    yogaEdge8 = YogaEdge.LEFT;
                } else if (iYgPropertyWithCSSStyle == 11) {
                    yogaEdge8 = YogaEdge.TOP;
                } else if (iYgPropertyWithCSSStyle == 9) {
                    yogaEdge8 = YogaEdge.RIGHT;
                } else if (iYgPropertyWithCSSStyle == 5) {
                    yogaEdge8 = YogaEdge.BOTTOM;
                } else if (iYgPropertyWithCSSStyle == 10) {
                    yogaEdge8 = YogaEdge.START;
                } else if (iYgPropertyWithCSSStyle == 6) {
                    yogaEdge8 = YogaEdge.END;
                } else if (iYgPropertyWithCSSStyle == 7) {
                    yogaEdge8 = YogaEdge.HORIZONTAL;
                } else if (iYgPropertyWithCSSStyle == 12) {
                    yogaEdge8 = YogaEdge.VERTICAL;
                } else if (iYgPropertyWithCSSStyle == 4) {
                    yogaEdge8 = YogaEdge.ALL;
                } else if (iYgPropertyWithCSSStyle == 13) {
                    yogaNode.setDirection(YogaDirection.fromInt(Math.round(number)));
                } else if (iYgPropertyWithCSSStyle == 14) {
                    yogaNode.setDisplay(YogaDisplay.fromInt(Math.round(number)));
                } else if (iYgPropertyWithCSSStyle == 15) {
                    yogaNode.setFlex(number);
                } else if (iYgPropertyWithCSSStyle == 16) {
                    yogaNode.setFlexBasis(number);
                } else if (iYgPropertyWithCSSStyle == 17) {
                    yogaNode.setFlexDirection(YogaFlexDirection.fromInt(Math.round(number)));
                } else if (iYgPropertyWithCSSStyle == 18) {
                    yogaNode.setFlexGrow(number);
                } else if (iYgPropertyWithCSSStyle == 19) {
                    yogaNode.setFlexShrink(number);
                } else if (iYgPropertyWithCSSStyle == 20) {
                    yogaNode.setHeight(number);
                } else {
                    if (iYgPropertyWithCSSStyle == 26) {
                        yogaEdge7 = YogaEdge.LEFT;
                    } else if (iYgPropertyWithCSSStyle == 21) {
                        yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(number)));
                    } else if (iYgPropertyWithCSSStyle == 29) {
                        yogaEdge7 = YogaEdge.TOP;
                    } else if (iYgPropertyWithCSSStyle == 27) {
                        yogaEdge7 = YogaEdge.RIGHT;
                    } else if (iYgPropertyWithCSSStyle == 23) {
                        yogaEdge7 = YogaEdge.BOTTOM;
                    } else if (iYgPropertyWithCSSStyle == 28) {
                        yogaEdge7 = YogaEdge.START;
                    } else if (iYgPropertyWithCSSStyle == 24) {
                        yogaEdge7 = YogaEdge.END;
                    } else if (iYgPropertyWithCSSStyle == 25) {
                        yogaEdge7 = YogaEdge.HORIZONTAL;
                    } else if (iYgPropertyWithCSSStyle == 30) {
                        yogaEdge7 = YogaEdge.VERTICAL;
                    } else if (iYgPropertyWithCSSStyle == 22) {
                        yogaEdge7 = YogaEdge.ALL;
                    } else if (iYgPropertyWithCSSStyle == 31) {
                        yogaNode.setMaxHeight(number);
                    } else if (iYgPropertyWithCSSStyle == 32) {
                        yogaNode.setMaxWidth(number);
                    } else if (iYgPropertyWithCSSStyle == 33) {
                        yogaNode.setMinHeight(number);
                    } else if (iYgPropertyWithCSSStyle == 34) {
                        yogaNode.setMinWidth(number);
                    } else {
                        if (iYgPropertyWithCSSStyle == 40) {
                            yogaEdge6 = YogaEdge.LEFT;
                        } else if (iYgPropertyWithCSSStyle == 43) {
                            yogaEdge6 = YogaEdge.TOP;
                        } else if (iYgPropertyWithCSSStyle == 41) {
                            yogaEdge6 = YogaEdge.RIGHT;
                        } else if (iYgPropertyWithCSSStyle == 37) {
                            yogaEdge6 = YogaEdge.BOTTOM;
                        } else if (iYgPropertyWithCSSStyle == 42) {
                            yogaEdge6 = YogaEdge.START;
                        } else if (iYgPropertyWithCSSStyle == 38) {
                            yogaEdge6 = YogaEdge.END;
                        } else if (iYgPropertyWithCSSStyle == 39) {
                            yogaEdge6 = YogaEdge.HORIZONTAL;
                        } else if (iYgPropertyWithCSSStyle == 44) {
                            yogaEdge6 = YogaEdge.VERTICAL;
                        } else if (iYgPropertyWithCSSStyle == 36) {
                            yogaEdge6 = YogaEdge.ALL;
                        } else {
                            if (iYgPropertyWithCSSStyle == 49) {
                                yogaEdge5 = YogaEdge.LEFT;
                            } else if (iYgPropertyWithCSSStyle == 52) {
                                yogaEdge5 = YogaEdge.TOP;
                            } else if (iYgPropertyWithCSSStyle == 50) {
                                yogaEdge5 = YogaEdge.RIGHT;
                            } else if (iYgPropertyWithCSSStyle == 46) {
                                yogaEdge5 = YogaEdge.BOTTOM;
                            } else if (iYgPropertyWithCSSStyle == 51) {
                                yogaEdge5 = YogaEdge.START;
                            } else if (iYgPropertyWithCSSStyle == 47) {
                                yogaEdge5 = YogaEdge.END;
                            } else if (iYgPropertyWithCSSStyle == 48) {
                                yogaEdge5 = YogaEdge.HORIZONTAL;
                            } else if (iYgPropertyWithCSSStyle == 54) {
                                yogaEdge5 = YogaEdge.VERTICAL;
                            } else if (iYgPropertyWithCSSStyle == 45) {
                                yogaEdge5 = YogaEdge.ALL;
                            } else if (iYgPropertyWithCSSStyle == 53) {
                                yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(number)));
                            } else if (iYgPropertyWithCSSStyle == 55) {
                                yogaNode.setWidth(number);
                            } else if (iYgPropertyWithCSSStyle == 56) {
                                yogaNode.setWrap(YogaWrap.fromInt(Math.round(number)));
                            }
                            yogaNode.setPosition(yogaEdge5, number);
                        }
                        yogaNode.setPadding(yogaEdge6, number);
                    }
                    yogaNode.setMargin(yogaEdge7, number);
                }
                yogaNode.setBorder(yogaEdge8, number);
            }
        }
        if (zIsAutoNumeric) {
            if (iYgPropertyWithCSSStyle == 16) {
                yogaNode.setFlexBasisAuto();
            } else if (iYgPropertyWithCSSStyle == 20) {
                yogaNode.setHeightAuto();
            } else {
                if (iYgPropertyWithCSSStyle == 26) {
                    yogaEdge4 = YogaEdge.LEFT;
                } else if (iYgPropertyWithCSSStyle == 29) {
                    yogaEdge4 = YogaEdge.TOP;
                } else if (iYgPropertyWithCSSStyle == 27) {
                    yogaEdge4 = YogaEdge.RIGHT;
                } else if (iYgPropertyWithCSSStyle == 23) {
                    yogaEdge4 = YogaEdge.BOTTOM;
                } else if (iYgPropertyWithCSSStyle == 28) {
                    yogaEdge4 = YogaEdge.START;
                } else if (iYgPropertyWithCSSStyle == 24) {
                    yogaEdge4 = YogaEdge.END;
                } else if (iYgPropertyWithCSSStyle == 25) {
                    yogaEdge4 = YogaEdge.HORIZONTAL;
                } else if (iYgPropertyWithCSSStyle == 30) {
                    yogaEdge4 = YogaEdge.VERTICAL;
                } else if (iYgPropertyWithCSSStyle == 22) {
                    yogaEdge4 = YogaEdge.ALL;
                } else if (iYgPropertyWithCSSStyle == 55) {
                    yogaNode.setWidthAuto();
                }
                yogaNode.setMarginAuto(yogaEdge4);
            }
        }
        if (zIsPercentageNumeric) {
            if (iYgPropertyWithCSSStyle == 16) {
                yogaNode.setFlexBasisPercent(number);
                return;
            }
            if (iYgPropertyWithCSSStyle == 20) {
                yogaNode.setHeightPercent(number);
                return;
            }
            if (iYgPropertyWithCSSStyle == 26) {
                yogaEdge3 = YogaEdge.LEFT;
            } else if (iYgPropertyWithCSSStyle == 29) {
                yogaEdge3 = YogaEdge.TOP;
            } else if (iYgPropertyWithCSSStyle == 27) {
                yogaEdge3 = YogaEdge.RIGHT;
            } else if (iYgPropertyWithCSSStyle == 23) {
                yogaEdge3 = YogaEdge.BOTTOM;
            } else if (iYgPropertyWithCSSStyle == 28) {
                yogaEdge3 = YogaEdge.START;
            } else if (iYgPropertyWithCSSStyle == 24) {
                yogaEdge3 = YogaEdge.END;
            } else if (iYgPropertyWithCSSStyle == 25) {
                yogaEdge3 = YogaEdge.HORIZONTAL;
            } else if (iYgPropertyWithCSSStyle == 30) {
                yogaEdge3 = YogaEdge.VERTICAL;
            } else {
                if (iYgPropertyWithCSSStyle != 22) {
                    if (iYgPropertyWithCSSStyle == 55) {
                        yogaNode.setWidthPercent(number);
                        return;
                    }
                    if (iYgPropertyWithCSSStyle == 31) {
                        yogaNode.setMaxHeightPercent(number);
                        return;
                    }
                    if (iYgPropertyWithCSSStyle == 32) {
                        yogaNode.setMaxWidthPercent(number);
                        return;
                    }
                    if (iYgPropertyWithCSSStyle == 33) {
                        yogaNode.setMinHeightPercent(number);
                        return;
                    }
                    if (iYgPropertyWithCSSStyle == 34) {
                        yogaNode.setMinWidthPercent(number);
                        return;
                    }
                    if (iYgPropertyWithCSSStyle == 40) {
                        yogaEdge2 = YogaEdge.LEFT;
                    } else if (iYgPropertyWithCSSStyle == 43) {
                        yogaEdge2 = YogaEdge.TOP;
                    } else if (iYgPropertyWithCSSStyle == 41) {
                        yogaEdge2 = YogaEdge.RIGHT;
                    } else if (iYgPropertyWithCSSStyle == 37) {
                        yogaEdge2 = YogaEdge.BOTTOM;
                    } else if (iYgPropertyWithCSSStyle == 42) {
                        yogaEdge2 = YogaEdge.START;
                    } else if (iYgPropertyWithCSSStyle == 38) {
                        yogaEdge2 = YogaEdge.END;
                    } else if (iYgPropertyWithCSSStyle == 39) {
                        yogaEdge2 = YogaEdge.HORIZONTAL;
                    } else if (iYgPropertyWithCSSStyle == 44) {
                        yogaEdge2 = YogaEdge.VERTICAL;
                    } else {
                        if (iYgPropertyWithCSSStyle != 36) {
                            if (iYgPropertyWithCSSStyle == 49) {
                                yogaEdge = YogaEdge.LEFT;
                            } else if (iYgPropertyWithCSSStyle == 52) {
                                yogaEdge = YogaEdge.TOP;
                            } else if (iYgPropertyWithCSSStyle == 50) {
                                yogaEdge = YogaEdge.RIGHT;
                            } else if (iYgPropertyWithCSSStyle == 46) {
                                yogaEdge = YogaEdge.BOTTOM;
                            } else if (iYgPropertyWithCSSStyle == 51) {
                                yogaEdge = YogaEdge.START;
                            } else if (iYgPropertyWithCSSStyle == 47) {
                                yogaEdge = YogaEdge.END;
                            } else if (iYgPropertyWithCSSStyle == 48) {
                                yogaEdge = YogaEdge.HORIZONTAL;
                            } else if (iYgPropertyWithCSSStyle == 54) {
                                yogaEdge = YogaEdge.VERTICAL;
                            } else if (iYgPropertyWithCSSStyle != 45) {
                                return;
                            } else {
                                yogaEdge = YogaEdge.ALL;
                            }
                            yogaNode.setPositionPercent(yogaEdge, number);
                            return;
                        }
                        yogaEdge2 = YogaEdge.ALL;
                    }
                    yogaNode.setPaddingPercent(yogaEdge2, number);
                    return;
                }
                yogaEdge3 = YogaEdge.ALL;
            }
            yogaNode.setMarginPercent(yogaEdge3, number);
        }
    }

    public static TKYogaConfig defaultConfig() {
        return TKYogaConfigHolder.instance;
    }

    public static float formatNumber(int i, Object obj) {
        if (obj instanceof Number) {
            return TKConverter.dp2px(Tachikoma.sApplication, ((Number) obj).floatValue());
        }
        if (!(obj instanceof String)) {
            return 0.0f;
        }
        Matcher matcher = getPattern("^(-?\\d+(\\.\\d+)?)(px|%)?$").matcher((String) obj);
        if (!matcher.matches()) {
            return defaultConfig().ygStyleValueIsString(i, obj);
        }
        if (matcher.groupCount() < 3) {
            return 0.0f;
        }
        String strGroup = matcher.group(3);
        String strGroup2 = matcher.group(1);
        return strGroup == null ? TKConverter.dp2px(Tachikoma.sApplication, Float.parseFloat(strGroup2)) : "px".equals(strGroup) ? TKFlexible.rem2px(Float.parseFloat(strGroup2)) : Float.parseFloat(strGroup2);
    }

    private static Pattern getPattern(String str) {
        Pattern pattern = sPatternMapCache.get(str);
        if (pattern != null) {
            return pattern;
        }
        Pattern patternCompile = Pattern.compile(str);
        sPatternMapCache.put(str, patternCompile);
        return patternCompile;
    }

    public static boolean isAutoNumeric(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).equals("auto");
        }
        return false;
    }

    public static boolean isColor(String str) {
        return getPattern("^#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})$").matcher(str).matches();
    }

    public static boolean isColor24(String str) {
        return getPattern("^#([0-9a-fA-F]{6})$").matcher(str).matches();
    }

    public static boolean isColor32(String str) {
        return getPattern("^#([0-9a-fA-F]{8})$").matcher(str).matches();
    }

    public static boolean isLinearGradientColor(String str) {
        return getPattern("^linear-gradient\\(\\d+deg(\\s+#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})){2}\\)$").matcher(str).matches();
    }

    private static boolean isNeedDPConvent(String str) {
        return !NON_DP_CONVERT_KEYS.contains(str);
    }

    public static boolean isNumeric(String str) {
        return getPattern("^-?\\d+(\\.\\d+)?$").matcher(str).matches();
    }

    public static boolean isPercentageNumeric(Object obj) {
        if (obj instanceof String) {
            return getPattern("^-?\\d+(\\.\\d+)?%$").matcher((String) obj).matches();
        }
        return false;
    }

    public static boolean isPxNumeric(String str) {
        return getPattern("^-?\\d+(\\.\\d+)?px$").matcher(str).matches();
    }

    public static int[] parseLinearGradientColor(String str) {
        String[] strArrSplit = str.replace("linear-gradient(", "").replace("deg", "").replace(l.t, "").trim().split("\\s+");
        int[] iArr = new int[strArrSplit.length];
        iArr[0] = Integer.parseInt(strArrSplit[0]) % 360;
        for (int i = 1; i < iArr.length; i++) {
            iArr[i] = rgba2argb(Color.parseColor(strArrSplit[i]));
        }
        return iArr;
    }

    private static int rgba2argb(int i) {
        return ((i >> 8) & 16777215) | (i << 24);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int ygStyleValueIsString(int i, Object obj) {
        Map map;
        if (i == 17) {
            if (flexDirectMap.containsKey(obj)) {
                map = flexDirectMap;
                return ((Integer) map.get(obj)).intValue();
            }
            return Integer.MAX_VALUE;
        }
        if (i == 21) {
            if (justifyMap.containsKey(obj)) {
                map = justifyMap;
                return ((Integer) map.get(obj)).intValue();
            }
            return Integer.MAX_VALUE;
        }
        if (i == 35) {
            if (overflowMap.containsKey(obj)) {
                map = overflowMap;
                return ((Integer) map.get(obj)).intValue();
            }
            return Integer.MAX_VALUE;
        }
        if (i == 53) {
            if (positionTypeMap.containsKey(obj)) {
                map = positionTypeMap;
                return ((Integer) map.get(obj)).intValue();
            }
            return Integer.MAX_VALUE;
        }
        if (i == 56) {
            if (wrapMap.containsKey(obj)) {
                map = wrapMap;
                return ((Integer) map.get(obj)).intValue();
            }
            return Integer.MAX_VALUE;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
                if (alignMap.containsKey(obj)) {
                    map = alignMap;
                    return ((Integer) map.get(obj)).intValue();
                }
                return Integer.MAX_VALUE;
            default:
                switch (i) {
                    case 13:
                        if (directionMap.containsKey(obj)) {
                            map = directionMap;
                            return ((Integer) map.get(obj)).intValue();
                        }
                        return Integer.MAX_VALUE;
                    case 14:
                        if (displayMap.containsKey(obj)) {
                            map = displayMap;
                            return ((Integer) map.get(obj)).intValue();
                        }
                        return Integer.MAX_VALUE;
                    default:
                        return Integer.MAX_VALUE;
                }
        }
    }

    public int ygPropertyWithCSSStyle(String str) {
        if (styleMap.containsKey(str)) {
            return ((Integer) styleMap.get(str)).intValue();
        }
        return Integer.MAX_VALUE;
    }

    public Array yogaProperties() {
        return null;
    }
}
