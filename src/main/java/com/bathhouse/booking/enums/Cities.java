package com.bathhouse.booking.enums;

public enum Cities {
    ALMATY,
    SHIELI,
    NURSULTAN,
    SHYMKENT,
    QYZYLORDA,
    TARAZ;


    @Override
    public String toString() {
        String s = super.toString();
        if(s=="NURSULTAN"){
            return "Nur-Sultan";
        }
        s = s.substring(0, 1) + s.substring(1).toLowerCase();
        return s;
    }
}
