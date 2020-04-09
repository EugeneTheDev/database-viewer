package controller

import model.AppDatabase
import model.EmployeeWithSalary
import tornadofx.Controller
import tornadofx.observable

class EmployeesWithSalaryController : Controller() {
    var data = mutableListOf<EmployeeWithSalary>().observable()

    fun loadData() = runAsync {
        data.let {
            it.clear()
            it.addAll(AppDatabase.getEmployeesWithSalary().observable())
        }
    }
}