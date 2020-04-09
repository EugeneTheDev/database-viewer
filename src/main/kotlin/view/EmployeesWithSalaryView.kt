package view

import controller.EmployeesWithSalaryController
import javafx.geometry.Pos
import javafx.scene.control.TableView
import javafx.scene.layout.Priority
import model.EmployeeWithSalary
import tornadofx.*

class EmployeesWithSalaryView : View("Employees with position") {
    private val controller: EmployeesWithSalaryController by inject()

    override val root = vbox {
        addClass(MainStyle.regularView)
        vboxConstraints {
            alignment = Pos.TOP_LEFT
            padding = insets(10.0)
            spacing = 20.0

        }

        hbox {
            hboxConstraints {
                alignment = Pos.CENTER
            }

            label("Employees with salary") {
                addClass(MainStyle.titleText)
            }

            region {
                style {
                    hgrow = Priority.ALWAYS
                }
            }

            button("Back") {
                addClass(MainStyle.regularButton)

                action {
                    replaceWith<MainView>()
                }
            }
        }

        tableview(controller.data) {
            style(append = true) {
                vgrow = Priority.ALWAYS
            }
            addClass(MainStyle.regularTable)
            readonlyColumn("Tab number", EmployeeWithSalary::tabNumber)
            readonlyColumn("Name", EmployeeWithSalary::name)
            readonlyColumn("Gender", EmployeeWithSalary::gender)
            readonlyColumn("INN", EmployeeWithSalary::inn)
            readonlyColumn("Birth date", EmployeeWithSalary::birthDate)
            readonlyColumn("Position", EmployeeWithSalary::position)
            readonlyColumn("Salary", EmployeeWithSalary::salary)
            columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY
        }

    }

    override fun onDock() {
        controller.loadData()
    }
}