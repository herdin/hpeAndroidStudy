package hp.edu.binderservice;

/**
 * Created by hanla on 2017-08-02.
 */

public interface IService {
    public void registCallback(ICallback iCallback);
    public void unregistCallBack(ICallback iCallback);
    public void onPlay();
    public String onPlay(int flag);
    public void onStop();
    public String onStop(int flag);
    public void onPause();
    public String onPause(int flag);
}//END OF INTERFACE
