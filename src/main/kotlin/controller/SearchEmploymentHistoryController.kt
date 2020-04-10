package controller

import javafx.beans.property.SimpleStringProperty
import model.AppDatabase
import model.EmploymentHistoryResult
import tornadofx.Controller
import tornadofx.observable

class SearchEmploymentHistoryController : Controller() {
    val searchResult = mutableListOf<EmploymentHistoryResult>().observable()

    fun search(searchQuery: SearchQuery) = runAsync {
        searchResult.clear()
        searchResult.addAll(
            AppDatabase.searchEmploymentHistory(
                tabNumber = searchQuery.tabNumber.value.toIntOrNull(),
                name = searchQuery.name.value.takeIf { it.isNotEmpty() }
            )
        )
    }
}

class SearchQuery {
    val tabNumber = SimpleStringProperty("")
    val name = SimpleStringProperty("")
}