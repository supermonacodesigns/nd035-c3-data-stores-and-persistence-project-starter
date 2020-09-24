package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeService (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /* {C}reate */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /* {R}ead */
    public Employee getEmployee(Long id) {
        return employeeRepository.getOne(id);
    }

    public List<Employee> getEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> allEmployees = employeeRepository.findAll();
        List<Employee> suitableEmployees = new ArrayList<>();

        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        DayOfWeek date = employeeRequestDTO.getDate().getDayOfWeek();

        for (Employee employee : allEmployees) {
            if (employee.getDaysAvailable().contains(date) && employee.getSkills().containsAll(skills)) {
                suitableEmployees.add(employee);
            }
        }
        return suitableEmployees;
    }

    /* {U}pdate */

    /* {D}elete */
}
