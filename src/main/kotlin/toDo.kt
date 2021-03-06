import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.a
import react.dom.button
import react.dom.div
import styled.*

external interface ToDoProps : RProps {
    var index: Int
    var message: String
    var toDoListReference: ToDoList
}

data class ModificationToDoState(
    val modificationMode: Boolean,
    val modificationToDoMessage: String,
) : RState

@JsExport
class ToDo : RComponent<ToDoProps, ModificationToDoState>() {

    init {
        state = ModificationToDoState(
            modificationMode = false,
            modificationToDoMessage = ""
        )
    }

    override fun RBuilder.render() {
        div {
            if (state.modificationMode) {
                styledInput {
                    css {
                        +ToDoListStyles.toDoInput
                    }
                    attrs {
                        type = InputType.text
                        value = state.modificationToDoMessage
                        onChangeFunction = {
                            val modificationToDoState =
                                ModificationToDoState(
                                    modificationMode = true,
                                    modificationToDoMessage = (it.target as HTMLInputElement).value,
                                )
                            setState(modificationToDoState)
                        }
                    }
                }
                styledButton {
                    css {
                        +ToDoListStyles.toDoButton
                        +ToDoListStyles.toDoModificationButton
                    }
                    attrs {
                        type = ButtonType.button
                        onClickFunction = {
                            val mappedToDoList =
                                props.toDoListReference.state.toDoMessages.mapIndexed { i, m ->
                                    if (i + 1 == props.index) {
                                        return@mapIndexed state.modificationToDoMessage
                                    }
                                    return@mapIndexed m
                                }
                            props.toDoListReference.setState(ToDoListState(mappedToDoList))

                            setState(getOutModificationMode())
                        }
                    }
                    +"??????"
                }
                styledButton {
                    css {
                        +ToDoListStyles.toDoButton
                        +ToDoListStyles.toDoDeletionButton
                    }
                    attrs {
                        type = ButtonType.button
                        onClickFunction = {
                            setState(getOutModificationMode())
                        }
                    }
                    +"??????"
                }
            } else {
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
                        onClickFunction = {
                            val modificationToDoState =
                                ModificationToDoState(
                                    modificationMode = true,
                                    modificationToDoMessage = "",
                                )
                            setState(modificationToDoState)
                        }
                    }
                    +"??????"
                }
                styledButton {
                    css {
                        +ToDoListStyles.toDoButton
                        +ToDoListStyles.toDoDeletionButton
                    }
                    attrs {
                        type = ButtonType.button
                        onClickFunction = {
                            val filteredToDoList =
                                props.toDoListReference.state.toDoMessages.filterIndexed { i, _ ->
                                    i + 1 != props.index
                                }
                            props.toDoListReference.setState(ToDoListState(filteredToDoList))
                        }
                    }
                    +"??????"
                }
            }
        }
    }
}

fun RBuilder.toDo(handler: ToDoProps.() -> Unit): ReactElement {
    return child(ToDo::class) {
        this.attrs(handler)
    }
}

fun getOutModificationMode() =
    ModificationToDoState(
        modificationMode = false,
        modificationToDoMessage = "",
    )