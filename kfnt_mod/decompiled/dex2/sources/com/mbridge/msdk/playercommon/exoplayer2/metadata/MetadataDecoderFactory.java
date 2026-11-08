package com.mbridge.msdk.playercommon.exoplayer2.metadata;

import com.mbridge.msdk.playercommon.exoplayer2.Format;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new MetadataDecoderFactory() { // from class: com.mbridge.msdk.playercommon.exoplayer2.metadata.MetadataDecoderFactory.1
        @Override // com.mbridge.msdk.playercommon.exoplayer2.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return MimeTypes.APPLICATION_ID3.equals(str) || MimeTypes.APPLICATION_EMSG.equals(str) || MimeTypes.APPLICATION_SCTE35.equals(str);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
        @Override // com.mbridge.msdk.playercommon.exoplayer2.metadata.MetadataDecoderFactory
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.mbridge.msdk.playercommon.exoplayer2.metadata.MetadataDecoder createDecoder(com.mbridge.msdk.playercommon.exoplayer2.Format r3) {
            /*
                r2 = this;
                java.lang.String r3 = r3.sampleMimeType
                int r0 = r3.hashCode()
                r1 = -1248341703(0xffffffffb597d139, float:-1.1311269E-6)
                if (r0 == r1) goto L2a
                r1 = 1154383568(0x44ce7ed0, float:1651.9629)
                if (r0 == r1) goto L20
                r1 = 1652648887(0x62816bb7, float:1.1936958E21)
                if (r0 == r1) goto L16
                goto L34
            L16:
                java.lang.String r0 = "application/x-scte35"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L34
                r3 = 2
                goto L35
            L20:
                java.lang.String r0 = "application/x-emsg"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L34
                r3 = 1
                goto L35
            L2a:
                java.lang.String r0 = "application/id3"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L34
                r3 = 0
                goto L35
            L34:
                r3 = -1
            L35:
                switch(r3) {
                    case 0: goto L4c;
                    case 1: goto L46;
                    case 2: goto L40;
                    default: goto L38;
                }
            L38:
                java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Attempted to create decoder for unsupported format"
                r3.<init>(r0)
                throw r3
            L40:
                com.mbridge.msdk.playercommon.exoplayer2.metadata.scte35.SpliceInfoDecoder r3 = new com.mbridge.msdk.playercommon.exoplayer2.metadata.scte35.SpliceInfoDecoder
                r3.<init>()
                return r3
            L46:
                com.mbridge.msdk.playercommon.exoplayer2.metadata.emsg.EventMessageDecoder r3 = new com.mbridge.msdk.playercommon.exoplayer2.metadata.emsg.EventMessageDecoder
                r3.<init>()
                return r3
            L4c:
                com.mbridge.msdk.playercommon.exoplayer2.metadata.id3.Id3Decoder r3 = new com.mbridge.msdk.playercommon.exoplayer2.metadata.id3.Id3Decoder
                r3.<init>()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.metadata.MetadataDecoderFactory.AnonymousClass1.createDecoder(com.mbridge.msdk.playercommon.exoplayer2.Format):com.mbridge.msdk.playercommon.exoplayer2.metadata.MetadataDecoder");
        }
    };

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
