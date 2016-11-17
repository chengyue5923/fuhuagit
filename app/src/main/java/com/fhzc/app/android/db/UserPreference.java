
package com.fhzc.app.android.db;

import com.base.framwork.cach.preferce.PreferceManager;
import com.fhzc.app.android.models.LoginModel;
import com.fhzc.app.android.models.UserModel;

public final class UserPreference {
    private static final String PREF_KEY_UID = "uid";
    private static final String PREF_KEY_SEX = "sex";
    private static final String PREF_KEY_LEVEL = "level";
    private static final String PREF_KEY_AVATAR = "avatar";
    private static final String PREF_KEY_NAME = "name";
    private static final String PREF_KEY_ROLE = "loginRole";
    private static final String PREF_KEY_PHONE = "phone";
    private static final String PREF_KEY_LOGINNAME = "loginName";
    private static final String PREF_KEY_CERTIFICATES = "CertificatesNum";
    private static final String PREF_KEY_PASSPORT = "passportCode";
    private static final String PREF_KEY_PLANNERID = "plannerId";
    private static final String PREF_KEY_PLANNERUID = "plannerUid";
    private static final String PREF_KEY_PLANNERNAME = "plannerName";

    private static final String PREF_KEY_PLANNERPOSITION = "plannerPosition";
    private static final String PREF_KEY_PLANNERDEPARTMENT = "plannerdepartment";
    private static final String PREF_KEY_CB_ID = "cb_id";
    private static final String PREF_KEY_LASTLOGIN = "lastLogin";
    private static final String PREF_KEY_ROLEID = "roleId";

    public static void setPositon(String position) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PLANNERPOSITION, String.valueOf(position));
    }

    public static String getPosition() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PLANNERPOSITION);
    }public static void setDepartment(String department) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PLANNERDEPARTMENT, String.valueOf(department));
    }
    public static String getDepartment() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PLANNERDEPARTMENT);
    }




    public static void setCBid(String cbid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_CB_ID, String.valueOf(cbid));
    }

    public static String getCBUid() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_CB_ID);
    }

    public static void setUid(int uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_UID, String.valueOf(uid));
    }

    public static int getUid() {
        return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_UID));
    }

    public static void setRoleId(int uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_ROLEID, String.valueOf(uid));
    }

    public static int getRoleId() {
        try {
            return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_ROLEID));
        } catch (Exception e) {
            return 0;
        }
    }

    public static void setPlannerId(int plannerId) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PLANNERID, String.valueOf(plannerId));
    }

    public static void setPlannerUid(int plannerId) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PLANNERUID, String.valueOf(plannerId));
    }

    public static int getPlannerId() {
        try {
            return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_PLANNERID));
        } catch (Exception e) {
            return 0;
        }


    }

    public static int getPlannerUid() {
        try {
            return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_PLANNERUID));
        } catch (Exception e) {
            return 0;
        }


    }

    public static void setSex(String sex) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_SEX, sex);
    }

    public static String getPassport() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PASSPORT);
    }

    public static void setPassport(String passport) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PASSPORT, passport);
    }

    public static String getSex() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_SEX);
    }

    public static void setRole(String role) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_ROLE, role);
    }

    public static String getROLE() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_ROLE);
    }

    public static void setphone(String phone) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PHONE, phone);
    }

    public static String getPhone() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PHONE);
    }

    public static void setCertificates(String certificates) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_CERTIFICATES, certificates);
    }

    public static String getCertificates() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_CERTIFICATES);
    }

    public static void setLoginName(String loginName) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_LOGINNAME, loginName);
    }

    public static String getLoginName() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_LOGINNAME);
    }

    public static void setPlannerName(String plannerName) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PLANNERNAME, plannerName);
    }

    public static String getPlannerName() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PLANNERNAME);
    }

    public static void setName(String name) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_NAME, name);
    }

    public static String getName() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_NAME);
    }

    public static void setAvatar(String avatar) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_AVATAR, avatar);
    }

    public static String getAvatar() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_AVATAR);
    }

    public static void setLevel(String level) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_LEVEL, level);
    }

    public static String getLevel() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_LEVEL);
    }

    public static void setUser(UserModel model) {
        setSex(model.getGender());
        setUid(model.getUid());
        setLevel(model.getLevel());
        setName(model.getRealname());
        setAvatar(model.getAvatar());
        setRole(model.getLoginRole());
        setphone(model.getMobile());
        setLoginName(model.getLogin());
        setPassport(model.getPassportCode());
        setCBid(model.getCb_id());
        setPositon(model.getPosition());
        setDepartment(model.getDepartment());
        try {
            setPlannerId(model.getPlanner().get(0).getPlannerId());
            setPlannerName(model.getPlanner().get(0).getPlannerName());
            setPlannerUid(model.getPlanner().get(0).getUid());
        } catch (Exception e) {
        }


    }

    public static void setLoginUser(LoginModel model) {
        setUid(model.getUid());
        setName(model.getRealname());
        setRole(model.getRole());
        if (isCustomer()) {
            setRoleId(model.getCustomerId());
        } else {
            setRoleId(model.getPlannerId());
        }

    }

    public static void cleanUser() {
        setSex("");
        setUid(0);
        setLevel("");
        setName("");
        setAvatar("");
        setRole("");
        setLoginName("");
        setCertificates("");
        setphone("");
        setPassport("");
        setPlannerId(0);
        setPlannerName("");
        setPlannerUid(0);
    }

    public static boolean isCustomer() {
        return getROLE() != null && getROLE().equals("customer");
    }

    public static void saveLastLogin(String lastLogin) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_LASTLOGIN, lastLogin);
    }

    public static String getLastLogin() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_LASTLOGIN);
    }


}
