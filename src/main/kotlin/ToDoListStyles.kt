import kotlinx.css.*
import styled.StyleSheet

object ToDoListStyles : StyleSheet("ToDoListStyles", isStatic = true) {
    val toDoText by css {
        fontSize = 20.px
        color = rgb(0, 0, 0)
    }

    val toDoButton by css {
        marginLeft = 10.px

        paddingTop = 2.px
        paddingRight = 4.px
        paddingBottom = 2.px
        paddingLeft = 4.px

        color = rgb(0, 0, 0)

        textAlign = TextAlign.center
    }

    val toDoModificationButton by css {
        backgroundColor = Color.myGreen
        borderColor = Color.myGreen
    }

    val toDoDeletionButton by css {
        backgroundColor = Color.myBlue
        borderColor = Color.myBlue
    }

    val toDoInput by css {
        marginTop = 10.px

        width = 400.px
        height = 20.px

        color = rgb(0, 0, 0)
    }

    val toDoInsertionButton by css {
        backgroundColor = Color.myDarkGray
        borderColor = Color.myDarkGray
    }
}

val Color.Companion.myGreen
    get() = rgb(56, 255, 189)
val Color.Companion.myBlue
    get() = rgb(102, 179, 255)
val Color.Companion.myDarkGray
    get() = rgb(169, 169, 169)