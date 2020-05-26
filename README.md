
# Firebase Cloud Messaging Android to other devices  
  
<img src="https://i.pinimg.com/originals/13/7e/ba/137eba682a73749a60c58c95ae6347f8.png" width="100%"/>  
  
Send notifications from your android to other devices  
  
* User segment  
* Topic  
* A single device  
  
Download via gradle:  
  
In file build.gradle (Module: app) :  
```groovy  
dependencies {  
   implementation 'com.github.DavidBarbaran:FCM-AndroidToOtherDevice:1.1.2'
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
  
[How to get firebase server key?](https://github.com/DavidBarbaran/FCM-AndroidToOtherDevice/wiki/How-to-get-the-firebase-server-key)
  
#### Basic Usage:  
```kotlin  
FirebasePush.build("SERVER_KEY")
        .setNotification(Notification("FCM-AndroidToOtherDevice", "This is a body"))  
        .sendToTopic("news")  
```  
  
#### Other usages:  
  
* Using in Kotlin:  
  

```kotlin  
val notification = Notification("FCM-AndroidToOtherDevice", "This is a body")  
  
val yourExtraData = JSONObject().put("key", "name")  
  
val firebasePush = FirebasePush.build("SERVER_KEY")
        .setNotification(notification)  
        .setData(yourExtraData)  
        .setOnFinishPush { onFinishPush() }  

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
  
* Using in Java:  
```java  
FirebasePush firebasePush = new FirebasePush("SERVER_KEY");
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
val notification = Notification("title", "body","icon", "sound.mp3","SplashActivity")  
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

### Reporting issues or improvements  🛠

Found a bug or a problem on a specific feature? Open an issue on  [Github issues](https://github.com/DavidBarbaran/FCM-AndroidToOtherDevice/issues)