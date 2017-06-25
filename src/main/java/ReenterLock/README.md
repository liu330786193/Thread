#### ReentrantLock
大多数情况下 锁的申请都是非公平的 不是根据请求顺序决定的 无序的
公平的锁 则不是这样 他会按照时间的先后顺序 保证先到的先得 不会产生饥饿现象 
如果我们使用synchronized关键字进行锁控制 那么产生的锁就是非公平的 而重入锁允许我们对其公平性进行设置
尽管贡品所看起来很优美 但是需要维护一个有序队列 因此公平锁的成本比较高 因此默认情况下锁是非公平的

#### ReentrantLock几个重要方法
- lock() 获得锁 如果所已经被占用 则等待
- lockInterruptibly() 获得锁 但优先相应终端
- tryLock() 尝试获得锁 如果成功 返回true 失败返回false 则方法不等待 立即返回
- tryLock(long time, TimeUnit unit) 在给定时间内获得锁
- unlock 释放锁

#### 重入锁的实现
- 原子状态 使用CAS操作存储当前锁的状态 判断锁是否已经被别的线程持有
- 等待队列 所有没有请求到锁的线程 进入等待队列进行等待 等待有锁的后 系统就能从等待队列中唤醒一个线程 继续工作
- 阻塞源于park和unpark 用来挂起和恢复线程 没有得到锁的将被挂起

### 重入锁的好搭档 Condition条件
- await() 使当前线程等待 同时释放锁 使用singal signalAll()方法 线程重新获得并继续执行 或者线程被中断 跳出循环
- awaitUninterruptibly方法基本相同 不等待中断
- singal() 唤醒线程

#### **Conditon的使用**
- 当使用Condition.await() 要求线程持有重入锁 在Condition.await()调用互殴 这个线程会释放这把锁
- 同理当使用者Condition.singal() 也要求线程获取冲入锁
- lock.lock() lock.unlock()
- JDK ArrayBlockingQueue威力 冲入锁和Condition对象被广泛的运用

### **允许多个线程同时访问：信号量(Semaphore)**
- 无论内部锁 重入锁 都可以指定多个线程 一次只允许一个线程访问某一个资源 信号量主却可以指定多个线程 同时访问某一个资源
- 在构造信号量是 需要指定信号量的准入数 同时能申请多少个许可 当每次只申请一个许可时 
- 使用完acquire时 同时需要释放这些许可 release
- acquire acquireUninterruptibly tryAcquire tryAcquire release

###ReadWriteLock读写锁
JDK5提供的读写锁

###倒计时器：CountDownLatch
- 非常实用的多线程控制工程类
- CountDownLatch(int count)
- 实例 火箭发射 当所有线程都检查完毕之后 使用end.await() 等待所有线程都完成之后
- 才会执行主线程 在每个单独线程都完成之后 调用end.countDown() 通知CountDownLatch 
- 一个线程已经完成了任务 倒计时器减一

### 循环栅栏 CyclicBarrier
- 他也可以完成线程间的计数等待 但是功能比CountDownLatch更加发杂且强大
- 凑齐10个计数器 就重置为0
- public CyclicBarrier(int parties, Runnable barrierAction)
- barrierAction就是当计数器一次计数完成后 系统就会执行动作

### 线程阻塞工具类 LockSupport
- 非常方便使用的线程阻塞工具 可以在线程内任意位置让线程阻塞
- 与Thread.suspend()相比 弥补了由于resume 导致线程无法继续执行的情况
- 与Object.wait()相比 他不需要获得某个对象的锁 也不会跑出异常
- park() parkNanos() parkUntil()

## 线程池
- newFixedThreadPool(int nThreads)
- newSingleThreadExecutor()
- newCachedThreadPool()
- newSingleThreadScheduledExecutor()
- newScheduledThreadPool() //某个固定延时之后执行 周期性执行某个任务
- newScheduledThreadPool(int corePoolSize)
#### 计划任务
- schedule(Runnable command, long delay, TimeUnit unit)
- scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
- scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, Timeunit unit)

####线程提交的队列策略
- SynchronousQueue 直接提交的队列 没有容量 每一个插入操作都要等待一个相应的删除操作
- ArrayBlockingQueue 有界的任务队列
- LinkedBlockingQueue 无界的任务队列
- PriorityBlockingQueue 优先任务队列

#### 线程拒绝策略 - 超负载了
- AbortPolicy策略 该策略会直接跑出异常组织系统正常工作
- CallerRunsPolicy策略 直接在调用者线程中 运行当前被丢弃的任务 不会真的丢弃任务
- DiscardOledestPolicy策略 丢弃最老的一个请求 也就是即将被执行的一个任务 并尝试再次提交当前任务
- DiscardPolicy策略 丢弃无法处理的任务 不予处理 
- 自定义策略实现RejectedExecutionHandler接口 即可自定义线程池和拒绝策略

- ## 合理的选择:优化线程池数量
- Ncpu = CPU的数量
- Ucpu = 目标CPU的使用率 0 ≤ Ucpu ≤1
- W/C = 等待时间与计算时间的比率
- Nthreads = Ncpu * Ucpu * (1+W/C)
- System.out.println(Runtime.getRuntime().availableProcessors());
- 线程池可能吃掉程序跑出的异常 导致我们队程序的错误一无所知







