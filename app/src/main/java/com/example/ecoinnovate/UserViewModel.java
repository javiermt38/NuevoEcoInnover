package com.example.ecoinnovate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class UserViewModel extends ViewModel {
    private final MutableLiveData <String>name = new MutableLiveData<>();
    private final MutableLiveData <Integer>birthYear = new MutableLiveData<>();
    private final MutableLiveData <String>message = new MutableLiveData<>();


    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<Integer> getBirthYear() {
        return birthYear;
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }
    public void SetName(String UserName){
        name.setValue(UserName);
    }
    public void SetBirthYear(Integer UserBirthYear){
        birthYear.setValue(UserBirthYear);
        upDateMessage();
    }
    public void upDateMessage(){
        if (name.getValue()!=null && birthYear.getValue()!=null){
            int ege = caculateAge(birthYear.getValue());
            String userMessage = "Hola " + name.getValue() + " tienes " + ege + " años";
            message.setValue(userMessage);
        }
    }
    private int caculateAge(int birthYear){
        Calendar Today = Calendar.getInstance();
        int currentYear = Today.get(Calendar.YEAR);
        return currentYear - birthYear;
    }


}
