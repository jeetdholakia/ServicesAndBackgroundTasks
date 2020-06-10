package dev.jeetdholakia.servicesandbackgroundtasks.jobIntentServices

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.inputExtra
import dev.jeetdholakia.servicesandbackgroundtasks.R
import kotlinx.android.synthetic.main.activity_intent_service.*

class JobIntentServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_intent_service)
    }

    public fun enqueueWork(view: View) {
        val input = editTextInput.text.toString()

        val intent = Intent(this, MyJobIntentService::class.java)
        intent.putExtra(inputExtra, input)

        MyJobIntentService.enqueueWork(this, intent)
    }
}