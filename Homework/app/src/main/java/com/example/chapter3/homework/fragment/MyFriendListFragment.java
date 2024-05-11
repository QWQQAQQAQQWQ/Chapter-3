package com.example.chapter3.homework.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chapter3.homework.R;

public class MyFriendListFragment extends Fragment {

    private LottieAnimationView animationView;

    public FriendListFragment() {
        // Required empty public constructor
    }

    public static FriendListFragment newInstance() {
        return new FriendListFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);



        animationView = view.findViewById(R.id.animation_view);
        showLoadingAnimation();
        return view;
    }

    private void showLoadingAnimation() {


        animationView.setVisibility(View.VISIBLE);
        animationView.setAlpha(0f);
        animationView.playAnimation();


        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(animationView, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(1000);
        fadeInAnimator.setInterpolator(new DecelerateInterpolator());
        fadeInAnimator.start();


        new Handler().postDelayed(() -> {

            ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(animationView, "alpha", 1f, 0f);
            fadeOutAnimator.setDuration(1000);
            fadeOutAnimator.setInterpolator(new AccelerateInterpolator());
            fadeOutAnimator.start();


            fadeOutAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    animationView.setVisibility(View.GONE);

                    fillListViewWithData();

                }




            });
        }, 5000);
    }

    private void fillListViewWithData() {
        ListView listView = getView().findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        adapter.add("Friend 1");
        adapter.add("Friend 2");

        listView.setAdapter(adapter);


        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(listView, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(1000);
        fadeInAnimator.start();
    }
}