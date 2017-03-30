package com.hoofee.everything.main.enums;

/**
 * Created by hufei on 2016/9/1.
 * 男女的枚举，枚举范例
 */
public enum SexType {
    //1=男，0=女，-1=""
    MAN {
        @Override
        int getValueInt() {
            return 1;
        }

        @Override
        String getValueStr() {
            return "男";
        }
    },
    WOMAN {
        @Override
        int getValueInt() {
            return 0;
        }

        @Override
        String getValueStr() {
            return "女";
        }
    },
    NULL {
        @Override
        int getValueInt() {
            return -1;
        }

        @Override
        String getValueStr() {
            return "";
        }
    };

    public static SexType getSexEnum(int sexInt) {
        switch (sexInt) {
            case 0:
                return WOMAN;
            case 1:
                return MAN;
            default:
                return NULL;
        }
    }

    public static SexType getSexEnum(String sexStr) {
        switch (sexStr.trim()) {
            case "男":
                return MAN;
            case "女":
                return WOMAN;
            default:
                return NULL;
        }
    }

    abstract int getValueInt();

    abstract String getValueStr();
}
