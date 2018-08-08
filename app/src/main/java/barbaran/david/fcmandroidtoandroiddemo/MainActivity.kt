package barbaran.david.fcmandroidtoandroiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fcm.androidtoandroid.FirebasePush
import fcm.androidtoandroid.connection.PushNotificationTask
import fcm.androidtoandroid.model.Notification
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebasePush = FirebasePush("AIzaSyDyTunA6DBeIZwju-XdP6GLmOVpQkXuqck")
        firebasePush.asyncResponse = object : PushNotificationTask.AsyncResponse{
            override fun onFinishPush(ouput: String) {
                Log.e("RESPONSE", ouput)
            }
        }
        firebasePush.notification = Notification("TITLE","BODY")
        val jsonObject = JSONObject()
        jsonObject.put("key","value")
        firebasePush.data = jsonObject
        firebasePush.sendToTopic("update")
    }
}
