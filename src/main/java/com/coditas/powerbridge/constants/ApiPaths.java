package com.coditas.powerbridge.constants;

public final class ApiPaths {
    private ApiPaths() {}

    public static final String BASE_PATH = "/api/v1";
    public static final String ON_BOARD = "/onboard";

    public static final class SalesTeamMember {

        private SalesTeamMember(){}

        public static final String BASE = BASE_PATH + "/sales-team-members";
        public static final String TASKS = "/tasks";
    }

    public static final class ServiceProvider{

        private ServiceProvider(){}

        public static final String BASE = BASE_PATH + "/service-providers";
    }

    public static final class Employee {
        private Employee(){}

        public static final String BASE = BASE_PATH + "/employees";
    }

    public static final class SuperAdmin {
        private SuperAdmin(){}

        public static final String BASE = BASE_PATH + "/super-admin";
    }

    public static final class Auth {
        private Auth() {}

        public static final String BASE = BASE_PATH + "/auth";
        public static final String LOGIN = "/login";
    }

    public static final class ManagementTeam {
        private ManagementTeam(){}

        public static final String BASE = BASE_PATH + "/management-team-members";
    }

    public static final class StateHead {
        private StateHead() {}

        public static final String BASE = BASE_PATH + "/state-heads";
    }

    public static final class Task {
        private Task(){}

        public static final String BASE = BASE_PATH + "/tasks";
    }

    public static final class State {
        private State(){}

        public static final String BASE = BASE_PATH + "/states";
        public static final String HEAD = "/head";
        public static final String ID = "/state_id";
    }

    public static final class District {
        private District(){}

        public static final String BASE = BASE_PATH + "/districts";
        public static final String ID = "/{district_id}";
        public static final String HEAD = "/head";
    }

    public static final class DistrictHead {
        private DistrictHead(){}

        public static final String BASE = BASE_PATH + "/district-heads";
    }

    public static final class City {
        private City(){}

        public static final String ID = "/{city_id}";
        public static final String BASE = BASE_PATH + "/districts" + "/{district_id}" + "/cities";
    }
}
