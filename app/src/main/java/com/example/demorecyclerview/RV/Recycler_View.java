package com.example.demorecyclerview.RV;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demorecyclerview.R;
import com.example.demorecyclerview.common.ItemTouchHelderCallback;
import com.example.demorecyclerview.common.ItemTouchHelperLinner;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Recycler_View extends AppCompatActivity {

    private ArrayList<Hero> mListHero = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    // check ID
    Button maddImage, mClose;
    ImageView mImage;
    EditText mInfo;
    private Button mRefresh, mAdd;
    private Bitmap bitmap;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mapping();
        mAdapter = new RecyclerAdapter(mListHero);
        RecyclerView.LayoutManager mManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        swapRemove();
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });
    }

    private void dialogShow() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);

        maddImage = dialog.findViewById(R.id.button_addImage);
        mClose = dialog.findViewById(R.id.button_close);
        mImage = dialog.findViewById(R.id.image_add);
        mInfo = dialog.findViewById(R.id.text_info);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        maddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage();
                dialog.cancel();
            }
        });

        //show
        dialog.show();
    }

    private void addImage() {
        String title = "" + mInfo.getText().toString();
        Bitmap image = bitmap;
        if (title.isEmpty() || image.isMutable()){
            Toast.makeText(this, "Input again", Toast.LENGTH_SHORT).show();
        }
        else {
            mListHero.add(new Hero(title,image));
            mAdapter.notifyDataSetChanged();
        }
    }

    private void getImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {

                String[] permission = { Manifest.permission.READ_EXTERNAL_STORAGE };
                requestPermissions(permission, PERMISSION_CODE);
            } else {
                pickImage();
            }
        } else {
            pickImage();
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            } else {
                Toast.makeText(this, "Permission Denied.....", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // check id
    private void mapping() {
        mRefresh = findViewById(R.id.button_refresh);
        mRecyclerView = findViewById(R.id.recycler_main);
        mAdd = findViewById(R.id.button_add);
    }

    private void swapRemove() {
        /*
         * Cách 1: Code trược tiếp vào activity
         * kéo thar và thay đổi vị trí items trong list v*/

            /*   ItemTouchHelper mTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper
                .DOWN|ItemTouchHelper.RIGHT, 0) {
                    */
        /*
         * hàm này cho phép ta kéo từ vị trí này sang vi trí khác*//*
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            @NonNull RecyclerView.ViewHolder target) {
                        int start = viewHolder.getAdapterPosition();
                        int end = target.getAdapterPosition();
                        Collections.swap(mListHero, start, end);
                        mAdapter.notifyItemMoved(start, end);
                        return false;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                            int direction) {
                        int item = viewHolder.getAdapterPosition();
                        mListHero.remove(item);
                        mAdapter.notifyItemRemoved(item);

                    }
                });
        mTouchHelper.attachToRecyclerView(mRecyclerView);
*/
        /*
         * Cách 2: tạo 1 class riêng*/
        ItemTouchHelper.Callback callback =
                new ItemTouchHelderCallback(new ItemTouchHelperLinner() {
                    @Override
                    public void onItemMove(int fromPosition, int endPosition) {
                        Collections.swap(mListHero, fromPosition, endPosition);
                        mAdapter.notifyItemMoved(fromPosition, endPosition);
                    }

                    @Override
                    public void onItemDimiss(int position) {
                        mListHero.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }

                    @Override
                    public void onRowSelect(RecyclerAdapter.ViewHolder view) {
                        view.mImageHero.setBackgroundColor(Color.RED);
                        view.mTextName.setTextColor(Color.GREEN);
                    }

                    @Override
                    public void onRowClear(RecyclerAdapter.ViewHolder view) {
                        view.mTextName.setTextColor(Color.WHITE);
                        view.mImageHero.setBackgroundColor(Color.WHITE);
                    }
                });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Refresh button
     */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)  {
        if ((resultCode == RESULT_OK) && (requestCode == IMAGE_PICK_CODE)) {
            Uri uri = data.getData();
            Log.d("image","Da set image");
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                mImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else {
            Toast.makeText(this, "meo hien", Toast.LENGTH_SHORT).show();
        }
    }


}
