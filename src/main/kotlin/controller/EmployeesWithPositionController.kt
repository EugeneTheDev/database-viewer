package controller

import model.EmployeeWithPosition
import tornadofx.Controller
import tornadofx.observable

class EmployeesWithPositionController : Controller() {
    val data = listOf(
        EmployeeWithPosition("Name", 'm'),
        EmployeeWithPosition("Name 1", 'f')

    ).observable()
}