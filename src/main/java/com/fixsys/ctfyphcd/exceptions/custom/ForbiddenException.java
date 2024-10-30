package com.fixsys.ctfyphcd.exceptions.custom;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
