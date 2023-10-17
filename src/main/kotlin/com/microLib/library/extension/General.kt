package com.microLib.library.extension

import org.springframework.stereotype.Component

@Component
class General {
    companion object {
        @JvmStatic
        fun <T> getOrDefaultValue(data: T?, defaultValue: T): T {
            return data ?: defaultValue
        }
    }
}
