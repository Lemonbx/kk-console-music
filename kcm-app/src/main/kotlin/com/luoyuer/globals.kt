package com.luoyuer

import kotlinx.coroutines.CoroutineScope

inline val KKScope: CoroutineScope
    get() = KCMApplication.instance

