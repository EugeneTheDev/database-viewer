package controller

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import model.AppDatabase
import model.EmployeeWithDepartment
import tornadofx.Controller
import tornadofx.observable

class EmployeesByDepartmentController : Controller() {
    private val numberTemplate = "(%d of %d):"

    val currentDepartment = SimpleStringProperty("Name")
    val numberOfDepartments = SimpleStringProperty(numberTemplate.format(0, 0))
    val data = mutableListOf<EmployeeWithDepartment>().observable()
    val prevButtonDisabled = SimpleBooleanProperty(true)
    val nextButtonDisabled = SimpleBooleanProperty(false)

    private var allEmployees = listOf<EmployeeWithDepartment>()
    private var allDepartments = listOf<String>()
    private var currentDepartmentIndex = 0

    fun loadData() = runAsync {
        allEmployees = AppDatabase.getEmployeesWithDepartment()
        allDepartments = allEmployees.map { it.department }.distinct()
    } ui {
        currentDepartmentIndex = 0
        updateData()
    }

    fun onClickNext() {
        if (currentDepartmentIndex < allDepartments.size - 1) {
            currentDepartmentIndex++
            updateData()
        }
    }

    fun onClickPrevious() {
        if (currentDepartmentIndex > 0) {
            currentDepartmentIndex--
            updateData()
        }
    }

    private fun updateData() {
        data.clear()
        currentDepartment.value = allDepartments[currentDepartmentIndex]
        numberOfDepartments.value = numberTemplate.format(currentDepartmentIndex + 1, allDepartments.size)
        data.addAll(allEmployees.filter { it.department == currentDepartment.value })
        prevButtonDisabled.value = currentDepartmentIndex == 0
        nextButtonDisabled.value = currentDepartmentIndex == allDepartments.lastIndex
    }

}