package com.aboust.timeago

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.aboust.timeago.TimeAgo.Companion.TAG
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


/**
 * 相对时间生成器
 *
 *
 * 相关类扩展如下:
 *
 *  @see java.util.Date
 *
 * 针对 android Q版本以上
 *
 *  @see java.time.LocalDate
 *  @see java.time.LocalDateTime
 *
 * 如需要定制国际化文件 请在strings.xml实现对应的国际化资源即可。
 *
 * 默认值为【英文】
 *
 * <string name="ago">前</string>
 * <string name="milliseconds">毫秒</string>
 * <string name="minute">1分钟</string>
 * <string name="minutes">分钟</string>
 * <string name="seconds">秒</string>
 * <string name="second">1秒</string>
 * <string name="hour">1小时</string>
 * <string name="hours">小时</string>
 * <string name="just_now">刚刚</string>
 * <string name="week">1星期</string>
 * <string name="last_week">最后1星期</string>
 * <string name="weeks">星期</string>
 * <string name="month">1个月</string>
 * <string name="months">个月</string>
 * <string name="months">个月</string>
 * <string name="last_month">最后1月</string>
 * <string name="year">1年</string>
 * <string name="last_year">最后1年</string>
 * <string name="years">年</string>
 * <string name="yesterday">昨天</string>
 * <string name="day">1天</string>
 * <string name="days">天</string>
 *
 *
 */
open class TimeAgo internal constructor(var context: Context) {


    /**
     * Returns more complex string like "a minute ago" , "a second ago"
     *
     * @return
     */
    fun ago(target: Date): String {
        val now = Calendar.getInstance().time
        val deltaMillis = intervalInMillis(target, now)
        val deltaSeconds = TimeUnit.MILLISECONDS.toSeconds(deltaMillis)
        val deltaMinutes = TimeUnit.MILLISECONDS.toMinutes(deltaMillis)
        return when {
            deltaSeconds < 1 -> String.format("%d%s%s", deltaMillis, getText("milliseconds"), getText("ago"))
            deltaSeconds < 5 -> getText("just_now")
            deltaSeconds < 60 -> String.format("%d%s%s", deltaSeconds, getText("seconds"), getText("ago"))
            deltaSeconds < 120 -> String.format("%s%s", getText("minute"), getText("ago"))
            deltaMinutes < 60 -> String.format("%d%s%s", deltaMinutes, getText("minutes"), getText("ago"))
            deltaMinutes < 120 -> String.format("%s%s", getText("hour"), getText("ago"))
            deltaMinutes < 24 * 60 -> String.format("%d%s%s", deltaMinutes / 60, getText("hours"), getText("ago"))
            deltaMinutes < 24 * 60 * 2 -> getText("yesterday")
            deltaMinutes < 24 * 60 * 7 -> String.format("%d%s%s", deltaMinutes / (60 * 24), getText("days"), getText("ago"))
            deltaMinutes < 24 * 60 * 14 -> getText("last_week")
            deltaMinutes < 24 * 60 * 31 -> String.format("%d%s%s", deltaMinutes / (60 * 24 * 7), getText("weeks"), getText("ago"))
            deltaMinutes < 24 * 60 * 61 -> getText("last_month")
            deltaMinutes < 24.0 * 60.0 * 365.25 -> String.format("%d%s%s", deltaMinutes / (60 * 24 * 30), getText("months"), getText("ago"))
            deltaMinutes < 24 * 60 * 731 -> getText("last_year")
            else -> String.format("%d%s%s", deltaMinutes / (60 * 24 * 365), getText("years"), getText("ago"))
        }.trimStart()
    }

    /**
     * text
     */
    private fun getText(key: String): String {
        return try {
            val resId = getFieldValue("string", key)
            context.getString(resId)
        } catch (e: java.lang.Exception) {
            " $key".replace("_", " ")
        }

    }

    /**
     *
     * 根据给定的类型名和字段名，返回R文件中的字段的值
     *
     * @param typeName 属于哪个类别的属性 （id,layout,drawable,string,color,attr......）
     * @param fieldName 字段名
     *
     * @return 字段的值 * @throws Exception
     **/
    private fun getFieldValue(typeName: String, fieldName: String): Int {
        val clazz = Class.forName(context.packageName + ".R$" + typeName)
        return clazz.getField(fieldName).getInt(null)
    }

    private fun intervalInMillis(first: Date, second: Date): Long {
        return abs(first.time - second.time)
    }


    companion object {
        const val TAG = "TimeAgo"
    }

}

/**
 * 测试代码
 */
public fun test(context: Context?) {
    if (null == context) return
    Log.d(TAG, "=================================================")

    val sdf = SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S", Locale.getDefault())
    val c: Calendar = Calendar.getInstance()
    c.time = Date()
    c.add(Calendar.YEAR, -10)
    val yy = c.time
    Log.d(TAG, String.format("time => %s , %s", yy.timeAgo(context), sdf.format(yy)))

    c.time = Date()
    c.add(Calendar.YEAR, -1)
    val y = c.time
    Log.d(TAG, String.format("time => %s , %s", y.timeAgo(context), sdf.format(y)))

    c.time = Date()
    c.add(Calendar.MONTH, -1)
    val m = c.time
    Log.d(TAG, String.format("time => %s , %s", m.timeAgo(context), sdf.format(m)))

    c.time = Date()
    c.add(Calendar.DATE, -7)
    val dd = c.time
    Log.d(TAG, String.format("time => %s , %s", dd.timeAgo(context), sdf.format(dd)))

    c.time = Date()
    c.add(Calendar.DAY_OF_YEAR, -70)
    val d = c.time
    Log.d(TAG, String.format("time => %s , %s", d.timeAgo(context), sdf.format(d)))

    c.time = Date()
    c.add(Calendar.HOUR, -1)
    val h = c.time
    Log.d(TAG, String.format("time => %s , %s", h.timeAgo(context), sdf.format(h)))

    c.time = Date()
    c.add(Calendar.MINUTE, -1)
    val mm = c.time
    Log.d(TAG, String.format("time => %s , %s", mm.timeAgo(context), sdf.format(mm)))

    c.time = Date()
    c.add(Calendar.SECOND, -10)
    val s = c.time
    Log.d(TAG, String.format("time => %s , %s", s.timeAgo(context), sdf.format(s)))


    c.time = Date()
    c.add(Calendar.SECOND, -1000)
    val ss = c.time
    Log.d(TAG, String.format("time => %s , %s", ss.timeAgo(context), sdf.format(ss)))


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val ldt = LocalDateTime.now()
        val zdt = ldt.atZone(ZoneId.systemDefault())
        val ddd = Date.from(zdt.toInstant())
        Log.d(TAG, String.format("time => %s , %s", ldt.timeAgo(context), sdf.format(ddd)))

        val ld = LocalDate.now()
        val zonedDateTime: ZonedDateTime = ld.atStartOfDay(ZoneId.systemDefault())
        val dddd = Date.from(zonedDateTime.toInstant())
        Log.d(TAG, String.format("time => %s , %s", ld.timeAgo(context), sdf.format(dddd)))

    }
}

public fun Date.timeAgo(context: Context): String = TimeAgo(context).ago(this)

@RequiresApi(Build.VERSION_CODES.O)
public fun LocalDateTime.timeAgo(context: Context): String {
    val zoneId = ZoneId.systemDefault()
    val zdt = this.atZone(zoneId)
    return TimeAgo(context).ago(Date.from(zdt.toInstant()))
}

@RequiresApi(Build.VERSION_CODES.O)
public fun LocalDate.timeAgo(context: Context): String {
    val zonedDateTime: ZonedDateTime = this.atStartOfDay(ZoneId.systemDefault())
    return TimeAgo(context).ago(Date.from(zonedDateTime.toInstant()))
}