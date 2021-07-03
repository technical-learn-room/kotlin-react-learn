import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import styled.css
import styled.styledDiv

external interface WelcomeProps : RProps

data class ToDoListState(val name: String) : RState

@JsExport
class ToDoList : RComponent<WelcomeProps, ToDoListState>() {

    override fun RBuilder.render() {
        styledDiv {
            css {
                + MainStyles.toDoListTitle
                + MainStyles.textCenterAlignment
            }
            a {
                + "TO DO LIST"
            }
        }
        styledDiv {
            css {
                + MainStyles.todoListBody
            }
        }
        styledDiv {
            css {
                + MainStyles.footer
                + MainStyles.textCenterAlignment
            }
            a(href = "https://logos-download.com/wp-content/uploads/2016/09/GitHub_logo.png") {
                + "GITHUB"
            }
        }
    }
}
