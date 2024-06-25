package gr.aueb.cf.mobilecontacts.service.exceptions;

import gr.aueb.cf.mobilecontacts.model.MobileContact;

public class PhoneNumberAlreadyExistsException extends Exception {
    private final static long serialVersionUID = 1L;

    public PhoneNumberAlreadyExistsException(MobileContact mobileContact) {
        super("Mobile contact with phone number: " + mobileContact.getPhoneNumber() + " already exists");
    }
}
