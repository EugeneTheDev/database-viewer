package view

import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.*

fun View.commonView(op: VBox.() -> Unit) = vbox {
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

        label(title) {
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

    op()
}