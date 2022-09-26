# App中预置widget

1，AndroidManifest.xml中配置

```
android:sharedUserId="android.uid.system"
```

增加权限

```
<uses-permission android:name="android.permission.BIND_APPWIDGET"
    tools:ignore="ProtectedPermissions" />
```

注意：必须使用系统签名打包App才能正确创建widget



2,创建widget RemoteView,(酷狗音乐示例)

```
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
```





问题：提示无法添加微件时，createView的第一个参数需要使用application
