package com.luoyuer.util

import kotlin.contracts.contract

public sealed class Optional<out T> {

    public data class Some<out T>(val value: T) : Optional<T>()

    public data object None : Optional<Nothing>()

    public fun hasValue(): Boolean {
        contract {
            returns(true) implies (this@Optional is Some<T>)
            returns(false) implies (this@Optional is None)
        }
        return this is Some
    }

    public fun cast(): T {
        return (this as Some<T>).value
    }

    public companion object
}

