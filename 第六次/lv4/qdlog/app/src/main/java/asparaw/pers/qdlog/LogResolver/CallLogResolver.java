package asparaw.pers.qdlog.LogResolver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import asparaw.pers.qdlog.DataBean.Status;

public class CallLogResolver {
    private static final int TYPE_IN = 0;
    private static final int TYPE_OUT=1;
    private static final int TYPE_MISS = 2;



    public static ArrayList<Status> getCallInfos(Context context) {
        ArrayList<Status> infos = new ArrayList<Status>();
        ContentResolver resolver = context.getContentResolver();
        // uri的写法需要查看源码JB\packages\providers\ContactsProvider\AndroidManifest.xml中内容提供者的授权
        // 从清单文件可知该提供者是CallLogProvider，且通话记录相关操作被封装到了Calls类中
        Uri uri =CallLog.Calls.CONTENT_URI;
        String[] projection = new String[]{
                CallLog.Calls.NUMBER, // 号码
                CallLog.Calls.DATE,   // 日期
                CallLog.Calls.TYPE,    // 类型：来电、去电、未接
                CallLog.Calls.CACHED_NAME
        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);
        while (cursor.moveToNext()){
            String number = cursor.getString(0);
            long date = cursor.getLong(1);
            int type = cursor.getInt(2);
            String name = cursor.getString(3);
            switch (type){
                case CallLog.Calls.INCOMING_TYPE:
                    type = TYPE_IN;
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    type = TYPE_OUT;
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    type = TYPE_MISS;
                    break;
            }
            if (name==null){
                name = number;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateString = format.format(date);
            infos.add(new Status(name, dateString, type));
        }
        cursor.close();
        return infos;
    }

}
