package fcm.androidtoandroid.model

import org.json.JSONObject

/**
 * Created by David on 3/08/2018.
 */
class Notification(var title: String = "", var body: String = "", var icon: String = "",
                   var sound: String = "", var tag: String = "", var color: String = "",
                   var clickAction: String = "", var bodyLocalizationKey: String = "",
                   var bodyLocalizationArgs: Array<String> = emptyArray(),
                   var titleLocalizationKey: String = "",
                   var titleLocalizationArgs: Array<String> = emptyArray()) {

    fun toJSONObject(): JSONObject {
        val data = JSONObject()
        data.put("title", title)
        data.put("body", body)
        data.put("icon", icon)
        data.put("sound", sound)
        data.put("tag", tag)
        data.put("color", color)
        data.put("click_action", clickAction)
        data.put("body_loc_key", bodyLocalizationKey)
        data.put("body_loc_args", bodyLocalizationArgs)
        data.put("title_loc_key", titleLocalizationKey)
        data.put("title_loc_args", titleLocalizationArgs)
        return data
    }
}