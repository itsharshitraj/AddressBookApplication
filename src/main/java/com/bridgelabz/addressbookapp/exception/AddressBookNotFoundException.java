package com.bridgelabz.addressbookapp.exception;

public class AddressBookNotFoundException  extends RuntimeException {
    public AddressBookNotFoundException(String message) {
        super(message);
    }
}
