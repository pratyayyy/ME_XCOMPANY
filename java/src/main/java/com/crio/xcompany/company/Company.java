package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Company{
    private String companyName;
    private Employee founder;
    private Map<String,Employee> employeeBook;
    private Map<String,ArrayList<Employee>> hierarchy;
    

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<String,Employee>();
        employeeBook.put(founder.getName(), founder);
        hierarchy = new HashMap<String,ArrayList<Employee>>();
    }
    

    public static Company create(String companyName, Employee founder){
        return new Company(companyName,founder);
    } 


    public String getCompanyName() {
        return companyName;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.

    public void registerEmployee(String employeeName, Gender gender)
    {
        Employee emp = new Employee(employeeName,gender);
        employeeBook.put(employeeName,emp);
        hierarchy.put(employeeName,new ArrayList<>());
        System.out.println("EMPLOYEE_REGISTRATION_SUCCEEDED");
    }

    public Employee getEmployee(String employeeName)
    {
       Employee emp = employeeBook.getOrDefault(employeeName,null);
       if(emp == null) System.out.println("EMPLOYEE_NOT_FOUND");
       return emp;
    }

    public void deleteEmployee(String employeeName)
    {
      employeeBook.remove(employeeName);  
      hierarchy.remove(employeeName);
      System.out.println("EMPLOYEE_DELETION_SUCCEEDED");
    }

    public void assignManager(String employeeName, String managerName)
    {
        hierarchy.get(managerName).add(employeeBook.get(employeeName));
    }

    public List getDirectReports(String managerName)
    {
        return hierarchy.get(managerName);
    }

    public List getTeamMates(String employeeName)
    {
        String managerName = "";

        for(Map.Entry<String, ArrayList<Employee>> entry : hierarchy.entrySet())
        {
            for(Employee emp :  entry.getValue())
            {
                if(emp.getName().equals(employeeName))
                {
                    managerName = entry.getKey();
                    break;
                }
            }
        }
        List<Employee> empList = new ArrayList<>();
        empList = hierarchy.get(managerName);
        empList.add(employeeBook.get(managerName));
        empList.sort(Comparator.comparing(Employee::getName));
        return empList;
    }

    public List<List<Employee>> getEmployeeHierarchy(String managerName)
    {
        List<List<Employee>> empHierarchy = new ArrayList<>();
        Queue<String> Q = new LinkedList<>();
        Q.add(managerName);
        HashSet<String> visited = new HashSet<>();
        List<Employee> temp = new ArrayList<>();
        temp.add(employeeBook.get(managerName));
        empHierarchy.add(temp);
        visited.add(managerName);

        while(!Q.isEmpty())
        {
            int size = Q.size();
            temp = new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                String empName = Q.poll();
                for(Employee emp : hierarchy.getOrDefault(empName, new ArrayList<>()))
                {
                    String name = emp.getName();
                    if(!visited.contains(name))
                    {
                        visited.add(name);
                        Q.add(name);
                        temp.add(emp);
                    }
                }
            }
            temp.sort(Comparator.comparing(Employee::getName));
            if(!temp.isEmpty())
            {
                empHierarchy.add(temp);
            }
        }
        return empHierarchy;
    }
}
