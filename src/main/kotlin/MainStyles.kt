import kotlinx.css.*
import styled.StyleSheet

object MainStyles : StyleSheet("WelcomeStyles", isStatic = true) {
    val toDoListTitle by css {
        width = LinearDimension("100vw")
        height = LinearDimension("10vh")

        backgroundColor = rgb(0, 0, 0)
        color = rgb(255, 255, 255)
        fontSize = LinearDimension("50px")
    }

    val todoListBody by css {
        width = LinearDimension("100vw")
        height = LinearDimension("80vh")

        backgroundColor = rgb(255, 255, 255)
    }

    val footer by css {
        width = LinearDimension("100vw")
        height = LinearDimension("10vh")

        backgroundColor = rgb(0, 0, 0)
        color = rgb(255, 255, 255)
        fontSize = LinearDimension("20px")
    }

    val textCenterAlignment by css {
        textAlign = TextAlign.center
        display = Display.flex
        alignItems = Align.center
        justifyContent = JustifyContent.center
    }
} 
