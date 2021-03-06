package com.step2hell.poker

import com.step2hell.poker.card.*
import com.step2hell.poker.suit.Suit
import com.step2hell.poker.util.isEven
import com.step2hell.poker.util.isOdd
import com.step2hell.poker.util.shuffle

object Deck {
    private val classes: List<Class<out Card>> = listOf(
        Two::class.java,
        Three::class.java,
        Four::class.java,
        Five::class.java,
        Six::class.java,
        Seven::class.java,
        Eight::class.java,
        Nine::class.java,
        Ten::class.java,
        Jack::class.java,
        Queen::class.java,
        King::class.java,
        Ace::class.java
    )

    val cards: Array<Card> = Array(54) {
        val index = it / 4
        val mod = it % 4
        if (it < 4 * classes.size) {
            classes[index].getDeclaredConstructor(Suit::class.java).newInstance(Suit.from(mod))
        } else {
            if (it.isEven) Joker.Black else Joker.Red
        }
    }

    fun print() {
        cards.forEachIndexed { index, card ->
            val newLine = if (index % 4 == 0) "\n" else ""
            // System.out.printf("%s\t", "$newLine$card")
            System.out.printf("%8s", "$newLine${alignRight(card.toString())}")
        }
    }

    private fun alignRight(src: String): String {
        val stringBuilder = StringBuilder()
        repeat(8 - src.length) { stringBuilder.append(" ") }
        return stringBuilder.append(src).toString()
    }
}
