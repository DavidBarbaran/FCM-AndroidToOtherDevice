# Firebase Cloud Messaging Android to android

<img src="https://i.pinimg.com/originals/13/7e/ba/137eba682a73749a60c58c95ae6347f8.png" width="100%"/>

Send notifications from your android to other devices

* User segment
* Topic
* A single device

Download via gradle:

In file build.gradle (Module: app) :
```css
dependencies {
	        implementation 'com.github.DavidBarbaran:fcm-android-to-android:1.0'
}
```

and in the file build.gradle (Project) :
```groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

### Usage of FCM Android to android:

Using in kotlin:
```kotlin
val firebasePush = FirebasePush("LEGACY_SERVER_KEY")
firebasePush.asyncResponse = object : PushNotificationTask.AsyncResponse{
    override fun onFinishPush(ouput: String) {
        Log.e("OUTPUT", ouput)
    }
}
firebasePush.notification = Notification("title","body")
// Send to topic
firebasePush.sendToTopic("news")
// or send to token
firebasePush.sendToToken("firebaseTokenId")
// or send to user segment
val jsonArray = JSONArray();
jsonArray.put("firebaseTokenId1")
jsonArray.put("firebaseTokenId2")
jsonArray.put("firebaseTokenId3")
firebasePush.sendToGroup(jsonArray)
```

Using in Java:
```java
FirebasePush firebasePush = new FirebasePush("LEGACY_SERVER_KEY");
firebasePush.setAsyncResponse(new PushNotificationTask.AsyncResponse() {
	@Override
	public void onFinishPush(@NotNull String ouput) {
          Log.e("OUTPUT", ouput);
    }
});
firebasePush.setNotification(new Notification("title","body"));

// Send to topic
firebasePush.sendToTopic("news");
// or send to token
firebasePush.sendToToken("firebaseTokenId");
// or send to user segment
JSONArray jsonArray = new JSONArray();
jsonArray.put("firebaseTokenId1");
jsonArray.put("firebaseTokenId2");
jsonArray.put("firebaseTokenId3");
firebasePush.sendToGroup(jsonArray);
```

Advanced use of Notification:
```kotlin
val notification = Notification("title", "body","icon", "sound.pm3","SplashActivity")
// or
val notification = Notification()
notification.title = "title"
notification.body = "body"
notification.icon = "icon"
notification.sound = "sound.mp3"
notification.clickAction = "SplashActivity"
notification.color = "#000000"
notification.tag = "youtTag"
notification.bodyLocalizationKey = "bodyLocalizationKey"
notification.titleLocalizationKey = "titleLocalizationKey"
```

Use of Data:
```kotlin
val firebasePush =  FirebasePush("LEGACY_SERVER_KEY")
val data = JSONObject()
data.put("key", "value")
firebasePush.data = data
```