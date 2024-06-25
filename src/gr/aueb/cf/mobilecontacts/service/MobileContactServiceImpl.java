package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.dto.UserDetailsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.UserDetailsUpdateDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.model.UserDetails;
import gr.aueb.cf.mobilecontacts.service.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public class MobileContactServiceImpl implements IMobileContactService {
    private final IMobileContactDAO dao;

    public MobileContactServiceImpl(IMobileContactDAO dao) {
        this.dao = dao;
    }


    @Override
    public MobileContact insertMobileContact(MobileContactInsertDTO dto) throws PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException {
        MobileContact mobileContact;

        try {
            mobileContact = mapMobileContactFromInsertDTO(dto);

            if (dao.phoneNumberExists(mobileContact.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException(mobileContact);
            }

            if (dao.userIdExists(mobileContact.getId())) {
                throw new UserIdAlreadyExistsException(mobileContact);
            }

            return dao.insert(mobileContact);
        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private MobileContact mapMobileContactFromInsertDTO(MobileContactInsertDTO dto) {
        UserDetails userDetails = mapUserDetailsFromInsertDTO(dto.getUserDetailsInsertDTO());
        return new MobileContact(dto.getId(), userDetails, dto.getPhoneNumber());
    }

    private UserDetails mapUserDetailsFromInsertDTO(UserDetailsInsertDTO dto) {
        return new UserDetails(dto.getId(), dto.getFirstname(), dto.getLastname());
    }

    private MobileContact mapMobileContactFromUpdateDTO(MobileContactUpdateDTO dto) {
        UserDetails userDetails = mapUserDetailsFromUpdateDTO(dto.getUserDetailsUpdateDTO());
        return new MobileContact(dto.getId(), userDetails, dto.getPhoneNumber());
    }

    private UserDetails mapUserDetailsFromUpdateDTO(UserDetailsUpdateDTO dto) {
        return new UserDetails(dto.getId(), dto.getFirstname(), dto.getLastname());
    }

    @Override
    public MobileContact updateMobileContact(long id, MobileContactUpdateDTO dto)
            throws PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException, ContactNotFoundException {

        MobileContact mobileContact;

        try {
            mobileContact = mapMobileContactFromUpdateDTO(dto);

            if (!dao.userIdExists(id)) {
                throw new ContactNotFoundException(id);
            }
            if (dao.phoneNumberExists(mobileContact.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException(mobileContact);
            }
            if (dao.userIdExists(mobileContact.getId())) {
                throw new UserIdAlreadyExistsException(mobileContact);
            }
            return dao.update(id, mobileContact);
        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException | ContactNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteMobileContactById(long id) throws ContactNotFoundException {

    }

    @Override
    public void deleteMobileContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {

    }

    @Override
    public MobileContact getMobileContactById(long id) throws ContactNotFoundException {
        return null;
    }

    @Override
    public MobileContact getMobileContactByPhoneNumber() throws ContactNotFoundException {
        return null;
    }

    @Override
    public List<MobileContact> getAllMobileContacts() {
        return null;
    }
}
