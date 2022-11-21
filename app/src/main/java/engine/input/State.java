package engine.input;

import engine.Kernel;

public interface State {

    void up(Kernel kernel);
    void right(Kernel kernel);
    void down(Kernel kernel);
    void left(Kernel kernel);
    void escape(Kernel kernel);
    void pause(Kernel kernel);



}
