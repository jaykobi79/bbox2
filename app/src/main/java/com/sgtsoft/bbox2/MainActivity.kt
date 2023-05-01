package com.sgtsoft.bbox2

// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgtsoft.bbox2.R
import com.sgtsoft.bbox2.fragments.GroupTitleListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GroupTitleListFragment())
                .commitNow()
        }
    }
}
