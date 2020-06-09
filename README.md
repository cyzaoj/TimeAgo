## TimeAgo - An Android TimeAgo Library, now in Kotlin!

class: LocalDate LocalDateTime Date support time ago.
 


[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![Download](https://api.bintray.com/packages/aboust/android/time_ago/images/download.svg)](https://bintray.com/aboust/android/time_ago/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-orange.svg?style=flat)](https://img.shields.io/github/license/cyzaoj/TimeAgo) 
[![license](https://img.shields.io/github/license/cyzaoj/TimeAgo)](https://img.shields.io/github/license/cyzaoj/TimeAgo) 



<BR>
<BR>

## Configure

#### Maven
```xml
<dependency>
  <groupId>com.aboust</groupId>
  <artifactId>time_ago</artifactId>
  <version>${latestVersion}</version>
  <type>pom</type>
</dependency>
```

#### JCenter

First. add to project build.gradle

``` gradle
repositories {
    jcenter()
}
```

Second. add to module build.gradle

```gradle
implementation "com.aboust:time_ago:${latestVersion}"
```

## Usage


First. coding in kotlin:

``` kotlin

  Date().timeAgo(context)
  
  BuildConfig.>= Android Q:
  LocalDateTime.now().timeAgo(context)
  LocalDate.now().timeAgo(context)
        
```

Second. edit strings.xml
(Optional)  如果不配置values/strings.xml相应配置默认为英文
  
```xml
<string name="ago">前</string>
<string name="milliseconds">毫秒</string>
<string name="minute">1分钟</string>
<string name="minutes">分钟</string>
<string name="seconds">秒</string>
<string name="second">1秒</string>
<string name="hour">1小时</string>
<string name="hours">小时</string>
<string name="just_now">刚刚</string>
<string name="week">1星期</string>
<string name="last_week">最后1星期</string>
<string name="weeks">星期</string>
<string name="month">1个月</string>
<string name="months">个月</string>
<string name="months">个月</string>
<string name="last_month">最后1月</string>
<string name="year">1年</string>
<string name="last_year">最后1年</string>
<string name="years">年</string>
<string name="yesterday">昨天</string>
<string name="day">1天</string>
<string name="days">天</string>
```

