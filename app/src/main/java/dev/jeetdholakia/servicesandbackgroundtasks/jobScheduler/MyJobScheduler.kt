package dev.jeetdholakia.servicesandbackgroundtasks.jobScheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobScheduler: JobService() {
    companion object {
        private const val TAG = "MyJobScheduler"
    }

    private var jobCancelled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob")
        doBackgroundJob(params)
        return true
    }

    @Throws
    private fun doBackgroundJob(params: JobParameters?) {
        Thread(Runnable {
            for(i in 0..10) {
                Log.d(TAG, "run: $i")
                if(jobCancelled) {
                    return@Runnable
                }
                Thread.sleep(1000)
            }

            Log.d(TAG, "doBackgroundJob: Job finished")
            jobFinished(params, false)
        }).start()
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob: Job cancelled before completion")
        jobCancelled = true
        return true
    }
}

