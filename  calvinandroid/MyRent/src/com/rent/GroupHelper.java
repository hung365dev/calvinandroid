package com.rent;

import com.rent.activitiy.MainActivity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class GroupHelper {

	public static Activity getActivityById(ActivityGroup paramActivityGroup,
			int paramInt) {
		LocalActivityManager localLocalActivityManager = paramActivityGroup
				.getLocalActivityManager();
		String str = "subActivity" + paramInt;
		return localLocalActivityManager.getActivity(str);
	}

	public void init(ActivityGroup paramActivityGroup,
			LinearLayout paramLinearLayout, GroupTabHost paramGroupTabHost,
			GroupStub[] paramArrayOfGroupStub, TabListener paramTabListener) {

		for (int i = 0; i < paramArrayOfGroupStub.length; i++) {
			String str = paramArrayOfGroupStub[i].getText();
			int k = paramArrayOfGroupStub[i].getActiveIconId();
			int m = paramArrayOfGroupStub[i].getInactiveIconId();
			GroupTab localGroupTab = new GroupTab(str, k, m);
			paramGroupTabHost.addTab(localGroupTab);
		}
		paramGroupTabHost.setListener(new TabClickListener(paramActivityGroup, paramLinearLayout,
				paramArrayOfGroupStub));
	}

	final class TabClickListener implements GroupListener {
		private ActivityGroup paramActivityGroup;
		private LinearLayout paramLinearLayout;
		private GroupStub[] subGroupStub;

		public TabClickListener(ActivityGroup paramActivityGroup,
				LinearLayout paramLinearLayout, GroupStub[] paramArrayOfGroupStub) {
			this.paramActivityGroup = paramActivityGroup;
			this.paramLinearLayout = paramLinearLayout;
			this.subGroupStub = paramArrayOfGroupStub;
		}

		public void sendVisableId(int paramInt) {
			paramLinearLayout.removeAllViews();
			Class localClass = subGroupStub[paramInt].getActivityClass();
			Intent localIntent1 = new Intent(paramActivityGroup, localClass);// 131072
			Intent localIntent2 = localIntent1
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			LocalActivityManager localLocalActivityManager = paramActivityGroup
					.getLocalActivityManager();
			String str = "subActivity" + paramInt;
			Window localWindow = localLocalActivityManager.startActivity(str,
					localIntent1);
			View localView = localWindow.getDecorView();
			paramLinearLayout.addView(localView, -1, -1);
			((MainActivity)paramActivityGroup).setCurrentTabId(paramInt);
		}
	}

	public abstract interface TabListener {
		public abstract void setCurrentTabId(int paramInt);
	}
}
