import kotlinx.html.ButtonType
import react.*
import react.dom.a
import react.dom.button
import react.dom.div
import styled.css
import styled.styledA
import styled.styledButton
import styled.styledDiv

external interface ToDoProps : RProps {
    var index: Int
    var message: String
}

@JsExport
class ToDo : RComponent<ToDoProps, RState>() {
    override fun RBuilder.render() {
        div {
            styledA {
                css {
                    +ToDoListStyles.toDoText
                }
                +"${props.index}. ${props.message}"
            }
            styledButton {
                css {
                    +ToDoListStyles.toDoButton
                    +ToDoListStyles.toDoModificationButton
                }
                attrs {
                    type = ButtonType.button
                }
                +"수정"
            }
            styledButton {
                css {
                    +ToDoListStyles.toDoButton
                    +ToDoListStyles.toDoDeletionButton
                }
                attrs {
                    type = ButtonType.button
                }
                +"삭제"
            }
        }
    }
}

fun RBuilder.toDo(handler: ToDoProps.() -> Unit): ReactElement {
    return child(ToDo::class) {
        this.attrs(handler)
    }
}