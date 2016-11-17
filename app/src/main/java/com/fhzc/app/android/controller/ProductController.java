package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.DividendRemendModel;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.models.ProductModels;
import com.fhzc.app.android.models.RightModels;
import com.fhzc.app.android.models.SuggestAssetsModel;
import com.fhzc.app.android.utils.net.ConnectTool;

/**
 * Created by yanbo on 2016/7/22.
 */
public class ProductController {
    private static ProductController instance;


    public static ProductController getInstance() {
        if (instance == null)
            instance = new ProductController();
        return instance;
    }

    public void productList(ViewNetCallBack callBack,int page) {
        try {
            ConnectTool.httpRequest(HttpConfig.productList, new MapBuilder<String, Object>()
                    .add("page", page)
                    .getMap(), callBack, ProductModels.class);
        } catch (Exception e) {
        }
    }

    public void getProductTypeList(ViewNetCallBack callBack,int cid ,int page) {
        try {
            ConnectTool.httpRequest(HttpConfig.getProductTypeList, new MapBuilder<String, Object>()
                    .add("cid",cid).add("page", page)
                    .getMap(), callBack, ProductModels.class);
        } catch (Exception e) {
        }
    }

    public void getProductType(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(HttpConfig.getProductType, new MapBuilder<String, Object>()
                    .getMap(), callBack, ProductModels.class);
        } catch (Exception e) {
        }
    }

    public void getProductDetail(ViewNetCallBack callBack,int productId,int customerId) {
        try {
            ConnectTool.httpRequest(HttpConfig.getProductDetail, new MapBuilder<String, Object>()
                    .add("productId",productId).add("customerId",customerId)
                    .getMap(), callBack, ProductModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 预约产品
     *
     * @param callBack
     * @param productId
     * @param customerId
     * @param plannerId
     * @param amount
     */
    public void orderProduct(ViewNetCallBack callBack, int productId, int customerId, int plannerId, int amount) {
        try {
            ConnectTool.httpRequest(HttpConfig.orderProduct, new MapBuilder<String, Object>()
                    .add("productId", productId)
                    .add("customerId", customerId)
                    .add("plannerId", plannerId)
                    .add("amount", amount)
                    .getMap(), callBack, String.class);
        } catch (Exception e) {
        }
    }

    /**
     * 取消预约产品
     *
     * @param callBack
     * @param id
     */
    public void cancelOrderProduct(ViewNetCallBack callBack, int id) {
        try {
            ConnectTool.httpRequest(HttpConfig.cancelOrderProduct, new MapBuilder<String, Object>()
                    .add("id", id)
                    .getMap(), callBack, String.class);
        } catch (Exception e) {
        }
    }

    /**
     * 我预约的产品
     *
     * @param callBack
     */
    public void myOrderProduct(ViewNetCallBack callBack,int uid) {
        try {
            ConnectTool.httpRequest(HttpConfig.myOrderProduct, new MapBuilder<String, Object>()
                    .add("customer_id", uid)
                    .getMap(), callBack, ProductModel.class);
        } catch (Exception e) {
        }
    }

    /**
     * 获取商品详情
     */

    public void getProduceDetail(ViewNetCallBack callBack, int pId) {
        try {
            ConnectTool.httpRequest(HttpConfig.produceDetail, new MapBuilder<String, Object>()
                    .add("productId", pId)
                    .getMap(), callBack, ProductModel.class);
        } catch (Exception e) {
        }
    }

    /**
     * 关注产品
     */
    public void focusProduct(ViewNetCallBack callBack, int productId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", productId)
                    .add("ftype", "product")
                    .add("status", 1)

                    .getMap(), callBack, RightModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 取消关注产品
     */

    public void cancelFocusProduct(ViewNetCallBack callBack, int productId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", productId)
                    .add("ftype", "product")
                    .add("status", 0)

                    .getMap(), callBack, RightModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 精选基金
     */

    public void selectProduct(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(HttpConfig.selectProduct, new MapBuilder<String, Object>()

                    .getMap(), callBack, ProductModels.class);
        } catch (Exception e) {
        }
    }
    public void selectProduct(ViewNetCallBack callBack,int page) {
        try {
            ConnectTool.httpRequest(HttpConfig.selectProduct, new MapBuilder<String, Object>()
                    .add("page", page)
                    .getMap(), callBack, ProductModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 我的资产
     */
    public void myProduct(ViewNetCallBack callBack,int uid) {
        try {
            ConnectTool.httpRequest(HttpConfig.myAssets, new MapBuilder<String, Object>()
                    .add("customer_id", uid)
                    .getMap(), callBack, MyProductModel.class);
        } catch (Exception e) {
        }
    }

    /**
     * 推荐配置比例
     */
    public void suggestAssets(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(HttpConfig.suggestAssets, new MapBuilder<String, Object>()
                    .getMap(), callBack, SuggestAssetsModel.class);
        } catch (Exception e) {
        }
    }
    /**
     * 客户某一产品的派息记录
     */
    public void DividendDate(ViewNetCallBack callBack,int productId) {
        try {
            ConnectTool.httpRequest(HttpConfig.dividend, new MapBuilder<String, Object>()
                    .add("customerId", UserPreference.getRoleId())
                    .add("productId", productId)
                    .getMap(), callBack, DividendRemendModel.class);
        } catch (Exception e) {
        }
    } /**
     * 客户某一产品的兑付记录
     */
    public void Redmemption(ViewNetCallBack callBack,int productId) {
        try {
            ConnectTool.httpRequest(HttpConfig.redemption, new MapBuilder<String, Object>()
                    .add("customerId",  UserPreference.getRoleId())
                    .add("productId", productId)
                    .getMap(), callBack, DividendRemendModel.class);
        } catch (Exception e) {
        }
    }

}
