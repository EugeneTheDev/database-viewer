package view

import controller.EmployeesByDepartmentController
import javafx.geometry.Pos
import javafx.scene.control.TableView
import javafx.scene.layout.Priority
import model.EmployeeWithDepartment
import tornadofx.*

class EmployeesByDepartmentView : View("Employees by department") {
    private val controller: EmployeesByDepartmentController by inject()

    override val root = commonView {
        hbox {
            hboxConstraints {
                spacing = 10.0
                alignment = Pos.CENTER_LEFT
            }

            label("Department") {
                addClass(MainStyle.regularText)
            }

            label(controller.numberOfDepartments) {
                addClass(MainStyle.regularText)
            }

            button("<") {
                addClass(MainStyle.regularButton)
                action {
                    controller.onClickPrevious()
                }
                disableProperty().bind(controller.prevButtonDisabled)
            }

            label(controller.currentDepartment) {
                addClass(MainStyle.regularText)
            }

            button(">") {
                addClass(MainStyle.regularButton)
                action {
                    controller.onClickNext()
                }
                disableProperty().bind(controller.nextButtonDisabled)
            }
        }

        tableview(controller.data) {
            style(append = true) {
                vgrow = Priority.ALWAYS
            }
            addClass(MainStyle.regularTable)
            readonlyColumn("Tab number", EmployeeWithDepartment::tabNumber)
            readonlyColumn("Name", EmployeeWithDepartment::name)
            readonlyColumn("Gender", EmployeeWithDepartment::gender)
            readonlyColumn("INN", EmployeeWithDepartment::inn)
            readonlyColumn("Birth date", EmployeeWithDepartment::birthDate)
            readonlyColumn("Position", EmployeeWithDepartment::position)
            columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY
        }

    }

    override fun onDock() {
        controller.loadData()
        println()
    }
}