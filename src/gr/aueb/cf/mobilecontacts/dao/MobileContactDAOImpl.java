package gr.aueb.cf.mobilecontacts.dao;

import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MobileContactDAOImpl implements IMobileContactDAO {
    private final static List<MobileContact> contacts = new ArrayList<>();

    @Override
    public MobileContact insert(MobileContact mobileContact) {
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(long id, MobileContact mobileContact) {
        Optional<MobileContact> optionalMobileContact = contacts
                .stream()
                .filter(mb -> mb.getId() == id)
                .findFirst();
        MobileContact mb = optionalMobileContact.orElse(null);
        if (mb == null) return null;

        MobileContact mobileContactToReturn = new MobileContact(mb);
        mb.setId(mobileContact.getId());
        mb.setUserDetails(mobileContact.getUserDetails());
        mb.setPhoneNumber(mobileContact.getPhoneNumber());

        return mobileContactToReturn;
    }

    @Override
    public void delete(long id) {
        contacts.removeIf(c -> c.getId() == id);
    }

    @Override
    public void delete(String phoneNumber) {
        contacts.removeIf(c -> c.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public MobileContact get(long id) {
        return contacts.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MobileContact> getAll() {
        return new ArrayList<>(contacts); // Collections.unmodifiableList(contacts);
    }

    @Override
    public MobileContact get(String phoneNumber) {
        return contacts.stream()
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        return contacts
                .stream()
                .anyMatch(c -> c.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public boolean userIdExists(long id) {
        return contacts
                .stream()
                .anyMatch(c -> c.getId() == id);
    }
}
