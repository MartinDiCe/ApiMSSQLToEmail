package com.diceprojects.apiexport.config;

import javax.activation.DataSource;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamDataSource implements DataSource {

    private final InputStream inputStream;

    public InputStreamDataSource(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException("Output stream not supported");
    }

    @Override
    public String getContentType() {
        return "application/octet-stream";
    }

    @Override
    public String getName() {
        return null;
    }
}
