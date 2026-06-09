package com.coditas.powerbridge.constants;

public final class ExceptionMessage {


    private ExceptionMessage(){}

    public static final String USER_NOT_AUTHENTICATED = "User is not authenticated";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String TASK_ALREADY_ASSIGNED = "Task already assigned";
    public static final String STATE_ALREADY_ADDED = "State is already added";
    public static final String STATE_NOT_FOUND = "State not found";
    public static final String STATE_MISMATCHED = "Access denied. You are not State head of the state you are trying to modify.";
    public static final String DISTRICT_NOT_FOUND = "District with given id does not exist";
    public static final String DISTRICT_ALREADY_EXIST = "District already exist";
    public static final String DISTRICT_MISMATCHED = "Access denied. You are not District head of the district you are trying to modify.";
    public static final String CITY_ALREADY_EXIST = "City already exist";
    public static final String CITY_NOT_FOUND = "City not found";
    public static final String NOT_A_CITY_HEAD = "Provided user is not city head";
    public static final String CITY_MISMATCHED = "Access denied. You are not City head of the city you are trying to modify.";
}
