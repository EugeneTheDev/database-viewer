package model

import java.time.LocalDate


data class EmployeeWithSalary(
    val tabNumber: Int,
    val name: String,
    val gender: Char,
    val inn: String,
    val birthDate: LocalDate,
    val position: String,
    val salary: Int
)

data class EmployeeWithDepartment(
    val tabNumber: Int,
    val name: String,
    val gender: Char,
    val inn: String,
    val birthDate: LocalDate,
    val position: String,
    val department: String
)
