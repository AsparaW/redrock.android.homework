package asparaw.pers.qdlog.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import asparaw.pers.qdlog.DataBean.Status;
import asparaw.pers.qdlog.LogResolver.CallLogResolver;
import asparaw.pers.qdlog.R;
import asparaw.pers.qdlog.adapter.MyFragmentPagerAdapter;
import asparaw.pers.qdlog.adapter.MyRecAdapter;
import asparaw.pers.qdlog.fragment.LogFragment;

public class MainActivity extends AppCompatActivity {

    private static final int TYPE_IN = 0;
    private static final int TYPE_OUT=1;
    private static final int TYPE_MISS = 2;


    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

        private void initViews() {
        //check
            if (checkPermission()) {
            } else {
                Toast.makeText(this, "缺少读取权限", Toast.LENGTH_SHORT).show();
                requestPermission();
            }//endcheck
            //init
            mTabLayout = findViewById(R.id.tab_layout);
            mViewPager = findViewById(R.id.view_pager);

            List<String> titles = new ArrayList<>();
            List<LogFragment> fragmentList = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                if (i==TYPE_IN){
                    titles.add("呼入电话");
                }else if (i==TYPE_OUT){
                    titles.add("呼出电话");
                }else if (i==TYPE_MISS){
                    titles.add("未接来电");
                }
                ArrayList<Status> resList = new ArrayList<>();
                resList = (ArrayList<Status>)initData(i).clone();
                LogFragment simpleFragment = new LogFragment();
                simpleFragment.setData(resList);
                fragmentList.add(simpleFragment);
            }


            MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
            mViewPager.setAdapter(adapter);
            mTabLayout.setupWithViewPager(mViewPager);//end init
        }

        //

    private ArrayList<Status> initData(int type){
        ArrayList<Status> resList = new ArrayList();
        CallLogResolver myclr= new CallLogResolver();
        List<Status> templist = new ArrayList();
        templist = (ArrayList)myclr.getCallInfos(this).clone();

        for (Status temp:templist){
            String TAG  = "gET DATA";
            Log.d(TAG, "setData: "+temp.getName()+"date"+temp.getDate()+"type"+temp.getType());
            if (temp.getType()==type){
                resList.add(temp);
                String TAG2  = "ADD DATA";
                Log.d(TAG2, "setData: "+temp.getName()+"date"+temp.getDate()+"type"+temp.getType());
            }
        }
        return resList;
    }
        //PER

    private boolean checkPermission() {
        boolean hasPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED;
        if (hasPermission)
        hasPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED;
        return hasPermission;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions
                (this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG,Manifest.permission.SEND_SMS}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                }
        }
    }

/*
    public void onItemClick(View view) {
        RecyclerView temp = view.findViewById(R.id.review);
        int childAdapterPosition = temp.getChildAdapterPosition(view);
        Toast.makeText(this, "item click index = "+childAdapterPosition, Toast.LENGTH_SHORT).show();
    }

*/


}
/*
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull MotionEvent e) {
                final String items[] = {"电话", "短信"};
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("选择操作")
                        .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getActivity(), items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which==1){
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse("tel:" + rv.getChildItemId(basicView)).get);
                                    startActivity(intent);
                                }else if (which==2){

                                }
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

 */