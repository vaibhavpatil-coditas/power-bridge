package com.coditas.powerbridge.constants;

public final class ExceptionMessage {
    private ExceptionMessage(){}
    public static final String STATE_NOT_FOUND = "State not found";
    public static final String DISTRICT_NOT_FOUND = "District not found";
    public static final String CITY_NOT_FOUND = "City not found";
    public static final String AREA_NOT_FOUND = "Area not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String STATE_MISMATCHED = "Access denied. You are not State head of the state you are trying to modify.";
    public static final String DISTRICT_MISMATCHED = "Access denied. You are not District head of the district you are trying to modify.";
    public static final String CITY_MISMATCHED = "Access denied. You are not City head of the city you are trying to modify.";
    public static final String AREA_MISMATCHED = "Access denied. You are not City head of the area you are trying to modify.";
    public static final String DISTRICT_ALREADY_EXIST = "District already exist";
    public static final String CITY_ALREADY_EXIST = "City already exist";
    public static final String STATE_ALREADY_ADDED = "State is already added";
    public static final String TASK_ALREADY_ASSIGNED = "Task already assigned";
    public static final String SERVICE_PROVIDER_ALREADY_EXIST = "Service provider is already onboarded";
    public static final String NOT_A_CITY_HEAD = "Provided user is not city head";
    public static final String NOT_A_LOCAL_TECHNICIAN = "Provided user is not a technician";
    public static final String NOT_A_BILLER = "Provided user is not a biller";
    public static final String USER_NOT_AUTHENTICATED = "User is not authenticated";
    public static final String USERNAME_OR_PASSWORD_MISMATCHED = "Username or password is wrong";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
}
