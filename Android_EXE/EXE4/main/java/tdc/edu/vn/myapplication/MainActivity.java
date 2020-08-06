package tdc.edu.vn.myapplication;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvDanhSach;
    ImageView imageView1,imgZing;
    ImageButton imgbtnPlay, imgbtnStop, imgbtnPrev, imgNext;
    TextView tenbaihat, tgdau, tgcuoi;
    SeekBar seekBar;
    ArrayList<Song> listbaihat;
    MediaPlayer mediaPlayer = new MediaPlayer();
    int position ;
   // Animation animation;
    SeekBar seekBarVolumn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        DanhSach();
        Adapter_BaiHat adapter_baiHat = new Adapter_BaiHat(MainActivity.this,R.layout.list_song,listbaihat);
        lvDanhSach.setAdapter(adapter_baiHat);
        mediaPlayer = MediaPlayer.create(MainActivity.this, listbaihat.get(position).getFile());
    }

    private void DanhSach() {
        listbaihat = new ArrayList<Song>();
        listbaihat.add(new Song("Bạc Phận", R.drawable.bacphan, R.raw.bacphan));
        listbaihat.add(new Song("Sóng Gió", R.drawable.songgio, R.raw.songgio));
        listbaihat.add(new Song("Tướng Quân", R.drawable.tuongquan, R.raw.tuongquan));
        listbaihat.add(new Song("Bánh Mì Không", R.drawable.banhmikhong, R.raw.banhmikhong));
        listbaihat.add(new Song("Như Ngày Hôm Qua", R.drawable.nhungayhomqua, R.raw.nhungayhomqua));
        listbaihat.add(new Song("Hành Lang Cũ", R.drawable.hanhlangcu, R.raw.hanhlangcu));

    }
    private void play() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, listbaihat.get(position).getFile());
        //tenbaihat.setText("Đang Phát "+listbaihat.get(position).getTenbaihat());
        mediaPlayer.start();
    }
    private void setEvent() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.quay);
        imgZing.startAnimation(animation);
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position2, long id) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                else {
                    position = position2;
                    play();
                }
                imgbtnPlay.setImageResource(R.drawable.pause);
            }
        });
        imgbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgbtnPlay.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    imgbtnPlay.setImageResource(R.drawable.pause);
                }
                setTime();
                updateTime();
            }
        });
        imgbtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                imgbtnPlay.setImageResource(R.drawable.play);
                //khoi tao lai bai hat
                 play();
                setTime();
                updateTime();
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > listbaihat.size() -1){
                    position = 0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                //khoi tao lai bai hat
                play();
                mediaPlayer.start();
                imgbtnPlay.setImageResource(R.drawable.pause);
                setTime();
                updateTime();
            }
        });

        imgbtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0){
                    position = listbaihat.size() -1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                //khoi tao lai bai hat
                play();
                mediaPlayer.start();
                imgbtnPlay.setImageResource(R.drawable.pause);
                setTime();
                updateTime();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

    }
    private void setTime(){
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        tgcuoi.setText(dinhDangGio.format(mediaPlayer.getDuration()) + "");

        //lay tong thoi gian 1 bai hat
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                tgdau.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()) + "");


                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > listbaihat.size() - 1){
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }

                        //khoi tao lai bai hat
                        mediaPlayer = MediaPlayer.create(MainActivity.this, listbaihat.get(position).getFile());
                        tenbaihat.setText(listbaihat.get(position).getTenbaihat());
                        mediaPlayer.start();
                        imgbtnPlay.setImageResource(R.drawable.ic_pause);
                        setTime();
                        updateTime();
                    }
                });
                handler.postDelayed(this,500);
            }
        }, 100);
    }

    private void volumn(){
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolumn = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBarVolumn.setMax(maxVolumn);

        seekBarVolumn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekBarVolumn.getProgress(), 0);
            }
        });
    }
    private void setControl() {
        imgbtnPlay = (ImageButton) findViewById(R.id.imgbtn_play);
        imgbtnStop = (ImageButton) findViewById(R.id.imgbtn_stop);
        imgbtnPrev = (ImageButton) findViewById(R.id.imgbtn_prev);
        imgNext = (ImageButton) findViewById(R.id.imgbtn_next);
        tgdau = (TextView) findViewById(R.id.tv_dau);
        tgcuoi = (TextView) findViewById(R.id.tv_cuoi);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        lvDanhSach = findViewById(R.id.lvBaiHat);
        imgZing = findViewById(R.id.imgZing);
    }
}
