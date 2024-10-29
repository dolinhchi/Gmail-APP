package com.example.gmailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var extendedFab: ExtendedFloatingActionButton
    private lateinit var nestedScrollView: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView)
        extendedFab = findViewById(R.id.extendedFab)
        nestedScrollView = findViewById(R.id.nestedScrollView)

        // Add OnScrollListener to RecyclerView
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Scroll down
                if (dy > 20 && extendedFab.isExtended) {
                    extendedFab.shrink()
                }

                // Scroll up
                if (dy < -20 && !extendedFab.isExtended) {
                    extendedFab.extend()
                }

                // At the top
                if (!recyclerView.canScrollVertically(-1)) {
                    extendedFab.extend()
                }
            }
        })

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY + 20 && extendedFab.isExtended) {     // Scroll Down
                extendedFab.shrink()
            }
            if (scrollY < oldScrollY - 20 && !extendedFab.isExtended) {      // Scroll Up
                extendedFab.extend()
            }

            if (scrollY == 0) {     // At the top
                extendedFab.extend()
            }
        })
    }

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}