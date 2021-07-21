package kr.hs.emirim.w2026.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ResultActivity2 extends AppCompatActivity {
    int[] imgVIds = {R.id.img01, R.id.img02, R.id.img03, R.id.img04, R.id.img05, R.id.img06, R.id.img07, R.id.img08, R.id.img09};
    ImageView[] imgv = new ImageView[imgVIds.length];
    ViewFlipper viewFlip;
    int[] imgSrcIds = {R.drawable.i01, R.drawable.i02, R.drawable.i03, R.drawable.i04, R.drawable.i05, R.drawable.i06, R.drawable.i07, R.drawable.i08, R.drawable.i09};
    int[] voteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        Intent intent = getIntent();
        voteCount = intent.getIntArrayExtra("voteCount");

        viewFlip = findViewById(R.id.view_flip);
        viewFlip.setFlipInterval(1000);
        sortDescImgSrc();
        for (int i=0; i < imgv.length; i++){
            imgv[i] = findViewById(imgVIds[i]);
            imgv[i].setImageResource(imgSrcIds[i]);
        }

        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(btnListener);
        btnStop.setOnClickListener(btnListener);

    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    viewFlip.startFlipping();
                    break;
                case R.id.btn_stop:
                    viewFlip.stopFlipping();
                    break;
            }
        }
    };

    protected void sortDescImgSrc(){

        for(int i=0; i<voteCount.length; i++) {
            for(int j=i+1; j<voteCount.length; j++) {
                if(voteCount[i] < voteCount[j]) { //내림차순
                    int tmp = voteCount[i];
                    int tempSrc = imgSrcIds[i];
                    voteCount[i] = voteCount[j];
                    imgSrcIds[i] = imgSrcIds[j];
                    voteCount[j] = tmp;
                    imgSrcIds[j] = tempSrc;
                }
            }
        }
        for (int i=0; i < voteCount.length; i++) {
            Log.i("Sorting 결과: ", voteCount[i]+"");
        }
    }
}