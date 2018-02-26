
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








