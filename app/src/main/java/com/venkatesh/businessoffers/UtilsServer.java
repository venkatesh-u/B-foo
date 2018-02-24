package com.venkatesh.businessoffers;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UtilsServer {


public static final String URL =
//        "http://172.16.19.52:3000/";

//        url;
 "https://qa-api.eteki.com/";

//  public static final String URL =
// "https://demo-api.eteki.com";
    // "http://192.168.199.113:3000/";

//	public static final String URL="https://api.eteki.com/";
    public static final String URL_API = URL;
    public static final String USER_REGISTRATION = "users/sign_up";
    public static final String USER_OAUTH_LOGIN = "users/oauth_login?";
    public static final String USER_LOGIN = "users/sign_in";
    public static final String USER_LOGOUT = "users/sign_out?";
    public static final String USER_CLEAR_ALL_SESSIONS = "users/clear_other_sessions";
    public static final String VERIFY_OTP = "/sms/verify_otp";
    public static final String RESEND_OTP = "/sms/resend_otp";

    public static final String INT_JOBSEARCH = "search_jobs";
    public static final String INT_TIME_SLOTS = "interviewers/time_slots";
    public static final String INT_PICK_SCHEDULE = "interviewers/pick_the_interview_schdule";
    public static final String INT_MY_CAL = "interviewers/get_calendar";

    //public static final String INT_INTERVIEWR="interviewer/interviews?";
    public static final String CAN_INTERVIEWR = "candidates/my_interviews?";
    public static final String CAN_MYPROFILES = "candidate/profiles?";
    public static final String CAN_TIME_SLOTS = "candidates/time_slots";
    public static final String CAN_ADD_SLOT = "candidates/create_available_slots";
    public static final String CAN_SAVE_AND_PROCEED = "candidates/sumbit_the_time_slots";
    public static final String CAN_PUBLIC_PROFILE = "recruiters/get_candidate_details";
    public static final String CANDIDATE_DETAILS = "candidate_details";
    public static final String CANDIDATE_SCHEDULED_INERVIEWS = "candidates/scheduled_interviews";
    public static final String COMPANY_AVAILABILITY = "company/company_availability";

    //public static final String INT_JOBS="interviewer/jobs?";
    public static final String INT_NOTIFICATIONS = "my_notifications";
    public static final String INT_START_INTERVIEW = "start_interview";
    public static final String INT_SCHEDULE = "interviews/valid_passcode";
    public static final String INT_START_AUDIO = "audio/call?";

    public static final String INT_SUBMIT_PROP = "jobs/make_proposal?";
    public static final String INT_SUBMIT_FB = "interviews/submit_report";
    public static final String INT_DIRECT_SUBMIT_FB = "submit_report";
    //public static final String INT_SUBMIT_DIRECT_FB="/interview/finish_and_feedback?";

    public static final String INT_ACCEPT_JOB_REQUEST = "accept_job_request?";
    public static final String INT_REJECT_JOB_REQUEST = "reject_job_request?";
    public static final String INT_ACCEPT = "accept_company_request?";
    public static final String INT_REJECT = "reject_company_request?";
    public static final String INT_REMIND = "remind_job_proposal";
    public static final String INT_REMIND_JOB_REQUEST = "remind_job_request";

    public static final String INT_DASHBOARD = "interviewers/my_interviews";
    public static final String INT_QUICK_SCHEDULE_PART = "jobs/get_participants_for_interview";
    public static final String INT_SCHEDULE_NEW = "schedule_interview";
    public static final String INT_SCHEDULE_QUICK = "quick_video_interview";
    public static final String INT_RE_SCHEDULE = "/reschedule_interview";

    public static final String REC_DASHBOARD = "recruiters/my_interviews";
    public static final String REC_MY_JOBS = "recruiters/open_jobs?";
    public static final String REC_JOB_DET = "jobs/job_details?";
    public static final String REC_JOB_DET_NEW = "jobs/get_job_details";
    public static final String REC_JOB_EDIT_DET = "jobs/edit_job?";
    public static final String REC_INT_JOBS = "recruiters/get_jobs_for_interviewer";

    public static final String INT_DASH_ARCHVS = "interview/full_report?";
    public static final String INT_JOBS_RECM = "interviewer/recommended_jobs?";
    public static final String INT_JOBS_ENGD = "interviewer/engaged_jobs?";
    public static final String INT_DETAILS = "interview_details";


    public static final String USER_PROFILE_SHOW = "profile_details";
    public static final String USER_RESET = "users/change_password";
    public static final String USER_FORGOT = "users/forgot_password";
    public static final String USER_SET = "users/set_password?";
    public static final String USER_SELECT_ROLE = "select_role?";
    public static final String USER_PROFILE_UPDATE_CONTACT = "profile/contact_update?";
    public static final String USER_PROFILE_UPDATE_ADDRESS = "profile/address_update?";
    public static final String USER_PROFILE_UPDATE_SKILLS = "interviewer/interviewer_update?";
    public static final String USER_PROFILE_UPDATE_AVAILABILITY = "interviewer/add_availability?";
    public static final String USER_PROFILE_Remove_AVAILABILITY = "interviewer/remove_availability?";
    public static final String USER_PROFILE_AVAILABILITIES = "interviewer/get_interviewer_availabilities?";
    public static final String USER_PROFILE_PAYPALINFO = "verify_paypal_account";
    public static final String USER_PROFILE_AVATAR_UPDATE = "/profile/avatar_update";

    public static final String USER_CONTACT_US = "users/contact_us?";
    public static final String USER_SWITCH_ROLE = "users/switch_role?";
    public static final String USER_PAY_TO_ETEKI = "pay_to_eteki?";
    public static final String USER_SUBMIT_REVIEW = "rate_interviewer?";
    public static final String USER_REVIEW_CANDIDATE = "rate_candidate";
    public static final String RATE_INTERVIEWER_CONFERENCE = "/interviews/rate_interviewer";
    public static final String USER_CREATE_JOB = "jobs/create_job?";
    public static final String USER_EDIT_JOB = "jobs/edit_job?";
    public static final String USER_UPDATE_JOB = "jobs/update_job";
    public static final String USER_CLOSE_JOB = "jobs/close_job";
    public static final String END_CALL = "/finish_interview";

    public static final String USER_SEARCH_INT = "search_interviewers?";
    public static final String USER_ADD_FAV_INTERVIEWER = "recruiters/add_fav_interviewer";
    public static final String USER_REM_FAV_INTERVIEWER = "recruiters/remove_fav_interviewer";
    public static final String USER_PUBLIC_PROFILE = "interviewer/get_interviewer_public_profile";

    public static final String USER_ADD_EXISTING = "jobs/add_job_interviewers";
    public static final String USER_ADD_NEW = "add_interviewer_new_job?";
    public static final String USER_ADD_CAND = "jobs/add_candidate";
    public static final String USER_UPDATE_CAND = "/candidates/update_candidate";
    public static final String USER_ADD_JOBS_INT = "jobs/add_interviewer_jobs";
    public static final String USER_DEL_INTER = "jobs/remove_interviewer";
    public static final String USER_DEL_CAND = "jobs/remove_candidate";
    //public static final String USER_PAY_SERVER="transaction_response?";

    //static data
    public static final String SKILLS_TABLE = "skills_list?";

    public static final String TIME_ZONES = "time_zone_list";
    public static final String COUNTRIES_TABLE = "countries_list";
    public static final String GET_DOMAINS = "get_domains";


    public static final String KEY_TOKEN = "authentication_token=";
    public static final String KEY_TOKEN_NO_EQUAL = "authentication_token";

    public static final String KEY_SKILL_ID = "skill_id=";

    public static final String KEY_INTERVIEW_ID = "&interview_id=";
    public static final String KEY_INTERVIEW_CODE = "&interview_code=";
    public static final String KEY_INTERVIEW_REPORT_ID = "interview_report_id";

    public static final String KEY_INT_CODE = "interview_code=";
    public static final String KEY_PASS_CODE = "&passcode=";

    public static final String START_INTERVIEW = "/interviews/finish_interview";

    public static final String DOWNLOAD_REPORT = URL_API + "/reports/download_pdf?report_id=";
    public static final String DOWNLOAD_REPORTS=URL_API;

    public static final String DOWNLOAD_AGREEMENT_RECRUITER ="https://s3-us-west-2.amazonaws.com/eteki-documents/agreements/Client_Agreement.docx";

    public static final String DOWNLOAD_AGREEMENT_INTERVIEWER ="https://s3-us-west-2.amazonaws.com/eteki-documents/agreements/Independent_Contractor_Agreement.docx";

    /**
     * Reports server calls
     */
    // post request, interview_report_id and interview_report_skills key with hash containing interview report skills parameters
    public static final String REPORT_ADD_SKILL = "/reports/add_report_skills";

    //post request with interview_report_id, interview_report_skill_id
    public static final String REPORT_REMOVE_SKILL = "/reports/remove_report_skills";

    //post requet, interview_report_id and signature
    public static final String REPORT_ADD_SIGNATURE = "/reports/add_report_signature";

    // post request interview_report_id and professional_background
    public static final String REPORT_ADD_PROF = "/reports/add_professional_background";

    public static final String INT_GET_FB = "interviews/get_report";

    public static final String REMIND_JOIN_COMPANY_REQUEST = "/company/remind_join_request";

    public static final String PAY_INTERVIEW = "/payments/pay_interview";


    public static final String MSG_AUTH_FAILED = "Authentication failed";

    public static final String GET_CONTACTS = "/get_contact_details";

    public static final String UPDATE_SMS_SERVICE = "/sms/sms_service_update";
    public static final String GET_CANDIDATE_DETAILS = "/candidate_details";
    public static final String GET_CANCELLATION_REASON = "/get_cancellation_reason";


    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,})$";
    public static final String URL_MATCHER = "<^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>";
    public static final String GET_BASIC_JOB_DETAILS = "/jobs/basic_details";
    public static final String ADD_EMAIL_ID_FOR_SHARING_INTERVIEW_REPORT ="interview_access/add_interview_access" ;

    public static final Integer REQUEST_CODE_ANALYTICS = 2018;


    public static void showToastError(Context c, String msg, boolean isError) {
        int dura = 20;
        Toast toast = Toast.makeText(c, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 100);
        TextView ve = (TextView) toast.getView().findViewById(android.R.id.message);
        if (isError)
            ve.setTextColor(Color.RED);
        else
            ve.setTextColor(Color.WHITE);
        ve.setTextSize(20);
        toast.show();
    }

    /*public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<>();
        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }*/
//    public static ArrayList<StaticDataPojo> ArraytoMap(JSONArray array) throws JSONException {
//
//        ArrayList<StaticDataPojo> list = new ArrayList<>();
//        StaticDataPojo pojo;
//        for (int i = 0; i < array.length(); i++) {
//            pojo = new StaticDataPojo();
//            pojo.id = i + 1;
//            JSONObject value = array.getJSONObject(i);
//            Iterator<String> key = value.keys();
//            while (key.hasNext()) {
//                String string = key.next();
//                ////Log.i(string, value.getString(string));
//
//                pojo.name = string;
//                pojo.description = value.get(string).toString();
//            }
//            list.add(pojo);
//        }
//        return list;
//
//    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {

                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

}
