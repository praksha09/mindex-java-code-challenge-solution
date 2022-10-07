package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeReportingStructureService {
    ReportingStructure fetchReport(String id);
    int computeNumberOfReports(String id);
}
