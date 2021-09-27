package com.skx.tomike

class A {

    object Inner {
        val instance = A()
    }


    companion object {

        fun getInstance(): A {
            return Inner.instance
        }
    }


}