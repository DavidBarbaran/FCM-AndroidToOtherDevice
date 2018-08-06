package fcm.androidtoandroid.connection

import android.os.AsyncTask
import fcm.androidtoandroid.FirebasePush
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

/**
 * Created by David on 3/08/2018.
 */
class PushNotificationTask(private var conn: HttpURLConnection,
                           private var root: JSONObject,
                           private var toTopic: Boolean) : AsyncTask<Void, Void, String>() {

    var asyncResponse: AsyncResponse? = null

    override fun doInBackground(vararg p0: Void?): String {
        var wr: OutputStreamWriter? = null
        var br: BufferedReader? = null
        try {
            wr = OutputStreamWriter(conn.outputStream)
            wr.write(root.toString())
            wr.flush()

            br = BufferedReader(InputStreamReader(conn.inputStream))

            val builder = StringBuilder()

            BufferedReader(br).use { r ->
                r.lineSequence().forEach {
                    builder.append(it)
                }
            }

            val result = builder.toString()
            val obj = JSONObject(result)

            if (toTopic) {
                if (obj.has("message_id")) {
                    return "SUCCESS"
                }
            } else {
                val success = Integer.parseInt(obj.getString("success"))
                if (success > 0) {
                    return "SUCCESS"
                }
            }
            return builder.toString()
        } catch (e: Exception) {
            return e.message!!
        } finally {
            if (wr != null) wr.close()
            if (br != null) br.close()
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (asyncResponse != null) {
            asyncResponse?.onFinishPush(result!!)
        }
    }

    interface AsyncResponse {
        fun onFinishPush(ouput: String)
    }
}