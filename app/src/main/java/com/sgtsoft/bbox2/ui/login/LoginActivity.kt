package com.sgtsoft.bbox2.ui.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sgtsoft.bbox2.databinding.ActivityLoginBinding
import com.sgtsoft.bbox2.data.database.M3uItemDatabase
import com.sgtsoft.bbox2.data.repository.M3uItemRepository
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewModel
        val database = M3uItemDatabase.getDatabase(applicationContext)
        val repository = M3uItemRepository(database.m3uItemDao())
        val viewModelFactory = LoginViewModelFactory(repository)
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        // Set up the login button click listener
        binding.button.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val baseUrl = binding.baseUrlInput.text.toString()

            // Call the login method on the ViewModel and handle the M3U file download
            lifecycleScope.launch {
                val success = loginViewModel.attemptLogin(username, password, baseUrl)
                if (success) {
                    Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()

                    val m3uContent = loginViewModel.downloadM3uFile(username, password, baseUrl)
                    if (m3uContent != null) {
                        val m3uItems = loginViewModel.parseM3uFile(m3uContent)

                        // Add these log statements
                        Log.d("LoginActivity", "M3U Items count: ${m3uItems.size}")
                        m3uItems.forEach { Log.d("LoginActivity", "Group title: ${it.groupTitle}") }

                        loginViewModel.clearM3uItemsDatabase() // Clear the database before saving new items
                        loginViewModel.saveM3uItemsToDatabase(m3uItems)

                        val uniqueGroupTitles = m3uItems.map { it.groupTitle }.distinct().size
                        Toast.makeText(applicationContext, "Saved $uniqueGroupTitles unique group-titles to database", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}






