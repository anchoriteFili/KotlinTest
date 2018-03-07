
## Kotlin教程

---

> Kotlin是一种在Java虚拟机上运行的静态类型编程语言，被称之为Android世界的swift,
由于JetBrains设计开发并开源。

### 为什么选择 Kotlin?

* 简洁：大大减少样板代码的数量
* 安全：避免空指针异常等整个类的错误。
* 互操作性：充分利用JVM、Android 和浏览器的现有库。
* 工具友好：可用任何 Java IDE 或者使用命令行构建。

#### Kotlin 基础语法

Kotlin文件以 .kt 为后缀

**包声明**

`代码文件的开头一般为包的声明：`

```kotlin
package com.runoob.main

import java.util.*

fun test() {}
class Runoob {}
```

> kotlin源文件不需要相匹配的目录和包，源文件可以放在任何文件的目录。
> 以上例中 test() 的全名是 com.runoob.main.test、Runoob 的全名是 com.runoob.main.Runoob
> 如果没有指定包，默认为 `default` 包。

**默认导入**

有多个包会默认导入到每个 Kotlin 文件中。

* kotlin.*
* kotlin.annotation.*
* kotlin.collections.*
* kotlin.comparisons.*
* kotlin.io.*
* kotlin.ranges.*
* kotlin.sequences.*
* kotlin.text.*

---

### 函数定义

`函数定义使用关键字fun，参数格式为：参数：类型。`

```kotlin
fun sum(a: Int, b: Int): Int { // Int 参数，返回值 Int
    return a + b
}
```

表达式作为函数体，返回类型自动推断：

```kotlin
fun sum(a: Int, b: Int) = a + b

public fun sum(a: Int, b: Int): Int = a + b // public 方法则必须明确写出返回类型
```

无返回值的函数(类似Java中的void):

```kotlin

fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}

// 如果是返回 Unit 类型，则可以省略(对public方法也是这样)：
public fun printSum(a: Int, b: Int) {
}

```

**可变长参数函数**

> 函数的变长参数可以用 `vararg` 关键字进行标识：

```kotlin

fun vars(vararg v: Int) {
    for (vt in v) {
        print(vt)
    }
}

// 测试
fun main(args: Array<String>) {
    vars(1,2,3,4,5)
}

```

#### lambda(匿名函数)

lambda表达式使用实例

```kotlin
fun main(args: Array<String>) {

    val sumlambda: (Int, Int) -> Int = {x,y -> x + y}
    print(sumlambda(1,2))
}

```

#### 定义常量和变量

> 可变变量定义： var关键字

```kotlin
var <标识符> : <类型> = <初始化值>
```

> 不可变变量定义：val关键字，只能赋值一次的变量(类似java中final修饰的变量)

```kotlin
val <标识符> : <类型> = <初始化值>
```

> 常量和变量都可以没有初始化值，但是在引用前必须初始化。
> 编译器支持自动类型判断，即声明时可以不指定类型，由编译器判断。

```kotlin

val a: Int = 1
val b = 1   // 系统自动推断变量类型为Int
val c: Int  // 如果不在声明时初始化则必须提供变量类型
c = 1       // 明确赋值

var x = 5   // 系统自动推断变量类型为Int
x += 1      // 变量可修改   

```

#### 注释

Kotlin 支持单元和多行注释，实例如下：

```kotlin

// 这是单行注释

/*
*  这是一个多行块注释
* */

```

与 Java 不同， Kotlin 中的块注释允许嵌套

#### 字符串摸版

$ 表示一个变量名或者变量值
$varName 表示变量值
${varName.fun()} 表示变量的方法返回值：

```kotlin
// 测试
fun main(args: Array<String>) {

    var a = 1
    // 模板中的简单名称：
    var s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is","was")}, but now is $a"

    println(s2)
}

```

#### NULL 检查机制

> Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式
> 字段后加!!像Java一样抛出空异常，另一种字段后加?可不做处理返回值为 null或配合?:做
> 空判断处理

```kotlin
//类型后面加?表示可为空
var age: String? = "23" 
//抛出空指针异常
val ages = age!!.toInt()
//不做处理返回 null
val ages1 = age?.toInt()
//age为空返回-1
val ages2 = age?.toInt() ?: -1

```

> 当一个引用可能为null值时，对应的类型声明必须明确地标记为可为null.
> 当str中的字符串内容不是一个整数时，返回null：

```kotlin
fun parseInt(str: String): Int? {
  // ...
}
```

以下实例演示如何使用一个返回值可为null的函数： 

```kotlin
// 测试
fun main(args: Array<String>) {


    if (args.size < 2) {
        print("Two integers expected")
        return
    }

    val x = parseInt(args[0])
    val y = parseInt(args[1])

    // 直接使用 x * y 会导致错误，因为它们可能为null
    if (x != null && y != null) {
        // 在运行过 null 值检查之后，x 和 y 的类型会被自动转换为非 null 变量
        print(x * y)
    }
}
```

#### 类型检测及自动类型转换

> 我们可以使用 is 运算符检测一个表达式事发后某类型
> 我们可以使用 is 运算符检测一个表达式事发后某类型的一个实例（类似java中的instanceof关键字）。

```kotlin

fun getStringLength(obj: Any): Int? {
    
    if (obj is String) {
        // 做过类型判断后，obj会被系统自动转换为 String 类型
        return obj.length
    }

    return null

    // 或者
    if (obj !is String)
        return null

    // 在这个分支中，obj 的类型会被自动转换为 String
    return obj.length
}

```

#### 区间

> 区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
> 区间是为任何可比较类型定义的。但对于整型原生类型，它有一个优化的实现。

```kotlin

fun main(args: Array<String>) {

    for (i in 1..4) print(i) // 输出"1234"

    for (i in 4..1) print(i) // 什么都不输出

    for (i in 1..10) { // 等同于 1 <= i && i <= 10
        println(i)
    }

    // 使用 step 指定步长
    for (i in 1..4 step 2) print(i) // 输出 "13"

    for (i in 4 downTo 1 step 2) print(i) // 输出 "42"

    // 使用 until 函数排除结束元素

    for (i in 1 until 10) { // i in [1, 10) 排除了 10
        println(i)
    }
}
```

### Kotlin 基本数据类型

#### 字面常量

* 十进制： 123
* 长整型以大写的L结尾： 123L
* 16进制以0x开头： 0x0F
* 2进制以0b开头: 0b00001011
* 注意：8进制不支持

Kotlin 同时也支持传统符号表示的浮点数值。

* Doubles 默认写法：123.5,123.5e10
* Floats 使用 f 或 F 后缀： 123.5f


> 你可以使用下划线使数字常量更易读：

```kotlin

val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
```

#### 比较两个数字

> Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其中Kotlin帮你
> 封装了一个对象，这样可以保证不会出现空指针。数字类型也一样，所以在比较两个数字的时候
> 就有比较数据代销和比较两个对象是否相同的区别了。 === 表示比较对象地址，两个 == 表示比较两个值大小。

```kotlin

fun main(args: Array<String>) {

    val a: Int = 10000
    println(a === a)    // true, 值相等，对象地址相等。

    // 经过了装箱，创建了两个不同的对象
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    // 虽然经过了装箱，但是值是相等的，都是10000

    println(boxedA === anotherBoxedA)   // false，值相等，对象地址不一样
    println(boxedA == anotherBoxedA)    // true, 值相等

}
```

#### 类型转换

> 由于不同的表示方式，较小类型并不是较大类型的子类型，较小的类型不能隐式转换为较大的
> 类型。这意味着在不进行显示转换的情况下我们不能把 Byte 型值赋给一个 Int 变量。

```kotlin

val b: Byte = 1 // OK, 字面值是静态测试的
val i: Int = b  // 错误

```

我们可以代用其 toInt() 方法。

```kotlin

val b: Byte = 1 // OK, 字面值是静态检测的
val i: Int = b.toInt() // OK
```

```kotlin
toByte(): Byte
toShort(): Short
toInt(): Int
toLong(): Long
toFloat(): Float
toDouble(): Double
toChar(): Char
```

#### 位操作符

> 对于Int和Long类型，还有一系列的为操作符可以使用，分别是：

```kotlin

shl(bits) - 左移位(Java`s <<)
shr(bits) - 右移位(Java`s >>)
ushr(bits) - 无符号右移位(Java`s >>> )
and(bits) - 与
or(bits) - 或
xor(bits) - 异或
inv() - 反向

```


#### 字符

> 和Java不一样，Kotlin 中的 Char 不能直接和数字操作, Char 必需是单引号包含起来，比如普通字符 '0','a'

```kotlin
fun check(c: Char) {
    if (c == 1) { // 错误，类型不兼容
        // ......
    }
}
```

> 字符字面值用单引括起来'1'。特殊字符可以用反斜杠转义。支持这几个转义序列：\t、\b、\n、\r、\'、\"、\\
和\$。编码其他字符要用Unicode转义序列语法： '\uFF00'。

```kotlin
fun decimalDigitValue(c: Char): Int {

    if (c !in '0'..'9')
        throw IllegalAccessException("Out of range")
    return c.toInt() - '0'.toInt() // 显示转换为数字
}

```
> 当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。。

#### 布尔

> 布尔用 Boolean 类型表示，它有两个值： true和false.

```kotlin
|| - 短路逻辑或
&& - 短路逻辑与
!  - 逻辑非
```

#### 数组

```kotlin
    val  a = arrayOf(1, 2, 3)
    val b = Array(3, {i -> (i * 2)})

    // 读取数组内容
    println(a[0]) // 输出结果：1
    println(b[1]) // 输出结果：2
```

#### 字符串

> 和Java一样，String是可不变的。方括号[]语法可以很方便的获取字符串中的某个字符，也可以通过
> for循环来遍历：

```kotlin
for (c in str) {
    println(c)
}
```

> Kotilin 支持三个引号 """ 括起来的字符串中的某个字符串，也可以通过for循环来遍历：

```kotlin
    val  text = """
        |多行字符串
        |菜鸟教程
        |多行字符串
        |Runoob
        """
    println(text)


//    String 可以通过trimMargin() 方法来删除多余的空白

    text.trimMargin(">")

    println(text)   // 前置空格删除了
```

#### 字符串模板

> 字符串可以包含模板表达式，即一些小段代码，会求值并把结果合并到字符串中。模板表达式
> 以美元符($)开头，由一个简单的名字构成：

```kotlin
    val s = "runoob"
    val str = "$s.length is ${s.length}" // 求值结果
    println(str)
```

> 原生字符串和转移字符串内部都支持模板。如果你需要在原生字符串中表示字面值$字符(它
> 不支持反斜杠转义)，你可以用下列语法：

### 条件控制

> 我们可以把IF表达式的结果赋值给一个变量。

```kotlin
    // 传统用法
    val  a = 2
    val  b = 3
    val max = if (a > b) {
        a
    } else {
        b
    }
```

#### 使用区间

> 使用in运算符来检测某个数字是否在指定区间内，区间格式为 x..y:

```kotlin
    val x = 5
    val y = 9
    if (x in 1..8) {
        println("x 在区间内。。。")
    }
```

#### When表达式

when 将它的参数和所有的分支条件顺序比较，知道耨个分支满足条件。
when 既可以被当做表达式使用也可以被当做语句使用。如果它被当做表达式，符合条件
的分支的值就是整个表达式的值，如果当做语句使用，则忽略个别分支的值。
when 类似其他语言的swich操作符。其最简单的形式如下。

```kotlin

    val x = 2
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> { // 注意这个块
            print("x 不是 1， 也不是 2")
        }
    }
```

> 在when中，else同switch 的 default。如果其他分支都不满足条件将会求值else分支。
如果很多分支需要用相同的方式处理,则可以把多个分支条件放在一起，用逗号分隔：

```kotlin
    val x = 1
    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }

```

> 我们也可以检测一个值在（in）或者不在（!in）一个区间或者集合中：

```kotlin

    val x = 11
    val vailiNumbers = arrayOf(10, 11, 12)

    when (x) {
        in 1..10 -> print("x is in the range")
        in vailiNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
```

> 另一种可能性是检测一个值是（is）或者不是（!is）一个特定类型的值。注意：由于智能
转换，你可以访问该类型的方法和属性而无需任何额外的检测。

```kotlin
print(hasPrefix("Prefixheheheh"))

fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("Prefix")
    else -> false
}
```

```kotlin

    var x = 0
    when (x) {
        0,1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {
            println("x 不是 1，也不是 2")
        }
    }

    when (x) {
        in 0..10 -> println("x 在该区间范围内")
        else -> println("x 不在该区间内")
    }

```

> when 中使用 in 运算符来判断集合内是否包含某实例：

```kotlin

    val items = setOf("apple", "banana", "kiwi")

    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fun too")
    }
```

### Kotlin 循环控制

> for 循环可以对任何提供迭代器（iterator）的对象进行遍历，语法如下：

```kotlin
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        print(item)
    }


    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
```

#### while 与 do...while循环

> while是最基本的循环，它的结构为：

```kotlin
while( 布尔表达式 ) {

}
```

> do...while 循环 对于 while 语句而言，如果不满足条件，则不能进入循环。但有时候我们需要
即使不满足条件，也要执行一次

```kotlin
do {
    // 代码语句
}while( 布尔表达式 )
```

#### 返回和跳转

Kotlin 有三种结构化的跳转表达式。
* return    默认从最直接包围它的函数或者匿名函数返回。
* break     终止最直接包围它的循环。
* continue  继续下一次最直接包围它的循环。



#### Break 和 Continue 标签

> 在Kotlin中任何表达式都可以用标签（label）来标记。标记格式为标识符后跟 @ fuhao ,
例如：abc@、fooBar@都是有效的标签。要为一个表达式加标签，我们只要在其前加标签即可。

```kotlin
    loop@ for (i in 1..100) {

        for (j in 1..100) {
            if () break@loop
        }
    }
```

#### 标签处返回

> Kotlin 有函数字面量、局部函数和对象表达式。因此 Kotlin 的函数可以被嵌套。标签
限制的 return 允许我们从外层函数返回。最重要的一个用途就是lambda表达式返回。


#### Kotlin 类和对象

**类定义**<br>
> Kotlin 类可以包含：构造函数和初始化代码块、函数、属性、内部类、对象声明。
Kotlin 中使用关键字 class 声明类，后面跟类名：

```kotlin
class Runoob { // 类名为 Runoob
    // 大括号内是类体构成
}
```

> 我们也可以定义一个空类：

```kotlin
class Empty
```

> 可以在类中定义成员函数：

```kotlin
class Runoob() { print("foo") } // 成员函数
```

#### 类的属性

**属性定义**

> 类的属性可以用关键字 var 声明为可变的，否则使用只读关键字 val 声明为不可变。

```kotlin
class Runoob {

    var name: String = ....
    var url: String = ....
    var city: String = ....
}
```

> 我们可以像使用普通函数那样使用构造函数创建类实例：

```kotlin
val site = Runoob() // Kotlin 中没有 new 关键字
```

> 要使用一个属性，只要用名称引用它即可

```kotlin
site.name   // 使用 . 号来引用
site.url
```


> Kotlin 中的类可以有一个 主构造器，以及一个或多个次构造器，主构造器是类头部的一部分，
位于类名称之后：

```kotlin
class Person constructor(firstName: String) {}
```

> 如果主要构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。

```kotlin
class Person(firstName: String) {

}
```

#### getter 和 setter

> 属性声明的完整语法：

```kotlin
var allByDefault: Int? // 错误：需要一个初始化语句，默认实现了 getter 和 setter方法。
var initialized = 1 // 类型为 Int，默认实现了 getter 和 setter
val simple: Int?    // 类型为Int，默认实现 getter，但必须在构造函数中初始化。 
val inferredType = 1// 类型为 Int 类型，默认实现 getter
```

**实例** 

> 以下实例定义了一个Person类，包含两个可变变量 lastName 和 no， lastName 修改了
getter 方法，no 修改了 setter 方法。

```kotlin
class Person {

    var lastName: String = "zhang"
    get() = field.toUpperCase() // 将变量赋值后转换为大写
    set

    var no: Int = 100
    get() = field               // 后端变量
    set(value) {

        if (value < 10) {       // 如果传入的值小于 10 返回该值
            field = value
        } else {
            field = -1          // 如果出入的值大于等于 10 返回 -1
        }
    }

    var height: Float = 145.4f
    private set

}


// 测试
fun main(args: Array<String>) {

    var person: Person = Person()

    person.lastName = "wang"

    println("lastName: ${person.lastName}")

    person.no = 9
    println("no:${person.no}")

    person.no = 20
    println("no:${person.no}")

}
```

> Kotlin 中类不能有字段。提供了 Backing Fields(后端变量)机制，备用字段使用
field的关键字声明，field关键字只能用于属性的访问器。

> 非空属性必须在定义的时候初始化，kotlin提供了一种可以延迟初始化的方案，使用
`lateinit` 关键字描述属性：

```kotlin
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSub
    }

    @Test fun test() {
        subject.method() // dereference directly
    }
```

#### 主构造器

> 主构造器中不能包含任何代码，初始化代码可以放在初始化代码段中，初始化代码段使用init
关键字作为前缀。

```kotlin
class PersonOne constructor(firstName: String) {
    init {
        System.out.print("Firstname is $firstName")
    }
}
```

> 注意：主构造器的参数可以在初始化代码段中使用，也可以在类主体n定义的属性初始化代码中
使用。一种简洁语法，可以主构造器来定义属性并初始化属性值（可以是var或val）：

```kotlin
class People(val fistName: String,val lastName: String) {
    // 。。。。。
}
```

> 如果构造器有注解，或者有可见度修饰符，这时constructor关键字是必须的，注解和修饰符要放在它之前。

```kotlin
class  Runoob constructor(name: String) { // 类名为 Runoob\
    // 大括号内是类体构成

    var url: String = "http://www.runoob.com"
    var country: String = "CN"
    var siteName = name

    init {
        println("初始化网站名：${name}")
    }

    fun printTest() {
        println("这是类的函数")
    }

}


// 测试
fun main(args: Array<String>) {

    val runoob = Runoob("菜鸟教程")
    println(runoob.siteName)
    println(runoob.url)
    println(runoob.country)
    runoob.printTest()
}
```

#### 次构造函数

> 类也可以有二级构造函数，需要加前缀 constructor

```kotlin
class Runoob constructor(name: String) { // 类名为 Runoob
    // 大括号内是类体构造

    var url: String = "http://www.runoob.com"
    var country: String = "CN"
    var siteName = name

    init {
        println("初始化网络名：${name}")
    }

    // 次构造函数
    constructor(name: String, alexa: Int) : this(name) {
        println("Alexa 排名 $alexa")
    }

    fun printTest() {
        println("我是类的函数")
    }
}


// 测试
fun main(args: Array<String>) {

    val runoob = Runoob("菜鸟教程",10000)
    println(runoob.siteName)
    println(runoob.url)
    println(runoob.country)
    runoob.printTest()

}
```

#### 抽象类

> 抽象是面向对象编程的特征之一，类本身，或类中的部分成员，都可以声明为abstract的。
抽象成员在类中不存在具体的实现。注意：无需对抽象类或抽象成员标注open注解

```kotlin
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f()
}
```


#### 嵌套类

> 我们可以把类嵌套在其他类中，看以下实例：

```kotlin
class Outer { // 外部类
    private val bar: Int = 1

    class Nested { // 嵌套类
        fun foo() = 2
    }
}

val demo = Outer.Nested().foo() // 调用格式： 外部类.嵌套类.嵌套类方法/属性
println(demo) // == 2

```

#### 内部类

> 内部类使用 inner 关键字来表示
> 内部类会带有一个对外部类的对象的引用，所以内部类可以访问外部类的成员

```kotlin

class Outer {
    private val bar: Int = 1
    var v = "成员属性"

    /*嵌套内部类*/
    inner class Inner {
        fun foo() = bar // 访问外部成员变量
        fun innerTest() {
            var o = this@Outer // 获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}


// 测试
fun main(args: Array<String>) {
    val demo = Outer().Inner().foo()
    println(demo) // 1
    val demo2 = Outer().Inner().innerTest()
    println(demo2)
}
```

> 为了消除歧义，要访问来自外部作用域的this，我们使用this@label，其中 @label 是一个
 代指 this 来源的标签
 
 #### 匿名内部类
 
 > 使用对象表达式来创建匿名内部类： 
 
 
 ```kotlin
// 匿名内部类
class Test {
    var v = "成员属性"

    fun setInterface(test: TestInterFace) {
        test.test()
    }
}

/*
* 定义接口
* */
interface TestInterFace {
    fun test()
}



// 测试
fun main(args: Array<String>) {

    var test = Test()

    /*
    * 采用对象表达式来创建接口对象，即匿名内部类的实例
    * */
    test.setInterface(object : TestInterFace {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })
}
```

#### 类的修饰符

> 类的修饰符包括 classModifier 和 _accessModifier_:

* classModifier: 类属性修饰符，标示类本身特性。

```kotlin
abstract    // 抽象类
final       // 类的不可继承，属性默认
enum        // 枚举类
open        // 类可继承，类默认是final的
annotation  // 注解类
```

* accessModifier: 访问权限修饰符

```kotlin
private     // 仅在同一个文件中可见
protected   // 同一个文件中或子类中可见
pulbic      // 所有调用的地方都可见
internal    // 同一个模块中可见
```

**实例**


```kotlin
package foo

private fun foo() {} // 在app.kt 内可见

public var bar: Int = 5 // 该属性随处可见

internal val baz = 6    // 相同模块内可见
```

### Kotlin 继承

> Kotlin 中所有类都继承该 Any 类，它是所有类的超类，对于没有超类型声明的类是默认超类：

```kotlin
class Example   // 从 Any 隐式继承
```

> Any 默认提供了三个函数：

```kotlin
equals()
hashCode()
toString()
```

> 注意：Any 不是 java.lang.Object。
如果一个类要被继承，可以使用 open 关键字进行修饰。

```kotlin
open class Base(p: Int)     // 定义基类
class Derived(p: Int) : Base(p)
```

#### 构造函数

**子类有主构造函数**

> 如果子类有主构造器函数，则基类必须在主构造函数中立即初始化。

```kotlin

open class Person(var name: String, var age: Int) { // 基类
}

class Student(name: String, age: Int, var no : String, var score : Int) : Person(name, age) {
}

// 测试
fun main(args: Array<String>) {

    val s = Student("Runoob", 18, "S12346", 89)

    println("学生名：${s.name}")
    println("年龄：${s.age}")
    println("学生号：${s.no}")
    println("成绩：${s.score}")
}
```

#### 子类没有主构造函数

> 如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，后者在代理另一个构造
函数。初始化基类时，可以调用基类的不同的构造函数。

```kotlin
/*用户基类*/
open class Person(name: String) {
    /*次级构造函数*/
    constructor(name:String, age: Int) : this(name) {
        // 初始化
        println("-------基类次级构造函数----------")
    }
}

/*子类继承 Person 类*/
class Student: Person {

    /*次级构造函数*/

    constructor(name: String, age: Int, no: String, score: Int): super(name,age) {

        println("------继承次级构造函数------")
        println("学生名： ${name}")
        println("年龄：${age}")
        println("学生好：${no}")
        println("成绩：${score}")
    }
}

// 测试
fun main(args: Array<String>) {

    var s = Student("Runoob",18, "S12346", 89)
}
```


#### 重写

> 在基类中，使用 fun 声明函数时，此函数默认为final修饰，不能被子类重写。如果允许子类
重写该函数，那么就要手动添加open修饰它，子类重写方法使用 override 关键词：

```kotlin
/*用户基类*/
open class Person {
    open fun study() { // 允许子类重写
        println("我毕业了")
    }
}

/*子类就成 Person 类*/
class Student: Person() {

    override fun study() { // 重写方法
        println("我在读大学")
    }
}


// 测试
fun main(args: Array<String>) {

    val s = Student()
    s.study()
}
```

> 如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择
性地调用父类的实现。

```kotlin

open class A {
    open fun f() { print("A") }
    fun a() { print("a") }
}

interface B {
    fun f() { print("B") }
    fun b() { print("b") }
}

class C() : A(), B {

    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
    }
}

// 测试
fun main(args: Array<String>) {

    val c = C()
    c.f()

}
```

> C 继承自 a() 或 b()，C 不仅可以从 A 或则 B 中继承函数，而且 C 可以继承 A()、B() 中共有的
函数。此时该函数在中只有一个实现，为了消除歧义，该函数必须调用A()和B()中该函数的实现，并提供
自己的实现。


#### 属性的重写

> 属性重写使用 override 关键字，属性必须具有兼容类型，每一个声明的属性都可以通过初始化程序或者
getter方法被重写：

```kotlin
open class Foo {
    open val x: Int get{ ...... }
}

class Bar1 : Foo() {
    override val x: Int = .....
}

```

> 你可以用一个var属性重写一个val属性，但是反过来不行。因为val属性本身定义了getter方法，重写
为var属性会在衍生类中额外声明一个setter方法

````kotlin
interface Foo {
    val count: Int
}

class Bar1(override val count: Int) : Foo

class Bar2 : Foo {
    override var count: Int = 0
}
````

### Kotilin 接口

> Kotlin 接口与 Java 8 类似，使用 interface 关键字定义接口，允许方法有默认实现：

```kotlin
interface MyInterface {
    fun bar()   // 未实现
    fun foo() { // 已实现
        // 可选的方法体
        print("foo")
    }
}

class Child : MyInterface {
    override fun bar() {
        // 方法体
        println("bar")
    }
}


// 测试
fun main(args: Array<String>) {

    val c = Child()
    c.foo()
    c.bar()
}
```

#### 接口中的属性

> 接口中的值只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口是，必须重写属性：

```kotlin
interface Myinterface {
    var name: String // name 属性，抽象的
    fun bar()
    fun foo() {
        // 可选的方法体
        println("foo")
    }
}

class Child : Myinterface {
    override var name: String = "runoob" // 重载属性
    override fun bar() {
        // 方法体
        println("bar")
    }
}


// 测试
fun main(args: Array<String>) {

    val c = Child()
    c.foo()
    c.bar()
    print(c.name)
}
```

#### 函数重写

> 实现多个接口时，可能会遇到同一个方法继承多个实现的问题。

```kotlin
interface A {
    fun foo() { print("A") }    // 已实现
    fun bar()                   // 未实现，没有方法体，是抽象的
}

interface B {
    fun foo() { print("B") }    // 已实现
    fun bar() { print("bar") }  // 已实现
}

class C : A {
    override fun bar() { print("bar") }  // 重写
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}

// 测试
fun main(args: Array<String>) {
    val d = D()
    d.foo()
    d.bar()
}
```

> 实例中接口 A 和 B 都定义了方法 foo() 和 bar()，两者都实现了 foo(), B 实现了 bar()。因为
C 是一个实现了 A 的具体类，所以必须要重写 bar() 并实现这个抽象方法

> 然而，如果我们从 A 和 B 派生 D，我们需要实现多个接口继承的所有方法，并指明 D 应该如何实现它们。
这一规则既适用于继承单个实现(bar()) 的方法也适用于继承多个实现 (foo()) 的方法。







 






















