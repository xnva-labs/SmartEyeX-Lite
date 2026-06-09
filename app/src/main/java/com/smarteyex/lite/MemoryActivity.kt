package com.smarteyex.lite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class MemoryActivity : AppCompatActivity() {

    private lateinit var searchInput: EditText
    private lateinit var clearSearchButton: ImageButton
    private lateinit var memoryRecyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var emptyText: TextView

    private val memories = mutableListOf<MemoryItem>()
    private val gson = Gson()
    private var isSearching = false

    data class MemoryItem(
        val content: String,
        val isUser: Boolean,
        val timestamp: Long
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        searchInput = findViewById(R.id.searchInput)
        clearSearchButton = findViewById(R.id.clearSearchButton)
        memoryRecyclerView = findViewById(R.id.memoryRecyclerView)
        backButton = findViewById(R.id.backButton)
        emptyText = findViewById(R.id.emptyText)

        memoryRecyclerView.layoutManager = LinearLayoutManager(this)

        loadMemories()

        setupSearch()

        backButton.setOnClickListener {
            finish()
        }

        clearSearchButton.setOnClickListener {
            searchInput.text.clear()
            clearSearch()
        }
    }

    private fun setupSearch() {
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    searchMemories(query)
                } else {
                    clearSearch()
                }
            }
        })
    }

    private fun loadMemories() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("chat_history", "[]")
        val type = object : TypeToken<MutableList<MainActivity.ChatMessage>>() {}.type
        val chatHistory: MutableList<MainActivity.ChatMessage> = gson.fromJson(json, type)

        memories.clear()
        chatHistory.forEach { chat ->
            memories.add(MemoryItem(chat.content, chat.isUser, chat.timestamp))
        }

        displayMemories(memories.reversed())
    }

    private fun searchMemories(query: String) {
        isSearching = true
        val filtered = memories.filter {
            it.content.contains(query, ignoreCase = true)
        }.reversed()

        displayMemories(filtered)

        if (filtered.isEmpty()) {
            emptyText.text = "🔍 Tidak ditemukan memory dengan kata '$query'"
            emptyText.visibility = View.VISIBLE
        } else {
            emptyText.visibility = View.GONE
        }
    }

    private fun clearSearch() {
        isSearching = false
        displayMemories(memories.reversed())
        emptyText.visibility = View.GONE
    }

    private fun displayMemories(items: List<MemoryItem>) {
        if (items.isEmpty()) {
            emptyText.text = "📭 Belum ada percakapan. Ajak SmartEyeX ngobrol yuk!"
            emptyText.visibility = View.VISIBLE
            memoryRecyclerView.visibility = View.GONE
        } else {
            emptyText.visibility = View.GONE
            memoryRecyclerView.visibility = View.VISIBLE
            val adapter = MemoryAdapter(items)
            memoryRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    inner class MemoryAdapter(private val items: List<MemoryItem>) :
        RecyclerView.Adapter<MemoryAdapter.ViewHolder>() {

        private val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale("id"))

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_memory, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount() = items.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val messageText: TextView = itemView.findViewById(R.id.messageText)
            private val timestampText: TextView = itemView.findViewById(R.id.timestampText)
            private val typeIcon: TextView = itemView.findViewById(R.id.typeIcon)

            fun bind(item: MemoryItem) {
                messageText.text = item.content
                timestampText.text = dateFormat.format(Date(item.timestamp))

                if (item.isUser) {
                    typeIcon.text = "👤"
                    typeIcon.setBackgroundColor(resources.getColor(android.R.color.transparent))
                } else {
                    typeIcon.text = "🤖"
                    typeIcon.setBackgroundColor(resources.getColor(android.R.color.transparent))
                }

                // Animasi fade in
                itemView.startAnimation(AnimationUtils.loadAnimation(itemView.context, android.R.anim.fade_in))
            }
        }
    }
}