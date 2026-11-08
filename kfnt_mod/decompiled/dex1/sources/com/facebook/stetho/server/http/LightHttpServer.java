package com.facebook.stetho.server.http;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.baidu.mobads.sdk.internal.ae;
import com.facebook.stetho.server.LeakyBufferedInputStream;
import com.facebook.stetho.server.SocketLike;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LightHttpServer {
    private static final String TAG = "LightHttpServer";
    private final HandlerRegistry mHandlerRegistry;

    public LightHttpServer(HandlerRegistry handlerRegistry) {
        this.mHandlerRegistry = handlerRegistry;
    }

    public void serve(SocketLike socketLike) throws IOException {
        LeakyBufferedInputStream leakyBufferedInputStream = new LeakyBufferedInputStream(socketLike.getInput(), 1024);
        OutputStream output = socketLike.getOutput();
        HttpMessageReader httpMessageReader = new HttpMessageReader(leakyBufferedInputStream);
        HttpMessageWriter httpMessageWriter = new HttpMessageWriter(new BufferedOutputStream(output));
        SocketLike socketLike2 = new SocketLike(socketLike, leakyBufferedInputStream);
        LightHttpRequest lightHttpRequest = new LightHttpRequest();
        LightHttpResponse lightHttpResponse = new LightHttpResponse();
        while (true) {
            LightHttpRequest requestMessage = readRequestMessage(lightHttpRequest, httpMessageReader);
            if (requestMessage == null) {
                return;
            }
            lightHttpResponse.reset();
            if (!dispatchToHandler(socketLike2, requestMessage, lightHttpResponse)) {
                return;
            } else {
                writeFullResponse(lightHttpResponse, httpMessageWriter, output);
            }
        }
    }

    private boolean dispatchToHandler(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) throws IOException {
        HttpHandler httpHandlerLookup = this.mHandlerRegistry.lookup(lightHttpRequest.uri.getPath());
        if (httpHandlerLookup == null) {
            lightHttpResponse.code = HttpStatus.HTTP_NOT_FOUND;
            lightHttpResponse.reasonPhrase = "Not found";
            lightHttpResponse.body = LightHttpBody.create("No handler found\n", ae.e);
            return true;
        }
        try {
            return httpHandlerLookup.handleRequest(socketLike, lightHttpRequest, lightHttpResponse);
        } catch (RuntimeException e) {
            lightHttpResponse.code = 500;
            lightHttpResponse.reasonPhrase = "Internal Server Error";
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            try {
                e.printStackTrace(printWriter);
                printWriter.close();
                lightHttpResponse.body = LightHttpBody.create(stringWriter.toString(), ae.e);
                return true;
            } catch (Throwable th) {
                printWriter.close();
                throw th;
            }
        }
    }

    @Nullable
    private static LightHttpRequest readRequestMessage(LightHttpRequest lightHttpRequest, HttpMessageReader httpMessageReader) throws IOException {
        lightHttpRequest.reset();
        String line = httpMessageReader.readLine();
        if (line == null) {
            return null;
        }
        String[] strArrSplit = line.split(" ", 3);
        if (strArrSplit.length != 3) {
            throw new IOException("Invalid request line: " + line);
        }
        lightHttpRequest.method = strArrSplit[0];
        lightHttpRequest.uri = Uri.parse(strArrSplit[1]);
        lightHttpRequest.protocol = strArrSplit[2];
        readHeaders(lightHttpRequest, httpMessageReader);
        return lightHttpRequest;
    }

    private static void readHeaders(LightHttpMessage lightHttpMessage, HttpMessageReader httpMessageReader) throws IOException {
        while (true) {
            String line = httpMessageReader.readLine();
            if (line == null) {
                throw new EOFException();
            }
            if ("".equals(line)) {
                return;
            }
            String[] strArrSplit = line.split(": ", 2);
            if (strArrSplit.length != 2) {
                throw new IOException("Malformed header: " + line);
            }
            String str = strArrSplit[0];
            String str2 = strArrSplit[1];
            lightHttpMessage.headerNames.add(str);
            lightHttpMessage.headerValues.add(str2);
        }
    }

    private static void writeFullResponse(LightHttpResponse lightHttpResponse, HttpMessageWriter httpMessageWriter, OutputStream outputStream) throws IOException {
        lightHttpResponse.prepare();
        writeResponseMessage(lightHttpResponse, httpMessageWriter);
        if (lightHttpResponse.body != null) {
            lightHttpResponse.body.writeTo(outputStream);
        }
    }

    public static void writeResponseMessage(LightHttpResponse lightHttpResponse, HttpMessageWriter httpMessageWriter) throws IOException {
        httpMessageWriter.writeLine("HTTP/1.1 " + lightHttpResponse.code + " " + lightHttpResponse.reasonPhrase);
        int size = lightHttpResponse.headerNames.size();
        for (int i = 0; i < size; i++) {
            httpMessageWriter.writeLine(lightHttpResponse.headerNames.get(i) + ": " + lightHttpResponse.headerValues.get(i));
        }
        httpMessageWriter.writeLine();
        httpMessageWriter.flush();
    }

    private static class HttpMessageReader {
        private final BufferedInputStream mIn;
        private final StringBuilder mBuffer = new StringBuilder();
        private final NewLineDetector mNewLineDetector = new NewLineDetector();

        public HttpMessageReader(BufferedInputStream bufferedInputStream) {
            this.mIn = bufferedInputStream;
        }

        @Nullable
        public String readLine() throws IOException {
            while (true) {
                int i = this.mIn.read();
                if (i < 0) {
                    return null;
                }
                char c2 = (char) i;
                this.mNewLineDetector.accept(c2);
                switch (this.mNewLineDetector.state()) {
                    case 1:
                        this.mBuffer.append(c2);
                        break;
                    case 3:
                        String string = this.mBuffer.toString();
                        this.mBuffer.setLength(0);
                        return string;
                }
            }
        }

        private static class NewLineDetector {
            private static final int STATE_ON_CR = 2;
            private static final int STATE_ON_CRLF = 3;
            private static final int STATE_ON_OTHER = 1;
            private int state;

            private NewLineDetector() {
                this.state = 1;
            }

            public void accept(char c2) {
                switch (this.state) {
                    case 1:
                        if (c2 == '\r') {
                            this.state = 2;
                            return;
                        }
                        return;
                    case 2:
                        if (c2 == '\n') {
                            this.state = 3;
                            return;
                        } else {
                            this.state = 1;
                            return;
                        }
                    case 3:
                        if (c2 == '\r') {
                            this.state = 2;
                            return;
                        } else {
                            this.state = 1;
                            return;
                        }
                    default:
                        throw new IllegalArgumentException("Unknown state: " + this.state);
                }
            }

            public int state() {
                return this.state;
            }
        }
    }

    public static class HttpMessageWriter {
        private static final byte[] CRLF = "\r\n".getBytes();
        private final BufferedOutputStream mOut;

        public HttpMessageWriter(BufferedOutputStream bufferedOutputStream) {
            this.mOut = bufferedOutputStream;
        }

        public void writeLine(String str) throws IOException {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                this.mOut.write(str.charAt(i));
            }
            this.mOut.write(CRLF);
        }

        public void writeLine() throws IOException {
            this.mOut.write(CRLF);
        }

        public void flush() throws IOException {
            this.mOut.flush();
        }
    }
}
