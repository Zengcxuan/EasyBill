package finalhomework.tcl.com.finalhomework.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
<<<<<<< HEAD:HomeWork/app/src/main/java/finalhomework/tcl/com/finalhomework/ui/adapter/ViewPagerAdapter.java
=======
import android.view.ViewGroup;

>>>>>>> 0395cfdd3c651dcd9b3ececa9dfa2775302a9572:HomeWork/app/src/main/java/finalhomework/tcl/com/finalhomework/UI/adapter/ViewPagerAdapter.java
import java.util.ArrayList;
import java.util.List;



public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        mFragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }


}
