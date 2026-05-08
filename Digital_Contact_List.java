package com.mycompany.digital_contact_list;

import java.util.Scanner;

public class Digital_Contact_List {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int option=-1;
        
        BSTPhoneBook phonebook = new BSTPhoneBook();
        
        do {

            System.out.print("""
                               
                               Choose an option:
                               1. Add contact
                               2. Remove contact
                               3. Update contact phone
                               4. Update Contact Name
                               5. Search contact
                               6. Display all contacts
                               7. Size of contacts
                               8. Display isolated contacts (0100)
                               0. Exit
                               Enter choice:
                               """);

            try{
                
                option = in.nextInt();
                in.nextLine();
                
                 switch(option) {

                case 1: {

                    System.out.println("Enter Person Name: ");
                    String name = in.nextLine();

                    System.out.println("Enter Person Phone: ");
                    String phone = in.nextLine();

                    Person p = new Person(name, phone);

                    phonebook.insert(p);

                    break;
                }

                case 2: {

                    System.out.println("Enter Person Name: ");
                    String name = in.nextLine();

                    phonebook.remove(name);

                    break;
                }

                case 3: {

                    System.out.println("Enter Contact Name: ");
                    String name = in.nextLine();
                    
                    if(phonebook.searchRec(phonebook.root, name)!=null){
                        System.out.println("Enter New Phone: ");
                        String phone = in.nextLine(); 
                        Person p= new Person(name, phone);
                        phonebook.updateExistingPhone2(p);
                    }
                    else
                        System.out.println("Contact not Found");
                    break;
                }

                case 4: {

                    System.out.println("Enter Current Contact Name: ");
                    String oldName = in.nextLine();
                    
                    if(phonebook.searchRec(phonebook.root, oldName)!=null){
                        System.out.println("Enter New Contact Name: ");
                        String newName = in.nextLine();
                        phonebook.updateExistingName(oldName, newName);
                    }
                    else
                        System.out.println("Contact not Found");
      
                    break;
                }

                case 5: {

                    System.out.println("Enter Person Name: ");
                    String name = in.nextLine();

                    phonebook.search(name);

                    break;
                }

                case 6:

                    phonebook.display();

                    break;

                case 7:

                    phonebook.size();

                    break;

                case 8:

                    phonebook.isolate0100();

                    break;

                case 0:

                    System.out.println("Exiting Program... Goodbye!");

                    break;

                default:

                    System.out.println("Invalid Choice.. Try again");
            
        }
                 
            }catch(Exception e){
            
                System.out.println("Invalid input! Please enter a number");
                in.nextLine(); //clean scanner
            }
            
           } while(option != 0);

//ArrayPhoneBook phonebook = new ArrayPhoneBook();
//
//        do {
//            System.out.print("""
//                               
//                               Choose an option:
//                               1. Add contact
//                               2. Remove contact
//                               3. Update contact phone
//                               4. Update contact name
//                               5. Search contact
//                               6. Display all contacts
//                               7. Size of contacts
//                               8. Display isolated contacts (0100)
//                               0. Exit
//                               Enter choice:
//                               """);
//
//            try {
//                option = in.nextInt();
//                in.nextLine();
//
//                switch (option) {
//
//                    case 1: {
//                        System.out.println("Enter Person Name:");
//                        String name = in.nextLine();
//
//                        System.out.println("Enter Person Phone:");
//                        String phone = in.nextLine();
//
//                        phonebook.insert(new Person(name, phone));
//                        break;
//                    }
//
//                    case 2: {
//                        System.out.println("Enter Person Name:");
//                        String name = in.nextLine();
//
//                        phonebook.remove(name);
//                        break;
//                    }
//
//                    case 3: {
//                        System.out.println("Enter Contact Name:");
//                        String name = in.nextLine();
//
//                        Person result = phonebook.search(name);
//
//                        if (result != null) {
//                            System.out.println("Enter New Phone:");
//                            String phone = in.nextLine();
//
//                            phonebook.updateExistingPhone(name, phone);
//                        }
//
//                        break;
//                    }
//
//                    case 4: {
//                        System.out.println("Enter Current Contact Name:");
//                        String oldName = in.nextLine();
//
//                        Person result = phonebook.search(oldName);
//
//                        if (result != null) {
//                            System.out.println("Enter New Contact Name:");
//                            String newName = in.nextLine();
//
//                            phonebook.updateExistingName(oldName, newName);
//                        }
//
//                        break;
//                    }
//
//                    case 5: {
//                        System.out.println("Enter Person Name:");
//                        String name = in.nextLine();
//
//                        phonebook.search(name);
//                        break;
//                    }
//
//                    case 6:
//                        phonebook.display();
//                        break;
//
//                    case 7:
//                        phonebook.size();
//                        break;
//
//                    case 8:
//                        phonebook.isolate0100();
//                        break;
//
//                    case 0:
//                        System.out.println("Exiting Program... Goodbye!");
//                        break;
//
//                    default:
//                        System.out.println("Invalid Choice.. Try again");
//                }
//
//            } catch (Exception e) {
//                System.out.println("Invalid input! Please enter a number.");
//                in.nextLine();
//            }
//
//        } while (option != 0);
    }
}
