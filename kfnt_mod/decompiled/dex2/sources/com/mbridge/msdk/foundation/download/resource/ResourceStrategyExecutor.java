package com.mbridge.msdk.foundation.download.resource;

import com.mbridge.msdk.foundation.download.database.IDatabaseHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class ResourceStrategyExecutor implements IResourceStrategy {
    private ResourceStrategy resourceStrategy;

    public ResourceStrategyExecutor(ResourceStrategy resourceStrategy) {
        this.resourceStrategy = resourceStrategy;
    }

    @Override // com.mbridge.msdk.foundation.download.resource.IResourceStrategy
    public void processResource(MBResourceManager mBResourceManager, IDatabaseHelper iDatabaseHelper, ResourceConfig resourceConfig) {
        if (this.resourceStrategy != null) {
            try {
                this.resourceStrategy.processResource(mBResourceManager, iDatabaseHelper, resourceConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.mbridge.msdk.foundation.download.resource.IResourceStrategy
    public String getResourceStrategyName() {
        return this.resourceStrategy.getResourceStrategyName();
    }
}
