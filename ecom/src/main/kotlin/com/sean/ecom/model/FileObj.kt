package com.sean.ecom.model

data class FileObj(
    var path: String,
    var oriName: String,
    var ext: String,
    var newName: String,
    var bytes: ByteArray = byteArrayOf(),
    var read: Boolean = true,
    var write: Boolean = true,
    var exec: Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileObj

        if (path != other.path) return false
        if (oriName != other.oriName) return false
        if (newName != other.newName) return false
        if (ext != other.ext) return false
        if (!bytes.contentEquals(other.bytes)) return false
        if (read != other.read) return false
        if (write != other.write) return false
        if (exec != other.exec) return false

        return true
    }

    override fun hashCode(): Int {
        var result = path.hashCode()
        result = 31 * result + oriName.hashCode()
        result = 31 * result + newName.hashCode()
        result = 31 * result + ext.hashCode()
        result = 31 * result + bytes.contentHashCode()
        result = 31 * result + read.hashCode()
        result = 31 * result + write.hashCode()
        result = 31 * result + exec.hashCode()
        return result
    }
}