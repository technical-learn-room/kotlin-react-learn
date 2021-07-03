import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.button
import styled.css
import styled.styledDiv

external interface ToDoProps : RProps {
    val message: String
}

@JsExport
class ToDo : RComponent<ToDoProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {

            }
            a {
                +props.message
            }
            button {
                
            }
            button {

            }
        }
    }
}