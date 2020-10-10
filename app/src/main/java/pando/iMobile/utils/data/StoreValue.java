package pando.iMobile.utils.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;


import pando.iMobile.utils.EA;
import pando.iMobile.utils.l;


public class StoreValue {


    public static String getVal(Context context, String key ,String defulat_if_not_exist)
    {
        if (!checkKeyExist(context , key)){
            editVal(context , key , defulat_if_not_exist);
        }
        try {

            String _key = EA.encrypt((Build.DEVICE+"Uekng3YxialIE42KjNtOaPoak5enAkeE").substring( 0 , 16 ) ,key);
            String _defulat_if_not_exist = EA.encrypt((Build.DEVICE+"Uekng3YxialIE42KjNtOaPoak5enAkeE").substring( 0 , 16 ) ,defulat_if_not_exist);
            String res = EA.decrypt((Build.DEVICE+"Uekng3YxialIE42KjNtOaPoak5enAkeE").substring( 0 , 16 ) ,  SharedValue.getVal(context,_key , _defulat_if_not_exist));
            return res;

        }catch (Exception e){
            l.l(e.getMessage());
        }
        return "";
    }
    public static void editVal(Context context, String key ,String newVal)
    {
        try {

            String _key = EA.encrypt((Build.DEVICE+"Uekng3YxialIE42KjNtOaPoak5enAkeE").substring( 0 , 16 ) ,key);
            String _newValue = EA.encrypt((Build.DEVICE+"Uekng3YxialIE42KjNtOaPoak5enAkeE").substring( 0 , 16 ) ,newVal);
            SharedValue.editVal(context,_key , _newValue);

        }catch (Exception e){
            l.l(e.getMessage());
        }

    }




//    private static final String key = "Uekng3YxialIE42KjNtOaPoak5enAkeE";
//    private static final String main_key = (Build.DEVICE+key).substring( 0 , 16 );
//    static String result = "";
//    static float fontsize = 15f;
//    static boolean exist = false;
//
//
//
//    public static String getVal(final Context context ,final String table ,final String key)
//    {
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                    try {
//                        String _key = EA.encrypt(main_key , key);
//
//                        String _value = EA.decrypt(main_key ,context.getSharedPreferences(table, Context.MODE_PRIVATE)
//                                .getString(_key , ""));
//                        result = _value != null ? _value : "";
//                    }catch (Exception e){
//                     l.l(e.getMessage());
//                    }
//
//            }
//        });
//
//
//        return result;
//
//    }
//    public static void editVal(final Context context ,final String table ,final String key ,final String value)
//    {
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//
//                try {
//                    String _value = EA.encrypt(main_key , value);
//                    String _key = EA.encrypt(main_key , key);
//                    l.l(EA.decrypt(main_key, _value));
//                    l.l(EA.decrypt(main_key, _key));
//                    l.l("my:"+EA.decrypt(main_key, "+0HsFovIut+1CcKr6MPmTA=="));
//                    l.l("my:"+EA.decrypt(main_key, "DILR2H3x+bhH9fjhcMqQKQ=="));
//                    SharedPreferences pref= context.getSharedPreferences(table, Context.MODE_PRIVATE);
//
////                    if(pref.edit().putString(_key , _value).commit()){
////                        l.l("Success Saved Prefrences."+key);
////                    }else{
////                        l.l("Not Save Prefrences."+key);
////                    }
//                    pref.edit().putString(_key , _value).apply();
//                }catch (Exception e){
//                    l.l(e.getMessage());
//                }
//
//            }
//        });
//
//
//
//    }
//    public static void editFontSize(final Context context ,final float value)
//    {
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                try {
//                    String _key = EA.encrypt(main_key , Settings.fontsize);
//                    String _value = EA.encrypt(main_key , String.valueOf(value));
//                    SharedPreferences pref= context.getSharedPreferences(val.settings, Context.MODE_PRIVATE);
//
////                    if(pref.edit().putString(_key , _value).commit()){
////                        l.l("Success Saved Prefrences."+Settings.fontsize );
////                    }else{
////                        l.l("Not Save Prefrences."+Settings.fontsize );
////                    }
//                    pref.edit().putString(_key  , _value).apply();
//                }catch (Exception e){
//                    l.l(e.getMessage());
//                }
//            }
//        });
//
//
//
//    }
//    public static float getValFontSize(final Context context)
//    {
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                    try {
//                        String _key = EA.encrypt(main_key , Settings.fontsize);
//                        String _value = EA.decrypt(main_key , context.getSharedPreferences(val.settings, Context.MODE_PRIVATE)
//                                .getString(_key, "15"));
//
//                        fontsize = Float.valueOf(_value);
//                    }catch (Exception e){
//                        l.l(e.getMessage());
//                    }
//
//            }
//        });
//
//        return fontsize;
//
//    }
//
    public static boolean checkKeyExist( Context context , String key)
    {
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                try {
//                    String _key = EA.encrypt(main_key , key);
//                    exist = context.getSharedPreferences(table , Context.MODE_PRIVATE).contains(_key);
//                }catch (Exception e){
//                    l.l(e.getMessage());
//                }
//            }
//        });
        try {
            String _key = EA.encrypt((Build.DEVICE+"Uekng3YxialIE42KjNtOaPoak5enAkeE").substring( 0 , 16 ) , key);
            return SharedValue.CheckExistKey(context , _key);
        }catch (Exception e){
            return false;
        }
    }


    public static final class Gym{
        public static String manager = "manager";
        public static String user_type = "user_type";//0 = nothing | 1 = normal user | 2 = manager user
    }
    public static final class YourDetails{
        public static String name = "name";
        public static String family = "family";
        public static String phone = "phone";
    }

    public static final class Settings{
        public static String city1 = "city1";
        public static String city2 = "city2";
        public static String fontsize = "fontsize";
        public static String fonttype = "fonttype";
        public static String logo = "logo";
        public static String phondex_loaded = "phondex_loaded";


    }


    public static final class YourGym{
        public static String id = "id";
        public static String email = "email";
        public static String pass = "pass";
        public static String name = "name";
        public static String address = "address";
        public static String about = "about";
        public static String img = "img";
        public static String city = "city";
        public static String lat = "lat";
        public static String lng = "lng";
        public static String token = "token";
        public static String open_close = "open_close";
    }
    public static final class User{
        public static String name = "name";
        public static String family = "family";
        public static String email = "email";
        public static String pass = "pass";
        public static String city = "city";
        public static String fav_shop = "fav_shop";
        public static String last_user_location = "last_user_location";//lat:lng:zoom
    }


    public static final String your_shop = "your_shop";
    public static final String settings = "settings";
    public static final String user = "user";
    public static final String shop = "shop";


    public static class SharedValue {

        public static final String NameShared = "android_native_library";

        public static String getVal(Context context ,String key ,String default_value)
        {
            SharedPreferences preferences;
            preferences = context.getSharedPreferences(NameShared , Context.MODE_PRIVATE);
            return preferences.getString(key  , default_value);
        }
        public static void editVal(Context context,String key ,String newVal)
        {
            SharedPreferences preferences;
            preferences = context.getSharedPreferences(NameShared , Context.MODE_PRIVATE);
            preferences.edit().putString(key , newVal).apply();
        }
        public void editFont(Context context,String key ,float font)
        {
            SharedPreferences preferences;
            preferences = context.getSharedPreferences(NameShared , Context.MODE_PRIVATE);
            preferences.edit().putFloat(key , font).apply();
        }

        //check value is okset
        public static boolean CheckExistKey(Context context,String key)
        {

            if (context.getSharedPreferences(NameShared , Context.MODE_PRIVATE).contains(key))
            {
                return true;

            }else
            {
                return false;
            }

        }



    }


}
