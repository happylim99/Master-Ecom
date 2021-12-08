package com.sean.ecom.util

import com.sean.base.annotation.Slf4j

@Slf4j
object SystemUtil {

    enum class OS {
        WINDOWS, LINUX, MAC, SOLARIS
    }

    fun getOS(): OS? {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> {
                OS.WINDOWS
            }
            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                OS.LINUX
            }
            os.contains("mac") -> {
                OS.MAC
            }
            os.contains("sunos") -> {
                OS.SOLARIS
            }
            else -> null
        }
    }

    fun getRoot() = if (getOS()?.equals(OS.WINDOWS)!!) "c:" else ""

}