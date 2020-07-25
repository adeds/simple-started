package id.adeds.started.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> asyncAwait(
    context: CoroutineContext = Dispatchers.IO,
    action: suspend CoroutineScope.() -> T
): T =
    withContext(CoroutineScope(context).coroutineContext) { action(this) }