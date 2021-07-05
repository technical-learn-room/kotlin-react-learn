# 코틀린으로 리액트 프로그래밍하기!
![kotlin react image](https://user-images.githubusercontent.com/48639421/124257806-b2094b00-db67-11eb-8da6-87e764023b60.png)

### [코틀린으로 리액트 프로그래밍하기 - 블로그](https://velog.io/@dhwlddjgmanf/%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9C%BC%EB%A1%9C-%EB%A6%AC%EC%95%A1%ED%8A%B8-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D%ED%95%98%EA%B8%B0)  

> 코틀린으로 리액트 프로그래밍을 하시기 전에  
> `수신 객체 지정 람다`와 `프로퍼티 위임(by)`에 대해서 미리 선생하시는 걸 추천드립니다.  

## 컴포넌트 생성하기
```kotlin
@JsExport
class TestComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            a {
                +"Hello, world!"
            }
        }
    }
}
```
컴포넌트를 생성하기 위해서는 `RComponent`를 상속받는 클래스를 만들면 됩니다.  
그리고 `RBuilder.render()` 확장 함수를 오버라이딩하고  
`HTML Kotlin DSL`을 이용하여 마음껏 코드를 작성하시면 됩니다.  

## 컴포넌트 조립하기
```kotlin
@JsExport
class TestComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            aComponent {}
        }
    }
}

@JsExport
class AComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        a {
            +"My name is jaychy-yy!"
        }
    }
}

fun RBuilder.aComponent(handler: RProps.() -> Unit): ReactElement {
    return child(AComponent::class) {
        this.attrs(handler)
    }
}
```
리액트를 제대로 사용하기 위해서는 한 컴포넌트에서 다른 컴포넌트를 가져와 조립할 수 있어야합니다.  
`RBuilder.render()`는 수신 객체 지정 람다로 구현되어 있기 때문에  
`RBuilder.aComponent()`처럼 `RBuilder`의 함수로 확장해야 합니다.  

만약 이후에 소개할 `props`가 필요없을 경우 다음과 같이 `handler`를 제거하여도 무방합니다.  
```kotlin
fun RBuilder.aComponent(): ReactElement {
    return child(AComponent::class) {}
}
```

## `props` 사용하기
```kotlin
external interface AComponentProps : RProps {
    var name: String
}

@JsExport
class AComponent : RComponent<AComponentProps, RState>() {
    override fun RBuilder.render() {
        a {
            +"My name is ${props.name}!"
        }
    }
}

fun RBuilder.aComponent(handler: AComponentProps.() -> Unit): ReactElement {
    return child(AComponent::class) {
        this.attrs(handler)
    }
}
```
`props`를 사용하기 위해서는 `RProps`를 상속 받는 `external interface`를 생성하면 됩니다.  
내부 프로퍼티는 임의로 라이브러리에서 생성 후 초기화 되기 때문에  
반드시 `var`로 선언해야 합니다.  

이제 `AComponent`를 사용하는 측에서는 `attrs {}`을 통해 `props`를 전달할 수 있습니다.
```kotlin
@JsExport
class TestComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            aComponent {
                attrs {
                    name = "jaychy-yy"
                }
                // attrs.name = "jaychy-yy" // 이렇게도 할 수 있습니다.
            }
        }
    }
}
```

## 컴포넌트의 상태 저장하기
```kotlin
data class AComponentState(
    val test: String,
) : RState

@JsExport
class AComponent : RComponent<AComponentProps, AComponentState>() {

    init {
        state = AComponentState("")
    }

    override fun RBuilder.render() {
        a {
            +"My name is ${props.name}!"
        }
    }
}
```
컴포넌트의 상태를 저장하기 위해서는 저장할 공간을 `data class`로 표현합니다.  
사용할 땐 `state.test` 형식으로 사용하고  
상태를 변경하기 위해서는 `setState()` 메소드를 사용합니다.  
또한 초기상태를 `init block`에서 진행할 수 있습니다.  

## CSS 적용하기
```kotlin
object MyStyles : StyleSheet("MyStyles", isStatic = true) {
    val name by css {
        fontSize = 20.px
        color = rgb(0, 0, 0)
        backgroundColor = Color.white
    }
}
```
스타일시트를 만드는 방법은 위와 같습니다.  
생긴 것은 `styled-component`와 비슷해서 쉽게 사용할 수 있을 것 같습니다.  
또한 보통 `object`로 선언하여 싱글톤으로 만들어 효율적으로 사용하게 합니다.  

```kotlin
@JsExport
class AComponent : RComponent<AComponentProps, AComponentState>() {
    init {...}
    override fun RBuilder.render() {
        styledA {
            css {
                +MyStyles.name
            }
            +"My name is ${props.name}!"
        }
    }
}
```
스타일을 적용하기 위해서는 태그를 `styled...` 태그로 변경하여야 합니다.  
`styled...` 태그 안에는 `css()` 함수를 사용할 수 있도록 `CSSBuilder`가 생성됩니다.  

> +로 연결되는 것은 내부적으로 연산자 오버로딩이 되어 있기 때문입니다.

## Library & Framework
- kotlin/js - 1.5.10
- kotlin react - 17.0.1-pre.148-kotlin-1.4.30
- kotlin react dom - 17.0.1-pre.148-kotlin-1.4.30
- kotlin styled - 5.2.1-pre.148-kotlin-1.4.30
