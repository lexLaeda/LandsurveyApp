package com.survey.mole.service.file.data;

public abstract class AbstractDataDocumentCreator implements DataDocumentCreator {

    private String header;
    private String footer;

    @Override
    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public void setFooter(String footer) {
        this.footer = footer;
    }

    protected String getHeader() {
        return header;
    }

    protected String getFooter() {
        return footer;
    }
}
