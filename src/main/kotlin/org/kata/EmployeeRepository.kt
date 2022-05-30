package org.kata

interface EmployeeRepository {
    fun all(): List<Employee>
}