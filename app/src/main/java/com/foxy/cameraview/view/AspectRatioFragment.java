package com.foxy.cameraview.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.cameraview.AspectRatio;

import java.util.Arrays;
import java.util.Set;

import static com.foxy.cameraview.ultis.Constants.ARG_ASPECT_RATIOS;
import static com.foxy.cameraview.ultis.Constants.ARG_CURRENT_ASPECT_RATIO;

public class AspectRatioFragment extends DialogFragment {

    private Listener mListener;

    public static AspectRatioFragment newInstance(Set<AspectRatio> ratios,
                                                  AspectRatio currentRatio) {
        final AspectRatioFragment fragment = new AspectRatioFragment();
        final Bundle args = new Bundle();
        args.putParcelableArray(ARG_ASPECT_RATIOS,
                ratios.toArray(new AspectRatio[ratios.size()]));
        args.putParcelable(ARG_CURRENT_ASPECT_RATIO, currentRatio);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mListener = (Listener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must be implement OnFragmentInteractionListener !");
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
        final AspectRatio[] ratios = (AspectRatio[]) args.getParcelableArray(ARG_ASPECT_RATIOS);
        if (ratios == null) {
            throw new RuntimeException("No ratios");
        }
        Arrays.sort(ratios);
        final AspectRatio current = args.getParcelable(ARG_CURRENT_ASPECT_RATIO);
        final AspectRatioAdapter adapter = new AspectRatioAdapter(ratios, current);
        return new AlertDialog.Builder(getActivity())
                .setAdapter(adapter, (dialog, position) -> mListener.onAspectRatioSelected(ratios[position]))
                .create();
    }

    private static class AspectRatioAdapter extends BaseAdapter {

        private final AspectRatio[] mRatios;
        private final AspectRatio mCurrentRatio;

        AspectRatioAdapter(AspectRatio[] ratios, AspectRatio current) {
            mRatios = ratios;
            mCurrentRatio = current;
        }

        @Override
        public int getCount() {
            return mRatios.length;
        }

        @Override
        public AspectRatio getItem(int position) {
            return mRatios[position];
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).hashCode();
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            AspectRatioAdapter.ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
                holder = new AspectRatioAdapter.ViewHolder();
                holder.text = view.findViewById(android.R.id.text1);
                view.setTag(holder);
            } else {
                holder = (AspectRatioAdapter.ViewHolder) view.getTag();
            }
            AspectRatio ratio = getItem(position);
            StringBuilder sb = new StringBuilder(ratio.toString());
            if (ratio.equals(mCurrentRatio)) {
                sb.append(" *");
            }
            holder.text.setText(sb);
            return view;
        }

        private static class ViewHolder {
            TextView text;
        }

    }

    public interface Listener {
        void onAspectRatioSelected(@NonNull AspectRatio ratio);
    }

}
