package com.mycompany.digital_contact_list;

import java.util.ArrayList;

public class ArrayPhoneBook {
    
    private ArrayList<Person> contacts;

    public ArrayPhoneBook() {
        contacts = new ArrayList<>();
    }

    public void insert(Person person) {

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(person.getName())) {
                System.out.println("Contact already exists.");
                return;
            }
        }

        for (int i = 0; i <= contacts.size(); i++) {
            if (i == contacts.size()
                    || person.getName().compareToIgnoreCase(contacts.get(i).getName()) < 0) {

                contacts.add(i, person);
                System.out.println("Contact inserted successfully.");
                return;
            }
        }
    }

    public Person search(String name) {

        for (Person person : contacts) {
            if (person.getName().equalsIgnoreCase(name)) {
                System.out.println("Contact Found: " + person.getName() + "  " + person.getPhone());
                return person;
            }
        }

        System.out.println("Contact not found.");
        return null;
    }

    public void remove(String name) {

        for (int i = 0; i < contacts.size(); i++) {

            if (contacts.get(i).getName().equalsIgnoreCase(name)) {

                for (int j = i; j < contacts.size() - 1; j++) {
                    contacts.set(j, contacts.get(j + 1));
                }

                contacts.remove(contacts.size() - 1);
                System.out.println("Contact removed successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    public void updateExistingPhone(String name, String newPhone) {

        for (Person person : contacts) {
            if (person.getName().equalsIgnoreCase(name)) {
                person.setPhone(newPhone);
                System.out.println("Contact phone updated successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    public void updateExistingName(String oldName, String newName) {

        for (Person person : contacts) {
            if (person.getName().equalsIgnoreCase(newName)) {
                System.out.println("New contact name already exists.");
                return;
            }
        }

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(oldName)) {

                String phone = contacts.get(i).getPhone();

                remove(oldName);
                insert(new Person(newName, phone));

                System.out.println("Contact name updated successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    public void display() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts were found to be displayed.");
            return;
        }

        System.out.println("Contacts:");

        for (Person person : contacts) {
            System.out.println(person.getName() + "  " + person.getPhone());
        }

        System.out.println("Contacts displayed successfully.");
    }

    public void size() {
        System.out.println("Number of Contacts found is " + contacts.size());
    }

    public void isolate0100() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("Contacts starting with 0100:");

        boolean found = false;

        for (Person person : contacts) {
            if (person.getPhone().startsWith("0100")) {
                System.out.println(person.getName() + "  " + person.getPhone());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contacts starting with 0100.");
        }
    }
}
