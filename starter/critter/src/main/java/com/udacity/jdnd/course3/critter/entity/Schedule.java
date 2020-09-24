package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Table generated in MySQL as 'schedule_employees'
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "employee_id"))  // renamed from MySQL default "employee_ids"
    private List<Employee> employees;

    // Table generated in MySQL as 'schedule pets'
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "pet_id"))  // renamed from MySQL default "pet_ids"
    private List<Pet> pets;

    private LocalDate date;

    @Column(name = "activity")  // renamed from MySQL default "activities"
    @ElementCollection
    private Set<EmployeeSkill> activities;


    public Schedule(long id, List<Employee> employees, List<Pet> pets, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }

    public Schedule() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
