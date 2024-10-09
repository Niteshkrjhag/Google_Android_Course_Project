package com.example.tipamountcounter

import java.text.NumberFormat
import junit.framework.TestCase.assertEquals
import org.junit.Test


class TipCalculatorTests {

    @Test
    fun CalculateTip(){
        val amount = 10.0
        val tipPercent = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        var actual_tip = calculatetip(amount,tipPercent,false)
        println(actual_tip)
        assertEquals(expectedTip,actual_tip)
    }
}