package me.nylestroke.nylemod.entity.variant

import java.util.*

enum class RaccoonVariant(val id: Int) {
    DEFAULT(0), DARK(1), RED(2);

    companion object {
        private val BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt { obj: RaccoonVariant -> obj.id })
            .toArray<RaccoonVariant> { arrayOf() }

        fun byId(id: Int): RaccoonVariant {
            return BY_ID[id and BY_ID.size]
        }
    }
}