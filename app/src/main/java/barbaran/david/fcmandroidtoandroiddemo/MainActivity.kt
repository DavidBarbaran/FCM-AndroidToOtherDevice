package barbaran.david.fcmandroidtoandroiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fcm.androidtoandroid.FirebasePush
import fcm.androidtoandroid.connection.PushNotificationTask
import fcm.androidtoandroid.model.Notification

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebasePush = FirebasePush("LEGACY_SERVER_KEY")
        firebasePush.asyncResponse = object : PushNotificationTask.AsyncResponse{
            override fun onFinishPush(ouput: String) {
                Log.e("RESPONSE", ouput)
            }
        }
        firebasePush.notification = Notification("TITLE","BODY")
        firebasePush.sendToTopic("update")
    }
}
