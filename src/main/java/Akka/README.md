# 使用Akka构建高并发程序
- Akka使用Scala创建的 
- Akka提供了一种称为Actor的并发模型 其粒度比线程更小 这意味着可以在系统启用机器大量的Actor
- Akka提供了一种容错机制 允许在Actor初选异常时进行一些恢复或者重置操作
- Akka不仅可以在单机上构建高并发程序 也可以在网络中构建分布式程序 并提供位置透明的Actor定位服务
- 通过是给Actor发送一条消息 当一个Actor受到消息后 他有可能会根据消息的内容作出某种行为 包括更改自身状态
这种情况下 这个状态的更改是Actor自己进行的 并不是外界被迫进行的



## 新并发模型:Actor
- 当系统内有多个Actor存在时 Akka会自动在线程池中选择线程来执行我们的Actor 因此多个不同的Actor有可能会被同一线程执行
因此 值得注意的地方是 不要在一个Actor中执行耗时的代码 这样可能会导致其他的Actor的调度出现问题

## 有关消息投递的一些说明
- 整个Akka应用是由消息驱动的 消息是除了Actor之外最重要的核心组件 作为在并发程序中组件
在Actor之间传递的消息应满足不可变性 也就是不变模式 强烈推荐使用不变模式 不可变对象
- 消息投递的3种策略
- 至多一次投递 可能失败 导致消息丢失
- 至少一次投递 可能接收到重复的消息
- 精确的消息投递 
- 第一种高性能 低成本 只需要把消息投递出去就可以了 第二种需要保存消息投递的状态不断充实
第三种成本最高 最不容易实现 消息的可靠性应该考应用的业务层去维护

## 监督策略
- Akka框架给予了我们足够的控制权 父Actor可以对子Actor进行监督
- 一种是OneForOneStrategy监督 父Actor只会对有问题的子Actor进行处理 比如重启或者停止
- 一种是AllForOneStrategy监督 更加适合各个Actor联系非常紧密的场景 如果多个Actor出现故障 
只要有一个出现故障 就会宣告整个任务失败
- 自定义监督策略 SupervisorStrategy 

## 选择Actor
- Akka提供了一个ActorSelection类 用来批量进行消息发送

## 消息收件箱
- Akka框架已经为我们提供了一个收件箱的组件 使用收件箱可以方便地对Actor进行消息发送和接受

## 消息路由
- Akka使用一个路由器组件来封装消息的调度 系统提供了几种实用的路由策略

## Actor的内置状态转换
- 一个Actor内部消息处理函数可以拥有多个不同的状态 在特定的状态下 可以对同一消息进行不同的处理 状态之间可以任意转变

## 询问模式:Actor中的Future
- 由于Actor之间都是通过异步消息通信的 

## 多个Actor同时修改数据:Agent
- 一个Agent提供对一个便来你给的一步更新  一个Agent最多只能执行一个Action 

## 像数据库一样操作内存数据：软件事务内存
- 软件事务内存的技术 具有隔离性 原子性和一致性 但是不具有持久性
