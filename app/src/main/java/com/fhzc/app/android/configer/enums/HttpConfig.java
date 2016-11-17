package com.fhzc.app.android.configer.enums;


/**
 * HttpConfig
 */
public enum HttpConfig {
    /**
     * 消息轮询
     */
    yapull(1),
    publish(2),
    audioContent(3),
    activityList(4),
    activityDetail(5),
    /**
     * 新用户登录
     */
    loginNewUser(6),
    /**
     * 老用户登录
     */
    loginOldUser(7),
    productList(8),
    setPsw(9),
    reportList(10),
    rightList(11),
    /**
     * 首页
     */
    index(12),
    orderProduct(13),
    cancelOrderProduct(14),
    exchangeRight(15),
    cancelExchangeRight(16),
    joinActivity(17),
    cancelJoinActivity(18),
    /**
     * 用户信息
     */
    userInfo(19),
    myOrderProduct(20),
    myOrderRight(21),
    myCollection(22),
    myOrderActivity(23),
    focus(24),
    rightDetail(25),
    produceDetail(26),
    focusStatus(27),
    selectRight(28),
    mine(29),
    selectProduct(30),
    pointRecord(31),
    customerList(32),
    myBank(33),
    pointRecordDetail(34),
    preModify(35),
    resetPsw(36),
    reportDetail(37),
    loginExist(38),
    modLogin(39),
    myAssets(40),
    shareForCostomer(41),
    customerDetail(42),
    changeCustomserRemark(43),
    noFocusOther(44),
    plannerDetail(45),
    suggestAssets(46),
    getSms(47),
    bindDevice(48),
    systemNotice(49),
    latestApp(50),
    aboutUS(51),
    aboutAPP(52),
    achievement(53),
    mouthRank(54),
    rankTen(55),
    yearRank(56),
    RankByYear(57),
    suggestAdd(58),
    changeAvatar(59),
    RiskTest(60),
    ForgetPass(61),
    rankBetween(62),
    logout(63),
    accountInfo(64),
    deviceInfo(65),
    getSmsWithoutCheck(66),
    getAllNews(67),
    getNewsIndex(68),
    getNewsType(69),
    getNewsTypeList(70),
    getNewsDetail(71),
    getNewsUpdate(72),
    /**
     * 活动分类列表
     */
    getActivityTypeList(73),
    getProductType(74),
    getProductTypeList(75),
    /**
     * 新的产品详情
     */
    getProductDetail(76),
    getReportType(77),
    getReportListByType(78),
    getReportLists(79),
    getReportClick(80),
    dividend(81),
    redemption(82);




    int type;

    HttpConfig(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
