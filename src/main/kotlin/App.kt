import javafx.scene.Scene
import tornadofx.*
import view.MainView


fun main(args: Array<String>) {
    launch<MyApp>(args)
}

class MyApp : App(MainView::class) {
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}