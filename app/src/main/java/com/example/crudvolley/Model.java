package com.example.crudvolley;

public class Model {
       String id, gender, fullName, age, email, profession, address;

       public Model(String id, String gender, String fullName, String age, String email, String profession, String address){
        this.id = id;
        this.gender = gender;
        this.fullName =fullName;
        this.age = age;
        this.email = email;
        this.profession = profession;
        this.address = address;
        }

    public String getId() {
        return id;
        }

public String getGender() {
        return gender;
        }

public String getFullName() {
        return fullName;
        }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
        }

public String getProfession() {
        return profession;
        }

public String getAddress() {
        return address;
        }

}
