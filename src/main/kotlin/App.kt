import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import tornadofx.*
import view.MainView


fun main(args: Array<String>) {
    launch<MyApp>(args)
}

class MyApp : App(MainView::class, MainStyle::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}

class MainStyle : Stylesheet() {
    companion object {
        val regularView by cssclass()
        val titleText by cssclass()
        val regularText by cssclass()
        val regularButton by cssclass()
        val regularTable by cssclass()

        private val mainColor = c("#373737")
    }

    init {
        regularView {
            backgroundColor += mainColor
        }

        titleText {
            textFill = Color.WHITE
            fontSize = 20.pt
        }

        regularText {
            textFill = Color.WHITE
            fontSize = 14.pt
        }

        regularButton {
            backgroundColor += Color.DODGERBLUE
            textFill = Color.WHITE

            and(focused) {
                borderColor += box(Color.SKYBLUE)
                borderRadius += box(2.pt)
                borderWidth += box(1.pt)
            }
        }

        regularTable {
            borderColor += box(mainColor)
            backgroundColor += mainColor

            tableCell {
                textFill = Color.WHITE
                borderColor += box(Color.DARKGRAY)
            }

            tableColumn {
                label {
                    backgroundColor += Color.GRAY
                    textFill = Color.WHITE
                    fontSize = 12.pt
                }
            }

            tableRowCell {
                backgroundColor += mainColor
                and(selected) {
                    backgroundColor += Color.SLATEGRAY
                }
            }

            scrollBar {
                backgroundColor += Color.DIMGREY
            }
        }

    }
}
