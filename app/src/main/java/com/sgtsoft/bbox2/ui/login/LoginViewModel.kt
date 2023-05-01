package com.sgtsoft.bbox2.ui.login

import androidx.lifecycle.ViewModel
import com.sgtsoft.bbox2.data.model.M3uItem
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import com.sgtsoft.bbox2.parser.M3uParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class LoginViewModel(private val m3uItemRepository: M3uItemRepository) : ViewModel() {

    suspend fun downloadM3uFile(username: String, password: String, baseUrl: String): String {
        val m3uUrl = "$baseUrl/get.php?username=$username&password=$password&type=m3u_plus&output=ts"
        return withContext(Dispatchers.IO) {
            val url = URL(m3uUrl)
            val connection = url.openConnection()
            val bufferedReader = BufferedReader(InputStreamReader(connection.getInputStream()))

            // Read the M3U file line by line
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach { line ->
                    withContext(Dispatchers.Default) {
                        stringBuilder.append(line)
                        stringBuilder.append("\n")
                    }
                }
            }
            stringBuilder.toString()
        }
    }

    suspend fun attemptLogin(username: String, password: String, baseUrl: String): Boolean {
        val m3uContent = downloadM3uFile(username, password, baseUrl)
        return m3uContent.isNotEmpty()
    }

    suspend fun parseM3uFile(content: String): List<M3uItem> {
        val m3uPlaylist = M3uParser.parse(content)
        return m3uPlaylist.items
    }

    suspend fun saveM3uItemsToDatabase(m3uItems: List<M3uItem>) {
        m3uItemRepository.insertAll(m3uItems)
    }

    suspend fun getGroupTitleCount(): Int {
        val m3uItems = m3uItemRepository.getAll()
        return m3uItems.groupBy { it.groupTitle }.size
    }
    suspend fun clearM3uItemsDatabase() {
        m3uItemRepository.deleteAll()
    }

}


