package com.example.trafficracer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    Thread thread;
    boolean isPlaying, isPaused = false, isGameOver = false;

    SurfaceHolder holder;
    Paint paint;
    Random random;

    int screenX, screenY;
    int speed = 10;
    int score = 0;

    // Background
    Bitmap bg;
    int bgY1 = 0, bgY2;

    // Player
    Bitmap player;
    float playerX;
    int playerY;
    int lane = 1;
    int[] lanes;

    // 5-step smooth movement
    int moveSteps = 0;
    float stepSize = 0;
    float targetX = 0;

    // Screen shake
    int shakeX = 0;
    int shakeTimer = 0;

    // Buttons
    Rect pauseBtn, restartBtn;

    // Enemy
    class Enemy {
        Bitmap bmp;
        int x, y;
    }
    ArrayList<Enemy> enemies;

    public GameView(Context context) {
        super(context);

        holder = getHolder();
        paint = new Paint();
        random = new Random();

        screenX = getResources().getDisplayMetrics().widthPixels;
        screenY = getResources().getDisplayMetrics().heightPixels;

        // Background
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bgnd);
        bg = Bitmap.createScaledBitmap(bg, screenX, screenY, false);
        bgY2 = -screenY;

        // Lanes
        lanes = new int[]{
                screenX / 6,
                screenX / 2 - 90,
                screenX * 5 / 6 - 180
        };

        // Player
        player = BitmapFactory.decodeResource(getResources(), R.drawable.car);
        player = Bitmap.createScaledBitmap(player, 180, 300, false);

        playerX = lanes[1];
        targetX = playerX;
        playerY = screenY - player.getHeight() - 50;

        // Buttons
        pauseBtn = new Rect(screenX - 200, 30, screenX - 40, 110);
        restartBtn = new Rect(
                screenX / 2 - 220, screenY / 2 + 100,
                screenX / 2 + 220, screenY / 2 + 180
        );

        // Enemies
        enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) enemies.add(createEnemy());
    }

    private Enemy createEnemy() {
        Enemy e = new Enemy();

        int[] imgs = {
                R.drawable.enemy1,
                R.drawable.enemy2,
                R.drawable.enemy3
        };

        e.bmp = BitmapFactory.decodeResource(
                getResources(),
                imgs[random.nextInt(imgs.length)]
        );
        e.bmp = Bitmap.createScaledBitmap(e.bmp, 180, 300, false);

        e.x = lanes[random.nextInt(3)];
        e.y = -random.nextInt(screenY);

        return e;
    }

    @Override
    public void run() {
        while (isPlaying) {
            if (!isPaused && !isGameOver) update();
            draw();
            sleep();
        }
    }

    private void update() {

        // Background scroll
        bgY1 += speed;
        bgY2 += speed;
        if (bgY1 >= screenY) bgY1 = -screenY;
        if (bgY2 >= screenY) bgY2 = -screenY;

        // 5-step smooth movement
        if (moveSteps > 0) {
            playerX += stepSize;
            moveSteps--;
        }

        // Speed increase
        score++;
        if (score % 300 == 0) speed++;

        Rect playerRect = new Rect(
                (int) playerX, playerY,
                (int) playerX + player.getWidth(),
                playerY + player.getHeight()
        );

        for (Enemy e : enemies) {
            e.y += speed;

            if (e.y > screenY) {
                e.y = -random.nextInt(screenY);
                e.x = lanes[random.nextInt(3)];
            }

            Rect enemyRect = new Rect(
                    e.x, e.y,
                    e.x + e.bmp.getWidth(),
                    e.y + e.bmp.getHeight()
            );

            if (Rect.intersects(playerRect, enemyRect)) {
                isGameOver = true;
                shakeTimer = 15;
            }
        }

        // Screen shake
        if (shakeTimer > 0) {
            shakeX = random.nextInt(20) - 10;
            shakeTimer--;
        } else {
            shakeX = 0;
        }
    }

    private void draw() {
        if (!holder.getSurface().isValid()) return;

        Canvas canvas = holder.lockCanvas();
        canvas.translate(shakeX, 0);

        // Background
        canvas.drawBitmap(bg, 0, bgY1, paint);
        canvas.drawBitmap(bg, 0, bgY2, paint);

        // Cars
        canvas.drawBitmap(player, playerX, playerY, paint);
        for (Enemy e : enemies) {
            canvas.drawBitmap(e.bmp, e.x, e.y, paint);
        }

        // Score
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 40, 70, paint);

        // Pause button
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(pauseBtn, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        canvas.drawText("PAUSE", pauseBtn.left + 30, pauseBtn.bottom - 25, paint);

        // Pause text
        if (isPaused) {
            paint.setTextSize(90);
            canvas.drawText("PAUSED",
                    screenX / 2 - 180, screenY / 2, paint);
        }

        // Game Over
        if (isGameOver) {
            paint.setTextSize(90);
            canvas.drawText("GAME OVER",
                    screenX / 2 - 260, screenY / 2, paint);

            paint.setColor(Color.RED);
            canvas.drawRect(restartBtn, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            canvas.drawText("RESTART",
                    restartBtn.left + 80, restartBtn.bottom - 30, paint);
        }

        holder.unlockCanvasAndPost(canvas);
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (Exception ignored) {}
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (Exception ignored) {}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            // Pause
            if (pauseBtn.contains(x, y)) {
                isPaused = !isPaused;
                return true;
            }

            // Restart
            if (isGameOver && restartBtn.contains(x, y)) {
                restartGame();
                return true;
            }

            // Lane change → 5 steps
            if (!isPaused && !isGameOver) {
                if (x < screenX / 2) lane--;
                else lane++;

                if (lane < 0) lane = 0;
                if (lane > 2) lane = 2;

                targetX = lanes[lane];
                stepSize = (targetX - playerX) / 5f;
                moveSteps = 5;
            }
        }
        return true;
    }

    private void restartGame() {
        score = 0;
        speed = 10;
        lane = 1;
        playerX = lanes[1];
        targetX = playerX;
        isGameOver = false;

        enemies.clear();
        for (int i = 0; i < 3; i++) enemies.add(createEnemy());
    }
}
