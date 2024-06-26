package gr.aueb.cf.mobilecontacts.dto;

public class MobileContactReadOnlyDTO extends BaseDTO {
    private UserDetailsReadOnlyDTO userDetailsReadOnlyDTO;
    private String phoneNumber;

    public MobileContactReadOnlyDTO() {
    }

    public MobileContactReadOnlyDTO(long id, UserDetailsReadOnlyDTO userDetailsReadOnlyDTO, String phoneNumber) {
        setId(id);
        this.userDetailsReadOnlyDTO = userDetailsReadOnlyDTO;
        this.phoneNumber = phoneNumber;
    }

    public UserDetailsReadOnlyDTO getUserDetailsReadOnlyDTO() {
        return userDetailsReadOnlyDTO;
    }

    public void setUserDetailsReadOnlyDTO(UserDetailsReadOnlyDTO userDetailsReadOnlyDTO) {
        this.userDetailsReadOnlyDTO = userDetailsReadOnlyDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
