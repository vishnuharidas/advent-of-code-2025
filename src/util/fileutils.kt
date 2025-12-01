package util

import java.io.File

// Utility functions
object R {
    private const val RESOURCE_DIR = "resources/"
    fun getContents(name: String) = File("$RESOURCE_DIR/$name").readLines()
}