package com.facebook.stetho.inspector.elements.android;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.stetho.common.android.AccessibilityUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class AccessibilityNodeInfoWrapper {
    public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
        ViewCompat.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompatObtain);
        return accessibilityNodeInfoCompatObtain;
    }

    public static boolean getIsAccessibilityFocused(View view) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateNodeInfoFromView = createNodeInfoFromView(view);
        boolean zIsAccessibilityFocused = accessibilityNodeInfoCompatCreateNodeInfoFromView.isAccessibilityFocused();
        accessibilityNodeInfoCompatCreateNodeInfoFromView.recycle();
        return zIsAccessibilityFocused;
    }

    public static boolean getIgnored(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2 || importantForAccessibility == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateNodeInfoFromView = createNodeInfoFromView(view);
        try {
            if (!accessibilityNodeInfoCompatCreateNodeInfoFromView.isVisibleToUser()) {
                return true;
            }
            if (!AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompatCreateNodeInfoFromView, view)) {
                return AccessibilityUtil.hasFocusableAncestor(accessibilityNodeInfoCompatCreateNodeInfoFromView, view) || !AccessibilityUtil.hasText(accessibilityNodeInfoCompatCreateNodeInfoFromView);
            }
            if (accessibilityNodeInfoCompatCreateNodeInfoFromView.getChildCount() <= 0) {
                return false;
            }
            return !AccessibilityUtil.isSpeakingNode(accessibilityNodeInfoCompatCreateNodeInfoFromView, view);
        } finally {
            accessibilityNodeInfoCompatCreateNodeInfoFromView.recycle();
        }
    }

    public static String getIgnoredReasons(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (importantForAccessibility == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateNodeInfoFromView = createNodeInfoFromView(view);
        try {
            return !accessibilityNodeInfoCompatCreateNodeInfoFromView.isVisibleToUser() ? "View is not visible." : AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompatCreateNodeInfoFromView, view) ? "View is actionable, but has no description." : AccessibilityUtil.hasText(accessibilityNodeInfoCompatCreateNodeInfoFromView) ? "View is not actionable, and an ancestor View has co-opted its description." : "View is not actionable and has no description.";
        } finally {
            accessibilityNodeInfoCompatCreateNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getFocusableReasons(View view) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateNodeInfoFromView = createNodeInfoFromView(view);
        try {
            boolean zHasText = AccessibilityUtil.hasText(accessibilityNodeInfoCompatCreateNodeInfoFromView);
            boolean zIsCheckable = accessibilityNodeInfoCompatCreateNodeInfoFromView.isCheckable();
            boolean zHasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(accessibilityNodeInfoCompatCreateNodeInfoFromView, view);
            if (AccessibilityUtil.isActionableForAccessibility(accessibilityNodeInfoCompatCreateNodeInfoFromView)) {
                if (accessibilityNodeInfoCompatCreateNodeInfoFromView.getChildCount() <= 0) {
                    return "View is actionable and has no children.";
                }
                if (zHasText) {
                    return "View is actionable and has a description.";
                }
                if (zIsCheckable) {
                    return "View is actionable and checkable.";
                }
                if (zHasNonActionableSpeakingDescendants) {
                    return "View is actionable and has non-actionable descendants with descriptions.";
                }
            }
            if (AccessibilityUtil.isTopLevelScrollItem(accessibilityNodeInfoCompatCreateNodeInfoFromView, view)) {
                if (zHasText) {
                    return "View is a direct child of a scrollable container and has a description.";
                }
                if (zIsCheckable) {
                    return "View is a direct child of a scrollable container and is checkable.";
                }
                if (zHasNonActionableSpeakingDescendants) {
                    return "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
                }
            }
            if (zHasText) {
                return "View has a description and is not actionable, but has no actionable ancestor.";
            }
            return null;
        } finally {
            accessibilityNodeInfoCompatCreateNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getActions(View view) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateNodeInfoFromView = createNodeInfoFromView(view);
        try {
            StringBuilder sb = new StringBuilder();
            for (AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat : accessibilityNodeInfoCompatCreateNodeInfoFromView.getActionList()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                switch (accessibilityActionCompat.getId()) {
                    case 1:
                        sb.append("focus");
                        break;
                    case 2:
                        sb.append("clear-focus");
                        break;
                    case 4:
                        sb.append("select");
                        break;
                    case 8:
                        sb.append("clear-selection");
                        break;
                    case 16:
                        sb.append("click");
                        break;
                    case 32:
                        sb.append("long-click");
                        break;
                    case 64:
                        sb.append("accessibility-focus");
                        break;
                    case 128:
                        sb.append("clear-accessibility-focus");
                        break;
                    case 256:
                        sb.append("next-at-movement-granularity");
                        break;
                    case 512:
                        sb.append("previous-at-movement-granularity");
                        break;
                    case 1024:
                        sb.append("next-html-element");
                        break;
                    case 2048:
                        sb.append("previous-html-element");
                        break;
                    case 4096:
                        sb.append("scroll-forward");
                        break;
                    case 8192:
                        sb.append("scroll-backward");
                        break;
                    case 16384:
                        sb.append("copy");
                        break;
                    case 32768:
                        sb.append("paste");
                        break;
                    case 65536:
                        sb.append("cut");
                        break;
                    case 131072:
                        sb.append("set-selection");
                        break;
                    default:
                        CharSequence label = accessibilityActionCompat.getLabel();
                        if (label != null) {
                            sb.append(label);
                            break;
                        } else {
                            sb.append("unknown");
                            break;
                        }
                }
            }
            return sb.length() > 0 ? sb.toString() : null;
        } finally {
            accessibilityNodeInfoCompatCreateNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static CharSequence getDescription(View view) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateNodeInfoFromView = createNodeInfoFromView(view);
        try {
            CharSequence contentDescription = accessibilityNodeInfoCompatCreateNodeInfoFromView.getContentDescription();
            CharSequence text = accessibilityNodeInfoCompatCreateNodeInfoFromView.getText();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = view instanceof EditText;
            if (!TextUtils.isEmpty(contentDescription) && (!z2 || !z)) {
                return contentDescription;
            }
            if (z) {
                return text;
            }
            if (!(view instanceof ViewGroup)) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
                ViewCompat.onInitializeAccessibilityNodeInfo(childAt, accessibilityNodeInfoCompatObtain);
                CharSequence description = (!AccessibilityUtil.isSpeakingNode(accessibilityNodeInfoCompatObtain, childAt) || AccessibilityUtil.isAccessibilityFocusable(accessibilityNodeInfoCompatObtain, childAt)) ? null : getDescription(childAt);
                if (!TextUtils.isEmpty(description)) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(description);
                }
                accessibilityNodeInfoCompatObtain.recycle();
            }
            return sb.length() > 0 ? sb.toString() : null;
        } finally {
            accessibilityNodeInfoCompatCreateNodeInfoFromView.recycle();
        }
    }
}
