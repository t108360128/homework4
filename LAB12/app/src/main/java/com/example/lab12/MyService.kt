package com.example.lab12

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch { // 使用協程執行耗時任務
            try {
                delay(3000) // 延遲三秒
                // 宣告 Intent，從 MyService 啟動 SecActivity
                val intent = Intent(this@MyService, SecActivity::class.java)
                // 加入 Flag 表示要產生一個新的 Activity
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY // Service 終止後不再重啟
    }

    override fun onBind(intent: Intent?): IBinder? = null
}