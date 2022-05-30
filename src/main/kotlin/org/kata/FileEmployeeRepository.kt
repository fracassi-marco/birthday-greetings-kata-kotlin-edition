package org.kata

import java.io.BufferedReader
import java.io.FileReader

class FileEmployeeRepository(private val fileName: String): EmployeeRepository {

    override fun all(): List<Employee> {
        return lines().skipHeader()
            .map { line -> line.split(", ") }
            .map { fields -> Employee(fields[1], fields[0], fields[2], fields[3])
        }
    }

    private fun lines() = BufferedReader(FileReader(fileName)).readLines()

    private fun List<String>.skipHeader() = this.drop(1)
}