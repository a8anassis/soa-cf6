package gr.aueb.cf.mobilecontacts;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.UserDetailsInsertDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.model.UserDetails;
import gr.aueb.cf.mobilecontacts.service.IMobileContactService;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;
import gr.aueb.cf.mobilecontacts.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.service.exceptions.UserIdAlreadyExistsException;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    // Wiring
    private final static IMobileContactDAO dao = new MobileContactDAOImpl();
    private final static IMobileContactService service = new MobileContactServiceImpl(dao);


    public static void main(String[] args) {
        String choice = "";

        while (true) {
            printMenu();
            System.out.println("Παρακαλώ εισάγετε επιλογή");
            choice = getChoice();

            if (choice.matches("[qQ]")) {
                System.out.println("Goodbye. Thanks for using our app");
                break;
            }

            if (!choice.matches("[1-5]")) {
                System.out.println("Error in choice");
                continue;
            }

            processWithChoice(choice);
        }
    }

    public static void processWithChoice(String choice) {
        MobileContact contact;
        long id;

        switch (choice) {
            case "1":
                contact = getMobileContact();
                insertContact(contact);
                break;
            case "2":
                System.out.println("Παρακαλώ δώστε το id της επαφής προς ενημέρωση");
                id = scanner.nextLong();
                System.out.println("Παρακαλώ εισάγετε τα στοιχεία της νέας επαφής");
                contact = getMobileContact();
                // updateContact(id, contact);
                break;

        }
    }

    public static void insertContact(MobileContact mobileContact) {
        try {
            MobileContactInsertDTO insertDTO = mapToContactInsertDto(mobileContact);
            MobileContact mc = service.insertMobileContact(insertDTO);
            System.out.println("Επιτυχής εισαγωγή: " + mc);
        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MobileContact getMobileContact() {
        MobileContact mobileContact = new MobileContact();

        System.out.println("Παρακαλώ εισάγετε τον κωδικό της επαφής");
        mobileContact.setId(scanner.nextLong());

        UserDetails userDetails = new UserDetails();
        System.out.println("Παρακαλώ εισάγετε τον κωδικό του χρήστη");
        userDetails.setId(scanner.nextLong());

        scanner.nextLine(); // consume new line, enter

        System.out.println("Παρακαλώ εισάγετε το όνομα");
        userDetails.setFirstname(scanner.nextLine());

        System.out.println("Παρακαλώ εισάγετε το επώνυμο");
        userDetails.setLastname(scanner.nextLine());

        System.out.println("Παρακαλώ εισάγετε τον αριθμό τηλεφώνου");
        mobileContact.setPhoneNumber(scanner.nextLine());

        mobileContact.setUserDetails(userDetails);
        return mobileContact;
    }

    private static UserDetailsInsertDTO mapToUserDetailsInsertDto(UserDetails userDetails) {
        return new UserDetailsInsertDTO(userDetails.getId(), userDetails.getFirstname(), userDetails.getLastname());
    }

    private static MobileContactInsertDTO mapToContactInsertDto(MobileContact mobileContact) {
        return new MobileContactInsertDTO(mobileContact.getId(),
                mapToUserDetailsInsertDto(mobileContact.getUserDetails()), mobileContact.getPhoneNumber());
    }

    public static void printMenu() {
        System.out.println("Επιλέξτε ένα από τα παρακάτω");
        System.out.println("1. Εισαγωγή επαφής");
        System.out.println("2. Ενημέρωση επαφής");
        System.out.println("3. Διαγραφή επαφής");
        System.out.println("4. Αναζήτηση επαφής με κωδικό");
        System.out.println("5. Εκτύπωση επαφών");
        System.out.println("6. q/Q για έξοδο");
    }

    public static String getChoice() {
        return scanner.nextLine().trim();
    }
}
