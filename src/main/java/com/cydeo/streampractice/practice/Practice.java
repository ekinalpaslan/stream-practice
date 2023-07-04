package com.cydeo.streampractice.practice;

import com.cydeo.streampractice.model.*;
import com.cydeo.streampractice.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        //TODO Implement the method
        return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        //TODO Implement the method
        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        //TODO Implement the method
        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        //TODO Implement the method
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        //TODO Implement the method
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        //TODO Implement the method
        return jobHistoryService.readAll();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        //TODO Implement the method
        return countryService.readAll().stream()
                .map(Country::getCountryName)
                .collect(Collectors.toList());
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .map(Department::getDepartmentName)
                .collect(Collectors.toList());
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(a -> a.getDepartmentName().equals("Steven"))
                .collect(Collectors.toList());
    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(a -> a.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(a -> a.getDepartmentName().equals("IT"))
                .map(Department::getLocation)
                .map(Location::getCountry)
                .map(Country::getRegion)
                .findFirst()
                .orElseThrow(() -> new Exception("Region of the IT department not found."));
    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        //TODO Implement the method
        return departmentService.readAll().stream()
                .filter(a -> a.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());
    }

    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .anyMatch(a -> a<1000);
    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getDepartment().getDepartmentName().equals("IT"))
                .map(Employee::getSalary)
                .anyMatch(b -> b>2000);
    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getSalary()<5000)
                .collect(Collectors.toList());
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> 6000<a.getSalary() && a.getSalary()<7000)
                .collect(Collectors.toList());

    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getFirstName().equals("Grant"))
                .filter(a -> a.getLastName().equals("Douglas"))
                .map(Employee::getSalary)
                .findFirst()
                .orElseThrow(() -> new Exception("Salary of Grant Douglas not found."));   //.orElse(null);  //get()
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .sorted()
//                .findFirst()
                .mapToLong(Employee::getSalary)
                .max()
                .orElseThrow(() -> new Exception("Salary of Grant Douglas not found."));
    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
        //TODO Implement the method

        Long maxSalary = employeeService.readAll().stream()
                .mapToLong(Employee::getSalary)
                .max()
                .getAsLong();

        return employeeService.readAll().stream()
                .filter(a -> a.getSalary()==maxSalary)
                .collect(Collectors.toList());
    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
        //TODO Implement the method
//        Long maxSalary = employeeService.readAll().stream()
//                .mapToLong(Employee::getSalary)
//                .max()
//                .getAsLong();

        Long maxSalary = getMaxSalary();

        return employeeService.readAll().stream()
                .filter(a -> a.getSalary()==maxSalary)
                .map(Employee::getJob)
                .findAny()
                .orElseThrow(() -> new Exception("Job of max salary employee not found."));
    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .mapToLong(Employee::getSalary)
                .max()
                .orElseThrow(() -> new Exception("Salary in Americas Region not found."));
    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        //TODO Implement the method
                 return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted()
                .skip(1)
                         .findFirst()
                         .orElseThrow(() -> new Exception("Second maximum salary not found."));
    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
        //TODO Implement the method
        Long secondMaxSalary = employeeService.readAll().stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted()
                .skip(1)
                .findFirst()
                .get();
        return employeeService.readAll().stream()
                .filter(a -> a.getSalary()==secondMaxSalary)
                .collect(Collectors.toList());
    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElseThrow(() -> new Exception("Salary not found."));
    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        //TODO Implement the method
        Long minSalary = employeeService.readAll().stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .get();
        return employeeService.readAll().stream()
                .filter(a-> a.getSalary().equals(minSalary))
                .collect(Collectors.toList());
    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new Exception("Second minimum salary not found."));

    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        //TODO Implement the method
        Long secondMinSalary = employeeService.readAll().stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst()
                .get();
        return employeeService.readAll().stream()
                .filter(a -> a.getSalary().equals(secondMinSalary))
                .collect(Collectors.toList());
    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        //TODO Implement the method
        Double averageSalary = getAverageSalary();
        return employeeService.readAll().stream()
                .filter(a -> a.getSalary()>averageSalary)
                .collect(Collectors.toList());
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        //TODO Implement the method
        Double averageSalary = getAverageSalary();
        return employeeService.readAll().stream()
                .filter(a -> a.getSalary()<averageSalary)
                .collect(Collectors.toList());
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        //TODO Implement the method

        return employeeService.readAll().stream()
                .collect(Collectors.groupingBy( employee -> employee.getDepartment().getId(), Collectors.toList() ));
    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        //TODO Implement the method
        return (long) departmentService.readAll().size();
    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getFirstName().equals("Alyssa"))
                .filter(a -> a.getDepartment().getDepartmentName().equals("Sales"))
                .filter(a -> a.getDepartment().getManager().equals("Eleni"))
                .findFirst()
                .orElseThrow(() -> new Exception("Employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales' not found."));
    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .collect(Collectors.toList());
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(a -> a.getStartDate().isAfter(LocalDate.of(2005, 1, 1)))
                .collect(Collectors.toList());
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(a -> a.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(b -> b.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
        //TODO Implement the method
        return jobHistoryService.readAll().stream()
                .filter(a -> a.getEndDate().equals(LocalDate.of(2007,12,31)))
                .filter(a -> a.getStartDate().equals(LocalDate.of(2007,1,1)))
                .map(JobHistory::getEmployee)
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("Shipping"))
                .findFirst()
                .orElseThrow(() -> new Exception("Employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping' not found."));
    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getJob().getId().contains("IT"))
                .collect(Collectors.toList());
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .filter(a -> a.getJob().getJobTitle().equals("Programmer"))
                .filter(a -> a.getDepartment().getDepartmentName().equals("IT"))
                .count();
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        //TODO Implement the method
        List<Integer> targetDepartmentIDs = Arrays.asList(50, 80, 100);
        return employeeService.readAll().stream()
                .filter(a -> targetDepartmentIDs.contains(a.getDepartment().getId()))
                .collect(Collectors.toList());
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(employee -> "" + employee.getFirstName().charAt(0) + employee.getLastName().charAt(0))
                .collect(Collectors.toList());
    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .map(employee -> "" + employee.getFirstName()  + " " + employee.getLastName())
                .collect(Collectors.toList());
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        //TODO Implement the method
        return employeeService.readAll().stream()
                .mapToInt(a -> a.getFirstName().length() + a.getLastName().length())
                .max()
                .orElseThrow(() -> new Exception("Name can not found."));

    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        //TODO Implement the method
        int maxLength = employeeService.readAll().stream()
                .mapToInt(a -> a.getFirstName().length() + a.getLastName().length())
                .max()
                .getAsInt();
        return employeeService.readAll().stream()
                .filter(a -> (a.getFirstName().length() + a.getLastName().length())==maxLength )
                .collect(Collectors.toList());
    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        //TODO Implement the method
        List<Integer> departmentIds = Arrays.asList(90, 60, 100, 120, 130);
        return employeeService.readAll().stream()
                .filter(a ->  departmentIds.contains(a.getDepartment().getId()))
                .collect(Collectors.toList());
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        //TODO Implement the method
        List<Integer> departmentIds = Arrays.asList(90, 60, 100, 120, 130);
        return employeeService.readAll().stream()
                .filter(a ->  !(departmentIds.contains(a.getDepartment().getId())))
                .collect(Collectors.toList());
    }

}
