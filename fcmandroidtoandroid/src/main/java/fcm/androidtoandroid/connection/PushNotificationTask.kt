package fcm.androidtoandroid.connection

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
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
        try {
            wr = OutputStreamWriter(conn.outputStream)
            wr.write(root.toString())
            wr.flush()

            val builder = StringBuilder()

            conn.inputStream.bufferedReader().use { reader ->
                builder.append(reader.readLine())
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
            Log.e("PushNotification", e.message, e)
            return "Error in post to ${e.message}"
        } finally {
            wr?.close()
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (asyncResponse != null) {
            result?.let {
                asyncResponse?.onFinishPush(it)
            }
        }
    }

    interface AsyncResponse {
        fun onFinishPush(ouput: String)
    }
}