package com.coditas.powerbridge.constants;

public interface ApiPaths {
    String BASE_PATH = "/api/v1";

    interface SalesTeam {
        String BASE = BASE_PATH + "/sales-team";

        String ON_BOARD = "/onboard";
    }

    interface Employee {
        String BASE = BASE_PATH + "/employees";
    }
}