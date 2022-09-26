package com.xj.testweiget

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mAppWidgetManager = AppWidgetManager.getInstance(this)
        var widgets=mAppWidgetManager.installedProviders
        var appWidgetHost = AppWidgetHost(this@MainActivity, 1024)
        appWidgetHost.startListening()
        for (widget in widgets) {
                when (widget.label) {
                    "酷狗音乐(4x2)" -> {
                        val appWidgetId: Int = appWidgetHost.allocateAppWidgetId()

                        //需要签名成系统应用后才可以绑定成功
                        if (mAppWidgetManager.bindAppWidgetIdIfAllowed(
                                appWidgetId,
                                widget.provider
                            )
                        ) {
                            val hostView: View = appWidgetHost
                                .createView(application, appWidgetId,widget)

                            findViewById<LinearLayoutCompat>(R.id.ll_root).addView(hostView)

                        }
                        break

                    }

                    "" -> {}
                }
        }
    }
}