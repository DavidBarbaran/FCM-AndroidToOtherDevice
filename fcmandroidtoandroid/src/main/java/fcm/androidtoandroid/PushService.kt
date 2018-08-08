package fcm.androidtoandroid

import org.json.JSONArray

/**
 * Created by David on 3/08/2018.
 */
interface PushService {
    fun sendToGroup(mobileTokens: JSONArray)
    fun sendToToken(token: String)
}