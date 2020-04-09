package model

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
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

}

private fun <T> tx(action: Transaction.() -> T) = transaction {
    addLogger(StdOutSqlLogger)
    action()
}