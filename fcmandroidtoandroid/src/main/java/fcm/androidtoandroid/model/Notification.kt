package fcm.androidtoandroid.model

import org.json.JSONObject

/**
 * Created by David on 3/08/2018.
 */
class Notification {
    var title: String? = null
    var body: String? = null
    var icon: String? = null
    var sound: String? = null
    var tag: String? = null
    var color: String? = null
    var clickAction: String? = null
    var bodyLocalizationKey: String? = null
    var bodyLocalizationArgs: Array<String>? = null
    var titleLocalizationKey: String? = null
    var titleLocalizationArgs: Array<String>? = null

    constructor()

    constructor(title: String?) {
        this.title = title
    }

    constructor(title: String?, body: String?) {
        this.title = title
        this.body = body
    }

    constructor(title: String?, body: String?, icon: String?) {
        this.title = title
        this.body = body
        this.icon = icon
    }

    constructor(title: String?, body: String?, icon: String?, sound: String?) {
        this.title = title
        this.body = body
        this.icon = icon
        this.sound = sound
    }

    constructor(title: String?, body: String?, icon: String?, sound: String?, tag: String?,
                color: String?, clickAction: String?, bodyLocalizationKey: String?,
                bodyLocalizationArgs: Array<String>?, titleLocalizationKey: String?,
                titleLocalizationArgs: Array<String>?) {
        this.title = title
        this.body = body
        this.icon = icon
        this.sound = sound
        this.tag = tag
        this.color = color
        this.clickAction = clickAction
        this.bodyLocalizationKey = bodyLocalizationKey
        this.bodyLocalizationArgs = bodyLocalizationArgs
        this.titleLocalizationKey = titleLocalizationKey
        this.titleLocalizationArgs = titleLocalizationArgs
    }

    constructor(title: String?, body: String?, icon: String?, sound: String?, clickAction: String?) {
        this.title = title
        this.body = body
        this.icon = icon
        this.sound = sound
        this.clickAction = clickAction
    }

    fun toJSONObject(): JSONObject {
        return JSONObject().apply {
            put("title", title)
            put("body", body)
            put("icon", icon)
            put("sound", sound)
            put("tag", tag)
            put("color", color)
            put("click_action", clickAction)
            put("body_loc_key", bodyLocalizationKey)
            put("body_loc_args", bodyLocalizationArgs)
            put("title_loc_key", titleLocalizationKey)
            put("title_loc_args", titleLocalizationArgs)
        }
    }
}