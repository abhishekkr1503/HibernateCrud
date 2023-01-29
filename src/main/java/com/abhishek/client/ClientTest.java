package com.abhishek.client;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.abhishek.entities.Employee;
import com.abhishek.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Session session = HibernateUtil.getSessionFactory().openSession();)
		{
//			String SQL = "SELECT VERSION()";
//			NativeQuery nativeQuery = session.createNativeQuery(SQL);
//			Object object = nativeQuery.getSingleResult();
//			String result = (String)object;
//			System.out.println("MYSQL database Version is: "+result);
			
//			createEmployeeInDB(session);
			int employeeId = 2;
			//readEmployeeById(session,employeeId);
			double newSal = 1000.00;
			//updateEmployeeSalaryById(session,newSal,employeeId);
			
			deleteEmployeeById(session,employeeId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
private static void deleteEmployeeById(Session session, int employeeId) {
	Employee employee = session.get(Employee.class, employeeId);
	
		if(employee != null)
		{
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
		}
		else
		{
			System.out.print("Employee does not exist with ID = "+employeeId);
		}
	}
// Update query method
private static void updateEmployeeSalaryById(Session session, double newSal, int employeeId) {
		Employee employee = session.get(Employee.class, employeeId);
		employee.setSalary(newSal);
		
		session.beginTransaction();
		session.update(employee);
		session.getTransaction().commit();
	}

// Read query from database
	private static void readEmployeeById(Session session, int employeeId) {
		Employee employee = session.get(Employee.class, employeeId);
		System.out.println(employee);		
	}

//	insert query in database
	private static void createEmployeeInDB(Session session) {
		Employee employee = createEmployee();
		session.beginTransaction();
		Serializable serializable = session.save(employee);
		
		Integer id = (Integer) serializable;
		session.getTransaction().commit();
		
		System.out.println("Employee is created with Id = "+id);
	}

	private static Employee createEmployee() 
	{
		Employee employee = new Employee();
		//employee.setEmployeeId(1);
		employee.setEmployeeName("John");
		employee.setEmail("johnkr@gmail.com");
		employee.setSalary(8000.00);
		employee.setDoj(new Date());
		return employee;
	}

}
