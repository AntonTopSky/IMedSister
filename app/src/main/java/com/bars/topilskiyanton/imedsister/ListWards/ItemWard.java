package com.bars.topilskiyanton.imedsister.ListWards;

/**
 * Created by Topilskiy Anton on 20.02.2018.
 */

public class ItemWard {

    private String numberWard;
    private String quanity;
    private String gender;
    private String capacity;

    public ItemWard(String numberWard, String quanity, String capacity, String gender) {
        this.numberWard = numberWard;
        this.capacity = capacity;
        this.quanity = quanity;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getNumberWard() {
        return numberWard;
    }

    public String getQuanity() {
        return quanity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setNumberWard(String numberWard) {
        this.numberWard = numberWard;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
