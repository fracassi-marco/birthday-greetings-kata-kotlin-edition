package org.kata

import java.io.BufferedReader
import java.io.FileReader

class FileEmployeeRepository(private val fileName: String): EmployeeRepository {

    override fun all(): List<Employee> {
        val employees = ArrayList<Employee>()
        val `in` = BufferedReader(FileReader(fileName))
        `in`.readLines().drop(1).forEach { str ->
            val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            employees.add(Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]))
        }
        return employees
    }
}