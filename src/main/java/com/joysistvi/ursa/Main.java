package com.joysistvi.ursa;

import com.joysistvi.ursa.config.DbConnection;

public class Main {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DbConnection.getInstance().closePool();
            System.out.println("Database pool closed safely.");
        }));

        DbConnection.init();

    }
}