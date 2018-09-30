package finalhomework.tcl.com.finalhomework.base;

public class Constants {

    //网络请求
    public static final String BASE_URL = "http://139.199.176.173:8080/ssmBillBook";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_SIGN = "/user/sign";
    public static final String USER_UPDATE = "/user/update";
    public static final String USER_CHANGEPW = "/user/changePw";
    public static final String USER_FORGETPW = "/user/forgetPw";
    public static final String BILL_MONTH_DETIAL = "/bill/user";
    public static final String BILL_MONTH_CHART = "/bill/chart";
    public static final String BILL_MONTH_CARD = "/bill/pay";
    public static final String BILL_DELETE = "/bill/delete";
    public static final String BILL_UPDATE = "/bill/update";
    public static final String BILL_ADD = "/bill/add";
    public static final String NOTE_USER = "/note/user";
    public static final String NOTE_SORT_ADD = "/note/sort/add";
    public static final String NOTE_SORT_UPDATE = "/note/sort/update";
    public static final String NOTE_PAY_ADD = "/note/pay/add";
    public static final String NOTE_PAY_UPDATE = "/note/pay/update";
    public static final String IMAGE_USER = "/upload/";
    public static final String IMAGE_SORT = "/upload/noteImg/sort/";
    public static final String IMAGE_PAY = "/upload/noteImg/pay/";


    public static final String CACHE = "cache";
    public static final int LATEST_COLUMN = Integer.MAX_VALUE;
    public static final int BASE_COLUMN = 100000000;

    //当前用户
    public static int currentUserId = 0;

    public static String EXTRA_IS_UPDATE_THEME = "com.copasso.cocobill.IS_UPDATE_THEME";

    //默认note(账单分类和支付方式)
    public static String BILL_NOTE = "{\"status\":100,\"message\":\"处理成功！\"," +
            "\"outSortlis\":[" +
            "{\"id\":1,\"uid\":0,\"sortName\":\"餐饮\",\"sortImg\":\"sort_meal.png\",\"priority\":0,\"income\":false}," +
            "{\"id\":2,\"uid\":0,\"sortName\":\"水果\",\"sortImg\":\"sort_fruit.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":3,\"uid\":0,\"sortName\":\"购物\",\"sortImg\":\"sort_shopping.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":4,\"uid\":0,\"sortName\":\"生活用品\",\"sortImg\":\"sort_supplies.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":5,\"uid\":0,\"sortName\":\"零食\",\"sortImg\":\"sort_snack.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":6,\"uid\":0,\"sortName\":\"蔬菜\",\"sortImg\":\"sort_vegetable.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":7,\"uid\":0,\"sortName\":\"娱乐\",\"sortImg\":\"sort_amusement.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":8,\"uid\":0,\"sortName\":\"住房\",\"sortImg\":\"sort_house.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":9,\"uid\":0,\"sortName\":\"医疗\",\"sortImg\":\"sort_medical.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":10,\"uid\":0,\"sortName\":\"教育\",\"sortImg\":\"sort_class.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":11,\"uid\":0,\"sortName\":\"服饰\",\"sortImg\":\"sort_clothes.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":12,\"uid\":0,\"sortName\":\"交通\",\"sortImg\":\"sort_traffic.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":13,\"uid\":0,\"sortName\":\"小孩\",\"sortImg\":\"sort_kid.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":14,\"uid\":0,\"sortName\":\"美容\",\"sortImg\":\"sort_cosmetology.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":15,\"uid\":0,\"sortName\":\"老人\",\"sortImg\":\"sort_older.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":16,\"uid\":0,\"sortName\":\"家具\",\"sortImg\":\"sort_furniture.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":17,\"uid\":0,\"sortName\":\"宠物\",\"sortImg\":\"sort_pet.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":18,\"uid\":0,\"sortName\":\"书籍\",\"sortImg\":\"sort_book.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":19,\"uid\":0,\"sortName\":\"旅行\",\"sortImg\":\"sort_travel.png\",\"priority\":null,\"income\":false}," +
            "{\"id\":20,\"uid\":0,\"sortName\":\"通讯\",\"sortImg\":\"sort_4g.png\",\"priority\":null,\"income\":false}]," +
            "\"inSortlis\":[" +
            "{\"id\":26,\"uid\":0,\"sortName\":\"生活费\",\"sortImg\":\"sort_living_expense.png\",\"priority\":null,\"income\":true}," +
            "{\"id\":27,\"uid\":0,\"sortName\":\"红包\",\"sortImg\":\"sort_red.png\",\"priority\":null,\"income\":true}," +
            "{\"id\":28,\"uid\":0,\"sortName\":\"工资\",\"sortImg\":\"sort_salary.png\",\"priority\":null,\"income\":true}," +
            "{\"id\":29,\"uid\":0,\"sortName\":\"理财\",\"sortImg\":\"sort_financing.png\",\"priority\":null,\"income\":true}," +
            "{\"id\":30,\"uid\":0,\"sortName\":\"兼职\",\"sortImg\":\"sort_parttimejob.png\",\"priority\":null,\"income\":true}," +
            "{\"id\":31,\"uid\":0,\"sortName\":\"社交\",\"sortImg\":\"sort_sociality.png\",\"priority\":null,\"income\":true}]," +
            "\"payinfo\":[" +
            "{\"id\":1,\"uid\":0,\"payName\":\"现金\",\"payImg\":\"card_cash.png\",\"payNum\":null}," +
            "{\"id\":2,\"uid\":0,\"payName\":\"支付宝\",\"payImg\":\"card_account.png\",\"payNum\":null}," +
            "{\"id\":3,\"uid\":0,\"payName\":\"微信\",\"payImg\":\"card_account.png\",\"payNum\":\"null\"}]}\n";
}
