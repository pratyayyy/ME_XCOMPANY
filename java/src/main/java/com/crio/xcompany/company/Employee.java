package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private Gender gender;

    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone before implementing the logic.
    // This will ensure that the project can be compiled successfully.

    void assignManager(Employee employee)
    {
        
    }

    List getDirectReports()
    {
        return new ArrayList<String>();
    }

    List getTeamMates()
    {
        return new ArrayList<String>();
    }


    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }   
}
