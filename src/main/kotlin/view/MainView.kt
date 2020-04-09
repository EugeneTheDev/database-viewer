package view

import javafx.geometry.Pos
import tornadofx.*

class MainView : View("Database viewer") {

    override val root = vbox {
        addClass(MainStyle.regularView)
        style(append = true) {
            spacing = 20.pt
        }

        vboxConstraints {
            alignment = Pos.CENTER
        }

        label("Database viewer") {
            addClass(MainStyle.titleText)
            style(append = true) {
                fontFamily = "Arial Black"
            }
        }

        label("Select action") {
            addClass(MainStyle.regularText)
        }

        vbox {
            vboxConstraints {
                alignment = Pos.CENTER
            }

            style {
                paddingTop = 20.0
                spacing = 10.pt
            }

            button("Employees with position") {
                addClass(MainStyle.regularButton)
                action {
                    replaceWith<EmployeesWithPositionView>()
                }
            }

            button("Employees by department") {
                addClass(MainStyle.regularButton)
            }

            button("Search employment history") {
                addClass(MainStyle.regularButton)
            }
        }

    }
}
