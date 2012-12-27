package simple.a;

import java.util.HashMap;

import android.content.Context;

import com.flurry.android.FlurryAgent;

public class Flurry {
	
	// API Key for flurry
	// dev key:     Z1YEMCFYXRQ8EP7Y84A1
	// release key: XUK7ZSI3ERAWVVNXSG2E
    public static final String FLURRY_API_KEY = "W9ELZHJ4FA861KCZDS1K";
	
	public static final String FLURRY_ONCLICK_ACTIONNAME_KEY = "name";
	
	/**
	 * This is a convenient wrapper for Flurry.doFlurry(DoFlurryType,Context,HashMap)
	 * 
	 * @param context			pass in the activity, cannot be null!!
	 * @param actionName		the name for this onClick action, cannot be null!!!
	 */
	public static void doFlurry_onClickEvent(Context context,String actionName){
		Flurry.doFlurry_onClickEvent(context, actionName, null);
	}
	
	/**
	 * This is a convenient wrapper for Flurry.doFlurry(DoFlurryType,Context,HashMap)
	 * You can add additional information, if no additional data is needed,
	 * please use <code>doFlurry_onClickEvent(Context context,String actionName)</code>
	 * instead!
	 * 
	 * @param context			pass in the activity, cannot be null!!
	 * @param actionName		the name for this onClick action, cannot be null!!!
	 * @param additionalData	additional data
	 */
	public static void doFlurry_onClickEvent(Context context ,String actionName ,HashMap<String, String> additionalData){
		// create parameter hashmap
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(FLURRY_ONCLICK_ACTIONNAME_KEY, actionName);
		if(additionalData != null){
			params.putAll(additionalData);
		}
		Flurry.doFlurry(DoFlurryType.FLURRY_TYPE_ON_CLICK, context, params);
	}

	public static void doFlurry(DoFlurryType type, Context context,
			HashMap<String, String> additionalData) {
		// create parameter hashmap
		HashMap<String, String> params = new HashMap<String, String>();
		// add context info
		params.put("Context", context.getClass().getSimpleName());
		// add additional data into it
		if (additionalData != null) {
			params.putAll(additionalData);
		}
		
		// decide event id
		StringBuilder EventID = new StringBuilder(type.eventID);
		switch (type) {
		// activity level
		case FLURRY_TYPE_ON_START_ACTIVITY:
		case FLURRY_TYPE_ON_CREATE_OPTION_MENU:
			EventID.append(context.getClass().getSimpleName());
			break;

		// action level
		case FLURRY_TYPE_ON_CLICK:
			EventID.append(context.getClass().getSimpleName()).append(":");
			if(additionalData.containsKey(FLURRY_ONCLICK_ACTIONNAME_KEY)){
				EventID.append(additionalData.get(FLURRY_ONCLICK_ACTIONNAME_KEY));
			}
			break;
		default:
			return;
		}

		FlurryAgent.onEvent(EventID.toString(), params);
	}

	public enum DoFlurryType {
		// activity level
		FLURRY_TYPE_ON_START_ACTIVITY("Activity started:"), 
		FLURRY_TYPE_ON_CREATE_OPTION_MENU("Create options menu:"),

		// operation level
		FLURRY_TYPE_ON_CLICK("On Click:");

		protected final String eventID;

		private DoFlurryType(String _id) {
			eventID = _id;
		}
	}



}
