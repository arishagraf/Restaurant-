package com.naaniz.naanizcustomer;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class AddRestaurantActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    private BarcodeDetector barcodeDetector;
    private EditText editText;


    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant_activity);
        initViews();

    }

    private void initViews() {
        surfaceView = findViewById(R.id.surfaceView);
        editText = findViewById(R.id.editText_restaurant_uid);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                                .setRequestedPreviewSize(1920, 1080)
                                .setAutoFocusEnabled(true) //you should add this feature
                                .build();
                        cameraSource.start(surfaceView.getHolder());

                        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
                            @Override
                            public void release() {
                                //    Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
                                Log.e("check 7 :", "To prevent memory leaks barcode scanner has been stopped");
                            }

                            @Override

                            public void receiveDetections(Detector.Detections<Barcode> detections) {
                                Log.e(" check 8 :", "detected");
                                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                                if (barcodes.size() != 0) {
                                    editText.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                                            vibrator.vibrate(1000);
                                            editText.setText(barcodes.valueAt(0).displayValue);
                                            cameraSource.stop();
                                        }
                                    });
                                }
                            }

                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private void initialiseDetectorsAndSources() {

        // Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
        Log.e("check 1 :", "Barcode scanner started");
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(AddRestaurantActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {


                        //  Toast.makeText(getApplicationContext(), "permission granted", Toast.LENGTH_LONG).show();
                        Log.e("check 2 :", "permission granted");

                        cameraSource.start(surfaceView.getHolder());

                    } else {
                        // Toast.makeText(getApplicationContext(), "ask permission", Toast.LENGTH_LONG).show();
                        Log.e("check 3 :", "ask permission");
                        ActivityCompat.requestPermissions(AddRestaurantActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //   Toast.makeText(getApplicationContext(), "Destroyed Surface", Toast.LENGTH_SHORT).show();
                Log.e("check 4 :", "Destroyed Surface");
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                // Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
                Log.e("check 5 :", "To prevent memory leaks barcode scanner has been stopped");
            }

            @Override

            public void receiveDetections(Detector.Detections<Barcode> detections) {
                Log.e("receiveDetections", "detected");

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            editText.setText(barcodes.valueAt(0).rawValue);
                            cameraSource.stop();

                        }
                    });
                }

            }


        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
        Log.e("check 6 :", "camera source release");
    }


    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();

    }


}




