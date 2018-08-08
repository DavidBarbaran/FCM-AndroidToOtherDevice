package fcm.androidtoandroid

import fcm.androidtoandroid.connection.PushNotificationTask
import fcm.androidtoandroid.model.Notification
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by David on 3/08/2018.
 */
class FirebasePush constructor(val serverKey: String) : PushService {

    private val LOG = this.javaClass.simpleName
    private val API_URL_FCM = "https://fcm.googleapis.com/fcm/send"
    var notification: Notification = Notification()
    var data = JSONObject()
    private var root: JSONObject = JSONObject()
    var asyncResponse: PushNotificationTask.AsyncResponse? = null

    fun sendToTopic(topic: String) {
        root.put("notification", notification.toJSONObject())
        root.put("data", data)
        root.put("condition", "'$topic' in topics")
        sendPushNotification(true)
    }

    override fun sendToGroup(mobileTokens: JSONArray) {
        root.put("notification", notification.toJSONObject())
        root.put("data", data)
        root.put("registration_ids", mobileTokens)
        sendPushNotification(false)
    }

    override fun sendToToken(token: String) {
        root.put("notification", notification.toJSONObject())
        root.put("data", data)
        root.put("to", token)
        sendPushNotification(false)
    }

    private fun sendPushNotification(toTopic: Boolean) {
        val url = URL(API_URL_FCM)
        val conn = url.openConnection() as HttpURLConnection

        conn.useCaches = false
        conn.doInput = true
        conn.doOutput = true
        conn.requestMethod = "POST"

        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")
        conn.setRequestProperty("Authorization", "key=$serverKey")

        val pushNotificationTask = PushNotificationTask(conn, root, toTopic)
        pushNotificationTask.asyncResponse = asyncResponse
        pushNotificationTask.execute()
    }
}