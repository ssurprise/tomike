package com.skx.tomike.bomber.basics

/**
 * 描述 : kotlinn 靶场 - 用于试验kotlin 语法
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/25 11:05 AM
 */


/*
解构声明
*/

data class Boo(val id: String, val name: String)

fun test() {
    val boo = Boo("0001", "三年级暑假作业-语文")
    val (id, name) = boo
    print("id:${id} name:${name}")
}


/*
只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
可以用 fun 修饰符在 Kotlin 中声明一个函数式接口。
 */
fun interface KRunnable {
    fun invoke()
}

