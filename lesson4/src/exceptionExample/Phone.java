package exceptionExample;

public class Phone {
    private String phoneType;
    private String phoneNumber;

    public Phone(String phoneType, String phoneNumber){
        if (phoneType == null || phoneNumber == null){
            throw new IllegalArgumentException("The type and number cannot be null");
        }
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType(){
        return phoneType;
    }

    public void setPhoneType(String newPhoneType){
        phoneType = newPhoneType;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber;
    }

    @Override
    public String toString(){
        return phoneType + " " + phoneNumber;
    }


}
