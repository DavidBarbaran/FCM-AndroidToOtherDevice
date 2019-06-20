package fcm.androidtoandroid

import fcm.androidtoandroid.config.SendType.TO_GROUP
import fcm.androidtoandroid.config.SendType.TO_TOKEN
import fcm.androidtoandroid.config.SendType.TO_TOPIC
import fcm.androidtoandroid.connection.PushNotificationTask
import fcm.androidtoandroid.model.Notification
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by David on 3/08/2018.
 */
class FirebasePush constructor(private val serverKey: String) : PushService {

    companion object {
        private const val API_URL_FCM = "https://fcm.googleapis.com/fcm/send"
    }

    var notification: Notification = Notification()
    var data = JSONObject()
    private var root: JSONObject = JSONObject()
    var asyncResponse: PushNotificationTask.AsyncResponse? = null

    override fun sendToTopic(topic: String) {
        root.put(TO_TOPIC, "'$topic' in topics")
        sendPushNotification(true)
    }

    override fun sendToGroup(mobileTokens: JSONArray) {
        root.put(TO_GROUP, mobileTokens)
        sendPushNotification(false)
    }

    override fun sendToToken(token: String) {
        root.put(TO_TOKEN, token)
        sendPushNotification(false)
    }

    private fun sendPushNotification(toTopic: Boolean) {
        with(URL(API_URL_FCM).openConnection() as HttpURLConnection) {

            useCaches = false
            doInput = true
            doOutput = true
            requestMethod = "POST"

            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("Accept", "application/json")
            setRequestProperty("Authorization", "key=$serverKey")

            root.put("notification", notification.toJSONObject())
            root.put("data", data)

            val pushNotificationTask = PushNotificationTask(this, root, toTopic)
            pushNotificationTask.asyncResponse = asyncResponse
            pushNotificationTask.execute()
        }
    }
}