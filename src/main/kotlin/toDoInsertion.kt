import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.form
import styled.css
import styled.styledButton
import styled.styledInput

external interface ToDoInsertionProps : RProps {
    var toDoListReference: ToDoList
}

data class ToDoInsertionState(
    val toDoMessage: String,
) : RState

@JsExport
class ToDoInsertion : RComponent<ToDoInsertionProps, ToDoInsertionState>() {

    init {
        state = ToDoInsertionState("")
    }

    override fun RBuilder.render() {
        div {
            styledInput {
                css {
                    +ToDoListStyles.toDoInput
                }
                attrs {
                    type = InputType.text
                    value = state.toDoMessage
                    onChangeFunction = {
                        setState(ToDoInsertionState((it.target as HTMLInputElement).value))
                    }
                }
            }
            styledButton {
                css {
                    +ToDoListStyles.toDoButton
                    +ToDoListStyles.toDoInsertionButton
                }
                attrs {
                    type = ButtonType.submit
                    onClickFunction = {
                        val newToDoList = props.toDoListReference.state.toDoMessages + state.toDoMessage
                        props.toDoListReference.setState(ToDoListState(newToDoList))
                    }
                }
                +"생성"
            }
        }
    }
}

fun RBuilder.toDoInsertion(handler: ToDoInsertionProps.() -> Unit): ReactElement {
    return child(ToDoInsertion::class) {
        this.attrs(handler)
    }
}