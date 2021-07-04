import react.*
import styled.css
import styled.styledDiv

data class ToDoListState(
    val toDoMessages: List<String>,
) : RState

@JsExport
class ToDoList: RComponent<RProps, ToDoListState>() {

    init {
        state = ToDoListState(listOf("test", "test2", "test3"))
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +MainStyles.todoListBody
            }
            state.toDoMessages.forEachIndexed { messageNumber, m ->
                toDo {
                    attrs {
                        index = messageNumber + 1
                        message = m
                        toDoListReference = this@ToDoList
                    }
                }
            }
            toDoInsertion {
                attrs {
                    toDoListReference = this@ToDoList
                }
            }
        }
    }
}

fun RBuilder.toDoList(): ReactElement {
    return child(ToDoList::class) {}
}