package com.mycompany.digital_contact_list;

import java.util.ArrayList;

public class ArrayPhoneBook {
    
    ArrayList<Person> contacts = new ArrayList<>();
    
    public void insert(Person p) {
        contacts.add(p);
        System.out.println("Contact Added Successfully...");
    }
    
    public void search(String name) {
        for (Person p : contacts) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Contact Found: " + p.getName() + "  " + p.getPhone());
                return;
            }
        }
        System.out.println("Unfortunately, Contact not Found...");
    }
    
    public void remove(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                
                for (int j = i; j < contacts.size() - 1; j++) {
                    contacts.set(j, contacts.get(j + 1));
                }
                
                contacts.remove(contacts.size() - 1);
                System.out.println("Contact Removed Successfully...");
                return;
            }
        }
        System.out.println("Contact Not Found...");
    }
    
    public void updateExistingPhone(String name, String newPhone) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.get(i).setPhone(newPhone);
                System.out.println("Contact Phone Updated Successfully");
                return;
            }
        }
        System.out.println("Contact Not Found..");
    }
    
    public void updateExistingName(String name, String name2) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.get(i).setName(name2);
                System.out.println("Contact Name Updated Successfully");
                return;
            }
        }
        System.out.println("Contact Not Found..");
    }
    
    public void display() {
        System.out.println("Contacts: ");
        
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(contacts.get(i).getName() + "   " + contacts.get(i).getPhone());
        }
        
    }
    
    public void size() {
        System.out.println("Number of Contacts found is " + contacts.size());
    }
    
    public void isolate0100() {
        System.out.println("Contacts starting with (0100): ");
        
        boolean found = false;
        
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhone().startsWith("0100")) {
                System.out.println(contacts.get(i).getName() + "   " + contacts.get(i).getPhone());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No contacts starting with 0100.");
        }
    }
}