#### 1、线性表的使用

```
com.data.struct.seqence.MultiplyPolyn
(2x + 5x^3 + 3x^5) + (3x^2 + x^3 + 5x^6 + 2x^8)
= 2x^1 + 3x^2 + 6x^3 + 3x^5 + 5x^6 + 2x^8
```

#### 2、括号匹配(栈)

```
com.data.struct.seqence.BracketMatch
{([()])} --> 匹配
```

#### 3、十进制转八进制(栈)

```
com.data.struct.seqence.Conversion
1348 --> 2504
```

#### 4、迷宫探索

```
com.data.struct.seqence.ThroughMaze
深度优先方式进行探索：基于栈的数据结构，探索出所有出口的路径；
广度优先方式进行探索：基于线性表的数据结构，探索出最优出口的路径。
```

#### 5、evaluation of expression(表达式求值)

```
com.data.struct.seqence.ExpressionEvaluation

IF 运算数
    压入到运算数栈
ELSE
    result = 当前的运算符 op2和运算符栈顶的元素 op1之间优先级对比结果
    SWITCH (result) {
        CASE op2 < op1:
            将 op2压入到运算符栈中;
            break;
        CASE op2 == op1:
            运算符栈弹出栈顶元素;
            break;
        CASE op2 > op1:
            右边操作数 right = 运算数栈弹出的栈顶元素;
            左边操作数 left = 运算数栈弹出的栈顶元素;
            运算符 = 运算符栈弹出的栈顶元素;
            将 left op right的结果压入到运算数栈中;
            break;
    }
 
eg: 3*(7-2)+5-(1+(1*3))#
运算符栈        运算数栈        表达式值
#                             3*(7-2)+5-(1+(1*3))#
#              3              *(7-2)+5-(1+(1*3))#
# *            3              (7-2)+5-(1+(1*3))#
# * (          3              7-2)+5-(1+(1*3))#
# * (          3 7            -2)+5-(1+(1*3))#
# * ( -        3 7            2)+5-(1+(1*3))#
# * ( -        3 7 2          )+5-(1+(1*3))#
# * (          3 5            )+5-(1+(1*3))#
# *            3 5            +5-(1+(1*3))#
#              15             +5-(1+(1*3))#
# +            15             5-(1+(1*3))#
# +            15 5           -(1+(1*3))#
#              20             -(1+(1*3))#
# -            20             (1+(1*3))#
# - (          20             1+(1*3))#
# - (          20 1           +(1*3))#
# - ( +        20 1           (1*3))#
# - ( + (      20 1           1*3))#
# - ( + (      20 1 1         *3))#
# - ( + ( *    20 1 1         3))#
# - ( + ( *    20 1 1 3       ))#
# - ( + (      20 1 3         ))#
# - ( +        20 1 3         )#
# - (          20 4           )#
# -            20 4           #
#              16             #
result: 16
```

#### 6、汉诺塔递归问题 hanoi tower

```
com.data.struct.seqence.HanoiTower

  -
 ---                       
-----
——————  —————— ——————
  x       y      z
  
  
          -             将 1,2从 x移动到 y上；3移动到 z上
         ---   -----
——————  —————— ——————
  x       y      z
  
                 -
                ---     将 1,2从 y移动到 z上
               -----
——————  —————— ——————
  x       y      z
```

#### 7、串的模式匹配

##### 7.1、普通匹配

```
com.data.struct.string.StringMatcher

从指定位置开始，对指定子串进行递归全匹配。
```

##### 7.2、KMP算法

```
com.data.struct.string.KmpNext

ababaca串的 next函数：
1、next数组的长度应等于目标串的长度；
2、1 <= 前后缀的长度 < 串的长度
3、-1表示不存在相同的前后缀；n表示最长的前后缀长度为 n + 1
4、next[i]表示串的前(i + 1)位的前后缀相同的最长长度

分析的示意图如下：

a;          k = -1;    next[0] = -1;
ab;         k = -1;    next[1] = -1;
aba;        k = 0;     next[2] = 0;
abab;       k = 1;     next[3] = 1;
ababa;      k = 2;     next[4] = 2;
ababac;     k = -1;    next[5] = -1;
ababaca;    k = 0;     next[6] = 0;

寻找串在目标串出现的起始位置示意图：

bacbababadababacambabacaddababacasdsd
ababaca
 
bacbababadababacambabacaddababacasdsd
    ababaca

bacbababadababacambabacaddababacasdsd
      ababaca

bacbababadababacambabacaddababacasdsd
        ababaca

bacbababadababacambabacaddababacasdsd
          ababaca
```

##### 8、树的遍历

com.data.struct.tree.BinaryTree

##### 8.1 先序遍历

父节点 -> 左孩子节点 -> 右孩子节点

##### 8.2 中序遍历

左孩子节点 -> 父节点 -> 右孩子节点

##### 8.3 后序遍历

左孩子节点 -> 右孩子节点 -> 父节点

#### 9、哈夫曼树（最优树）

com.data.struct.tree.HuffmanTree

对权值集合 {w1, w2, ..., wn}进行排序，每次选取权值最小的 2个节点，作为一个新的二叉树的左右子节点，
并将其从权值集合中去除。其权值之和构建为一个新的节点，作为它们的父节点，并帮其加入到权值集合中。重复上述步骤，直至权值集合
里面只剩下一个元素为止。

注意：新构建的节点，其权值如果等于原集合里的某个权值，逻辑上其值应该大于原集合里的权值。

eg: 7 5 2 4

```
sort: 2 4 5 7
   6
 2   4
 
sort: 5 6 7

       11
     5    6
        2   4
        
sort: 7 11

       18
    11    7
  6    5
4   2
```

#### 10、求解集合的幂集

com.data.struct.tree.PowerSet

设集合 A = {a, b, c, d}

##### 10.1 分治法求解

A的幂集 = {a}的幂集与 {b, c, d}的幂集之积

{a}的幂集等于 a和 Φ

所以 A的幂集 = a * {b, c, d}的幂集 + Φ * {b, c, d}的幂集

{b, c, d}的幂集求解同上。

##### 10.2 二进制表示法

A的幂集个数就等于 2^A集合元素的个数，和 A集合的个数的二进制表示是一致的。

0000    Φ

0001        d

0010       c

0011       cd

....

1110    abc

1111    abcd

0011所表示的集合元素，要进行以下计算：

0011 & 0001 存在
 
0011 & 0010 存在

0011 & 0100 不存在

0011 & 1000 不存在

结果就是 c, d。

&后面的二进制计算：1 << j;(j € {0, 1, 2, 3})

##### 10.3 树求解

求解集合 A的幂集，可以看做是对集合 A的元素依次进行取舍的过程。

                        Φ
                a               Φ
            b       Φ       b       Φ
          c   Φ   c   Φ   c   Φ   c   Φ

集合 A有 n个元素，按照上面构造出的二叉树，就有 2^n个叶子节点，
根据叶子节点逐级遍历父亲节点，就可以得到其幂集元素。

#### 11、八皇后问题求解

构造 n皇后在棋盘中的位置，其中要满足以下条件：

1. 每两个皇后不能再同一列同一行出现
2. 皇后不能处于对角线上

这样皇后在棋盘中的位置一定是：每一行、每一列有且只能存在一个皇后。所以可以将问题抽象成：
从第一行一直到最后一行，依次放入一个皇后，且要满足上面的 2个条件。最后一个皇后落下且满足
条件的，就是八皇后问题的一个解。

按照上面的思路，递归求解是一个很好的选择。

```
public static boolean isLocationValid(int posX, int posY) {
    ......
    return false;
}

public static void explore(int deep) {
    for (int i = 0; i < queenNum; i++) {
        boolean isValid = isLocationValid(i, deep);
        if (isValid) {
            if (deep == queenNum - 1) {
                输出八皇后解;
                continue;
            } 
            explore(deep + 1);
        }
    }
}
```

利用队列来实现八皇后求解问题，和递归求解一样，将每一步正确的步骤放到一个队列里，判断是否
合法，需要依赖于队列最前面的那个元素去进行判断。

实现代码：com.data.struct.tree.EightQueen