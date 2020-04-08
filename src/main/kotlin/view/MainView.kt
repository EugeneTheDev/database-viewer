package view

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*
import java.net.URI

class MainView : View("My View") {

    override val root = vbox {
        title = "Database viewer"

        vboxConstraints {
            alignment = Pos.CENTER
        }

        label("Database viewer") {
            style {
                fontSize = 20.pt
                fontFamily = "Arial Black"
            }
        }

        label("Select action") {
            style {
                paddingTop = 32
                fontSize = 14.pt
            }
        }

        hbox {
            hboxConstraints {
                alignment = Pos.CENTER
            }

            style {
                paddingTop = 20.0
                spacing = 10.pt
            }

            button("One") {

            }

            button("Two") {

            }
        }

    }
}
