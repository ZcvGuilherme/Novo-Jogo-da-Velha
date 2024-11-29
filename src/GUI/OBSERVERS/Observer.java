package GUI.OBSERVERS;

import GAME.STATUS.GameStatus;

public interface Observer {
    void update(GameStatus status);
}
