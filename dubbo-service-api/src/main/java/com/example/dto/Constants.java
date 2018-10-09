package com.example.dto;

public final class Constants {

    /**
     *渠道类型 0默认值代表不限渠道
     */
    public static final String CHANNEL_TYPE_UNLIMITED= "0";
    /**
     *
     *渠道类型 2默认值代表手机端
     */
    public static final String CHANNEL_TYPE_MOBILE= "2";


    /**
     * 图片规格尺寸
     */
    public interface ProductPicSize {
        /**
         * 1-微型规格
         */
        public static final String MIN = "1";

        /**
         * 2-小图规格
         */
        public static final String SMALL = "2";

        /**
         * 3-中型规格
         */
        public static final String MEDIUM = "3";

        /**
         * 4-大型规格
         */
        public static final String LARGE = "4";

        /**
         * 5-原图规格
         */
        public static final String ORIGINAL = "5";
    }



}
