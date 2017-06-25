# 第六章 Java8与并发
## Java 8的函数式编程
- 函数作为一等公民
- 无副作用
- 申明式的
- 不变的对象 几乎所有的对象都是拒绝被修改的 类似于不变模式
- 易于并行 由于对象都处于不变的状态 因此函数式编程更加易于并行 
- 更少的代码

#### FunctionalInterface注释
- 注释FunctionalInterface表明IntHandler接口是一个函数式接口 只包含一个方法 符合函数式接口的定义
- 在Java 8中 接口运行存在实例方法 其次任何被java.lang.Object实现的方法 都不能是为抽象方法

#### 函数式编程基础
- 接口默认方法
- 在Java之前的版本 接口只能包含抽象方法 但从Java 8之后 接口也可以包含若干个实例方法
- Java 8使用default关键字 可以在接口内定义实例方法 这个方法并非抽象方法 而是拥有特定逻辑的具体实例方法
- 例子：Comparator接口新增了若干默认方法

#### lambda表达式
- lambda表达式可以说是函数式编程的核心 lambda表达式即匿名函数 它是一段没有函数明德函数体 作为参数直接传递给相关的调用者

#### 方法引用
- java 8 简化lambda表达式的一种手段 他通过类名和方法名来定位到一个静态方法或者实例方法
- 静态方法应用
- 实例上的实例方法引用
- 超类上的实例方法引用
- 类型上的实例方法引用
- 构造方法引用 Class::new
- 数组构造方法引用 TypeName[]::new
- 如果一个类中存在同名的实例方法和静态函数 那么编译器就会感到很困惑
- Java 8中对lambda表达式的处理几乎等同于匿名类的实现

#### 并行排序
- Arrays.sort()
- Arrays.parallelSort()
- Arrays.parallelSetAll()

### 增强的Future：CompletableFuture
- 实现了Future接口 实现了CompletionState接口
- 通过设置CompletatbleFuture 可以手动设置CompletableFuture的完成状态

