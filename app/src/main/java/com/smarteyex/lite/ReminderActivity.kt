package com.smarteyex.lite

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ReminderActivity : AppCompatActivity() {

    private lateinit var reminderInput: EditText
    private lateinit var timeButton: Button
    private lateinit var addButton: Button
    private lateinit var reminderRecyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var emptyText: TextView

    private var selectedHour = 0
    private var selectedMinute = 0
    private val reminders = mutableListOf<ReminderItem>()
    private val gson = Gson()

    data class ReminderItem(
        val id: String,
        val text: String,
        val hour: Int,
        val minute: Int,
        val timestamp: Long
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        initViews()
        loadReminders()
        setupListeners()
        displayReminders()
    }

    private fun initViews() {
        reminderInput = findViewById(R.id.reminderInput)
        timeButton = findViewById(R.id.timeButton)
        addButton = findViewById(R.id.addButton)
        reminderRecyclerView = findViewById(R.id.reminderRecyclerView)
        backButton = findViewById(R.id.backButton)
        emptyText = findViewById(R.id.emptyText)

        reminderRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set default time to current time + 1 hour
        val calendar = Calendar.getInstance()
        selectedHour = (calendar.get(Calendar.HOUR_OF_DAY) + 1) % 24
        selectedMinute = calendar.get(Calendar.MINUTE)
        timeButton.text = String.format("%02d:%02d", selectedHour, selectedMinute)
    }

    private fun setupListeners() {
        timeButton.setOnClickListener {
            showTimePicker()
        }

        addButton.setOnClickListener {
            addReminder()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(this, { _, hour, minute ->
            selectedHour = hour
            selectedMinute = minute
            timeButton.text = String.format("%02d:%02d", hour, minute)
        }, selectedHour, selectedMinute, true)
        timePicker.show()
    }

    private fun addReminder() {
        val text = reminderInput.text.toString().trim()
        if (text.isEmpty()) {
            Toast.makeText(this, "Masukkan pesan reminder", Toast.LENGTH_SHORT).show()
            return
        }

        val reminder = ReminderItem(
            id = UUID.randomUUID().toString(),
            text = text,
            hour = selectedHour,
            minute = selectedMinute,
            timestamp = System.currentTimeMillis()
        )

        reminders.add(reminder)
        saveReminders()
        scheduleReminder(reminder)

        reminderInput.text.clear()
        displayReminders()

        Toast.makeText(this, "⏰ Reminder disimpan untuk pukul ${String.format("%02d:%02d", selectedHour, selectedMinute)}", Toast.LENGTH_LONG).show()
    }

    private fun scheduleReminder(reminder: ReminderItem) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, reminder.hour)
            set(Calendar.MINUTE, reminder.minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val intent = Intent(this, ReminderReceiver::class.java).apply {
            putExtra("message", reminder.text)
            putExtra("id", reminder.id)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            this, reminder.id.hashCode(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun saveReminders() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = gson.toJson(reminders)
        prefs.edit().putString("reminders", json).apply()
    }

    private fun loadReminders() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("reminders", "[]")
        val type = object : TypeToken<MutableList<ReminderItem>>() {}.type
        val loaded: MutableList<ReminderItem> = gson.fromJson(json, type)
        reminders.clear()
        reminders.addAll(loaded)
    }

    private fun displayReminders() {
        if (reminders.isEmpty()) {
            emptyText.visibility = android.view.View.VISIBLE
            reminderRecyclerView.visibility = android.view.View.GONE
        } else {
            emptyText.visibility = android.view.View.GONE
            reminderRecyclerView.visibility = android.view.View.VISIBLE
            val adapter = ReminderAdapter(reminders) { reminder ->
                cancelReminder(reminder)
                reminders.remove(reminder)
                saveReminders()
                displayReminders()
                Toast.makeText(this, "Reminder dihapus", Toast.LENGTH_SHORT).show()
            }
            reminderRecyclerView.adapter = adapter
        }
    }

    private fun cancelReminder(reminder: ReminderItem) {
        val intent = Intent(this, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, reminder.id.hashCode(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    inner class ReminderAdapter(
        private val items: List<ReminderItem>,
        private val onDelete: (ReminderItem) -> Unit
    ) : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.item_reminder, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
            holder.itemView.setOnLongClickListener {
                onDelete(item)
                true
            }
        }

        override fun getItemCount() = items.size

        inner class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
            private val timeText: TextView = itemView.findViewById(R.id.reminderTime)
            private val messageText: TextView = itemView.findViewById(R.id.reminderMessage)
            private val statusText: TextView = itemView.findViewById(R.id.reminderStatus)

            fun bind(reminder: ReminderItem) {
                timeText.text = String.format("%02d:%02d", reminder.hour, reminder.minute)
                messageText.text = reminder.text
                statusText.text = "⏰ Active"
            }
        }
    }
}