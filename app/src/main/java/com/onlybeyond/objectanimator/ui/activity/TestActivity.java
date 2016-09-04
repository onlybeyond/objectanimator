package com.onlybeyond.objectanimator.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.onlybeyond.objectanimator.R;
import com.onlybeyond.objectanimator.model.PictureBean;
import com.onlybeyond.objectanimator.ui.adapter.ImageAdapter;
import com.onlybeyond.objectanimator.ui.utils.FileUtils;

import java.io.StringReader;
import java.util.LinkedList;

import static com.onlybeyond.objectanimator.ui.utils.LogUtils.*;

/**
 * Created by only on 16/9/4.
 * Email: onlybeyond99@gmail.com
 */
public class TestActivity extends BaseActivity implements ImageAdapter.SelfItemClickListener {

    private static String TAG = makeLogTag(TestActivity.class);
    public static String TEST_ONE = "test_one";
    public static String TEST_TWO = "test_two";
    public static String TEST_THREE = "test_three";
    public static String TEST_FOUR = "test_four";

    //data
    private String mTestSort;

    private LinkedList<Integer> resourceList;
    private PictureBean[] mPictureBean;

    //view
    private RecyclerView rvImage;
    private ImageView ivTest;
    private ImageView ivTestBg;
    private TextView tvTitle;


    @Override
    public void initTop() {
        super.initTop();
        tvTitle = (TextView)findViewById(R.id.toolbar_title);

    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTestSort = extras.getString("test_sort");

        }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_test);
        ivTest = (ImageView) findViewById(R.id.iv_test);
        ivTestBg = (ImageView) findViewById(R.id.iv_test_bg);
        rvImage = (RecyclerView) findViewById(R.id.rv_image);
    }

    @Override
    public void fillDate() {



        rvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        resourceList = new LinkedList<>();
        resourceList.add(R.mipmap.test_one_img);
        resourceList.add(R.mipmap.test_tow_img);
        resourceList.add(R.mipmap.test_three_img);
        resourceList.add(R.mipmap.test_four_img);
        ImageAdapter adapter = new ImageAdapter(this, resourceList, this);
        rvImage.setAdapter(adapter);

        String pictureString = FileUtils.readAssets(this, "picture.json");
        JsonReader reader = new JsonReader(new StringReader(pictureString));
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
        JsonArray albumTest = jsonObject.get("albumTest").getAsJsonArray();
        mPictureBean = new Gson().fromJson(albumTest, PictureBean[].class);

        if (TEST_ONE.equals(mTestSort)) {
            ivTest.setImageResource(R.mipmap.test_meizhi_one_img);
            tvTitle.setText(getString(R.string.test_first));

        } else if (TEST_TWO.equals(mTestSort)) {
            ivTest.setImageResource(R.mipmap.test_meizi_two_img);
            tvTitle.setText(getString(R.string.test_second));

        } else if (TEST_THREE.equals(mTestSort)) {
            ivTest.setImageResource(R.mipmap.test_meizhi_three_img);
            tvTitle.setText(getString(R.string.test_three));


        } else if (TEST_FOUR.equals(mTestSort)) {
            ivTest.setImageResource(R.mipmap.test_meizhi_four_img);
            tvTitle.setText(getString(R.string.test_four));

        }

    }

    @Override
    public void selfItemClick(int position) {
        ivTestBg.setImageResource(resourceList.get(position));
        calculatePosition(position);

    }

    public void calculatePosition(int position) {


        if (position >= 0) {

            int height = ivTestBg.getHeight();
            int width = ivTestBg.getWidth();
            final float xBg = ivTestBg.getX();
            final float yBg = ivTestBg.getY();
            float x = ivTest.getTranslationX();
            float y = ivTest.getTranslationY();
            PictureBean pictureBean = mPictureBean[position];


            final float widthX = width * Float.parseFloat(pictureBean.getXScale());
            final float widthY = height * Float.parseFloat(pictureBean.getYScale());
            LOGD(TAG, "---x" + x + "---y" + y + "---width x" + widthX + "---height y" + widthY + "---bg height" + height + "---bg width" + width + "---bg x" + xBg + "--- bg y" + yBg);
            float scaleX = Float.parseFloat(pictureBean.getWScale());
            float scaleY = Float.parseFloat(pictureBean.getHScale());

            ObjectAnimator objectAnimator1=null;
            ObjectAnimator objectAnimator2=null;

            if (TEST_ONE.equals(mTestSort)) {
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("translationX",widthX);
                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("translationY", widthY);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX",
                        scaleX);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY",
                        scaleY);
                 objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhX, pvhX1);
                 objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhY, pvhZ);

            } else if (TEST_TWO.equals(mTestSort)) {
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", xBg + widthX);
                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("y", yBg + widthY);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX",
                        scaleX);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY",
                        scaleY);
                objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhX, pvhX1);
                objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhY, pvhZ);
            } else if (TEST_THREE.equals(mTestSort)) {
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", xBg + widthX);
                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("y", yBg + widthY);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX",
                        scaleX);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY",
                        scaleY);
                objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhX, pvhX1);
                objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhY, pvhZ);
            } else if (TEST_FOUR.equals(mTestSort)) {
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", xBg + widthX);
                PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("y", yBg + widthY);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX",
                        scaleX);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY",
                        scaleY);
                float ivCodeX = ivTest.getX();
                float ivCodeY = ivTest.getY();
                LOGD(TAG, "---iv code before x" + ivCodeX + "---iv code before y" + ivCodeY);
                ivTest.setPivotX(0);
                ivTest.setPivotY(0);
                ivTest.postInvalidate();
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(ivTest, pvhX, pvhX1, pvhY, pvhZ);
//        objectAnimator1.setProperty("");
                objectAnimator.setDuration(500).start();
            }





           if(objectAnimator1!=null&&objectAnimator2!=null) {
               objectAnimator1.addListener(new Animator.AnimatorListener() {
                   @Override
                   public void onAnimationStart(Animator animation) {
                       float ivCodeX = ivTest.getX();
                       float ivCodeY = ivTest.getY();
                       int top = ivTest.getTop();
                       int left = ivTest.getLeft();
                       LOGD(TAG, "---iv  code animator start x" + ivCodeX + "---iv code y" + ivCodeY + "---xbg +widthX" + (xBg + widthX) + "---top" + top + "---left" + left);

                   }

                   @Override
                   public void onAnimationEnd(Animator animation) {
                       ivTest.postInvalidate();
                       float ivCodeX = ivTest.getX();
                       float ivCodeY = ivTest.getY();
                       int top = ivTest.getTop();
                       int left = ivTest.getLeft();
                       LOGD(TAG, "---iv code animator end x" + ivCodeX + "---iv code y" + ivCodeY + "---xbg +widthX" + (xBg + widthX) + "---top" + top + "---left" + left);
                   }

                   @Override
                   public void onAnimationCancel(Animator animation) {

                   }

                   @Override
                   public void onAnimationRepeat(Animator animation) {

                   }
               });

               objectAnimator2.addListener(new Animator.AnimatorListener() {
                   @Override
                   public void onAnimationStart(Animator animation) {
                       float ivCodeX = ivTest.getX();
                       float ivCodeY = ivTest.getY();
                       int top = ivTest.getTop();
                       int left = ivTest.getLeft();
                       LOGD(TAG, "---iv code animator 1 start x" + ivCodeX + "---iv code start y" + ivCodeY + "---xbg +widthX" + (xBg + widthX) + "---top" + top + "---left" + left);

                   }

                   @Override
                   public void onAnimationEnd(Animator animation) {
                       float ivCodeX = ivTest.getX();
                       float ivCodeY = ivTest.getY();
                       int top = ivTest.getTop();
                       int left = ivTest.getLeft();
                       LOGD(TAG, "---iv code animator 1 end x" + ivCodeX + "---iv code start y" + ivCodeY + "---xbg +widthX" + (xBg + widthX) + "---top" + top + "---left" + left);

                   }

                   @Override
                   public void onAnimationCancel(Animator animation) {

                   }

                   @Override
                   public void onAnimationRepeat(Animator animation) {

                   }
               });


               AnimatorSet set = new AnimatorSet();
               set.play(objectAnimator2).after(objectAnimator1);
               set.setDuration(5000);
               set.start();
           }

//        objectAnimator;

        }


        /*ObjectAnimator//
                .ofFloat(ivCode, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();*/

    }

}
