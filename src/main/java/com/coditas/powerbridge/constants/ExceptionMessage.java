package com.coditas.powerbridge.constants;

public final class ExceptionMessage {

    private ExceptionMessage(){}

    public static final String USER_NOT_AUTHENTICATED = "User is not authenticated";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String TASK_ALREADY_ASSIGNED = "Task already assigned";
    public static final String STATE_ALREADY_ADDED = "State is already added";
    public static final String STATE_NOT_FOUND = "State not found";
    public static final String ROLE_MISMATCHED_STATE_HEAD = "Provided userId does not belong to state head";
}
