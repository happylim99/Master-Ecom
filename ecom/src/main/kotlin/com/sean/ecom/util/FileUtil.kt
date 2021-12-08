package com.sean.ecom.util

import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.ecom.model.FileObj
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Slf4j
object FileUtil {

    fun isDirExist(reqPath: String): Boolean {
        val path: Path = Paths.get(reqPath)
        var rtnValue = false
        if (Files.exists(path)) {
            rtnValue = true
        }
        return rtnValue
    }

    fun genPath(reqPath: String, fileName: String, extension: String): String? {
        var newPath = "$reqPath$fileName.$extension"
        var isDirExist = isDirExist(newPath)
        if (isDirExist) {
            var inc = 0
            var cont = true
            while (cont) {
                inc++
                newPath = "$reqPath$fileName($inc).$extension"
                isDirExist = isDirExist(newPath)
                if (!isDirExist) {
                    cont = false
                }
            }
        }
        return newPath
    }

    fun handleDuplicate(obj: FileObj): String {
        val pathFile = "${obj.path}${obj.oriName}"
        var newPathFile = "$pathFile.${obj.ext}"
        var fname: String = ""
        if (isDirExist(newPathFile)) {
            var inc = 0
            var cont = true
            while (cont) {
                inc++
                fname = "${obj.oriName}($inc)"
                newPathFile = "${obj.path}$fname.${obj.ext}"
                if (!isDirExist(newPathFile)) {
                    cont = false
                }
            }
        }
        return fname
    }

    fun createDirectory(path: String) {
        try {
            Files.createDirectories(Paths.get(path))
            log.info("Create directory $path")
        } catch (e: IOException) {
            log.error("error $e")
        }
    }

    fun setPathPermission(reqPath: String?, read: Boolean, write: Boolean, exec: Boolean) {
        val file = File(reqPath)
        file.setReadable(read, false)
        file.setExecutable(exec, false)
        file.setWritable(write, false)
        log.info(file.getPath().toString() + " set permission read: $read, exec: $exec, write: $write")
    }

    fun save(reqPath: String, bytes: ByteArray?) {
        val path = Paths.get(reqPath)
        try {
            Files.write(path, bytes)
        } catch (e: IOException) {
            log.error("Save file error $e")
        }
    }

    fun saveFile(fileObj: FileObj) {
        if(!isDirExist(fileObj.path)) {
            createDirectory(fileObj.path)
        }
        save("${fileObj.path}${fileObj.newName}.${fileObj.ext}", fileObj.bytes)
        setPathPermission(fileObj.path, true, true, true)
    }

    private fun saveFile_bak(path: String, pathFile: String, bytes: ByteArray) {
        if(!isDirExist(path)) {
            createDirectory(path)

        }
        save(pathFile, bytes)
        setPathPermission(path, true, true, true)
    }

}