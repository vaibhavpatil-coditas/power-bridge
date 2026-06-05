package com.coditas.powerbridge.constants;

public final class ApiPaths {
    private ApiPaths() {}

    public static final String BASE_PATH = "/api/v1";

    public static final class SalesTeam {
        private SalesTeam(){}

        public static final String BASE = BASE_PATH + "/sales-team";

        public static final String ON_BOARD = "/onboard";
    }

    public static final class Employee {
        private Employee(){}

        public static final String BASE = BASE_PATH + "/employees";
    }

    public static final class SuperAdmin {
        private SuperAdmin(){}

        public static final String SUPER_ADMIN_BASE = BASE_PATH + "/super-admin";

        public static final String ONBOARD_MANAGEMENT_TEAM_MEMBER = "/onboard/management-team-member";
    }

    public static final class Auth {
        private Auth() {}

        public static final String BASE = BASE_PATH + "/auth";

        public static final String LOGIN = "/login";
    }

    public static final class ManagementTeam {
        private ManagementTeam(){}

        public static final String BASE = BASE_PATH + "/management-team";
    }
}