package barbaran.david.fcmandroidtoandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fcm.androidtoandroid.FirebasePush
import fcm.androidtoandroid.model.Notification

class MainActivity : AppCompatActivity() {

    private val serverKey = "your_firebase_server_key"
    private val topic = "your_topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebasePush.build(serverKey)
                .setNotification(Notification("FCM-AndroidToOtherDevice", "This is a body"))
                .setOnFinishPush { onFinishPush() }
                .sendToTopic(topic)
    }

    fun onFinishPush() {
        // Actions on end...
    }
}
