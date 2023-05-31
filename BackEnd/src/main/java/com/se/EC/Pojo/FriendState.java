package com.se.EC.Pojo;

public enum FriendState {
    Request(0), Wait(1), Commit(2);

    private final int value;

    FriendState(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
