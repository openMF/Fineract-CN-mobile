package org.apache.fineract.ui.online.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author Rajan Maurya
 *         On 27/07/17.
 */

public class DashboardContract extends AppCompactActivity {
   private static long BackPressed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
        @Override
         public void onBackPressed(){
            if(BackPressed+2000>System.currentTimeMillis())
            {
                super.onBackPressed();
            }
            else{
                Toast.makeText(getBaseContext(),"Press once again to exit",Toast.LENGTH_SHORT).show();
            }
           BackPressed = System.currentTimeMillis();
        }
    }




