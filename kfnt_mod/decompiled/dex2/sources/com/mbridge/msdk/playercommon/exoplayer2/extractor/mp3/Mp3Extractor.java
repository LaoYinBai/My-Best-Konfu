package com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.Format;
import com.mbridge.msdk.playercommon.exoplayer2.ParserException;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.Extractor;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorInput;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorOutput;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorsFactory;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.GaplessInfoHolder;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.Id3Peeker;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.PositionHolder;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.SeekMap;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.TrackOutput;
import com.mbridge.msdk.playercommon.exoplayer2.metadata.Metadata;
import com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class Mp3Extractor implements Extractor {
    public static final int FLAG_DISABLE_ID3_METADATA = 2;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    private static final int MAX_SNIFF_BYTES = 16384;
    private static final int MAX_SYNC_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_UNSET = 0;
    private long basisTimeUs;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private Metadata metadata;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private Seeker seeker;
    private final MpegAudioHeader synchronizedHeader;
    private int synchronizedHeaderData;
    private TrackOutput trackOutput;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.1
        @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new Mp3Extractor()};
        }
    };
    private static final int SEEK_HEADER_XING = Util.getIntegerCodeForString("Xing");
    private static final int SEEK_HEADER_INFO = Util.getIntegerCodeForString("Info");
    private static final int SEEK_HEADER_VBRI = Util.getIntegerCodeForString("VBRI");

    public @interface Flags {
    }

    interface Seeker extends SeekMap {
        long getTimeUs(long j);
    }

    private static boolean headersMatch(int i, long j) {
        return ((long) (i & MPEG_AUDIO_HEADER_MASK)) == (j & (-128000));
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.Extractor
    public final void release() {
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int i) {
        this(i, C.TIME_UNSET);
    }

    public Mp3Extractor(int i, long j) {
        this.flags = i;
        this.forcedFirstSampleTimestampUs = j;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioHeader();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = C.TIME_UNSET;
        this.id3Peeker = new Id3Peeker();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return synchronize(extractorInput, true);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = this.extractorOutput.track(0, 1);
        this.extractorOutput.endTracks();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.Extractor
    public final void seek(long j, long j2) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = C.TIME_UNSET;
        this.samplesRead = 0L;
        this.sampleBytesRemaining = 0;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            this.seeker = maybeReadSeekFrame(extractorInput);
            if (this.seeker == null || (!this.seeker.isSeekable() && (this.flags & 1) != 0)) {
                this.seeker = getConstantBitrateSeeker(extractorInput);
            }
            this.extractorOutput.seekMap(this.seeker);
            this.trackOutput.format(Format.createAudioSampleFormat(null, this.synchronizedHeader.mimeType, null, -1, 4096, this.synchronizedHeader.channels, this.synchronizedHeader.sampleRate, -1, this.gaplessInfoHolder.encoderDelay, this.gaplessInfoHolder.encoderPadding, null, null, 0, null, (this.flags & 2) != 0 ? null : this.metadata));
        }
        return readSample(extractorInput);
    }

    private int readSample(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int i = this.scratch.readInt();
            if (!headersMatch(i, this.synchronizedHeaderData) || MpegAudioHeader.getFrameSize(i) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            MpegAudioHeader.populateHeader(i, this.synchronizedHeader);
            if (this.basisTimeUs == C.TIME_UNSET) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != C.TIME_UNSET) {
                    this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0L);
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
        }
        int iSampleData = this.trackOutput.sampleData(extractorInput, this.sampleBytesRemaining, true);
        if (iSampleData == -1) {
            return -1;
        }
        this.sampleBytesRemaining -= iSampleData;
        if (this.sampleBytesRemaining > 0) {
            return 0;
        }
        this.trackOutput.sampleMetadata(this.basisTimeUs + ((this.samplesRead * 1000000) / this.synchronizedHeader.sampleRate), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z) throws InterruptedException, IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int frameSize;
        int i5 = z ? MAX_SNIFF_BYTES : 131072;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            this.metadata = this.id3Peeker.peekId3Data(extractorInput, (this.flags & 2) != 0 ? GaplessInfoHolder.GAPLESS_INFO_ID3_FRAME_PREDICATE : null);
            if (this.metadata != null) {
                this.gaplessInfoHolder.setFromMetadata(this.metadata);
            }
            int peekPosition = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(peekPosition);
            }
            i4 = peekPosition;
            i = 0;
            i2 = 0;
            i3 = 0;
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        while (true) {
            if (!extractorInput.peekFully(this.scratch.data, 0, 4, i > 0)) {
                break;
            }
            this.scratch.setPosition(0);
            int i6 = this.scratch.readInt();
            if ((i2 == 0 || headersMatch(i6, i2)) && (frameSize = MpegAudioHeader.getFrameSize(i6)) != -1) {
                i++;
                if (i != 1) {
                    if (i == 4) {
                        break;
                    }
                } else {
                    MpegAudioHeader.populateHeader(i6, this.synchronizedHeader);
                    i2 = i6;
                }
                extractorInput.advancePeekPosition(frameSize - 4);
            } else {
                int i7 = i3 + 1;
                if (i3 == i5) {
                    if (z) {
                        return false;
                    }
                    throw new ParserException("Searched too many bytes.");
                }
                if (z) {
                    extractorInput.resetPeekPosition();
                    extractorInput.advancePeekPosition(i4 + i7);
                } else {
                    extractorInput.skipFully(1);
                }
                i3 = i7;
                i = 0;
                i2 = 0;
            }
        }
        if (z) {
            extractorInput.skipFully(i4 + i3);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i2;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.Seeker maybeReadSeekFrame(com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorInput r10) throws java.lang.InterruptedException, java.io.IOException {
        /*
            r9 = this;
            com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray r5 = new com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.frameSize
            r5.<init>(r0)
            byte[] r0 = r5.data
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r1 = r9.synchronizedHeader
            int r1 = r1.frameSize
            r6 = 0
            r10.peekFully(r0, r6, r1)
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.version
            r1 = 1
            r0 = r0 & r1
            r2 = 21
            if (r0 == 0) goto L2b
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.channels
            if (r0 == r1) goto L28
            r2 = 36
            r7 = 36
            goto L36
        L28:
            r7 = 21
            goto L36
        L2b:
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.channels
            if (r0 == r1) goto L32
            goto L28
        L32:
            r2 = 13
            r7 = 13
        L36:
            int r8 = getSeekFrameHeader(r5, r7)
            int r0 = com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.SEEK_HEADER_XING
            if (r8 == r0) goto L62
            int r0 = com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.SEEK_HEADER_INFO
            if (r8 != r0) goto L43
            goto L62
        L43:
            int r0 = com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.SEEK_HEADER_VBRI
            if (r8 != r0) goto L5d
            long r0 = r10.getLength()
            long r2 = r10.getPosition()
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r4 = r9.synchronizedHeader
            com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.VbriSeeker r0 = com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.VbriSeeker.create(r0, r2, r4, r5)
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r1 = r9.synchronizedHeader
            int r1 = r1.frameSize
            r10.skipFully(r1)
            goto Lb2
        L5d:
            r0 = 0
            r10.resetPeekPosition()
            goto Lb2
        L62:
            long r0 = r10.getLength()
            long r2 = r10.getPosition()
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r4 = r9.synchronizedHeader
            com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.XingSeeker r0 = com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.XingSeeker.create(r0, r2, r4, r5)
            if (r0 == 0) goto L9a
            com.mbridge.msdk.playercommon.exoplayer2.extractor.GaplessInfoHolder r1 = r9.gaplessInfoHolder
            boolean r1 = r1.hasGaplessInfo()
            if (r1 != 0) goto L9a
            r10.resetPeekPosition()
            int r7 = r7 + 141
            r10.advancePeekPosition(r7)
            com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray r1 = r9.scratch
            byte[] r1 = r1.data
            r2 = 3
            r10.peekFully(r1, r6, r2)
            com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray r1 = r9.scratch
            r1.setPosition(r6)
            com.mbridge.msdk.playercommon.exoplayer2.extractor.GaplessInfoHolder r1 = r9.gaplessInfoHolder
            com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray r2 = r9.scratch
            int r2 = r2.readUnsignedInt24()
            r1.setFromXingHeaderValue(r2)
        L9a:
            com.mbridge.msdk.playercommon.exoplayer2.extractor.MpegAudioHeader r1 = r9.synchronizedHeader
            int r1 = r1.frameSize
            r10.skipFully(r1)
            if (r0 == 0) goto Lb2
            boolean r1 = r0.isSeekable()
            if (r1 != 0) goto Lb2
            int r1 = com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.SEEK_HEADER_INFO
            if (r8 != r1) goto Lb2
            com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor$Seeker r10 = r9.getConstantBitrateSeeker(r10)
            return r10
        Lb2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor.maybeReadSeekFrame(com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorInput):com.mbridge.msdk.playercommon.exoplayer2.extractor.mp3.Mp3Extractor$Seeker");
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput) throws InterruptedException, IOException {
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        MpegAudioHeader.populateHeader(this.scratch.readInt(), this.synchronizedHeader);
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader);
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() >= i + 4) {
            parsableByteArray.setPosition(i);
            int i2 = parsableByteArray.readInt();
            if (i2 == SEEK_HEADER_XING || i2 == SEEK_HEADER_INFO) {
                return i2;
            }
        }
        if (parsableByteArray.limit() < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        if (parsableByteArray.readInt() == SEEK_HEADER_VBRI) {
            return SEEK_HEADER_VBRI;
        }
        return 0;
    }
}
