package com.mohan.serviceexample

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class BackgroundService : Service() {
    lateinit var myPlayer : MediaPlayer

    private lateinit var handlerThread : HandlerThread

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    private val TAG = "BackgroundService"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate is called")
        myPlayer= MediaPlayer.create(this,R.raw.song)
        myPlayer.isLooping= false

        thread(start = true) {
            myPlayer.start()
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
        Log.d(TAG,"onStartCommand is called")
    }




    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy is called")
        myPlayer.stop()
    }
}