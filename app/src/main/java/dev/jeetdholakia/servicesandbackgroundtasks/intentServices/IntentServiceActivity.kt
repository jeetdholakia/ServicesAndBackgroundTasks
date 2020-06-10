package dev.jeetdholakia.servicesandbackgroundtasks.intentServices

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.inputExtra
import dev.jeetdholakia.servicesandbackgroundtasks.R
import kotlinx.android.synthetic.main.activity_intent_service.*

class IntentServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
    }

    public fun startService(view: View) {
        val input = editTextInput.text.toString()

        val serviceIntent = Intent(this, MyIntentService::class.java)
        serviceIntent.putExtra(inputExtra, input)

        ContextCompat.startForegroundService(this, serviceIntent)
    }
}