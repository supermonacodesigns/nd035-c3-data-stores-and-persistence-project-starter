package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.saveSchedule(convertScheduleDTOToEntity(scheduleDTO));

        return convertEntityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        scheduleList.forEach(schedule -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByPetId(petId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        scheduleList.forEach(schedule -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByEmployeeId(employeeId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        scheduleList.forEach(schedule -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByCustomerId(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        scheduleList.forEach(schedule -> scheduleDTOList.add(convertEntityToScheduleDTO(schedule)));
        return scheduleDTOList;
    }


    private static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (scheduleDTO.getEmployeeIds() != null && scheduleDTO.getEmployeeIds().size() > 0) {
            List<Employee> employees = new ArrayList<>();

            scheduleDTO.getEmployeeIds().forEach(id -> {
                Employee employee = new Employee();
                employee.setId(id);
                employees.add(employee);
            });
            schedule.setEmployees(employees);
        }

        if (scheduleDTO.getPetIds() != null && scheduleDTO.getEmployeeIds().size() > 0) {
            List<Pet> pets = new ArrayList<>();

            scheduleDTO.getPetIds().forEach(id -> {
                Pet pet = new Pet();
                pet.setId(id);
                pets.add(pet);
            });
            schedule.setPets(pets);
        }
        return schedule;
    }

    private static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (schedule.getEmployees() != null && schedule.getEmployees().size() > 0) {
            List<Long> employeeIds = new ArrayList<>();

            schedule.getEmployees().forEach(employee -> {
                employeeIds.add(employee.getId());
                scheduleDTO.setEmployeeIds(employeeIds);
            });
        }

        if (schedule.getPets() != null && schedule.getPets().size() > 0) {
            List<Long> petIds = new ArrayList<>();

            schedule.getPets().forEach(pet -> {
                petIds.add(pet.getId());
                scheduleDTO.setPetIds(petIds);
            });
        }

        return scheduleDTO;
    }
}
