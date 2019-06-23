package org.amfoss.templeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import org.amfoss.templeapp.R;


public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;


    // Constructors
    public MyArrayAdapter(Context context, List<MyDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MyDataModel item = getItem(position);

        String[] str = item.getName().split("     ");
        String lastWord = str[0].substring(str[0].lastIndexOf(" ") + 1);

        vh.textViewName.setText(str[1] + "     " + str[2]);
        vh.type.setText(lastWord);
        vh.textViewId.setText(item.getId());

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.listview_animator);
        vh.rootView.startAnimation(animation);

        return vh.rootView;
    }


    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textViewId;
        public final TextView textViewName;
        public final TextView type;
        public final RelativeLayout relativeLayout;

        private ViewHolder(RelativeLayout rootView, TextView textViewName, TextView textViewId, TextView type, RelativeLayout relativeLayout) {
            this.rootView = rootView;
            this.textViewId = textViewId;
            this.textViewName = textViewName;
            this.type = type;
            this.relativeLayout = relativeLayout;

        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewId = rootView.findViewById(R.id.textViewId);
            TextView textViewName = rootView.findViewById(R.id.textViewName);
            TextView type = rootView.findViewById(R.id.imageText);
            RelativeLayout relativeLayout = rootView.findViewById(R.id.relativelayout);

            return new ViewHolder(rootView, textViewName, textViewId, type, relativeLayout);
        }
    }
}