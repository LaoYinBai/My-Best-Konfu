package com.unity3d.services.monetization.placementcontent.purchasing;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Item {
    private String itemId;
    private long quantity;
    private String type;

    private Item(Builder builder) {
        this.itemId = builder.itemId;
        this.quantity = builder.quantity;
        this.type = builder.type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getItemId() {
        return this.itemId;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public String getType() {
        return this.type;
    }

    public static final class Builder {
        private String itemId;
        private long quantity;
        private String type;

        private Builder() {
        }

        public Builder withItemId(String str) {
            this.itemId = str;
            return this;
        }

        public Builder withQuantity(long j) {
            this.quantity = j;
            return this;
        }

        public Builder withType(String str) {
            this.type = str;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
