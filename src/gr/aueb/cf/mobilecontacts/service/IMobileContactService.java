package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public interface IMobileContactService {
    MobileContact insertMobileContact(MobileContactInsertDTO dto)
            throws PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException;

    MobileContact updateMobileContact(long id, MobileContactUpdateDTO dto)
        throws PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException, ContactNotFoundException;

    void deleteMobileContactById(long id) throws ContactNotFoundException;

    void deleteMobileContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException;

    MobileContact getMobileContactById(long id) throws ContactNotFoundException;

    MobileContact getMobileContactByPhoneNumber() throws ContactNotFoundException;

    List<MobileContact> getAllMobileContacts();
}
