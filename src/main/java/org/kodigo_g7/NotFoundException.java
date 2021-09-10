package org.kodigo_g7;

public class NotFoundException extends Exception {

    public NotFoundException(String mensaje) {
        super("Problema:" + mensaje);
    }

}