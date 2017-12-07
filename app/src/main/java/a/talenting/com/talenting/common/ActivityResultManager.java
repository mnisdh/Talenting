package a.talenting.com.talenting.common;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daeho on 2017. 12. 7..
 */

public class ActivityResultManager {
    private List<ActivityResultItem> items = new ArrayList<>();

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for(int i = 0; i < items.size(); i++){
            ActivityResultItem item = items.get(i);

            if(item.getRequestCode() == requestCode) {
                item.onActivityResult(resultCode, data);
                items.remove(item);
                i--;
            }
        }
    }

    public void addItem(ActivityResultItem item){
        items.add(item);
    }

    interface IActivityResultManagerEvent{
        void onActivityResult(int resultCode, Intent data);
    }
}

class ActivityResultItem {
    private int requestCode;
    private ActivityResultManager.IActivityResultManagerEvent event;

    public ActivityResultItem(int requestCode, ActivityResultManager.IActivityResultManagerEvent event){
        this.requestCode = requestCode;
        this.event = event;
    }

    public int getRequestCode(){
        return requestCode;
    }

    public void onActivityResult(int resultCode, Intent data){
        if(event != null) event.onActivityResult(resultCode, data);
    }
}
