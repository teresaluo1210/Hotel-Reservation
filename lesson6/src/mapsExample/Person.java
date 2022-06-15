package mapsExample;

public class Person {
    private String name;
    private String email;

    public Person(String name,String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public String toString(){
        return "Name: "+ name + " " + "Email: " + email;
    }
}
