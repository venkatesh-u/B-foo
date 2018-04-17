package com.venkatesh.businessoffers.pojos;

import java.io.File;

/**
 * Created by venkatesh on 4/4/18.
 */

public class CouponsPojo {

    public String title, description,  token, business_account_id, id;
    public File avatar;
    public Coupon coupan;


    class Coupon{

        public String title, description, business_account_id;
        public File avatar;
    }

}
