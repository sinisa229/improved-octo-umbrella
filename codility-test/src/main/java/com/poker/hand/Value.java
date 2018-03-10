package com.poker.hand;

public enum Value {
    V_2(2, "Two"),
    V_3(3, "Three"),
    V_4(4, "Four"),
    V_5(5, "Five"),
    V_6(6, "Six"),
    V_7(7, "Seven"),
    V_8(8, "Eight"),
    V_9(9, "Nine"),
    V_T(10, "Ten"),
    V_J(11, "Jack"),
    V_Q(12, "Queen"),
    V_K(13, "King"),
    V_A(14, "Ace");

    private final Integer weight;

    private final String description;

    Value(Integer weight, String description) {
        this.weight = weight;
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }
}
