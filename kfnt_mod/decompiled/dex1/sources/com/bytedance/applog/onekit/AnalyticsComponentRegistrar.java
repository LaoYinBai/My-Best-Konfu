package com.bytedance.applog.onekit;

import android.content.Context;
import com.bytedance.a.r;
import com.volcengine.onekit.component.Component;
import com.volcengine.onekit.component.ComponentContainer;
import com.volcengine.onekit.component.ComponentFactory;
import com.volcengine.onekit.component.ComponentRegistrar;
import com.volcengine.onekit.component.Dependency;
import com.volcengine.onekit.model.InitOptions;
import com.volcengine.onekit.service.Analytics;
import com.volcengine.onekit.service.AppInfo;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AnalyticsComponentRegistrar implements ComponentRegistrar {

    public class a implements ComponentFactory<Analytics> {
        public a(AnalyticsComponentRegistrar analyticsComponentRegistrar) {
        }

        public Object create(ComponentContainer componentContainer) {
            return new r();
        }
    }

    public List<Component> getComponents() {
        return Arrays.asList(Component.builder(Analytics.class, new Class[0]).addDependency(Dependency.required(Context.class)).addDependency(Dependency.required(AppInfo.class)).addDependency(Dependency.required(InitOptions.class)).enablePrivacy().factory(new a(this)).build());
    }
}
