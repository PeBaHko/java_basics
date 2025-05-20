package org.example;

public class ErrorMessage {
    private final String message;

    public ErrorMessage(long id) {
        message = "{"+ System.lineSeparator() +
                "\t\"message\": "+"\"Новость с ID " + id + " не найдена.\""
                     + System.lineSeparator() + "}";
    }

    public String getMessage() {
        return message;
    }
}