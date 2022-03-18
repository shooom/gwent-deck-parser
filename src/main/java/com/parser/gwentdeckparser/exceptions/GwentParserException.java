package com.parser.gwentdeckparser.exceptions;

public class GwentParserException extends RuntimeException {
    public GwentParserException(String msg) {
        super(msg);
    }
    public GwentParserException(Exception ex) {
        super(ex);
    }

    public GwentParserException(String msg, Exception ex) {
        super(msg, ex);
    }
}
