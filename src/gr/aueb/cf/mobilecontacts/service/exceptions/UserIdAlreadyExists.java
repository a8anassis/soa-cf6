package gr.aueb.cf.mobilecontacts.service.exceptions;

import gr.aueb.cf.mobilecontacts.model.MobileContact;

public class UserIdAlreadyExists extends Exception {
    private final static long serialVersionUID = 1L;

    public UserIdAlreadyExists(MobileContact mobileContact) {
        super("Mobile contact with id: " + mobileContact.getId() + " already exists");
    }
}
