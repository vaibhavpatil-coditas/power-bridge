package com.coditas.powerbridge.constants;

public final class ApiPaths {
    private ApiPaths() {}

    public static final String BASE_PATH = "/api/v1";
    public static final String ON_BOARD = "/onboard";

    public static final class SalesTeamMember {
        private SalesTeamMember() {}
        public static final String BASE = BASE_PATH + "/sales-team-members";
        public static final String TASKS = "/tasks";
    }

    public static final class ServiceProvider {
        private ServiceProvider() {}
        public static final String BASE = "/service-providers";
        public static final String METER = "/meters";
    }


    public static final class SuperAdmin {
        private SuperAdmin() {}
        public static final String BASE = BASE_PATH + "/super-admin";
    }

    public static final class Auth {
        private Auth() {}
        public static final String BASE = BASE_PATH + "/auth";
        public static final String LOGIN = "/login";
    }

    public static final class ManagementTeam {
        private ManagementTeam() {}
        public static final String BASE = BASE_PATH + "/management-team-members";
    }

    public static final class StateHead {
        private StateHead() {}
        public static final String BASE = BASE_PATH + "/state-heads";
    }

    public static final class Task {
        private Task() {}
        public static final String TASKS = "/tasks";
        public static final String BASE = BASE_PATH + TASKS;
    }

    public static final class State {
        private State() {}
        public static final String STATES = "/states";
        public static final String HEAD = "/head";
        public static final String ID = "/{state-id}";
    }

    public static final class District {
        private District() {}
        public static final String BASE = BASE_PATH + "/districts";
        public static final String ID = "/{district-id}";
        public static final String HEAD = "/head";
    }

    public static final class DistrictHead {
        private DistrictHead() {}
        public static final String BASE = BASE_PATH + "/district-heads";
    }

    public static final class City {
        private City() {}
        public static final String ID = "/{city-id}";
        public static final String CITIES = "/cities";
        public static final String BASE = BASE_PATH + "/districts" + District.ID + CITIES;
        public static final String HEAD = "/head";
    }

    public static final class CityHead {
        private CityHead() {}
        public static final String BASE = BASE_PATH + "/city-heads";
    }

    public static final class LocalTechnician {
        private LocalTechnician() {}
        public static final String BASE = BASE_PATH + City.CITIES + City.ID + "/local-technicians";
    }

    public static final class Biller {
        private Biller() {}
        public static final String BASE = BASE_PATH + City.CITIES + City.ID + "/biller";
    }

    public static final class CRM {
        private CRM() {}
        public static final String BASE = BASE_PATH + City.CITIES + City.ID + "/crm";
    }

    public static final class Area {
        private Area() {}
        public static final String BASE = BASE_PATH + City.CITIES + City.ID + "/areas";
        public static final String ID = "/{area-id}";
        public static final String TECHNICIAN = ID + "/technician";
        public static final String BILLER = ID + "/biller";
    }

    public static final class Customer {



        private Customer() {}
        public static final String CUSTOMERS = "/customers";
        public static final String BASE = BASE_PATH + CUSTOMERS;
        public static final String ASSIGN_SERVICE_PROVIDER = "/assign-service-provider";
        public static final String ID = "/{customer-id}";
        public static final String CUSTOMER_QUERY = "/customer-queries";
        public static final String CUSTOMER_QUERY_ID = "/{queries-id}";
        public static final String CUSTOMER_QUERY_RESOLVE = "/resolve";
        public static final String CUSTOMER_QUERY_ESCALATE_TO_M1 = "/escalate-m1";
        public static final String CUSTOMER_QUERY_ESCALATE_TO_M2 = "/escalate-m2";
    }

    public static final class ElectricityBill {
        private ElectricityBill() {}
        public static final String ELECTRICITY_BILL = "/electricity-bills";
    }

    public static final class BillerQuery {
        private BillerQuery() {}
        public static final String BILLER_QUERY = "/biller-queries";
    }

    public static final class Employee {


        private Employee() {}
        public static final String BASE = BASE_PATH + "/employees";
        public static final String SALES_TEAM = "/sales-team";
        public static final String OPERATIONS_HEAD = "/operations-head";
        public static final String BPO = "/bpo";
        public static final String BPO_ID = "/{bpo-id}";
        public static final String MANAGER1 = "/manager-1";
        public static final String MANAGER2 = "/manager-2";
    }
}