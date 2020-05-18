package com.boomaa.frcsim.utils

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.text.CharacterIterator
import java.text.StringCharacterIterator


object FileUtil {
    fun loadAsString(path: String): String {
        val result = StringBuilder()
        try {
            BufferedReader(InputStreamReader(FileUtil::class.java.getResourceAsStream(path))).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result.append(line).append("\n")
                }
            }
        } catch (e: IOException) {
            System.err.println("Couldn't find the file at $path")
        }

        return result.toString()
    }

    fun folderSize(directory: File): Long {
        var length: Long = 0
        for (file in directory.listFiles()!!) {
            length += if (file.isFile) {
                file.length()
            } else {
                folderSize(file)
            }
        }
        return length
    }

    fun humanReadable(bytes: Long): String {
        var bytes = bytes
        if (-1000 < bytes && bytes < 1000) {
            return "$bytes B"
        }
        val ci: CharacterIterator = StringCharacterIterator("kMGTPE")
        while (bytes <= -999950 || bytes >= 999950) {
            bytes /= 1000
            ci.next()
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current())
    }
}