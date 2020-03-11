package com.himanshu.hungercroton.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesData {
    public static final String TAG = SharedPreferencesData.class.getName();
    public static final String SHARED_PREF_NAME = "PREF";
    private static final int PRIVATE_MODE = 0;
    private static SharedPreferences _pref;
    private static SharedPreferencesData _instance;

    private SharedPreferencesData() {
    }

    public static SharedPreferencesData getInstance(Context context) {
        if (_pref == null) {
            _pref = context
                    .getSharedPreferences(SHARED_PREF_NAME, PRIVATE_MODE);
        }
        if (_instance == null) {
            _instance = new SharedPreferencesData();
        }
        return _instance;
    }

    /**
     * This Method Clear shared preference.
     */
    public void clear() {
        SharedPreferences.Editor editor = _pref.edit();
        editor.clear();
        editor.commit();
    }

    public String getToken(String defaultValue) {
        return getString(Keys.TOKEN.getLabel(), defaultValue);
    }

    public void setToken(String value) {
        setString(Keys.TOKEN.getLabel(), value);
    }

    public String getEmail(String defaultValue) {
        return getString(Keys.EMAILID.getLabel(), defaultValue);
    }

    public void setEmail(String value) {
        setString(Keys.EMAILID.getLabel(), value);
    }

    public String getPhoneNumber(String defaultValue) {
        return getString(Keys.PHONENUMBEFR.getLabel(), defaultValue);
    }

    public void setPhoneNumber(String value) {
        setString(Keys.PHONENUMBEFR.getLabel(), value);
    }

    public String getProperty(String defaultValue) {
        return getString(Keys.PROPERTY.getLabel(), defaultValue);
    }

    public void setProperty(String value) {
        setString(Keys.PROPERTY.getLabel(), value);
    }

    public void setLogin(String value) {
        setString(Keys.LOGIN.getLabel(), value);
    }

    public String getLogin(String defaultValue) {
        return getString(Keys.LOGIN.getLabel(), defaultValue);
    }

    public void setUserID(String value) {
        setString(Keys.USER_ID.getLabel(), value);
    }

    public String getUserId(String defaultValue) {
        return getString(Keys.USER_ID.getLabel(), defaultValue);
    }

    public void setImei(String value) {
        setString(Keys.IMEI.getLabel(), value);
    }

    public String getImei(String defaultValue) {
        return getString(Keys.IMEI.getLabel(), defaultValue);
    }

    public void setPasswor(String value) {
        setString(Keys.PASSWORD.getLabel(), value);
    }

    public String getPasswor(String defaultValue) {
        return getString(Keys.PASSWORD.getLabel(), defaultValue);
    }

    public void setUserName(String value) {
        setString(Keys.USERNAME.getLabel(), value);
    }

    public String getUserName(String defaultValue) {
        return getString(Keys.USERNAME.getLabel(), defaultValue);
    }

    public void setFirstName(String value) {
        setString(Keys.USERFNAME.getLabel(), value);
    }

    public String getFirstName(String defaultValue) {
        return getString(Keys.USERFNAME.getLabel(), defaultValue);
    }

    public void setCompamyName(String value) {
        setString(Keys.USERCNAME.getLabel(), value);
    }

    public String getCompamyName(String defaultValue) {
        return getString(Keys.USERCNAME.getLabel(), defaultValue);
    }

    private void setString(String key, String value) {
        if (key != null && value != null) {
            try {
                if (_pref != null) {
                    SharedPreferences.Editor editor = _pref.edit();
                    editor.putString(key, value);
                    editor.commit();
                }
            } catch (Exception e) {
                Log.e(TAG, "Unable to set " + key + "= " + value
                        + "in shared preference", e);
            }
        }
    }

    private void setInt(String key, int value) {
        if (key != null) {
            try {
                if (_pref != null) {
                    SharedPreferences.Editor editor = _pref.edit();
                    editor.putInt(key, value);
                    editor.commit();
                }
            } catch (Exception e) {
                Log.e(TAG, "Unable to set " + key + "= " + value
                        + "in shared preference", e);
            }
        }
    }

    private void setLong(String key, Long value) {
        if (key != null) {
            try {
                if (_pref != null) {
                    SharedPreferences.Editor editor = _pref.edit();
                    editor.putLong(key, value);
                    editor.commit();
                }
            } catch (Exception e) {
                Log.e(TAG, "Unable to set " + key + "= " + value
                        + "in shared preference", e);
            }
        }
    }

    private String getString(String key, String defaultValue) {
        if (_pref != null && key != null && _pref.contains(key)) {
            return _pref.getString(key, defaultValue);
        }
        return defaultValue;
    }

    private int getInt(String key, int defaultValue) {
        if (_pref != null && key != null && _pref.contains(key)) {
            return _pref.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    private long getLong(String key, long defaultValue) {
        if (_pref != null && key != null && _pref.contains(key)) {
            return _pref.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    private void setBoolean(String key, boolean value) {
        if (key != null) {
            try {
                if (_pref != null) {
                    SharedPreferences.Editor editor = _pref.edit();
                    editor.putBoolean(key, value);
                    editor.commit();
                }
            } catch (Exception e) {
                Log.e(TAG, "Unable to set " + key + "= " + value
                        + "in shared preference", e);
            }
        }
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        if (_pref != null && key != null && _pref.contains(key)) {
            return _pref.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    private enum Keys {
        STATUS("status"), TOKEN("token"),
        EMAILID("emailID"),PHONENUMBEFR("number_p"),PROPERTY("PROPERTY"), LOGIN("LOGIN"), IMEI("imei"), USERNAME("user_name"), PASSWORD("password"), USERFNAME("f_name"), USERCNAME("company"),USER_ID("user_id");

        private String label;

        Keys(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}