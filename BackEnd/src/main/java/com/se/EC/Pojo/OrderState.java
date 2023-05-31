package com.se.EC.Pojo;

public enum OrderState {
    Unpaid(0), Payed(1), Send(2), Received(3);

    private final int value;

    OrderState(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
