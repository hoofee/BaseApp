package com.hoofee.everything.main.utils;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateTimeUtils {
    public static String NORMALDATEFORMAT = "MM-dd kk:mm";

    /**
     * 获取当前时间戳(毫秒),13位
     *
     * @return
     */
    public static long getCurDateInt() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis;
    }

    /**
     * 获取当前时间戳(毫秒),10位
     *
     * @return
     */
    public static long getCurDateInt10() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis / 1000;
    }

    /**
     * 获取某时间的某种格式
     *
     * @param date
     * @param format
     * @param calendarField Calendar.MONTH
     * @param n
     * @return
     */
    public static String getDateTime(Date date, String format, Integer calendarField, Integer n) {
        if (calendarField != null && n != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(calendarField, n);
            date = cal.getTime();
        }
        if (format == null) {
            format = DateTimeUtils.NORMALDATEFORMAT;
        }
        String timestr = new SimpleDateFormat(format).format(date);
        return timestr;
    }

    /**
     * 将10位整形数字转换成对应时间格式
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDateTime(int time, String format) {
        // "MM-dd kk:mm"
        try {
            if (format == null || "".equals(format.trim())) {
                format = DateTimeUtils.NORMALDATEFORMAT;// "MM-dd kk:mm";
            }
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date curDate = new Date(time * 1000L);// 获取当前时间
            String timestr = formatter.format(curDate);
            return timestr;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将10位整形数字转换带上下午的时间格式
     *
     * @param time
     * @return
     */

    public static String getDateTime(int time) {
        try {
            String tmpDate = getDateTime(time, "yyyy.MM.dd HH");
            int isAm = Integer.parseInt(tmpDate.substring(tmpDate.length() - 2, tmpDate.length()));
            if (isAm < 12) {
                return tmpDate.substring(0, tmpDate.length() - 2) + "  上午";
            } else {
                return tmpDate.substring(0, tmpDate.length() - 2) + "  下午";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将日期2016-5-6替换为2016.5.6
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDateTimeString(String time) {
        return time.replace("-", ".");
    }

    /**
     * 将13位长整形数字转换成时间格式
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDateTime(long time, String format) {
        // "MM-dd kk:mm"
        try {
            if (format == null) {
                format = DateTimeUtils.NORMALDATEFORMAT;
            }
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date curDate = new Date(time);// 获取当前时间
            String timestr = formatter.format(curDate);
            return timestr;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前日期时间 ，时间格式(MM-dd kk:mm)
     *
     * @return
     */
    public static String getCurDateTime() {
        // "MM-dd kk:mm"
        return getCurDateTime(DateTimeUtils.NORMALDATEFORMAT);
    }

    public static String getCurDateTime(String format) {
        // "MM-dd kk:mm"
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String timestr = formatter.format(new Date());
            return timestr;
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 判断时间戳是否过一天
     *
     * @param time
     * @return
     */
    public static boolean isPassOneDay(long time) {
        long curLongTime = getCurDateInt();
        long loneday = 86400000;
        return curLongTime - time > loneday;
    }

    /**
     * 获取 当前时间往后推一段时间 的格式化时间
     *
     * @param format
     * @param calendarField
     * @param affterTime
     * @return
     */
    public static String getCurDateAffterTime(String format, int calendarField, int affterTime) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(calendarField, affterTime);
        String time = new SimpleDateFormat(format).format(calendar.getTime());
        return time;
    }

    /**
     * 返回指定时间往后推一段时间 的格式化时间
     *
     * @param date          格式：2015-10-20
     * @param format        返回的时间格式
     * @param calendarField 指定往后推一点时间的单位，如时/分/秒/天
     * @param affterTime    单位的数值
     * @return
     */
    public static String getDateAffterTime(String date, String format, int calendarField, int affterTime) {
        Calendar calendar = Calendar.getInstance();
        String[] dateArr = date.split("-");
        calendar.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1, Integer.parseInt(dateArr[2]));
        calendar.add(calendarField, affterTime);
        String time = new SimpleDateFormat(format).format(calendar.getTime());
        return time;
    }

    /**
     * 将传入的时间往后推一段时间，再跟系统时间做比较，判断是否过期
     *
     * @param date          传入的时间，格式：2015-10-20
     * @param calendarField 指定往后推一点时间的单位，如时/分/秒/天
     * @param affterTime    单位的数值
     * @return true代表过期
     */
    public static boolean isValidityDate(String date, int calendarField, int affterTime) {
        Long validityDate = Long.parseLong(getDateAffterTime(date, "yyyyMMdd", calendarField, affterTime));
        Long systemDate = Long.parseLong(DateTimeUtils.getCurDateTime("yyyyMMdd"));
        if (validityDate > systemDate)
            return false;
        return true;
    }

    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 拿一个月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        // 年
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 月，因为Calendar里的月是从0开始，所以要-1
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        // 日，设为一号
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
    }

    /**
     * 使用参数Format将字符串转为Date
     *
     * @throws java.text.ParseException
     */
    public static Date parse(String strDate, String pattern) throws ParseException, java.text.ParseException {
        return new SimpleDateFormat(pattern).parse(strDate);
    }

    /***
     * 帅气的勇勇
     * 获取今天到上一个星期的日期,首页血液库存的日期存储
     *
     * @return
     */
    public static List<String> getStringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        List<String> stringList = new ArrayList<>();
        int y = 0;
        for (int i = 0; i < 6; i++) {
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) - i);// 获取当前月份的日期号码
            if ((c.get(Calendar.DAY_OF_MONTH) - i) <= 0) {
                mMonth = String.valueOf(c.get(Calendar.MONTH));
                int MaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                mDay = String.valueOf(MaxDay - y);
                y++;
            }
            stringList.add(mMonth + "/" + mDay);
        }
        return stringList;
    }

    /***
     * 帅气的勇勇
     * 获取今天到上一个星期的分别是星期几
     *
     * @return
     */
    public static List<String> getWeekData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        List<String> weekList = new ArrayList<>();
        weekList.add("今天");
        weekList.add("昨天");
        for (int i = 2; i < 6; i++) {
            if (i != 0) {
                mWay = String.valueOf(Integer.parseInt(mWay) - 1);
            } else {
                mWay = "7";
            }

            String wayStr = "";
            switch (mWay) {
                case "1":
                    wayStr = "周日";
                    break;
                case "2":
                    wayStr = "周一";
                    break;
                case "3":
                    wayStr = "周二";
                    break;
                case "4":
                    wayStr = "周三";
                    break;
                case "5":
                    wayStr = "周四";
                    break;
                case "6":
                    wayStr = "周五";
                    break;
                case "7":
                    wayStr = "周六";
                    break;
            }
            weekList.add(wayStr);
        }
        return weekList;
    }

}
