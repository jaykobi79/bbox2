package com.sgtsoft.bbox2.parser

data class M3uChannel(
    val xuiId: String,
    val tvgId: String,
    val tvgName: String,
    val tvgLogo: String,
    val groupTitle: String,
    val name: String,
    val url: String
)

fun parseM3u(content: String): List<M3uChannel> {
    val channels = mutableListOf<M3uChannel>()
    val lines = content.trim().split("\n")
    var metadata = mutableMapOf<String, String>()

    for (line in lines) {
        when {
            line.startsWith("#EXTINF") -> {
                metadata = parseMetadata(line.substringAfter("#EXTINF:").trim())
            }
            line.startsWith("#") -> {
                // Skip comments and other directives
            }
            else -> {
                channels.add(
                    M3uChannel(
                        xuiId = metadata["xui-id"] ?: "",
                        tvgId = metadata["tvg-id"] ?: "",
                        tvgName = metadata["tvg-name"] ?: "",
                        tvgLogo = metadata["tvg-logo"] ?: "",
                        groupTitle = metadata["group-title"] ?: "",
                        name = metadata["name"] ?: "",
                        url = line.trim()
                    )
                )
            }
        }
    }

    return channels
}

fun parseMetadata(metadataStr: String): MutableMap<String, String> {
    val metadata = mutableMapOf<String, String>()
    val metadataFields = metadataStr.split(",", limit = 2)
    metadataFields.getOrNull(1)?.let { name ->
        metadata["name"] = name.trim()
    }
    metadataFields.getOrNull(0)?.let { fields ->
        fields.split(" ").forEach { field ->
            val keyValue = field.split("=")
            if (keyValue.size == 2) {
                metadata[keyValue[0]] = keyValue[1].removeSurrounding("\"")
            }
        }
    }
    return metadata
}

fun serializeM3u(channels: List<M3uChannel>): String {
    val groupedChannels = channels.groupBy { it.groupTitle }
    val builder = StringBuilder("#EXTM3U\n")

    for ((groupName, groupChannels) in groupedChannels) {
        builder.append("#EXTGRP:$groupName\n")
        for (channel in groupChannels) {
            val metadataFields = mutableMapOf<String, String>()
            metadataFields["xui-id"] = channel.xuiId
            metadataFields["tvg-id"] = channel.tvgId
            metadataFields["tvg-name"] = channel.tvgName
            metadataFields["tvg-logo"] = channel.tvgLogo
            metadataFields["group-title"] = channel.groupTitle
            metadataFields["name"] = channel.name

            val metadataString = metadataFields.entries.joinToString(separator = " ") {
                "${it.key}=\"${it.value}\""
            }

            builder.append("#EXTINF:-1 $metadataString,${channel.name}\n")
            builder.append("${channel.url}\n")
        }
    }

    return builder.toString()
}

