package com.example.myapplication;

import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Constants  {
    public static final String Base_Url="https://soulmatepa.000webhostapp.com/login.php";
    public static final String signup_Url="https://soulmatepa.000webhostapp.com/signup.php";
    public static final String FETCH_URL="https://soulmatepa.000webhostapp.com/fetchall.php";
    public static final String CHANGE_FORGOT_EMAILPASSWORD="https://soulmatepa.000webhostapp.com/changeforgotpassword.php";
    public static final String FETCH_URL_with_progress="https://soulmatepa.000webhostapp.com/emptyfields2.php";
    public static final String FETCH_URL_PROFILEID="https://soulmatepa.000webhostapp.com/fetchallwithprofileid.php";
    public static final String FETCH_URL_PROFILEID_altered="https://soulmatepa.000webhostapp.com/fetchallwithprofileidaltered.php";
    public static final String MATCH_WITH_CITY_URL="https://soulmatepa.000webhostapp.com/matchwithcitydef234.php";
    public static final String SHORTLIST_PROFILEID="https://soulmatepa.000webhostapp.com/shortlistselection.php";
    public static final String PROFILEVIEW_PROFILEID="https://soulmatepa.000webhostapp.com/profileviewselection.php";
    public static final String MATCHES_PROFILEID="https://soulmatepa.000webhostapp.com/matchesselection.php";
    public static final String REQUEST_PROFILEID="https://soulmatepa.000webhostapp.com/requestselection.php";
    public static final String SHORTLIST_PROFILEID_DATA="https://soulmatepa.000webhostapp.com/shortlistfetchall.php";
    public static final String SEARCHBAR="https://soulmatepa.000webhostapp.com/searchfetchall.php";
    public static final String PROFILEVIEW_PROFILEID_DATA="https://soulmatepa.000webhostapp.com/profileviewfetchall.php";
    public static final String REQUEST_PROFILEID_DATA="https://soulmatepa.000webhostapp.com/shortlistfetchall.php";
    public static final String ADD_TO_MATCH="https://soulmatepa.000webhostapp.com/addtomatch.php";
    public static final String SHORTLIST_DELETE_USER="https://soulmatepa.000webhostapp.com/shortlistdeletion.php";
    public static final String PROFILEVIEW_DELETE_USER="https://soulmatepa.000webhostapp.com/profileviewdeletion.php";
    public static final String REQUEST_DELETE_USER="https://soulmatepa.000webhostapp.com/requestdeletion.php";
    public static final String IMAGE_UPLOAD_URL = "https://soulmatepa.000webhostapp.com/imageupload.php";
    public static final String EXTRAS_FAMILY_SIGNUP_URL = "https://soulmatepa.000webhostapp.com/signup2.php";
    public static final String EXTRAS_APPEARANCE_SIGNUP_URL = "https://soulmatepa.000webhostapp.com/signup3.php";
    public static final String EXTRAS_SIGNUP_URL_FULL = "https://soulmatepa.000webhostapp.com/signup4.php";
    public static final String FETCH_WITH_NAME = "https://soulmatepa.000webhostapp.com/fetchwithname.php";
    public static final String ADD_TO_SHORTLIST = "https://soulmatepa.000webhostapp.com/shortlist.php";
    public static final String ADD_TO_REQUEST = "https://soulmatepa.000webhostapp.com/request.php";
    public static final String ADD_TO_PROFILEVIEW = "https://soulmatepa.000webhostapp.com/profileview.php";
    public static final String ADD_TO_LOOKINGFOR = "https://soulmatepa.000webhostapp.com/signuplookingfor.php";
    public static final String Update_social = "https://soulmatepa.000webhostapp.com/updatesocialdetails.php";
    public static final String Update_lookingfor = "https://soulmatepa.000webhostapp.com/updatelookingfor.php";
    public static final String Update_family = "https://soulmatepa.000webhostapp.com/updatefamily.php";
    public static final String Update_personaldetails = "https://soulmatepa.000webhostapp.com/updatepersonaldetails.php";
    public static final String Update_logindetails = "https://soulmatepa.000webhostapp.com/updatelogindetails.php";
    public static final String Update_careerdetails = "https://soulmatepa.000webhostapp.com/updatecareerdetails.php";
    public static final String Update_Appearance = "https://soulmatepa.000webhostapp.com/updateappearance.php";
    public static final String Change_password = "https://soulmatepa.000webhostapp.com/changepassword.php";
    public static final String AddtoSubscription = "https://soulmatepa.000webhostapp.com/addtosubscription.php";
    public static final String Check_Subscription = "https://soulmatepa.000webhostapp.com/fetchpremium.php";
    public static final String Add_to_photolock = "https://soulmatepa.000webhostapp.com/addtophotolock.php";
    public static final String Delete_from_photolock = "https://soulmatepa.000webhostapp.com/deletefromphotolock.php";
    public static final String fetch_from_photolock = "https://soulmatepa.000webhostapp.com/photolockfetchall.php";
    public static final String lockimageurl="https://soulmatepa.000webhostapp.com/images/lock.jpg";
    public static final String lockimageurlboy="https://soulmatepa.000webhostapp.com/images/boyblock.png";
    public static final String lockimageurlgirl="https://soulmatepa.000webhostapp.com/images/girlblock.png";
    public static final String Request_photo="https://soulmatepa.000webhostapp.com/addtophotolockpermission.php";
    public static final String Request_photo_match="https://soulmatepa.000webhostapp.com/photorequestselection.php";
    public static final String Request_photo_delete="https://soulmatepa.000webhostapp.com/photorequestdeletion.php";
    public static final String Request_photo_accept="https://soulmatepa.000webhostapp.com/photorequestaccept.php";
    public static final String Request_number="https://soulmatepa.000webhostapp.com/addtonumberlockpermission1.php";
    public static final String Delete_subscription="https://soulmatepa.000webhostapp.com/deletefromsubscription.php";
    public static final String Get_Usage_Info="https://soulmatepa.000webhostapp.com/info.php";
    public static final String ADD_TO_LIKES = "https://soulmatepa.000webhostapp.com/addtolikes.php";
    public static final String LIKES_PROFILEID="https://soulmatepa.000webhostapp.com/likesselection.php";
    public static final String LIKES_DELETE_USER="https://soulmatepa.000webhostapp.com/likesdeletion.php";
    public static final int PLACE_PICKER_REQUEST = 1;
    public static final String ADDRESS_INTENT = "Nadiad";
    public static  String selected_user;

    public void addtolikes(String toprofileid,String fromprofileid) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("toprofileid", toprofileid);
        rp.put("fromprofileid", fromprofileid);
        client.post(Constants.ADD_TO_LIKES, rp, new JsonHttpResponseHandler() {
        });
    }
    public void addtoprofileview(String  toprofileid,String fromprofileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("toprofileid",toprofileid);
        rp.put("fromprofileid",fromprofileid);
        client.post(Constants.ADD_TO_PROFILEVIEW,rp,new JsonHttpResponseHandler(){
        });
    }

}





