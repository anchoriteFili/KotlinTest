### Kotlin 扩展

> Kotlin 可以对一个累的属性和方法进行扩展，且不需要继承或使用 Decorator 模式
> 扩展是一种静态行为，对被扩展的类代码本身不会造成任何影响。

---

#### 扩展函数

> 扩展函数可以在已有类中添加新的方法，不会对原类做修改，扩展函数定义形式：

```kotlin
fun receiverType.functionName(params) {
    body
}

```

* receiverType : 表示函数的接收者，也就是函数扩展的对象。
* functionName : 扩展函数的名称
* params : 扩展函数的参数，可以为NULL

> 以下实例扩展 User 类：

```kotlin
class User(var name: String)

/*扩展函数*/

fun User.Print() {
    print("用户名：$name")
}

// 测试
fun main(args: Array<String>) {

    var user = User("Runoob")
    user.Print()
}
```

> 下面代码为 MutableList 添加一个 swap 函数：

```kotlin

// 扩展函数 swap，调换不同位置的值
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

// 测试
fun main(args: Array<String>) {
    val l = mutableListOf(1, 2, 3)

    // 位置0和2的值做了互换
    l.swap(0,2) // 'swap()'函数内的 'this' 将指向 'l' 的值

    println(l.toString())
}
```

> this关键字指代接收者对象(receiver object)(也就是调用扩展函数时，在点后之前指定的对象实例)

---

#### 扩展函数是静态解析的

> 扩展函数是静态解析的，并不是接收者类型的虚拟成员，在调用扩展函数时，具体被调用的是哪一个函数，
由调用函数的对象表达式来决定的，而不是动态的类型决定的：

```kotlin
/*
*   扩展函数时静态解析的，并不是接收者类型的虚拟成员，在调用扩展函数时，
*   具体被调用的是哪个函数，有调用函数的对象表达式来决定的，而不是动态的
*   类型决定的。
* */

open class C

class D: C()

fun C.foo() = "c" // 扩展函数 foo

fun D.foo() = "d" // 扩展函数 foo

fun printFoo(c: D) {
    println(c.foo()) // 类型是 C 类
}

// 测试
fun main(args: Array<String>) {

    printFoo(D())
}
```

> 若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。

```kotlin
/*
*  若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
* */

class C {
    fun foo() { println("成员函数") }
}

fun C.foo() { println("扩展函数") }

// 测试
fun main(args: Array<String>) {

    var c = C()
    c.foo()
}
```

#### 扩展一个空对象

> 在扩展函数内，可以通过 this 来判断接收者是否为NULL，这样，即使接收者为NULL，也可以调用扩展函数。

```kotlin
fun Any?.toString(): String {
    if (this == null) return "这是个null"
    // 空检测之后，"this"会自动转换为非空类型，所以下面的 toString() 解析为 Any 类的成员函数

    return toString()
}

fun main(args: Array<String>) {

    var t = null
    println(t.toString())
}
```

> 扩展属性
> 除了函数，Kotlin 也支持属性对属性进行扩展：

```kotlin

val <T> List<T>.lastIndex: Int
    get() = size -1
```

> 扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中。初始化属性因为属性没有后端字段
(backing field)，所以不允许被初始化，只能由显式的提供的 getter/setter 定义

**扩展属性只能被声明为 val**


#### 伴生对象的扩展


> 如果一个类定义有一个伴生对象，你也可以为伴生对象定义扩展函数和属性。
> 伴生对象通过类名. 形式调用伴生对象，伴生对象声明的扩展函数，通过用类名限定符来调用

```kotlin

class MyClass {
    companion object {
        // 将被称为 "Companion"
    }
}

fun MyClass.Companion.foo() {
    println("伴随对象的扩展函数")
}

val MyClass.Companion.no: Int
    get() = 10


fun main(args: Array<String>) {
    println("no:${MyClass.no}")
    MyClass.foo()
}
```

#### 扩展的作用域

> 通常扩展函数或属性定义在顶级包下：

```kotlin
package com.example.usage

import foo.bar.goo  // 导入所有名为 goo 的扩展
                    // 或者
import foo.bar.*    // 从 foo.bar 导入一切

fun usage(baz: Baz) {
    baz.goo()
}
```

#### 扩展声明为成员

> 在一个类内部你可以为另一个类声明扩展
> 在这个扩展中，有个多个隐含的接受者，其中扩展方法定义所在的类的实例称为分发接受者，
而扩展方法的目标类型的实例称为扩展接受者。


> 在 C 类内，创建了 D 类的扩展。此时，C 被成为分发接受者，而 D 为扩展接受者。从上例中，可以清楚的看到，在扩展函数中，可以调用派发接收者的成员函数。
  假如在调用某一个函数，而该函数在分发接受者和扩展接受者均存在，则以扩展接收者优先，要引用分发接收者的成员你可以使用限定的 this 语法。
  
```kotlin
class D {
    fun bar() { println("D bar") }
}

class C {
    fun bar() { println("C bar") }  // 与 D 类 的 bar 同名


    /*C被称为分发接受者，而D为扩展接受者*/
    fun D.foo() {
        bar()           // 调用 D.bar(), 扩展接受者优先
        this@C.bar()    // 调用 C.bar()
    }

    fun caller(d: D) {
        d.foo()     // 调用扩展函数
    }

}

fun main(args: Array<String>) {

    val c: C = C()
    val d: D = D()
    c.caller(d)
}
```

```kotlin
/*
* 假如在调用某一个函数，而该函数在分发接受者和扩展接受者均存在，则以扩展接收者优先，要引用分发接受者的成员
* 你可以使用限定的 this 语法
* 
* 以成员的形式定义的扩展函数，可以声明为 open，而且可以在子类中覆盖，
* */
open class D {}

class D1 : D() {}

open class C {

    open fun D.foo() {
        println("D.foo in C")
    }
    open fun D1.foo() {
        println("D1.foo in C")
    }
    fun caller(d: D) {
        d.foo() // 调用扩展函数
    }
}

class C1 : C() {
    override fun D.foo() {
        println("D.foo in C1")
    }
    override fun D1.foo() {
        println("D1.foo in C1")
    }
}

fun main(args: Array<String>) {

    C().caller(D())     // 输出 "D.foo in C"
    C1().caller(D())    // 输出 "D.foo in C1" -- 分发接受者虚拟解析
    C().caller(D1())    // 输出 "D.foo in C"  -- 扩展接收者静态解析
}
```

### Kotlin 数据类与密封类

---

#### 数据类

> Kotlin 可以创建一个只包含数据的类，关键字为 `data`：

```kotlin
data class User(val name: String, val age: Int)
```

> 编译器会自动的从主构造函数中根据所有声明的属性提取以下函数：

* equals() / hashCode()
* toString() 格式如 "User(name=John, age=42)"
* componentN() functions 对应于属性，按声明顺序排列
* copy() 函数

> 如果这些函数在类中已经被明确定义了，或者从超类中继承而来，就不再会生成。
为了保证生成的代码的一致性以及有意义，数据类需要满足一下条件：

* 主构造函数至少包含一个参数
* 所有的主构造函数的参数必须标识为val或者var；
* 数据类不可以声明为 abstract,open,sealed 或者 inner；
* 数据类不能继承其他类（但是可以实现接口）

```kotlin
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
```


#### 复制

> 复制使用 copy() 函数，我们可以使用该函数复制对象并修改部分属性，对于上文的 User
类，其实现会类似下面这样：


```kotlin
data class User(val name: String, val age: Int)

fun main(args: Array<String>) {

    val jack = User(name = "Jack", age = 1)
    /*使用 copy 类复制 User 数据类，并修改 age 属性：*/
    val olderJack = jack.copy(age = 2)

    println(jack)
    print(olderJack)
}
```

#### 数据类以及解构声明

> 组件函数允许数据类在解构声明中使用：

```kotlin
val jane = User("jane", 35)
val (name, age) = jane
println("$name, $age years of age")
```

#### 标准数据类

> 标准库提供了 `pair` 和 `Triple`。在大多数情形中，命名数据类是更好的设计选择，因为这样代码
可读性更强而且提供了有意义的名字和属性。

#### 密封类

> 密封类用来表示受限的类继承结构：当一个值为有限几种类型，而不能有任何其他类型时。
在某种意义上，他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在
一个实例，而密封类的一个子类可以有可包含状态的过个实例。声明一个密封类，使用 
`sealed` 修饰类，密封类可以有子类，但是所有子类都必须要内嵌在密封类中。
sealed 不能修饰 interface, abstract class(会报 warning, 但是不会出现编译错误)


```kotlin

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val  e1: Exr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}

```

> 使用密封类的关键好处在于使用 when 表达式 的时候，如果能够验证语句覆盖类所有
情况，就不需要为该语句再添加一个 else 子句类。

```kotlin
fun eval(expr: Expr): Double = when(expr) {
    is Expr.Const -> expr.number
    is Expr.Sum -> eval(expr.e1) + eval(expr.e2)
    Expr.NotANumber -> Double.NaN
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}
```

### Kotlin 泛型

> 泛型，即 "参数化类型"，将类型参数化，可以用在类，接口，方法上。
与 Java 一样，Kotlin 也提供泛型，为类型安全提供保证，消除类型强转的烦恼。
声明一个泛型类：

```kotlin
class Box<T>(t: T) {
    val value = t
}
```

> 创建类的实例时我们需要指定类型参数：

```kotlin
val box: Box<Int> = Box<Int>(1)
// 或者
// 编译器会进行类型推断，1 类型 Int，所以编译器知道我们说的是 Box<Int>。
val box = Box(1)
```

> 以下实例想泛型类 Box 传入整型数据和字符串：

```kotlin
class Box<T>(t: T) {
    var value = t
}

fun main(args: Array<String>) {
    var boxInt = Box<Int>(10)
    var boxString = Box<String>("Runoob")

    println(boxInt.value)
    println(boxString.value)
}
```

> 定义泛型类型变量，可以完整地写明类型参数，如果编译器可以自动推定类型参数，也可以
省略类型参数。Kotlin 泛型函数的声明与 Java 相同，类型参数要放在函数名的前面：

```kotlin
class Box<T>(t : T) {
    var value = t
}

fun <T> boxIn(value: T) = Box(value)

fun main(args: Array<String>) {

    // 以下都是合法语句
    val box4 = boxIn<Int>(1)

    val box5 = boxIn(1)

    println(box4.value)
    println(box5.value)
}
```

> 在调用泛型函数时，如果可以推断出类型参数，可以省略泛型参数。
以下实例创建了泛型函数 doPrintln，函数根据传入的不同类型做相应处理：

```kotlin
fun <T> doPrintln(content: T) {
    when (content) {
        is Int -> println("整型数字为 $content")
        is String -> println("字符串转换为大写：${content.toUpperCase()}")
        else -> println("T 不是整型，也不是字符串")
    }
}

fun main(args: Array<String>) {

    val age = 23
    val name = "runoob"
    val bool = true

    doPrintln(age) // 整型
    doPrintln(name) // 字符串
    doPrintln(bool) // 布尔型
}
```

#### 泛型约束

> 我们可以使用泛型约束来设定一个给定参数允许使用的类型。
Kotlin 中使用：对泛型的类型上线进行约束。
最常见的约束是上界(upper bound):

```kotlin
fun <T : Comparable<T>> sort(list: List<T>) {
    // ....
}
```

Comparable 的子类型可以替代 T。例如：

```kotlin
sort(listof(1, 2, 3)) // OK. Int 是 Comparable<Int> 的子类型
sort(listof(HashMap<Int, String>())) // 错误：HashMap<Int, String> 不是 
Comparable<HashMap<Int, String>> 的子类型
```

> 默认的上界是 Any?。
对于多个上界约束条件，可以用 where 子句：

```kotlin
fun <T> cloneWhenGreater(list: List<T>, threshold: T): List<T>
    where T : Comparable, Cloneable {
        return list.filter(it > threshold).map(it.clone())
    }
```

#### 型变

> Kotlin 中没有通配符类型，它有两个其他的东西：声明处形变（declaration-site variance）
与类型投影（type projections）。

#### 声明处型边

> 声明处的类型变异使用协变注解修饰符：in、out，消费者in，生产者 out.
使用 out 使得一个类型参数协变，协变类型参数只能用作输出，可以作为返回值
类型但是无法作物入参的类型：

```kotlin

/*使用 out 使得一个类型参数协变，协变类型参数只能用作输出，可以作为返回值类型但是无法作为入参的类型*/
class Runoob<out A>(val a: A) {

    fun foo(): A {
        return a
    }
}

// in 使得一个类型参数叛变，逆变类型参数只能用作输入，可以作为入参的类型但是无法作为返回值的类型：

class RunoobIn<in A>(a: A) {
    fun foo(a: A) {
        print(a)
    }
}

fun main(args: Array<String>) {

    var strCo: Runoob<String> = Runoob("a")
    var anyCo: Runoob<Any> = Runoob<Any>("b")
    anyCo = strCo

    println(anyCo.foo()) // a

//    In
    var strDCo = RunoobIn("a")
    var anyDco = RunoobIn<Any>("b")
    strDCo = anyDco
}
```

#### 星号投射

* 假如类型定义为 Foo<out T>，其中 T 是一个协变的类型参数，上界(upper bound)为 TUpper，Foo<> 等价于Foo<out TUpper>. 
它表示，当 T 未知时，你可以安全地从 Foo<> 中 读取TUpper 类型的值。
* 假如类型定义为 Foo<in T>，其中 T 是一个反向协变的类型参数，Foo<> 等价于 Foo<inNothing>.
它表示，当 T 未知时，你不能安全地向 Foo<> 写入任何东西。
* 假如类型定义为 Foo<T>，其中 T 是一个协变的类型参数，上界(upper bound)为 TUpper，
对于读取值的场合，Foo<*> 等价于 Foo<out TUpper>，对于写入值得场合，等价于
Foo<in Nothing>.

1. Function<*,String>，代表 Function<in Nothing, String>；
2. Function<Int,*>，代表 Function<Int, out Any?>
3. Function<,>，代表 Function<in Nothing, out Any?>

注意：星号投射与 Java 的原生类型(raw type)非常类似，但可以安全使用。


### Kotlin 枚举类

> 枚举类最基本的用法是实现一个类型安全的枚举
枚举常量用逗号分隔，每个枚举常量都是一个对象。

```kotlin
enum class Color {
    RED,BLACK,BLUE,GREEN,WHITE
}
```

#### 枚举初始化

> 每一个枚举都是枚举类的实例，他们可以被初始化：

```kotlin
enum class Color(val rgb: Int) {
    RED(0xFF0000)
    GREEN(0x00FF00)
    BLUE(0x0000FF)
}
```

> 默认名称为枚举字符名，值从0开始。若需要指定值，则可以使用其构造函数：

```kotlin
enum class Shape(value: Int) {
    ovel(100),
    rectangle(200)
}
```

> 枚举还支持一声明自己的匿名类及相应的方法、以及覆盖基类的方法。如：

```kotlin
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}
```

> 如果枚举类定义任何成员，是使用分号将成员定义中的枚举常量定义分隔开。

#### 使用枚举常量

```kotlin
enum class Color {
    RED,BLACK,BLUE,GREEN,WHITE
}

fun main(args: Array<String>) {

    var color: Color = Color.BLUE
    println(Color.values())
    println(Color.valueOf("RED"))
    println(color.name)
    println(color.ordinal)
}
```

> 自 Kotlin 1.1 起，可以使用 enumValues<T>() 和 enumValueOf<T>()
函数以泛型的方式访问枚举类中的常量：

```kotlin
enum class Color {
    RED,BLACK,BLUE,GREEN,WHITE
}

enum class RGB {RED, GREEN, BLUE }

inline fun <reified T: Enum<T>> printAllValues() {

    print(enumValues<T>().joinToString { it.name })
}

fun main(args: Array<String>) {

    var color: Color = Color.BLUE
    println(Color.values())
    println(Color.valueOf("RED"))
    println(color.name)
    println(color.ordinal)
    printAllValues<RGB>() // 输入 RED，GREEN，BLUE
}
```







































































