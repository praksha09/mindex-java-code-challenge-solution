The following endpoints are added to existing codebase:
* READ  - ReportingStructure
    * HTTP Method: GET
    * URL: localhost:8080/employee/report-structure/{id}
    * RESPONSE: ReportingStructure
* CREATE  - Compensation
    * HTTP Method: POST
    * URL: localhost:8080/compensation
    * PAYLOAD: Compensation
    * RESPONSE: Compensation
* READ  - Compensation
    * HTTP Method: GET
    * URL: localhost:8080/compensation/employee/{id}
    * RESPONSE: Compensation

The ReportingStructure has a JSON schema of:
```json
{
  "type": "ReportingStructure",
  "properties": {
    "employee": {
      "type": "Employee"
    },
    "numberOfReports": {
      "type": "integer"
    }
  }
}
```

The Compensation has a JSON schema of:
```json
{
  "type": "Compensation",
  "properties": {
    "employee": {
      "type": "Employee"
    },
    "effectiveDate": {
      "type": "string",
      "format": "mm/dd/yyyy"
    },
    "salary": {
      "type": "integer"
    }
  }
}
```


### Task 1 Solution

<li> Created new data class <b>ReportingStructure.java</b> that has two properties: employee and numberOfReports. </li> 
<li> New REST endpoint /employee/report-structure/{id} created in <b>EmployeeController</b></li>
<li> <b>EmployeeReportingStructureService.java</b> compute numberOfReports on the fly.</li>
<li> Performed unit testing and achieved 100% coverage results <b>EmployeeReportingStructureServiceImplTest.java</b> </li>
<li> Performed local testing using Postman to test the endpoints and confirm results</li>

### Task 2 Solution

<li> Created new data class <b>Compensation.java</b> that has three properties: employee and  salary, and effectiveDate. </li> 
<li> New REST endpoint /compensation and compensation/employee/{id} created in <b>CompensationController</b> to create and read compensation</li>
<li> <b>CompensationService.java</b> to crete and find employee's compensation by Id.</li>
<li> Performed unit testing and achieved 100% coverage results <b>CompensationServiceImplTest.java</b> </li>
<li> Added Compensation input stream in <b>DataBootstrap.java</b></li>
<li> Performed local testing using Postman to test the endpoints and confirm results</li>