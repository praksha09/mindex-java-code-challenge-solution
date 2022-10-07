package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeReportingStructureServiceImplTest {

    private String employeeReportingUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeReportingUrl = "http://localhost:" + port + "/employee/report-structure/{id}";
    }

    @Test
    public void testReport() {
        Employee testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

        // Create checks
        ReportingStructure report = restTemplate.getForEntity(employeeReportingUrl, ReportingStructure.class, testEmployee.getEmployeeId()).getBody();

        //Employee data check
        assertNotNull(report);
        assertEquals(testEmployee.getFirstName(),report.getEmployee().getFirstName());
        assertEquals(testEmployee.getLastName(),report.getEmployee().getLastName());
        assertEquals(testEmployee.getDepartment(),report.getEmployee().getDepartment());
        assertEquals(testEmployee.getPosition(),report.getEmployee().getPosition());

        //Check report count
        assertEquals(4, report.getNumberOfReports());
    }
}