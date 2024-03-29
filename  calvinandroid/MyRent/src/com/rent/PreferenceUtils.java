package com.rent;

import android.content.Context;
import android.content.SharedPreferences;

import com.rent.data.DoublePoint;
import com.rent.db.AddressChoiceDBManager;

public class PreferenceUtils {
	public static final String CLEAR_PIC_SETTING = "clear_pic_setting";
	  private static final String HISTORY_KEYWORD = "keyword";
	  private static final String HISTORY_RECORD = "histroy_record";
	  private static final String HISTORY_SEPARATE = "#<#";
	  public static final String INIT = "init";
	  public static final String LOCATION_POINT = "location_point";
	  public static final String LOCATION_SETTING = "location_point";
	  private static final String MARS_LOCATION_LAT = "mars_location_lat";
	  private static final String MARS_LOCATION_LON = "mars_location_lon";
	  public static final String REFRESH_STATUS = "refresh_status";
	  public static final int REFRESH_STATUS_IDLE = 2;
	  public static final int REFRESH_STATUS_LOCATION_OR_FILTER_CHANGED = 1;
	  public static final int REFRESH_STATUS_LOCATION_SET_AND_CURRENT = 5;
	  public static final int REFRESH_STATUS_LOCATION_SET_BUT_NOT_CURRENT = 4;
	  public static final int REFRESH_STATUS_RELOCATE = 0;
	  public static final int REFRESH_STATUS_SEARCH = 3;
	  public static final String RENT_SP_SETTING = "rent_sp_setting";
	  public static final String SHOW_KEYWORD = "show_keyword";
	  public static final String VALUE_CURRENT_BUSSINESS = "current_bussiness";
	  public static final String VALUE_CURRENT_CITY = "current_city";
	  public static final String VALUE_CURRENT_DISTRICT = "current_district";
	  public static final String VALUE_CURRENT_DISTRICT_ID = "current_district_id";
	  public static final String VALUE_CURRENT_TEMP_WORD = "current_temp_word";
	  public static final String VALUE_KEY_WORD = "current_key_word";
	  
	  public static HouseFilter getCurrentHouseFilter(Context paramContext)
	  {
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("filterSearchView", 0);
	    HouseFilter localHouseFilter = new HouseFilter();
	    int i = localSharedPreferences.getInt("price_upper", -1);
	    localHouseFilter.setmPriceHight(i);
	    int j = localSharedPreferences.getInt("price_low", -1);
	    localHouseFilter.setmPriceLow(j);
	    int k = localSharedPreferences.getInt("room_number", -1);
	    localHouseFilter.setmRoomNumber(k);
	    int m = localSharedPreferences.getInt("distance_length", -1);
	    localHouseFilter.setmDistanceLength(m);
	    boolean bool1 = localSharedPreferences.getBoolean("is_agency", false);
	    localHouseFilter.setmIsAgency(bool1);
	    boolean bool2 = localSharedPreferences.getBoolean("is_personal", false);
	    localHouseFilter.setmIsPersonal(bool2);
	    boolean bool3 = localSharedPreferences.getBoolean("rent_all", false);
	    localHouseFilter.setmIsRentAll(bool3);
	    boolean bool4 = localSharedPreferences.getBoolean("rent_part", false);
	    localHouseFilter.setmIsRentPart(bool4);
	    return localHouseFilter;
	  }
	  
	  public static void saveCityName(Context paramContext, String paramString)
	  {
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("location_point", 0);
	    String str1 = paramContext.getString(R.string.defalut_city);
	    String str2 = localSharedPreferences.getString("current_city", str1);
	    SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
	    SharedPreferences.Editor localEditor2 = localEditor1.putString("current_city", paramString);
	    boolean bool = localEditor1.commit();
	    if ((!str2.equals(paramString)) || (getCurrentDistrictName(paramContext) == null) || (getCurrentDistrictId(paramContext) == -1))
	    {
	      AddressChoiceDBManager localAddressChoiceDBManager = new AddressChoiceDBManager(paramContext);
	      localAddressChoiceDBManager.openDatabase();
	      String str3 = getCurrentCityName(paramContext);
	      String str4 = localAddressChoiceDBManager.getCityFirstDistrictName(str3);
	      int i = localAddressChoiceDBManager.getCityFirstDistrictId(str3, str4);
	      localAddressChoiceDBManager.closeDatabase();
	      saveDistrictName(paramContext, str4, i);
	    }
	  }
	  
	  public static DoublePoint getMarsLocation(Context paramContext)
	  {
	    DoublePoint localDoublePoint = new DoublePoint();
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("location_point", 0);
	    double d1 = Double.parseDouble(localSharedPreferences.getString("mars_location_lat", "39.920591"));
	    double d2 = Double.parseDouble(localSharedPreferences.getString("mars_location_lon", "116.432791"));
	    localDoublePoint.mLat = d1;
	    localDoublePoint.mLon = d2;
	    return localDoublePoint;
	  }
	  
	  public static int getCurrentDistrictId(Context paramContext)
	  {
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("location_point", 0);
	    AddressChoiceDBManager localAddressChoiceDBManager = new AddressChoiceDBManager(paramContext);
	    localAddressChoiceDBManager.openDatabase();
	    String str1 = getCurrentCityName(paramContext);
	    String str2 = localAddressChoiceDBManager.getCityFirstDistrictName(str1);
	    int i = localAddressChoiceDBManager.getCityFirstDistrictId(str1, str2);
	    localAddressChoiceDBManager.closeDatabase();
	    return localSharedPreferences.getInt("current_district_id", i);
	  }
	  
	  public static String getCurrentDistrictName(Context paramContext)
	  {
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("location_point", 0);
	    AddressChoiceDBManager localAddressChoiceDBManager = new AddressChoiceDBManager(paramContext);
	    localAddressChoiceDBManager.openDatabase();
	    String str1 = getCurrentCityName(paramContext);
	    String str2 = localAddressChoiceDBManager.getCityFirstDistrictName(str1);
	    localAddressChoiceDBManager.closeDatabase();
	    return localSharedPreferences.getString("current_district", str2);
	  }
	  
	  public static boolean getClearPicStatus(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("rent_sp_setting", 0).getBoolean("clear_pic_setting", false);
	  }

	  public static String getCurrentBussiness(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("location_point", 0).getString("current_bussiness", "");
	  }

	  public static String getCurrentCityName(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("location_point", 0).getString("current_city", "");
	  }

	  public static String getCurrentKeyWord(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("location_point", 0).getString("current_key_word", "");
	  }

	  public static String getCurrentTempKeyWord(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("location_point", 0).getString("current_temp_word", "");
	  }

	  public static String[] getHistoryRecord(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("histroy_record", 0).getString("keyword", "").split("#<#");
	  }

	  public static int getRefreshStatus(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("location_point", 0).getInt("refresh_status", 0);
	  }

	  public static boolean getShowKeyword(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("location_point", 0).getBoolean("show_keyword", false);
	  }

	  public static final boolean isShowZoom(Context paramContext)
	  {
	    return paramContext.getSharedPreferences("rent_setting", 0).getBoolean("show_map_zoom_button", true);
	  }

	  public static void saveBussiness(Context paramContext, String paramString)
	  {
	    SharedPreferences.Editor localEditor1 = paramContext.getSharedPreferences("location_point", 0).edit();
	    SharedPreferences.Editor localEditor2 = localEditor1.putString("current_bussiness", paramString);
	    boolean bool = localEditor1.commit();
	  }

	  public static void saveClearPicStatus(Context paramContext, boolean paramBoolean)
	  {
	    boolean bool = paramContext.getSharedPreferences("rent_sp_setting", 0).edit().putBoolean("clear_pic_setting", paramBoolean).commit();
	  }

	  public static void saveDistrictName(Context paramContext, String paramString, int paramInt)
	  {
	    SharedPreferences.Editor localEditor1 = paramContext.getSharedPreferences("location_point", 0).edit();
	    SharedPreferences.Editor localEditor2 = localEditor1.putString("current_district", paramString);
	    SharedPreferences.Editor localEditor3 = localEditor1.putInt("current_district_id", paramInt);
	    boolean bool = localEditor1.commit();
	    saveBussiness(paramContext, "");
	  }

	  public static void saveHistoryRecord(Context paramContext, String paramString1, String paramString2)
	  {
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("histroy_record", 0);
	    String[] arrayOfString = localSharedPreferences.getString("keyword", "").split("#<#");
	    StringBuilder localStringBuilder1 = new StringBuilder();
	    String str1 = paramString2 + "#city#" + paramString1 + "#<#";
	    StringBuilder localStringBuilder2 = localStringBuilder1.append(str1);
	    int i = arrayOfString.length;
	    int j = Math.min(9, i);
	    int k = 0;
	    while (k < j)
	    {
	      String str2 = arrayOfString[k];
	      String str3 = paramString2 + "#city#" + paramString1;
	      if (!str2.equals(str3))
	      {
	        StringBuilder localStringBuilder3 = new StringBuilder();
	        String str4 = arrayOfString[k];
	        String str5 = str4 + "#<#";
	        StringBuilder localStringBuilder4 = localStringBuilder1.append(str5);
	      }
	      k += 1;
	    }
	    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
	    String str6 = localStringBuilder1.toString();
	    boolean bool = localEditor.putString("keyword", str6).commit();
	  }

	  public static void saveKeyWord(Context paramContext, String paramString)
	  {
	    SharedPreferences.Editor localEditor1 = paramContext.getSharedPreferences("location_point", 0).edit();
	    SharedPreferences.Editor localEditor2 = localEditor1.putString("current_key_word", paramString);
	    boolean bool = localEditor1.commit();
	  }

	  public static void saveMarsLocation(Context paramContext, double paramDouble1, double paramDouble2)
	  {
	    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("location_point", 0);
	    SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
	    String str1 = paramDouble2 + "";
	    boolean bool1 = localEditor1.putString("mars_location_lat", str1).commit();
	    SharedPreferences.Editor localEditor2 = localSharedPreferences.edit();
	    String str2 = paramDouble1 + "";
	    boolean bool2 = localEditor2.putString("mars_location_lon", str2).commit();
	  }

	  public static void saveTempKeyWord(Context paramContext, String paramString)
	  {
	    SharedPreferences.Editor localEditor1 = paramContext.getSharedPreferences("location_point", 0).edit();
	    SharedPreferences.Editor localEditor2 = localEditor1.putString("current_temp_word", paramString);
	    boolean bool = localEditor1.commit();
	  }

	  public static void setDistanceLength(Context paramContext, int paramInt)
	  {
	    boolean bool = paramContext.getSharedPreferences("filterSearchView", 0).edit().putInt("distance_length", paramInt).commit();
	  }

	  public static void setInit(Context paramContext)
	  {
	    boolean bool = paramContext.getSharedPreferences("location_point", 0).edit().putBoolean("init", true).commit();
	  }

	  public static void setRefreshStatus(Context paramContext, int paramInt)
	  {
	    boolean bool = paramContext.getSharedPreferences("location_point", 0).edit().putInt("refresh_status", paramInt).commit();
	  }

	  public static void setShowKeyword(Context paramContext, boolean paramBoolean)
	  {
	    boolean bool = paramContext.getSharedPreferences("location_point", 0).edit().putBoolean("show_keyword", paramBoolean).commit();
	  }

	  public static void setShowZoom(Context paramContext, boolean paramBoolean)
	  {
	    boolean bool = paramContext.getSharedPreferences("rent_setting", 0).edit().putBoolean("show_map_zoom_button", paramBoolean).commit();
	  }
}
