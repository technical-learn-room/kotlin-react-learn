import react.*
import styled.css
import styled.styledDiv

@JsExport
class ToDoList: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +MainStyles.todoListBody
            }
            +"나다"
        }
    }
}

//fun RBuilder.toDoList(handler: RProps.() -> Unit): ReactElement {
//    return child(ToDoList::class) {
//        this.attrs(handler)
//    }
//}

fun RBuilder.toDoList(): ReactElement {
    return child(ToDoList::class) {}
}