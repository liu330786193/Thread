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
- supplyAsync()
- runAsync()
- supplyAsync()方法用于那些需要有返回值的场景 比如计算某个数据
- runAsync()方法用于没有返回值的场景 比如简单地执行某一个异步动作

#### 组合多个CompletableFuture
 - 允许多个CompletableFuture进行组合 一种是使用thenCompose() 另一种是使用thenCombine()

### 读写锁的改进:StampedLock
- 读写锁的一个改进 读写锁虽然分离了读和写的工嗯呢该 但是读和写依然是冲突 如果大量线程 可能引起线程的饥饿
- StampedLock则提供了一种乐观的策略 类似于无锁的操作 使得乐观锁不会阻塞写线程

####StampedLock的实现思想
- 基于CLH的锁 一种自旋锁 他保证没有接发生 并且保证FIFO的服务顺序
- CLH锁的基本思想 所维护一个等待线程队列 所有申请锁都记录在这个队列 每一个节点 保存一个标记位 用于判断当前线程是已经释放锁
- 乐观锁失败之后 则可以升级锁级别 使用悲观读锁 使用若干次自旋之后 视图通过CAS操作获得锁 如果过自旋宣告失败 则会启用CLH队列 将自己加入到队列中 之后自旋 成功激活队列的锁 失败使用 
Unsafe.park()方法挂起线程 

## 原子类的增强 LondAdder
- 无锁的原子类操作使用系统的CAS指令
- 仿造ConcurrentHashMap 将热点数据分离
- LongAdder 并不会一开始就动用组进行处理 而是将所有数据都先记录在一个base的变量中 如果
多线程条件下 大家修改base都没有冲突 那么也没有必要扩展cell数组 一旦base数组发生冲突 就
会初始化数组 使用新的策略 如果使用cell数组更新后 发现某一个cell上的更新依然发生冲突 那么系统
就会尝试创建新的cell

### LongAdder的功能增强版:LongAccumulator
- LongAccumulator内部的优化方式和LongAdder是一样的 它们都将一个long整数进行烦分割
存储在不同的变量中 以防止多线程竞争
