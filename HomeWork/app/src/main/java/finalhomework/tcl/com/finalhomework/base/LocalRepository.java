package finalhomework.tcl.com.finalhomework.base;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.greendao.DaoSession;
import finalhomework.tcl.com.finalhomework.greendao.SortBillDao;
import finalhomework.tcl.com.finalhomework.greendao.TotalBillDao;

import finalhomework.tcl.com.finalhomework.pojo.BPay;

import finalhomework.tcl.com.finalhomework.pojo.SortBill;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LocalRepository {

//    private static final String TAG = "LocalRepository";
//    private static final String DISTILLATE_ALL = "normal";
//    private static final String DISTILLATE_BOUTIQUES = "distillate";

    private static volatile LocalRepository sInstance;
    private DaoSession mSession;

    private LocalRepository() {
        mSession = DaoDBHelper.getInstance().getSession();
    }

    public static LocalRepository getInstance() {
        if (sInstance == null) {
            synchronized (LocalRepository.class) {
                if (sInstance == null) {
                    sInstance = new LocalRepository();
                }
            }
        }
        return sInstance;
    }

    /******************************save**************************************/
    public Observable<TotalBill> saveTotalBill(final TotalBill bill) {
        return Observable.create(new ObservableOnSubscribe<TotalBill>() {
            @Override
            public void subscribe(ObservableEmitter<TotalBill> e) throws Exception {
                mSession.getTotalBillDao().insert(bill);
                e.onNext(bill);
                e.onComplete();
            }
        });
    }

    /**
     * 批量添加账单
     * @param TotalBills
     */
    public void saveTotalBills( List<TotalBill> TotalBills) {
       mSession.getTotalBillDao().insertInTx(TotalBills);
    }



    public Long saveSortBill(SortBill sort) {
        return mSession.getSortBillDao().insert(sort);
    }

    /**
     * 批量添加账单分类
     *
     * @param sorts
     */
    public void saveSortBills(List<SortBill> sorts) {
        for (SortBill sort : sorts)
            saveSortBill(sort);
    }
    /**
     * 批量添加支付方式
     *
     * @param pays
     */
    public void saveBPays(List<BPay> pays) {
        for (BPay pay : pays)
            saveBPay(pay);
    }

    /**
     * 批量添加账单分类
     *
     * @param sorts
     */
    public void saveBsorts(List<SortBill> sorts) {
        for (SortBill sort : sorts)
            saveBSort(sort);
    }

    /**
     * 批量添加账单
     * @param
     */

    public Long saveBPay(BPay pay) {
        return mSession.getBPayDao().insert(pay);
    }

    public Long saveBSort(SortBill sort) {
        return mSession.getSortBillDao().insert(sort);
    }

    /******************************get**************************************/
    public TotalBill getTotalBillById(int id) {
        return mSession.getTotalBillDao().queryBuilder()
                .where(TotalBillDao.Properties.Id.eq(id)).unique();
    }

    public List<TotalBill> getTotalBills() {
        return mSession.getTotalBillDao().queryBuilder().list();
    }

    public Observable<List<TotalBill>> getTotalBillByUserId(int id) {
        QueryBuilder<TotalBill> queryBuilder = mSession.getTotalBillDao()
                .queryBuilder()
                .where(TotalBillDao.Properties.Userid.eq(id));
        return queryListToRx(queryBuilder);
    }

    public Observable<List<TotalBill>> getTotalBillByUserIdWithYM(int id, String year, String month) {
        String startStr = year + "-" + month + "-00 00:00:00";
        Date date = DateUtils.str2Date(startStr);
        Date endDate = DateUtils.addMonth(date, 1);
        QueryBuilder<TotalBill> queryBuilder = mSession.getTotalBillDao()
                .queryBuilder()
                .where(TotalBillDao.Properties.Crdate.between(DateUtils.getMillis(date), DateUtils.getMillis(endDate)))
                .where(TotalBillDao.Properties.Version.ge(0))
                .orderDesc(TotalBillDao.Properties.Crdate);
        return queryListToRx(queryBuilder);
    }
    public Observable<List<TotalBill>> getTotalBillByUserIdWithYM2(String id, String year, String month) {
        String startStr = year + "-" + month + "-00 00:00:00";
        Date date = DateUtils.str2Date(startStr);
        Date endDate = DateUtils.addMonth(date, 1);
        QueryBuilder<TotalBill> queryBuilder = mSession.getTotalBillDao()
                .queryBuilder()
                .where(TotalBillDao.Properties.Userid.eq(id))
                .where(TotalBillDao.Properties.Crdate.between(DateUtils.getMillis(date), DateUtils.getMillis(endDate)))
                .where(TotalBillDao.Properties.Version.ge(0))
                .orderDesc(TotalBillDao.Properties.Crdate);
        return queryListToRx(queryBuilder);
    }
    public Observable<List<TotalBill>> getTotalBillByUserIdWithAll(int id, String year, String month) {
        String startStr = year + "-" + month + "-00 00:00:00";
        Date date = DateUtils.str2Date(startStr);
        Date endDate = DateUtils.addMonth(date, 1);
        QueryBuilder<TotalBill> queryBuilder = mSession.getTotalBillDao()
                .queryBuilder()
                .where(TotalBillDao.Properties.Crdate.between(DateUtils.getMillis(date), DateUtils.getMillis(endDate)))
                .where(TotalBillDao.Properties.Version.ge(0))
                .orderDesc(TotalBillDao.Properties.Crdate);
        return queryListToRx(queryBuilder);
    }
    /*public Observable<List<TotalBill>> getTotalBillByUserIdWithYMD(int id, String year, String month, String day) {
        String startStr = year + "-" + month +"-"+ day+" 00:00:00";
        Date date = DateUtils.str2Date(startStr);

        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        Date begin = DateUtils.addDay(date,-i);
        Date end = DateUtils.addDay(date,i);

        Date endDate = DateUtils.addMonth(date, 1);
        QueryBuilder<TotalBill> queryBuilder = mSession.getTotalBillDao()
                .queryBuilder()
                .where(TotalBillDao.Properties.Crdate.between(DateUtils.getMillis(begin), DateUtils.getMillis(end)))
                .where(TotalBillDao.Properties.Version.ge(0))
                .orderDesc(TotalBillDao.Properties.Crdate);
        return queryListToRx(queryBuilder);
    }*/

    public Observable<List<SortBill>> getSortBill(boolean income){
        QueryBuilder<SortBill> queryBuilder = mSession.getSortBillDao()
                .queryBuilder()
                .where(SortBillDao.Properties.Income.eq(income));
        return queryListToRx(queryBuilder);
    }

    public Observable<List<SortBill>> getSortBill(){
        QueryBuilder<SortBill> queryBuilder = mSession.getSortBillDao()
                .queryBuilder();
        return queryListToRx(queryBuilder);
    }




    /******************************update**************************************/

    /**
     * 更新账单（用于同步）
     * @param bill
     */
    public void updateTotalBillByBmob(TotalBill bill) {
        mSession.getTotalBillDao().update(bill);
    }

    /**
     * 更新账单
     * @param bill
     * @return
     */
    public Observable<TotalBill> updateTotalBill(final TotalBill bill) {
       
        return Observable.create(new ObservableOnSubscribe<TotalBill>() {
            @Override
            public void subscribe(ObservableEmitter<TotalBill> e) throws Exception {
                mSession.getTotalBillDao().update(bill);
                e.onNext(bill);
                e.onComplete();
            }
        });
    }

    /******************************delete**************************************/
    /**
     * 删除账单分类
     * @param id
     */
    public void deleteSortBillById(Long id){
        mSession.getSortBillDao().deleteByKey(id);
    }

    /**

    /**
     * 批量删除账单（便于账单同步）
     * @param TotalBills
     */
    public void deleteBills(List<TotalBill> TotalBills){
        mSession.getTotalBillDao().deleteInTx(TotalBills);
    }

    /**
     * 删除本地所有账单
     */
    public void deleteAllBills(){
        deleteBills(getTotalBills());
    }

    public Observable<Long> deleteTotalBillById(Long id) {
        mSession.getTotalBillDao().deleteByKey(id);
        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                e.onNext(new Long(0));
                e.onComplete();
            }
        });
    }

    /******************************Rx**************************************/
    private <T> Observable<T> queryToRx(final QueryBuilder<T> builder) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                T data = builder.list().get(0);
                e.onNext(data);
                e.onComplete();
            }
        });
    }

    private <T> Observable<List<T>> queryListToRx(final QueryBuilder<T> builder) {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> e) throws Exception {
                List<T> data = builder.list();
                e.onNext(data);
                e.onComplete();
            }
        });
    }

}
