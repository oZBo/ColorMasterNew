package braincollaboration.colormaster.engine;

import android.content.Context;

import java.util.Random;

import braincollaboration.colormaster.R;

class ColorHelper {

    private static Random random = new Random();

    public static String getColorName(Context mContext, int colorNumber) {
        switch (colorNumber) {
            case 0:
                return mContext.getResources().getString(R.string.color_red);
            case 1:
                return mContext.getResources().getString(R.string.color_pink);
            case 2:
                return mContext.getResources().getString(R.string.color_purple);
            case 3:
                return mContext.getResources().getString(R.string.color_indigo);
            case 4:
                return mContext.getResources().getString(R.string.color_blue);
            case 5:
                return mContext.getResources().getString(R.string.color_turquoise);
            case 6:
                return mContext.getResources().getString(R.string.color_green);
            case 7:
                return mContext.getResources().getString(R.string.color_yellow);
            case 8:
                return mContext.getResources().getString(R.string.color_orange);
            case 9:
                return mContext.getResources().getString(R.string.color_brown);
            default:
                return "WTF???";
        }
    }

    public static int getColorValue(Context mContext, int colorNumber) {
        switch (colorNumber) {
            case 0:
                return mContext.getResources().getColor(R.color.red);
            case 1:
                return mContext.getResources().getColor(R.color.pink);
            case 2:
                return mContext.getResources().getColor(R.color.purple);
            case 3:
                return mContext.getResources().getColor(R.color.indigo);
            case 4:
                return mContext.getResources().getColor(R.color.blue);
            case 5:
                return mContext.getResources().getColor(R.color.turquoise);
            case 6:
                return mContext.getResources().getColor(R.color.green);
            case 7:
                return mContext.getResources().getColor(R.color.yellow);
            case 8:
                return mContext.getResources().getColor(R.color.orange);
            case 9:
                return mContext.getResources().getColor(R.color.brown);
            default:
                return 505;
        }
    }
        //TODO add dependency between color and background
//    public static Drawable getColorImageBackground(Context mContext, int colorNumber) {
//        int randomSeedBackground = random.nextInt(140);
//        switch (randomSeedBackground) {
//            case 0:
//                return mContext.getResources().getDrawable(R.drawable.bg_1);
//            case 1:
//                return mContext.getResources().getDrawable(R.drawable.bg_2);
//            case 2:
//                return mContext.getResources().getDrawable(R.drawable.bg_3);
//            case 3:
//                return mContext.getResources().getDrawable(R.drawable.bg_4);
//            case 4:
//                return mContext.getResources().getDrawable(R.drawable.bg_5);
//            case 5:
//                return mContext.getResources().getDrawable(R.drawable.bg_6);
//            case 6:
//                return mContext.getResources().getDrawable(R.drawable.bg_7);
//            case 7:
//                return mContext.getResources().getDrawable(R.drawable.bg_8);
//            case 8:
//                return mContext.getResources().getDrawable(R.drawable.bg_9);
//            case 9:
//                return mContext.getResources().getDrawable(R.drawable.bg_10);
//            case 10:
//                return mContext.getResources().getDrawable(R.drawable.bg_11);
//            case 11:
//                return mContext.getResources().getDrawable(R.drawable.bg_12);
//            case 12:
//                return mContext.getResources().getDrawable(R.drawable.bg_13);
//            case 13:
//                return mContext.getResources().getDrawable(R.drawable.bg_14);
//            case 14:
//                return mContext.getResources().getDrawable(R.drawable.bg_15);
//            case 15:
//                return mContext.getResources().getDrawable(R.drawable.bg_16);
//            case 16:
//                return mContext.getResources().getDrawable(R.drawable.bg_17);
//            case 17:
//                return mContext.getResources().getDrawable(R.drawable.bg_18);
//            case 18:
//                return  mContext.getResources().getDrawable(R.drawable.bg_19);
//            case 19:
//                return  mContext.getResources().getDrawable(R.drawable.bg_20);
//            case 20:
//                return  mContext.getResources().getDrawable(R.drawable.bg_21);
//            case 21:
//                return  mContext.getResources().getDrawable(R.drawable.bg_22);
//            case 22:
//                return  mContext.getResources().getDrawable(R.drawable.bg_23);
//            case 23:
//                return  mContext.getResources().getDrawable(R.drawable.bg_24);
//            case 24:
//                return  mContext.getResources().getDrawable(R.drawable.bg_25);
//            case 25:
//                return  mContext.getResources().getDrawable(R.drawable.bg_26);
//            case 26:
//                return  mContext.getResources().getDrawable(R.drawable.bg_27);
//            case 27:
//                return  mContext.getResources().getDrawable(R.drawable.bg_28);
//            case 28:
//                return  mContext.getResources().getDrawable(R.drawable.bg_29);
//            case 29:
//                return  mContext.getResources().getDrawable(R.drawable.bg_30);
//            case 30:
//                return  mContext.getResources().getDrawable(R.drawable.bg_31);
//            case 31:
//                return  mContext.getResources().getDrawable(R.drawable.bg_32);
//            case 32:
//                return  mContext.getResources().getDrawable(R.drawable.bg_33);
//            case 33:
//                return  mContext.getResources().getDrawable(R.drawable.bg_34);
//            case 34:
//                return  mContext.getResources().getDrawable(R.drawable.bg_35);
//            case 35:
//                return  mContext.getResources().getDrawable(R.drawable.bg_36);
//            case 36:
//                return  mContext.getResources().getDrawable(R.drawable.bg_37);
//            case 37:
//                return  mContext.getResources().getDrawable(R.drawable.bg_38);
//            case 38:
//                return  mContext.getResources().getDrawable(R.drawable.bg_39);
//            case 39:
//                return  mContext.getResources().getDrawable(R.drawable.bg_40);
//            case 40:
//                return  mContext.getResources().getDrawable(R.drawable.bg_41);
//            case 41:
//                return  mContext.getResources().getDrawable(R.drawable.bg_42);
//            case 42:
//                return  mContext.getResources().getDrawable(R.drawable.bg_43);
//            case 43:
//                return  mContext.getResources().getDrawable(R.drawable.bg_44);
//            case 44:
//                return  mContext.getResources().getDrawable(R.drawable.bg_45);
//            case 45:
//                return  mContext.getResources().getDrawable(R.drawable.bg_46);
//            case 46:
//                return  mContext.getResources().getDrawable(R.drawable.bg_47);
//            case 47:
//                return  mContext.getResources().getDrawable(R.drawable.bg_48);
//            case 48:
//                return  mContext.getResources().getDrawable(R.drawable.bg_49);
//            case 49:
//                return  mContext.getResources().getDrawable(R.drawable.bg_50);
//            case 50:
//                return  mContext.getResources().getDrawable(R.drawable.bg_51);
//            case 51:
//                return  mContext.getResources().getDrawable(R.drawable.bg_52);
//            case 52:
//                return  mContext.getResources().getDrawable(R.drawable.bg_53);
//            case 53:
//                return  mContext.getResources().getDrawable(R.drawable.bg_54);
//            case 54:
//                return  mContext.getResources().getDrawable(R.drawable.bg_55);
//            case 55:
//                return  mContext.getResources().getDrawable(R.drawable.bg_56);
//            case 56:
//                return  mContext.getResources().getDrawable(R.drawable.bg_57);
//            case 57:
//                return  mContext.getResources().getDrawable(R.drawable.bg_58);
//            case 58:
//                return  mContext.getResources().getDrawable(R.drawable.bg_59);
//            case 59:
//                return  mContext.getResources().getDrawable(R.drawable.bg_60);
//            case 60:
//                return  mContext.getResources().getDrawable(R.drawable.bg_61);
//            case 61:
//                return  mContext.getResources().getDrawable(R.drawable.bg_62);
//            case 62:
//                return  mContext.getResources().getDrawable(R.drawable.bg_63);
//            case 63:
//                return  mContext.getResources().getDrawable(R.drawable.bg_64);
//            case 64:
//                return  mContext.getResources().getDrawable(R.drawable.bg_65);
//            case 65:
//                return  mContext.getResources().getDrawable(R.drawable.bg_66);
//            case 66:
//                return  mContext.getResources().getDrawable(R.drawable.bg_67);
//            case 67:
//                return  mContext.getResources().getDrawable(R.drawable.bg_68);
//            case 68:
//                return  mContext.getResources().getDrawable(R.drawable.bg_69);
//            case 69:
//                return  mContext.getResources().getDrawable(R.drawable.bg_70);
//            case 70:
//                return  mContext.getResources().getDrawable(R.drawable.bg_71);
//            case 71:
//                return  mContext.getResources().getDrawable(R.drawable.bg_72);
//            case 72:
//                return  mContext.getResources().getDrawable(R.drawable.bg_73);
//            case 73:
//                return  mContext.getResources().getDrawable(R.drawable.bg_74);
//            case 74:
//                return  mContext.getResources().getDrawable(R.drawable.bg_75);
//            case 75:
//                return  mContext.getResources().getDrawable(R.drawable.bg_76);
//            case 76:
//                return  mContext.getResources().getDrawable(R.drawable.bg_77);
//            case 77:
//                return  mContext.getResources().getDrawable(R.drawable.bg_78);
//            case 78:
//                return  mContext.getResources().getDrawable(R.drawable.bg_79);
//            case 79:
//                return  mContext.getResources().getDrawable(R.drawable.bg_80);
//            case 80:
//                return  mContext.getResources().getDrawable(R.drawable.bg_81);
//            case 81:
//                return  mContext.getResources().getDrawable(R.drawable.bg_82);
//            case 82:
//                return  mContext.getResources().getDrawable(R.drawable.bg_83);
//            case 83:
//                return  mContext.getResources().getDrawable(R.drawable.bg_84);
//            case 84:
//                return  mContext.getResources().getDrawable(R.drawable.bg_85);
//            case 85:
//                return  mContext.getResources().getDrawable(R.drawable.bg_86);
//            case 86:
//                return  mContext.getResources().getDrawable(R.drawable.bg_87);
//            case 87:
//                return  mContext.getResources().getDrawable(R.drawable.bg_88);
//            case 88:
//                return  mContext.getResources().getDrawable(R.drawable.bg_89);
//            case 89:
//                return  mContext.getResources().getDrawable(R.drawable.bg_90);
//            case 90:
//                return  mContext.getResources().getDrawable(R.drawable.bg_91);
//            case 91:
//                return  mContext.getResources().getDrawable(R.drawable.bg_92);
//            case 92:
//                return  mContext.getResources().getDrawable(R.drawable.bg_93);
//            case 93:
//                return  mContext.getResources().getDrawable(R.drawable.bg_94);
//            case 94:
//                return  mContext.getResources().getDrawable(R.drawable.bg_95);
//            case 95:
//                return  mContext.getResources().getDrawable(R.drawable.bg_96);
//            case 96:
//                return  mContext.getResources().getDrawable(R.drawable.bg_97);
//            case 97:
//                return  mContext.getResources().getDrawable(R.drawable.bg_98);
//            case 98:
//                return  mContext.getResources().getDrawable(R.drawable.bg_99);
//            case 99:
//                return  mContext.getResources().getDrawable(R.drawable.bg_100);
//            case 100:
//                return  mContext.getResources().getDrawable(R.drawable.bg_101);
//            case 101:
//                return  mContext.getResources().getDrawable(R.drawable.bg_102);
//            case 102:
//                return  mContext.getResources().getDrawable(R.drawable.bg_103);
//            case 103:
//                return  mContext.getResources().getDrawable(R.drawable.bg_104);
//            case 104:
//                return  mContext.getResources().getDrawable(R.drawable.bg_105);
//            case 105:
//                return  mContext.getResources().getDrawable(R.drawable.bg_106);
//            case 106:
//                return  mContext.getResources().getDrawable(R.drawable.bg_107);
//            case 107:
//                return  mContext.getResources().getDrawable(R.drawable.bg_108);
//            case 108:
//                return  mContext.getResources().getDrawable(R.drawable.bg_109);
//            case 109:
//                return  mContext.getResources().getDrawable(R.drawable.bg_110);
//            case 110:
//                return  mContext.getResources().getDrawable(R.drawable.bg_111);
//            case 111:
//                return  mContext.getResources().getDrawable(R.drawable.bg_112);
//            case 112:
//                return  mContext.getResources().getDrawable(R.drawable.bg_113);
//            case 113:
//                return  mContext.getResources().getDrawable(R.drawable.bg_114);
//            case 114:
//                return  mContext.getResources().getDrawable(R.drawable.bg_115);
//            case 115:
//                return  mContext.getResources().getDrawable(R.drawable.bg_116);
//            case 116:
//                return  mContext.getResources().getDrawable(R.drawable.bg_117);
//            case 117:
//                return  mContext.getResources().getDrawable(R.drawable.bg_118);
//            case 118:
//                return  mContext.getResources().getDrawable(R.drawable.bg_119);
//            case 119:
//                return  mContext.getResources().getDrawable(R.drawable.bg_120);
//            case 120:
//                return  mContext.getResources().getDrawable(R.drawable.bg_121);
//            case 121:
//                return  mContext.getResources().getDrawable(R.drawable.bg_122);
//            case 122:
//                return  mContext.getResources().getDrawable(R.drawable.bg_123);
//            case 123:
//                return  mContext.getResources().getDrawable(R.drawable.bg_124);
//            case 124:
//                return  mContext.getResources().getDrawable(R.drawable.bg_125);
//            case 125:
//                return  mContext.getResources().getDrawable(R.drawable.bg_126);
//            case 126:
//                return  mContext.getResources().getDrawable(R.drawable.bg_127);
//            case 127:
//                return  mContext.getResources().getDrawable(R.drawable.bg_128);
//            case 128:
//                return  mContext.getResources().getDrawable(R.drawable.bg_129);
//            case 129:
//                return  mContext.getResources().getDrawable(R.drawable.bg_130);
//            case 130:
//                return  mContext.getResources().getDrawable(R.drawable.bg_131);
//            case 131:
//                return  mContext.getResources().getDrawable(R.drawable.bg_132);
//            case 132:
//                return  mContext.getResources().getDrawable(R.drawable.bg_133);
//            case 133:
//                return  mContext.getResources().getDrawable(R.drawable.bg_134);
//            case 134:
//                return  mContext.getResources().getDrawable(R.drawable.bg_135);
//            case 135:
//                return  mContext.getResources().getDrawable(R.drawable.bg_136);
//            case 136:
//                return  mContext.getResources().getDrawable(R.drawable.bg_137);
//            case 137:
//                return  mContext.getResources().getDrawable(R.drawable.bg_138);
//            case 138:
//                return  mContext.getResources().getDrawable(R.drawable.bg_139);
//            case 139:
//                return  mContext.getResources().getDrawable(R.drawable.bg_140);
//            default:
//                return null;
//        }
//    }
}

