package model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object AppDatabase {

    fun getEmployeesWithSalary() = tx {
        Employee.innerJoin(Position).selectAll().map {
            EmployeeWithSalary(
                tabNumber = it[Employee.tabNumber],
                name = it[Employee.name],
                gender = it[Employee.gender],
                inn = it[Employee.inn],
                birthDate = it[Employee.birthDate],
                position = it[Employee.position],
                salary = it[Position.salary]
            )
        }
    }

    fun getEmployeesWithDepartment() = tx {
        Employee.innerJoin(Department).selectAll().map {
            EmployeeWithDepartment(
                tabNumber = it[Employee.tabNumber],
                name = it[Employee.name],
                gender = it[Employee.gender],
                inn = it[Employee.inn],
                birthDate = it[Employee.birthDate],
                position = it[Employee.position],
                department = it[Department.departmentName]
            )
        }
    }

    fun searchEmploymentHistory(tabNumber: Int? = null, name: String? = null) = tx {
        Employee.innerJoin(EmploymentHistory, onColumn = { Employee.tabNumber }, otherColumn = { employee })
            .innerJoin(Department, onColumn = { EmploymentHistory.department }, otherColumn = { id })
            .select {
                (tabNumber?.let { Employee.tabNumber.eq(it) } ?: Op.TRUE) and
                (name?.let { Employee.name like "%$it%" } ?: Op.TRUE)
            }
            .orderBy(
                Pair(Employee.tabNumber, SortOrder.ASC),
                Pair(EmploymentHistory.hireDate, SortOrder.DESC)
            )
            .map {
                EmploymentHistoryResult(
                    tabNumber = it[Employee.tabNumber],
                    employeeName = it[Employee.name],
                    position = it[EmploymentHistory.position],
                    department = it[Department.departmentName],
                    hireDate = it[EmploymentHistory.hireDate]
                )
            }
    }

}

private fun <T> tx(action: Transaction.() -> T) = transaction {
    addLogger(StdOutSqlLogger)
    action()
}