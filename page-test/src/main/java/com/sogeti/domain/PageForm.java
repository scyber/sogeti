package com.sogeti.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PageForm {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String companyName;
        private String messageSent;

        private static final PageForm form = buildFormObject();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getMessageSent() {
        return messageSent;
    }

    public static PageForm getFormObject(){
        return form;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setMessageSent(String messageSent) {
        this.messageSent = messageSent;
    }

    private static PageForm buildFormObject(){
        PageForm pageForm = new PageForm();
        pageForm.setFirstName(getRandomFirstName());
        pageForm.setLastName(getRandomLastName());
        pageForm.setCompanyName(getRandomCompany());
        pageForm.setEmail(getRandomEmail());
        pageForm.setMessageSent(getRandomTextMessage());
        pageForm.setPhoneNumber(getRandomPhoneNumber());
        return pageForm;
    }

    private static String getRandomFirstName(){
        ArrayList<String> firstNames = new ArrayList<>(Arrays.asList("Joe", "Bob", "Rob", "Jet"));
        int index = new Random().nextInt(3);
        return firstNames.get(index);
    }
    private static String getRandomLastName(){
        ArrayList<String> lastNames = new ArrayList<>(Arrays.asList("Robinson", "Daly", "Bronson", "Howard"));
        int index = getRandomIndex(3);
        return lastNames.get(index);
    }
    private static String getRandomPhoneNumber(){
        String numbers = "";
        for(int i = 0; i<9; i++){
            int rnd = getRandomIndex(9);
            numbers = numbers + rnd;
        }
        return numbers;
    }
    private static String getRandomTextMessage(){
        String msg = "";
        ArrayList<String> words = new ArrayList<>(Arrays.asList("Simple", "text", "random", "message"));
        for (int i=0; i< 20;i++){
            int index = getRandomIndex(3);
            msg = msg + words.get(index);
        }
        return msg;
    }
    private static String getRandomCompany(){
        ArrayList<String> companies = new ArrayList<>(Arrays.asList("Test Company", "Random Test Company", "Generated new Company", "New Company"));
        int index = getRandomIndex(3);
        return companies.get(index);
    }
    private static String getRandomEmail(){
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("guest@test.com", "guest@test.org", "guest@test.biz", "hello@mail.com"));
        int index = getRandomIndex(3);
        return emails.get(index);
    }
    private static int getRandomIndex(int range){
        return new Random().nextInt(range);
    }


}
