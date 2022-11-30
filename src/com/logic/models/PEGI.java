package com.logic.models;

public enum PEGI {
    
    PUBLIC(0), PEGI7(1), PEGI16(2), PEGI12(3), 
    PEGI14(4), PEGI18(5);
    
    public int minYear;
    
    private PEGI(int minYear){
        this.minYear = minYear;
    }

    public int getMinYear() {
        return minYear;
    }
    
    
}
