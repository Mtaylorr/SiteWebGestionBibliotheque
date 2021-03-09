package com.ensta.librarymanager.exception;

import java.sql.SQLException;

public class DaoException extends Exception {
    public DaoException() {
        super("Dao Exception");
    }
}
