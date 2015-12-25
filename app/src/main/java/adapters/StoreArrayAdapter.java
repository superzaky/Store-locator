package adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.yomac_000.store_locator.R;

import java.util.List;

import model.ParentStore;
import model.Store;

/**
 * Created by yomac_000 on 20-12-2015.
 */
public class StoreArrayAdapter extends BaseExpandableListAdapter {
    private List<ParentStore> parentStoresList;
    private Context context;

    public StoreArrayAdapter(Context context, List<ParentStore> parentStoresList) {
        this.context = context;
        this.parentStoresList = parentStoresList;
    }

    @Override
    public int getGroupCount() {
        return parentStoresList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentStoresList.get(groupPosition).getChildStoresList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentStoresList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentStoresList.get(groupPosition).getChildStoresList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, parent, false);
        }
        final ParentStore parentStore = (ParentStore) getGroup(groupPosition);
        TextView tvID = (TextView) convertView.findViewById(R.id.txtId);
        tvID.setText("Facebook ID: " + parentStore.getChildStoresList().get(groupPosition).getFacebookID());
        tvID.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        TextView tvName = (TextView) convertView.findViewById(R.id.txtName);
        tvName.setText(parentStore.getChildStoresList().get(groupPosition).getName());
        tvName.setTextColor(ContextCompat.getColor(context, R.color.yellow));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, parent, false);
        }
        //final Store store = (Store) getChild(groupPosition, groupPosition);
        final Store store = (Store) getChild(groupPosition, childPosition);
        TextView title = (TextView) convertView.findViewById(R.id.lblListItem);
        title.setText(store.getFacebookID());
        title.setTextColor(ContextCompat.getColor(context, R.color.white));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
