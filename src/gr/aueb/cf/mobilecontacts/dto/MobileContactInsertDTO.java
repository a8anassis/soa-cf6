package gr.aueb.cf.mobilecontacts.dto;

public class MobileContactInsertDTO extends BaseDTO {
    private UserDetailsInsertDTO userDetailsInsertDTO;
    private String phoneNumber;

    public MobileContactInsertDTO() {

    }

    public MobileContactInsertDTO(long id, UserDetailsInsertDTO userDetailsInsertDTO, String phoneNumber) {
        setId(id);
        this.userDetailsInsertDTO = userDetailsInsertDTO;
        this.phoneNumber = phoneNumber;
    }

    public UserDetailsInsertDTO getUserDetailsInsertDTO() {
        return userDetailsInsertDTO;
    }

    public void setUserDetailsInsertDTO(UserDetailsInsertDTO userDetailsInsertDTO) {
        this.userDetailsInsertDTO = userDetailsInsertDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
