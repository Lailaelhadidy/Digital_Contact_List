package com.mycompany.digital_contact_list;
public class Person {
    
    String PersonName;
    String PersonPhone;
    

    public Person(String PersonName, String PersonPhone) {
        this.PersonName = PersonName;
        this.PersonPhone = PersonPhone;
    }

    public String getName() {
        return PersonName;
    }

    public String getPhone() {
        return PersonPhone;
    }

    public void setName(String PersonName) {
        this.PersonName = PersonName;
    }

    public void setPhone(String PersonPhone) {
        this.PersonPhone = PersonPhone;
    }
    
    
    
}
