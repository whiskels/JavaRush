package com.javarush.games.moonlander;

import java.util.List;
import com.javarush.engine.cell.*;

public class RocketFire extends GameObject{
    private List<int[][]> frames;
    private int frameIndex;
    private boolean isVisible;


    public RocketFire(List<int[][]> frameList) {
        super(0, 0, frameList.get(0));
        frames = frameList;
        frameIndex = 0;
        isVisible = false;
    }

    private void nextFrame() {
        frameIndex++;
        if (frameIndex >= frames.size()) {
            frameIndex = 0;
        }
        this.matrix = frames.get(frameIndex);
    }

    @Override
    public void draw(Game game) {
        if (isVisible) {
            nextFrame();
            super.draw(game);
        } else return;
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }


}
