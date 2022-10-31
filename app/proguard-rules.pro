# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

################ 基本混淆 ################
# 代码混淆压缩比，在0~7之间，默认为5，一般不需要改
-optimizationpasses 5

# 混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclasses

# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是ProGuard的4个步骤之一
# Android不需要preverify，去掉这一步可加快混淆速度
-dontpreverify

# 有了verbose这句话，混淆后就会生成映射文件

# 包含有类名->混淆后类名的映射关系
# 然后使用printmapping指定映射文件的名称
-verbose
-printmapping proguardMapping.txt

# 指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/arithmetic,!field/*,!c

# 保护代码中的Annotation不被混淆
# 这在JSON实体映射时非常重要，比如Gson
-keepattributes *Annotation*

# 避免混淆泛型，
# 这在JSON实体映射时非常重要，比如Gson
-keepattributes Signature
-keepattributes EnclosingMethod

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable


################ 需要保留的东西 ################
# 保留所有的本地navtive方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留在Activity中的方法参数是view的方法，从而我们在layout里面编写onClick不受影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# 枚举类不能被混淆
-keepclassmembers enum * {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.oyzb.wallpaper.util.LogUtil{*;}
-keep class com.oyzb.wallpaper.util.ToastUtils{*;}

# androidx 保留继承自系统自带组件等的子类
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

# 保留继承自系统自带组件等的子类
-dontwarn android.support.**
-keep class android.support.** {*;}
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.v7.** {*;}
-keep interface android.support.v7.** {*;}
-keep public class * extends android.support.v7.**

-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留 Parcelable 序列化的类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留 Serializable 序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 对于R资源下的所有类及方法，都不能被混淆
-keep class **.R$* {
    *;
}

# 对于带有回调函数onXXEvent的，不能被混淆
-keepclassmembers class * {
    void *(**on*Event);
}

# 保留 WebView
-keepclassmembers class * extends android.Webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
    public void *(android.webkit.WebView, java.lang.String);
}

# 保留 JavaScript
-keepclassmembers class com.chatlive.onetoone.go.ui.jsbridge.JsAndroid {
    <methods>;
}
-keepclassmembers class com.chatlive.onetoone.go.ui.webview.GameDialog$JsBridge {
    <methods>;
}

# 避免Log打印输出
-assumenosideeffects class android.util.Log {
   public static *** v(...);
   public static *** d(...);
   public static *** i(...);
   public static *** w(...);
}

-dontwarn javax.**
-keep class javax.**
-keep class java.lang.ClassValue

# model
-keep class com.oyzb.wallpaper.bean.*{*;}

################ 第三方 Jar ################
# retrofit
-keepclassmembernames, allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.Nullable
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions

# okhttp
-dontwarn okio.**
-keep class okio.**
-keep interface okio.**
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.* { *; }
-dontwarn javax.annotation.ParametersAreNonnullByDefault

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

#butterknife
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keep class !butterknife.*$*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

# eventbus
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# pictureselector
-dontwarn com.luck.picture.lib.**
-keep class com.luck.picture.lib.** { *; }
-keep interface com.luck.picture.lib.** { *; }

# ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }



# google
-keep class com.google.** {*;}
-keep interface com.google.** {*;}
-keep enum com.google.** {*;}
-keep class com.crashlytics.** {*;}
-keep interface com.crashlytics.** {*;}
-keep enum com.crashlytics.** {*;}
-keep class com.android.billingclient.** {*;}
-keep interface com.android.billingclient.** {*;}
-keep enum com.android.billingclient.** {*;}
-keep public class * extends com.android.billingclient.**{*;}

#brvh
-keep class com.chad.library.adapter.** {*;}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}
-keepattributes InnerClasses

#svga
-keep class com.squareup.wire.** { *; }
-keep class com.opensource.svgaplayer.proto.** { *; }

#agora
-keep class io.agora.**{*;}

#BMartPay
   -keep class com.just.agentweb.** {
        *;
    }
    -dontwarn com.just.agentweb.**
-keep public class com.bigfun.tm.PayActivity{*;}
#AppsFlyer
-dontwarn com.android.installreferrer
-keep class com.appsflyer.** { *; }
-dontwarn com.appsflyer.**
-keep public class com.google.firebase.messaging.FirebaseMessagingService {
  public *;
}