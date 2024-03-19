import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact contact1, Contact contact2) {
        return contact1.getName().compareTo(contact2.getName());
    }
}

public class AddressBook {
    private List<Contact> contacts;
    private ContactComparator contactComparator;

    public AddressBook() {
        contacts = new ArrayList<>();
        contactComparator = new ContactComparator();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        Collections.sort(contacts, contactComparator);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void printContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println("Name: " + contact.getName() + ", Phone: " + contact.getPhoneNumber());
            }
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add contact");
            System.out.println("2. Search contact");
            System.out.println("3. Print all contacts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter contact phone number: ");
                    String phoneNumber = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully.");
                    break;

                case 2:
                    System.out.print("Enter contact name to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found - Name: " + foundContact.getName() +
                                ", Phone: " + foundContact.getPhoneNumber());
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    System.out.println("All contacts:");
                    addressBook.printContacts();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
