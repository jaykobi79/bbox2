package com.sgtsoft.bbox2.parser

import android.util.Log
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.data.model.M3uPlaylist
import java.io.BufferedReader
import java.io.StringReader

object M3uParser {
    fun parse(content: String): M3uPlaylist {
        val items = mutableListOf<M3uItem>()
        val reader = BufferedReader(StringReader(content))
        var extinf = ""
        var url = ""

        reader.useLines { lines ->
            lines.forEach { line ->
                when {
                    line.startsWith("#EXTINF") -> {
                        extinf = line.trim()
                        Log.d("M3uParser", "EXTINF: $extinf")
                    }
                    line.startsWith("#") -> {
                        // Skip comments and other directives
                    }
                    else -> {
                        url = line.trim()
                        val metadata = Parser.parseMetadata(extinf)
                        val title = metadata["title"] ?: ""
                        val attributes = metadata.filterKeys { it != "title" }

                        val m3uItem = M3uItem(
                            xuiId = attributes["xui-id"] ?: "",
                            tvgId = attributes["tvg-id"] ?: "",
                            tvgName = attributes["tvg-name"] ?: title,
                            tvgLogo = attributes["tvg-logo"] ?: "",
                            groupTitle = attributes["group-title"] ?: "",
                            channelName = title,
                            streamUrl = url
                        )
                        items.add(m3uItem)

                        Log.d("M3uParser", "Added item: $m3uItem")
                    }
                }
            }
        }

        return M3uPlaylist(items)
    }

}

object Parser {
    private val CHANNEL_REGEX = "EXTINF:([^,]+),(.+)$".toRegex()
    private val METADATA_REGEX = "(\\S+?)=\"(.+?)\"".toRegex()

    fun parseMetadata(extinf: String): Map<String, String> {
        val metadata = mutableMapOf<String, String>()
        val regex = "^(#EXTINF:[^,]+),(.+)$".toRegex()
        regex.find(extinf)?.let { match ->
            val title = match.groupValues[2].trim()
            metadata["title"] = title
            val metadataStr = match.groupValues[1]
            METADATA_REGEX.findAll(metadataStr).forEach { it ->
                val key = it.groupValues[1]
                val value = it.groupValues[2]
                metadata[key] = value
            }
        }
        return metadata
    }

}




