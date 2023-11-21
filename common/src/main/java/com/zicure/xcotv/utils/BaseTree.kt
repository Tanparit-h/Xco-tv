package com.zicure.xcotv.utils

import android.os.Build
import timber.log.Timber
import java.util.regex.Pattern

abstract class BaseTree : Timber.Tree() {

    abstract fun getPackageName(): String

    private val explicitTag = ThreadLocal<String>()

    protected val tag: String
        get() {
            val tag = explicitTag.get()
            if (tag != null) {
                explicitTag.remove()
            }
            return tag ?: Throwable().stackTrace
                .first { it.className !in fqcnIgnore }
                .let(::createStackElementTag)
        }

    protected open val fqcnIgnore = listOf(
        Timber::class.java.name,
        Timber.Tree::class.java.name,
        Timber.DebugTree::class.java.name,
        BaseTree::class.java.name
    )

    private companion object {
        private const val MAX_TAG_LENGTH = 50
        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
    }

    protected open fun createStackElementTag(element: StackTraceElement): String {
        var tag = element.className.substringAfterLast('.')
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        val classNameTag = if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= 26) {
            tag
        } else {
            tag.substring(0, MAX_TAG_LENGTH)
        }
        return String.format("%s.%s (%s:%s)", classNameTag, element.methodName, element.fileName, element.lineNumber)
    }
}