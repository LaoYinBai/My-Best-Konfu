package com.mbridge.msdk.out;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.a;
import com.mbridge.msdk.appwall.service.HandlerProvider;
import com.mbridge.msdk.foundation.tools.s;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBWallHandler extends MBCommonHandler {
    private ViewGroup container;
    private Context context;
    private View handlerCustomerLayout;
    private HandlerProvider wallProvider;

    public interface AppWallViewCampaignClickListener {
        void onEndJump();

        void onStartJump();
    }

    public interface AppWallViewLoadingEndListener {
        void onLoadEnd();
    }

    public interface AppWallViewNoMoreDateListener {
        void onNoMoreData();
    }

    public interface WallViewBackClickListener {
        void onBackClick();
    }

    public MBWallHandler(Map<String, Object> map, Context context, ViewGroup viewGroup) {
        super(map, context);
        this.context = context;
        setHandlerContainer(viewGroup);
    }

    public MBWallHandler(Map<String, Object> map, Context context) {
        super(map, context);
        this.context = context;
    }

    public void refreshUI(View view) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            if (view == null || !cls.isInstance(view)) {
                return;
            }
            cls.getMethod("refresh", new Class[0]).invoke(cls.cast(view), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getWallProperties(String str, String str2) {
        HashMap map = new HashMap();
        map.put(MBridgeConstans.PLACEMENT_ID, str);
        map.put(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
        map.put(MBridgeConstans.PLUGIN_NAME, new String[]{a.PLUGIN_WALL});
        map.put(MBridgeConstans.PROPERTIES_LAYOUT_TYPE, 3);
        return map;
    }

    @Override // com.mbridge.msdk.out.MBCommonHandler
    public boolean load() {
        if (this.properties != null && this.properties.containsKey(MBridgeConstans.PROPERTIES_UNIT_ID)) {
            loadAppWall();
            return true;
        }
        s.c("", "no unit id.");
        return true;
    }

    private boolean loadAppWall() {
        try {
            if (this.handlerCustomerLayout != null) {
                this.properties.put(a.PROPERTIES_WALL_ENTRY, this.handlerCustomerLayout);
            }
            if (this.wallProvider == null) {
                this.wallProvider = new HandlerProvider();
                this.wallProvider.insetView(this.container, (Resources) null, this.properties);
            }
            this.wallProvider.load();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.mbridge.msdk.out.MBCommonHandler
    public void release() {
        if (this.wallProvider != null) {
            this.wallProvider.release();
        }
        if (this.container != null) {
            this.container = null;
        }
        if (this.handlerCustomerLayout != null) {
            this.handlerCustomerLayout = null;
        }
    }

    public void setWallViewBackClickListener(View view, WallViewBackClickListener wallViewBackClickListener) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            if (view == null || !cls.isInstance(view)) {
                return;
            }
            cls.getMethod("setmWallViewClickListener", WallViewBackClickListener.class).invoke(cls.cast(view), wallViewBackClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View getWallView(Context context, AppWallTrackingListener appWallTrackingListener) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        Class<?> cls;
        if (this.properties == null || this.properties.size() <= 0) {
            return null;
        }
        try {
            cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            objNewInstance = cls.getConstructor(Context.class, AppWallTrackingListener.class).newInstance(context, appWallTrackingListener);
        } catch (Exception e) {
            e = e;
            objNewInstance = null;
        }
        try {
            cls.getMethod("setParamsIntent", Bundle.class).invoke(objNewInstance, getBundleFromMap(this.properties));
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return (View) objNewInstance;
        }
        return (View) objNewInstance;
    }

    public void releaseWallView(View view) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            if (view != null && cls.isInstance(view)) {
                cls.getMethod("destory", new Class[0]).invoke(cls.cast(view), new Object[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.context != null) {
            this.context = null;
        }
    }

    private Bundle getBundleFromMap(Map<String, Object> map) {
        LoadListener loadListener;
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        int iIntValue4;
        int iIntValue5;
        int iIntValue6;
        int iIntValue7;
        int iIntValue8;
        int iIntValue9;
        if (map == null || !map.containsKey(MBridgeConstans.PROPERTIES_UNIT_ID)) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, (String) map.get(MBridgeConstans.PROPERTIES_UNIT_ID));
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_BACKGROUND_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_TITLE_BACKGROUND_ID, ((Integer) map.get(a.PROPERTIES_WALL_TITLE_BACKGROUND_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_LOGO_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_TITLE_LOGO_ID, ((Integer) map.get(a.PROPERTIES_WALL_TITLE_LOGO_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_MAIN_BACKGROUND_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_MAIN_BACKGROUND_ID, ((Integer) map.get(a.PROPERTIES_WALL_MAIN_BACKGROUND_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_TAB_BACKGROUND_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_TAB_BACKGROUND_ID, ((Integer) map.get(a.PROPERTIES_WALL_TAB_BACKGROUND_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_TAB_INDICATE_LINE_BACKGROUND_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_TAB_INDICATE_LINE_BACKGROUND_ID, ((Integer) map.get(a.PROPERTIES_WALL_TAB_INDICATE_LINE_BACKGROUND_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_BUTTON_BACKGROUND_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_BUTTON_BACKGROUND_ID, ((Integer) map.get(a.PROPERTIES_WALL_BUTTON_BACKGROUND_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_LOAD_ID)) {
            bundle.putInt(a.PROPERTIES_WALL_LOAD_ID, ((Integer) map.get(a.PROPERTIES_WALL_LOAD_ID)).intValue());
        }
        if (map.containsKey(a.PROPERTIES_WALL_STATUS_COLOR) && (iIntValue9 = ((Integer) map.get(a.PROPERTIES_WALL_STATUS_COLOR)).intValue()) > 0) {
            bundle.putInt(a.PROPERTIES_WALL_STATUS_COLOR, iIntValue9);
        }
        if (map.containsKey(a.PROPERTIES_WALL_NAVIGATION_COLOR) && (iIntValue8 = ((Integer) map.get(a.PROPERTIES_WALL_NAVIGATION_COLOR)).intValue()) > 0) {
            bundle.putInt(a.PROPERTIES_WALL_NAVIGATION_COLOR, iIntValue8);
        }
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_BACKGROUND_COLOR) && (iIntValue7 = ((Integer) map.get(a.PROPERTIES_WALL_TITLE_BACKGROUND_COLOR)).intValue()) > 0) {
            bundle.putInt(a.PROPERTIES_WALL_TITLE_BACKGROUND_COLOR, iIntValue7);
        }
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_LOGO_TEXT)) {
            String str = (String) map.get(a.PROPERTIES_WALL_TITLE_LOGO_TEXT);
            if (!TextUtils.isEmpty(str)) {
                bundle.putString(a.PROPERTIES_WALL_TITLE_LOGO_TEXT, str);
            }
        }
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_COLOR) && (iIntValue6 = ((Integer) map.get(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_COLOR)).intValue()) > 0) {
            bundle.putInt(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_COLOR, iIntValue6);
        }
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_SIZE) && (iIntValue5 = ((Integer) map.get(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_SIZE)).intValue()) > 0) {
            bundle.putInt(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_SIZE, iIntValue5);
        }
        if (map.containsKey(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_TYPEFACE) && (iIntValue4 = ((Integer) map.get(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_TYPEFACE)).intValue()) > 0) {
            bundle.putInt(a.PROPERTIES_WALL_TITLE_LOGO_TEXT_TYPEFACE, iIntValue4);
        }
        if (map.containsKey(a.PROPERTIES_WALL_CURRENT_TAB_ID) && (iIntValue3 = ((Integer) map.get(a.PROPERTIES_WALL_CURRENT_TAB_ID)).intValue()) >= 0) {
            bundle.putInt(a.PROPERTIES_WALL_CURRENT_TAB_ID, iIntValue3);
        }
        if (map.containsKey(a.PROPERTIES_WALL_TAB_SHAPE_COLOR) && (iIntValue2 = ((Integer) map.get(a.PROPERTIES_WALL_TAB_SHAPE_COLOR)).intValue()) >= 0) {
            bundle.putInt(a.PROPERTIES_WALL_TAB_SHAPE_COLOR, iIntValue2);
        }
        if (map.containsKey(a.PROPERTIES_WALL_TAB_SHAPE_HEIGHT) && (iIntValue = ((Integer) map.get(a.PROPERTIES_WALL_TAB_SHAPE_HEIGHT)).intValue()) >= 0) {
            bundle.putInt(a.PROPERTIES_WALL_TAB_SHAPE_HEIGHT, iIntValue);
        }
        if (map.containsKey(a.PROPERTIES_WALL_CONFIGCHANGES)) {
            bundle.putInt(a.PROPERTIES_WALL_CONFIGCHANGES, ((Integer) map.get(a.PROPERTIES_WALL_CONFIGCHANGES)).intValue());
        }
        if (map.containsKey(a.WALL_VIEW_VIEWPAGER_NOSCROLL)) {
            Object obj = map.get(a.WALL_VIEW_VIEWPAGER_NOSCROLL);
            if (obj instanceof Boolean) {
                bundle.putBoolean(a.WALL_VIEW_VIEWPAGER_NOSCROLL, ((Boolean) obj).booleanValue());
            }
        }
        if (map.containsKey(a.WALL_VIEW_VIEWPAGER_NOSCROLL)) {
            Object obj2 = map.get(a.WALL_VIEW_VIEWPAGER_NOSCROLL);
            if (obj2 instanceof Boolean) {
                bundle.putBoolean(a.WALL_VIEW_VIEWPAGER_NOSCROLL, ((Boolean) obj2).booleanValue());
            }
        }
        if (map.containsKey(a.APPWALL_VIEW_LOAD_RESULT_LISTENER) && (loadListener = (LoadListener) map.get(a.APPWALL_VIEW_LOAD_RESULT_LISTENER)) != null) {
            bundle.putSerializable(a.APPWALL_VIEW_LOAD_RESULT_LISTENER, loadListener);
        }
        return bundle;
    }

    public ViewGroup getHandlerContainer() {
        return this.container;
    }

    public void setHandlerContainer(ViewGroup viewGroup) {
        this.container = viewGroup;
    }

    public View getHandlerCustomerLayout() {
        return this.handlerCustomerLayout;
    }

    public void setHandlerCustomerLayout(View view) {
        this.handlerCustomerLayout = view;
    }

    public void setAppWallViewCampaignClickListener(View view, AppWallViewCampaignClickListener appWallViewCampaignClickListener) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            if (view == null || !cls.isInstance(view)) {
                return;
            }
            cls.getMethod("setAppWallViewCampaignClickListener", AppWallViewCampaignClickListener.class).invoke(cls.cast(view), appWallViewCampaignClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAppWallViewNoMoreDateListener(View view, AppWallViewNoMoreDateListener appWallViewNoMoreDateListener) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            if (view == null || !cls.isInstance(view)) {
                return;
            }
            cls.getMethod("setAppWallViewNoMoreDateListener", AppWallViewNoMoreDateListener.class).invoke(cls.cast(view), appWallViewNoMoreDateListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAppWallViewLoadingEnd(View view, AppWallViewLoadingEndListener appWallViewLoadingEndListener) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.appwallex.WallView");
            if (view == null || !cls.isInstance(view)) {
                return;
            }
            cls.getMethod("setAppWallViewLoadingEndListener", AppWallViewLoadingEndListener.class).invoke(cls.cast(view), appWallViewLoadingEndListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startWall() {
        if (this.properties != null && this.properties.containsKey(MBridgeConstans.PROPERTIES_UNIT_ID)) {
            toShuffleOrAppwall();
        } else {
            s.c("", "no unit id.");
        }
    }

    private void toShuffleOrAppwall() {
        if (this.wallProvider == null) {
            this.wallProvider = new HandlerProvider();
        }
        this.wallProvider.startShuffleOrAppwall(this.context, this.properties);
    }
}
