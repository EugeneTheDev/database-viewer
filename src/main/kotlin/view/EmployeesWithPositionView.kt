package view

import controller.EmployeesWithPositionController
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import model.EmployeeWithPosition
import tornadofx.*

class EmployeesWithPositionView : View("Employees with position") {
    private val controller: EmployeesWithPositionController by inject()

    override val root = vbox {
        addClass(MainStyle.regularView)
        vboxConstraints {
            alignment = Pos.TOP_LEFT
            padding = insets(10.0)
            spacing = 20.0

        }

        hbox {
            vboxConstraints {
                alignment = Pos.CENTER
            }

            label("Employees") {
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
            style {
                vgrow = Priority.ALWAYS
            }
            addClass(MainStyle.regularTable)
            readonlyColumn("Name", EmployeeWithPosition::name).pctWidth(50)
            readonlyColumn("Gender", EmployeeWithPosition::gender).pctWidth(50)
            smartResize()
        }

    }
}