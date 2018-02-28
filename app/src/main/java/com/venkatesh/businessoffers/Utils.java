package com.venkatesh.businessoffers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amzur.eteki.Home;
import com.amzur.eteki.chat.ChatService;
import com.amzur.eteki.db.DataBase;
import com.amzur.eteki.interfaces.AlertAnswerListener;
import com.amzur.eteki.interfaces.ConformationListener;
import com.amzur.eteki.interfaces.DialogDismissListner;
import com.amzur.eteki.pojos.Model;
import com.amzur.eteki.pojos.ProfilePicPojo;
import com.crashlytics.android.Crashlytics;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;

public class Utils {

    public static final int PICK_IMAGE = 1;
    public static final int CROP_PIC = 2;
    public static final int CAPTURE_IMAGE = 3;
    public static final String STATUS_SCHEDULED = "Scheduled";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_PENDING_PAYMENT = "Pending Payment";
    public static final String STATUS_CLOSED = "Completed";
    public static final String STATUS_WAITING_CANDIDATE = "Waiting on Candidate";
    public static final String STATUS_WAITING_INTERVIEWER = "Waiting on Interviewer";
    public static final String STATUS_INCOMPLETE = "Incomplete";
    public static final String STATUS_CANCELED = "Cancelled";
    public static final String STATUS_PENDING_REPORT = "Pending Report";
    public static final String STATUS_PENDING_APPROVAL = "Pending Approval";
    public static final String STATUS_INTERVIEW_REPORT_PENDING_REPORT_APPROVAL = "Pending Admin Approval";
    public static final String STATUS_ADMIN_APPROVED_REPORT = "Admin Approved Report";
    public static final String//BOTH_ACCEPTED = "Upcoming",
            //BOTH_REJECTED = "Both Rejected",
            RECRUITER_ACCEPT = "Recruiter Accept";
    public static final String RECRUITER_REJECTED = "Recruiter Reject";
    public static final String INTERVIEWER_REQUEST = "Interviewer Request";
    //INTERVIEWER_ACCEPTED = "Interviewer Accepted",
    public static final String MESSAGE_ROLE_INVALID = "Role sent is invalid.";
    public static final String TYPE_INTERVIEW_SCHEDULED = "Interview Schedule";
    public static final String TYPE_INTERVIEWER_JOB_ASSIGNMENT_OPPORTUNITY = "Interview Assignment Opportunity";

    public static final String TYPE_INTERVIEWER_JOB_PROPOSAL = "Interviewer Proposal";
    public static final String TYPE_PROPOSAL_ACCEPT_FOR_JOB = "Recruiter accepted your job proposal";
    public static final String TYPE_PROPOSAL_REJECT_FOR_JOB = "Recruiter rejected your job proposal";
    public static final String JOB_ST_NEW = "New";
    public static final String JOB_ST_ACCEPTED = "Accepted";
    public static final String JOB_ST_PENDING = "Pending";
    public static final String JOB_ST_REJECT = "Rejected";
    public static final String TYPE_INTERVIEW_RESCHEDULED = "Interview Rescheduled";
    public static final String TYPE_RECRUITER_REQUEST_TO_JOIN_COMPANY = "Request to Join Company";
    public static final String TYPE_RECRUITER_INTERVIEWER_SCREENING = "Interview Assignment Opportunity";
    public static final String TYPE_RECRUITER_REJECT_TO_JOIN_COMPANY = "Recruiter rejected to join company";
    public static final String TYPE_RECRUITER_REVOKE_TO_JOIN_COMPANY = "Recruiter removed from the company";
    public static final String ERROR_SOMETHING = "Something went wrong";
    public static final String REPORT_STATUS_PENDING_SUBMISSION = "Pending for Submission";
    public static final String REPORT_STATUS_PENDING_CHANGES = "Pending Changes";
    public static final String[] LANGUAGES = {"Select", "Mandarin", "Spanish", "Hindi / Urdu", "Arabic", "Portuguese", "Bengali", "Russian", "Japanese", "Punjabi", "German", "Javanese", "Wu", "Malay/Indonesian", "Telugu", "Vietnamese", "Korean", "French", "Marathi", "Tamil", "Persian", "Turkish", "Italian", "Cantonese", "Thai", "Gujarati", "Jin", "Min Nan", "Polish", "Pashto", "Kannada", "Xiang", "Malayalam", "Sundanese", "Hausa", "Oriya", "Burmese", "Hakka", "Ukrainian", "Bhojpuri", "Tagalog", "Yoruba", "Maithili", "Swahili", "Uzbek", "Sindhi", "Amharic", "Fula", "Romanian", "Oromo", "Igbo", "Azerbaijani", "Awadhi", "Gan", "Cebuano", "Dutch", "Kurdish", "Serbo-Croatian", "Malagasy", "Saraiki", "Nepali", "Sinhalese", "Chittagonian", "Khmer", "Assamese", "Madurese", "Somali", "Marwari", "Magahi", "Haryanvi", "Hungarian", "Chhattisgarhi", "Greek", "Chewa", "Deccan", "Akan", "Kazakh", "Min Bei", "Sylheti", "Zulu", "Czech", "Kinyarwanda", "Dhundhari", "Haitian Creole", "Min Dong", "Ilokano", "Quechua", "Kirundi", "Swedish", "Hmong", "Shona", "Uyghur", "Hiligaynon", "Mossi", "Xhosa", "Belarusian", "Balochi", "Konkani"};
    public static final String[] countries = {"Select", "Åland Islands", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorre", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbade", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bonaire,Sint Eustatius and Saba", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo (Dem. Rep.)", "Cook Islands", "Costa Rica", "Crna Gora", "Croatia", "Cuba", "Curaçao", "Cyprus", "Czech Republic", "Côte D'Ivoire", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey and Alderney", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea (North)", "Korea (South)", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Romania", "Russia", "Rwanda", "Réunion", "Saint Barthélemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syria", "São Tomé and Príncipe", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States Minor Outlying Islands", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands of the United States", "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"};
    public static final String[] countries_create_job = {"Please Select Country", "United States of America","Åland Islands", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorre", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbade", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bonaire,Sint Eustatius and Saba", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo (Dem. Rep.)", "Cook Islands", "Costa Rica", "Crna Gora", "Croatia", "Cuba", "Curaçao", "Cyprus", "Czech Republic", "Côte D'Ivoire", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey and Alderney", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea (North)", "Korea (South)", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Romania", "Russia", "Rwanda", "Réunion", "Saint Barthélemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syria", "São Tomé and Príncipe", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands of the United States", "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"};
    public static final String[] countries_static = {"Åland Islands", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorre", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbade", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bonaire,Sint Eustatius and Saba", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo (Dem. Rep.)", "Cook Islands", "Costa Rica", "Crna Gora", "Croatia", "Cuba", "Curaçao", "Cyprus", "Czech Republic", "Côte D'Ivoire", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey and Alderney", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea (North)", "Korea (South)", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Romania", "Russia", "Rwanda", "Réunion", "Saint Barthélemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syria", "São Tomé and Príncipe", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States Minor Outlying Islands", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands of the United States", "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"};
    public static final String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawali", "Idaho", "Illinios", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
    public static final String[] languages_job_creation = {"English", "Akan", "Amharic", "Arabic", "Assamese", "Awadhi", "Azerbaijani", "Balochi", "Belarusian", "Bengali", "Bhojpuri", "Burmese", "Cantonese", "Cebuano", "Chewa", "Chhattisgarhi", "Chittagonian", "Czech", "Deccan", "Dhundhari", "Dutch", "French", "Fula", "Gan", "German", "Greek", "Gujarati", "Haitian Creole", "Hakka", "Haryanvi", "Hausa", "Hiligaynon", "Hindi / Urdu", "Hmong", "Hungarian", "Igbo", "Ilokano", "Italian", "Japanese", "Javanese", "Jin", "Kannada", "Kazakh", "Khmer", "Kinyarwanda", "Kirundi", "Konkani", "Korean", "Kurdish", "Madurese", "Magahi", "Maithili", "Malagasy", "Malay/Indonesian", "Malayalam", "Mandarin", "Marathi", "Marwari", "Min Bei", "Min Dong", "Min Nan", "Mossi", "Nepali", "Oriya", "Oromo", "Pashto", "Persian", "Polish", "Portuguese", "Punjabi", "Quechua", "Romanian", "Russian", "Saraiki", "Serbo-Croatian", "Shona", "Sindhi", "Sinhalese", "Somali", "Spanish", "Sundanese", "Swahili", "Swedish", "Sylheti", "Tagalog", "Tamil", "Telugu", "Thai", "Turkish", "Ukrainian", "Uyghur", "Uzbek", "Vietnamese", "Wu", "Xhosa", "Xiang", "Yoruba", "Zulu"};


    public static final String[] SERVICE_TYPES_HELP_TEXTS = {"I prefer eTeki take responsibility for scheduling, interviewer selection, candidate tech checks, speed of delivery, and interview quality.", "I take responsibility for scheduling, interviewer selection, and candidate tech checks. I understand this level of service does not include any eTeki guarantee of delivery speed or interview quality."};
    public static final String[] DOMAINS = {
            "Accounting, Finance", "Advertising, PR, MR, Event Management", "Agriculture, Dairy", "Animation, Gaming", "Architecture, Interior Design", "Automobile, Auto Ancillary, Auto Components", "Aviation, Aerospace Firms", "Banking, Financial Services, Broking", "BPO, Call Centre, ITES", "Brewery, Distillery", "Broadcasting", "Ceramics, Sanitary ware", "Chemicals, Petro Chemical, Plastic, Rubber", "Construction, Engineering, Cement, Metals", "Consumer Electronics, Appliances, Durables", "Courier, Transportation, Freight, Warehousing", "Education, Teaching, Training", "Electricals, Switchgears", "Export, Import", "Facility Management", "Fertilizers, Pesticides", "FMCB, Foods, Beverage", "Food Processing", "Fresher, Trainer, Entry Level", "Gems, Jewellery", "Government, Defence", "Heat Ventilation, Air Conditioning", "Industrial Products, Heavy machinery", "Insurance", "Internet, Ecommerce", "Iron and Steel", "IT-Hardware & Networking", "IT-Software, Software Services", "KPO, Research, Analytics", "Leather", "Legal", "Media, Entertainment, Internet", "Medical, Healthcare, Hospitals", "Mining, Quarrying", "NGO, Social Services, Regulators, Industry Associations", "Office Equipment, Automation", "Oil and Gas, Energy, Power, Infrastructure", "Other", "Pharma, Biotech, Clinical Research", "Printing, Packaging", "Publishing", "Pulp and Paper", "Real Estate, Property", "Recruitment, Staffing", "Retail, Wholesale", "Security, Law Enforcement", "Semiconductors, Electronics", "Shipping, Marine", "Strategy, Management Consulting Firms", "Sugar", "Telecom, ISP", "Textiles, Garments, Accessories", "Travel, Hotels, Restaurants, Airlines, Railways", "Tyres", "Water Treatment, Waste management", "Wellness, Fitness, Sports"};
    public static final String CURRENCYCODES[][] = new String[][]{
            {"Australian Dollar (AUD)", "AUD"},
            {"Brazilian Real (BRL)", "BRL"},
            {"Canadian Dollar (CAD)", "CAD"},
            {"Czech Koruna (CZK)", "CZK"},
            {"Danish Krone (DKK)", "DKK"},
            {"Euro (EUR)", "EUR"},
            {"Hong Kong Dollar (HKD)", "HKD"},
            {"Hungarian Forint (HUF)", "HUF"},
            {"Israeli New Sheqel (ILS)", "ILS"},
            {"Japanese Yen (JPY", "JPY)"},
            {"Malaysian Ringgit (MYR)", "MYR"},
            {"Mexican Peso (MXN)", "MXN"},
            {"Norwegian Krone (NOK)", "NOK"},
            {"New Zealand Dollar (NZD)", "NZD"},
            {"Philippine Peso (PHP)", "PHP"},
            {"Polish Zloty (PLN)", "PLN"},
            {"Pound Sterling (GBP)", "GBP"},
            {"Russian Ruble (RUB)", "RUB"},
            {"Singapore Dollar (SGD)", "SGD"},
            {"Swedish Krona (SEK)", "SEK"},
            {"Swiss Franc (CHF)", "CHF"},
            {"Taiwan New Dollar (TWD)", "TWD"},
            {"Thai Baht (THB)", "THB"},
            {"Turkish Lira (TRY)", "TRY"},
            {"U.S. Dollar (USD)", "USD"}
    };

    public static String SCHEDULE_AUTOMATED = "Auto";
    public static String HYBRID_SCHEDULE = "Hybrid";

    //Candidate Statuses
    public static String PENDING_TIME_SLOTS = "Waiting for candidate",
//            PENDING_AUTO_SOURCING = "Pending Autosourcing",
            OBTAINING_INTEREST_1 = "Waiting for Interviewer",
//            OBTAINING_INTEREST_2 = "Obtaining Interests 2",
            OBTAINED_INTEREST_1 = "Proposal Submitted",
//            OBTAINED_INTEREST_2 = "Proposal Resubmission",
            PENDING_INTERVIEW = "Pending Interview",
            INTERVIEWED = "Completed",
            INTERVIEW_SCHEDULED = "Scheduled",
//            FAILED_AUTOSOURCING1 = "Revise Match Criteria",
//            FAILED_AUTOSOURCING2 = "Request Admin Match",
//            FAILED_INTERESTS = "Failed Interests",
            FAILED_INTEREST_1 = "Revise Schedule",
//            FAILED_INTEREST_2 = "Request Admin Scheduling",
            INTERVIEW_CANCELLED = "Cancelled";




    public static String CANDIDATE_STATUSES[] = {OBTAINED_INTEREST_1, INTERVIEWED,  FAILED_INTEREST_1, INTERVIEW_CANCELLED, PENDING_TIME_SLOTS, PENDING_INTERVIEW, OBTAINING_INTEREST_1};

    public static String TYPE_RECRUITER = "Recruiter", TYPE_INTERVIEWER = "Interviewer", TYPE_CANDIDATE = "Candidate",
            TYPE_INTERVIEWER_FREELANCE = "eTeki Interviewer", TYPE_INTERVIEWER_EXTERNAL = "External Interviewer";
    /* public static String[] timeZones={"US/Samoa  (GMT-11:00)", "US/Hawaii  (GMT-10:00)", "US/Alaska  (GMT-09:00)", "US/Pacific-New  (GMT-08:00)",
               "US/Mountain  (GMT-07:00)", "US/Indiana-Starke  (GMT-06:00)", "US/Michigan  (GMT-05:00)", "Chile/Continental  (GMT-04:00)",
               "Canada/Newfoundland  (GMT-03:30)", "Brazil/East  (GMT-03:00)", "Brazil/DeNoronha  (GMT-02:00)", "UTC  (GMT+00:00)", "Poland  (GMT+01:00)",
               "Europe/Nicosia  (GMT+02:00)", "Africa/Asmera  (GMT+03:00)", "W-SU  (GMT+04:00)", "Asia/Ashkhabad  (GMT+05:00)", "Asia/Calcutta  (GMT+05:30)",
               "Asia/Thimbu  (GMT+06:00)", "Asia/Saigon  (GMT+07:00)", "Singapore  (GMT+08:00)", "ROK  (GMT+09:00)", "Pacific/Yap  (GMT+10:00)",
               "Pacific/Ponape  (GMT+11:00)", "NZ  (GMT+12:00)"};*/



    // SMS provider identification
    // It should match with your SMS gateway origin
    // You can use  MSGIND, TESTER and ALERTS as sender ID
    // If you want custom sender Id, approve MSG91 to get one
    public static final String SMS_ORIGIN = "BH-PLVSMS";

    // special character to prefix the otp. Make sure this character appears only once in the sms
    public static final String OTP_DELIMITER = ":";
    public static final String AGENT_ETEKI="Eteki";
    public static final String AGENT_SELF="Self";


    public static final int CODE_END_VIDEO = 2025;

    public static AlertDialog.Builder helpDialog;


    public static InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            String blockCharacterSet = "!#$%&'*+-/=?^_`{|}~";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
    //Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private static Animator mCurrentAnimator;
    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private static int mShortAnimationDuration = 500;
    private static ProgressDialog pDialog;
    private static android.app.AlertDialog alertHelpDialog;

    public static boolean validateEditext(View v, int[] ids) {
        boolean b = true;
        for (int id : ids) {
            //Log.i("view_id", id + "");
            EditText et = (EditText) v.findViewById(id);
            int iType = et.getInputType();
            //Log.i("email validation", iType + "");
            if (et.getText().toString().trim().equals("")) {
                et.setError("Field can't be empty");
                b = false;
                et.requestFocus();
                shakeView(v.getContext(), et);
                break;
            } else if ((iType == 33) && !validateEmail(et.getText().toString())) {
                et.setError("Email id not valid");
                b = false;
                break;
            }
        }
        return b;
    }



    public static void showSnackBar(Activity act, String msg) {
        if (msg.contains(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        final Snackbar bar = Snackbar.make(act.findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE);
        bar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.dismiss();
            }
        });
        bar.show();
    }
    public static boolean validateEmail(String email) {
        return email.trim().matches(UtilsServer.EMAIL_PATTERN);
    }

    public static void shakeView(Context c, View v) {
        Animation utils = AnimationUtils.loadAnimation(c, R.anim.shake);
        v.startAnimation(utils);
        v.requestFocus();
    }

    public static void showErrorAlert(String title, final String msg, final Activity act, final ErrorAlertCompleted alertCompleted) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if (title != null)
            builder.setTitle(title);
        else
            builder.create().requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setMessage(msg);
        if (msg.equals(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        builder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (alertCompleted != null)
                            alertCompleted.OkaySelected();

                    }
                });
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    /////////
    /*public static Snackbar showIndefiniteSnackBar(Activity act,String msg){
        if (msg.contains(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        final Snackbar bar=Snackbar.make(act.findViewById(android.R.id.content),msg,Snackbar.LENGTH_INDEFINITE);
        bar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.dismiss();
            }
        });
        bar.show();
        return bar;
    }*/

    public static void showSuccessAlert(String title, final String msg, final Activity act, final ErrorAlertCompleted alertCompleted) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(title);
        builder.setMessage(msg);
        if (msg.equals(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (alertCompleted != null)
                    alertCompleted.OkaySelected();
            }
        });
        builder.setCancelable(false);
        //builder.setIcon(android.R.drawable.ic_menu_save);
        builder.show();
    }

    public static AlertDialog globalShowSuccessAlert(String title, final String msg, final Activity act, final ErrorAlertCompleted alertCompleted) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(title);
        builder.setMessage(msg);
        if (msg.equals(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (alertCompleted != null)
                    alertCompleted.OkaySelected();
            }
        });
        builder.setCancelable(false);
        //builder.setIcon(android.R.drawable.ic_menu_save);
//        builder.show();
        return builder.create();
    }



    public static void showSnackBarLongTime(Activity act, String msg) {
        if (act == null)
            act = MyApplication.getInstance().getCurrentActivity();
        if (msg.contains(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        Snackbar.make(act.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();

    }

    public static Snackbar showSnackBarLongTime2(Activity act, String msg) {
        if (msg.equals(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        return Snackbar.make(act.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);

    }

    public static void showSnackBarShortTime(Activity act, String msg) {
        if (msg.equals(UtilsServer.MSG_AUTH_FAILED)) {
            parseJsonFeed(act);
        }
        Snackbar.make(act.findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackBarOnTop(Activity act, String str) {

        Snackbar snack = Snackbar.make(act.findViewById(android.R.id.content), str, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snack.show();
    }

    public static void showReasonDialog(Activity act, final DialogDismissListner listner) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(act);
        LayoutInflater inflater = act.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextInputEditText edt = (TextInputEditText) dialogView.findViewById(R.id.et_suggestion);

        dialogBuilder.setTitle("Reschedule Interview");
        dialogBuilder.setMessage("Do you want to reschedule this interview?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (listner != null) {
                    listner.Success(new Model(edt.getText().toString(), null, 0, 0));
                }
//do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public static void showConformationDialog(Activity act, String title, String msg, final ConformationListener cl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if (title != null)
            builder.setTitle(title);
        else
            builder.create().requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setMessage(msg);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cl != null)
                    cl.conformed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_info_outline_black);
        builder.show();

    }

    public static Dialog globalShowConfirmationDialog(Activity act, String title, String msg, final ConformationListener cl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if (title != null)
            builder.setTitle(title);
        else
            builder.create().requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setMessage(msg);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cl != null)
                    cl.conformed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_info_outline_black);
//        Dialog dialog=builder.create();
//        builder.show();
        return builder.create();

    }

    public static void showYesOrNoDialog(Activity act, String title, String msg, final AlertAnswerListener cl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if (title != null)
            builder.setTitle(title);
        else
            builder.create().requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setMessage(msg);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (cl != null)
                    cl.onAnswered(true);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (cl != null)
                    cl.onAnswered(false);
            }
        });

        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_info_black_36dp);
        builder.show();
    }

    public static void showSuccessDialog(Activity act, String title, String msg, final ConformationListener cl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if (title != null)
            builder.setTitle(title);
        else
            builder.create().requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setMessage(msg);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cl != null)
                    cl.conformed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setCancelable(false);

        builder.show();
    }

    public static void showOkayDialog(Activity act, String title, String msg, final ConformationListener cl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if (title != null)
            builder.setTitle(title);
        else
            builder.create().requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setMessage(msg);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (cl != null)
                    cl.conformed();
            }
        });
        builder.setCancelable(true);
        builder.show();
    }

    public static void helpDialog(Context context) {
        final TextView message = new TextView(context);
        // i.e.: R.string.dialog_message =>
        // "Test this dialog following the link to dtmilano.blogspot.com"
        final SpannableString s =
                new SpannableString(context.getText(R.string.help_content));
        Linkify.addLinks(s, Linkify.ALL);
        //Linkify.addLinks(s,Linkify.PHONE_NUMBERS);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        message.setPadding(10, 10, 10, 10);

        new AlertDialog.Builder(context)
                .setTitle(R.string.help)
                .setCancelable(true)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(message)
                .create().show();
    }

    public static void signOut(final Activity act) {

        Call<ResponseBody> cl = MyApplication.getSerivce().userSignout();
        cl.enqueue(new Listener(new RetrofitService() {
            @Override
            public void onSuccess(String result, int pos, Throwable t) {
                if (pos == 0) {
                    parseJsonFeed(act);
                }
            }
        }, null, true, act));
    }

    public static void parseJsonFeed(Activity act) {
        ChatService.unSubScribeToAll();
        SingleTon.getInstance().setTempBitmap(null);
        act.stopService(new Intent(act, ChatService.class));
        Intent in = new Intent(act, Home.class);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PreferencesData.putBool(false, PreferencesData.PREF_LOGIN);
        PreferencesData.putBool(true, PreferencesData.PREF_ON_OFFLINE);
        PreferencesData.putProifleFilled(act, false);
        PreferencesData.putBool(false, PreferencesData.PREF_PROFILE_SUBSCRIPTION);
        PreferencesData.putAutoSourceStatus(act, false);
        StaticDataPuller.storeImageUrls(new ProfilePicPojo(), act);
        SingleTon.getInstance().setFirstTimeAppearing(true);
        act.startActivity(in);

    }

    public static Map getParamsInMap(String[] keys, Object[] values) {
        Map<String, Object> params = new HashMap<>();
        if (keys != null && values != null && keys.length == values.length) {
            for (int i = 0; i < values.length; i++) {
                params.put(keys[i], values[i]);
            }
        }
        return params;
    }

    public static void zoomImageFromThumb(final View thumbView, int imageResId, Activity act) {
        // If there"s an animation in progress, cancel it
        // immediately and proceed with this one.
        //Log.i("Called", "Zooming image");
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) act.findViewById(
                R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        expandedImageView.bringToFront();
        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        act.findViewById(android.R.id.content)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    public static String makeUnderLine(String skills) {
        String[] list = skills.split(",");
        StringBuilder builder = new StringBuilder();

        for (String s : list) {
            builder.append("#");
            builder.append(s.trim());
            builder.append("   ");
        }

        return builder.toString();
    }

    public static String makeUnderLine(ArrayList<String> skills) {

        StringBuilder builder = new StringBuilder();
        if (skills != null)
            for (String s : skills) {
                builder.append("#");
                builder.append(s.trim());
                builder.append("   ");
            }

        return builder.toString();
    }

    public static InputFilter getRequiredFilter(String digits) {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; ++i) {
                    if (!Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmn opqrstuvwxyz]*").matcher(String.valueOf(source.charAt(i))).matches()) {
                        return "";
                    }
                }

                return null;
            }
        };
    }

    /**
     * Convert string date to comma separated
     *
     * @param data string data to handle
     */
    public static String convertToCommaSeperated(String data) {
        String[] name = data.split(",");
        if (name.length > 0) {
            StringBuilder nameBuilder = new StringBuilder();

            for (String n : name) {
                if (!nameBuilder.toString().contains(n)) {
                    nameBuilder.append(n.trim()).append(",");
                }
                // can also do the following
                // nameBuilder.append("'").append(n.replace("'", "''")).append("',");
            }
            if (nameBuilder.length() > 0)
                nameBuilder.deleteCharAt(nameBuilder.length() - 1);

            return nameBuilder.toString();
        } else {
            return "";
        }

    }


    public static String convertToCommaSeparatedString(ArrayList<String> list) {
        String result = null;
        if (list != null && list.size() > 0) {

            StringBuilder sb = new StringBuilder();
            for (String item : list) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(item);
            }
            result = sb.toString();

        }
        return result;
    }


    /**
     * Check whether same skill repeated
     */
    public static boolean findSkillRepeated(String data) {
        String[] name = data.split(",");
        for (int i = 0; i < name.length; i++) {
            for (int j = i + 1; j < name.length; j++) {
                if (name[j] != null && name[j].trim().equalsIgnoreCase(name[i]))
                    return true;
            }
        }
        return false;
    }

    public static void exportDB(Activity activity) {
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source;
        FileChannel destination;
        String currentDBPath =//activity.getDatabasePath(DataBase.DB_NAME).getAbsolutePath();
                "/data/" + "com.amzur.eteki" + "/databases/eTeki.db";
        String backupDBPath = "eteki.db";
        File currentDB = activity.getDatabasePath(DataBase.DB_NAME);
        //new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(activity, "DB Exported!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Backup");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.setType("application/octet-stream");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(backupDB));
            activity.startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check file size
     */
    public static int getFileSize(String path) {
        try {
            File file = new File(path);
            long length = file.length();
            length = length / 1024 / 1024;
            //Log.i("File Path : " + file.getPath(), " File size : " + length + " MB");
            return (int) length;
        } catch (Exception e) {
            e.printStackTrace();
            Crashlytics.logException(e);
            return 0;
        }
    }

    /**
     * Check file extensions
     */
    public static boolean checkFileExtension(String filePath, String[] arrayFormats) {
        CustomStringList formats = new CustomStringList();
        formats.addAll(Arrays.asList(arrayFormats));
        //Log.i("formatslist", formats.toString());
        return formats.contains(filePath.substring(filePath.lastIndexOf(".") + 1));
    }


    /**
     * Delete file from local
     */
    public static void deleteFileFromLocal(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFlashAnimation(View v) {
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        v.startAnimation(animation);
    }

    /**
     * @param data
     * @return
     */
    public static boolean checkNullOrEmpty(String data) {
        boolean flag;
        flag = data == null || data.equals("") ? false : true;
        return flag;

    }

    /**
     * This method set the data to the text view based on conditions.
     *
     * @param v          Textview to which we want to set the data.
     * @param data       data to be added to the textview.
     * @param visibility if true then the textview visibility is gone if data is null or empty otherwise setText as None Provided.
     */
    public static void setDataToTextview(View v, String data, boolean visibility) {
        boolean flag;
        flag = data == null || data.equals("") ? false : true;
        if (flag) {
            ((TextView) v).setText(data);
        } else {
            if (visibility)
                v.setVisibility(View.GONE);
            else
                ((TextView) v).setText(R.string.none_provided);
        }
    }

    /**
     * This method network error dialogue when network is not available.
     */
    public static void showNetworkErrorDilaogue(Activity activity) {
        Utils.showErrorAlert("Network error", "Please turn on your mobile data", activity, new Utils.ErrorAlertCompleted() {
            @Override
            public void OkaySelected() {

            }
        });

    }

    /**
     * Returns the date in device date format.
     *change this format ("2018-01-11T13:55:59.000Z") to 01-Jan-2018 1:55 pm
     * @return
     */
    public static String getFormattedDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date result = null;
        String formattedDtae = null;
        try {
            result = df.parse(date);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a", Locale.ENGLISH);
            TimeZone timeZone = TimeZone.getDefault();
            sdf.setTimeZone(timeZone);
            formattedDtae = (sdf.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDtae;
    }


    public static String getFormattedDateOnly(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date result = null;
        String formattedDtae = null;
        try {
            result = df.parse(date);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH);
            TimeZone timeZone = TimeZone.getDefault();
            sdf.setTimeZone(timeZone);
            formattedDtae = (sdf.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDtae;
    }

    /**
     * sets data to textview from the jsonobject send by the server.
     * @param jsonObject
     * @param key_required key of the value.
     * @param textView textview to which the value has to be set.
     */
    public static void setDataToUiElements(JSONObject jsonObject,String key_required,TextView textView){
        if(jsonObject!=null){
            if(!jsonObject.isNull(key_required)){
                try {
                    textView.setText(jsonObject.getString(key_required));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                textView.setText(R.string.none_provided);
            }
        }

    }

    public static Date getDateForString(String dat) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parsed = new Date();
        try {
            parsed = inputFormat.parse(dat);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return parsed;
    }


    /**Convert the format (2017-11-03T07:26:54.000Z) to this format (Wed Oct 25 15:21:11 GMT+05:30 2017)*/
    public static Date getDateInLocalTimezone(String date) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        TimeZone timeZone = TimeZone.getDefault();
        inputFormat.setTimeZone(timeZone);
        Date parsed = new Date();
        try {
            parsed = inputFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsed;
    }

    /**
     * Returns the date object in current time zone from calendar object.
     *
     * @param date milliseconds from calendar object.
     * @return date in current timezone.
     */
    public static Date getDateObjectFromCalendarTime(String date) {
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        TimeZone timeZone = TimeZone.getDefault();
        df.setTimeZone(timeZone);
        Date result = null;
        try {
            result = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * rconverts the utc formatted date to local time zone and returns the date object.
     *
     * @param date utc date from server.
     * @return the date object in current time zone.12hrs format
     */
    public static Date getFormattedDateFromUTC(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date result = null;
        String formattedDtae = null;
        try {
            result = df.parse(date);
            System.out.println("date:" + result); //prints date in current locale
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            TimeZone timeZone = TimeZone.getDefault();
            sdf.setTimeZone(timeZone);
            formattedDtae = (sdf.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getDateInLocalTimezone(formattedDtae);
    }

    /**
     * rconverts the utc formatted date to local time zone and returns the date object.
     *
     * @param date utc date from server.
     * @return the date object in current time zone.24hrs format
     */
    public static Date getFormattedDateFromUTCC(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date result = null;
        String formattedDtae = null;
        try {
            result = df.parse(date);
            System.out.println("date:" + result); //prints date in current locale
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            TimeZone timeZone = TimeZone.getDefault();
            sdf.setTimeZone(timeZone);
            formattedDtae = (sdf.format(result));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDateInLocalTimezone(formattedDtae);
    }


    /**
     * Returns the candidate statuses for which the feedback due by can be edited while editing the candidate.
     *
     * @return
     */
    public static List<String> getCandidateStatuses() {
        return Arrays.asList(CANDIDATE_STATUSES);
    }

    /**
     * This mehod shows a snackbar after the network call is succeded.
     *
     * @param responce responce from the server.
     * @param activity activity from which this method is being called.
     */
    public static void showSuccessMessageFromNetworkCall(String responce, Activity activity) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responce);
            JSONArray jsonArray = jsonObject.getJSONArray("message");
            Toast.makeText(activity, jsonArray.getString(0), Toast.LENGTH_SHORT).show();
            ColoredSnackbar.info(Utils.showSnackBarLongTime2(activity, jsonArray.getString(0))).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**Set the dialog width to parent and height to wrapContent*/
    public static Dialog getParamsForDialog(Dialog dialog) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        return dialog;

    }


    public interface ErrorAlertCompleted {
        void OkaySelected();
    }

    /**
     * @param filepath
     * @return Mime type of the file
     */
    public static String getMimeType(String filepath){
        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        String mimeType = myMime.getMimeTypeFromExtension(fileExt(filepath));
        return mimeType;
    }

    /**
     *
     * @param url
     * @return file extension
     */
    private static String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }




    /**
     *
     * @param context
     * @param intent
     * @return matching activity count
     */
    public static boolean hasMatchingActivity(Context context, Intent intent){
        PackageManager packageManager = context.getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        return isIntentSafe;
    }


    private static void setProgress(Activity mContext) {
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

    /**
     * Background Async Task to download file
     */
   public static class DownloadFileFromURLL extends AsyncTask<String, String, String> {

        private Activity mContext;
        public DownloadFileFromURLL(Context context) {
            mContext = (Activity) context;
        }

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        String fileType, file_name;
        boolean isFailed=false;
        @Override
        protected void onPreExecute() {
            setProgress(mContext);
            super.onPreExecute();

        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {

                String s = f_url[0];
                fileType = s.substring(s.lastIndexOf(".") + 1);

                file_name = getFileNameFromUrl(s);

                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                String extr = Environment.getExternalStorageDirectory().toString();
                File mFolder = new File(extr + "/eteki/");
                if (!mFolder.exists()) {
                    mFolder.mkdir();
                }
                // getting file length
                int lenghtOfFile = conection.getContentLength();
                conection.connect();
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

//                "/agreement."+fileType

                // Output stream to write file
                OutputStream output = new FileOutputStream(mFolder.getAbsolutePath() + "/"+file_name);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    //      publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                ColoredSnackbar.warning(Utils.showSnackBarLongTime2(mContext, "Download failed please contact CSR")).show();
                Crashlytics.logException(e);
                e.printStackTrace();
                isFailed = true;

            }
            return null;
        }


        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
            if (progress[0].equals(100)) {
                Utils.showSnackBarLongTime2(mContext, "Report saved successfully").setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(Environment.getExternalStorageDirectory() + "/eteki/" +"/"+file_name);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file) , "application/"+fileType);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        mContext.startActivity(intent);
                    }
                }).show();
            }
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute( String file_url) {
            pDialog.dismiss();
            if (!isFailed)
                Utils.showSnackBarLongTime2(mContext, "Report saved successfully").setAction("OPEN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String filepath = Environment.getExternalStorageDirectory() + "/eteki/" + "/"+file_name;
                        File file = new File(filepath);
                        String authority = BuildConfig.APPLICATION_ID + ".fileprovider";
                        try {
                            Uri uri = FileProvider.getUriForFile(mContext, authority, file);
                            String mimeType = Utils.getMimeType(filepath);
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(uri, mimeType);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            boolean isIntentSafe = Utils.hasMatchingActivity(mContext, intent);
                            if (isIntentSafe)
                                mContext.startActivity(intent);
                            else
                                Toast.makeText(mContext, mContext.getString(R.string.no_app_found), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
            // dismiss the dialog after the file was downloaded
        }

    }

    private static String getFileNameFromUrl(String file_url) {
        String fileName = file_url.substring(file_url.lastIndexOf('/') + 1);
        return fileName;
    }


    /**
     * This method returns true if the file type is .ODT
     */
    public static boolean isFileODT(String filePath) {
        String filename = filePath;
        String filenameArray[] = filename.split("\\.");
        String extension = filenameArray[filenameArray.length-1];
        return extension.equalsIgnoreCase("odt");
    }


    /**Dialog with icon and animation*/
    public static void showSweetAlertDialog(Activity act, String title, String msg, int successType) {
        new SweetAlertDialog(act, successType)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    public static SweetAlertDialog globalShowSweetAlertDialog(Activity act, String title, String msg, int successType) {
        SweetAlertDialog dialog = new SweetAlertDialog(act, successType);
        dialog.setTitleText(title)
                .setContentText(msg)
                .show();
        return dialog;
    }


    public static void showSnackbarInChat(final Activity act, String msg){
        Utils.showSnackBarLongTime2(act, msg).setAction("OPEN", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("receiverForChat");
                LocalBroadcastManager.getInstance(act).sendBroadcast(intent);
            }
        }).show();
    }

    /*Help dialog in Navigation drawer of all user types**/
    public static Dialog globalHelpDialog(Activity activity){
        final TextView message = new TextView(activity);
        // i.e.: R.string.dialog_message =>
        // "Test this dialog following the link to dtmilano.blogspot.com"
        final SpannableString s = new SpannableString(activity.getText(R.string.help_content));
        Linkify.addLinks(s, Linkify.ALL);
        //Linkify.addLinks(s,Linkify.PHONE_NUMBERS);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        message.setPadding(10, 10, 10, 10);

        alertHelpDialog = new android.app.AlertDialog.Builder(activity).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create();
        alertHelpDialog.setTitle(R.string.help);
        alertHelpDialog.setCancelable(true);
        alertHelpDialog.setView(message);
//        alertHelpDialog.show();
        return alertHelpDialog;

    }

    public static String convertGMTtoUTC(String inputString) {
        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date timestamp = null;
        try {
            timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(inputString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utcFormat.format(timestamp) + "Z";
    }

}