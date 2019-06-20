package fcm.androidtoandroid

import fcm.androidtoandroid.connection.PushNotificationTask
import fcm.androidtoandroid.model.Notification
import org.json.JSONObject

/**
 * Created by David on 20/056/2019.
 */

interface FirebasePushBuilder {
    fun setNotification(notification: Notification) : FirebasePush
    fun setData(data: JSONObject) : FirebasePush
    fun setOnFinishPush(asyncResponse: PushNotificationTask.AsyncResponse) : FirebasePush
    fun setOnFinishPush(onFinishPush: () -> Unit) : FirebasePush
}