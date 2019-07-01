package com.demo.error;

import java.util.Set;

public class ProductUnSupportedFieldPatchException extends RuntimeException {

    public ProductUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
