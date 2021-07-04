import kotlinx.css.*
import styled.StyleSheet

object MainStyles : StyleSheet("WelcomeStyles", isStatic = true) {
    val toDoListTitle by css {
        width = 100.vw
        height = 10.vh

        backgroundColor = rgb(0, 0, 0)
        color = rgb(255, 255, 255)
        fontSize = 50.px
    }

    val todoListBody by css {
        width = 100.vw
        height = 80.vh

        backgroundColor = rgb(255, 255, 255)
    }

    val footer by css {
        width = 100.vw
        height = 10.vh

        backgroundColor = rgb(0, 0, 0)
        color = rgb(255, 255, 255)
        fontSize = 20.px
    }

    val textCenterAlignment by css {
        textAlign = TextAlign.center
        display = Display.flex
        alignItems = Align.center
        justifyContent = JustifyContent.center
    }
} 
