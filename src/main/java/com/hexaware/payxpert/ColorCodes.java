package com.hexaware.payxpert;

public enum ColorCodes {
	
	RESET("\u001B[0m"),
	RED("\033[1;91m"),
	YELLOW("\u001B[33m"),
	BOLD("\u001B[1m"),
	GREEN("\033[1;92m"),
	CYAN("\033[1;96m");

    private final String code;

    ColorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
 