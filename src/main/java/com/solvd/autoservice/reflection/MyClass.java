package com.solvd.autoservice.reflection;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

public class MyClass {
    // Check modifiers
    private String checkPrivateModifier = "Field has private modifier";
    public String checkPublicModifier = "Field has public modifier";
    protected static final String CHECK_PROTECTED_MODIFIER = "Field has protected modifier";
    final String checkDefaultModifier = "Field has default modifier";

    private String str;

    // Check primitives
    public byte byteType;
    protected short shortType;
    int intType;
    public long longType;
    protected float floatType;
    double doubleType;
    public char charType;
    private boolean booleanType;

    // Constructors
    private MyClass() {
    }

    public MyClass(String checkPrivateModifier) {
        this.checkPrivateModifier = checkPrivateModifier;
    }

    public MyClass(
            byte byteType, int intType, long longType,
            float floatType, double doubleType, boolean booleanType
    ) {
        this.byteType = byteType;
        this.intType = intType;
        this.longType = longType;
        this.floatType = floatType;
        this.doubleType = doubleType;
        this.booleanType = booleanType;
    }

    public MyClass(
            String str, int intType, long longType, float floatType, double doubleType,
            char charType, boolean booleanType
    ) {
        this.str = str;
        this.intType = intType;
        this.longType = longType;
        this.floatType = floatType;
        this.doubleType = doubleType;
        this.charType = charType;
        this.booleanType = booleanType;
    }

    // Getters - Setters
    public String getCheckPrivateModifier() {
        return checkPrivateModifier;
    }

    public void setCheckPrivateModifier(String checkPrivateModifier) {
        this.checkPrivateModifier = checkPrivateModifier;
    }

    private String getCheckPublicModifier() {
        return checkPublicModifier;
    }

    private void setCheckPublicModifier(String checkPublicModifier) {
        this.checkPublicModifier = checkPublicModifier;
    }

    String getCheckDefaultModifier() {
        return checkDefaultModifier;
    }

    String getStr() {
        return str;
    }

    void setStr(String str) {
        this.str = str;
    }

    protected byte getByteType() {
        return byteType;
    }

    protected void setByteType(byte byteType) {
        this.byteType = byteType;
    }

    public short getShortType() {
        return shortType;
    }

    public void setShortType(short shortType) {
        this.shortType = shortType;
    }

    int getIntType() {
        return intType;
    }

    void setIntType(int intType) {
        this.intType = intType;
    }

    public long getLongType() {
        return longType;
    }

    public void setLongType(long longType) {
        this.longType = longType;
    }

    private float getFloatType() {
        return floatType;
    }

    private void setFloatType(float floatType) {
        this.floatType = floatType;
    }

    protected double getDoubleType() {
        return doubleType;
    }

    protected void setDoubleType(double doubleType) {
        this.doubleType = doubleType;
    }

    char getCharType() {
        return charType;
    }

    void setCharType(char charType) {
        this.charType = charType;
    }

    public boolean isBooleanType() {
        return booleanType;
    }

    public void setBooleanType(boolean booleanType) {
        this.booleanType = booleanType;
    }

    // Methods for operations with fields
    public String stringConcatenation(String str1, String str2) {
        return "Результат объединения: " + str1 + " " + str2;
    }

    protected int primitiveOperation(int num1, int num2) {
        return num1 + num2;
    }

    double doubleOperation(double dub1, double dub2, double dub3) {
        return dub1 + (dub2 * dub3);
    }

    public String mixedOperation(String str1, int num, double dub) {
        return "Результат: " + str1 + " " + (num + dub);
    }

    private void printInfo() {
        MY_LOGGER.info(ANSI_YELLOW + checkPrivateModifier + ANSI_RESET);
        System.out.println();
    }

    @Override
    public String toString() {
        return ANSI_GREEN + "Класс MyClass содержит следующие поля: " + ANSI_RESET +
                ANSI_YELLOW + "checkPrivateModifier = " + checkPrivateModifier +
                " | checkPublicModifier = " + checkPublicModifier +
                " | CHECK_PROTECTED_MODIFIER = " + CHECK_PROTECTED_MODIFIER +
                " | checkDefaultModifier = " + checkDefaultModifier +
                " | str = " + str +
                " | byteType = " + byteType +
                " | shortType = " + shortType +
                " | intType = " + intType +
                " | longType = " + longType +
                " | floatType = " + floatType +
                " | doubleType = " + doubleType +
                " | charType = " + charType +
                " | booleanType = " + booleanType + ANSI_RESET;
    }
}
