import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import styled.css
import styled.styledDiv

@JsExport
class Main : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                + MainStyles.toDoListTitle
                + MainStyles.textCenterAlignment
            }
            a {
                +"TO DO LIST"
            }
        }
        toDoList()
        styledDiv {
            css {
                +MainStyles.footer
                +MainStyles.textCenterAlignment
            }
            a(href = "https://github.com/technical-learn-room/kotlin-react-learn") {
                +"GITHUB"
            }
        }
    }
}
