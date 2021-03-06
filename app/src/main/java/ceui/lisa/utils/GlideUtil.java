package ceui.lisa.utils;

import android.text.TextUtils;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.util.HashMap;

import ceui.lisa.models.UserBean;
import ceui.lisa.models.IllustsBean;

public class GlideUtil {

    private static final Headers sHeaders = () -> {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Params.MAP_KEY, Params.IMAGE_REFERER);
        hashMap.put(Params.USER_AGENT, Params.PHONE_MODEL);
        hashMap.put(Params.HOST, Params.HOST_NAME);
        return hashMap;
    };

    public static GlideUrl getMediumImg(IllustsBean illustsBean) {
        Common.showLog("展示image " + illustsBean.getImage_urls().getMedium());
        return new GlideUrl(illustsBean.getImage_urls().getMedium(), sHeaders);
    }

    public static GlideUrl getMediumImg(String imageUrl) {
        return new GlideUrl(imageUrl, sHeaders);
    }

    public static GlideUrl getArticle(String url) {
        return new GlideUrl(url, sHeaders);
    }

    public static GlideUrl getLargeImage(IllustsBean illustsBean) {
        return new GlideUrl(illustsBean.getImage_urls().getLarge(), sHeaders);
    }

    public static GlideUrl getHead(UserBean userBean) {
        if (userBean == null) {
            Common.showLog("getHead userBean == null");
            return null;
        }

        if (userBean.getProfile_image_urls() == null) {
            Common.showLog("getHead userBean.getProfile_image_urls == null");
            return null;
        }

        if (!TextUtils.isEmpty(userBean.getProfile_image_urls().getMaxImage())) {
            Common.showLog("getHead userBean.getProfile_image_urls.getMaxImage " +
                    userBean.getProfile_image_urls().getMaxImage());
            return new GlideUrl(userBean.getProfile_image_urls().getMaxImage(), sHeaders);
        }

        return null;
    }


    public static GlideUrl getSquare(IllustsBean illustsBean) {
        return new GlideUrl(illustsBean.getImage_urls().getSquare_medium(), sHeaders);
    }

    public static GlideUrl getLargeImage(IllustsBean illustsBean, int i) {
        Common.showLog("GlideUrl getLargeImage");
        if (illustsBean.getPage_count() == 1) {
            return getLargeImage(illustsBean);
        } else {
            return new GlideUrl(illustsBean.getMeta_pages().get(i).getImage_urls().getLarge(), sHeaders);
        }
    }


    public static GlideUrl getOriginal(IllustsBean illustsBean, int i) {
        Common.showLog("GlideUrl getOriginal");
        if (illustsBean.getPage_count() == 1) {
            return new GlideUrl(illustsBean.getMeta_single_page().getOriginal_image_url(), sHeaders);
        } else {
            return new GlideUrl(illustsBean.getMeta_pages().get(i).getImage_urls().getOriginal(), sHeaders);
        }
    }
}
