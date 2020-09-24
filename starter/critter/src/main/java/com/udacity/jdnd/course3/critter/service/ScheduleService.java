package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    // {C}reate
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // {R}ead

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByEmployeeId(Long employeeId) {
        return scheduleRepository.getSchedulesByEmployees_Id(employeeId);
    }

    public List<Schedule> getScheduleByPetId(Long petId) {
        return scheduleRepository.getSchedulesByPets_Id(petId);
    }

    public List<Schedule> getScheduleByCustomerId(Long customerId) {
        return scheduleRepository.getSchedulesByCustomer_Id(customerId);
    }

    // {U}pdate


    // {D}elete

}
