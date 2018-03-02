package com.venkatesh.businessoffers;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import com.venkatesh.businessoffers.pojos.BusinessAccountPojo;
import com.venkatesh.businessoffers.utilities.UtilsServer;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PartMap;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.Streaming;
import retrofit.http.Url;

/**
 * Created by RameshK on 24-11-2015.
 */

public interface WebService {

    /**
     * Static Data*/
    /**
     * Getting Updated SKills
     */
    @GET(UtilsServer.SKILLS_TABLE)
    Call<ResponseBody> getUpdatedSkills(@Query("skill_id") int id);


    @GET("interviewers/get_rating")
    Call<ResponseBody> getRating();

    @GET
    Call<ResponseBody> getStaticData(@Url String url, @QueryMap Map<String, Object> params);

    @POST(UtilsServer.USER_PROFILE_PAYPALINFO)
        // Call<ResponseBody> updatePaypalId(@Field("paypal_id") String id,@Field("paypal_first_name") String fname,@Field("paypal_last_name") String lname,@Field("Currency Code") String code);
    Call<ResponseBody> updatePaypalId(@QueryMap Map<String, String> params);

    //(@Query(UtilsServer.KEY_TOKEN_NO_EQUAL) String token,@Query("job") String job,@Query("schedule") String schedule,@Query("status") String status);

    @GET(UtilsServer.INT_DASHBOARD)
    Call<ResponseBody> getIntDashBoard(@QueryMap Map<String, Object> params);



   /* @Multipart
    @Headers({ "Accept: application/json",  "Content-Type: multipart/form-data"})
    @POST(UtilsServer.USER_PROFILE_UPDATE_CONTACT)
    Call<ResponseBody> postProfilePersonal(@Part("avatar") RequestBody image,@Part("first_name") String fName,@Part("last_name") String lName,@Part("alternate_email") String aEmail,@Part("blog_url") String blogUrl,
                                          @Part("primary_contact") String c1,@Part("alternate_contact") String c2,@Part("country_code1") String cc1,@Part("country_code2") String cc2,@Part("display_name") String disName,
                                         @Query(UtilsServer.KEY_TOKEN_NO_EQUAL) String token);*/

    @FormUrlEncoded
    @POST(UtilsServer.USER_PROFILE_AVATAR_UPDATE)
    Call<ResponseBody> updateAvatar(@Field("avatar") String name);


    @Multipart
    @POST("profile/avatar_update")
    Call<ResponseBody> updateAvatar(@PartMap Map<String, RequestBody> mapFileAndName);

    /**
     * For Sign in
     */
//    @POST
//    Call<ResponseBody> userSignin(@Url String url, @Header("DEVICE-TOKEN") String token, @Body UserRegBean user);
//    //@FieldMap Map<String,Object> params);

    /**
     * Reset Password
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_RESET)
    Call<ResponseBody> userResetPwd(@Header("refresh-token") String refreshToken, @FieldMap Map<String, Object> params);

    /**
     * Sign out from session
     */

    @POST(UtilsServer.USER_LOGOUT)
    Call<ResponseBody> userSignout();

    /**
     * Clearing all other session for this user
     */
    @POST(UtilsServer.USER_CLEAR_ALL_SESSIONS)
    Call<ResponseBody> userClearAllSessions();

    /**
     * Update User contact details
     */
//    @FormUrlEncoded
//    @POST(UtilsServer.USER_PROFILE_UPDATE_CONTACT)
////    Call<ResponseBody> updateContactDet(@FieldMap Map<String, Object> params);
//    Call<ResponseBody> updateContactDet(@Body ContactPojo params);
//
//    /**
//     * Update User Address details
//     */
//    @FormUrlEncoded
//    @POST(UtilsServer.USER_PROFILE_UPDATE_ADDRESS)
//    Call<ResponseBody> updateAddressDet(@FieldMap Map<String, Object> params);
//
//    /**
//     * Post Skills Data
//     */
//    @POST(UtilsServer.USER_PROFILE_UPDATE_SKILLS)
//    Call<ResponseBody> updateProfileSkills(@Body SkillsUpdate update);

    /**
     * Post Create Job
     */
    @Multipart
    @POST(UtilsServer.USER_CREATE_JOB)
    Call<ResponseBody> createJob(@PartMap Map<String, String> params);

    /**
     * Post Update Job
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_UPDATE_JOB)
    Call<ResponseBody> updateJob(@FieldMap Map<String, Object> params);

    /**
     * Make interviewer Favorite
     * @param int_id interviewer's id
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_ADD_FAV_INTERVIEWER)
    Call<ResponseBody> makeIntFav(@Field("interviewer_id") int int_id);

    /**
     * Make interviewer Favorite
     *
     * @param int_id interviewer's id
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_REM_FAV_INTERVIEWER)
    Call<ResponseBody> makeIntUnFav(@Field("interviewer_id") int int_id);

    /**
     * Create and add candidate to job
     *
     * @param candidates object of candidate details
     */
    @POST(UtilsServer.USER_ADD_CAND)
    Call<ResponseBody> createCandidate(@Body String candidates, @Header("Content-type") String val);

    /**
     * Update and add candidate to job
     *
     * @param candidates object of candidate details
     */
    @POST(UtilsServer.USER_UPDATE_CAND)
    Call<ResponseBody> updateCandidate(@Body String candidates, @Header("Content-type") String val);

    /**
     * To adding interviewer to his jobs
     */
    //@FormUrlEncoded
    @POST(UtilsServer.USER_ADD_JOBS_INT)
    Call<ResponseBody> addJobsToInts(@Body String ids, @Header("Content-type") String val);

    /**
     * To adding interviewer to his jobs
     */
    //@FormUrlEncoded
    @POST(UtilsServer.USER_ADD_EXISTING)
    Call<ResponseBody> addIntsToJob(@Body String ids, @Header("Content-type") String val);

    /**
     * Create Schedule interview
     *
     * @param send data for scheduling
     */
//    //@FormUrlEncoded
//    @POST(UtilsServer.INT_SCHEDULE_NEW)
//    Call<ResponseBody> scheduleInterview(@Body QuickIntervieweSend send);
//
//    @FormUrlEncoded
//    @POST(UtilsServer.INT_RE_SCHEDULE)
//    Call<ResponseBody> reScheduleInterview(@FieldMap Map<String, Object> params);
//
//    /**
//     * Post Skills Data
//     */
//    @POST(UtilsServer.INT_SCHEDULE_QUICK)
//    Call<ResponseBody> quickScheduleInterview(@Body QuickIntervieweSend update);

    /**
     * Review interviewers
     *
     * @param params Map of params
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_SUBMIT_REVIEW)
    Call<ResponseBody> rateInterviewer(@FieldMap Map<String, Object> params);

    /**
     * Review interviewers from video conference
     *
     * @param params Map of params
     */
    @FormUrlEncoded
    @POST(UtilsServer.RATE_INTERVIEWER_CONFERENCE)
    Call<ResponseBody> rateInterviewerFromConference(@FieldMap Map<String, Object> params);

    /**
     * Review candidate
     *
     * @param params Map of params
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_REVIEW_CANDIDATE)
    Call<ResponseBody> rateCandidate(@FieldMap Map<String, Object> params);

    /**
     * Closing job
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_CLOSE_JOB)
    Call<ResponseBody> closeJob(@Field("job_id") int id);

    /**
     * Remove Interviewer
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_DEL_INTER)
    Call<ResponseBody> removeInterviewer(@Field("job_id") int jobId, @Field("interviewer_id") int intID);

    /**
     * Remove Candidate
     */
    @FormUrlEncoded
    @POST(UtilsServer.USER_DEL_CAND)
    Call<ResponseBody> removeCandidate(@Field("job_id") int jobId, @Field("candidate_id") int canID);

    /**
     * Apply to job
     */
    @FormUrlEncoded
    @POST(UtilsServer.INT_SUBMIT_PROP)
    Call<ResponseBody> applyToJob(@Field("job_id") int jobId, @Field("proposal_description") String desc);

    /**
     * Submit interview report
     */
//    @POST
//    Call<ResponseBody> submitReport(@Url String url, @Body FeedbackPojo pojo);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> doRecording(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * Cancel interview
     */
    @POST("/interview_schedule/cancel_interview")
    Call<ResponseBody> cancelInterview(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/accept_terms")
    Call<ResponseBody> acceptTerms(@Field("user_id") int id);

    @POST("/accept_terms")
    Call<ResponseBody> acceptTerms(@Query("interview_code") String intCode, @Query("passcode") String passcode);

//    @FormUrlEncoded
//    @POST("/company/company_update")
//    Call<ResponseBody> updateCompany(@FieldMap Map<String, Object> params);

//    @POST("/company/company_update")
//    Call<ResponseBody> updateCompany(@Body CompanyPojo companyUpdate);

    @POST(UtilsServer.START_INTERVIEW)
    Call<ResponseBody> finishInterview(@Query("interview_code") String interview_code);


    @POST("users/forgot_password")
    Call<ResponseBody> forgotPassword(@Query("email") String email);

    @POST("/interviews/revert_report")
    Call<ResponseBody> sendComments(@QueryMap Map<String, Object> params);

    /**
     * Interview report calls*/
    /**
     * Add skill to report
     */
    @POST(UtilsServer.REPORT_ADD_SKILL)
    Call<ResponseBody> addReportSkill(@Body String skill, @Header("Content-type") String val);

    @POST(UtilsServer.REPORT_REMOVE_SKILL)
    Call<ResponseBody> removeReportSkill(@Query("interview_report_id") int id, @Query("interview_report_skill_id") int skillId);

    @POST("/reports/add_report_signature")
    Call<ResponseBody> sendSignature(@QueryMap Map<String, Object> params);


    @POST("/reports/add_professional_background")
    Call<ResponseBody> submitProfessionalBackground(@QueryMap Map<String, Object> params);


    /**
     * Add slot for candidate
     */
    @POST(UtilsServer.CAN_ADD_SLOT)
    Call<ResponseBody> addCandidateSlot(@QueryMap Map<String, Object> params);

    /**
     * Save the slots and proceed to the further
     */
    @POST(UtilsServer.CAN_SAVE_AND_PROCEED)
    Call<ResponseBody> saveAndProceed(@Query("interview_code") String interview_code);

    /**
     * Interviewer pick the slot for interview
     */
    @POST(UtilsServer.INT_PICK_SCHEDULE)
    Call<ResponseBody> pickSlotAndScheduleInterview(@QueryMap Map<String, Object> params);

    /**
     * Remind recruiter about job proposal
     */
    @FormUrlEncoded
    @POST(UtilsServer.INT_REMIND)
    Call<ResponseBody> remindRecruiter(@Field("job_id") int jobId);

    /**
     * Remind Interviewer about job request
     */
    @FormUrlEncoded
    @POST(UtilsServer.INT_REMIND_JOB_REQUEST)
    Call<ResponseBody> remindInterviewer(@Field("job_id") int jobId, @Field("interviewer_id") int intId);

    /**
     * Accept or reject job proposal from job details page
     */
    @POST
    Call<ResponseBody> acceptOrRejectJobProposal(@Url String url, @Query("job_proposal_id") Object id);

    /**
     * Reminds join company request.
     */
    @POST(UtilsServer.REMIND_JOIN_COMPANY_REQUEST)
    Call<ResponseBody> remindJoinExistiingCompanyRequest();

    /**
     * Deletes available slot.
     *
     * @param slotId selected slot id
     */
    @POST("/candidates/delete_available_slots")
    Call<ResponseBody> deleteSlot(@Query("slot_id") long slotId);

    /**
     * Reschedule a slot with given slot id.
     *
     * @param params map containing slot id and reason for rescheduling the slot.
     * @return
     */
    @POST("/request_reschedule_interview")
    Call<ResponseBody> rescheduleSlot(@QueryMap Map<String, String> params);

    /**
     * Reschedules interview for the recruiter.
     *
     * @param params map containing interview id and due date.
     * @return
     */
    @POST("/recruiter_reschedule_request")
    Call<ResponseBody> rescheduleInterviewForRecruiter(@QueryMap Map<String, String> params);


    @POST("/recruiters/authorize_preapproval")
    Call<ResponseBody> authorizePreApproval(@Query("job_id") String job_id);


    @POST("/recruiters/validate_preapproval")
    Call<ResponseBody> validatePreApproval(@Query("job_id") String job_id);


    @POST(UtilsServer.PAY_INTERVIEW)
    Call<ResponseBody> payInterview(@Query("interview_id") String interview_id);

    @POST(UtilsServer.RESEND_OTP)
    Call<ResponseBody> resendOTP();


    @POST(UtilsServer.UPDATE_SMS_SERVICE)
    Call<ResponseBody> updateSmsService(@QueryMap Map<String, Object> params);


    @POST(UtilsServer.VERIFY_OTP)
    Call<ResponseBody> verifyOTP(@Query("otp_code") String otp);

    @GET(UtilsServer.INT_SCHEDULE)
    Call<ResponseBody> validatePassCode(@Query("passcode") String passcode, @Query("interview_code") String intCode);


    /**
     * GET Requests
     * /**
     * Get Profile details
     */
    @GET(UtilsServer.USER_PROFILE_SHOW)
    Call<ResponseBody> getProfileDetails(@Query("role") String role);

    /**
     * Get My Jobs
     *
     * @param params filtering options
     */
    @GET(UtilsServer.REC_MY_JOBS)
    Call<ResponseBody> getMyJobs(@QueryMap Map<String, Object> params);

    /**
     * Get interviewer job
     *
     * @param params interviewer id
     */
    @GET(UtilsServer.REC_INT_JOBS)
    Call<ResponseBody> getInterviewerJobs(@QueryMap Map<String, Object> params);

    /**
     * Get JOB details
     *
     * @param job_id id of desired job
     */
    @GET(UtilsServer.REC_JOB_DET_NEW)
    Call<ResponseBody> getJobDetails(@Query("job_id") int job_id);

    /**
     * Get Edit Job details
     *
     * @param job_id id of desired job
     */
    @GET(UtilsServer.REC_JOB_EDIT_DET)
    Call<ResponseBody> getEditJobDetails(@Query("job_id") int job_id);

    /**
     * Get List of interviewers
     */
    @GET(UtilsServer.USER_SEARCH_INT)
    Call<ResponseBody> getInterviewers(@QueryMap Map<String, Object> params);

    /**
     * Get My interviews
     */
    @GET(UtilsServer.REC_DASHBOARD)
    Call<ResponseBody> getRecDashBoard(@QueryMap Map<String, Object> params);

    /**
     * get public profile of interviewer
     *
     * @param interviewer_id int id
     */
    @GET(UtilsServer.USER_PUBLIC_PROFILE)
    Call<ResponseBody> getPublicProfile(@Query("interviewer_id") int interviewer_id);

    /**
     * get participants for job id
     *
     * @param job_id job id
     */
    @GET(UtilsServer.INT_QUICK_SCHEDULE_PART)
    Call<ResponseBody> getParticipants(@Query("job_id") int job_id);

    /**
     * Get interviewer
     */
    @GET
    Call<ResponseBody> getInterviewDetails(@Url String url, @Query("interview_code") String int_code);

    /**
     * Get interview report
     */
    @GET(UtilsServer.INT_GET_FB)
    Call<ResponseBody> getIntReport(@Query("interview_report_id") int interviewer__report_id);

    /**
     * Get Candidate details
     */
    @GET(UtilsServer.CANDIDATE_DETAILS)
    Call<ResponseBody> getCanDetails(@Query("candidate_id") int id);

    /**
     * Get Scheduled interview Details
     */
    @GET("interviews/edit_interview")
    Call<ResponseBody> getInterviewDetails(@Query("interview_id") int interviewer_id);

    /**
     * Get scheduled interviews for particular candidate
     */
    @GET(UtilsServer.CANDIDATE_SCHEDULED_INERVIEWS)
    Call<ResponseBody> getScheduledInterviews(@QueryMap Map<String, Object> params);

    /**
     * fetch Notifications for user
     */
    @GET(UtilsServer.INT_NOTIFICATIONS)
    Call<ResponseBody> getNotifications(@Query("role") String role, @Query("page") int page, @Query("per_page") int per_page);

    @GET(UtilsServer.INT_JOBSEARCH)
    Call<ResponseBody> searchJobs(@QueryMap Map<String, Object> params);

    /***
     * Notifications services
     **/
    @POST
    Call<ResponseBody> notificationAction(@Url String url, @Query("notification_id") Object notification_id);

    @GET("/interviewers/engaged_jobs")
    Call<ResponseBody> engagedJobs(@QueryMap Map<String, Object> params);


    /**
     * Finish interview when interviewer leaves the conference
     */
    @POST(UtilsServer.END_CALL)
    Call<ResponseBody> endCall(@Query("interview_code") String interview_code, @Query("passcode") String passcode);

    @GET
    Call<ResponseBody> getFeedback(@Url String url);


    @GET("/company_autocomplete")
    Call<ResponseBody> getCompanyNames(@Query("name") String name);

    @POST("/company/join_existing_company")
    Call<ResponseBody> joinExistingCompany(@Query("name") String name);

    @GET("/users/get_full_name")
    Call<ResponseBody> getFullName(@Query("user_id") String id);

    @GET(UtilsServer.COMPANY_AVAILABILITY)
    Call<ResponseBody> checkAvailability(@Query("company_name") String name);

    @GET(UtilsServer.CAN_INTERVIEWR)
    Call<ResponseBody> getCandidateInterviews(@QueryMap Map<String, Object> params);

    @GET("/users/resend_confirmation")
    Call<ResponseBody> resendConfirmation(@Query("email") String email);


    @POST("/recruiters/submit_report_comments")
    Call<ResponseBody> postComment(@QueryMap Map<String, Object> params);

    @GET("reports/download_pdf")
    Call<ResponseBody> downLoadPdf(@Query("report_id") int id);


    @GET(UtilsServer.CAN_TIME_SLOTS)
    Call<ResponseBody> getCandidateTimeSlots(@Query("interview_code") String iCode, @Query("candidate_id") String candidate_id);

    @GET(UtilsServer.INT_TIME_SLOTS)
    Call<ResponseBody> getInterviewTimeSlots(@Query("interview_code") String iCode);

    @GET(UtilsServer.GET_BASIC_JOB_DETAILS)
    Call<ResponseBody> getBasicJobDetails(@Query("job_id") int job_id);

    @GET(UtilsServer.GET_CONTACTS)
    Call<ResponseBody> getContacts();


    @GET(UtilsServer.GET_CANDIDATE_DETAILS)
    Call<ResponseBody> getCandidateDetails(@Query("candidate_id") int id);

    @GET(UtilsServer.GET_CANCELLATION_REASON)
    Call<ResponseBody> getCancellationReason(@Query("interview_id") int id);

    @POST(UtilsServer.ADD_EMAIL_ID_FOR_SHARING_INTERVIEW_REPORT)
    Call<ResponseBody> addEmailId(@Query("email") String email, @Query("job_id") String code);

    @POST("interview_access/remove_interview_access")
    Call<ResponseBody> deleteEmail(@Query("email") String email, @Query("job_id") String code);

    @GET
    Call<ResponseBody> getNumberOfInterviewsConducted(@Url String url, @Query("start_date") String startdate, @Query("end_date") String enddate, @Query("given_by") String given_by);

    @GET
    @Streaming
    Call<ResponseBody> getNumberOfInterviewsConductedFile(@Url String url, @Query("start_date") String startdate, @Query("end_date") String enddate, @Query("given_by") String given_by);

    @GET("/get_counter_time")
    Call<ResponseBody> getCounterTime(@Query("interview_code") String interview_code, @Query("passcode") String passcode);

    @POST("/mark_no_show")
    Call<ResponseBody> markAsNoShow(@Query("interview_code") String interview_code, @Query("passcode") String passcode);

    @POST("/start_no_show_recording")
    Call<ResponseBody> startNoShowRecording(@Query("interview_code") String interview_code, @Query("passcode") String passcode);

    @GET("/interview_access/list_interview_access")
    Call<ResponseBody> getSharedEmails(@Query("job_id") String job_id);

    @POST("/profile/reset_recruiter_report_passcode")
    Call<ResponseBody> resetPasscode();

    @GET("/candidates/get_candidate_interview_details")
    Call<ResponseBody> getRequesetedInterviewDetails(@Query("candidate_id") String candidate_id);

//    @POST("/interviewer/create_interest")
//    Call<ResponseBody> markInterest(@Body CreateInterestPojo params);

    @GET("check_reschedule_interview_time")
    Call<ResponseBody> checkRescheduleInterviewTime(@Query("interview_id") String id);

//    @POST("/candidates/submit_the_time_slots")
//    Call<ResponseBody> addCandidateTimeSlots(@Body CandidateSlot candidateSlot);

    @GET("/recruiters/get_recruiter_details")
    Call<ResponseBody> getRecruiterDetails();

    @POST("/interviews/reject_automated_interview")
    Call<ResponseBody> rejectAutomatedInterview(@Query("interview_id") String id, @Query("reason") String reason);

    @GET("/interview_interests/get_job_interest_details")
    Call<ResponseBody> getInterestsForJob(@Query("job_id") String job_id, @Query("interviewer_id") String interviewer_id);

    @GET("/recruiters/get_interests_list")
    Call<ResponseBody> getInterestList(@Query("candidate_id") String candidateId);

//    @POST("/interview_interests/accept_multiple_interests")
//    Call<ResponseBody> acceptMultipleInterestsByRecruiter(@Body SelectedInterests selectedInterests);

    @GET("/grievances/list_grievances")
    Call<ResponseBody> getGrievances(@QueryMap Map<String, Object> map);

    @POST("/candidates/update_auto_source")
    Call<ResponseBody> updateAutoSource(@Query("candidate_id") String candidate_id, @Query("auto_source") boolean autoSource);

    @POST
    Call<ResponseBody> addOrRemoveFavourite(@Url String url, @Query("interviewer_id") String interviewer_id);

    @POST("interview_interests/accept_interest")
    Call<ResponseBody> acceptSinleInterest(@Query("interest_id") String interestId);

    @GET("/grievances/get_grievance_record")
    Call<ResponseBody> getGrievanceRecord(@Query("interview_id") String interview_id);

    @POST("/grievances/create_grievance")
    Call<ResponseBody> createGrievance(@Query("interview_id") String interview_id, @Query("grievance_message") String grievance_message);

    @GET("/recruiters/my_interviewers")
    Call<ResponseBody> getMyInterviewersData(@QueryMap Map<String, Object> params);

//    @POST("recruiters/schedule_interview")
//    Call<ResponseBody> scheduleAutoInterview(@Body SceduleInterviewPojo scheduleInterviewPojo);
//
//    @POST("recruiters/schedule_hybrid_interview")
//    Call<ResponseBody> scheduleHybridInterview(@Body SceduleInterviewPojo scheduleInterviewPojo);

    @GET("/candidates/my_jobs")
    Call<ResponseBody> getCandidateMyJobs(@QueryMap Map<String, Object> paramsInMap);

    @POST("payments/pay_interview")
    Call<ResponseBody> getPaymentDetails(@Query("interview_id") String interview_id);

    @POST("payments/confirm_interview_payment_mobile")
    Call<ResponseBody> confirmInterviewPayment(@Query("interview_id") String interview_id, @Query("paykey") String paykey);

    @GET("/skillset_autocomplete")
    Call<ResponseBody> getSkills(@Query("skill") String key);

    @GET("/users/email_availability")
    Call<ResponseBody> checkEmailAvailability(@Query("email") String email);

    @POST("/comments/create_comment")
    Call<ResponseBody> addComments(@Query("interview_report_id") String intId, @Query("content") String comnt);

    @POST("/reactivate_job")
    Call<ResponseBody> reactivateJob(@Query("job_id") String job_id, @Query("due_at") String dueDate);

    @GET("/subscriptions/list_subscription_packages")
    Call<ResponseBody> getSubscriptionPackages();

    @POST("/subscriptions/add_free_subscription")
    Call<ResponseBody> subscribePackage(@Query("subscription_package_id") String subscription_id);

    @GET("/interview_proposal_details")
    Call<ResponseBody> getBaseDetails(@Query("interview_proposal_id") String interview_proposal_id);

    @POST("/confirm_interview_proposal")
    Call<ResponseBody> acceptRreject(@Query("status") String status, @Query("interview_proposal_id") String interview_proposal_id);

    @GET("/profile/recruiter_profile_details")
    Call<ResponseBody> getProfileDetails();

    @POST("/interviewer_note")
    Call<ResponseBody> postNotes(@Query("note") String note, @Query("passcode") String passcode, @Query("interview_code") String interview_code);

    @GET("/get_interview_note")
    Call<ResponseBody> getNotes(@Query("passcode") String passcode, @Query("interview_code") String interview_code);

    @GET("recruiter/interview_due_details")
    Call<ResponseBody> getDueDetails(@Query("interview_id") String interview_id);

    @GET("/version_validation")
    Call<ResponseBody> versionValidation(@Query("version") String version);

    @POST("/accept_license_agreement")
    Call<ResponseBody> acceptAgreementByUploadingFile(@Query("agreement_url") String fileName);


//    @POST("/accept_license_agreement")
//    Call<ResponseBody> acceptAgreement(@Body AgreementParams agreementParams);
//
//    @GET("/interviewer/training_videos")
//    Call<ResponseBody> getTrainingVideos();
//
//    @POST("/interviewer/update_watched_video")
//    Call<ResponseBody> updateWatchedVideo(@Query("training_video_id") String video_id, @Query("watched") boolean watched);
//
//    @POST("/recruiter/self_auto_match")
//    Call<ResponseBody> scheduleSelfInterview(@Body SceduleInterviewPojo scheduleSelfInterviewpojo);

    @GET("/interviewers/pending_reports")
    Call<ResponseBody> getPendingReports(@Query("page") String page, @Query("per_page") String per_page);

    @GET("recruiter/pending_payment_interviews")
//    recruiters/interview_cancellation_payments
    Call<ResponseBody> getInterviewPayments(@QueryMap Map<String, Object> params);

    @GET("recruiter/pending_payment_interviews")
    Call<ResponseBody> getPendingPaymentInterviews(@QueryMap Map<String, Object> params);

    @GET("recruiters/interview_cancellation_payments")
    Call<ResponseBody> getCancelledInterviews(@QueryMap Map<String, Object> params);


    @GET("recruiters/payments_list")
    Call<ResponseBody> getOutstandingInterviews(@QueryMap Map<String, Object> params);


//    @GET("interview_payment_details")
//    Call<ResponseBody> getInterviewPaymentDetails(@Query("interview_id") String id);
//
//    @POST("/recruiter/create_profile")
//    Call<ResponseBody> createProfile(@Body SinglePageProfilePojo pojo);
//
//    @GET("/validate_phone")
//    Call<ResponseBody> validatePhoneNumber(@Query("phone") String phoneNumber);
//
////    Call<ResponseBody> suspectProxy(@Query("interview_code") String interview_code, @Query("passcode")  String pass_code);
//
//    @POST("/mark_candidate_proxy")
//    Call<ResponseBody> suspectProxy(@Body SuspectProxyPojo pojo);

    @POST("/jobs/accept_proposal")
    Call<ResponseBody> notificationActions(@Url String url, @Query("notification_id") Object notification_id);

    @GET("/get_interview_status")
    Call<ResponseBody> getInterviewStatus(@Query("interview_code") String interview_code);

    @GET("/interviewers/ratings")
    Call<ResponseBody> getMyRatings(@QueryMap Map<String, Object> params);

    @POST("/get_sharing_mail")
    Call<ResponseBody> getSharingEmail(@Query("interview_code") String interview_code);

    @POST("/interviews/close_interview")
    Call<ResponseBody> closeTimeOutInterview(@Query("interview_code") String interview_code);

    @GET("/recruiters/get_completed_candidates")
    Call<ResponseBody> getCompletedCandidates(@QueryMap Map<String, Object> paramsInMap);

//    @POST("/candidates/update_hiring_status")
//    Call<ResponseBody> updateHiringStatus(@Body InterviewAnalyticsPojo pojo);
//
//    @POST("/candidates/update_hiring_status")
//    Call<ResponseBody> updateHiringStatuss(@Body CandidateHiringStatusPojo pojo);
//
//    @GET("/get_candidate_roi_details")
//    Call<ResponseBody> getCandidateROIDetails(@Query("candidate_id") String candidate_id);
//
//    @POST("/candidate/update_withdraw_status")
//    Call<ResponseBody> updateWithdrawStatus(@Body JobPojo obj);
//
//    @POST("/confirm_interview_proposal")
//    Call<ResponseBody> interviewerAcceptedSlot(@Query("interview_proposal_id") String str, @Query("slot_id") String str2,
//                                               @Query("status") String str3, @Query("interview_time") String str4, @Query("reject_reason") String str5);
//    @GET("/interviewers/time_slots")
//    Call<ResponseBody> getInterviewersSlots(@Query("interview_proposal_id") String relatable_id);
//
//    @POST("/profile/update_billing_address")
//    Call<ResponseBody> updateBillingAddress(@Body AddressPojo pojo);

    @GET("/company_payments/invoice_details")
    Call<ResponseBody> getInvoiceDetails(@Query("invoice_id") String invoice_id);

    // Offers
    @POST("/create_user")
    Call<ResponseBody> userRegister(@Query("phone_number") String phone_number, @Query("name") String name, @Query("email") String email);

//Create Busineess account
    @POST("/create_baccount")
    Call<ResponseBody> createBusinessAccountServer(@Body BusinessAccountPojo pojo);

    @POST("/login")
    Call<ResponseBody> loginIntoBusinessAccountServer(@Query("phone_number") String phone_num);

    @POST("/send_otp")
    Call<ResponseBody> sendOtpToServer(@Query("otp") String num);
}
