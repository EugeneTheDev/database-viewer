package view

import controller.SearchEmploymentHistoryController
import controller.SearchQuery
import javafx.scene.control.TableView
import javafx.scene.layout.Priority
import model.EmploymentHistoryResult
import tornadofx.*

class SearchEmploymentHistoryView : View("Search employment history") {
    private val controller: SearchEmploymentHistoryController by inject()
    private val model = SearchQueryViewModel(SearchQuery())

    override val root = commonView {
        hbox {
            form {
                addClass(MainStyle.regularForm)
                style(append = true) {
                    hgrow = Priority.ALWAYS
                }

                fieldset("Employee data") {
                    field("Tab number") {
                        textfield(model.tabNumber) {
                            validator {
                                if (it.isNullOrBlank() || it.matches(Regex("""^\d+"""))) null
                                else error("Tab number is number")
                            }
                        }
                    }

                    field("Name") {
                        textfield(model.name) {
                            validator {
                                if (it.isNullOrBlank() || it.matches(Regex("""^[a-zа-я ]+$""", RegexOption.IGNORE_CASE))) null
                                else error("Name should contains only letters and spaces")
                            }
                        }
                    }
                }

                button("Search") {
                    addClass(MainStyle.regularButton)
                    action {
                        model.commit()
                        controller.search(model.item)
                    }
                }
            }

            region {
                style {
                    hgrow = Priority.ALWAYS
                }
            }

            region {
                style {
                    hgrow = Priority.ALWAYS
                }
            }
        }

        tableview(controller.searchResult) {
            style(append = true) {
                vgrow = Priority.ALWAYS
            }
            addClass(MainStyle.regularTable)
            readonlyColumn("Tab number", EmploymentHistoryResult::tabNumber)
            readonlyColumn("Employee name", EmploymentHistoryResult::employeeName)
            readonlyColumn("Position", EmploymentHistoryResult::position)
            readonlyColumn("Department", EmploymentHistoryResult::department)
            readonlyColumn("Hire date", EmploymentHistoryResult::hireDate)
            columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY
        }
    }
}

class SearchQueryViewModel(searchQuery: SearchQuery) : ItemViewModel<SearchQuery>(searchQuery) {
    val tabNumber = bind(SearchQuery::tabNumber)
    val name = bind(SearchQuery::name)
}
