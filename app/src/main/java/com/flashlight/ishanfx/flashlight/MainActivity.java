package com.flashlight.ishanfx.flashlight;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnSwitch;
    private Camera camera;
    private Camera.Parameters parameter;
    private boolean isFlash;
    private boolean isFlashLightOn = false;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.main);
        btnSwitch = (ImageButton) findViewById(R.id.flash_light);
        isFlash = getApplication().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!isFlash){
            Toast.makeText(MainActivity.this, "Camera Device Not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            this.camera = Camera.open(0);
            parameter = this.camera.getParameters();
        }


        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFlashLightOn){
                    onFlashLight();
                }else{
                    offFlashLight();
                }
            }
        });
    }
    // getting camera parameters
    private void offFlashLight() {
        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        this.camera.setParameters(parameter);
        this.camera.stopPreview();
        isFlashLightOn = false;
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        //flashLight.setImageResource(R.drawable.buttonoff);
    }

    private void onFlashLight() {
        if(this.camera != null){
            parameter = this.camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            this.camera.setParameters(parameter);
            this.camera.startPreview();
            isFlashLightOn = true;
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        }
    }
    /*
* Turning On flash
*/


}
