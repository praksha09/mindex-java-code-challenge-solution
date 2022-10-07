package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeReportingStructureService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Service
public class EmployeeReportingStructureServiceImpl implements EmployeeReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure fetchReport(String id) {
        LOG.debug("Fetching ReportingStructure for employee with id [{}]", id);

        Employee employee = employeeService.read(id);

        return new ReportingStructure(employee, computeNumberOfReports(id));

    }


    @Override
    public int computeNumberOfReports(String id) {
        LOG.debug("Computing number of Reports for employee with id [{}]", id);

        Stack<String> employeeIdStack = new Stack<>();
        employeeIdStack.push(id);

        Set<String> visitedEmployeeIdSet = new HashSet<>();

        int numberOfReports = 0;

        while (!employeeIdStack.empty()) {
            String employeeId = employeeIdStack.pop();
            List<Employee> employeeList = employeeRepository.findByEmployeeId(employeeId).getDirectReports();

            if (employeeList != null) {
                for (Employee employee: employeeList) {
                    String empId = employee.getEmployeeId();
                    if (!visitedEmployeeIdSet.contains(employee)) {
                        visitedEmployeeIdSet.add(empId);
                        employeeIdStack.push(empId);
                        numberOfReports++;
                    }
                }
            }
        }

        return numberOfReports;
    }
}
