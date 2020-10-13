package com.ashehata.testme

import com.ashehata.testme.homeWork.HomeWork
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeWorkTest {


    @Test
    fun `try get the fib 0`() {
        val result = HomeWork.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `try get the fib 1`() {
        val result = HomeWork.fib(1)
        assertThat(result).isEqualTo(1)
    }


    @Test
    fun `try get the fib 10`() {
        val result = HomeWork.fib(5)
        assertThat(result).isEqualTo(HomeWork.fib(3) - HomeWork.fib(4))
    }
    /**
     * for checkBraces fun
     */
    @Test
    fun `pass braces in the start of text more than end`() {
        val result = HomeWork.checkBraces("(a, b))")
        assertThat(result).isFalse()
    }

    @Test
    fun `pass braces in the start of text less than end`() {
        val result = HomeWork.checkBraces("((a, b)")
        assertThat(result).isFalse()
    }


    @Test
    fun `pass equlas braces(2)  (start = end)`() {
        val result = HomeWork.checkBraces("((a, b))")
        assertThat(result).isTrue()
    }


    @Test
    fun `pass equlas braces(3)  (start = end)`() {
        val result = HomeWork.checkBraces("(((a, b)))")
        assertThat(result).isTrue()
    }

    @Test
    fun `pass equlas braces(1000000000)  (start = end)`() {
        val text = ""
        for (brac in 0..1000000000) {
            text.substringAfterLast('(')
        }
        text.substringAfterLast('a')
        for (brac in 0..1000000000) {
            text.substringAfterLast(')')
        }
        val result = HomeWork.checkBraces(text)
        assertThat(result).isTrue()
    }
}