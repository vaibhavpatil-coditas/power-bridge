package com.coditas.powerbridge.constants;

public final class ApiPaths {
    private ApiPaths() {}

    public static final String BASE_PATH = "/api/v1";
    public static final String ON_BOARD = "/onboard";

    public static final class SalesTeam {

        private SalesTeam(){}

        public static final String SALES_TEAM_MEMBER = "/sales-team-member";
        public static final String BASE = BASE_PATH + SALES_TEAM_MEMBER;
        public static final String ASSIGN_TASK = "/assign-task" + SalesTeam.SALES_TEAM_MEMBER;
        public static final String TASKS = "/tasks";
        public static final String ONBOARD_SALES_TEAM_MEMBER = ON_BOARD + SalesTeam.SALES_TEAM_MEMBER;
    }

    public static final class ServiceProvider{
        private ServiceProvider(){}

        public static final String ONBOARD_SERVICE_PROVIDER = ON_BOARD + "/service-provider";
    }

    public static final class Employee {
        private Employee(){}

        public static final String BASE = BASE_PATH + "/employees";
    }

    public static final class SuperAdmin {
        private SuperAdmin(){}

        public static final String BASE = BASE_PATH + "/super-admin";

        public static final String ONBOARD_MANAGEMENT_TEAM_MEMBER = ON_BOARD + ManagementTeam.MANAGEMENT_TEAM_MEMBER;
    }

    public static final class Auth {
        private Auth() {}

        public static final String BASE = BASE_PATH + "/auth";
        public static final String LOGIN = "/login";
    }

    public static final class ManagementTeam {
        private ManagementTeam(){}

        public static final String MANAGEMENT_TEAM_MEMBER = "/management-team-member";
        public static final String BASE = BASE_PATH + MANAGEMENT_TEAM_MEMBER;
    }

    public static final class StateHead {
        private StateHead() {}

        public static final String STATE_HEAD = "/state-head";
        public static final String BASE = BASE_PATH + STATE_HEAD;
        public static final String ONBOARD_STATE_HEAD = ON_BOARD + STATE_HEAD;
    }
}
