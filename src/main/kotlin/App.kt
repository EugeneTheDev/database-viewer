import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.paint.Color
import org.jetbrains.exposed.sql.Database
import tornadofx.*
import view.MainView


fun main(args: Array<String>) {
    Database.connect("jdbc:postgresql://${args[0]}/${args[1]}", user = args[2], password = args[3])
    launch<MyApp>(args)
}

class MyApp : App(MainView::class, MainStyle::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1000.0, 600.0)
}

class MainStyle : Stylesheet() {
    companion object {
        val regularView by cssclass()
        val titleText by cssclass()
        val regularText by cssclass()
        val regularButton by cssclass()
        val regularTable by cssclass()
        val regularForm by cssclass()

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
            borderColor += box(Color.GRAY)
            borderWidth += box(2.pt)
            backgroundColor += mainColor

            tableCell {
                textFill = Color.WHITE
                borderColor += box(Color.DARKGRAY)
                alignment = Pos.CENTER

                and(empty) {
                    borderWidth += box(0.pt)
                }
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

        regularForm {
            fieldset {
                legend {
                    textFill = Color.WHITE
                    fontSize = 16.pt
                }

                field {
                    labelContainer {
                        label {
                            textFill = Color.WHITE
                            fontSize = 12.pt
                        }
                    }

                    inputContainer {
                        textField {
                            backgroundColor += Color.DIMGREY
                            textFill = Color.WHITE
                            fontSize = 12.pt
                        }
                    }
                }
            }
        }
    }
}
