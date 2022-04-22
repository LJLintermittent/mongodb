### lombok工作原理

工作的原理分析，以Oracle的javac编译工具为例。
自从Java 6起，javac就支持“JSR 269 Pluggable Annotation Processing API”规范，只要程序实现了该API，就能在javac运行的时候得到调用。
举例来说，现在有一个实现了”JSR 269 API”的程序A,那么使用javac编译源码的时候具体流程如下:

Javac对源代码进行分析，生成一棵**抽象语法树(AST) **
运行过程中调用实现了”JSR 269 API”的A程序
此时A程序就可以完成它自己的逻辑，包括修改第一步骤得到的抽象语法树(AST)
javac使用修改后的抽象语法树(AST)生成字节码文件

所以lombok本质上就是这样的一个实现了”JSR 269 API”的程序。在使用javac的过程中，结合官方说明，它编译的流程如下：

javac对源代码进行分析，生成一棵**抽象语法树(AST)****
运行过程中调用实现了”JSR 269 API”的lombok程序
此时lombok就对第一步骤得到的AST进行处理，找到@Data注解所在类对应的语法树(AST)，然后修改该语法树(AST)，增加getter和setter方法定义的相应树节点
javac使用修改后的抽象语法树(AST)生成字节码文件

